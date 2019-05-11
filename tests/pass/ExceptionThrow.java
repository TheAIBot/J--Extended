package pass;

import java.io.EOFException;
import java.io.IOException;
import java.lang.RuntimeException;
import java.lang.Exception;
import java.lang.Error;

public class ExceptionThrow {

	public void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	// No need for throws-declaration of unchecked exceptions
	public void throwError(String message) {
		throw new Error(message);
	}
	
	public void throwInheritingException(String message) throws Exception {
		throw new IOException(message);
	}
	
	public void throwMultipleExceptions(boolean throwExceptionA) throws EOFException, RuntimeException {
		if (throwExceptionA) {
			throw new EOFException();
		} else {
			throw new RuntimeException();
		}
	}
	
}
