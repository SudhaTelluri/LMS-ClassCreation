@all
Feature: Delete Multiple Class

  @isMultiDeleteEnabled
  Scenario: Validate Common Delete button enabled after clicking on any checkbox
    Given Admin is in Manage Class page
    When Admin clicks any checkbox in the data table
    Then Admin should see common delete option enabled under header Manage class

  @MultiDeleteUsingSingleCheckboxAndNo
  Scenario: Validate multiple class deletion by selecting Single checkbox
    Given Admin is on Confirm Deletion alert using Multi delete
    When Admin clicks <No> button on the alert
    Then Admin should land on Manage class page and can see the selected class is not deleted from the data table

  @MultiDeleteUsingSingleCheckbox
  Scenario: Validate multiple class deletion by selecting Single checkbox
    Given Admin is on Confirm Deletion alert using Multi delete
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage class page and can see the selected class is deleted from the data table

  @current @MultiDeleteWithMultipleCheckboxesNo
  Scenario: Validate multiple class deletion by selecting multiple check boxes
    Given Class is on Confirm Deletion alert after selecting multiple checkboxes
    When Admin clicks <No> button on the alert
    Then Admin should land on Manage Class page and can see the selected class are not deleted from the data table

  @current @MultiDeleteWithMultipleCheckboxes
  Scenario: Validate multiple class deletion by selecting multiple check boxes
    Given Class is on Confirm Deletion alert after selecting multiple checkboxes
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage class page and can see the selected class are deleted from the data table
