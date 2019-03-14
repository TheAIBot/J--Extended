package junit;

import junit.framework.TestCase;

public class TernaryTest extends TestCase {

    public void testArithLeftShift() {
    	boolean tt1 = true;
    	boolean tt2 = true;
    	boolean ff1 = false;
    	boolean ff2 = false;
    	
    	TestCase.assertEquals(tt1 == tt2 ? 1 : 2, 1);
    	TestCase.assertEquals(tt1 != tt2 ? 1 : 2, 2);
    	TestCase.assertEquals((tt1 || ff1) && (ff1 && ff2) ? 1 : 2, 2);
    	TestCase.assertEquals((tt1 || ff1) || (ff1 && ff2) ? 1 : 2, 1);
    }
}
