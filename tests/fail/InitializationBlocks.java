package pass;

public class InitializationBlocks {

    public static int myStaticInt;
    public int myInstanceInt;

    static {
        myInstanceInt = 5;
        myStaticInt = 4;
    }
}