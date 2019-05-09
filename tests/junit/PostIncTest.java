package junit;

import junit.framework.TestCase;
import pass.PostInc;

public class PostIncTest extends TestCase {

    private PostInc postInc;

    protected void setUp() throws Exception {
        super.setUp();
        postInc = new PostInc();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPostInc() {
        this.assertEquals(1, postInc.postInc(0));

    }

}