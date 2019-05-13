package fail;

import java.lang.Exception;
import java.io.EOFException;

public class ExceptionNeverBeingCaughtInTry {

	public static void main() {
		try {
			throw new Exception();
		} catch (EOFException e) {
			
		}
	}
	
}
