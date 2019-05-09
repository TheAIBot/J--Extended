package junit;

import junit.framework.TestCase;
import pass.ExceptionThrow;
import pass.InterfaceWithThrow;

public class ThrowTest extends TestCase {

	private ExceptionThrow thrower;
	private InterfaceWithThrow interfaceThrower;
	
	protected void setUp() throws Exception {
		super.setUp();
		thrower = new ExceptionThrow();
		interfaceThrower = new ExceptionThrow();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testThrow() {
		try {
			thrower.throwException("test");
		} catch (Exception e) {
			
		}
	}
}
