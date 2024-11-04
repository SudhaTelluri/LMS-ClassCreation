package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Utilities.Constant;
import com.Utilities.ElementsUtil;

public class POM_DashboardPage {

	WebDriver driver;

	public POM_DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space(text()) = 'LMS - Learning Management System']")
	private WebElement getDashboardHeader;
	@FindBy(xpath="//span[text()='Class']") private WebElement classLink;
	@FindBy(id = "program")
	private WebElement programBtn;


	
	public String getDashboardHeader() {
		ElementsUtil.waitForElementVisibility(driver, getDashboardHeader, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return getDashboardHeader.getText();
	}
	
	public void getClickOnClassLink()
	{
		ElementsUtil.waitForElementVisibility(driver, classLink, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, classLink, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}
	public void getClickOnProgramLink()
	{
	ElementsUtil.waitForElementVisibility(driver, programBtn, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, programBtn, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}
	
	
}
