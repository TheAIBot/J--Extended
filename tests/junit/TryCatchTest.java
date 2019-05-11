package junit;

import junit.framework.TestCase;
import java.io.EOFException;
import java.lang.RuntimeException;
import pass.ExceptionThrow;
import pass.TryCatch;

public class TryCatchTest extends TestCase {

	private TryCatch tryCatch;

	protected void setUp() throws Exception {
		super.setUp();
		tryCatch = new TryCatch();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCatchException() {
		assertTrue(tryCatch.catchException("test").equals("test"));
	}

	public void testCatchMethodException() {
		assertTrue(tryCatch.catchMethodException("test").equals("test"));
	}

	public void testMulticatch() {
		assertTrue(tryCatch.multicatchExceptions(true) instanceof EOFException);
		assertTrue(tryCatch.multicatchExceptions(false) instanceof RuntimeException);
	}

	public void testCatchEOFExceptionsGeneralized() {
		assertTrue(tryCatch.catchEOFExceptionsGeneralized() instanceof EOFException);
	}

	public void testAvoidCatchingSuperException() {
		try {
			tryCatch.avoidCatchingSuperException("test");
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("test"));
		}
	}

	public void testCatchError() {
		assertTrue(tryCatch.catchError("test").equals("test"));
	}

	public void testAvoidCatchingError() {
		try {
			tryCatch.avoidCatchingError("test");
			fail();
		} catch (Error e) {
			assertTrue(e.getMessage().equals("test"));
		}
	}

	public void testExceptionNestedTry() {
		assertTrue(tryCatch.catchAndRethrowExceptionNestedTry("test").equals("test"));
	}

	public void testCatchExceptionInOuterTry() {
		assertTrue(tryCatch.catchExceptionInOuterTry("test").equals("test"));
	}

	public void testMultipleCatch() {
		assertTrue(tryCatch.multipleCatch(true) instanceof EOFException);
		assertTrue(tryCatch.multipleCatch(false) instanceof RuntimeException);
	}

	public void testFinallyGetsRun() {
		assertTrue(tryCatch.finallyGetsRun("test").equals("test"));
	}

	public void testFinallyGetsRunBeforeReturn() {
		assertTrue(tryCatch.finallyGetsRunBeforeReturn("test").equals("test"));
	}
}
