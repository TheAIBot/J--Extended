package junit;

import junit.framework.TestCase;
import pass.Subtract;

public class SubtractTest extends TestCase {

    private Subtract subtract;

    protected void setUp() throws Exception {
        super.setUp();
        subtract = new Subtract();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(subtract.subtract(50,42), 8);
        this.assertEquals(subtract.subtract(42,3), 39);
        this.assertEquals(subtract.subtract(127,3), 124);
        
        this.assertEquals(subtract.subtractD(0d,42d), -42d);
        this.assertEquals(subtract.subtractD(42d,1d), 41d);
        this.assertEquals(subtract.subtractD(128d,4d), 124d);
    }

}
