package com.Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = 
"@all",features="src/test/resources/Features",



glue = { "com.StepDefinitions","com.ApplicationHooks" }, monochrome = true, dryRun = false, 
plugin = { "pretty",
				"html:target/HtmlReport/htmlreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"rerun:target/failed_scenarios.txt" })

public class TestRunner extends AbstractTestNGCucumberTests {

  
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
