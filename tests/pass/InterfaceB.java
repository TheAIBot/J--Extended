package pass;

import java.util.ArrayList;

/**
 * @author Kasper
 */


interface InterfaceA {
    public int f(int x);
    public Object q();
    public void manyParameters(int x, Object b, String d, double qq);
}

public class InterfaceB implements InterfaceA {
    public int f(int x) {
        return x * x;
    }

    public String q(){
        return "";
    }

    public void manyParameters(int x, String b, String d, double qq) {
    }
}
