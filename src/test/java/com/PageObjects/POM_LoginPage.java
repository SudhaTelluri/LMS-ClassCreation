package com.PageObjects;

import java.util.Map;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ApplicationHooks.AppHooks;
import com.Utilities.Constant;
import com.Utilities.CredentialResouceBundle;
import com.Utilities.ElementsUtil;

public class POM_LoginPage {

	WebDriver driver;
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");

	public POM_LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	private WebElement username;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(id = "login")
	private WebElement login;
	

	public String getTitle() {
		String Title = driver.getTitle();
		return Title;
	}

	public void loginWithValidCredentials(String testCaseName) {
		Map<String, String> testData = AppHooks.testCaseDataMap.get(testCaseName);
		ElementsUtil.typeInputIntoElement(driver, username, testData.get("Username "),
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.typeInputIntoElement(driver, password, testData.get("Password "),
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, login, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}
	
	public void getLogin() throws InterruptedException {

		ElementsUtil.typeInputIntoElement(driver, username, CredentialResouceBundle.getUsername().trim(),
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.typeInputIntoElement(driver, password,CredentialResouceBundle.getPassword().trim() ,
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, login, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}
	


}
