Feature: Login Page Verification

   @validLogin
  Scenario Outline: Validate login with valid credentials
    Given Admin is in login Page
    When Admin enter valid credentials using "<TestCaseName>"  and clicks login button
    Then Admin should land on dashboard page ( centre of the page will be empty , menu bar is present).

    Examples: 
      | TestCaseName |
      | ValidLogin   |

 