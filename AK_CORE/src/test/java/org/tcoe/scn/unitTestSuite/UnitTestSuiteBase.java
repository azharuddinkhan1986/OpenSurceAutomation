package org.tcoe.scn.unitTestSuite;

import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.resources.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class UnitTestSuiteBase extends TestBase {

	// check if the suite execution has to be skipped
	
				@BeforeSuite
				public void checkSuiteSkip() throws Exception
				{
					System.out.println("Before Unit");
					if(!TestUtil.isSuiteRunnable(suiteXls, "UnitSuite")){
						throw new SkipException("Runmode of Unit Suite set to no. So Skipping all tests in Unit Suite");
					}
					
				}
}
