package pass;

/**
 * @author Kasper
 */

interface InterfaceA {
    public int f(int x);
}

public class InterfaceB implements pass.InterfaceA {
    public int f(int x) {
        return x * x;
    }
}
