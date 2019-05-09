package junit;

import junit.framework.TestCase;
import pass.BORAssign;

public class BORAssignTest extends TestCase {
    private BORAssign bORAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    bORAssign = new BORAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testbORAssignTest() {
		TestCase.assertEquals(bORAssign.bORAssign(3 , 6),  3 | 6);
		TestCase.assertEquals(bORAssign.bORAssign(6 , 6),  6 | 6);
		TestCase.assertEquals(bORAssign.bORAssign(7 , 3),  7 | 3);
		TestCase.assertEquals(bORAssign.bORAssign(13, 2), 13 | 2);
	}
}
