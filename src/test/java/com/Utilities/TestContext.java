package com.Utilities;

import com.DriverFactory.BaseTest;
 

public class TestContext extends BaseTest {
 

public BaseTest basetest;
 
public pageObjectManager pageobjectmanager;

public TestContext()
{
	basetest = new BaseTest();
	pageobjectmanager = new pageObjectManager(basetest.getDriver());
}

public BaseTest getDrivermanager() {
	return basetest;
}

public pageObjectManager getPageObjectManager() {
	return pageobjectmanager;
}
}

