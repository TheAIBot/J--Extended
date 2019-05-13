package pass;

import java.lang.System;

public class ForLoops {

    public static void main(String[] args) {
        ForLoops forLoops = new ForLoops();
        int[] intArray = {1, 2, 3, 4, 5};
        int sum = forLoops.forEachLoop(intArray);
        System.out.println(sum);
    }

    public int forLoop(int startValue, int endValue, int increment) {
        int rounds = 0;
        for(int i = startValue; endValue > i; i += increment) {
            rounds++;
        }

        return rounds;
    }

    public int forEachLoop(int[] array) {
        int sum = 0;
        //int[] intArray = {1, 2, 3, 4, 5};
        for(int i : array) {
            //System.out.println("Integer For Each Loop " + i);
            sum += i;
        }

        return sum;
    }

    public double forEachLoop(double[] array) {
        double sum = 0d;
        for(double d : array) {
            sum += d;
        }

        return sum;
    }
}