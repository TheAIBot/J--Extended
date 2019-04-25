package junit;

import junit.framework.TestCase;
import pass.BANDAssign;

public class BANDAssignTest extends TestCase {
    private BANDAssign bANDAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    bANDAssign = new BANDAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testbANDAssignTest() {
		TestCase.assertEquals(bANDAssign.bANDAssign(3 , 6),  3 & 6);
		TestCase.assertEquals(bANDAssign.bANDAssign(6 , 6),  6 & 6);
		TestCase.assertEquals(bANDAssign.bANDAssign(7 , 3),  7 & 3);
		TestCase.assertEquals(bANDAssign.bANDAssign(13, 2), 13 & 2);
	}
}
