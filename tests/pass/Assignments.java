package pass;

public class Assignments {

	public int plusAssignment(int init, int toAdd) {
		int x = init;
		x += toAdd;
		return x;
	}
	
	public int minusAssignment(int init, int toSubtract) {
		int x = init;
		x -= toSubtract;
		return x;
	}

	public int divAssignment(int init, int toDiv) {
		int x = init;
		x /= toDiv;
		return x;
	}
	
	public int multAssignment(int init, int toMult) {
		int x = init;
		x *= toMult;
		return x;
	}
	
	public int modAssignment(int init, int toMod) {
		int x = init;
		x %= toMod;
		return x;
	}
}
