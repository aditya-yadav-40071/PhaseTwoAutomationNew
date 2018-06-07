Feature: Successful and Unsuccessful Change password functionality to the application
As an Individual User of the application
I want to test Change Password feature
 
Background: Login:loginAsIndividualUserSuccess


Scenario: To Verify the page title for change password
	Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK changePassword link
	Then I am ON changePassword page


Scenario: To verify the invalid scenarios for change password with DATA ChangePassword_Failure
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER invalid password details
	Then I SUBMIT the form
	And the error messages are displayed
	

Scenario: To verify the valid scenario for change password with DATA ChangePassword_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER valid password details
	Then I SUBMIT the form
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER valid password details with DATA ChangePasswordRevert_Success
	Then I SUBMIT the form
	Then I am ON dashboard page
	
	
Scenario: To verify the user should not be able to login with old password with DATA ChangePassword_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER valid password details
	Then I SUBMIT the form
	Then I am ON dashboard page	
	Then I CLICK leftMenuButton
	And I CLICK logoutLink 
	Then I am ON home page
	And I CLICK login button
	Then I am ON login page
	When I ENTER old user credentials with DATA LoginAsIndividualUser_Success
	Then I CLICK signIn button 
	Then I am ON login page
	When I ENTER valid credentials with DATA LoginAfterIUsrChangePwd_Success
	And I CLICK signIn button
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER valid password details with DATA ChangePasswordRevert_Success
	Then I SUBMIT the form
	Then I am ON dashboard page
	
	
	