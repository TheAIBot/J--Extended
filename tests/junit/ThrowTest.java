package junit;

import junit.framework.TestCase;
import pass.ExceptionThrow;

public class ThrowTest extends TestCase {

	private ExceptionThrow thrower;
	
	protected void setUp() throws Exception {
		super.setUp();
		thrower = new ExceptionThrow();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testBXOR() {
		try {
			thrower.throwException("test");
			this.fail("No exception thrown");
		} catch (Exception e) {
			this.assertTrue(e.getMessage().equals("test"));
		}
	}
	
}
