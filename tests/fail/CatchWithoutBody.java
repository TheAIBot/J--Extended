package fail;

import java.lang.Exception;

public class CatchWithoutBody {
	
	public static void main() {
		try {
			throw new Exception();
		} catch (Exception e);
	}
	
}
