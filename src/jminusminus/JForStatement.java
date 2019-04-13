package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JForStatement extends JStatement {

    JVariableDeclaration before;
    JExpression condition, postIter;
    JStatement body;

    /**
     * Construct an AST node for a for expression given its line number, initialization,
     * test and post expressions.
     *
     * @param line line in which the expression occurs in the source file.
     */
    protected JForStatement(int line, JVariableDeclaration before, JExpression condition, JStatement body, JExpression postIter) {
        super(line);
        this.before = before;
        this.condition = condition;
        this.body = body;
        this.postIter = postIter;
    }

    @Override
    public JAST analyze(Context context) {
        before = (JVariableDeclaration) before.analyze(context);
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        postIter = postIter.analyze(context);
        postIter.type().mustMatchOneOf(line(), Type.INT, Type.DOUBLE);
        body = (JStatement) body.analyze(context);
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\" before=\"%s\" test=\"%s\" after=\"%s\">\n",
                line(),
                before != null ? before.toString() : "null",
                condition != null ? condition.toString() : "null",
                postIter != null ? postIter.toString() : "null");
            p.printf("<Body>\n");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.printf("</Body>\n");
        p.println("</JForStatement>");
    }
}
