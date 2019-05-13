package jminusminus;

import java.util.ArrayList;

class JCatchStatement extends JStatement {

	/** The different throwables caught. There might be more than one (i.e. multicatch). */
	private ArrayList<Type> exceptions;
	
	/** The shared name for all the caught throwables. */
	private String name;
	
	/** The body of the catch statement. */
	private JBlock body;
	
	/** The local context for this catch statement. Created in analyze(). */
	private LocalContext context; 
	
	/** The exception variable. Created in analyze(). */
	private JVariable variable; 
	
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
		if (exceptions == null || exceptions.isEmpty()) {
			// Shouldn't be possible using parser
			JAST.compilationUnit.reportSemanticError(line(), "A catch-statement must catch "
					+ "at least one exception.");
		}
		Type throwableType = Type.typeFor(java.lang.Throwable.class);
		// Resolve exception types and ensure that it inherits Throwable
		for (int i = 0; i < exceptions.size(); i++) {
        	exceptions.set(i, exceptions.get(i).resolve(context));
        	if (!throwableType.isJavaAssignableFrom(exceptions.get(i))) {
        		JAST.compilationUnit.reportSemanticError(line(), 
        				"Type " + exceptions.get(i) + " is not a Throwable type.");
        	}
        }
		this.context = new LocalContext(context);
		Type commonType = exceptions.get(0);
		// If this is a multicatch, then the common super type should be used.
		if (exceptions.size() > 1) {
			// Use common super class as type definition
			for (int i = 1; i < exceptions.size() - 1; i++) {
				commonType = exceptions.get(i).commonSuperClass(exceptions.get(i + 1));
			}
		}
		// Create a local variable declaration corresponding to the caught exceptions.
		LocalVariableDefn defn = new LocalVariableDefn(commonType, this.context.nextOffset(commonType));
		defn.initialize();
		this.context.addEntry(line(), name, defn);
		// Create a variable representing the exception variable and analyze it
		variable = new JVariable(line(), name);
		variable = (JVariable) variable.analyzeLhs(this.context);
		// Analyze the body
		if (body != null) {
			body = body.analyze(this.context);
		} else {
			JAST.compilationUnit.reportSemanticError(line(), "A catch-statement must have a body.");
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
		// Generate code for storing the exception on the stack
		variable.codegenStore(output);
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