package org.tcoe.scn.base;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tcoe.scn.resources.ExtentManager;
import org.tcoe.scn.resources.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import com.schneider.electric.idms.utility.TestUtil;
//import com.schneider.electric.idms.utility.Xls_Reader;

public class TestBase {
//#################################################################################################################################################
	public static WebDriver driver=null;
	public static WebDriverWait explicitWait=null;
	//fail variable set to false into test data sheet
	public static boolean fail=false;
	public static String screenshot=null;
	public static Actions act;
	public static WebElement element=null;
	public static Properties OR=null;
	public static Properties CONFIG=null;
	public static Properties URL=null;
	//Intializaing Suite object at Global level
	public static Xls_Reader suiteXls=null;
	//Intializaing object of Suite one at Global level 
	public static Xls_Reader resgressionSuiteXls=null;
	//Intializaing object of Suite one at Global level 
		public static Xls_Reader smokeSuiteXls=null;
		//Intializaing object of Suite one at Global level 
		public static Xls_Reader unitSuiteXls=null;
		//Intializaing object of Suite one at Global level 
		public static Xls_Reader apiSuiteXls=null;
	//Intilaizing Extent Report object for Starting Writing Report During Runtime at global level(Class level)
	public static ExtentReports report;
	//Initializing Extent Test Object for capturing all the steps into Extent report and Screenshots at global level(Class level)
	public static ExtentTest test;
	//#########################################################################################################################################
	/**
	 * Base Class Constructor to initialize all required Properties Files,webdriver instance,Extent Report instance,reading file(xls) instance.
	 */
	public TestBase()
	{
		if(driver==null){
			//Configuring OR Properties File	
			OR=new Properties();
			CONFIG=new Properties();
			URL=new Properties();
			FileInputStream fs=null;
			try {
				//Load Config Properties file
				fs=new FileInputStream(System.getProperty("user.dir")+"\\Config.properties");
				CONFIG.load(fs);
				//Load Object Repository file
				fs = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\OR.properties");
				OR.load(fs);
				fs=new FileInputStream(System.getProperty("user.dir")+"\\Properties\\url.properties");
				URL.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
				return ;
			}
			finally
			{
				try {
					if(fs!=null)
						fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//Using Reflection Concept to get the instance of Extent Manager Class	
			report=ExtentManager.getInstance();
			//Getting Log using getLogger method of Logger class
			
			//Load Config Config.properties file
		
			//initialize excel files
			suiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\xls\\Suite.xlsx");
			resgressionSuiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\xls\\RegressionSuite.xlsx");
			smokeSuiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\xls\\SmokeSuite.xlsx");
			unitSuiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\xls\\UnitSuite.xlsx");
			apiSuiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\xls\\APISuite.xlsx");
		}
	}

//#################################################################################################################################
	/**
	 * @author Azharuddin Khan
	 * Method name: openBrowser
	 * @param browser
	 * @descrition: This method is used for open browser instance passing browser as parameter
	 *              Browser parameter value will be taken from config.properties file
	 *              Other useful actions are : maximizing window,applying implicit wait,deleting cookies/session
	 *             
	 *              overlappingCheckDisabled(in case of mozilla)
	 *              
	 *              Example: openBrowser(Config.getProperty("Browser"));
	 *              
	 *              
	 */
	public static void openBrowser(String browser)
	{
		try{
			if(browser.equals("Mozilla1")){
				driver=new FirefoxDriver();
				act=new Actions(driver);
				explicitWait=new WebDriverWait(driver, 120);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("overlappingCheckDisabled", true);
				
				test.log(LogStatus.INFO,CONFIG.getProperty("Browser")+" Browser Has Been Launched.");
			}
			else if(browser.equals("Mozilla")){
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
				driver=new FirefoxDriver();
				act=new Actions(driver);
				explicitWait=new WebDriverWait(driver, 120);
				test.log(LogStatus.INFO,CONFIG.getProperty("Browser")+" Browser Has Been Launched.");
			}
			
			else if(browser.equals("IE")){
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Drivers\\IEDriverServer_old.exe"); 
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver=new InternetExplorerDriver();
				act=new Actions(driver);
				explicitWait=new WebDriverWait(driver, 120);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				test.log(LogStatus.INFO,CONFIG.getProperty("Browser")+" Browser Has Been Launched.");

			}
			else if(browser.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
			    options.setExperimentalOption("useAutomationExtension", false);
				driver=new ChromeDriver(options);
				act=new Actions(driver);
				explicitWait=new WebDriverWait(driver, 120);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
				test.log(LogStatus.INFO,"Chrome Browser Has Been Launched.");

			}
			else if(browser.equals("ChromeProfile")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				//User Profile can be dynamic
				String userProfile= "C:\\Users\\sesa443020\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
				System.out.println(userProfile);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir="+userProfile);
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);
				act=new Actions(driver);
				explicitWait=new WebDriverWait(driver, 120);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				test.log(LogStatus.INFO,CONFIG.getProperty("Browser")+" Browser Has Been Launched.");

			}
		}
		catch(Exception e)
		{
			//Write Exception into Extent Report if it fails
			//Write Exception into Log file if it fails	
			test.log(LogStatus.FAIL,e.getMessage());
			test.log(LogStatus.INFO,CONFIG.getProperty("Browser")+" Browser Has Not Been Launched.");
		}
	}	

//###########################################################################################################################################
	/**
	 * @author Azharuddin khan
	 * Method name:turnOnImplicitWaits
	 * @description:Turning on Implicit wait feature and default maximum  value is 20 seconds to wait for the element on page.
	 * 
	 */
	public static void turnOnImplicitWaits()
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
//##############################################################################################################################################
	/**
	 * @author Azharuddin Khan
	 * Method name: turnturnOnImplicitWaits
	 * type of method: Overloaded method
	 * @param time
	 * @description:This method works for turn on implicit wait while passing time(String) as parameter which will convert 
	 *              this time parameter from string to Long.
	 *              Example:turnturnOnImplicitWaits("30")
	 *Passing time as String value and converting/parsing into long value.
	 */
	public static void turnturnOnImplicitWaits(String time)
	{
		long time_=Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(time_,TimeUnit.SECONDS);
	}
//################################################################################################################################################
	/**
	 * @author Azharuddin khan
	 * Method name:turnOffImplicitWaits
	 * @description:Turning off Implicit wait feature and make default time as 0.
	 * 
	 */
	public static void turnOffImplicitWaits()
	{
		driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
	}
//################################################################################################################################################
	/**
	 * @author Azharuddin Khan
	 * Method name:turnOffImplicitWaits 
	 * @param time
	 * @description:This method works for turn off implicit wait while passing time(String) as parameter which will convert 
	 *              this time parameter from string to Long. 
	 *              Example:turnOffImplicitWaits("0")
	 * Passing "0" as parameter and convert this to long value.             
	 */
	public static void turnOffImplicitWaits(String time)
	{
		long time_=Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(time_,TimeUnit.SECONDS);
	}

//##################################################################################################################################################
	/***
	 * @author SESA443020(Azharuddin Khan)
	 * Method Name: launchUrl
	 * @param:env,type
	 * @type: String,String
	 * @Description:This method is used to Launch URL based on parameter envrionement and Type(Home or Work)
	 *
	 */

	public static void launchUrl(String url)
	{
		
		try{

			driver.navigate().to(url);	
			//driver.get(URL.getProperty(url));
			test.log(LogStatus.INFO,driver.getCurrentUrl()+" Has been launched.");
			//turnOffImplicitWaits();
			Thread.sleep(10000);
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}

		finally
		{
			//turnOnImplicitWaits();
		}

	}
//############################################################################################################################################	
	/***
	 * @author SESA443020(Azharuddin Khan)
	 * Method Name: closeBrowser
	 * @param:N/A
	 * @Description:This method is used to close the current focused browser window
	 * 
	 */
	public static void closeBrowser()
	{   
		driver.close();
		test.log(LogStatus.INFO, "Browser has been closed.");
	}
//##############################################################################################################################################	
	/***
	 * @author SESA443020(Azharuddin Khan)
	 * Method Name: closeAllBrowser
	 * @param:N/A
	 * @Description:This method is used to close all browser instances
	 * 
	 */
	public static void closeAllBrowser()
	{   
		driver.quit();
		test.log(LogStatus.INFO, "Browser has been closed.");
	}
//#############################################################################################################################################
	
}
