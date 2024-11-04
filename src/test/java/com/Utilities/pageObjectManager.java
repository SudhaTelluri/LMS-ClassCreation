package com.Utilities;

import org.openqa.selenium.WebDriver;

import com.PageObjects.POM_ClassDetailsPage;
import com.PageObjects.POM_DashboardPage;
import com.PageObjects.POM_LoginPage;
import com.PageObjects.POM_ManageClassPage;

public class pageObjectManager {

	WebDriver driver;

	private POM_LoginPage loginpage;
	private POM_DashboardPage dashboard;
	private POM_ManageClassPage manageClass;
	private POM_ClassDetailsPage classDetails;
		

	public pageObjectManager(WebDriver driver) {

		this.driver = driver;
	}

	public POM_LoginPage getLoginPage() {
		if (loginpage == null) {

			loginpage = new POM_LoginPage(driver);
		}
		return loginpage;

	}

	public POM_DashboardPage getDashboardPage() {
		if (dashboard == null) {
			dashboard = new POM_DashboardPage(driver);
		}
		return dashboard;

	}

	public POM_ManageClassPage getManageClassPage() {
		if (manageClass == null) {
			manageClass = new POM_ManageClassPage(driver);
		}
		return manageClass;

	}
	public POM_ClassDetailsPage getClassDetailsPage() {
		if (classDetails == null) {
			classDetails = new POM_ClassDetailsPage(driver);
		}
		return classDetails;
	}

}
