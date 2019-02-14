package junit;

import junit.framework.TestCase;
import pass.Complement;

public class ComplementTest extends TestCase {
	private Complement complement;
	
	protected void setUp() throws Exception {
		super.setUp();
		complement = new Complement();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testComplement() {
		this.assertEquals(complement.complement(2), -3);
		this.assertEquals(complement.complement(17000), -17001);
		this.assertEquals(complement.complement(-25), 24);
	}
}
