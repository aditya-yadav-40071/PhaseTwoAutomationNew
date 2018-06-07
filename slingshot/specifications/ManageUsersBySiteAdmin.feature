Feature: To test all the feature of Manage Users in site admin role
  As an user of the application
  I want to test Manage User feature

  Background: Login:LoginAsOrgAdmin,ManageSites:AddSiteSuccess,ManageSiteAdmin:LoginAsSiteAdmin,SearchPods:NavigateToPodOverview,AddParticipant:AddUserToPodSuccess

Scenario: To verify if the created user is displayed on the Manage User page with DATA InviteUser_Success
    Given I am ON participantResultList page
    And I CLICK leftMenuButton
    Then I CLICK manageUsers link
    Then I am ON manageUser page
    And I VERIFY ifCorrectUserDisplayed on Manage User page
    Then it is verified that user is displayed on the manage users page
    
Scenario: To verify search functionality and then check user details match on View Public Profile page with DATA InviteUser_Success
    Given I am ON participantResultList page
    And I CLICK leftMenuButton
    Then I CLICK manageUsers link
    Then I am ON manageUser page
    Then I PERFORM searchForUser
    Then user is displayed
    When I PERFORM clickOnUserName to view user Profile details
    Then I am ON userViewProfile page
    And I VERIFY userDetailsOnPublicProfile
    Then user dretails are verified on public profile

Scenario: To verify if the user is removed from the Manage User list of site admin and then verify if the status is removed for the user on participant list page of the pod with DATA PodSearch_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    When I PERFORM removeUserFromOrg which removes the user from the organization
    Then I am ON manageUser page
    When I PERFORM searchForUser
    Then I VERIFY ifUserIsRemoved from the list
    When I CLICK leftMenuButton
    And I CLICK searchPods link
    Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodSearch_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    And I CLICK firstPod
    Then I am ON overview page
    When I VERIFY podName
    Then the pod name is same as the first pod clicked
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY userRemovedStatus on page
    Then user status is removed on participant list page

Scenario: To verify if the user removed by site admin has removed status in organization participant list page of the pod with DATA PodSearch_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    When I PERFORM removeUserFromOrg which removes the user from the organization
    Then I am ON manageUser page
    When I PERFORM searchForUser
    Then I VERIFY ifUserIsRemoved from the list
    When I CLICK leftMenuButton
    And I CLICK searchPods link
    Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodSearch_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    And I CLICK firstPod
    Then I am ON overview page
    When I VERIFY podName
    Then the pod name is same as the first pod clicked
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY userRemovedStatus on page
    Then user status is removed on participant list page
    And I PERFORM logOut operation
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid organization details with DATA LoginAsAdmin_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK searchPods link
    Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodSearch_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    And I CLICK firstPod
    Then I am ON overview page
    When I VERIFY podName
    Then the pod name is same as the first pod clicked
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY userRemovedStatus on page
    Then user status is removed on participant list page

Scenario: To verify if the user is able to login when user is removed by site admin with DATA PodSearch_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    When I PERFORM removeUserFromOrg which removes the user from the organization
    Then I am ON manageUser page
    When I PERFORM searchForUser
    Then I VERIFY ifUserIsRemoved from the list
    And I PERFORM logOut operation
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    Then I PERFORM loginWithInvitedUser
    Then I am ON login page
    And I VERIFY ifUserNotAllowedToLogin after user is removed
    Then it is verified that user is not allowed to login after remove

Scenario: To edit the email of the created user and verify the edited users details on manage user page of site admin role with DATA EditUserEmail_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    Then I PERFORM clickToEditUserEmail to edit users email
    And I am ON editUserEmail page
    Then I ENTER valid new email with DATA EditUserEmail_Success
    And I SUBMIT the form
    Then I am ON manageUser
    When I PERFORM searchForUser
    Then user is displayed
    And I VERIFY ifCorrectUserDisplayed on Manage User page
    Then edited user details are verified

