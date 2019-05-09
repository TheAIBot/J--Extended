package junit;

import junit.framework.TestCase;
import pass.Remainder;

public class RemainderTest extends TestCase {

    private Remainder remainder;

    protected void setUp() throws Exception {
        super.setUp();
        remainder = new Remainder();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRemainder() {
        this.assertEquals(remainder.remainder(10,4), 2);
        this.assertEquals(remainder.remainder(8,4), 0);
        this.assertEquals(remainder.remainder(10,12), 10);
        
        this.assertEquals(remainder.remainderD(10d,4d), 2d);
        this.assertEquals(remainder.remainderD(8d,4d), 0d);
        this.assertEquals(remainder.remainderD(10d,12d), 10d);
    }

}