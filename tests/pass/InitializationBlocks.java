package pass;

import java.lang.System;

public class InitializationBlocks {

    public static int myStaticInt;
    public int myInstanceInt;

    static {
        int one = 2;
        int two = 1;

        myStaticInt = one + two;
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