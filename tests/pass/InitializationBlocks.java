package pass;

import java.lang.System;

public class InitializationBlocks {

    public static int myStaticInt;
    public int myInstanceInt;

    static {
        int one = 2;
        int two = 1;

        myStaticInt = one + two;

        for(int i = 2; i > 0; i--) {
            myStaticInt += 1;
        }
    }

    {
        int one = 2;
        int two = 3;
        myInstanceInt = one + two;
    }

    public InitializationBlocks() {
        System.out.println(myInstanceInt);
    }

    public static void main(String[] args) {
        System.out.println(myStaticInt);
        new InitializationBlocks();
    }
}