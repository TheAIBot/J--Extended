package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */

import static jminusminus.CLConstants.*;

public class JForStatement extends JStatement {

    JVariableDeclaration before;
    JExpression condition;
    JStatement body, postIter;

    private Context context;

    /**
     * Construct an AST node for a for expression given its line number, initialization,
     * test and post expressions.
     *
     * @param line line in which the expression occurs in the source file.
     */
    protected JForStatement(int line, JVariableDeclaration before, JExpression condition, JStatement body, JStatement postIter) {
        super(line);
        this.before = before;
        this.condition = condition;
        this.body = body;
        this.postIter = postIter;
    }

    @Override
    public JAST analyze(Context context) {
        this.context = new LocalContext(context);

        if(before != null)
            before = (JVariableDeclaration) before.analyze(this.context);

        if(condition != null) {
            condition = condition.analyze(this.context);
            condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        } else {
            condition = new JLiteralTrue(line()).analyze(this.context);
        }

        if(postIter != null) {
            postIter = (JStatement) postIter.analyze(this.context);
        }

        body = (JStatement) body.analyze(this.context);
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        if(before != null)
            before.codegen(output);
        // Branch out of the loop on the test condition
        // being false
        output.addLabel(test);
        condition.codegen(output, out, false);

        // Codegen body
        body.codegen(output);
        if(postIter != null)
            postIter.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
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
