package fail;

public class ThrowNonException {

	public static void main() {
		try {
			throw new String();
		} catch (String e) {
			
		}
	}
	
}
