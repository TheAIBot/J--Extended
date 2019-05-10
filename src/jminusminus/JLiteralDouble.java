package jminusminus;

import static jminusminus.CLConstants.BIPUSH;
import static jminusminus.CLConstants.ICONST_0;
import static jminusminus.CLConstants.ICONST_1;
import static jminusminus.CLConstants.ICONST_2;
import static jminusminus.CLConstants.ICONST_3;
import static jminusminus.CLConstants.ICONST_4;
import static jminusminus.CLConstants.ICONST_5;
import static jminusminus.CLConstants.SIPUSH;

/**
 * @author Kasper
 */

public class JLiteralDouble extends JExpression {


    /** String representation of the double. */
    private String text;

    /**
     * Construct an AST node for an expression given its line number.
     *
     * @param line line in which the expression occurs in the source file.
     */
    protected JLiteralDouble(int line, String text) {
        super(line);
        this.text = text;
    }

    @Override
    public JExpression analyze(Context context) {
        type = Type.DOUBLE;
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        double i = Double.parseDouble(text);
        if (i == 0d) {
            output.addNoArgInstruction(CLConstants.DCONST_0);
		} else if (i == 1d) {
			output.addNoArgInstruction(CLConstants.DCONST_1);
		} else {
			output.addLDCInstruction(i);
		}
    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralDouble line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }
}
