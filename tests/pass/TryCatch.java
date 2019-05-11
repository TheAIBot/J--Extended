package pass;

import java.lang.Exception;

public class TryCatch {
	
	private void throwException(String message) throws Exception {
		throw new Exception(message);
	}
	
	public String catchException(String message) {
		String exMessage = "";
		try {
			throw new Exception(message);
		} catch (Exception e) {
			exMessage = e.getMessage();
		}
		return exMessage;
	}
	
	public String catchMethodException(String message) {
		String exMessage = "";
		try {
			throwException(message);
		} catch (Exception e) {
			exMessage = e.getMessage();
		}
		return exMessage;
	}
	
	public Exception multicatchExceptions(boolean throwExceptionA) {
		try {
			if (throwExceptionA) {
				throw new ExceptionA();
			} else {
				throw new ExceptionB();
			}
		} catch (ExceptionA | ExceptionB e) {
			return e;
		}
	}
	
	public Exception catchExceptionAsGeneralized() {
		try {
			throw new ExceptionA();
		} catch (Exception e) {
			return e;
		}
	}
	
	public void avoidCatchingSuperException(String message) throws Exception {
		try {
			throw new Exception(message);
		} catch (ExceptionA e) {
		}
	}
	
	public String catchError(String message) {
		try {
			throw new Error(message);
		} catch (Error e) {
			return e.getMessage();
		}
	}
	
	private void throwError(String message) {
		throw new Error(message);
	}
	
	public void avoidCatchingError(String message) {
		try {
			throwError(message);
		} finally {
		}
	}
	
	public String catchAndRethrowExceptionNestedTry(String message) {
		try {
			try {
				throw new Exception(message);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String catchExceptionInOuterTry(String message) {
		try {
			try {
				throw new Exception(message);
			} catch (ExceptionA e) {
				return new String();
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public Exception multipleCatch(boolean throwExceptionA) {
		try {
			if (throwExceptionA) {
				throw new ExceptionA();
			} else {
				throw new ExceptionB();
			}
		} catch (ExceptionA e) {
			return e;
		} catch (ExceptionB e) {
			return e;
		}
	}
	
	public String finallyGetsRun(String message) {
		String returnMessage = "";
		try {
			throw new Exception();
		} catch (Exception e) {
		} finally {
			returnMessage = message;
		}
		return returnMessage;
	}
	
	public String finallyGetsRunBeforeReturn(String message) {
		String returnMessage = "";
		try {
			throw new Exception();
		} catch (Exception e) {
			return returnMessage;
		} finally {
			returnMessage = message;
		}
	}
}
