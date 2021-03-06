package junit;

import junit.framework.TestCase;
import pass.DivAssign;

public class DivAssignTest extends TestCase {
    private DivAssign divAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    divAssign = new DivAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testdivAssignTest() {
		TestCase.assertEquals(divAssign.divAssign(3 , 6),  3 / 6);
		TestCase.assertEquals(divAssign.divAssign(6 , 6),  6 / 6);
		TestCase.assertEquals(divAssign.divAssign(7 , 3),  7 / 3);
		TestCase.assertEquals(divAssign.divAssign(13, 2), 13 / 2);
		
		TestCase.assertEquals(divAssign.divAssignD(3d , 6d),  3d / 6d);
		TestCase.assertEquals(divAssign.divAssignD(6d , 6d),  6d / 6d);
		TestCase.assertEquals(divAssign.divAssignD(7d , 3d),  7d / 3d);
		TestCase.assertEquals(divAssign.divAssignD(13d, 2d), 13d / 2d);
	}
}
