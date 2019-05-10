package junit;

import junit.framework.TestCase;
import pass.BXORAssign;

public class BXORAssignTest extends TestCase {
    private BXORAssign bXORAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    bXORAssign = new BXORAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testbXORAssignTest() {
		TestCase.assertEquals(bXORAssign.bXORAssign(3 , 6),  3 ^ 6);
		TestCase.assertEquals(bXORAssign.bXORAssign(6 , 6),  6 ^ 6);
		TestCase.assertEquals(bXORAssign.bXORAssign(7 , 3),  7 ^ 3);
		TestCase.assertEquals(bXORAssign.bXORAssign(13, 2), 13 ^ 2);
	}
}
