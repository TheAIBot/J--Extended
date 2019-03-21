package jminusminus;

import java.util.ArrayList;

public class JTryStatement extends JStatement {

	/** The try-block that might throw exception(s). */
	private JBlock tryBlock;
	
	/** The exception types to be catched for each catch-block. Each item in the list
	 * corresponds to one item in the catchBlocks list. */
	private ArrayList<ArrayList<JFormalParameter>> catchParameters;
	
	/** The blocks that handles the corresponding exceptions in the catchParameters list. */
	private ArrayList<JBlock> catchBlocks;
	
	/** The finally-block of this statement. */
	private JBlock finallyBlock;
	
	/**
	 * Constructs an AST-node for the try-catch-finally statement.
	 * @param line
	 * @param tryBlock
	 * @param catchParameters
	 * @param catchBlocks
	 * @param finallyBlock
	 */
	protected JTryStatement(int line, JBlock tryBlock, 
							ArrayList<ArrayList<JFormalParameter>> catchParameters,
							ArrayList<JBlock> catchBlocks, JBlock finallyBlock) {
		super(line);
		this.tryBlock = tryBlock;
		this.catchParameters = catchParameters;
		this.catchBlocks = catchBlocks;
		this.finallyBlock = finallyBlock;
	}

	@Override
	public JAST analyze(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void codegen(CLEmitter output) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<Try line=\"%d\">", line());
		p.indentRight();
		if (tryBlock != null) {
			tryBlock.writeToStdOut(p);
		}
		for (int i = 0; i < catchParameters.size(); i++) {
			p.println("<Catch>");
			p.indentRight();
			p.println("<FormalParameters>");
			p.indentRight();
			for (JFormalParameter fpar : catchParameters.get(i)) {
				fpar.writeToStdOut(p);
			}
			p.indentLeft();
			p.println("</FormalParameters>");
			catchBlocks.get(i).writeToStdOut(p);
			p.indentLeft();
			p.println("</Catch>");
		}
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
