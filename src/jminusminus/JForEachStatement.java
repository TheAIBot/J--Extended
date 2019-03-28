package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JForEachStatement extends JStatement {

    protected JFormalParameter internalVariable;
    protected String arrayName;

    /**
     * Construct an AST node for a for each expression given its line number in the source file,
     * internal variable and array name.
     *
     * @param line line in which the source for the AST was found.
     */
    protected JForEachStatement(int line, JFormalParameter var, String arrayName, JStatement body) {
        super(line);
        this.internalVariable = var;
        this.arrayName = arrayName;
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
        p.printf("<JForEachStatement line=\"%d\" var=\"%s\" array=\"%s\">\n",
                line(), internalVariable.toString(), arrayName);
        p.println("</JForEachStatement>");
    }
}
