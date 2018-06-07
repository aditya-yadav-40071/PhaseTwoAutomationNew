Feature: Successful and Unsuccessful forgot password scenarios and links on forgot password page
As a user of the application
I want to test the forgot password feature

Background: Login:ForgotPassword

Scenario: To Verify failure scenarios of forgot password with DATA ForgotPassword_Failure
    Given I am ON forgotPassword page
    And I ENTER invalid inputs
    When I SUBMIT the form
    Then I am ON forgotPassword page
    And error messages are being displayed

Scenario: To Verify the success scenarios of forgot password through mobile number and email with DATA ForgotPassword_Success
    Given I am ON forgotPassword page
    And I ENTER invalid inputs
    When I SUBMIT the form
    Then I am ON forgotPassword page
    And success message 'Reset password link sent successfully' is displayed

Scenario: To verify 'Go To Login Page' link on forgot password page
    Given I am ON forgotPassword page
    When I CLICK goToLoginPage link on the Page
    Then I am ON login page

Scenario: To verify 'Privacy Policy' link on forgot password page
    Given I am ON forgotPassword page
    When I CLICK privacyPolicy link on the Page
    Then I am ON privacyPolicy page

Scenario: To verify 'Terms of Use' link on forgot password page
    Given I am ON forgotPassword page
    When I CLICK termsOfUse link on the Page
    Then I am ON termsOfUse page

Scenario: To verify brand image link on forgot password page
    Given I am ON forgotPassword page
    When I CLICK brandImageLink on the Page
    Then I am ON home page
