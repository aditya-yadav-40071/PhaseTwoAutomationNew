Feature: Successful and Unsuccessful Scenarios of User Connection functionality to the application  
  As an user of the application
  I want to test User connection feature
  
Background: Login:loginAsIndividualUserSuccess

Scenario: To verify the User Connection landing page with title
	Given I am ON dashboard page
	When I CLICK connectionLink 
	Then I am ON userConnection page
	
Scenario: To verify that clicking on search button should redirect to search for connection page
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page

Scenario: To verify the result displayed for searched user is correct with DATA UserConnectionSearch_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then the correct search result is displayed

Scenario: To verify that onclick of profile name should navigate to view public profile page
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then I PERFORM getFirstConnectedName from the displayed
	And I CLICK firstConnectedUser from the list of users displayed
	Then I am ON userViewProfile page
	And I verify correctUserProfile displayed
	Then its verified successfully
	
Scenario: To verify search user functionality with DATA UserConnectionSearch_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then I ENTER searchInputs in search field
	And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed or not
	Then I VERIFY correctUserDisplayed in list
	Then its verified successfully
	
Scenario: To verify the result displayed for Organization filter is correct with DATA UserConnectOrgFilter_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed 
	Then I ENTER filterDetails in Organization field
	And I VERIFY userListDisplayed 
	Then I VERIFY correctOrgDisplayed in result set
	Then its verified successfully
	
	
Scenario: To verify sending request to the first connected user after the default search
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	Then I PERFORM getFirstConnectedName from the displayed list
	And I CLICK firstConnectLink from the list
	Then I VERIFY successMsgOnConnect 
	Then the success message is displayed
	
Scenario: To verify the success message for blocking a user from user connection page
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then I PERFORM getUserToBeBlocked from my connection list
	Then I VERIFY successMsgOnBlock of a user
	Then its verified successfully


Scenario: To create a new enroll user and get the name of that user with DATA LoginAsAdmin_Success
	Given I am ON dashboard page
	And I CLICK userEditProfile link 
	Then I am ON editProfile page
	And I PERFORM getProfileNameAndEmailId 
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	Then I am ON home page
	And I CLICK login
	Then I am ON login page
	And I ENTER the details with DATA LoginAsAdmin_Success
	When I SUBMIT the form
	Then I am ON dashboard page of Organization admin
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I PERFORM inputPodName in search field
	And I CLICK searchPodArrow
	Then I VERIFY podsDisplayed or not
	When I CLICK firstPod
	Then I am ON overview page
	And I CLICK addParticipant button
	Then I am ON participantResultList page
	And I CLICK inviteUser button
	Then I am ON inviteUserPage
	And I ENTER user details to create a new user with DATA InviteUser_Success
	Then I SUBMIT the invite user form
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	Then I am ON home page
	And I CLICK login
	Then I am ON login page
	And I ENTER credentials of invited user in signin page with DATA LoginAsIndividualUser_Success
	When I SUBMIT the form
	Then I am ON changePassword page
	And I ENTER new credentials for changing password with DATA ChangePassword_Success
	When I SUBMIT the form
	Then I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	Then I am ON searchForConnection page
	And I PERFORM searchForInputUser from search field
	And I CLICK searchPodArrow icon
	Then I PERFORM getFirstRequestingName with emailId from the displayed list
	Then the name and email saved successfully
	
	

Scenario: To verify the result displayed for Status filter is correct with DATA UserConnectStatus_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then I ENTER filterDetails related to status
	Then I VERIFY correctStatusResultDisplayed as per the selected option
	Then its verified successfully
	


Scenario: To verify the result displayed for SortBy filter is correct with DATA UserConnectSortBy_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I VERIFY userListDisplayed or not
	Then I ENTER filterDetails related to status
	Then I VERIFY correctSortByResultDisplayed as per the selected option
	Then its verified successfully
