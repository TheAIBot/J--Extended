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
	}
}
