Feature: Successful scenarios to the application
As an Individual user of the application
I want to perform Smoke Test 


Scenario: To Verify successful organization registration to application with DATA IndividualRegister_Success
    Given I am ON home page
    And I CLICK registerFrmPage
    Then I am ON bifurcateUser page
    When I CLICK individualRegister link
    And I CLICK nextBtn
    Then I am ON individaulRegister page
    And I ENTER registerForm details
    Then I PERFORM getOTP 
    And I ENTER otpForm of the page with DATA IndividualUserOTP_Success
    When I SUBMIT registerForm 
    Then I am ON login page

    
	
Scenario: To verify the successful login to the application with DATA LoginAsIndividualUser_Success
	Given I am ON home page
	When I CLICK login link
	Then I am ON login page
	And I ENTER valid login details
	And I SUBMIT the form
	Then I am ON dashboard page
	
	
	
	
  
    
