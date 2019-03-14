package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JForExpression extends JAST {

    JExpression before, after;
    JBooleanBinaryExpression test;

    /**
     * Construct an AST node for a for expression given its line number, initialization,
     * test and post expressions.
     *
     * @param line line in which the expression occurs in the source file.
     */
    protected JForExpression(int line, JExpression before, JBooleanBinaryExpression test, JExpression after) {
        super(line);
        this.before = before;
        this.test = test;
        this.after = after;
    }

    @Override
    public JAST analyze(Context context) {
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForExpression line=\"%d\" before=\"%s\" test=\"%s\" after=\"%s\">\n",
                line(), before.toString(), test.toString(), after.toString());
        p.println("</JForExpression>");
    }
}
