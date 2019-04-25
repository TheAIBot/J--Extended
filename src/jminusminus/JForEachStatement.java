package jminusminus;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JForEachStatement extends JStatement {

    protected JFormalParameter internalVariable;
    protected String arrayName;
    JStatement body;

    private Context context;

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
        this.body = body;
    }

    @Override
    public JAST analyze(Context context) {
        this.context = new LocalContext(context);

        //Declare the variable
        LocalVariableDefn defn = new LocalVariableDefn(internalVariable.type(), this.context.methodContext().nextOffset());
        defn.initialize();
        this.context.addEntry(internalVariable.line(), internalVariable.name(), defn);

        //Make sure the array exists
        IDefn array = this.context.lookup(arrayName);
        if (array == null || !array.type().isArray()) {
            JAST.compilationUnit.reportSemanticError(line(), arrayName + " is not a declared array");
        }

        body = (JStatement) body.analyze(this.context);
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForEachStatement line=\"%d\" var=\"%s\" array=\"%s\">\n",
                line(), internalVariable.toString(), arrayName);
            p.printf("<Body>\n");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.printf("</Body>\n");
        p.println("</JForEachStatement>");
    }
}
