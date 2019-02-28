package jminusminus;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Kasper
 */

public class JLiteralDouble extends JExpression {


    /** String representation of the double. */
    private String text;

    /**
     * Construct an AST node for an expression given its line number.
     *
     * @param line line in which the expression occurs in the source file.
     */
    protected JLiteralDouble(int line, String text) {
        super(line);
        this.text = text;
    }

    @Override
    public JExpression analyze(Context context) {
        type = Type.DOUBLE;
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        throw new NotImplementedException(); // todo
    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {

    }
}
