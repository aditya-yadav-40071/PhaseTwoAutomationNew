Feature: Successful and Unsuccessful Login to the application
  As an user of the application
  I want to test Login feature

Scenario: To Verify landing on login page
    Given I am ON home page
    When I CLICK login link
    Then I am ON login page

Scenario: To verify the page-title of Contact Support page while navigating from header link
    Given I am ON home page
    When I CLICK login link
    When I CLICK contactSupportHeader button
    Then I am ON contactSupport page

Scenario: To verify the error messages with wrong login credentials with DATA Login_Failure
    Given I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER invalid login details
    And I SUBMIT the form
    Then I am ON login page

@Name(loginSuccess)
Scenario: To verify the successful login to the application with DATA Login_Success
    Given I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid login details
    And I SUBMIT the form
    Then I am ON dashboard page

Scenario: To verify the user after logging in with the credentials with DATA Login_Success
    Given I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid login details
    And I SUBMIT the form
    Then I am ON dashboard page
    And I CLICK editOrganizationProfile link
    Then I am ON editOrganizationProfile page
    And I VERIFY correctUserLoggedIn
    Then the logged in user is the one displayed on dashboard

@Name(loginAsEnrolledUserSuccess)   
Scenario: To login to the application as an enrolled user with DATA Login_Success
    Given I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I PERFORM loginWithEnroledUser
    Then I am ON dashboard page

@Name(ForgotPassword)
Scenario: To Verify forgotPassword Link on Login Page
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    When I CLICK forgotPassword link on the Page
    Then I am ON forgotPassword page

@Name(LoginAsAdmin)
Scenario: To login to the application as an admin with DATA LoginAsOrgAdmin
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsSalesAdmin)
Scenario: To login to the application as a sales admin with DATA LoginAsSalesAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsSubAdmin)
Scenario: To login to the application as a Sub-Admin with DATA LoginAsSubAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details with DATA LoginAsSubAdmin_Success
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsOrgAdmin)
Scenario: To login to the application as a organization admin with DATA LoginAsAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details with DATA LoginAsAdmin_Success
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsTrainerAdmin)
Scenario: To login to the application as a Sub-Admin with DATA LoginAsTrainerAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details with DATA LoginAsTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsHRAdmin)
Scenario: To login to the application as a HR Admin with DATA LoginAsHRAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details with DATA LoginAsHRAdmin_Success
    When I SUBMIT the form
    Then I am ON dashboard page

@Name(LoginAsSiteAdmin)
Scenario: To login to the application as a HR Admin with DATA LoginAsSiteAdmin_Success
    Given I am ON home page
    And I CLICK login
    Then I am ON login page
    And I ENTER the details with DATA LoginAsSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON dashboard page
