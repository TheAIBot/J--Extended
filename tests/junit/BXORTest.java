package junit;

import junit.framework.TestCase;
import pass.BXOR;

public class BXORTest extends TestCase {
	private BXOR bxor;
	
	protected void setUp() throws Exception {
		super.setUp();
		bxor = new BXOR();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testBXOR() {
		this.assertEquals(bxor.xor(21, 10), 31);
		this.assertEquals(bxor.xor(-1, 3), -4);
		this.assertEquals(bxor.xor(10, 8), 2);
	}
}
