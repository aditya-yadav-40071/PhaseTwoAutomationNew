Feature: Successful and Unsuccessful footer links functionality to the application
  As an user of the application
  I want to test Footer Links

  Scenario: To verify after successful login it is navigating to Dashboard page with DATA LoginAsAllUsers_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details
    When I SUBMIT the form
    Then I am ON dashboard page

  Scenario: To verify after successful login it is navigating to Dashboard page with DATA LoginAsAllUsers_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details
    When I SUBMIT the form
    Then I am ON dashboard page
    When I CLICK termsOfUse link
    Then I am ON termsOfUse page
    And I CLICK dashBoardLink to come back to home page
    Then I am ON dashboard page
    When I CLICK privacyPolicy link
    Then I am ON privacyPolicy page
    And I CLICK dashBoardLink to come back to home page
    Then I am ON dashboard page
    When I CLICK aboutUs link
    Then I am ON aboutUs page
    And I CLICK dashBoardLink to come back to home page
    Then I am ON dashboard page
    When I CLICK contactUs link
    Then I am ON contactUs page
    And I CLICK dashBoardLink to come back to home page
    Then I am ON dashboard page

