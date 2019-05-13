// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import java.io.File;
import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import pass.*;

/**
 * JUnit test suite for running the j-- programs in tests/pass.
 */

public class JMinusMinusTestRunner {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HelloWorldTest.class);
        suite.addTestSuite(FactorialTest.class);
        suite.addTestSuite(GCDTest.class);
        suite.addTestSuite(SeriesTest.class);
        suite.addTestSuite(ClassesTest.class);
        suite.addTestSuite(ComplementTest.class);
        suite.addTestSuite(BANDTest.class);
        suite.addTestSuite(BXORTest.class);
        suite.addTestSuite(BORTest.class);
        suite.addTestSuite(DivisionTest.class);
        suite.addTestSuite(RemainderTest.class);
        suite.addTestSuite(ArithLeftShiftTest.class);
        suite.addTestSuite(ArithRightShiftTest.class);
        suite.addTestSuite(LogicRightShiftTest.class);
        suite.addTestSuite(UnaryPlusTest.class);
        suite.addTestSuite(AssignmentsTest.class);
        suite.addTestSuite(ArithLeftShiftAssignTest.class);
        suite.addTestSuite(ArithRightShiftAssignTest.class);
        suite.addTestSuite(LogicRightShiftAssignTest.class);
        suite.addTestSuite(BANDAssignTest.class);
        suite.addTestSuite(BORAssignTest.class);
        suite.addTestSuite(BXORAssignTest.class);
        suite.addTestSuite(DivAssignTest.class);
        suite.addTestSuite(MinusAssignTest.class);
        suite.addTestSuite(ModAssignTest.class);
        suite.addTestSuite(MultiplyAssignTest.class);
        suite.addTestSuite(PlusAssignTest.class);
        suite.addTestSuite(TernaryTest.class);
        suite.addTestSuite(PostIncTest.class);
        suite.addTestSuite(MultiLineCommentTest.class);
        suite.addTestSuite(InterfaceTest.class);
        suite.addTestSuite(PlusTest.class);
        suite.addTestSuite(SubtractTest.class);
        suite.addTestSuite(MultiplyTest.class);
        suite.addTestSuite(TryCatchTest.class);
        suite.addTestSuite(ThrowTest.class);
        suite.addTestSuite(ForLoopsTest.class);
        suite.addTestSuite(InitializationBlocksTest.class);
        suite.addTestSuite(DoubleTest.class);
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
