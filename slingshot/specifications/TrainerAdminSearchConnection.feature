Feature: Successful and Unsuccessful Scenarios of Search Connection functionality to the application  
  As an trainer admin of the application
  I want to test Search connection feature
  
Background: Login:LoginAsTrainerAdmin


Scenario: To verify the Search Connection landing page with title
	Given I am ON dashboard page
	When I CLICK connectionLink 
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	

Scenario: To verify the result displayed for searched user is correct with DATA SearchConnectionList_Success
	Given I am ON dashboard page
	When I CLICK connectionLink 
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed or not
	Then I ENTER searchInputs in search field
	And I CLICK searchPodArrow icon
	Then I VERIFY correctUser displayed
	Then user is verified successfully 
	
	

Scenario: To verify that onclick of profile name should navigate to  correct view public profile page
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed or not
	Then I PERFORM getFirstConnectedName from the displayed
	And I CLICK firstConnectedUser from the list of users displayed
	Then I am ON userViewProfile page
	And I verify correctUserProfile displayed
	Then its verified successfully
	
	
	
Scenario: To verify the result displayed for Organization filter is correct with DATA UserConnectOrgFilter_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	Then I ENTER filterDetails in Organization field
	And I VERIFY userListDisplayed or not
	Then I VERIFY correctOrgDisplayed in result set
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
	And I PERFORM inputParticipantDetails to create a new user
	Then I store those user credentials for next scenarios
	
	

Scenario: To verify the result displayed for SortBy filter is correct with DATA UserConnectSortBy_Success
	Given I am ON dashboard page
	When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed or not
	Then I ENTER filterDetails related to status
	Then I VERIFY correctSortByResultDisplayed as per the selected option
	Then its verified successfully
	


Scenario: To verify that the results displayed for location filter is correct with DATA SearchConnectLocation_Success
    Given I am ON dashboard page
    When I CLICK connectionLink
	Then I am ON userConnection page
	And I CLICK searchConnection button
	Then I am ON searchForConnection page
	And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed or not
	Then I ENTER filterDetails related to location with DATA SearchConnectLocation_Success
    And I CLICK searchPodArrow icon
	And I VERIFY userListDisplayed
	And I VERIFY correctLocationDisplayed
	Then the result set is verified successfully	 



