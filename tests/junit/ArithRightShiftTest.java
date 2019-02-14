package junit;

import junit.framework.TestCase;
import pass.ArithRightShift;

public class ArithRightShiftTest extends TestCase {
    private ArithRightShift arithRightShift;

    protected void setUp() throws Exception {
        super.setUp();
        arithRightShift = new ArithRightShift();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testArithRightShift() {
        TestCase.assertEquals(arithRightShift.arithRightShift(0b00000000_00000000_00000000_00000001, 0), 0b00000000_00000000_00000000_00000001);
        TestCase.assertEquals(arithRightShift.arithRightShift(0b10000000_00000000_00000000_00000010, 1), 0b11000000_00000000_00000000_00000001);
        TestCase.assertEquals(arithRightShift.arithRightShift(0b10000000_00010000_00000000_00100010, 3), 0b11110000_00000010_00000000_00000100);
    }
}
