package pass;

public class ExceptionThrow implements InterfaceWithThrow {

	public void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	@Override
	public void interfaceMethodWithThrow() throws Exception {
		throw new Exception();
	}
	
}
