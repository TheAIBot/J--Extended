package jminusminus;

/**
 * Created by Tobias on 2/14/2019.
 */
public class JUnaryPlus extends JUnaryExpression {

    /**
     * Construct an AST node for a unary expression given its line number, the
     * unary operator, and the operand.
     *
     * @param line     line in which the unary expression occurs in the source file.
     * @param arg
     */
    public JUnaryPlus(int line, JExpression arg) {
        super(line, "+", arg);
    }

    @Override
    public JExpression analyze(Context context) {
        arg = arg.analyze(context);
        arg.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        arg.codegen(output);
    }
}
