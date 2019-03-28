package junit;

import junit.framework.TestCase;
import pass.ExceptionThrow;
import pass.TryCatch;
import pass.ExceptionA;
import pass.ExceptionB;
import pass.ExceptionThrower;

public class TryCatchTest extends TestCase {

	TryCatch tryCatch;
	
	protected void setUp() throws Exception {
		super.setUp();
		tryCatch = new TryCatch();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testTryCatch() {
		ExceptionThrower throwerBoth = new ExceptionThrower() {
				@Override
				public void throwExceptionA() throws ExceptionA {
					throw new ExceptionA("exA");
				}
	
				@Override
				public void throwExceptionB() throws ExceptionB {
					throw new ExceptionB("exB");
				}
			};
		ExceptionThrower throwerB = new ExceptionThrower() {
				@Override
				public void throwExceptionA() throws ExceptionA {
					return;
				}
	
				@Override
				public void throwExceptionB() throws ExceptionB {
					throw new ExceptionB("exB");
				}
			};
		this.assertTrue(tryCatch.tryCatch(throwerBoth).equals("exA"));
		this.assertTrue(tryCatch.tryCatch(throwerB).equals("exB"));
		this.assertTrue(tryCatch.tryFinally(throwerBoth, "test").equals("test"));
	}
}
