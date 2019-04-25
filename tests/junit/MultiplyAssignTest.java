package junit;

import junit.framework.TestCase;
import pass.MultiplyAssign;

public class MultiplyAssignTest extends TestCase {
    private MultiplyAssign multiplyAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    multiplyAssign = new MultiplyAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testmultiplyAssignTest() {
		TestCase.assertEquals(multiplyAssign.multiplyAssign(3 , 6),  3 * 6);
		TestCase.assertEquals(multiplyAssign.multiplyAssign(6 , 6),  6 * 6);
		TestCase.assertEquals(multiplyAssign.multiplyAssign(7 , 3),  7 * 3);
		TestCase.assertEquals(multiplyAssign.multiplyAssign(13, 2), 13 * 2);
	}
}
