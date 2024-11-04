@all
Feature: Class Page Validation

  Background: Admin navigated to Dashboard and clicked on Class link
    Given Admin is on the Dashboard Page
    When Admin clicks the Class Navigation bar in the Header

  @ManageClasslanding
  Scenario: Validating the class manage page
    Then Admin should land on the Manage class page

  @ValidatingTitle
  Scenario: Validating the Title in the Manage class page
    Then Admin should see the "LMS - Learning Management System" Title

  @ManageClassHeader
  Scenario: Validating the Header in the Manage class page
    Then Admin should see the "Mange Class" Header

  @ValidateSearchBar
  Scenario: Validating Search bar in class page
    Then Admin should see the Searchbar in Manage class page

  @ValidateDataHeaders
  Scenario Outline: Validating the data table headers in the class page
    Then Admin should see the datatable heading like "Batch Name,Class Topic,Class Description,Status,Class Date,Staff Name,Edit/Delete"

  @ValidatingPaginationTextAndIcons
  Scenario: Verify paginator and enabled pagination controls
    Then Admin should see the "Showing \\d+ to \\d+ of \\d+ entries"  and enabled pagination controls under the data table

  @isSortIconPresentInHeaders
  Scenario: Validate the sort icon of all the field in datatable
    Then Admin should see the Sort icon of all the field in the datatable.

  @isMultiDeleteButtonVisible
  Scenario: Validating the Delete button under the Manage class
    Then Admin should see the Delete button under the Manage class page header.

  @isPaginatorTextVisibleOrNot
  Scenario: Validate the total no of classes in manage class page
    Then Admin should see Total no of classes in below of the data table.
