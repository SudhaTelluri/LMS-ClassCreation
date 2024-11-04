package com.PageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ApplicationHooks.AppHooks;
import com.Utilities.Constant;
import com.Utilities.ElementsUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class POM_ClassDetailsPage {
	WebDriver driver;
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");
	public static String uniqueClassTopic;


	public POM_ClassDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'ng-trigger')]")
	private WebElement popUp;
	@FindBy(id = "saveClass")
	private WebElement saveButton;
	@FindBy(xpath = "//button[@id='saveClass']/preceding-sibling::button")
	private WebElement cancelButton;
	@FindBy(xpath = "//button[contains(@class,'p-dialog-header-close')]")
	private WebElement closeButton;
	@FindBy(xpath = "//input[@type='text']")
	private List<WebElement> inputfields;
	@FindBy(xpath = "//input[@placeholder='Select a Batch Name']")
	private WebElement batchDropdown;
	@FindBy(id = "classTopic")
	private WebElement classTopic;
	@FindBy(id = "classDescription")
	private WebElement classDescription;
	@FindBy(id = "icon")
	private WebElement datePicker;
	@FindBy(id = "classNo")
	private WebElement noOfClasses;
	@FindBy(xpath = "//input[@placeholder='Select a Staff Name']")
	private WebElement staffName;
	@FindBy(xpath = "//div[@class=\"p-radiobutton-box\"]")
	private WebElement statusActive;
	@FindBy(xpath = "//div[contains(@class,'p-toast-message-content')]")
	private WebElement classCreatedMsg;
	@FindBy(xpath = "//button[contains(@class,'p-datepicker-trigger')]")
	private WebElement dateIcon;
	@FindBy(xpath = "//table[contains(@class,'p-datepicker-calendar')]//td//span[text()='31']")
	private WebElement pickingDate;
	@FindBy(xpath = "//td[contains(@class,'ng-star-inserted')]/span[text()='14']")
	private WebElement pickingOneMoreDate;
	@FindBy(xpath = "//div[contains(@class,'p-dropdown-trigger')]")
	private WebElement staffNameArrow;
	@FindBy(xpath = "//div[text()='Class Created']")
	private WebElement toastmsg;
	@FindBy(xpath = "//button[contains(@class,'p-datepicker-next')]")
	private WebElement nextMonthArrow;
	@FindBy(xpath = "//td/span[contains(@class,'p-disabled')]")
	private List<WebElement> disabledDates;
	@FindBy(xpath = "//div//small[contains(@class,'p-invalid')]")
	private List<WebElement> mandatoryFieldErrorMessages;
	@FindBy(id = "classComments")
	private WebElement comments;
	@FindBy(id = "classNotes")
	private WebElement notes;
	@FindBy(id = "classRecordingPath")
	private WebElement recording;
	@FindBy(xpath = "//div//small[@style='color: red !important;']")
	private List<WebElement> errorMessages;
	@FindBy(id = "//input[@type='text' and @id='icon']")
	private WebElement dateTextbox;
	@FindBy(id="filterGlobal")
	private WebElement searchTextbox;
	@FindBy(xpath="//div[contains(@class,'p-dialog-header')]//button//span")
	private WebElement classPopupClose;
	@FindBy(xpath="//div[contains(@class,'p-dropdown-trigger')]")
	private WebElement dropdownButton;
	@FindBy(xpath="//ul[contains(@class, 'dropdown')]//li")
	private List<WebElement> dropdownList;
	@FindBy(xpath="//span[text()='Class Details']")
	private WebElement classDetailsHeading;
	@FindBy(xpath="//div[contains(@class, 'p-toast-message-content')]")
	private WebElement toastInEdit;
	@FindBy(id="batchName")
	private WebElement batchNameInEditWindow;
	@FindBy(xpath="//i[contains(@class,'p-dropdown-clear-icon')]")
	private WebElement staffNameClearButton;

	@FindBy(xpath="//button[contains(@class,'p-datepicker-next')]")
	private WebElement dateNextButton;
	
	@FindBy(xpath="//p-dropdown[@id='batchName']//span")
	private WebElement dropdownArrow;
	
	@FindBy(xpath="//ul//li[@aria-label='Micro service-01']")
	private WebElement selectingDropdownOption;
	
	@FindBy(xpath="//p-dropdown[@id='staffId']//span")
	private WebElement staffDropdown;
	
	@FindBy(xpath="//ul//li[@aria-label='Urmila Rao']")
	private WebElement selectingStaffDropdownOption;

	

	public WebElement getPopup() {
		return popUp;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getCloseButton() {
		return closeButton;
	}

	public boolean arePopupFieldsEmpty() {
		List<WebElement> popupFields = driver.findElements(By.xpath("//input[@type='text']"));
		for (WebElement field : popupFields) {
			// Check if the field has a placeholder attribute
			if (field.getAttribute("placeholder") != null) {
				return false; // Return false if any field contains a placeholder attribute
			}
		}
		return true;
	}

	public List<WebElement> getInputfields() {
		return inputfields;
	}

	public void getCreateClass(String testCaseName) throws InterruptedException {
		
		// Generate a unique Class Topic
		
		Map<String, String> testData = AppHooks.classTestDataMap.get(testCaseName);

		System.out.println("Starting getCreateClass with test case: " + testCaseName);
		ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
		
		//Thread.sleep(2000);
		ElementsUtil.waitForElementClickablity(driver, dropdownArrow, 10);
		ElementsUtil.ScrolltoElementandClick(driver, dropdownArrow, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, selectingDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		
		uniqueClassTopic = testData.get("Class Topic")+ System.currentTimeMillis();
		
		ElementsUtil.typeInputIntoElement(driver, classTopic, uniqueClassTopic,Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Class Topic entered*****" + uniqueClassTopic);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Class Topic entered*****" + uniqueClassTopic);
		
		ElementsUtil.ScrolltoElementandClick(driver, dateIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, pickingOneMoreDate, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		// Click at an offset to trigger "Number of Classes" auto-population
		Actions actions = new Actions(driver);
		actions.moveToElement(dateIcon, 0, 20).click().perform();
		
		ElementsUtil.ScrolltoElementandClick(driver, staffDropdown, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, selectingStaffDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
		ElementsUtil.ScrolltoElementandClick(driver, statusActive, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Status set to Active****");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Status set to Active****");
		
		System.out.println("****Save button clicked****");
		ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****getCreateClass completed for test case****" + testCaseName);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"****getCreateClass completed for test case****" + testCaseName);
	}

	public boolean getSuccessmsg() {
		ElementsUtil.waitForElementVisibility(driver, toastmsg, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		return toastmsg.isDisplayed();
	}

	public void getSelectClassDate() {
		Map<String, String> testData = AppHooks.classTestDataMap.get("CloseFunctionalityTestdata");
		System.out.println("Starting getCreateClass with test case: " + "CloseFunctionalityTestdata");
		ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
		
		ElementsUtil.ScrolltoElementandClick(driver, dropdownArrow, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, selectingDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
								
		String uniqueClassTopic = testData.get("Class Topic")+ System.currentTimeMillis();
					ElementsUtil.typeInputIntoElement(driver, classTopic, uniqueClassTopic,Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Class Topic entered*****" + uniqueClassTopic);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Class Topic entered*****" + uniqueClassTopic);
		
		System.out.println("***Class Description entered**** " + testData.get("Class Description"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"***Class Description entered**** " + testData.get("Class Description"));
		
		ElementsUtil.ScrolltoElementandClick(driver, dateIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, pickingOneMoreDate, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		// Click at an offset to trigger "Number of Classes" auto-population
		Actions actions = new Actions(driver);
		actions.moveToElement(dateIcon, 0, 20).click().perform();
		
	}

	public boolean isNoOfClassesAddedAutomatically() {

		String noOfClassesValue = noOfClasses.getAttribute("value");
		
		System.out.println("****No of class values****"+noOfClassesValue);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****No of class values****"+noOfClassesValue);

		return !noOfClassesValue.isEmpty();
	}

	public void areWeekendsDisabled() {
		ElementsUtil.ScrolltoElementandClick(driver, datePicker, 10);
		ElementsUtil.ScrolltoElementandClick(driver, nextMonthArrow, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	}

	public boolean areDisabledDatesAtLeastEight() {
		// Count the total number of disabled dates
		int disabledDateCount = disabledDates.size();

		return disabledDateCount >= 8;
	}

	public void fillOptionalFieldsOnly() throws InterruptedException {
		Map<String, String> testData = AppHooks.classTestDataMap.get("ValidClassDetails");

		System.out.println("Starting getCreateClass with test case: " + "ValidClassDetails");
		ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
		
		ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("***Class Description entered**** " + testData.get("Class Description"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"***Class Description entered**** " + testData.get("Class Description"));
		
		ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Comments entered*****" + testData.get("Comments"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Comments entered*****" + testData.get("Comments"));
		
		ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Notes entered**** " + testData.get("Notes"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Notes entered**** " + testData.get("Notes"));
		
		ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****recording entered**** " + testData.get("Recording"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****recording entered**** " + testData.get("Recording"));
		
		ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		Thread.sleep(4000);
	}

	public boolean areErrorMessagesDisplayed() {
		ElementsUtil.waitForElementsVisibility(driver, errorMessages, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		// Check if any error messages were found
		if (errorMessages.isEmpty()) {
			System.out.println("No error messages found.");
			return false;
		}
		boolean allDisplayed = true;
		for (WebElement errorMessage : errorMessages) {
			if (!errorMessage.isDisplayed()) {
				System.out.println("Error message not displayed: " + errorMessage.getText());
				allDisplayed = false;
			} else {
				// Print the error message text if it is displayed
				System.out.println("*****Displayed error message************ " + errorMessage.getText());
				ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
						"****Class Topic entered*****" + errorMessage.getText());
			}
		}
		return allDisplayed; // Return true if all error messages are displayed
	}

	public void getCreateClassWithInvalidData(String testCaseName) throws InterruptedException {
		Map<String, String> testData = AppHooks.classTestDataMap.get(testCaseName);
		System.out.println("Starting getCreateClass with test case: " + testCaseName);
		ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
		ElementsUtil.typeInputIntoElement(driver, batchDropdown, testData.get("Batch Name"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("***Batch Name entered**** " + testData.get("Batch Name"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"***Batch Name entered**** " + testData.get("Batch Name"));
		ElementsUtil.typeInputIntoElement(driver, classTopic, testData.get("Class Topic"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Class Topic entered*****" + testData.get("Class Topic"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Class Topic entered*****" + testData.get("Class Topic"));
		ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("***Class Description entered**** " + testData.get("Class Description"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"***Class Description entered**** " + testData.get("Class Description"));
		System.out.println("***Class Date entered**** " + testData.get("Select Class Dates"));

		Actions actions = new Actions(driver);

		// Click on the date icon to open the date picker
		actions.moveToElement(dateIcon).click().perform();
		for (char c : testData.get("Select Class Dates").toCharArray()) {
		    actions.sendKeys(String.valueOf(c)).pause(Duration.ofMillis(100)).perform();
		}

		actions.moveToElement(dateIcon, 0, 20).click().perform();
				
		ElementsUtil.typeInputIntoElement(driver, noOfClasses, testData.get("No of Classes"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.ScrolltoElementandClick(driver, staffName, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		ElementsUtil.typeInputIntoElement(driver, staffName, testData.get("Staff Name"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Staff Name entered**** " + testData.get("Staff Name"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Staff Name entered**** " + testData.get("Staff Name"));
		ElementsUtil.ScrolltoElementandClick(driver, statusActive, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Status set to Active****");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Status set to Active****");
		System.out.println("****Save button clicked****");
		ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Comments entered*****" + testData.get("Comments"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Comments entered*****" + testData.get("Comments"));
		ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****getCreateClass completed for test case****" + testCaseName);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****getCreateClass completed for test case****" + testCaseName);
		ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Notes entered**** " + testData.get("Notes"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Notes entered**** " + testData.get("Notes"));
		ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****recording entered**** " + testData.get("Recording"));
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****recording entered**** " + testData.get("Recording"));
		ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		
	}
	
	public void getClickOnSaveButton()
	{
		//ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	
		System.out.println("****Admin clicked on save button**** ");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Admin clicked on save button**** ");
		
	}
	public void getclassPopupClose()
	{
		ElementsUtil.ScrolltoElementandClick(driver, classPopupClose, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		System.out.println("****Admin clicked on close button**** ");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Admin clicked on close button**** ");
		
	}
	
	  public void searchForBatchInClass() {
	        
		  searchTextbox.clear();
		  
		  Map<String, String> testData = AppHooks.classTestDataMap.get("OnlySpecialCharacters");

			System.out.println("****Starting getCreateClass with test case: OnlySpecialCharacters***");
			searchTextbox.sendKeys(testData.get("Batch Name"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****Batch Name entered**** " + testData.get("Batch Name"));
			
	    }
	  
	  
	  public void getClickOnNotes()
	  {
		  System.out.println("****Clicking on Notes just to close the popup of Class Topic***");
			
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****Clicking on Notes just to close the popup of Class Topic***");  
	  }
	  
	  public void getCreateClassWithAllFields() throws InterruptedException {
			Map<String, String> testData = AppHooks.classTestDataMap.get("CloseFunctionalityTestdata");
			System.out.println("Starting getCreateClass with test case: " + "CloseFunctionalityTestdata");
			ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
			
			ElementsUtil.ScrolltoElementandClick(driver, dropdownArrow, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, selectingDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
									
			String uniqueClassTopic = testData.get("Class Topic")+ System.currentTimeMillis();
						ElementsUtil.typeInputIntoElement(driver, classTopic, uniqueClassTopic,Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Class Topic entered*****" + uniqueClassTopic);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Class Topic entered*****" + uniqueClassTopic);
			
			System.out.println("***Class Description entered**** " + testData.get("Class Description"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"***Class Description entered**** " + testData.get("Class Description"));
			
			ElementsUtil.ScrolltoElementandClick(driver, dateIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, pickingOneMoreDate, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			// Click at an offset to trigger "Number of Classes" auto-population
			Actions actions = new Actions(driver);
			actions.moveToElement(dateIcon, 0, 20).click().perform();
			
			ElementsUtil.ScrolltoElementandClick(driver, staffDropdown, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, selectingStaffDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			ElementsUtil.ScrolltoElementandClick(driver, statusActive, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Status set to Active****");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Status set to Active****");
			
			ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
			ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Comments entered*****" + testData.get("Comments"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Comments entered*****" + testData.get("Comments"));
			
			ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Notes entered**** " + testData.get("Notes"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Notes entered**** " + testData.get("Notes"));
			
			ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****recording entered**** " + testData.get("Recording"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****recording entered**** " + testData.get("Recording"));
			
			System.out.println("****getCreateClass completed for test case****" + "SaveFunctionalityTestdata");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****getCreateClass completed for test case****" + "SaveFunctionalityTestdata");
			Thread.sleep(4000);	 
	  }
	  public void searchClassTopicForCloseFunctionality() throws InterruptedException {
	        
		  
		  searchTextbox.clear();
		  
		  Map<String, String> testData = AppHooks.classTestDataMap.get("CloseFunctionalityTestdata");

			System.out.println("****Starting getCreateClass with test case: CloseFunctionalityTestdata***");
			searchTextbox.sendKeys(testData.get("Class Topic"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****Searched for class topic entered**** " + testData.get("Class Topic"));
			Thread.sleep(3000);
	    }
	  
	  public void searchClassTopicForSaveFunctionality() throws InterruptedException {
	        
		  ElementsUtil.waitForElementVisibility(driver, searchTextbox, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		  searchTextbox.clear();
		  
		  Map<String, String> testData = AppHooks.classTestDataMap.get("SaveFunctionalityTestdata");

			System.out.println("****Starting getCreateClass with test case: SaveFunctionalityTestdata***");
			searchTextbox.sendKeys(testData.get("Class Topic"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****Searched for class topic entered**** " + testData.get("Class Topic"));
			Thread.sleep(3000);
	    }
	  public boolean isClassDetailsHeadingPresent() {
		  ElementsUtil.waitForElementVisibility(driver, classDetailsHeading, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		  return classDetailsHeading.isDisplayed();
	  }
	  
	  public boolean getIsBatchNameFieldDisabled()
	  {
		  return batchDropdown.isDisplayed();
	  }
	  public boolean getIsClassTopicFieldDisabled()
	  {
		  return classTopic.isDisplayed();
	  }
	  
	  public void getClassTopicAfterUpdateFieldsWithValidData() throws InterruptedException {

	Thread.sleep(3000);
			Map<String, String> testData = AppHooks.classTestDataMap.get("UpdateRowTestData");
			
			classDescription.clear();
			ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			comments.clear();
			ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			notes.clear();
			ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			
	  }
	  

	  
	  public WebElement getToastMessage()
	  {
		  ElementsUtil.waitForElementVisibility(driver, toastInEdit, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		  return toastInEdit;
	  }
	  
	  public void getUpdateFieldsWithInValidData() throws InterruptedException {
		  Map<String, String> testData = AppHooks.classTestDataMap.get("OnlySpecialCharacters");
			System.out.println("Starting getCreateClass with test case: " + "OnlySpecialCharacters");
			ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			//Focus, Clear, and Immediately Trigger a Keyboard Action
			Actions actions = new Actions(driver);
			actions.click(dateIcon).sendKeys(Keys.ESCAPE).sendKeys(Keys.BACK_SPACE).perform();

			ElementsUtil.typeInputIntoElement(driver, dateIcon, testData.get("Select Class Dates"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			actions.moveToElement(dateIcon, 0, 20).click().perform();
			
			Thread.sleep(3000);
			ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
	  }
	  

	  public void  getUpdateMandatoryFieldsWithValidData() throws InterruptedException {
		  Map<String, String> testData = AppHooks.classTestDataMap.get("CloseFunctionalityTestdata");

			System.out.println("Starting getCreateClass with test case: " + "UpdateMandatoryFields");
			ElementsUtil.ScrolltoElementandClick(driver, dateIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, pickingDate, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, staffNameClearButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, staffName, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.typeInputIntoElement(driver, staffName, testData.get("Staff Name"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			Thread.sleep(3000);
	  }
	  
		public void getupdateOptionalFieldsOnly() throws InterruptedException {
			Map<String, String> testData = AppHooks.classTestDataMap.get("UpdateOptionalFields");

			ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
		}
		  public void getUpdateFieldsWithOnlyNumbers() throws InterruptedException {
			  Map<String, String> testData = AppHooks.classTestDataMap.get("OnlyNumbers");
				System.out.println("Starting getCreateClass with test case: " + "OnlyNumbers");
				ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				Actions actions = new Actions(driver);
				actions.click(dateIcon).sendKeys(Keys.ESCAPE).sendKeys(Keys.BACK_SPACE).perform();
				//Actions actions = new Actions(driver);
				actions.moveToElement(dateIcon, 0, 20).click().perform();
			
				ElementsUtil.typeInputIntoElement(driver, dateIcon, testData.get("Select Class Dates"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
				Thread.sleep(6000);
		  }

	  public WebElement  getBatchNameInEditWindow()
	  {
		  return batchNameInEditWindow;
	  }
	  public WebElement  getclassTopic()
	  {
		  return classTopic;
	  }
	  public WebElement  getclassDescription()
	  {
		  return classDescription;
	  }
	  
	 public void getClickOnCancelButton()
	 {
		  ElementsUtil.ScrolltoElementandClick(driver, cancelButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	 }
	 public void getCreateClassWithAllFieldsForSaveFunctionality() throws InterruptedException {
			Map<String, String> testData = AppHooks.classTestDataMap.get("SaveFunctionalityTestdata");
			System.out.println("Starting getCreateClass with test case: " + "SaveFunctionalityTestdata");
			ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
			
			ElementsUtil.ScrolltoElementandClick(driver, dropdownArrow, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, selectingDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
						
			String uniqueClassTopic = testData.get("Class Topic")+ System.currentTimeMillis();
			
			ElementsUtil.typeInputIntoElement(driver, classTopic, uniqueClassTopic,Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Class Topic entered*****" + uniqueClassTopic);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****Class Topic entered*****" + uniqueClassTopic);
			
			ElementsUtil.typeInputIntoElement(driver, classDescription, testData.get("Class Description"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("***Class Description entered**** " + testData.get("Class Description"));
		
			ElementsUtil.ScrolltoElementandClick(driver, dateIcon, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, pickingOneMoreDate, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			// Click at an offset to trigger "Number of Classes" auto-population
			Actions actions = new Actions(driver);
			actions.moveToElement(dateIcon, 0, 20).click().perform();
						
			
			ElementsUtil.ScrolltoElementandClick(driver, staffDropdown, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			ElementsUtil.ScrolltoElementandClick(driver, selectingStaffDropdownOption, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			ElementsUtil.ScrolltoElementandClick(driver, statusActive, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Status set to Active****");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Status set to Active****");
			
			ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO, "Starting getCreateClass with test case: ");
			ElementsUtil.typeInputIntoElement(driver, comments, testData.get("Comments"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Comments entered*****" + testData.get("Comments"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Comments entered*****" + testData.get("Comments"));
			
			ElementsUtil.typeInputIntoElement(driver, notes, testData.get("Notes"), Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****Notes entered**** " + testData.get("Notes"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "****Notes entered**** " + testData.get("Notes"));
			
			ElementsUtil.typeInputIntoElement(driver, recording, testData.get("Recording"),Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			System.out.println("****recording entered**** " + testData.get("Recording"));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"****recording entered**** " + testData.get("Recording"));
			
			System.out.println("****Save button clicked****");
			ElementsUtil.ScrolltoElementandClick(driver, saveButton, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
			
			System.out.println("****getCreateClass completed for test case****" + "SaveFunctionalityTestdata");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
					"****getCreateClass completed for test case****" + "SaveFunctionalityTestdata");
	  }
	 

	    public void getConfirmDeletionAlert() throws InterruptedException {
	    	
	    	
	    	
	    	getCreateClass("ValidDataToTestMultiDelete");
	    	
	    	
	    	searchTextbox.clear();
	    	
	    	

	    	ElementsUtil.typeInputIntoElement(driver, searchTextbox, uniqueClassTopic, Constant.EXPLICIT_ELEMENT_WAIT_TIME);
	    	
	    	Thread.sleep(2000);
	    	    	
	    }
	    
	    public void getSearchForClassTopic() {
	    	  

	    	  searchTextbox.clear();
	    	  ElementsUtil.typeInputIntoElement(driver, searchTextbox, uniqueClassTopic, Constant.EXPLICIT_ELEMENT_WAIT_TIME);

	    	  
	    	    }

	  
}
