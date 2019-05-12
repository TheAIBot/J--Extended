package pass;

/**
 * @author Kasper
 */

public class MultiLineUnclosed {

    public void testFunction(){
        int x = 0;
        /* this is
        a multi line comment
        that i will forget to close

        This means that the rest of the class will be commented out,
        thus giving a compiling issue
    }

}