Scenario: To edit the email of the created user and verify if the users email is same as the edited value on View Public Profile page with DATA EditUserEmail_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    Then I PERFORM clickToEditUserEmail to edit users email
    And I am ON editUserEmail page
    And I VERIFY ifSameEmailIsDisplayed
    Then I ENTER valid new email with DATA EditUserEmail_Success
    And I SUBMIT the form
    Then I am ON manageUser
    When I PERFORM searchForUser
    Then user is displayed
    When I PERFORM clickOnUserName to view user Profile details
    Then I am ON userViewProfile page
    And I VERIFY userDetailsOnPublicProfile
    Then it is verified edited email id is displayed on public profile of the user

Scenario: To edit the email of the created user and verify if the user is able to login with the updated email id with DATA EditUserEmail_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    Then I PERFORM clickToEditUserEmail to edit users email
    And I am ON editUserEmail page
    Then I ENTER valid new email with DATA EditUserEmail_Success
    And I SUBMIT the form
    Then I am ON manageUser
    When I PERFORM searchForUser
    Then user is displayed
    And I VERIFY ifCorrectUserDisplayed on Manage User page
    Then I PERFORM logOut action
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    Then I PERFORM loginWithInvitedUser
    And I am ON changePassword page
    Then I ENTER valid password details with DATA ChangePassword_Success
    And I SUBMIT the form
    Then I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK viewPublicProfile link
    Then I am ON userViewProfile page
    And I VERIFY userDetailsOnPublicProfile
    Then user edited email is also displayed on users public profile page

Scenario: To edit the email of a user and verify the user details on the manage user page of the organization with DATA EditUserEmail_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    Then I PERFORM clickToEditUserEmail to edit users email
    And I am ON editUserEmail page
    Then I ENTER valid new email with DATA EditUserEmail_Success
    And I SUBMIT the form
    Then I am ON manageUser
    When I PERFORM searchForUser
    Then user is displayed
    And I VERIFY ifCorrectUserDisplayed on Manage User page
    Then I PERFORM logOut action
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid login details of organization with DATA LoginAsAdmin_Success
    When I SUBMIT the login form
    Then I am ON dashboard page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    And I VERIFY ifCorrectUserDisplayed on Manage User page

Scenario: To edit the email of the created user and verify if the user with same email is displayed in the participant list of the pod in site admin role with DATA EditUserEmail_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    Then I PERFORM clickToEditUserEmail to edit users email
    And I am ON editUserEmail page
    And I VERIFY ifSameEmailIsDisplayed
    Then I ENTER valid new email with DATA EditUserEmail_Success
    And I SUBMIT the form
    Then I am ON manageUser
    When I PERFORM searchForUser
    Then user is displayed
    And I CLICK leftMenuButton
    And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	And I CLICK firstPod
	Then I am ON overview page
	When I VERIFY podName
	Then the pod name is same as the first pod clicked
	And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	Then I am ON overview page
	And I CLICK addParticipant button
	Then I am ON participantResultList page
	And I PERFORM searchParticipantList
  Then I VERIFY userDisplayedOnPage
  And it is verified that edited email user is displayed correctly

Scenario: To verify if the user removed by Site Admin has status removed in Sub Admin participant list page with DATA PodSearch_Success
    Given I am ON participantResultList page
    When I CLICK leftMenuButton
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    When I PERFORM removeUserFromOrg which removes the user from the organization
    Then I am ON manageUser page
    When I PERFORM searchForUser
    Then I VERIFY ifUserIsRemoved from the manage user list
    And I PERFORM logOut operation
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid organization details with DATA LoginAsAdmin_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    Then I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSubAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page
    And I PERFORM logOut operation
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I PERFORM loginAsAnAdmin
    And I am ON changePassword page
    Then I ENTER valid password details with DATA ChangePassword_Success
    And I SUBMIT the form
    Then I am ON dashboard page
	  When I CLICK leftMenuButton
	  And I CLICK searchPods link
	  Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodSearch_Success
   	Then I CLICK searchPodArrow 
  	And I VERIFY correctPodsDisplayed
  	And I CLICK firstPod
	  Then I am ON overview page
    And I VERIFY podName
  	Then the pod name is same as the first pod clicked
	  And I VERIFY forumAndEnterClsRmButtonDisplayed or not
  	Then I am ON overview page
  	When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY userRemovedStatus on page
    Then user status is removed on participant list page
    