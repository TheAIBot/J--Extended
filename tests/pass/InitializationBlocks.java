package pass;

public class InitializationBlocks {

    public static int myStaticInt;
    public int myInstanceInt;

    static {
        myStaticInt = 4;
    }

    {
        myInstanceInt = 5;
    }
}