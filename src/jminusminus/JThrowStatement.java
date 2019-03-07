package jminusminus;

public class JThrowStatement extends JStatement {

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
	public JStatement analyze(Context context) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void codegen(CLEmitter output) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
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
