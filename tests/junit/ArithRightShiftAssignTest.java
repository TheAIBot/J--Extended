package junit;

import junit.framework.TestCase;
import pass.ArithRightShiftAssign;

public class ArithRightShiftAssignTest extends TestCase {
    private ArithRightShiftAssign arithRightShiftAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    arithRightShiftAssign = new ArithRightShiftAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testarithRightShiftAssignTest() {
		TestCase.assertEquals(arithRightShiftAssign.arithRightShiftAssign(3 , 6),  3 >> 6);
		TestCase.assertEquals(arithRightShiftAssign.arithRightShiftAssign(6 , 6),  6 >> 6);
		TestCase.assertEquals(arithRightShiftAssign.arithRightShiftAssign(7 , 3),  7 >> 3);
		TestCase.assertEquals(arithRightShiftAssign.arithRightShiftAssign(13, 2), 13 >> 2);
	}
}
