package com.Utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsUtil {

	
	public static WebElement waitForElementVisibility(WebDriver driver, WebElement element, long durationInSeconds) {
		// explicit wait
		WebElement webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			LoggerLoad.error("waitForElementVisibility()::The element " + element.toString()
					+ " may not be visible. Exception is: " + e.getMessage());
		}
		return webElement;
	}

	public static List<WebElement> waitForElementsVisibility(WebDriver driver, List<WebElement> elememt, long durationInSeconds) {
		// explicit wait
		List<WebElement> webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.visibilityOfAllElements(elememt));
		} catch (Exception e) {
			LoggerLoad.error("waitForElementVisibility()::The element " + elememt.toString()
					+ " may not be visible. Exception is: " + e.getMessage());
		}
		return webElement;
	}


	public static void ScrolltoElementandClick(WebDriver driver, WebElement element, long durationInSeconds) {

		WebElement webElement = null;
		try {
			webElement = waitForElementVisibility(driver, element, durationInSeconds);
			Actions action = new Actions(driver);
			action.moveToElement(webElement).click().perform();
		} catch (Exception e) {
			LoggerLoad.error("actionScrolltoElement::The element " + element.toString()
					+ " may not scrolled to element. Exception is: " + e.getMessage());
		}

	}

	public static WebElement waitForElementClickablity(WebDriver driver, WebElement element, long durationInSeconds) {

		WebElement webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			LoggerLoad.error("waitForElementClickablity()::The element " + element.toString()
					+ " may not be clickable. Exception is: " + e.getMessage());
		}
		return webElement;
	}

	public static void typeInputIntoElement(WebDriver driver, WebElement element, String textToBeTyped,
			long durationInSeconds) {
		WebElement webElement = null;
		try {
			webElement = waitForElementClickablity(driver, element, durationInSeconds);
			webElement.clear();
			webElement.click();
			webElement.sendKeys(textToBeTyped);
		} catch (Exception e) {
			LoggerLoad.error("typeInputIntoElement()::Not able to send text in " + element.toString()
					+ ". Exception is: " + e.getMessage());
		}
	}

	public static String getTextFromElement(WebDriver driver, WebElement element, long durationInSeconds) {
	    String text = null; 
	    try {
	        
	        ElementsUtil.waitForElementVisibility(driver, element, durationInSeconds);
	        
	        if (element.isDisplayed()) {
	            text = element.getText(); 
	        } else {
	            LoggerLoad.error("getTextFromElement()::Element is not visible: " + element.toString());
	        }
	    } catch (Exception e) {
	        LoggerLoad.error("getTextFromElement()::Not able to retrieve text from " + element.toString()
	                + ". Exception is: " + e.getMessage());
	    }
	    return text;
	}

	public static boolean isTextBox(WebDriver driver, WebElement element) {

		String tagName = element.getTagName().toLowerCase();
		String type = element.getAttribute("type");
		return tagName.equals("input") && (type.equalsIgnoreCase("text") || (type.equalsIgnoreCase("number")));

	}

	public static boolean isradioButton(WebDriver driver, WebElement element) {

		String tagName = element.getTagName().toLowerCase();
		String type = element.getAttribute("type");
		return tagName.equals("input") && (type.equalsIgnoreCase("radio"));

	}

	public static void implicitPageWait(WebDriver driver) {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.IMPLICIT_PAGE_LOAD));//10sec

     }
	public static void explicitElementWait(WebDriver driver) {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.EXPLICIT_ELEMENT_WAIT_TIME));//10sec

     }
	public static void waitforElementLoad() throws InterruptedException {
		Thread.sleep(1000);
	}
	public static void waitForPageLoad(WebDriver driver) {

		String pageLoadStatus =null;
		do {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		pageLoadStatus = (String)js.executeScript("return document.readyState");

	    } while ( !pageLoadStatus.equals("complete") );

	          System.out.println("Page Loaded.");
	}
	
	public static boolean areElementsDisplayed(WebElement... elements) {
	    for (WebElement element : elements) {
	        if (!element.isDisplayed()) {
	            System.out.println("Element not displayed: " + element);
	            return false;
	        }
	    }
	    return true;
	}
	
	public static Map<String, List<String>> getClassDetailsBeforeAndAfterSorting(WebDriver driver, String sortBy) throws InterruptedException {
	    // Define the locator for the sort icon based on the sorting criteria
	    String sortIconXpath;
	    switch (sortBy.toLowerCase()) {
	        case "batch name":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'batchName')]//i"; // Adjust with actual locator
	            break;
	        case "class topic":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'classTopic')]//i"; // Adjust with actual locator
	            break;
	        case "class description":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'classDescription')]//i"; // Adjust with actual locator
	            break;
	        case "status":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'classStatus')]//i"; // Adjust with actual locator
	            break;
	        case "class date":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'classDate')]//i"; // Adjust with actual locator
	            break;
	        case "staff name":
	            sortIconXpath = "//th[contains(@psortablecolumn, 'classStaffName')]//i"; // Adjust with actual locator
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid sort criteria: " + sortBy);
	    }

	    List<WebElement> headerElements = driver.findElements(By.xpath("//table/thead/tr/th[position()!=1 and position()!=last()]"));
	    int columnIndex = -1; // Start with -1 to detect if column not found

	    for (int i = 0; i < headerElements.size(); i++) {
	        if (headerElements.get(i).getText().trim().equalsIgnoreCase(sortBy)) {
	            columnIndex = i;
	            break;
	        }
	    }

	    if (columnIndex == -1) {
	        throw new IllegalArgumentException("Column not found: " + sortBy);
	    }

	    Thread.sleep(4000);

	    // Retrieve data before sorting, excluding the first and last <td> elements in each row
	    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
	    List<String> dataBeforeSort = new ArrayList<>();

	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.xpath("td[position() > 1 and position() < last()]"));
	        if (cells.size() > columnIndex) { // Ensure index is within bounds
	            dataBeforeSort.add(cells.get(columnIndex).getText().trim());
	        }
	    }

	    // Click the sort icon to trigger sorting
	    driver.findElement(By.xpath(sortIconXpath)).click();
	    
	    // Wait for sorting to complete
	    ElementsUtil.waitForPageLoad(driver);

	    // Retrieve data after sorting
	    List<String> dataAfterSort = new ArrayList<>();
	    rows = driver.findElements(By.xpath("//table/tbody/tr")); // Re-fetch rows after sorting

	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.xpath("td[position() > 1 and position() < last()]"));
	        if (cells.size() > columnIndex) { // Ensure index is within bounds
	            dataAfterSort.add(cells.get(columnIndex).getText().trim());
	        }
	    }

	    // Store both lists in a map and return
	    Map<String, List<String>> sortingData = new HashMap<>();
	    sortingData.put("beforeSort", dataBeforeSort);
	    sortingData.put("afterSort", dataAfterSort);

	    // Print data before and after sorting
	    System.out.println("**********Data before sorting******" + dataBeforeSort);
	    System.out.println("**********Data after sorting**********" + dataAfterSort);

	    return sortingData;
	}
	
	public static void clickElement(WebDriver driver, WebElement element, long durationInSeconds) {
	    try {
	        WebElement webElement = waitForElementClickablity(driver, element, durationInSeconds);
	        webElement.click();
	    } catch (Exception e) {
	        LoggerLoad.error("clickElement()::Not able to click on " + element.toString()
	                + ". Exception is: " + e.getMessage());
	    }
	}
	


	   }

