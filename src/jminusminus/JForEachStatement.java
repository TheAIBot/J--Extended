package jminusminus;

import java.util.ArrayList;
import java.util.List;

import static jminusminus.CLConstants.*;

/**
 * Created by Tobias on 3/7/2019.
 */
public class JForEachStatement extends JStatement {

    protected JFormalParameter internalVariable;
    protected String arrayName;
    JStatement body;

    private JStatement arrayLengthDeclaration, indexDeclaration, arrayElementDeclaration;
    private JExpression arrayLength, index, increment;

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

        //Make sure the array exists
        IDefn array = this.context.lookup(arrayName);
        if (array == null || !array.type().isArray()) {
            JAST.compilationUnit.reportSemanticError(line(), arrayName + " is not a declared array");
        }
        JVariable realArray = new JVariable(line(), arrayName);

        //Make sure the variable has the same type as the array
        if(!internalVariable.type().isJavaAssignableFrom(array.type().componentType())) {
            JAST.compilationUnit.reportSemanticError(line(), "Incompatible types: " + internalVariable.type() + " cannot be converted to " + array.type().componentType());
        }

        //Create array length
        JFieldSelection realArrayLength = new JFieldSelection(line(), realArray, "length");
        JVariableDeclarator arrayLengthDecl = new JVariableDeclarator(line(), "very illegal array length", Type.INT, realArrayLength);
        ArrayList<JVariableDeclarator> arrayLengthDecls = new ArrayList<>();
        arrayLengthDecls.add(arrayLengthDecl);
        arrayLengthDeclaration = new JVariableDeclaration(line(), new ArrayList<>(), arrayLengthDecls).analyze(this.context);
        arrayLength = new JVariable(line(), "very illegal array length").analyze(this.context);

        //Create index
        JVariableDeclarator indexDecl = new JVariableDeclarator(line(), "very illegal index", Type.INT, new JLiteralInt(line(), "0"));
        ArrayList<JVariableDeclarator> indexDecls = new ArrayList<>();
        indexDecls.add(indexDecl);
        indexDeclaration = new JVariableDeclaration(line(), new ArrayList<>(), indexDecls).analyze(this.context);
        index = new JVariable(line(), "very illegal index").analyze(this.context);

        //Create array element variable
        JArrayExpression arrayExpression = new JArrayExpression(line(), realArray, index);
        JVariableDeclarator arrayElementdecl = new JVariableDeclarator(line(), internalVariable.name(), internalVariable.type(), arrayExpression);
        ArrayList<JVariableDeclarator> arrayElementDecls = new ArrayList<>();
        arrayElementDecls.add(arrayElementdecl);
        arrayElementDeclaration =  new JVariableDeclaration(line(), new ArrayList<>(), arrayElementDecls).analyze(this.context);

        //Create incrementor
        increment = new JPreIncrementOp(line(), index);
        increment.isStatementExpression = true;
        increment.analyze(this.context);


        body = (JStatement) body.analyze(this.context);
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        arrayLengthDeclaration.codegen(output);
        indexDeclaration.codegen(output);

        // Branch out of the loop on the test condition
        // being false
        output.addLabel(test);
        index.codegen(output);
        arrayLength.codegen(output);
        output.addBranchInstruction(IF_ICMPGE, out);

        // Codegen body
        arrayElementDeclaration.codegen(output);
        body.codegen(output);

        //Codegen incrementor
        increment.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
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
