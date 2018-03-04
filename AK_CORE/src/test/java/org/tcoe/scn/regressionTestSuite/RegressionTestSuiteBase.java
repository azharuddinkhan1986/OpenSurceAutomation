package org.tcoe.scn.regressionTestSuite;

import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.resources.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class RegressionTestSuiteBase extends TestBase {

	// check if the suite execution has to be skipped
			@BeforeSuite
			public void checkSuiteSkip() throws Exception
			{
				
				System.out.println("Before Regression Suite");
				if(!TestUtil.isSuiteRunnable(suiteXls, "RegressionSuite")){
					throw new SkipException("Runmode of Regression Suite set to no. So Skipping all tests in Regression Suite");
				}
				
			}
}


