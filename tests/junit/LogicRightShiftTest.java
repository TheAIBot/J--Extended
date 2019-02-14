package junit;

import junit.framework.TestCase;
import pass.LogicRightShift;

public class LogicRightShiftTest extends TestCase {
    private LogicRightShift logicRightShift;

	protected void setUp() throws Exception {
	    super.setUp();
	    logicRightShift = new LogicRightShift();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testlogicRightShift() {
	    TestCase.assertEquals(logicRightShift.logicRightShift(0b00000000_00000000_00000000_00000001, 0), 0b00000000_00000000_00000000_00000001);
	    TestCase.assertEquals(logicRightShift.logicRightShift(0b10000000_00000000_00000000_00000010, 1), 0b01000000_00000000_00000000_00000001);
	    TestCase.assertEquals(logicRightShift.logicRightShift(0b10000000_00010000_00000000_00100010, 3), 0b00010000_00000010_00000000_00000100);
	}
}
