@Pagination
Feature: Pagination

@VerifyNext
  Scenario: Verify Next page link(>)
    Given Admin is on Manage class page to check pagination
    When Admin clicks Next page link on the class table
    Then Admin should see the next page record on the table  with Pagination has next active link enabled
@VerifyLast
  Scenario: Admin is on Manage class page
    Given Admin is on Manage class page to check pagination
    When Admin clicks Last page link
    Then Admin should see the last page record on the table with Next page link are disabled
@VerifyPrevious
  Scenario: Verify First page link(<)
    Given Admin is on last page of class table
    When Admin clicks First page link
    Then Admin should see the previous page record on the table with pagination has previous page link enabled
@VerifyFirst
  Scenario: Verify Start page link(<<)
    Given Admin is on Previous class page
    When Admin clicks Start page link
    Then Admin should see the very first page record on the table with Previous page link are disabled
