package junit;

import junit.framework.TestCase;
import pass.BAND;

public class BANDTest extends TestCase {
	private BAND band;
	
	protected void setUp() throws Exception {
		super.setUp();
		band = new BAND();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testBAND() {
		this.assertEquals(band.and(10, 2), 2);
		this.assertEquals(band.and(70, 64), 64);
		this.assertEquals(band.and(123456, 0), 0);
	}
}
