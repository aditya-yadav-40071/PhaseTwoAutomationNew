Feature: Successful organization registration of an organization admin to the application
As an Organization admin of the application
I want to test Organization registration feature

Scenario: To Verify successful organization registration to application with DATA Organisation_Reg_Success
    Given I am ON home page
    And I CLICK registerFrmPage
    Then I am ON bifurcateUser page
    When I CLICK organizationLnk link
    And I CLICK nextBtn
    Then I am ON companyRegistration page
    And I ENTER registerForm details
    Then I PERFORM getOTP 
    And I ENTER otpForm of the page with DATA Organisation_Otp_Success
    When I SUBMIT registerForm
    And I VERIFY isEmailIDVerified using db connection 
    Then I am ON login page
    
