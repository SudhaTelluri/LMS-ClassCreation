package com.DriverFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.Utilities.Constant;
import com.Utilities.LoggerLoad;

public class BaseTest {
	private static final String headlesschrome = "headlesschrome";
	private static final String chrome = "chrome";
	private static final String firefox = "firefox";
	private static final String headlessfirefox = "headlessfirefox";
	private static final String headlessedge = "headlessedge";
	private static final String edge = "edge";
	private static final String safari = "safari";

	public static ThreadLocal<WebDriver> thdriver = new ThreadLocal<>();

	// we keep our WebDriver reference static to make it thread-safe

	// This method is use to initialize thread driver for a given browser and thread
	// local will help in parallel execution

	public WebDriver initializeDriver(String browser) {

		browser = browser.toLowerCase();
		try {
			switch (browser) {
			case headlesschrome:
				LoggerLoad.info("Initializing Chrome Browser in headless mode...");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("headless");
				thdriver.set(new ChromeDriver(chromeOptions));
				break;
			case chrome:
				LoggerLoad.info("Initializing chrome Browser...");
				chromeOptions = new ChromeOptions();//added
				chromeOptions.addArguments("--no-sandbox");//added bcoz while running the multiple invalid data in single feature fail getting error to avoid this we added this in cucumber
				chromeOptions.addArguments("--disable-dev-shm-usage");//added for the above reason
				thdriver.set(new ChromeDriver());
				break;
			case firefox:
				LoggerLoad.info("Initializing firefox Browser...");
				thdriver.set(new FirefoxDriver());
				break;
			case headlessfirefox:
				LoggerLoad.info("Initializing Firefox Browser in headless mode....");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("headless");

				thdriver.set(new FirefoxDriver(firefoxOptions));
				break;
			case edge:
				LoggerLoad.info("Initializing edge Browser...");
				thdriver.set(new EdgeDriver());
				break;
			case headlessedge:
				LoggerLoad.info("Initializing edge Browser in headless mode....");
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless");
				thdriver.set(new EdgeDriver(edgeOptions));
				break;
			case safari:
				LoggerLoad.info("Initializing safari Browser...");
				thdriver.set(new SafariDriver());
				break;
			default:
				LoggerLoad.info("Initializing Chrome Browser...");
				thdriver.set(new ChromeDriver());
				break;
			}
		} catch (Exception e) {
			LoggerLoad.error("Error while Initializing browser. Error is " + e.getMessage());
		}
		thdriver.get().manage().window().maximize();
		thdriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.IMPLICIT_PAGE_LOAD));
		return thdriver.get();
	}

	public static synchronized WebDriver getDriver() {
		if (thdriver == null) {
			LoggerLoad.error("driver value is null, lets wait a second.");
		}
		WebDriver dri = thdriver.get();
		LoggerLoad.error("driver value is :-" + dri);
		return dri;
	}

}
