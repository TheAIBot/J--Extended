package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;
import static jminusminus.CLConstants.ARETURN;

/**
 * @author Kasper
 */

public class JMethodSignature extends JAST implements JMember {

    /**
     * Method modifiers.
     */
    protected ArrayList<String> mods;

    /**
     * Method name.
     */
    protected String name;

    /**
     * Return type.
     */
    protected Type returnType;

    /**
     * The formal parameters.
     */
    protected ArrayList<JFormalParameter> params;

    /** The declared exceptions thrown by the method. */
    protected ArrayList<Type> exceptions;
    
    /** The exception names in internal form. Computed by preAnalyze(). */
    protected ArrayList<String> exceptionNames;
    
    /** Built in analyze(). */
    protected MethodContext context;

    /**
     * Computed by preAnalyze().
     */
    protected String descriptor;

    /**
     * Is method abstract.
     */
    protected boolean isAbstract;

    /**
     * Is method static.
     */
    protected boolean isStatic;

    /**
     * Is method private.
     */
    protected boolean isPrivate;

    /**
     * Construct an AST node the given its line number in the source file.
     *
     * @param line       line in which the method declaration occurs
     *                   in the source file.
     * @param mods       modifiers.
     * @param name       method name.
     * @param returnType return type.
     * @param params     the formal parameters.
     */

    public JMethodSignature(int line, ArrayList<String> mods, String name,
                            Type returnType, ArrayList<JFormalParameter> params,
                            ArrayList<Type> exceptions) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.exceptions = exceptions;
        this.isAbstract = mods.contains("abstract");
        this.isStatic = mods.contains("static");
        this.isPrivate = mods.contains("private");
    }

    @Override
    public JAST analyze(Context context) {
        this.context = new MethodContext(context, isStatic, returnType, exceptions);

        if (!isStatic) {
            // Offset 0 is used to address "this".
            this.context.nextOffset();
        }

        // Declare the parameters. We consider a formal parameter
        // to be always initialized, via a function call.
        for (JFormalParameter param : params) {
            LocalVariableDefn defn = new LocalVariableDefn(param.type(), this.context.nextOffset(param.type()));
            defn.initialize();
            this.context.addEntry(param.line(), param.name(), defn);
        }
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        output.addMethod(mods, name, descriptor, exceptionNames, false);

        // Add implicit RETURN
        if (returnType == Type.VOID) {
            output.addNoArgInstruction(RETURN);
        }
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
        if (exceptions != null) {
        	p.println("<ThrownExceptions>");
        	p.indentRight();
        	for (Type type : exceptions) {
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
        // Resolve types of the formal parameters
        for (JFormalParameter param : params) {
            param.setType(param.type().resolve(context));
        }

        // Resolve return type
        returnType = returnType.resolve(context);

        if (isAbstract && isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(),
                    "private method cannot be declared abstract");
        } else if (isAbstract && isStatic) {
            JAST.compilationUnit.reportSemanticError(line(),
                    "static method cannot be declared abstract");
        } else if (isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(),
                    "a signature method cannot be private");
        }

        // Compute descriptor
        descriptor = "(";
        for (JFormalParameter param : params) {
            descriptor += param.type().toDescriptor();
        }
        descriptor += ")" + returnType.toDescriptor();
        if (exceptions != null) {
        	exceptionNames = new ArrayList<String>();
            // Resolve exception types
            for (int i = 0; i < exceptions.size(); i++) {
            	exceptions.set(i, exceptions.get(i).resolve(context));
            	exceptionNames.add(exceptions.get(i).jvmName());
            }
        }
        
        // Generate the method with an empty body (for now)
        partialCodegen(context, partial);
    }

    /**
     * Add this method declaration to the partial class.
     *
     * @param context the parent (class) context.
     * @param partial the code emitter (basically an abstraction
     *                for producing the partial class).
     */

    public void partialCodegen(Context context, CLEmitter partial) {
        // Generate a method with an empty body; need a return to
        // make
        // the class verifier happy.
        partial.addMethod(mods, name, descriptor, exceptionNames, false);

        // Add implicit RETURN
        if (returnType == Type.VOID) {
            partial.addNoArgInstruction(RETURN);
        } else if (returnType == Type.INT
                || returnType == Type.BOOLEAN || returnType == Type.CHAR) {
            partial.addNoArgInstruction(ICONST_0);
            partial.addNoArgInstruction(IRETURN);
        } else {
            // A reference type.
            partial.addNoArgInstruction(ACONST_NULL);
            partial.addNoArgInstruction(ARETURN);
        }
    }

}
