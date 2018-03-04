package org.tcoe.scn.apiTestSuite;

import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.resources.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class APITestSuiteBase extends TestBase {
	// check if the suite execution has to be skipped
				@BeforeSuite
				public void checkSuiteSkip() throws Exception
				{
					
					
					if(!TestUtil.isSuiteRunnable(suiteXls, "APISuite")){
						throw new SkipException("Runmode of API Suite set to no. So Skipping all tests in API Suite");
					}
					
				}

}
