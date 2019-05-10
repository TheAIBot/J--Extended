package junit;

import junit.framework.TestCase;
import pass.PlusAssign;

public class PlusAssignTest extends TestCase {
    private PlusAssign plusAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    plusAssign = new PlusAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testplusAssignTest() {
		TestCase.assertEquals(plusAssign.plusAssign(3 , 6),  3 + 6);
		TestCase.assertEquals(plusAssign.plusAssign(6 , 6),  6 + 6);
		TestCase.assertEquals(plusAssign.plusAssign(7 , 3),  7 + 3);
		TestCase.assertEquals(plusAssign.plusAssign(13, 2), 13 + 2);
		
		TestCase.assertEquals(plusAssign.plusAssignD(3d , 6d),  3d + 6d);
		TestCase.assertEquals(plusAssign.plusAssignD(6d , 6d),  6d + 6d);
		TestCase.assertEquals(plusAssign.plusAssignD(7d , 3d),  7d + 3d);
		TestCase.assertEquals(plusAssign.plusAssignD(13d, 2d), 13d + 2d);
	}
}
