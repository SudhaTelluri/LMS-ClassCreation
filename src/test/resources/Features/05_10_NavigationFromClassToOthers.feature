@NavigationFromClassModule
Feature: Navigation Validation from Manage Class to other Pages

@Classpage
  Scenario: Class link on navigation bar
    Given Admin is on Manage Class page for navigation check
    When Admin clicks on Class link on Manage Class page
    Then Admin is re-directed to class page

@OtherModule
  Scenario: Class link to other module on navigation bar
    Given Admin is on Manage Class page for navigation check
    When Admin clicks on any module link on Manage Class page
    Then Admin is re-directed to which module link they clicked.

@LogoutLink
  Scenario: Logout link on navigation bar
    Given Admin is on Manage Class page for navigation check
    When Admin clicks on Logout link on Manage class page
    Then Admin is re-directed to Login page
