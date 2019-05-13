// Copyright 2011 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a method declaration.
 */

class JMethodDeclaration extends JAST implements JMember {

    /** Method modifiers. */
    protected ArrayList<String> mods;

    /** Method name. */
    protected String name;

    /** Return type. */
    protected Type returnType;

    /** The formal parameters. */
    protected ArrayList<JFormalParameter> params;

    /** The exception types thrown by the method. */
    protected ArrayList<Type> exceptions;
    
    /** The exception names in internal form. Computed by preAnalyze(). */
    protected ArrayList<String> exceptionNames;
    
    /** Method body. */
    protected JBlock body;

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
     * Construct an AST node for a method declaration given the
     * line number, method name, return type, formal parameters,
     * and the method body.
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
     * @param body
     *                method body.
     */

    public JMethodDeclaration(int line, ArrayList<String> mods,
        String name, Type returnType,
        ArrayList<JFormalParameter> params, 
        ArrayList<Type> exceptions, JBlock body)

    {
        super(line);
        this.mods = mods;
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.exceptions = exceptions;
        this.body = body;
        this.isAbstract = mods.contains("abstract");
        this.isStatic = mods.contains("static");
        this.isPrivate = mods.contains("private");
    }

    /**
     * Declare this method in the parent (class) context.
     * 
     * @param context
     *                the parent (class) context.
     * @param partial
     *                the code emitter (basically an abstraction
     *                for producing the partial class).
     */

    public void preAnalyze(Context context, CLEmitter partial) {
        // Resolve types of the formal parameters
        for (JFormalParameter param : params) {
            param.setType(param.type().resolve(context));
        }

        // Resolve return type
        returnType = returnType.resolve(context);

        // Check proper local use of abstract
        if (isAbstract && body != null) {
            JAST.compilationUnit.reportSemanticError(line(),
                "abstract method cannot have a body");
        } else if (body == null && !isAbstract) {
            JAST.compilationUnit.reportSemanticError(line(),
                "Method with null body must be abstarct");
        } else if (isAbstract && isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(),
                "private method cannot be declared abstract");
        } else if (isAbstract && isStatic) {
            JAST.compilationUnit.reportSemanticError(line(),
                "static method cannot be declared abstract");
        }
        
        if (exceptions != null) {
        	exceptionNames = new ArrayList<String>();
            // Resolve exception types
            for (int i = 0; i < exceptions.size(); i++) {
            	exceptions.set(i, exceptions.get(i).resolve(context));
            	exceptionNames.add(exceptions.get(i).jvmName());
            }
        }
        
        // Compute descriptor
        descriptor = "(";
        for (JFormalParameter param : params) {
            descriptor += param.type().toDescriptor();
        }
        descriptor += ")" + returnType.toDescriptor();

        // Generate the method with an empty body (for now)
        partialCodegen(context, partial);
    }

    /**
     * Analysis for a method declaration involves (1) creating a
     * new method context (that records the return type; this is
     * used in the analysis of the method body), (2) bumping up
     * the offset (for instance methods), (3) declaring the
     * formal parameters in the method context, and (4) analyzing
     * the method's body.
     * 
     * @param context
     *                context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JAST analyze(Context context) {
        MethodContext methodContext = 
        		new MethodContext(context, isStatic, returnType, exceptions);
        this.context = methodContext;

        if (!isStatic) {
            // Offset 0 is used to address "this".
            this.context.nextOffset(Type.INT);
        }

        // Declare the parameters. We consider a formal parameter 
        // to be always initialized, via a function call.
        for (JFormalParameter param : params) {
            LocalVariableDefn defn = new LocalVariableDefn(param.type(), 
                this.context.nextOffset(param.type()));
            defn.initialize();
            this.context.addEntry(param.line(), param.name(), defn);
        }
        
        // Enforce exception is subclass of Throwable
        if (exceptions != null) {
        	Type throwableType = Type.typeFor(java.lang.Throwable.class);
            for (Type exception : exceptions) {
            	if (!throwableType.isJavaAssignableFrom(exception)) {
            		JAST.compilationUnit.reportSemanticError(line(), "Type %s "
            				+ "is not a Throwable-type.", exception);
            	}
            }
        }
        
        if (body != null) {
            body = body.analyze(this.context);
		    if (returnType!=Type.VOID && ! methodContext.methodHasReturn()){
		    	JAST.compilationUnit.reportSemanticError(line(),
		    			"Non-void method must have a return statement");
		    }
        }
        
        // Ensure that all thrown exception declared
 		Set<Type> leftoverExceptions = new HashSet<Type>((this.context.thrownExceptions).keySet());
 		if (exceptions != null) {
	 		for (Type declaredException : exceptions) {
	 			for (Type leftoverException : leftoverExceptions) {
	 				if (declaredException.isJavaAssignableFrom(leftoverException)) {
	 					leftoverExceptions.remove(leftoverException);
	 				}
	 			}
	 		}
 		}
 		for (Type exception : leftoverExceptions) {
 			for (int line : this.context.thrownExceptions.get(exception)) {
 				JAST.compilationUnit.reportSemanticError(line, "The exception %s"
 						+ " is never being caught.", exception.toString());
 			}
 		}
        
        return this;
    }

    /**
     * Add this method declaration to the partial class.
     * 
     * @param context
     *                the parent (class) context.
     * @param partial
     *                the code emitter (basically an abstraction
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
        } else if (returnType == Type.DOUBLE) {
                partial.addNoArgInstruction(DCONST_0);
                partial.addNoArgInstruction(DRETURN);
            } else {
            // A reference type.
            partial.addNoArgInstruction(ACONST_NULL);
            partial.addNoArgInstruction(ARETURN);
        }
    }

    /**
     * Generate code for the method declaration.
     * 
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {
        output.addMethod(mods, name, descriptor, exceptionNames, false);
        if (body != null) {
            body.codegen(output);
        }

        // Add implicit RETURN
        if (returnType == Type.VOID) {
            output.addNoArgInstruction(RETURN);
        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JMethodDeclaration line=\"%d\" name=\"%s\" "
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
        if (body != null) {
            p.println("<Body>");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.println("</Body>");
        }
        p.indentLeft();
        p.println("</JMethodDeclaration>");
    }
}
