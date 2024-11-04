@all
Feature: Sorting Class Details

  Background: 
    Given Admin is on the Manage Class Page to verify sorting

@sortingVerification
  Scenario Outline: Verify class details are sorted by different columns
    When Admin clicks on the columnname "<columnName>"
    Then Admin should see the column sorted by columnname "<columnName>"

    Examples: 
      | columnName        |
      | Batch Name        |
      | Class Topic       |
      | Class Description |
      | Status            |
      | Class Date        |
      | Staff Name        |
