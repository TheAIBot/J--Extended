package junit;

import junit.framework.TestCase;
import pass.Multiply;

public class MultiplyTest extends TestCase {

    private Multiply multiply;

    protected void setUp() throws Exception {
        super.setUp();
        multiply = new Multiply();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(multiply.multiply(0,42), 0);
        this.assertEquals(multiply.multiply(42,1), 42);
        this.assertEquals(multiply.multiply(127,3), 381);
        
        this.assertEquals(multiply.multiplyD(0d,42d), 0d);
        this.assertEquals(multiply.multiplyD(42d,1d), 42d);
        this.assertEquals(multiply.multiplyD(128d,4d), 512d);
    }

}
