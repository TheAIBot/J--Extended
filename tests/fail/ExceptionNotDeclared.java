package fail;

import java.io.EOFException;

public class ExceptionNotDeclared {
	public static void main() {
		throw new EOFException();jj//todo: fix this error
	}
}
