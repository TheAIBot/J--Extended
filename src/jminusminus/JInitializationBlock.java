package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
class JInitializationBlock extends JBlock implements JMember {

    protected boolean isStatic = false;

    /**
     * Construct an AST node for a static initialization block given the
     * line number and the method body.
     *
     * @param line line in which the method declaration occurs
     *             in the source file.
     * @param body
     */
    public JInitializationBlock(int line, JBlock body) {
        super(line, body.statements());
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }

    @Override
    public JBlock analyze(Context context) {
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInitializationBlock line=\"%d\">\n", line());
        if (this.statements() != null) {
            p.indentRight();
            for (JStatement statement : this.statements()) {
                p.indentRight();
                statement.writeToStdOut(p);
                p.indentLeft();
            }
            p.indentLeft();
        }
        p.println("</JInitializationBlock>");
    }
}
