@all
Feature: Edit New class

@isEditIconOpensPopup
  Scenario: Validate row level edit icon
    Given Admin is on the Manage Class page to validate edit icon
    When Admin clicks on the edit icon
    Then A new pop up with class details appears

@checkBatchNameIsDisabled
  Scenario: Check disabled  batch name
    Given Admin is on the Manage Class page to validate disabled batch
    When Admin clicks on the edit icon
    Then Admin should see batch name field is disabled

@checkDisabledClassTopic
  Scenario: Check disabled class topic
    Given Admin is on the Manage Class page to validate disabled class topic
    When Admin clicks on the edit icon
    Then Admin should see class topic field is disabled

@validateClassUpdate
  Scenario: Check if the fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the fields with valid data and click save
    Then Admin gets message "Successful Class Updated" and see the updated values in data table

@InvalidUpdate
  Scenario: Check if the fields are updated with invalid values
    Given Admin is on the Edit Class Popup window
    When Update the fields with invalid values and click save
    Then Admin should get Error message

@UpdateMandatoryFieldsWithValidData
  Scenario: Check if the mandatory fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the mandatory fields with valid values and click save
    Then Admin gets message "Successful Class Updated" and see the updated values in data table
    
@UpdateOnlyOptionalfields
  Scenario: Check if the optional fields are updated with valid data
    Given Admin is on the Edit  Class Popup window to validate optional fields which means admin will be
    When Update the optional fields with valid values and click save
    Then Admin gets message "Successful Class Updated" and see the updated values in data table

@updateAdminWithOnlyNumbers
  Scenario: Validate invalid values in the text fields
    Given Admin is on the Edit Class Popup window
    When Admin enters only numbers or special char in the text fields
    Then Admin should get Error message

@verifyCancelFunctionality
  Scenario: Validate Cancel button on Edit popup
    Given Admin is on the Edit Class Popup window
    When Admin clicks Cancel button on edit popup
    Then Admin can see the class details popup disappears and can see nothing changed for particular Class
