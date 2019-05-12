package junit;

import junit.framework.TestCase;
import pass.MultiLineComment;

public class MultiLineCommentTest extends TestCase {

    private MultiLineComment multiLineComment;

    protected void setUp() throws Exception {
        super.setUp();
        multiLineComment = new MultiLineComment();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMultiLineComment() {
        this.assertEquals(2, multiLineComment.testFunction());

    }

}