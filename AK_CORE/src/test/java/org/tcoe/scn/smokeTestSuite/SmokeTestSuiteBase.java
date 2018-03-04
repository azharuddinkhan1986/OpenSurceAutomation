package org.tcoe.scn.smokeTestSuite;

import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.resources.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class SmokeTestSuiteBase extends TestBase {
	// check if the suite execution has to be skipped
				@BeforeSuite
				public void checkSuiteSkip() throws Exception
				{
					
					
					if(!TestUtil.isSuiteRunnable(suiteXls, "SmokeSuite")){
						throw new SkipException("Runmode of Smoke Suite set to no. So Skipping all tests in Smoke Suite");
					}
					
				}

}
