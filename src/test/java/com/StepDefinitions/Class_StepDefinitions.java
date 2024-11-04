package com.StepDefinitions;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.PageObjects.POM_ClassDetailsPage;
import com.PageObjects.POM_DashboardPage;
import com.PageObjects.POM_LoginPage;
import com.PageObjects.POM_ManageClassPage;
import com.Utilities.ElementsUtil;
import com.Utilities.TestContext;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Class_StepDefinitions {

	TestContext testcontext;
	POM_LoginPage loginpage;
	POM_DashboardPage dashboardpage;
	POM_ManageClassPage manageclass;
	POM_ClassDetailsPage classdetails;
	private String originalBatchName;
	private String originalClassTopic;
	private String topic;
	private String originalClassDescription;
	boolean isSorted;
	int pageEntriesBeforeDeletion;
	int pageEntriesAfterDeletion;
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");
	List<String> previousPageClassTopics;
	List<String> currentPageClassTopics;

	public Class_StepDefinitions(TestContext testcontext) {

		this.testcontext = testcontext;

		loginpage = testcontext.pageobjectmanager.getLoginPage();
		dashboardpage = testcontext.pageobjectmanager.getDashboardPage();
		manageclass = testcontext.pageobjectmanager.getManageClassPage();
		classdetails = testcontext.pageobjectmanager.getClassDetailsPage();
	}

	@Given("Admin is on the Dashboard Page")
	public void admin_is_on_the_dashboard_page() throws InterruptedException {
		loginpage.getLogin();
	}

	@When("Admin clicks the Class Navigation bar in the Header")
	public void admin_clicks_the_class_navigation_bar_in_the_header() {
		dashboardpage.getClickOnClassLink();
	}

	@Then("Admin should land on the Manage class page")
	public void admin_should_land_on_the_manage_class_page() {
		Assert.assertTrue(manageclass.isManageClassHeadingPresent());
	}

	@Then("Admin should see the {string} Title")
	public void admin_should_see_the_title(String title) {
		Assert.assertEquals(manageclass.getManageClassTitle(), title);
	}

	@Then("Admin should see the {string} Header")
	public void admin_should_see_the_header(String string) {
		Assert.assertTrue(manageclass.isManageClassHeadingPresent());
	}

	@Then("Admin should see the Searchbar in Manage class page")
	public void admin_should_see_the_searchbar_in_manage_class_page() {
		Assert.assertTrue(manageclass.isSearchBarDisplayed());
	}

	@Then("Admin should see the datatable heading like {string}")
	public void admin_should_see_the_datatable_heading_like(String expectedHeaders) {

		String[] expectedResultArray = expectedHeaders.split(",\\s*");

		// Removed all spaces, trimmed, and normalized to lowercase
		List<String> expectedResultList = Arrays.stream(expectedResultArray)
				.map(header -> header.replaceAll("\\s+", "").trim().toLowerCase()).collect(Collectors.toList());

		// Get the actual column headers from the manageclass
		List<String> actualColumnHeaders = manageclass.getactualColumnHeaderList();
		actualColumnHeaders = actualColumnHeaders.stream()
				.map(header -> header.replaceAll("\\s+", "").trim().toLowerCase()).collect(Collectors.toList());

		// Logged the actual and expected column headers for debugging purposes
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Actual Column Headers: " + actualColumnHeaders);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Expected Column Headers: " + expectedResultList);

		// Print statements for debugging in console
		System.out.println("Expected Headers: " + expectedResultList);
		System.out.println("Actual Headers: " + actualColumnHeaders);

		Assert.assertEquals(expectedResultList, actualColumnHeaders);
	}

	@Then("Admin should see the {string}  and enabled pagination controls under the data table")
	public void admin_should_see_the_and_enabled_pagination_controls_under_the_data_table(String expectedText) {
		String actualText = manageclass.getPaginatorText();

		System.out.println("***Paginator text*** " + actualText);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "***Actual table footer text*** " + actualText);

		System.out.println("***Expected paginator text***" + expectedText);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "***expected table footer text*** " + expectedText);

		Assert.assertTrue(actualText.matches(expectedText.replace("\\\\", "\\")));
		Assert.assertTrue(manageclass.isPaginationControlsDisiplayedOrNot());
	}

	@Then("Admin should see the Sort icon of all the field in the datatable.")
	public void admin_should_see_the_sort_icon_of_all_the_field_in_the_datatable() {
		Assert.assertTrue(manageclass.isSortIconPresentInHeaders());
	}

	@Then("Admin should see the Delete button under the Manage class page header.")
	public void admin_should_see_the_delete_button_under_the_manage_class_page_header() {
		Assert.assertTrue(manageclass.isMultiDeleteButtonVisible());
	}

	@Then("Admin should see Total no of classes in below of the data table.")
	public void admin_should_see_total_no_of_classes_in_below_of_the_data_table() {
		Assert.assertTrue(manageclass.isPaginatorTextVisibleOrNot());
	}

	@Given("Admin is on the Manage class page")
	public void admin_is_on_the_manage_class_page() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
	}

	@When("Admin clicks add new class under the class menu bar.")
	public void admin_clicks_add_new_class_under_the_class_menu_bar() {
		manageclass.getClickOnAddNewClass();
	}

	@Then("Admin should see a popup open for class details with empty form along with <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window")
	public void admin_should_see_a_popup_open_for_class_details_with_empty_form_along_with_save_and_cancel_button_and_close_x_icon_on_the_top_right_corner_of_the_window() {
		boolean elementsDisplayed = ElementsUtil.areElementsDisplayed(classdetails.getPopup(),
				classdetails.getSaveButton(), classdetails.getCancelButton(), classdetails.getCloseButton());
		Assert.assertTrue(elementsDisplayed, "***Popup and Save,Cancel,Close Buttons are displayed***");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Verifying that all form fields are empty.");
		Assert.assertTrue(classdetails.arePopupFieldsEmpty(), "Some popup fields are not empty");

	}

	@Then("Admin should see few input fields and their respective text boxes in the class details window")
	public void admin_should_see_few_input_fields_and_their_respective_text_boxes_in_the_class_details_window() {

		ExtentCucumberAdapter.getCurrentScenario().log(Status.INFO,
				"Verifying input fields in the class details window that has text boxes. Found "
						+ classdetails.getInputfields().size() + " input fields.");
		System.out.println("***No of input fields with text boxes****" + classdetails.getInputfields().size());
		Assert.assertTrue(classdetails.getInputfields().size() > 0, "Input fields are not found");
	}

	@Given("Admin is on the Class Popup window")
	public void admin_is_on_the_class_popup_window() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnAddNewClass();
	}

	@When("Admin enters mandatory fields using {string} in the form and clicks on save button")
	public void admin_enters_mandatory_fields_using_in_the_form_and_clicks_on_save_button(String TestCaseName)
			throws InterruptedException {
		classdetails.getCreateClass(TestCaseName);

	}

	@Then("Admin gets message Class added Successfully")
	public void admin_gets_message_class_added_successfully() {
		Assert.assertTrue(classdetails.getSuccessmsg(), "*****Success message not displayed******");
	}

	@When("Admin selects class date in date picker")
	public void admin_selects_class_date_in_date_picker() throws InterruptedException {
		classdetails.getSelectClassDate();
	}

	@Then("Admin should see no of class value is added automatically")
	public void admin_should_see_no_of_class_value_is_added_automatically() {
		Assert.assertTrue(classdetails.isNoOfClassesAddedAutomatically());
	}

	@When("Admin clicks date picker")
	public void admin_clicks_date_picker() {
		classdetails.areWeekendsDisabled();
	}

	@Then("Admin should see weekends dates are disabled to select")
	public void admin_should_see_weekends_dates_are_disabled_to_select() {
		Assert.assertTrue(classdetails.areDisabledDatesAtLeastEight());
	}

	@When("Admin skips to add value in mandatory field and enter only the optional field")
	public void admin_skips_to_add_value_in_mandatory_field_and_enter_only_the_optional_field()
			throws InterruptedException {
		classdetails.fillOptionalFieldsOnly();
	}

	@Then("Admin should see error message below the test field and the field will be highlighed in red color")
	public void admin_should_see_error_message_below_the_test_field_and_the_field_will_be_highlighed_in_red_color() {
		Assert.assertTrue(classdetails.areErrorMessagesDisplayed());
	}

	@When("Admin enters invalid data using {string} in all of the  fields in the form and clicks on save button")
	public void admin_enters_invalid_data_using_in_all_of_the_fields_in_the_form_and_clicks_on_save_button(
			String TestCaseName) throws InterruptedException {
		classdetails.getCreateClassWithInvalidData(TestCaseName);
	}

	@Then("Admin gets error message and class is not created")
	public void admin_gets_error_message_and_class_is_not_created() {
		Assert.assertTrue(classdetails.areErrorMessagesDisplayed());
	}

	@When("Admin clicks on save button without entering data")
	public void admin_clicks_on_save_button_without_entering_data() throws InterruptedException {
		classdetails.getClickOnNotes();
		Thread.sleep(2000);
		classdetails.getClickOnSaveButton();

	}

	@Then("class wont be created and Admin gets error message")
	public void class_wont_be_created_and_admin_gets_error_message() throws InterruptedException {

		Assert.assertTrue(classdetails.areErrorMessagesDisplayed());

		classdetails.getclassPopupClose();

		classdetails.searchForBatchInClass();
		int pageCount = manageclass.getPaginationCount();
		Assert.assertTrue(pageCount == 0,
				"Class was created when it shouldn't have been (page count: " + pageCount + ")");
	}

	@When("Admin clicks Cancel\\/Close Icon on Admin Details form")
	public void admin_clicks_cancel_close_icon_on_admin_details_form() throws InterruptedException {
		classdetails.getCreateClassWithAllFields();
		classdetails.getCloseButton();

	}

	@Then("Class Details popup window should be closed without saving")
	public void class_details_popup_window_should_be_closed_without_saving() throws InterruptedException {
		classdetails.getclassPopupClose();

		classdetails.searchClassTopicForCloseFunctionality();
		;
		int pageCount = manageclass.getPaginationCount();
		Assert.assertTrue(pageCount == 0,
				"Class was created when it shouldn't have been (page count: " + pageCount + ")");

	}

	@When("Admin clicks save button")
	public void admin_clicks_save_button() throws InterruptedException {
		classdetails.getCreateClassWithAllFieldsForSaveFunctionality();
		Thread.sleep(5000);
	}

	@Then("Admin can see the class details popup closed and adding new class")
	public void admin_can_see_the_class_details_popup_closed_and_adding_new_class() throws InterruptedException {

		classdetails.searchClassTopicForSaveFunctionality();
		;
		int pageCount = manageclass.getPaginationCount();
		Assert.assertTrue(pageCount > 0, "Class was not created as expected (page count: " + pageCount);
	}

	/********************** Edit New Class **********************/

	@Given("Admin is on the Manage Class page to validate edit icon")
	public void admin_is_on_the_manage_class_page_to_validate_edit_icon() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
	}

	@When("Admin clicks on the edit icon")
	public void admin_clicks_on_the_edit_icon() {
		manageclass.getClickOnFirstEditIcon();
	}

	@Then("A new pop up with class details appears")
	public void a_new_pop_up_with_class_details_appears() {
		Assert.assertTrue(classdetails.isClassDetailsHeadingPresent());
	}

	@Given("Admin is on the Manage Class page to validate disabled batch")
	public void admin_is_on_the_manage_class_page_to_validate_disabled_batch() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
	}

	@Then("Admin should see batch name field is disabled")
	public void admin_should_see_batch_name_field_is_disabled() {
		Assert.assertTrue(classdetails.getIsBatchNameFieldDisabled());
	}

	@Given("Admin is on the Manage Class page to validate disabled class topic")
	public void admin_is_on_the_manage_class_page_to_validate_disabled_class_topic() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
	}

	@Then("Admin should see class topic field is disabled")
	public void admin_should_see_class_topic_field_is_disabled() {
		Assert.assertTrue(classdetails.getIsClassTopicFieldDisabled());
	}

	@Given("Admin is on the Edit Class Popup window")
	public void admin_is_on_the_edit_class_popup_window() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
		topic = manageclass.getClassTopicForSearch();
		manageclass.getClickOnEditIcon(topic);

	}

	@When("Update the fields with valid data and click save")
	public void update_the_fields_with_valid_data_and_click_save() throws InterruptedException {

		classdetails.getClassTopicAfterUpdateFieldsWithValidData();
		// classdetails.getClickOnSaveButton();
	}

	@Then("Admin gets message {string} and see the updated values in data table")
	public void admin_gets_message_and_see_the_updated_values_in_data_table(String successmsg)
			throws InterruptedException {
		Assert.assertEquals(classdetails.getToastMessage().getText().replaceAll("\\s+", " "),
				successmsg.replaceAll("\\s+", " "), "The success message does not match.");
		manageclass.getupdatedRowForGivenClasstopic(topic);

	}

	@When("Update the fields with invalid values and click save")
	public void update_the_fields_with_invalid_values_and_click_save() throws InterruptedException {
		classdetails.getUpdateFieldsWithInValidData();
		// classdetails.getClickOnSaveButton();
	}

	@Then("Admin should get Error message")
	public void admin_should_get_error_message() {
		Assert.assertTrue(classdetails.areErrorMessagesDisplayed(),
				"Error messages are not displayed for invalid input while updating Class Details.");
	}

	@When("Update the mandatory fields with valid values and click save")
	public void update_the_mandatory_fields_with_valid_values_and_click_save() throws InterruptedException {
		classdetails.getUpdateMandatoryFieldsWithValidData();
		// classdetails.getClickOnSaveButton();
	}

	@Given("Admin is on the Edit  Class Popup window to validate optional fields which means admin will be")
	public void admin_is_on_the_edit_class_popup_window_to_validate_optional_fields_which_means_admin_will_be()
			throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
		manageclass.getClickOnFirstEditIcon();

	}

	@When("Update the optional fields with valid values and click save")
	public void update_the_optional_fields_with_valid_values_and_click_save() throws InterruptedException {
		classdetails.getupdateOptionalFieldsOnly();
		classdetails.getClickOnSaveButton();
	}

	@When("Admin enters only numbers or special char in the text fields")
	public void admin_enters_only_numbers_or_special_char_in_the_text_fields() throws InterruptedException {
		classdetails.getUpdateFieldsWithOnlyNumbers();
		// classdetails.getClickOnSaveButton();
	}

	@When("Admin clicks Cancel button on edit popup")
	public void admin_clicks_cancel_button_on_edit_popup() throws InterruptedException {
		manageclass.getClickOnFirstEditIcon();

		// Capture original values for debugging
		originalBatchName = classdetails.getBatchNameInEditWindow().getAttribute("ng-reflect-model");
		originalClassTopic = classdetails.getclassTopic().getAttribute("ng-reflect-model");
		originalClassDescription = classdetails.getclassDescription().getAttribute("ng-reflect-model");

		// Print original values for debugging
		System.out.println("*****Original Batch Name:***** " + originalBatchName);
		System.out.println("*****Original Class Topic***** " + originalClassTopic);
		System.out.println("*****Original Class Description***** " + originalClassDescription);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "*****Original Batch Name:***** " + originalBatchName);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "*****Original Class Topic***** " + originalClassTopic);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"*****Original Class Description***** " + originalClassDescription);

		classdetails.getclassDescription().clear();
		classdetails.getclassDescription().sendKeys("This is an updated description");
		classdetails.getClickOnCancelButton();

	}

	@Then("Admin can see the class details popup disappears and can see nothing changed for particular Class")
	public void admin_can_see_the_class_details_popup_disappears_and_can_see_nothing_changed_for_particular_class()
			throws InterruptedException {

		manageclass.getSearchbar().sendKeys(originalClassTopic);

		manageclass.getClickOnFirstEditIcon();
		String currentBatchName = classdetails.getBatchNameInEditWindow().getAttribute("ng-reflect-model");
		String currentClassTopic = classdetails.getclassTopic().getAttribute("ng-reflect-model");
		String currentClassDescription = classdetails.getclassDescription().getAttribute("ng-reflect-model");

		System.out.println("*****Current Class Topic***** " + currentClassTopic);
		System.out.println("*****Current Class Description***** " + currentClassDescription);

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "*****Current Class Topic***** " + currentClassTopic);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"*****Current Class Description***** " + currentClassDescription);

		// Assert that original values match current values, ensuring no changes were
		// made
		Assert.assertTrue(
				originalBatchName.equals(currentBatchName) && originalClassTopic.equals(currentClassTopic)
						&& originalClassDescription.equals(currentClassDescription),
				"The class details have changed unexpectedly after clicking Cancel.");
	}

	/**************** Sorting ****************/

	@Given("Admin is on the Manage Class Page to verify sorting")
	public void admin_is_on_the_manage_class_page_to_verify_sorting() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
	}

	@When("Admin clicks on the columnname {string}")
	public void admin_clicks_on_the_columnname(String columnname) throws InterruptedException {
		isSorted = manageclass.getSortClassDetails(columnname);
	}

	@Then("Admin should see the column sorted by columnname {string}")
	public void admin_should_see_the_column_sorted_by_columnname(String string) {
		Assert.assertTrue(isSorted);
	}

	/****************
	 * Delete Class
	 * 
	 * @throws InterruptedException
	 ****************/

	@Given("Admin is on Manage Class Page")
	public void admin_is_on_manage_class_page() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();

	}

	@When("Admin clicks the delete icon")
	public void admin_clicks_the_delete_icon() {
		manageclass.getDeleteIcon().click();

	}

	@Then("Admin should see a alert open with heading {string} along with  <YES> and <NO> button for deletion")
	public void admin_should_see_a_alert_open_with_heading_along_with_yes_and_no_button_for_deletion(String string) {
		Assert.assertTrue(manageclass.getValidatePresenceOfFields());
	}

	@Given("Admin is on Confirm Deletion alert")
	public void admin_is_on_confirm_deletion_alert() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
		manageclass.searchClassTopicAndDelete();
	}

	@When("Admin clicks yes option")
	public void admin_clicks_yes_option() throws InterruptedException {

		manageclass.confirmDeletion();
	}

	@Then("Admin gets a message {string} alert and do not see that Class in the data table")
	public void admin_gets_a_message_alert_and_do_not_see_that_class_in_the_data_table(String string)
			throws InterruptedException {
		Assert.assertTrue(manageclass.getReturnToastText(), "*****Class topic not found*****");

	}

	@When("Admin clicks  No option")
	public void admin_clicks_no_option() throws InterruptedException {
		manageclass.getClickOnNo();
		Thread.sleep(2000);
	}

	@Then("Admin can see the deletion alert disappears without deleting")
	public void admin_can_see_the_deletion_alert_disappears_without_deleting() {
		int pageCount = manageclass.getPaginationCount();
		Assert.assertTrue(pageCount > 0, "Class was deleted (page count: " + pageCount);

	}

	@When("Admin clicks on close button")
	public void admin_clicks_on_close_button() {
		manageclass.getClickOnClose();
	}

	@Then("Admin can see the deletion alert disappears without any changes")
	public void admin_can_see_the_deletion_alert_disappears_without_any_changes() {
		int pageCount = manageclass.getPaginationCount();
		Assert.assertTrue(pageCount > 0, "Class was deleted if the page count was >0 (page count: " + pageCount);
	}

	/*******************
	 * Delete Multiple Class
	 * 
	 * @throws InterruptedException
	 ******************/

	@Given("Admin is in Manage Class page")
	public void admin_is_in_manage_class_page() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
	}

	@When("Admin clicks any checkbox in the data table")
	public void admin_clicks_any_checkbox_in_the_data_table() {
		manageclass.getClickOnSingleCheckbox();
	}

	@Then("Admin should see common delete option enabled under header Manage class")
	public void admin_should_see_common_delete_option_enabled_under_header_manage_class() {
		manageclass.getisMultiDeleteEnabled();
	}

