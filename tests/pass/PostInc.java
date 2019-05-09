package pass;

import java.lang.System;

public class PostInc {

    public int postInc(int x) {
        x++;
        return x;
    }

    public static void main(String[] args) {
        PostInc pi = new PostInc();
        System.out.println(pi.postInc(1));
    }

}
