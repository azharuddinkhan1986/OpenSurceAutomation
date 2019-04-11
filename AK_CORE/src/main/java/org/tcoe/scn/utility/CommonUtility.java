package org.tcoe.scn.utility;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tcoe.scn.base.TestBase;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;


public class CommonUtility extends TestBase{
	
	public static WebDriverWait wait;
	public static String screenshot="";
	public static void acceptAlert()
	{
		try{
		Alert alert=driver.switchTo().alert();
		alert.accept();
		}
		catch(Exception ex)
		{
			test.log(LogStatus.FAIL, ex.getMessage());
		}
	}
	
	public static void switchToWindowOrTab()
	{
		//get windowhandles
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		//switch to new opened window and get title of the page-it should be login page
	    driver.switchTo().window(tabs2.get(1));
	}
	
	public static void TabHandles() {
	   // String currentWindowHandle = driver.getWindowHandle();
	    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
	   /* //Get the list of all window handles
	    ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

	    for (String window:windowHandles){

	        //if it contains the current window we want to eliminate that from switchTo();
	        if (window != currentWindowHandle){
	            //Now switchTo new Tab.
	            driver.switchTo().window(window);
	            //Do whatever you want to do here.

	            //Close the newly opened tab
	            driver.close();
	        }
	    }*/
	}

	// highLightElement Method
			public static void highLightElement(WebDriver driver,WebElement element)
			{
			JavascriptExecutor js=(JavascriptExecutor)driver; 
			 
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
			 
			try 
			{
			Thread.sleep(2000);
			} 
			catch (InterruptedException e) {
			 
			System.out.println(e.getMessage());
			} 
			 
			 js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
			 
			}
			
			
			public static void selectDropdown(WebElement element,String Data,String desc)
			{
				try{
					//CommonUtility.highLightElement(driver, element);
					Select DropDown=new Select(element);
					DropDown.selectByVisibleText(Data);
					test.log(LogStatus.INFO,desc+" as "+Data);
				}
				
				catch(Exception ex)
				{
					test.log(LogStatus.ERROR, ex.getMessage());
				}
			}
			
			public static void selectDropdownByValue(WebElement element,String Data,String desc)
			{
				try{
					//CommonUtility.highLightElement(driver, element);
					Select DropDown=new Select(element);
					DropDown.selectByValue(Data);
					test.log(LogStatus.INFO,desc+" as "+Data);
				}
				
				catch(Exception ex)
				{
					test.log(LogStatus.ERROR, ex.getMessage());
				}
			}
			
