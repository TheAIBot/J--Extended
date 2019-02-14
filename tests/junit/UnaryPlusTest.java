package junit;

import junit.framework.TestCase;
import pass.Division;
import pass.UnaryPlus;

public class UnaryPlusTest extends TestCase {

    private UnaryPlus uPlus;

    protected void setUp() throws Exception {
        super.setUp();
        uPlus = new UnaryPlus();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(5, uPlus.unaryPlus(5));
    }

}
