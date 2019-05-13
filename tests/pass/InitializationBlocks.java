package pass;

import java.lang.System;

public class InitializationBlocks {

    public static int myStaticInt;
    public int myInstanceInt;

    static {
        int one = 1;
        int two = 2;

        myStaticInt = one + two;

        for(int i = 2; i > 0; i--) {
            myStaticInt += 1;
        }
    }

    {
        int one = 1;
        int two = 2;
        myInstanceInt = one + two;
    }
}