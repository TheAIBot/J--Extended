package junit;

import junit.framework.TestCase;
import pass.Assignments;

public class AssignmentsTest extends TestCase {
	private Assignments assignments;
	
	protected void setUp() throws Exception {
		super.setUp();
		assignments = new Assignments();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPlusAssignment() {
		this.assertEquals(assignments.plusAssignment(10, 5), 15);
		this.assertEquals(assignments.plusAssignment(5, -2), 3);
	}
	
	public void testMinusAssignment() {
		this.assertEquals(assignments.minusAssignment(15, 2), 13);
		this.assertEquals(assignments.minusAssignment(5, -20), 25);
	}
	
	public void testDivAssignment() {
		this.assertEquals(assignments.divAssignment(20, 5), 4);
		this.assertEquals(assignments.divAssignment(5, 2), 2);
		this.assertEquals(assignments.divAssignment(10, -2), -5);
	}
	
	public void testMultAssignment() {
		this.assertEquals(assignments.multAssignment(5, 3), 15);
		this.assertEquals(assignments.multAssignment(5, -2), -10);
	}

	public void testModAssignment() {
		this.assertEquals(assignments.modAssignment(5, 3), 2);
		this.assertEquals(assignments.modAssignment(10, 10), 0);
		this.assertEquals(assignments.modAssignment(5, -2), 1);
	}
	
}


