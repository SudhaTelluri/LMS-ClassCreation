package com.StepDefinitions;

import org.testng.Assert;

import com.PageObjects.POM_DashboardPage;
import com.PageObjects.POM_LoginPage;
import com.Utilities.LoggerLoad;
import com.Utilities.TestContext;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDefinition {

	TestContext testcontext;
	POM_LoginPage loginpage;
	POM_DashboardPage dashboardpage;

	public Login_StepDefinition(TestContext testcontext) {

		this.testcontext = testcontext;

		loginpage = testcontext.pageobjectmanager.getLoginPage();
		dashboardpage = testcontext.pageobjectmanager.getDashboardPage();

	}

	@Given("Admin is in login Page")
	public void admin_is_in_login_page() {
		Assert.assertEquals(loginpage.getTitle(), "LMS");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		LoggerLoad.info("**************Admin is in login page**************");
	}

	@When("Admin enter valid credentials using {string}  and clicks login button")
	public void admin_enter_valid_credentials_using_and_clicks_login_button(String loginCredentials)
			throws InterruptedException {
		loginpage.loginWithValidCredentials(loginCredentials);
	}

	@Then("Admin should land on dashboard page \\( centre of the page will be empty , menu bar is present).")
	public void admin_should_land_on_dashboard_page_centre_of_the_page_will_be_empty_menu_bar_is_present() {
		Assert.assertEquals(dashboardpage.getDashboardHeader(), "LMS - Learning Management System");
	}

}
