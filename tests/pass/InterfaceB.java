package pass;

/**
 * @author Kasper
 */


interface InterfaceA {
    public int f(int x);
}

public class InterfaceB implements InterfaceA {
    public int f(int x) {
        return x * x;
    }
}
