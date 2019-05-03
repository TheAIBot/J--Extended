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
		finallyBlock = finallyBlock.analyze(context);
		// Thrown exceptions are added to the context in tryBlock.analyze()
		tryBlock = tryBlock.analyze(this.context);
		Map<Type, List<Integer>> thrownExceptions = this.context.getThrownExceptions();
		// Ensure that all thrown exception are caught.
		Set<Type> leftoverExceptions = new HashSet<Type>(thrownExceptions.keySet());
		leftoverExceptions.removeAll(caughtExceptions);
		for (Type exception : leftoverExceptions) {
			for (int line : thrownExceptions.get(exception)) {
				JAST.compilationUnit.reportSemanticError(line, "The exception %s "
						+ " is never being caught.", exception.toString());
			}
		}
		// Go through each catch statement and check that every caught exception
		// is actually thrown inside the try-block (unless the caught exception 
		// is unchecked).
		for (JCatchStatement cStatement : catchStatements) {
			for (Type exception : cStatement.getCaughtExceptions()) {
				if (!Type.typeFor(java.lang.Error.class).isJavaAssignableFrom(exception)
				 && !Type.typeFor(java.lang.RuntimeException.class)
	    					.isJavaAssignableFrom(exception)
				 && !thrownExceptions.keySet().contains(exception)) {
					JAST.compilationUnit.reportSemanticError(cStatement.line(), 
							"Exception %s is never thrown in the corresponding "
							+ "try-block.", exception.toString());
				}
			}
		}
		return this;
	}
	
	@Override
	public void codegen(CLEmitter output) {
		String startLabel = output.createLabel();
		String endLabel = output.createLabel();
		String finallyLabel = output.createLabel();
		output.addLabel(startLabel);
		tryBlock.codegen(output);
		output.addBranchInstruction(CLConstants.GOTO, finallyLabel);
		output.addLabel(endLabel);
		for (JCatchStatement cStatement : catchStatements) {
			cStatement.setTryStartLabel(startLabel);
			cStatement.setTryEndLabel(endLabel);
			cStatement.codegen(output);
		}
		output.addLabel(finallyLabel);
		if (finallyBlock != null) {
			output.addExceptionHandler(startLabel, finallyLabel, finallyLabel, null);
			finallyBlock.codegen(output);
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

class JCatchStatement extends JStatement {

	/** The different throwables caught. There might be more than one (i.e. multicatch). */
	private ArrayList<Type> exceptions;
	
	/** The shared name for all the caught throwables. */
	private String name;
	
	/** The body of the catch statement. */
	private JBlock body;
	
	/** The local context for this catch statement. Created in analyze(). */
	private LocalContext context; 
	
	/** JVM start label for corresponding try-block. Only set and used during codegen. */
	private String tryStartLabel;
	
	/** JVM end label for corresponding try-block. Only set and used during codegen. */
	private String tryEndLabel;
	
	/**
	 * AST node for a catch statement.
	 * @param line
	 * @param throwables 
	 * 				the exceptions(s) caught by the catch statement.
	 * @param name
	 * 				the shared identifier for the caught exceptions.
	 * @param body
	 * 				the body of the catch statement.
	 */
	protected JCatchStatement(int line, ArrayList<Type> exceptions, String name, JBlock body) {
		super(line);
		this.exceptions = exceptions;
		this.name = name;
		this.body = body;
	}
				
	@Override
	public JCatchStatement analyze(Context context) {
		Type throwableType = Type.typeFor(java.lang.Throwable.class);
		// Resolve exception types and ensure that it inherits Throwable
		for (int i = 0; i < exceptions.size(); i++) {
        	exceptions.set(i, exceptions.get(i).resolve(context));
        	if (!exceptions.get(i).isJavaAssignableFrom(throwableType)) {
        		JAST.compilationUnit.reportSemanticError(line(), 
        				"Type " + exceptions.get(i) + " is not a Throwable type.");
        	}
        }
		this.context = new LocalContext(context);
		// Create a local variable declaration corresponding to the caught exceptions.
		// If this is a multicatch, then the Throwable-type should be used.
		LocalVariableDefn defn;
		if (exceptions.size() > 1) {
			defn = new LocalVariableDefn(throwableType, this.context.nextOffset());
		} else {
			defn = new LocalVariableDefn(exceptions.get(0), this.context.nextOffset());
		}
		defn.initialize();
		this.context.addEntry(line(), name, defn);
		// Analyze the body
		if (body != null) {
			body = body.analyze(this.context);
		}		
		return this;
	}

	public void setTryStartLabel(String label) {
		this.tryStartLabel = label;
	}
	
	public void setTryEndLabel(String label) {
		this.tryEndLabel = label;
	}
	
	public ArrayList<Type> getCaughtExceptions() {
		return exceptions;
	}
	
	/**
	 * Codegen involves adding an exception handler for each caught throwable and then
	 * generating the code for the body.
	 */
	@Override
	public void codegen(CLEmitter output) {
		String handlerLabel = output.createLabel();
		output.addLabel(handlerLabel);
		for (Type type : exceptions) {
			String internalName = type.jvmName();
			output.addExceptionHandler(tryStartLabel, tryEndLabel, handlerLabel, internalName);
		}
		body.codegen(output);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<Catch line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<Throwables name=\"%s\">", name);
		p.indentRight();
		for (Type type : exceptions) {
			p.printf("<Throwable type=\"%s\"/>\n", type);
		}
		p.indentLeft();
		p.println("</Throwables>");
		p.println("<Body>");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.println("</Body>");
		p.indentLeft();
		p.println("</Catch>");
	}	
}
