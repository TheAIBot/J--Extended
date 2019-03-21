package pass;

public class TryCatch {
	
	public String tryCatch(ExceptionThrower callback) {
		String returnStr = null;
		try {
			callback.throwExceptionA();
			callback.throwExceptionB();
		} catch (ExceptionA e) {
			returnStr = e.getMessage();
		} catch (ExceptionB e) {
			returnStr = e.getMessage();
		} 
		return returnStr;
	}
	
	public String tryFinally(ExceptionThrower callback, String finallyString) {
		String returnStr = null;
		try {
			callback.throwExceptionA();
			callback.throwExceptionB();
		} catch (Exception e) {
			returnStr = e.getMessage();
		} finally {
			returnStr = finallyString;
		}
		return returnStr;
	}
	
	public static class ExceptionA extends Exception {
		public ExceptionA(String string) {
			super(string);
		}
	}
	
	public static class ExceptionB extends Exception {
		public ExceptionB(String string) {
			super(string);
		}
	}
	
	public static interface ExceptionThrower {
		public void throwExceptionA () throws ExceptionA;
		public void throwExceptionB () throws ExceptionB;
	}
}





