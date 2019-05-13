package junit;

import junit.framework.TestCase;
import pass.Doubles;

public class DoubleTest extends TestCase {

    private Doubles doubles;

    protected void setUp() throws Exception {
        super.setUp();
        doubles = new Doubles();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(doubles.doubleToInt(42d), 42);
        this.assertEquals(doubles.doubleToInt(-3d), -3);
        this.assertEquals(doubles.doubleToInt(27.3), 27);
        
        this.assertEquals(doubles.intToDouble(0), 0d);
        this.assertEquals(doubles.intToDouble(42), 42d);
        this.assertEquals(doubles.intToDouble(32), 32d);
        
        double[] dds = doubles.getDoubles();
        dds[1] = 3.1415;
    }

}
