package junit;

import junit.framework.TestCase;
import pass.ArithLeftShiftAssign;

public class ArithLeftShiftAssignTest extends TestCase {
    private ArithLeftShiftAssign arithLeftShiftAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    arithLeftShiftAssign = new ArithLeftShiftAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testarithLeftShiftAssignTest() {
		TestCase.assertEquals(arithLeftShiftAssign.arithLeftShiftAssign(3 , 6),  3 << 6);
		TestCase.assertEquals(arithLeftShiftAssign.arithLeftShiftAssign(6 , 6),  6 << 6);
		TestCase.assertEquals(arithLeftShiftAssign.arithLeftShiftAssign(7 , 3),  7 << 3);
		TestCase.assertEquals(arithLeftShiftAssign.arithLeftShiftAssign(13, 2), 13 << 2);
	}
}
