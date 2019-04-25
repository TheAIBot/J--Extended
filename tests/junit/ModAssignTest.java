package junit;

import junit.framework.TestCase;
import pass.ModAssign;

public class ModAssignTest extends TestCase {
    private ModAssign modAssign;

	protected void setUp() throws Exception {
	    super.setUp();
	    modAssign = new ModAssign();
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public void testmodAssignTest() {
		TestCase.assertEquals(modAssign.modAssign(3 , 6),  3 % 6);
		TestCase.assertEquals(modAssign.modAssign(6 , 6),  6 % 6);
		TestCase.assertEquals(modAssign.modAssign(7 , 3),  7 % 3);
		TestCase.assertEquals(modAssign.modAssign(13, 2), 13 % 2);
	}
}
