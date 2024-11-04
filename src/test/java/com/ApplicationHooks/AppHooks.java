package com.ApplicationHooks;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.DriverFactory.BaseTest;
import com.Utilities.Constant;
import com.Utilities.CredentialResouceBundle;
import com.Utilities.ExcelReader;
import com.Utilities.LoggerLoad;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class AppHooks {

	private WebDriver driver;
	private BaseTest basefactory;
	public static Map<String, Map<String, String>> testCaseDataMap = new HashMap<>();
	public static Map<String, Map<String, String>> classTestDataMap = new HashMap<>();
	public static Map<String, Map<String, String>> programTestDataMap = new HashMap<>();
	
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");

	@Before
	public void setUp() {

		//String browseName = com.Utilities.CredentialResouceBundle.getBrowser();
		String browseName = rb.getString("url");
		System.out.println(browseName);
		basefactory = new BaseTest();
		driver = basefactory.initializeDriver(browseName);
		LoggerLoad.info("Hook:-Initializing driver for browser :" + browseName);
		driver.get(CredentialResouceBundle.getURL());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.IMPLICIT_PAGE_LOAD));
		LoggerLoad.info("Hook:-home page url opened successfully");
		
	}

	@AfterStep
	public void getScreenshot(Scenario scenario) {
		if (driver != null && scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
	

	@After
	public void tearDown(Scenario scenario) {
		LoggerLoad.info("Closing driver from hook's teardown method...");
		if (driver != null)
			driver.quit();
	}

	@BeforeAll
	public static void loadTestCases() {
		try {
			// Load Login Data
			ExcelReader excelReader = new ExcelReader(
					"src/test/resources/TestData/Team08-NinjaAutomators-TestData.xlsx", "LoginData");
			testCaseDataMap = excelReader.readAllTestCases(); // Load all test cases into a global map
			System.out.println("Login test cases loaded successfully!");

			// Load Class Test Data
			ExcelReader classTestDataReader = new ExcelReader(
					"src/test/resources/TestData/Team08-NinjaAutomators-TestData.xlsx", "Class");
			classTestDataMap = classTestDataReader.readAllTestCases(); // Load class test cases
			System.out.println("Class test cases loaded successfully!");

			
			ExcelReader programTestDataReader = new ExcelReader(
					"src/test/resources/TestData/Team08-NinjaAutomators-TestData.xlsx", "Program");
			programTestDataMap = programTestDataReader.readAllTestCases(); // Load class test cases
			System.out.println("Program test cases loaded successfully!");
			
		} catch (Exception e) {
			System.err.println("Error loading test cases: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
