package jminusminus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JTryStatement extends JStatement {

	/** The try-block that might throw exception(s). */
	private JBlock tryBlock;
	
	/** The associated catch statements. */
	private ArrayList<JCatchStatement> catchStatements;
	
	/** The finally-block of this statement. */
	private JBlock finallyBlock;
	
	/** The local context of the try-block. */
	private LocalContext context;
	
	/** The variable that should store an uncaught exception in the
	 * finally-block. This is necessary to further throw the exception. */
	private JVariable uncaughtVariable;
	
	/**
	 * Constructs an AST-node for the try-catch-finally statement.
	 * @param line
	 * @param tryBlock
	 * @param catchParameters
	 * @param catchBlocks
	 * @param finallyBlock
	 */
	protected JTryStatement(int line, JBlock tryBlock, 
							ArrayList<JCatchStatement> catchStatements, 
							JBlock finallyBlock) {
		super(line);
		this.tryBlock = tryBlock;
		this.catchStatements = catchStatements;
		this.finallyBlock = finallyBlock;
	}

	@Override
	public JTryStatement analyze(Context context) {
		this.context = new LocalContext(context);
		Set<Type> caughtExceptions = new HashSet<Type>();
		// Analyze each catch statement and add the caught exceptions to this
		// context's allowed exceptions.
		for (int i = 0; i < catchStatements.size(); i++) {
			catchStatements.set(i, catchStatements.get(i).analyze(context));
			for (Type exception : catchStatements.get(i).getCaughtExceptions()) {
				// check that an exception type is caught only once
				if (caughtExceptions.contains(exception)) {
					JAST.compilationUnit.reportSemanticError(catchStatements.get(i).line(), 
							"The exception %s is already caught by another catch-"
							+ "statement.", exception.toString());
				}
				caughtExceptions.add(exception);
				this.context.addAllowedException(exception);
			}
		}
		if (finallyBlock != null) {
			finallyBlock = finallyBlock.analyze(context);
			// Initiate the variable used by a finally-block if an exception is uncaught
			LocalVariableDefn defn = new LocalVariableDefn(Type.typeFor(java.lang.Throwable.class), 
					this.context.nextOffset(Type.typeFor(java.lang.Throwable.class)));
			defn.initialize();
			context.addEntry(line(), "uncaught cariable", defn);
			uncaughtVariable = new JVariable(line(), "uncaught variable");
			uncaughtVariable.analyzeLhs(context);
		}
		// Thrown exceptions are added to the context in tryBlock.analyze()
		tryBlock = tryBlock.analyze(this.context);
		Map<Type, List<Integer>> thrownExceptions = this.context.getThrownExceptions();
		// Ensure that all thrown exception are caught.
		Set<Type> leftoverExceptions = new HashSet<Type>(thrownExceptions.keySet());
		for (Type caughtException : caughtExceptions) {
			for (Type leftoverException : leftoverExceptions) {
				if (caughtException.isJavaAssignableFrom(leftoverException)) {
					leftoverExceptions.remove(leftoverException);
				}
			}
		}
		for (Type exception : leftoverExceptions) {
			for (int line : thrownExceptions.get(exception)) {
				JAST.compilationUnit.reportSemanticError(line, "The exception %s"
						+ " is not declared.", exception.toString());
			}
		}
		// Go through each catch statement and check that every caught exception or
		// its supertype is actually thrown inside the try-block (unless the caught 
		// exception is unchecked).
		for (JCatchStatement cStatement : catchStatements) {
			for (Type caughtException : cStatement.getCaughtExceptions()) {
				if (!Type.typeFor(java.lang.Error.class).isJavaAssignableFrom(caughtException)
				 && !Type.typeFor(java.lang.RuntimeException.class)
	    					.isJavaAssignableFrom(caughtException)) {
					boolean isType = false;
					for (Type thrownException : thrownExceptions.keySet()) {
						if (caughtException.isJavaAssignableFrom(thrownException)) {
							isType = true;
							break;
						}
					}
					if (!isType) {
						JAST.compilationUnit.reportSemanticError(cStatement.line(), 
								"Exception %s is never thrown in the corresponding "
								+ "try-block.", caughtException.toString());
					}
				}
			}
		}
		
	
		
	
		return this;
	}
	
	/**
	 * Codegen for a TryStatement involves creating the exception table by
	 * specifying an exception handler for each exception, which consists of
	 * the range at which we should look for a specific thrown exception, 
	 * and the label to which we should branch.
	 */
	@Override
	public void codegen(CLEmitter output) {
		String startLabel = output.createLabel();
		String endLabel = output.createLabel();
		// We create two different versions of the finally block, one 
		// that should apply after we have caught an exception, and
		// one that should apply when the exception hasn't been caught 
		// by any of the catch-statements. In the latter, the exception
		// should be rethrown so that it can be handled by a surrounding
		// exception handler.
		String finallyLabel = output.createLabel();
		String finallyEndLabel = output.createLabel();
		String uncaughtFinallyEndLabel = output.createLabel();
		output.addLabel(startLabel);
		tryBlock.codegen(output);
		output.addBranchInstruction(CLConstants.GOTO, finallyLabel);
		output.addLabel(endLabel);
		for (JCatchStatement cStatement : catchStatements) {
			cStatement.setTryStartLabel(startLabel);
			cStatement.setTryEndLabel(endLabel);
			cStatement.codegen(output);
			output.addBranchInstruction(CLConstants.GOTO, finallyLabel);
		}
		// Use finallyLabel as the end of the try-catch, even if finally is null
		output.addLabel(finallyLabel);
		if (finallyBlock != null) {
			finallyBlock.codegen(output);
			output.addBranchInstruction(CLConstants.GOTO, uncaughtFinallyEndLabel);
			output.addExceptionHandler(startLabel, finallyLabel, finallyLabel, null);
			output.addExceptionHandler(finallyLabel, finallyEndLabel, finallyLabel, null);
			// Go to the end of the finally for uncaught exceptions
			output.addLabel(finallyEndLabel);
			uncaughtVariable.codegenStore(output);
			// The finally for uncaught exceptions
			finallyBlock.codegen(output);
			uncaughtVariable.codegen(output);
			output.addNoArgInstruction(CLConstants.ATHROW);
			output.addLabel(uncaughtFinallyEndLabel);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<Try line=\"%d\">\n", line());
		p.indentRight();
		if (tryBlock != null) {
			tryBlock.writeToStdOut(p);
		}
		p.println("<CatchStatements>");
		p.indentRight();
		for (JCatchStatement cStatement : catchStatements) {
			cStatement.writeToStdOut(p);
		}
		p.indentLeft();
		p.println("</CatchStatements>");
		if (finallyBlock != null) {
			p.println("<Finally>");
			p.indentRight();
			finallyBlock.writeToStdOut(p);
			p.indentLeft();
			p.println("</Finally>");
		}
		p.indentLeft();
		p.println("</Try>");
	}
}


