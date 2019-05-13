package pass;

import java.util.ArrayList;

/**
 * @author Kasper
 */

public interface InterfaceA {
    public int f(int x);
    public Object q();
    public void manyParameters(int x, Object b, String d, double qq);

    public String someField = "field hell yeah";
}

public interface InterfaceB {
    public String toString();
}

public class Interfaces implements pass.InterfaceA, pass.InterfaceB {
    public int f(int x) {
        return x * x;
    }

    public String q(){
        return InterfaceA.someField;
    }

    public void manyParameters(int x, String b, String d, double qq) {
    }
    public String toString(){
        return "InterfaceB was implemented (:";
    }
}
