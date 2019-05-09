package fail;

public class Throw implements pass.InterfaceWithThrow {
	
	// a thrown type must be a throwable
	public static void main(String[] args) throws Throw {
		throw new Throw();
	}

	// May not throw a supertype
	@Override
	public void interfaceMethodWithThrow() throws Exception {
		throw new Throwable();
	}
}
