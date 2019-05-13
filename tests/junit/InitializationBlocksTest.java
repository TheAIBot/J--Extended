package junit;

import junit.framework.TestCase;
import pass.InitializationBlocks;

public class InitializationBlocksTest extends TestCase {

    private InitializationBlocks initBlocks;

    protected void setUp() throws Exception {
        super.setUp();
        initBlocks = new InitializationBlocks();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStaticInitializationBlocks() {
        assertEquals(5, initBlocks.myStaticInt);
    }

    public void testInstanceInitializationBlocks() {
        assertEquals(3, initBlocks.myInstanceInt);
    }

}