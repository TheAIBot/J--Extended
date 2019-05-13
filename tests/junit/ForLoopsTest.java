package junit;

import junit.framework.TestCase;
import pass.ForLoops;

public class ForLoopsTest extends TestCase {

    private ForLoops forLoops;

    protected void setUp() throws Exception {
        super.setUp();
        forLoops = new ForLoops();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testForLoops() {
        this.assertEquals(10, forLoops.forLoop(0, 10, 1));
        this.assertEquals(3, forLoops.forLoop(0, 10, 4));
    }

    public void testForEachLoops() {
        int[] intArray = {1, 2, 3, 4, 5};
        this.assertEquals(15, forLoops.forEachLoop(intArray));
        this.assertEquals(75, forLoops.nestedForEachLoop(intArray));

        double[] doubleArray = {2d, 4d, 6d};
        this.assertEquals(12d, forLoops.forEachLoop(doubleArray));
        this.assertEquals(36d, forLoops.nestedForEachLoop(doubleArray));
    }

}