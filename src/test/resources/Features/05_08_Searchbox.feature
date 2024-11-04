@all
Feature: Manage Class Search Functionality

  Background: 
    Given Admin is on the Manage Class page after login

  @SearchFunctionality
  Scenario Outline: Search class by <Criteria>
    When Admin enters "<SearchValue>" in the search textbox
    Then Admin should see atleast one record

    Examples: 
      | Criteria    | SearchValue      |
      | Batch Name  | Micro service-01 |
      | Class Topic | testing          |
      | Staff Name  | Kevin Thomas     |
