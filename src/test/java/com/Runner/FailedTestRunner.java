package com.Runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "@target/failed_scenarios.txt",
glue = { "com.StepDefinitions",
		"com.ApplicationHooks" },
monochrome=true,plugin= {"html:target/cucumber.html",
"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class FailedTestRunner extends AbstractTestNGCucumberTests{

	@Override
	@DataProvider(parallel= false)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
}

