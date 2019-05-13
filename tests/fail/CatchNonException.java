package fail;

import java.lang.Exception;

public class CatchNonException {

	public static void main() {
		try {
			throw new Exception();
		} catch (Exception | String e) {
			return;
		}
	}
}
