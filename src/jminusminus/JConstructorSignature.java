package jminusminus;

import java.util.ArrayList;

/**
 * @author Kasper
 */

public class JConstructorSignature extends JMethodSignature implements JMember  {


    /**
     * Construct an AST node the given its line number in the source file.
     *
     * @param line line in which the source for the AST was found.
     */
    public JConstructorSignature(int line, ArrayList<String> mods, String name, ArrayList<JFormalParameter> params) {
        super(line, mods, name, Type.CONSTRUCTOR, params);
    }
}
