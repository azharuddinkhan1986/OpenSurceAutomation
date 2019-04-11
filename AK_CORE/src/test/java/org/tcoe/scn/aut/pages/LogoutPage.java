package org.tcoe.scn.aut.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.utility.CommonUtility;

public class LogoutPage extends TestBase {
	//logout from the application//
	WebElement logoutBtn;
	WebElement welcome;
	public void logoutFromApplication() throws InterruptedException {
		welcome=driver.findElement(By.xpath("//a[@id=\"welcome\"]"));
		CommonUtility.click(welcome, "click on the welcomeicon");
		logoutBtn=driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a"));
		explicitWait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
		CommonUtility.click(logoutBtn, "logout from the application ");
		Thread.sleep(5000);
	}

}

