package junit;

import junit.framework.TestCase;
import pass.MinusAssign;

public class MinusAssignTest extends TestCase {
    private MinusAssign minusAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    minusAssign = new MinusAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testminusAssignTest() {
		TestCase.assertEquals(minusAssign.minusAssign(3 , 6),  3 - 6);
		TestCase.assertEquals(minusAssign.minusAssign(6 , 6),  6 - 6);
		TestCase.assertEquals(minusAssign.minusAssign(7 , 3),  7 - 3);
		TestCase.assertEquals(minusAssign.minusAssign(13, 2), 13 - 2);
		
		TestCase.assertEquals(minusAssign.minusAssignD(3d , 6d),  3d - 6d);
		TestCase.assertEquals(minusAssign.minusAssignD(6d , 6d),  6d - 6d);
		TestCase.assertEquals(minusAssign.minusAssignD(7d , 3d),  7d - 3d);
		TestCase.assertEquals(minusAssign.minusAssignD(13d, 2d), 13d - 2d);
	}
}
