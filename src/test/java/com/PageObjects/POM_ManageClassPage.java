package com.PageObjects;

import java.text.Collator;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ApplicationHooks.AppHooks;
import com.Utilities.Constant;
import com.Utilities.ElementsUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class POM_ManageClassPage {

	WebDriver driver;
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");

	public POM_ManageClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[normalize-space(text()) = 'Manage Class']")
	private WebElement ManageClassText;
	@FindBy(xpath = "//*[normalize-space(text())='LMS - Learning Management System']")
	private WebElement title;
	@FindBy(id = "filterGlobal")
	private WebElement searchbar;
	@FindBy(xpath = "//table//tr/th[not(.//input[@type='checkbox'])]")
	private List<WebElement> actualHeaderElements;
	@FindBy(xpath = "//button[@type='button']/../span[not(preceding-sibling::span)]")
	private WebElement paginator;
	@FindBy(xpath = "//button[@type='button' and contains(@class, 'p-paginator') and not(@disabled)]")
	private List<WebElement> paginatorControls;
	@FindBy(xpath = "//table//tr/th[not(.//input[@type='checkbox']) and position()!= last()]")
	private List<WebElement> headersExcludedEdit;
	@FindBy(xpath = "//i[contains(@class,'p-sortable-column-icon')]")
	private WebElement sortIcon;
	@FindBy(xpath = "//button[contains(@class,'p-button-danger') and not(contains(@class,'p-button-rounded'))]")
	private WebElement multiDeleteButton;
	@FindBy(xpath = "//button[text()='Add New Class']")
	private WebElement addNewClass;
//	@FindBy(xpath = "//table/tbody/tr//div[@class='action']/span/button[contains(@ng-reflect-icon,'pi-pencil')]")
	// private WebElement firstEditIcon;
	@FindBy(xpath = "//tr[1]//div[@class='action']/span/button[contains(@ng-reflect-icon,'pi-pencil')]")
	private WebElement firstEditIcon;
	@FindBy(xpath = "//button[contains(@class,'mat-focus-indicator')]/span[text()='Class']")
	private WebElement clickOnClassLink;
	@FindBy(xpath = "//div//tr[1]//div[@class='action']/span/button[contains(@ng-reflect-icon,'pi-trash')]")
	private WebElement firstDeleteIcon;
	@FindBy(xpath = "//*[text()='Confirm']")
	private WebElement confirm;
	@FindBy(xpath = "//*[text()='Yes']")
	private WebElement yes;
	@FindBy(xpath = "//*[text()='No']")
	private WebElement no;
	@FindBy(xpath = "//th[text()='Class Topic ']")
	private WebElement classtopic;
	@FindBy(xpath = "//div[contains(@class,'p-toast-message-text')]")
	private WebElement toastText;
	@FindBy(xpath = "//button[contains(@class,'p-dialog-header-close')]")
	private WebElement confirmClose;
//	@FindBy(xpath = "//div[@role='checkbox']")
//	private WebElement allCheckboxes ;
	@FindBy(xpath = "//div[@class='p-checkbox-box']")
	private WebElement MultiDeleteCheckbox;
//	@FindBy(xpath = "//div[@class='p-checkbox-box']")
//	private WebElement multiDeleteCheckbox ;
	@FindBy(xpath = "(//button[contains(@class,'p-button-danger')])[position()=1]")
	private WebElement multiDelete;
	@FindBy(xpath = "(//div[@role='checkbox'])[position()=2]")
	private WebElement singleCheckbox;
	@FindBy(xpath = "//*[text()='Classes Deleted']")
	private WebElement multiDeleteToastText;
	@FindBy(xpath = "//button[@ng-reflect-label='Yes']")
	private WebElement multiDeleteYes;
	@FindBy(xpath = "//table[@role='grid']//tbody/tr")
	private List<WebElement> rows;
	@FindBy(xpath = "//th[@psortablecolumn='classTopic']")
	private WebElement classtopicHeader;
	@FindBy(xpath = "//table//tr[1]/td[position() > 1 and position() < last()]")
	private WebElement firstRowData;
	@FindBy(xpath = "//tr[1]/td//button[contains(@icon,'pi-pencil')]")
	private WebElement editIcon;
	@FindBy(xpath = "//div[text()='Class Deleted']")
	private WebElement classDeletedSuccessMessage;

	@FindBy(xpath = "//button[contains(@class,'p-paginator-first')]")
	private WebElement firstPageButton;
	@FindBy(xpath = "//button[contains(@class,'p-paginator-last')]")
	private WebElement lastPageButton;
	@FindBy(xpath = "//button[contains(@class,'p-paginator-next')]")
	private WebElement nextPageButton;
	@FindBy(xpath = "//button[contains(@class,'p-paginator-prev')]")
	private WebElement prevPageButton;
	
	@FindBy(id="logout")
	private WebElement logout;
	@FindBy(xpath="//p[text()='Please login to LMS application']")
	private WebElement loginText;
	@FindBy(xpath="//button/span[text()='Batch']")
	private WebElement batchLink;
	@FindBy(xpath="//div[normalize-space(text()) = 'Manage Batch']")
	private WebElement manageBatchHeader;
	@FindBy(xpath="//div[contains(@class,'p-dialog-header')]/span[text()]")
	private WebElement classDetailsHeading;
	

	public boolean isManageClassHeadingPresent() {
		ElementsUtil.waitForElementVisibility(driver, ManageClassText, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return ManageClassText.isDisplayed();
	}

	public String getManageClassTitle() {
		ElementsUtil.waitForElementVisibility(driver, title, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return title.getText().trim();

	}

	public boolean isSearchBarDisplayed() {
		ElementsUtil.waitForElementVisibility(driver, searchbar, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return searchbar.isDisplayed();
	}

	public List<String> getactualColumnHeaderList() {
		List<String> actualHeaderList = actualHeaderElements.stream().map(WebElement::getText)
				.collect(Collectors.toList());
		return actualHeaderList;
	}

	public String getPaginatorText() {
		return ElementsUtil.getTextFromElement(driver, paginator, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public List<String> getButtons() {
		// Use Java streams to collect the text of all enabled pagination controls
		// (buttons)
		return paginatorControls.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
	}

	public boolean isSortIconPresentInHeaders() {
		boolean headersWithIcons = true;

		for (WebElement header : headersExcludedEdit) {

			WebElement sortIconInHeader = header
					.findElement(By.xpath(".//i[contains(@class,'p-sortable-column-icon')]"));

			if (!sortIconInHeader.isDisplayed()) {
				headersWithIcons = false;
				System.out.println("Sort icon is not displayed for header: " + header.getText());
			}
		}
		return headersWithIcons;
	}

	public boolean isMultiDeleteButtonVisible() {
		return multiDeleteButton.isDisplayed();
	}

	public boolean isPaginatorTextVisibleOrNot() {
		return paginator.isDisplayed();
	}

	public boolean isPaginationControlsDisiplayedOrNot() {
		for (WebElement paginationcontrol : paginatorControls) {
			if (!paginationcontrol.isDisplayed()) {
				return false;
			}
		}
		return true;
	}

	public void getClickOnAddNewClass() {
		ElementsUtil.ScrolltoElementandClick(driver, addNewClass, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public int getPaginationCount() {

		ElementsUtil.waitForElementVisibility(driver, paginator, 10);

		String paginationText = paginator.getText();
		System.out.println("Pagination text: " + paginationText);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Pagination text**** " + paginationText);
		Pattern pattern = Pattern.compile("of (\\d+) entries");
		Matcher matcher = pattern.matcher(paginationText);

		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1)); // Extract the total count Z
		} else {
			System.out.println("Pagination count not found in text.");
			return 0;
		}
	}

	public void getClickOnClass() {
		ElementsUtil.ScrolltoElementandClick(driver, clickOnClassLink, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public void getClickOnFirstEditIcon() {
		ElementsUtil.ScrolltoElementandClick(driver, firstEditIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);

	}

	public WebElement getSearchbar() {
		return searchbar;
	}

	public void getupdatedRowForGivenClasstopic(String classtopic) throws InterruptedException {
		searchbar.clear();
		ElementsUtil.typeInputIntoElement(driver, searchbar, classtopic, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		List<String> updatedRowData = new ArrayList<>();
		List<WebElement> rowCells = driver.findElements(By.xpath("//table//tr[1]/td")); // Adjust if row number changes

		for (WebElement cell : rowCells) {
			updatedRowData.add(cell.getText());
		}

		// Print the updated row data in a single line
		System.out.println("****Updated row for class topic**** " + updatedRowData);
	}

	public String getClassTopicForSearch() throws InterruptedException {

		Map<String, String> testData = AppHooks.classTestDataMap.get("ValidClassDetails");
		searchbar.clear();
		ElementsUtil.typeInputIntoElement(driver, searchbar, testData.get("Class Topic"),
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return testData.get("Class Topic");
	}

	public void getClickOnEditIcon(String classtopic) throws InterruptedException {

		Thread.sleep(3000);
		WebElement editButton = driver.findElement(By.xpath(
				"//table//tr[td[starts-with(text(),'" + classtopic + "')]]//button[contains(@icon,'pi-pencil')]"));
		// ElementsUtil.waitForElementVisibility(driver, editButton,
		// Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, editButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	/************************************************************/

//    public boolean getSortClassDetails(String sortBy) throws InterruptedException {
//        Map<String, List<String>> sortingData = ElementsUtil.getClassDetailsBeforeAndAfterSorting(driver, sortBy);
//        List<String> beforeSort = sortingData.get("beforeSort");
//        List<String> afterSort = sortingData.get("afterSort");
//        beforeSort.replaceAll(String::trim);
//        afterSort.replaceAll(String::trim);
//
//
//        // Check if the sorted data matches the data after sorting
//        List<String> sortedBeforeSort = new ArrayList<>(beforeSort);
//        Collections.sort(sortedBeforeSort); // Sort the data before sorting
//        
//        
//        System.out.println("****Data after sorting using Collections.sort()****" + sortedBeforeSort);
//        System.out.println("***Data from the application after sorting****" + afterSort);
//        
//        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Data after sorting using Collections.sort()****" + sortedBeforeSort);
//        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "***Data from the application after sorting****" + afterSort);
//
//        // Return whether the sorting is correct
//        return sortedBeforeSort.equals(afterSort);
//    }

	public boolean getSortClassDetails(String sortBy) throws InterruptedException {
		Map<String, List<String>> sortingData = ElementsUtil.getClassDetailsBeforeAndAfterSorting(driver, sortBy);
		List<String> beforeSort = sortingData.get("beforeSort");
		List<String> afterSort = sortingData.get("afterSort");

		// Normalize empty values and set consistent case
		beforeSort.replaceAll(value -> (value == null || value.trim().isEmpty()) ? "" : value.trim().toLowerCase());
		afterSort.replaceAll(value -> (value == null || value.trim().isEmpty()) ? "" : value.trim().toLowerCase());

		// Sort with Collator for locale-aware alphanumeric sorting
		Collator collator = Collator.getInstance(Locale.ENGLISH);
		collator.setStrength(Collator.PRIMARY); // case-insensitive sorting
		List<String> sortedBeforeSort = new ArrayList<>(beforeSort);
		sortedBeforeSort.sort(collator);

		// Debugging Output
		System.out.println("Expected Sorted Data (Java): " + sortedBeforeSort);
		System.out.println("Data from Application (After Sort): " + afterSort);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Expected Sorted Data (Java): " + sortedBeforeSort);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Data from Application (After Sort): " + afterSort);

		// Check for sorting equality
		boolean isSorted = sortedBeforeSort.equals(afterSort);

		// Enhanced mismatch logging if sorting fails
		if (!isSorted) {
			System.out.println("Mismatch detected for column: " + sortBy);
			ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, "Mismatch for column: " + sortBy);
			for (int i = 0; i < Math.min(sortedBeforeSort.size(), afterSort.size()); i++) {
				if (!sortedBeforeSort.get(i).equals(afterSort.get(i))) {
					System.out.println("Mismatch at index " + i + ": expected '" + sortedBeforeSort.get(i)
							+ "', but got '" + afterSort.get(i) + "'");
					ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Mismatch at index " + i + ": expected '"
							+ sortedBeforeSort.get(i) + "', but got '" + afterSort.get(i) + "'");
				}
			}
		}
		return isSorted;
	}

	/************
	 * Delete Class
	 * 
	 * @throws InterruptedException
	 ***********/

	public boolean getValidatePresenceOfFields() {
		ElementsUtil.waitForElementVisibility(driver, confirm, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return confirm.isDisplayed() && yes.isDisplayed() && no.isDisplayed();
	}

	public WebElement getDeleteIcon() {
		return firstDeleteIcon;
	}

	public void searchClassTopic(String classTopic) {
		searchbar.clear();
		ElementsUtil.typeInputIntoElement(driver, searchbar, classTopic, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.waitForPageLoad(driver);
		String deleteIconXpath = "//table/tbody/tr[contains(., '" + classTopic
				+ "')]//div[@class='action']/span/button[contains(@ng-reflect-icon, 'pi-trash')]";

		WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath));
		ElementsUtil.ScrolltoElementandClick(driver, deleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);

		ElementsUtil.waitForPageLoad(driver);
	}

	public void searchClassTopicAndDelete() throws InterruptedException {

		Map<String, String> testData = AppHooks.classTestDataMap.get("ValidClassDetails");
		searchbar.clear();
		ElementsUtil.typeInputIntoElement(driver, searchbar, testData.get("Class Topic"),
				Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.waitForPageLoad(driver);
		Thread.sleep(3000);
		String deleteIconXpath = "//table/tbody/tr[td[contains(text(), '" + testData.get("Class Topic")
				+ "')]]//div[@class='action']/span/button[contains(@ng-reflect-icon, 'pi-trash')]";
		WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath));
		ElementsUtil.ScrolltoElementandClick(driver, deleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.waitForPageLoad(driver);
	}

	public void clickOnDeleteIcon() {
		ElementsUtil.waitForElementVisibility(driver, firstDeleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, firstDeleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("******Delete icon clicked****");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "******Delete icon clicked****");
	}

	public void confirmDeletion() {
		ElementsUtil.ScrolltoElementandClick(driver, yes, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("******Deletion confirmed******");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "******Deletion confirmed****");
	}

	public boolean getReturnToastText() {
		ElementsUtil.waitForElementVisibility(driver, toastText, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return toastText.isDisplayed();
	}

	public void getClickOnNo() {
		ElementsUtil.ScrolltoElementandClick(driver, no, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

//    public boolean isDeletionAlertClosed() {
//    	
//    	System.out.println("****Attempting to click the 'No' button to close the deletion alert****");
//    	ElementsUtil.ScrolltoElementandClick(driver, no, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
//
//    	
//        String classTopic = rb.getString("classTopic");
//        System.out.println("****Searching for class topic**** " + classTopic);
//        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr[td[normalize-space(text())='" + classTopic + "']]"));
//        
//        // Return true if the class topic is still present, indicating it wasn't deleted
//        if (rows.isEmpty()) {
//            System.out.println("No rows found for class topic: " + classTopic + ". The topic may have been deleted.");
//        } else {
//            System.out.println("Found " + rows.size() + " row(s) for class topic: " + classTopic + ". It still exists in the table.");
//        }
//        return !rows.isEmpty();
//    
//    }
	public void getClickOnClose() {
		ElementsUtil.ScrolltoElementandClick(driver, confirmClose, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public boolean isDeletionAlertClosedUsingClose() {

		System.out.println("****Attempting to click the 'No' button to close the deletion alert****");
		ElementsUtil.ScrolltoElementandClick(driver, confirmClose, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		String classTopic = rb.getString("classTopic");
		System.out.println("****Searching for class topic**** " + classTopic);
		List<WebElement> rows = driver
				.findElements(By.xpath("//table/tbody/tr[td[normalize-space(text())='" + classTopic + "']]"));

		// Return true if the class topic is still present, indicating it wasn't deleted
		if (rows.isEmpty()) {
			System.out.println("No rows found for class topic: " + classTopic + ". The topic may have been deleted.");
		} else {
			System.out.println("Found " + rows.size() + " row(s) for class topic: " + classTopic
					+ ". It still exists in the table.");
		}
		return !rows.isEmpty();

	}

	/************** Multiple Class Delete ***********/

	public void getClickOnBatchNameCheckbox() {
		ElementsUtil.ScrolltoElementandClick(driver, MultiDeleteCheckbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public boolean getisMultiDeleteEnabled() {

		return multiDelete.isEnabled();
	}

	public void getClickOnSingleCheckbox() {
		ElementsUtil.ScrolltoElementandClick(driver, singleCheckbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public void getClickOnMultiDelete() {
		ElementsUtil.ScrolltoElementandClick(driver, multiDelete, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

//    public void getDeleteClassTopicUsingMultiDelete(String classTopic2) {
//        
//        searchbar.clear();
//        ElementsUtil.typeInputIntoElement(driver, searchbar, classTopic2, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
//        ElementsUtil.waitForPageLoad(driver);
//        
//        // Construct dynamic XPath for the checkbox based on the class topic
//        String checkboxXpath = "//table/tbody/tr[td[normalize-space(text()) = '" + classTopic2 + "']]//div[contains(@class, 'p-checkbox') and @role='checkbox']";
//
//            WebElement checkbox = driver.findElement(By.xpath(checkboxXpath));
//            System.out.println("****Attempting to select the checkbox for class topic: " + classTopic2 + "****");
//            ElementsUtil.ScrolltoElementandClick(driver, checkbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
//
//
//        // Construct dynamic XPath for the delete icon based on the class topic
//        String deleteIconXpath = "//table/tbody/tr[td[normalize-space(text()) = '" + classTopic2 + "']]//div[@class='action']/span/button[contains(@ng-reflect-icon, 'pi-trash')]";
//
//            WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath));
//           
//            ElementsUtil.ScrolltoElementandClick(driver, deleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
//            ElementsUtil.waitForPageLoad(driver);
//
//    }

	public void getClickOnMultiDeleteYes() {
		ElementsUtil.ScrolltoElementandClick(driver, multiDeleteYes, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public boolean getMultiDeleteToastText() {
		ElementsUtil.waitForElementVisibility(driver, multiDeleteToastText, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return multiDeleteToastText.isDisplayed();
	}

	public boolean getIsAdminLandOnManageClassPage() {
		return ManageClassText.isDisplayed();
	}

	public void getClickOnMultiDeleteCheckbox() {
		ElementsUtil.ScrolltoElementandClick(driver, MultiDeleteCheckbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public void getSearchOfClassTopic(String classtopic) {

		ElementsUtil.waitForElementVisibility(driver, searchbar, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		searchbar.clear();
		ElementsUtil.typeInputIntoElement(driver, searchbar, classtopic, Constant.EXPLICIT_ELEMENT_WAIT_TIME);

	}

	public void getClickOnCheckboxesFromSecondRow() {
		// Locate all checkboxes starting from the second row
		List<WebElement> checkboxes = driver.findElements(By.xpath("(//div[@role='checkbox'])[position()>1]"));

		// Loop through each checkbox and click
		for (WebElement checkbox : checkboxes) {
			ElementsUtil.ScrolltoElementandClick(driver, checkbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		}

		System.out.println("Clicked all checkboxes from the second row onwards.");
	}

	public void clickOnCorrespondingDeleteIcon(String classtopic) throws InterruptedException {

		String deleteIconXpath = "//table/tbody/tr[td[contains(text(), '" + classtopic
				+ "')]]//div[@class='action']/span/button[contains(@ng-reflect-icon, 'pi-trash')]";
		WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath));
		ElementsUtil.ScrolltoElementandClick(driver, deleteIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.waitForPageLoad(driver);
	}

	public void getClickOnMultiDeleteButton() {
		ElementsUtil.ScrolltoElementandClick(driver, multiDeleteButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	/**************** Search Box ****************/

	public boolean getIsSearchfound(String searchValue) {
		List<WebElement> searchResults = driver.findElements(By.xpath("(//tr)[2]"));

		// Check if each result contains the search value
		for (WebElement result : searchResults) {
			if (!result.getText().contains(searchValue)) {
				return false; // If any result does not match, return false
			}
		}
		return true; // All results matched
	}

	/****************************** Pagination ***********************************/

	public List<String> getCaptureColumnData() {

		List<String> classtopics = new ArrayList<>();

		for (WebElement row : rows) {

			String classtopic = row.findElement(By.xpath("./td[3]")).getText();
			classtopics.add(classtopic);

		}
		for (String topic : classtopics) {
			System.out.println("***********"+topic+"***********");
		}
		return classtopics;

	}
	
	public void getClickOnNextPageLink() {
		
		ElementsUtil.waitForElementVisibility(driver, nextPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, nextPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
		}
	
	public boolean isNextLinkEnabled() {
		return nextPageButton.isEnabled();
	}
	
public void getClickOnLastPageLink() {
		
		ElementsUtil.waitForElementVisibility(driver, lastPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, lastPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
		}


public boolean isLastPageDisplayedCorrectly() {
    // Step 1: Check if "Next" page link is disabled
    if (isNextLinkEnabled()) {
        return false; // If "Next" link is enabled, we are not on the last page
    }

        String footerText = paginator.getText(); // Expected format: "Showing 11 to 13 of 13 entries"
    
    // Extract the numbers from the footer text
    Pattern pattern = Pattern.compile("Showing (\\d+) to (\\d+) of (\\d+) entries");
    Matcher matcher = pattern.matcher(footerText);

    if (matcher.find()) {
        int startRecord = Integer.parseInt(matcher.group(1));
        int endRecord = Integer.parseInt(matcher.group(2));
        int totalRecords = Integer.parseInt(matcher.group(3));

        // Step 3: Confirm that the endRecord equals totalRecords, indicating last page
        return endRecord == totalRecords;
    }

    return false; // Return false if footer text format doesn't match
}
public void getClickOnPrevPageLink() {
	
	ElementsUtil.waitForElementVisibility(driver, prevPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, prevPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
	}

public boolean isPrevLinkEnabled() {
	return prevPageButton.isEnabled();
}

public void getClickOnFirstPageLink() {
	
	ElementsUtil.waitForElementVisibility(driver, firstPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, firstPageButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
	}

public boolean isFirstPageDisplayedCorrectly() {
   
    String footerText = paginator.getText();
    System.out.println("***footer text***"+footerText);
    ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "***footer text***"+footerText);
    return footerText.contains("Showing 1 to"); 
}

public void getClickOnLogout() {
	
	ElementsUtil.waitForElementVisibility(driver, logout, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, logout, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
	}

public boolean isLoginTextDisplayed() {
	
	ElementsUtil.waitForElementVisibility(driver, loginText, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, loginText, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	return loginText.isDisplayed();
	}

public void getClickOnBatchLink() {
	
	ElementsUtil.waitForElementVisibility(driver, batchLink, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	ElementsUtil.ScrolltoElementandClick(driver, batchLink, Constant.EXPLICIT_ELEMENT_WAIT_TIME);;
	}
public boolean getManageBatchIsDisplayed() {
	ElementsUtil.waitForElementVisibility(driver, manageBatchHeader, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	
	return manageBatchHeader.isDisplayed();

}
public boolean isClassDetailsHeadingDisplayed() {
	ElementsUtil.waitForElementVisibility(driver, classDetailsHeading, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	return classDetailsHeading.isDisplayed();
}



}
