package pass;

import java.util.ArrayList;

/**
 * @author Kasper
 */


interface InterfaceA {
    public int f(int x);
    public Object q();
}

public class InterfaceB implements InterfaceA {
    public int f(int x) {
        return x * x;
    }

    public String q(){
        return "";
    }
}
