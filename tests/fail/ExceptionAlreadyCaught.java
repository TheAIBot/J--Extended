package fail;

import java.lang.Exception;

public class ExceptionAlreadyCaught {

	public static void main() {
		try {
			throw new Exception(); 
		} catch (Exception e) {
			
		} catch (Exception e) {
			
		}
	}
	
}
