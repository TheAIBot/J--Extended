package junit;

import junit.framework.TestCase;
import pass.ArithLeftShift;

public class ArithLeftShiftTest extends TestCase {
    private ArithLeftShift arithLeftShift;

    protected void setUp() throws Exception {
        super.setUp();
        arithLeftShift = new ArithLeftShift();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testArithLeftShift() {
        TestCase.assertEquals(arithLeftShift.arithLeftShift(0b00000000_00000000_00000000_00000001, 0), 0b00000000_00000000_00000000_00000001);
        TestCase.assertEquals(arithLeftShift.arithLeftShift(0b00000000_00000000_00000000_00000010, 1), 0b00000000_00000000_00000000_00000100);
        TestCase.assertEquals(arithLeftShift.arithLeftShift(0b00000000_00000000_00000000_10010011, 3), 0b00000000_00000000_00000100_10011000);
    }
}
