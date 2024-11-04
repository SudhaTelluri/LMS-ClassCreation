@all
Feature: Add New Class popup

  Background: 
    Given Admin is on the Class Popup window

  @classCreation
  Scenario Outline: Check if class is created when only mandatory fields are entered with valid data
    When Admin enters mandatory fields using "<TestCaseName>" in the form and clicks on save button
    Then Admin gets message Class added Successfully

    Examples: 
      | TestCaseName                      |
      | ValidClassDetails                 |
      | ValidClassDetails2                |
      | ValidClassDetails3                |
      | ValidDataToTestMultiDelete        |
      | ValidDataToTestMultiDelete2       |
     
  @isNoOfClassesAutomaticallyUpdated
  Scenario: Check no classes value added when selecting class dates
    When Admin selects class date in date picker
    Then Admin should see no of class value is added automatically

  @areWeekendsDisabled
  Scenario: Check weekend dates are disabled in calender
    When Admin clicks date picker
    Then Admin should see weekends dates are disabled to select

  @verifyErrorMessageAndHighlightedBox
  Scenario: Check if class is created when only optional fields are entered with valid data
    When Admin skips to add value in mandatory field and enter only the optional field
    Then Admin should see error message below the test field and the field will be highlighed in red color

  @CreateClassWithInvalidData
  Scenario Outline: check if class is created when invalid data is entered in all of the fields
    When Admin enters invalid data using "<TestCaseName>" in all of the  fields in the form and clicks on save button
    Then Admin gets error message and class is not created

    Examples: 
      | TestCaseName          |
      | OnlySpecialCharacters |
      | OnlyNumbers           |
      | Alphanumeric          |
      | Negative Number       |
      | OnlyAlphabet          |
      | OnlyZeros             |

  @emptyFormSubmission
  Scenario: Empty form submission
    When Admin clicks on save button without entering data
    Then class wont be created and Admin gets error message

  @closefunctionality
  Scenario: Validate Cancel/Close(X) icon on class Details form
    When Admin clicks Cancel/Close Icon on Admin Details form
    Then Class Details popup window should be closed without saving

  @saveFunctionality
  Scenario: Validate Save button on class Details form
    When Admin clicks save button
    Then Admin can see the class details popup closed and adding new class
