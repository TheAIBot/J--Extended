package junit;

import junit.framework.TestCase;
import pass.LogicRightShiftAssign;

public class LogicRightShiftAssignTest extends TestCase {
    private LogicRightShiftAssign logicRightShiftAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    logicRightShiftAssign = new LogicRightShiftAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testlogicRightShiftAssignTest() {
		TestCase.assertEquals(logicRightShiftAssign.logicRightShiftAssign(3 , 6),  3 >>> 6);
		TestCase.assertEquals(logicRightShiftAssign.logicRightShiftAssign(6 , 6),  6 >>> 6);
		TestCase.assertEquals(logicRightShiftAssign.logicRightShiftAssign(7 , 3),  7 >>> 3);
		TestCase.assertEquals(logicRightShiftAssign.logicRightShiftAssign(13, 2), 13 >>> 2);
	}
}
