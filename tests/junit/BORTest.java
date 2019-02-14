package junit;

import junit.framework.TestCase;
import pass.BOR;

public class BORTest extends TestCase {
	private BOR bor;
	
	protected void setUp() throws Exception {
		super.setUp();
		bor = new BOR();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testBOR() {
		this.assertEquals(bor.or(10, 2), 10);
		this.assertEquals(bor.or(5, 3), 7);
		this.assertEquals(bor.or(-2, 1), -1);
	}
	
}
