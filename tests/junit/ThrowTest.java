package junit;

import junit.framework.TestCase;
import pass.ExceptionA;
import pass.ExceptionB;
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
	
	public void testThrowException() {
		try {
			thrower.throwException("test");
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("test"));
		}
	}
	
	public void testThrowError() {
		try {
			thrower.throwError("test");
			fail();
		} catch (Error e) {
			assertTrue(e.getMessage().equals("test"));
		}
	}
	
	public void testThrowInheritingException() {
		try {
			thrower.throwInheritingException("test");
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("test"));
		}
	}
	
	public void testThrowMultipleExceptions() {
		try {
			// Make the method throw ExceptionA
			thrower.throwMultipleExceptions(true);
			fail();
		} catch (ExceptionB e) {
			fail();
		} catch (ExceptionA e) {
		}
		
		try {
			// Make the method throw ExceptionB
			thrower.throwMultipleExceptions(false);
			fail();
		} catch (ExceptionA e) {
			fail();
		} catch (ExceptionB e) {
		}
	}
	
	public void testInterfaceMethodWithThrow() {
		try {
			interfaceThrower.interfaceMethodWithThrow();
			fail();
		} catch (Exception e) {
		}
	}
}
