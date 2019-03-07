package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JStaticInitializationBlock extends JInitializationBlock implements JMember {
    /**
     * Construct an AST node for a static initialization block given the
     * line number and the method body.
     *
     * @param line line in which the method declaration occurs
     *             in the source file.
     * @param body
     */
    public JStaticInitializationBlock(int line, JBlock body) {
        super(line, body);
        this.isStatic = true;
    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JStaticInitializationBlock line=\"%d\">\n", line());
        if (this.statements() != null) {
            p.indentRight();
            for (JStatement statement : this.statements()) {
                p.indentRight();
                statement.writeToStdOut(p);
                p.indentLeft();
            }
            p.indentLeft();
        }
        p.println("</JStaticInitializationBlock>");
    }
}
