package jminusminus;

import static jminusminus.CLConstants.*;

public class UnifiedInstructions {

	public static void store(CLEmitter output, Type type, int offset) {
		if (isIntegerType(type)) {
			switch (offset) {
			case 0:
				output.addNoArgInstruction(ISTORE_0);
				break;
			case 1:
				output.addNoArgInstruction(ISTORE_1);
				break;
			case 2:
				output.addNoArgInstruction(ISTORE_2);
				break;
			case 3:
				output.addNoArgInstruction(ISTORE_3);
				break;
			default:
				output.addOneArgInstruction(ISTORE, offset);
				break;
			}
		}
		else if (type.isReference()) {
			switch (offset) {
			case 0:
				output.addNoArgInstruction(ASTORE_0);
				break;
			case 1:
				output.addNoArgInstruction(ASTORE_1);
				break;
			case 2:
				output.addNoArgInstruction(ASTORE_2);
				break;
			case 3:
				output.addNoArgInstruction(ASTORE_3);
				break;
			default:
				output.addOneArgInstruction(ASTORE, offset);
				break;
			}
		}
		else if (type == Type.DOUBLE) {
			switch (offset) {
			case 0:
				output.addNoArgInstruction(DSTORE_0);
				break;
			case 1:
				output.addNoArgInstruction(DSTORE_1);
				break;
			case 2:
				output.addNoArgInstruction(DSTORE_2);
				break;
			case 3:
				output.addNoArgInstruction(DSTORE_3);
				break;
			default:
				output.addOneArgInstruction(DSTORE, offset);
				break;
			}
		} else {
			throw new Error("does not support the type: " + type.toString());
		}
	}
	
	private static boolean isIntegerType(Type type) {
		return type == Type.INT || type == Type.BOOLEAN || type == Type.CHAR;
	}
}