//		@Given("Admin is on Confirm Deletion alert using Multi delete")
//		public void admin_is_on_confirm_deletion_alert_using_multi_delete() throws InterruptedException {
//			loginpage.getLogin();
//			dashboardpage.getClickOnClassLink();
//			System.out.println("******clicking on class link to avoid element click intercepted exception*******");
//			manageclass.getSearchOfClassTopic(rb.getString("classTopic"));
//			manageclass.getClickOnMultiDeleteCheckbox();
//		}

	@Given("Admin is on Confirm Deletion alert using Multi delete")
	public void admin_is_on_confirm_deletion_alert_using_multi_delete() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
		manageclass.getSearchOfClassTopic(rb.getString("classTopic2"));
		Thread.sleep(2000);
		pageEntriesBeforeDeletion = manageclass.getPaginationCount();
		manageclass.getClickOnSingleCheckbox();
		manageclass.getClickOnMultiDeleteButton();
		Thread.sleep(2000);
	}

	@When("Admin clicks <YES> button on the alert")
	public void admin_clicks_yes_button_on_the_alert() throws InterruptedException {
		manageclass.getClickOnMultiDeleteYes();
		Thread.sleep(4000);

		pageEntriesAfterDeletion = manageclass.getPaginationCount();

	}

	@Then("Admin should land on Manage class page and can see the selected class is deleted from the data table")
	public void admin_should_land_on_manage_class_page_and_can_see_the_selected_class_is_deleted_from_the_data_table()
			throws InterruptedException {

		Assert.assertTrue(manageclass.isManageClassHeadingPresent());

		Thread.sleep(3000);
		System.out.println("****pageEntriesBeforeDeletion:*********" + pageEntriesBeforeDeletion
				+ "******pageEntriesAfterDeletion:*********" + pageEntriesAfterDeletion);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "pageEntriesBeforeDeletion:" + pageEntriesBeforeDeletion
				+ "pageEntriesAfterDeletion:" + pageEntriesAfterDeletion);
		Assert.assertTrue(pageEntriesBeforeDeletion > pageEntriesAfterDeletion,
				"Class is  deleted pageEntriesBeforeDeletion:***  " + pageEntriesAfterDeletion
						+ "pageEntriesAfterDeletion:**" + pageEntriesBeforeDeletion);

	}

	@When("Admin clicks <No> button on the alert")
	public void admin_clicks_no_button_on_the_alert() throws InterruptedException {
		manageclass.getClickOnNo();
	}

	@Then("Admin should land on Manage class page and can see the selected class is not deleted from the data table")
	public void admin_should_land_on_manage_class_page_and_can_see_the_selected_class_is_not_deleted_from_the_data_table()
			throws InterruptedException {
		Assert.assertTrue(manageclass.isManageClassHeadingPresent());
		pageEntriesAfterDeletion = manageclass.getPaginationCount();
		System.out.println("****pageEntriesBeforeDeletion:*****" + pageEntriesBeforeDeletion
				+ "*****pageEntriesAfterDeletion:******" + pageEntriesAfterDeletion);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "pageEntriesBeforeDeletion:" + pageEntriesBeforeDeletion
				+ "pageEntriesAfterDeletion:" + pageEntriesAfterDeletion);
		Assert.assertEquals(pageEntriesBeforeDeletion, pageEntriesAfterDeletion,
				"Class is  deleted pageEntriesBeforeDeletion:  " + pageEntriesAfterDeletion
						+ "pageEntriesAfterDeletion:" + pageEntriesBeforeDeletion);

	}

