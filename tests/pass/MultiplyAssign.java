package pass;

import java.lang.System;

public class MultiplyAssign {

	public static int multiplyAssign(int x, int y) {
		int t = x;
		t *= y;
		return t;
	}
	
	public static double multiplyAssignD(double x, double y) {
		double t = x;
		t *= y;
		return t;
	}
	
	public static void main(String[] args) {
		System.out.println(MultiplyAssign.multiplyAssignD(3d, 4d));
	}
}
