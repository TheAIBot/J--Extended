package pass;

/**
 * @author Kasper
 */

public class MultiLineComment {

    public int testFunction(){
        int x = 0;
        /*
         / * / //////////*******
        * *******
         * //// Ø/
        * * /*

         */
        x = 2;
        // more test
        /* this also works */
        return x;
    }

}
