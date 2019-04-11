package org.tcoe.scn.aut.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tcoe.scn.base.TestBase;
import org.tcoe.scn.utility.CommonUtility;

public class LoginPage extends TestBase {
	WebElement usernameTextbox;
	WebElement passwordTextbox;
	WebElement loginBtn;
	public WebElement getUsernameTextbox() {
		usernameTextbox=driver.findElement(By.id(OR.getProperty("login.username.textbox")));
		return usernameTextbox;
	}
	public WebElement getPasswordTextbox() {
		passwordTextbox=driver.findElement(By.id(OR.getProperty("login.password.textbox")));
		return passwordTextbox;
	}
	public WebElement getLoginButton() {
		loginBtn=driver.findElement(By.id(OR.getProperty("login.button")));
		return loginBtn;
			
	}
	public void loginToApplication(String username,String password) {
		CommonUtility.input(getUsernameTextbox(), username, "Enter user name ");
		CommonUtility.input(getPasswordTextbox(), password, "Enter password ");		
		CommonUtility.click(getLoginButton(), "click on the login button ");
		CommonUtility.captureScreenShotOnPass();
		
		
	}

}
