package org.tcoe.scn.unitTestSuite;
import org.tcoe.scn.resources.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author SESA443020- Azharuddin Khan
 * @description: This Scripts is used to Perform Login@HOME
 * @Detail:UC-05: Login from an IDMS Page
 * @CoveredUsecases: 1)UC-05-OKTS-01: Successful login with existing user in IDMS
 */
public class TestCase1 extends UnitTestSuiteBase {
	
	
	
	//Initialize innstance variables
	String runmodes[]=null;
	//intial counter is negative
	static int count=-1;
	//skip variable set to false into test data sheet
	public static boolean skip=false;
	//pass variable set to false into test data sheet
	public static boolean pass=false;
	//fail variable set to false into test data sheet
	public static boolean fail=false;
	//set result overall in test case sheet
	public static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip()
	{  
		if(!TestUtil.isTestCaseRunnable(unitSuiteXls,this.getClass().getSimpleName()))
		{
			
			test=report.startTest(this.getClass().getSimpleName());
			test.log(LogStatus.SKIP,this.getClass().getSimpleName()+" Skipped.");
			skip=true;
			reportTestResult();
			throw new SkipException("Test case skipped.");
		}
		//load the runmodes of the tests
		runmodes=TestUtil.getDataSetRunmodes(unitSuiteXls, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void idmsHomeLoginTest(String username) throws Exception
	{  
		
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			test=report.startTest(this.getClass().getSimpleName());
			test.log(LogStatus.SKIP,this.getClass().getSimpleName()+" Skipped.");
			reportTestResult();
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		//1. Start Test
		test=report.startTest(this.getClass().getSimpleName());
		//2.Open Browser
		openBrowser(CONFIG.getProperty("Browser"));
	}
	    
	
	//Parameterize test case
	@DataProvider
	public Object[][] getTestData()
	{
		return TestUtil.getData(unitSuiteXls, this.getClass().getSimpleName());
	}
	
	@AfterTest
	public void reportTestResult()
	{
		if(isTestPass)
		{
			TestUtil.reportDataSetResult(unitSuiteXls,"Test Cases",TestUtil.getRowNum(unitSuiteXls,this.getClass().getSimpleName()),"PASS");
		}
		else
		{
			TestUtil.reportDataSetResult(unitSuiteXls,"Test Cases",TestUtil.getRowNum(unitSuiteXls,this.getClass().getSimpleName()),"FAIL");
		}
		if(skip&&isTestPass)
		{
			TestUtil.reportDataSetResult(unitSuiteXls,"Test Cases",TestUtil.getRowNum(unitSuiteXls,this.getClass().getSimpleName()),"SKIP");
		}
	}	
	
	
	@AfterMethod
	public void reportDataSetResult()
	{
		if(driver != null)
		{
			closeAllBrowser();
		}
		
		if(skip)
			TestUtil.reportDataSetResult(unitSuiteXls, this.getClass().getSimpleName(),count+2,"SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(unitSuiteXls, this.getClass().getSimpleName(),count+2,"FAIL");
		}
		else
			TestUtil.reportDataSetResult(unitSuiteXls, this.getClass().getSimpleName(),count+2,"PASS");
		skip=false;
		fail=false;
		report.endTest(test);
		report.flush();
	}
	
	
}
