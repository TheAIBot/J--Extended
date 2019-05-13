package pass;

public class Doubles {
	
	public double[] getDoubles() {
		double[] doubles = new double[3];
		doubles[0] = 3d;
		doubles[1] = 2.1;
		doubles[2] = 4e1;
		
		return doubles;
	}

	public int doubleToInt(double d) {
		return (int)d;
	}
	
	public double intToDouble(int i) {
		return (double)i;
	}
}