//	@Given("Class is on Confirm Deletion alert after selecting multiple checkboxes using {string}")
//	public void class_is_on_confirm_deletion_alert_after_selecting_multiple_checkboxes_using(String testcase) throws InterruptedException {
//		  loginpage.getLogin();
//		  dashboardpage.getClickOnClassLink();
//		  System.out.println("******clicking on class link to avoid element click intercepted exception*******");
//		  manageclass.getClickOnAddNewClass();
//	    classdetails.getCreateClass(testcase);
//	    classdetails.getSearchForClassTopic();
//	    manageclass.getClickOnMultiDeleteCheckbox();
//	}

	@Given("Class is on Confirm Deletion alert after selecting multiple checkboxes")
	public void class_is_on_confirm_deletion_alert_after_selecting_multiple_checkboxes() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
		manageclass.getSearchOfClassTopic(rb.getString("classTopic"));
		Thread.sleep(2000);
		pageEntriesBeforeDeletion = manageclass.getPaginationCount();
		manageclass.getClickOnCheckboxesFromSecondRow();
		Thread.sleep(2000);
		manageclass.getClickOnMultiDeleteButton();

		Thread.sleep(2000);
	}

	@Then("Admin should land on Manage class page and can see the selected class are deleted from the data table")
	public void admin_should_land_on_manage_class_page_and_can_see_the_selected_class_are_deleted_from_the_data_table() {
		Assert.assertTrue(manageclass.isManageClassHeadingPresent());
		int noOfentries = manageclass.getPaginationCount();
		Assert.assertTrue(noOfentries == 0, "Records still exists in the table:" + noOfentries);

	}

	@Then("Admin should land on Manage Class page and can see the selected class are not deleted from the data table")
	public void admin_should_land_on_manage_class_page_and_can_see_the_selected_class_are_not_deleted_from_the_data_table() {
		Assert.assertTrue(manageclass.isManageClassHeadingPresent());
		pageEntriesAfterDeletion = manageclass.getPaginationCount();
		System.out.println("****pageEntriesBeforeDeletion:*****" + pageEntriesBeforeDeletion
				+ "*****pageEntriesAfterDeletion:******" + pageEntriesAfterDeletion);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "pageEntriesBeforeDeletion:" + pageEntriesBeforeDeletion
				+ "pageEntriesAfterDeletion:" + pageEntriesAfterDeletion);
		Assert.assertEquals(pageEntriesBeforeDeletion, pageEntriesAfterDeletion,
				"Class is  deleted pageEntriesBeforeDeletion:  " + pageEntriesAfterDeletion
						+ "pageEntriesAfterDeletion:" + pageEntriesBeforeDeletion);
	}

	/************* Search Box ****************/

	@Given("Admin is on the Manage Class page after login")
	public void admin_is_on_the_manage_class_page_after_login() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		System.out.println("******clicking on class link to avoid element click intercepted exception*******");
		manageclass.getClickOnClass();
	}

	@When("Admin enters {string} in the search textbox")
	public void admin_enters_in_the_search_textbox(String SearchValue) throws InterruptedException {
		manageclass.getSearchbar().clear();
		Thread.sleep(3000);
		manageclass.getSearchbar().sendKeys(SearchValue);
		Thread.sleep(3000);
	}

	@Then("Admin should see atleast one record")
	public void admin_should_see_atleast_one_record() {
		int pagecount = manageclass.getPaginationCount();
		Assert.assertTrue(pagecount > 0,
				"**********Test failed: Expected total records greater than 0 , but got****** " + pagecount);
	}

	/************************** Pagination **********************************/

	@Given("Admin is on Manage class page to check pagination")
	public void admin_is_on_manage_class_page_to_check_pagination() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
		
	}

	@When("Admin clicks Next page link on the class table")
	public void admin_clicks_next_page_link_on_the_class_table() throws InterruptedException {
		previousPageClassTopics=manageclass.getCaptureColumnData();
		manageclass.getClickOnNextPageLink();
		Thread.sleep(4000);
		currentPageClassTopics=manageclass.getCaptureColumnData();
		
	}

	@Then("Admin should see the next page record on the table  with Pagination has next active link enabled")
	public void admin_should_see_the_next_page_record_on_the_table_with_pagination_has_next_active_link_enabled() {
		Assert.assertTrue(manageclass.isNextLinkEnabled());
		Assert.assertNotEquals(previousPageClassTopics, currentPageClassTopics, "The records did not change after navigating to the next page.");
		
			}
	

	@Given("Admin is on Previous class page")
	public void admin_is_on_previous_class_page() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
		manageclass.getClickOnLastPageLink();
	}

	@When("Admin clicks Start page link")
	public void admin_clicks_start_page_link() {
		manageclass.getClickOnFirstPageLink();
	}

	@Then("Admin should see the very first page record on the table with Previous page link are disabled")
	public void admin_should_see_the_very_first_page_record_on_the_table_with_previous_page_link_are_disabled() {
		Assert.assertFalse(manageclass.isPrevLinkEnabled());
		Assert.assertTrue(manageclass.isFirstPageDisplayedCorrectly());
	}

	@Given("Admin is on last page of class table")
	public void admin_is_on_last_page_of_class_table() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
		manageclass.getClickOnLastPageLink();
		
	}

	@When("Admin clicks First page link")
	public void admin_clicks_first_page_link() {
		manageclass.getClickOnPrevPageLink();
	}

	@Then("Admin should see the previous page record on the table with pagination has previous page link enabled")
	public void admin_should_see_the_previous_page_record_on_the_table_with_pagination_has_previous_page_link_enabled() {
		Assert.assertTrue(manageclass.isPrevLinkEnabled());
	}

	@When("Admin clicks Last page link")
	public void admin_clicks_last_page_link() throws InterruptedException {
		manageclass.getClickOnLastPageLink();
		currentPageClassTopics=manageclass.getCaptureColumnData();
		Thread.sleep(2000);
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled() {
		Assert.assertTrue(manageclass.isLastPageDisplayedCorrectly());
	}
	
	/********************Navigation From Class
	 * @throws InterruptedException **************************/
	
	@Given("Admin is on Manage Class page for navigation check")
	public void admin_is_on_manage_class_page_for_navigation_check() throws InterruptedException {
		loginpage.getLogin();
		dashboardpage.getClickOnClassLink();
		manageclass.getClickOnClass();
	}

	@When("Admin clicks on Logout link on Manage class page")
	public void admin_clicks_on_logout_link_on_manage_class_page() {
	  manageclass.getClickOnLogout();
	}
	@Then("Admin is re-directed to Login page")
	public void admin_is_re_directed_to_login_page() {
		Assert.assertTrue(manageclass.isLoginTextDisplayed());
	}

@When("Admin clicks on any module link on Manage Class page")
public void admin_clicks_on_any_module_link_on_manage_class_page() {
manageclass.getClickOnBatchLink();
}
@Then("Admin is re-directed to which module link they clicked.")
public void admin_is_re_directed_to_which_module_link_they_clicked() { 
Assert.assertTrue(manageclass.getManageBatchIsDisplayed());
}

@When("Admin clicks on Class link on Manage Class page")
public void admin_clicks_on_class_link_on_manage_class_page() {
	manageclass.getClickOnClass();
	manageclass.getClickOnAddNewClass();
}
@Then("Admin is re-directed to class page")
public void admin_is_re_directed_to_class_page() {
   Assert.assertTrue(manageclass.isClassDetailsHeadingDisplayed());
}




}
