Feature: To check successful and unsuccessful Migrate User functionality by Org Admin in the application
  As an user of the application
  I want to test Add Participant Org Admin feature
  
  Background: Login:LoginAsOrgAdmin,SearchPods:SearchForPod,AddParticipant:AddUserToPodAndNavigateToDashboard,ManageSites:AddSiteSuccess,ManageSiteAdmin:CreateSiteAdmin

Scenario: To verify the user details of the added participant on the migrate user pop up with DATA InviteUser_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp 

Scenario: To perform success migration of a user from Default Online Site to the created site with DATA InviteUser_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp
  Then I PERFORM migrateUserToAnotherSite
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed
  Then it is verified user is migrated to the site
  
Scenario: To verify the migrated user is displayed in the manage user page of the site to which the user is migrated with DATA InviteUser_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp
  Then I PERFORM migrateUserToAnotherSite
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed
  And it is verified user is migrated to the site
  Then I PERFORM logOut action
  And I am ON home page
  Then I CLICK login button
  And I am ON login page
  Then I PERFORM loginAsAnAdmin to login as a site admin
  And I am ON changePassword page
  Then I ENTER valid details with DATA ChangePassword_Success
  And I SUBMIT the form
  Then I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page of site admin
  And it is verified that the user has been successfully migrated to another site and it is displayed in sites admins Manage User page

Scenario: To verify the migrated user is displayed in the participant list page of the pod in the site admin to which user is migrated with DATA InviteUser_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp
  Then I PERFORM migrateUserToAnotherSite
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed
  And it is verified user is migrated to the site
  Then I PERFORM logOut action
  And I am ON home page
  Then I CLICK login button
  And I am ON login page
  Then I PERFORM loginAsAnAdmin to login as a site admin
  And I am ON changePassword page
  Then I ENTER valid details with DATA ChangePassword_Success
  And I SUBMIT the form
  Then I am ON dashboard page
  When I CLICK leftMenuButton
  And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	Then the correct search pod result is displayed
	When I CLICK firstPod
	When I VERIFY podName
	Then the pod name is same as the first pod clicked
	And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	Then I am ON overview page
	When I CLICK addParticipant button
	And I am ON participantResultList page
  Then I PERFORM searchParticipantList
  And I VERIFY userDisplayedOnPage

Scenario: To verify if the user who is migrated is displayed when searched by a site name filter on participant list of the pod in org admin with DATA FilterEnrollSearch_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp
  Then I PERFORM migrateUserToAnotherSite
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed
  Then it is verified user is migrated to the site
  When I CLICK leftMenuButton
  And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	Then the correct search pod result is displayed
	When I CLICK firstPod
	When I VERIFY podName
	Then the pod name is same as the first pod clicked
	And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	Then I am ON overview page
	When I CLICK addParticipant button
	And I am ON participantResultList page
	And I PERFORM searchUserByFilter based upon site name with DATA FilterEnrollSearch_Success
  Then correct user is displayed on the page
  And I VERIFY userDisplayedOnPage
  
Scenario: To verify if the user who is migrated is displayed when searched by a site name filter on Manage user page of org admin with DATA FilterEnrollSearch_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton
  Then I CLICK manageUsers link
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed on Manage User page
  And I PERFORM clickMigrateUserlink of a user
  Then I am ON migrateUser page
  Then I VERIFY migratePopUpDisplayed
  And I VERIFY userDetailsOnMigratePopUp
  Then I PERFORM migrateUserToAnotherSite
  And I am ON manageUser page
  Then I VERIFY ifCorrectUserDisplayed
  When I CLICK leftMenuButton
  And I CLICK manageUsers link
  Then I am ON manageUser page
  And I PERFORM selectUserSiteFromFilter
  Then I VERIFY ifCorrectUserDisplayed
  And it is verified that user is displayed with the site selected from filter
  
