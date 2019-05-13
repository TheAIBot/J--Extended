package junit;

import junit.framework.TestCase;
import pass.Interfaces;
import pass.InterfaceA;
import pass.InterfaceB;

public class InterfaceTest extends TestCase {

    private Interfaces interfaces;
    private InterfaceA interfaceA;
    private InterfaceB interfaceB;

    protected void setUp() throws Exception {
        super.setUp();
        interfaces = new Interfaces();
        interfaceA = new Interfaces();
        interfaceB = new Interfaces();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInterfaces() {
        this.assertEquals("InterfaceB was implemented (:", interfaces.toString());
        this.assertEquals("InterfaceB was implemented (:", interfaceB.toString());
        this.assertEquals(25, interfaces.f(5));
        this.assertEquals(25, interfaceA.f(5));

        this.assertEquals("field hell yeah", interfaces.q());
    }

}