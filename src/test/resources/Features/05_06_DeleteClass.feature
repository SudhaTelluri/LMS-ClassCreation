@all
Feature: Delete Class

@validatingFieldsInDelete
Scenario: Validate row level delete icon
Given Admin is on Manage Class Page
When Admin clicks the delete icon
Then Admin should see a alert open with heading "Confirm" along with  <YES> and <NO> button for deletion

@validateYesInDelete
Scenario: Click Yes on deletion window
Given Admin is on Confirm Deletion alert
When Admin clicks yes option
Then Admin gets a message "Successful Class Deleted" alert and do not see that Class in the data table

@validateNoInDelete
Scenario: Click No on deletion window
Given Admin is on Confirm Deletion alert
When Admin clicks  No option
Then Admin can see the deletion alert disappears without deleting

@validatecloseInDelete
Scenario: Validate Close(X) icon on Confirm Deletion alert
Given Admin is on Confirm Deletion alert
When Admin clicks on close button
Then Admin can see the deletion alert disappears without any changes
