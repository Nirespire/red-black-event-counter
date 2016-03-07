package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.DeleteTests;
import test.GetTests;
import test.InsertTests;

@RunWith(Suite.class)
@SuiteClasses({InsertTests.class, GetTests.class,  DeleteTests.class, AdvGetTests.class})
public class AllTests {
	
}