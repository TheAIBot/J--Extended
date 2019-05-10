package pass;

public class ExceptionThrow implements InterfaceWithThrow {

	public void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	// No need for throws-declaration of unchecked exceptions
	public void throwError(String message) {
		throw new Error(message);
	}
	
	public void throwInheritingException(String message) throws Exception {
		throw new InheritingException(message);
	}
	
	public void throwMultipleExceptions(boolean throwExceptionA) throws ExceptionA, ExceptionB {
		if (throwExceptionA) {
			throw new ExceptionA();
		} else {
			throw new ExceptionB();
		}
	}
	
	public void interfaceMethodWithThrow() throws Exception {
		throw new Exception();
	}
	
}