			public static void selectDropdownWithoutHighlight(WebElement element,String Data,String desc)
			{
				try{
					Select DropDown=new Select(element);
					DropDown.selectByVisibleText(Data);
					test.log(LogStatus.INFO,desc+" as "+Data);
				}
				
				catch(Exception ex)
				{
					test.log(LogStatus.ERROR, ex.getMessage());
				
				}
			}
			
	
	public static void waitForElementToBePresent(WebDriver driver,long time,long Polltime,String Locator,String locatorType)
	{
		System.out.println(Locator);
		   WebDriverWait wait=new WebDriverWait(driver,time);
		   try{
			   if(locatorType.equals("CSS")){
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Locator)));
		   wait.pollingEvery(Polltime,TimeUnit.MILLISECONDS);
			   }
			   if(locatorType.equals("Xpath")){
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Locator)));
				   wait.pollingEvery(Polltime,TimeUnit.MILLISECONDS);
					   }
			   if(locatorType.equals("ID")){
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Locator)));
				   wait.pollingEvery(Polltime,TimeUnit.MILLISECONDS);
					   }
			   if(locatorType.equals("Name")){
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.name(Locator)));
				   wait.pollingEvery(Polltime,TimeUnit.MILLISECONDS);
					   }
			   if(locatorType.equals("ClassName")){
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.className(Locator)));
				   wait.pollingEvery(Polltime,TimeUnit.MILLISECONDS);
					   }
		   }
		   catch(Exception ex)
		   {
			   ex.getMessage();
		   }
	}
	
	
	
	//Select from list dropdown
	public void selectFromList(String option) {
	       // Open the dropdown so the options are visible
	        driver.findElement(By.className("dropdown-menu")).click();
	        // Get all of the options
	        List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));
	        // Loop through the options and select the one that matches
	        for (WebElement opt : options) {
	            if (opt.getText().equals(option)) {
	                opt.click();
	                return;
	            }
	        }
	        throw new NoSuchElementException("Can't find " + option + " in dropdown");
	}

	
	public static void navigateTo(String Url)
	{
		try{
		driver.navigate().to(Url);
		test.log(LogStatus.INFO,"Navigate to Url "+Url);
		String x=addScreenshot();
		test.log(LogStatus.PASS,test.addScreenCapture(x));
		}
		catch(Exception ex)
		{
			test.log(LogStatus.INFO,ex.getMessage());
		}
	}
	
	public static void clearText(WebElement element)
	{
		try{
			element.clear();
		}
		catch(Exception ex)
		{
			test.log(LogStatus.INFO,ex.getMessage());
			String x=addScreenshot();
			test.log(LogStatus.FAIL,test.addScreenCapture(x));
		}
		
	}
	
	public static void verifyTitle(String Title )
	{
		try{
			String actual=driver.getTitle();
			Assert.assertEquals(Title,actual);
			test.log(LogStatus.PASS, "Title of the page is "+actual);
		}
		catch(Throwable t)
		{
			//Add Error report to Extent Report
			test.log(LogStatus.FAIL, "Title of the page is not correct.");
			String x=addScreenshot();
			test.log(LogStatus.FAIL,"Image-1",test.addScreenCapture(x));
			ErrorUtil.addVerificationFailure(t);
			//report to xls file
			//this return will make test case stops
			//return;
		}

	}
	
	
	
	public static void selectListBox(WebElement element,String data,String desc)
	{
		try{
			CommonUtility.highLightElement(driver, element);
			Select listBox=new Select(element);	
			if(data.contains(",")){
				ArrayList<String> items = new  ArrayList<String>(Arrays.asList(data.split(",")));
				for (String temp : items) {
					listBox.selectByVisibleText(temp);
				}
			}
			else
				listBox.selectByVisibleText(data);		
			test.log(LogStatus.INFO,desc+" as "+data);
		}

		catch(Exception ex)
		{
			test.log(LogStatus.ERROR, ex.getMessage());
			
		}
	}

	
	
	public static String getListBoxValues(WebElement element) {
		String listString=" ";
		Select select = new Select(element);
		ArrayList<String>values=new ArrayList<String>();
		List<WebElement> allOptions=select.getOptions();
		for (int i=0; i<allOptions.size();i++){
			//System.out.println("ENter loop");
			values.add(allOptions.get(i).getText());
			//System.out.println("ListBox value: "+values);
		}
		//convert List to csv
		if(values.size()>1){
			listString = String.join(", ", values);
		}
		else if (values.size()==1){
			listString = String.valueOf(values);
		}
		else if(values.size()==0){
			listString=" ";
		}
		return listString;  
	}
	
	public void waitBeforeAction(long time) throws InterruptedException
	{
		Thread.sleep(time);
	}
	
	public static void waitElementForInvisible(WebDriver driver,long time,long Polltime,String locator,String locatorType)
	{
		System.out.println(locator);
		WebDriverWait wait=new WebDriverWait(driver,time);
		try{
		if(locatorType.equals("CSS")){
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
		  wait.pollingEvery(Polltime, TimeUnit.MILLISECONDS);
		}
		if(locatorType.equals("Xpath")){
			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
			  wait.pollingEvery(Polltime, TimeUnit.MILLISECONDS);
			}
		if(locatorType.equals("ID")){
			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
			  wait.pollingEvery(Polltime, TimeUnit.MILLISECONDS);
			}
		if(locatorType.equals("Name")){
			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locator)));
			  wait.pollingEvery(Polltime, TimeUnit.MILLISECONDS);
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
	}
	
	//Enter Input in Textbox,TextArea
			public static void input(WebElement element,String Data,String desc)
			{
				try{
				CommonUtility.highLightElement(driver, element);	
				element.sendKeys(Data);
				byte bytes[] = Data.getBytes("UTF-8"); 
				String value = new String(bytes, "UTF-8");
				System.out.println(value);
				test.log(LogStatus.INFO,desc+ "as "+value);
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL,e.getMessage());
				}
			  
			}
			
			public static void inputWithoutHiglight(WebElement element,String Data,String desc)
			{
				try{	
				element.sendKeys(Data);
				byte bytes[] = Data.getBytes("UTF-8"); 
				String value = new String(bytes, "UTF-8");
				System.out.println(value);
				test.log(LogStatus.INFO,desc+ "as "+value);
				}
				catch(Exception e)
				{
					test.log(LogStatus.INFO,e.getMessage());
				}
			  
			}
			//Click on link,button
			public static void click(WebElement element,String desc)
			{  
				try{
				//CommonUtility.highLightElement(driver, element);	
				element.click();
				test.log(LogStatus.INFO,desc);
			   }
			catch(Exception ex)
			{
				test.log(LogStatus.FAIL,ex.getMessage());
			}
			}
			
			//Click on link,button
			public void doubleClick(WebElement element,String desc)
			{  
				try{
				act=new Actions(driver);
				act.moveToElement(element).doubleClick().build().perform();
				test.log(LogStatus.INFO,desc);
			   }
			catch(Exception ex)
			{
				test.log(LogStatus.INFO,ex.getMessage());
			}
			}
			
			 //Click on link,button
			public static void highlightAndClick(WebElement element,String desc)
			{  
				try{
				CommonUtility.highLightElement(driver, element);	
				element.click();
				test.log(LogStatus.INFO,desc);
			   }
			catch(Exception ex)
			{
				test.log(LogStatus.INFO,ex.getMessage());
			}
			}
			
			
			public static void errorMessage(WebElement element)
			{
				try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				Boolean is_valid=(Boolean)js.executeScript("return arguments[0].checkValidity()",element);
				String message=(String)js.executeScript("return arguments[0].validationMessage;",element);
				test.log(LogStatus.PASS, message);
				System.out.println(is_valid);
				}
				
				catch(Exception ex)
				{
					test.log(LogStatus.INFO,"Error Message is not displaying.");
				}
			}
			public static String htmlErrorMessage(WebElement element)
			{
				String message="";
				try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				Boolean is_valid=(Boolean)js.executeScript("return arguments[0].checkValidity()",element);
				message=(String)js.executeScript("return arguments[0].validationMessage;",element);
				test.log(LogStatus.PASS, message);
				System.out.println(is_valid);
				String screenshot=addScreen();
				test.log(LogStatus.PASS,test.addScreenCapture(screenshot));
				}
				
				catch(Exception ex)
				{
					test.log(LogStatus.FAIL,"Error Message is not displaying.");
					String screenshot=addScreen();
					test.log(LogStatus.FAIL,test.addScreenCapture(screenshot));
				}
				return message;
			}
			
			
			public static String getValue(WebElement element)
			{  String value="";
				try{
					value=element.getAttribute("value");
				}
				catch(Exception ex)
				{
					test.log(LogStatus.INFO,ex.getMessage());
					
				}
				return value;
				
			}
			
			
			public static void tabAndSpaceOnElement(WebElement element,String desc)
			{
				try{
				//CommonUtility.highLightElement(driver, element);	
				element.sendKeys(Keys.TAB);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("document.getElementById('id').focus();");
				element.sendKeys(Keys.SPACE);
				test.log(LogStatus.INFO,desc);
				}
				catch(Exception ex)
				{
					test.log(LogStatus.ERROR,ex.getMessage());
					
					
				}
			}
			
			
			public static String getDefaultvalue(WebElement element) {
				   Select select = new Select(element);
				   WebElement value = select.getFirstSelectedOption();
				   return value.getText();  
				}
			
			
			public static String addScreen()
			{  String encodedBase64 = null;
			FileInputStream fileInputStreamReader = null;
				try {
				      Toolkit tool = Toolkit.getDefaultToolkit();
				      Dimension d = tool.getScreenSize();
				      Rectangle rect = new Rectangle(d);
				      Robot robot = new Robot();
				      Thread.sleep(2000);
				     // File f = new File("screenshot.png");
				      File f=new File(System.getProperty("user.dir")+"\\Screenshots\\screenshot.png");
				      BufferedImage img = robot.createScreenCapture(rect);
				      ImageIO.write(img,"png",f);
				      fileInputStreamReader = new FileInputStream(f);
				        byte[] bytes = new byte[(int)f.length()];
				        fileInputStreamReader.read(bytes);
				        encodedBase64 = new String(Base64.encodeBase64(bytes));
				      tool.beep();
				    } catch(Exception e){
				      e.printStackTrace();
				    }
				  
				 return "data:image/png;base64,"+encodedBase64;
				
			}
			

			//Add screenshot method is used for getting screenshot using base64
			public static String addScreenshot() {
			    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    String encodedBase64 = null;
			    FileInputStream fileInputStreamReader = null;
			    try {
			        fileInputStreamReader = new FileInputStream(scrFile);
			        byte[] bytes = new byte[(int)scrFile.length()];
			        fileInputStreamReader.read(bytes);
			        encodedBase64 = new String(Base64.encodeBase64(bytes));
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    return "data:image/png;base64,"+encodedBase64;
			}
			
			
			/***
			 * Method Name: IsWebElementDisplayed
			 * 
			 * @param: WebElement
			 *             and Description
			 * @return: boolean
			 * @Description:If the WebElement passed to the method is displayed on
			 *                 Webpage, the method will return true and the description
			 *                 passed to the method will be captured in the report. If
			 *                 the WebElement is not displayed on page, the method will
			 *                 return false.
			 * @author SESA459145(Shyni Prasanna)
			 */
			public static boolean isWebElementDisplayed(WebElement element, String description) {
				try {
					if (element.isDisplayed()) {
						test.log(LogStatus.PASS, description);
					}
				}

				catch (Exception ex) {
					String x = addScreenshot();
					test.log(LogStatus.FAIL, test.addScreenCapture(x));
					test.log(LogStatus.FAIL, "Expected element is not displayed");
					test.log(LogStatus.FAIL, ex.getMessage());
					return false;
				}
				return true;

			}
			
			public static void verifyLandingPage(WebElement element, String message)
			{
				 try{
						if(element.isDisplayed())
						  {
							String x=addScreenshot();
							test.log(LogStatus.PASS,test.addScreenCapture(x));
							test.log(LogStatus.PASS,message);
						  }
					}
						catch(Exception ex)
						{
							String x=addScreenshot();
							test.log(LogStatus.FAIL,test.addScreenCapture(x));
							test.log(LogStatus.FAIL,"Landing page is not displayed");
							
						}
			}
			
			public static void getErrorMessage(WebElement element)
			{
				try{
				    test.log(LogStatus.PASS,element.getText());
				}
				catch(Exception ex)
				{
					String x=addScreenshot();
					test.log(LogStatus.FAIL,test.addScreenCapture(x));
					test.log(LogStatus.FAIL,"Error Message is not displayed");
				}
			}
			
			
			public static void getErrorMessage(WebElement element,String text)
			{
				try{
					if(element.isDisplayed()){
				    test.log(LogStatus.PASS,text);
				    String x=addScreenshot();
					test.log(LogStatus.PASS,test.addScreenCapture(x));
					}
				}
				catch(Exception ex)
				{
					String x=addScreenshot();
					test.log(LogStatus.FAIL,test.addScreenCapture(x));
				}
			}
			
			public static void scrollToBottom(int time)
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				for(int i=0;i<time;i++){
				jse.executeScript("window.scrollBy(0,250)", "");
				}
			}
			
			public static void scrollToUp(int time)
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				for(int i=0;i<time;i++){
					jse.executeScript("window.scrollBy(0,-250)", "");
				}
			}

		public static void scrollUptoElementIsVisible(WebElement element)
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true);",element);
		}
		
		public static void selectByValue(String xpath, String value)
		{
		 driver.findElement(By.xpath(xpath+"/option[.='"+value+"']")).click();
		}
		
		public static String getLinkAddress(WebElement locator){
			String linkAddress=locator.getAttribute("href");
			return linkAddress;
		} 
		
		public static boolean isElemenVisible(long time,WebElement element)
		{   try{
			wait=new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
		   }catch(Exception ex)
		      {
			     test.log(LogStatus.FAIL, ex.getMessage()); 
			     screenshot=addScreenshot();
		      }
			return true;
		}
		
		public static void waitFor(long time) throws InterruptedException
		{
			Thread.sleep(time*1000);
		}
		
		public static void pageDownByKey()
		{
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
		}
		
		public static String getMessage(WebElement ele)
		{   String message="";
			try{
				message=ele.getText();
				test.log(LogStatus.INFO, message);
				screenshot=CommonUtility.addScreenshot();
				test.log(LogStatus.INFO, test.addScreenCapture(screenshot));
		 }catch(Exception ex)
		{
			 test.log(LogStatus.FAIL, ex.getMessage());
			 return null;
		}
			return message;
		}
		
		
		public static String getFilePath(String filePath) throws InterruptedException
		{
			 File file = new File(filePath);
			 String path = file.getAbsolutePath();
			 Thread.sleep(3000);
			 return path;
		}
		public static void captureScreenShotOnPass() {
			String  screenshotName=addScreenshot();
			test.log(LogStatus.PASS, test.addScreenCapture(screenshotName));
		}
}


