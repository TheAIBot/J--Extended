package jminusminus;

public class JThrowStatement extends JStatement {
	
	/** The expression thrown. */
	private JExpression expr;
	
	/**
	 * Construct an AST node for a throw-statement.
	 * @param line 
	 * @param expr the thrown expression.
	 */
	public JThrowStatement(int line, JExpression expr) {
		super(line);
		this.expr = expr;
	}

	@Override
	public JThrowStatement analyze(Context context) {
		expr = expr.analyze(context);
		Type type = expr.type();
		if (!Type.typeFor(java.lang.Throwable.class).isJavaAssignableFrom(type)) {
			JAST.compilationUnit.reportSemanticError(line(), "Type %s"
					+ " is not a Throwable type.", type.toString());
		}
		((LocalContext)context).addThrownException(type, line());
		return this;
	}

	@Override
	public void codegen(CLEmitter output) {
		expr.codegen(output);
		output.addNoArgInstruction(CLConstants.ATHROW);
	}

	/**
     * @inheritDoc
     */
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		if (expr != null) {
			p.printf("<JThrowStatement line=\"%d\">\n", line());
			p.indentRight();
			expr.writeToStdOut(p);
			p.indentLeft();
			p.printf("<JThrowStatement>\n");
		} else {
			p.printf("<JReturnStatement line=\"%d\"/>\n", line());
		}
	}
}
