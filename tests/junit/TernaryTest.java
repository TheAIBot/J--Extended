package junit;

import junit.framework.TestCase;
import pass.Ternary;

public class TernaryTest extends TestCase {
    private Ternary ternary;

	protected void setUp() throws Exception {
	    super.setUp();
	    ternary = new Ternary();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
    public void testTernary() {
    	TestCase.assertEquals(ternary.ternary(true, 1, 2), 1);
    	TestCase.assertEquals(ternary.ternary(false, 1, 2), 2);
    	
    	TestCase.assertEquals(ternary.ternaryD(true, 1d, 2d), 1d);
    	TestCase.assertEquals(ternary.ternaryD(false, 1d, 2d), 2d);
    }
}
