package junit;

import junit.framework.TestCase;
import pass.Plus;

public class PlusTest extends TestCase {

    private Plus plus;

    protected void setUp() throws Exception {
        super.setUp();
        plus = new Plus();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(plus.plus(3,42), 45);
        this.assertEquals(plus.plus(42,1), 43);
        this.assertEquals(plus.plus(127,3), 130);
        
        this.assertEquals(plus.plusD(3d,42d), 45d);
        this.assertEquals(plus.plusD(42d,1d), 43d);
        this.assertEquals(plus.plusD(128d,4d), 132d);
    }

}
