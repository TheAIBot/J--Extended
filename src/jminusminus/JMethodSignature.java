package jminusminus;

import java.util.ArrayList;

/**
 * @author Kasper
 */

public class JMethodSignature extends JAST implements JMember  {

    // todo: kasper

    /** Method modifiers. */
    protected ArrayList<String> mods;

    /** Method name. */
    protected String name;

    /** Return type. */
    protected Type returnType;

    /** The formal parameters. */
    protected ArrayList<JFormalParameter> params;

    /** The declared exceptions thrown by the method. */
    protected ArrayList<TypeName> exceptionList;
    
    /** Built in analyze(). */
    protected MethodContext context;

    /** Computed by preAnalyze(). */
    protected String descriptor;

    /** Is method abstract. */
    protected boolean isAbstract;

    /** Is method static. */
    protected boolean isStatic;

    /** Is method private. */
    protected boolean isPrivate;

    /**
     * Construct an AST node the given its line number in the source file.
     *
     * @param line
     *                line in which the method declaration occurs
     *                in the source file.
     * @param mods
     *                modifiers.
     * @param name
     *                method name.
     * @param returnType
     *                return type.
     * @param params
     *                the formal parameters.
     */

    public JMethodSignature(int line, ArrayList<String> mods, String name,
                            Type returnType, ArrayList<JFormalParameter> params,
                            ArrayList<TypeName> exceptionList) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.exceptionList = exceptionList;
        this.isAbstract = mods.contains("abstract");
        this.isStatic = mods.contains("static");
        this.isPrivate = mods.contains("private");
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
        p.printf("<JMethodSignature line=\"%d\" name=\"%s\" "
                + "returnType=\"%s\">\n", line(), name, returnType
                .toString());
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (params != null) {
            p.println("<FormalParameters>");
            for (JFormalParameter param : params) {
                p.indentRight();
                param.writeToStdOut(p);
                p.indentLeft();
            }
            p.println("</FormalParameters>");
        }
        if (exceptionList != null) {
        	p.println("<ThrownExceptions>");
        	p.indentRight();
        	for (TypeName type : exceptionList) {
        		p.printf("<Exception type=\"%s\"/>\n", type.toString());
        	}
        	p.indentLeft();
        	p.println("</ThrownExceptions>");
        }
        p.indentLeft();
        p.println("</JMethodSignature>");
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }
}
