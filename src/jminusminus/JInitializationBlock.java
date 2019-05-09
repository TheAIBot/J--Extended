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

    public JBlock analyze(Context context) {
        // { ... } defines a new level of scope.
        this.context = new LocalContext(context);

        //We need to start at the next offset since offset 0 will be the class reference
        this.context.nextOffset();

        for (int i = 0; i < statements.size(); i++) {
            statements.set(i, (JStatement) statements.get(i).analyze(
                    this.context));
        }
        return this;
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
