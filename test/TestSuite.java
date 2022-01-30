import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *  Test suite for all JUnit tests.
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {CoordTest.class, FrontierTest.class, StateTest.class, SearchTest.class}
)
public class TestSuite { }