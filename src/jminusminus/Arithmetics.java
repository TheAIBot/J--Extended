package jminusminus;

import static jminusminus.CLConstants.*;

public class Arithmetics {
	public static void addCodegen(CLEmitter output, JExpression lhs, JExpression rhs, Type type) {
        lhs.codegen(output);
        rhs.codegen(output);
        
        switch (type) {
        case Type.CHAR:
		case Type.INT:
			output.addNoArgInstruction(IDIV);
			break;
		case Type.INT:
			output.addNoArgInstruction(IDIV);
			break;
		case Type.INT:
			output.addNoArgInstruction(IDIV);
			break;
		case Type.INT:
			output.addNoArgInstruction(IDIV);
			break;

		default:
			break;
		}
        
        if (type == Type.INT) {
            output.addNoArgInstruction(IDIV);
        } else if (type == Type.LONG) {
        	output.addNoArgInstruction(LDIV);
		} else if (type == Type.FLOAT) {
			output.addNoArgInstruction(FDIV);
		} else if (type == Type.DOUBLE) {
            output.addNoArgInstruction(DDIV);
		}
	}
}
