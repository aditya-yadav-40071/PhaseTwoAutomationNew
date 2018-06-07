Feature: To test all the feature of Manage Site Admins page
  As an user of the application
  I want to test various functionalities of Manage Site Admin page

  Background: Login:LoginAsOrgAdmin,ManageSites:AddSiteSuccess

@Name(CreateSiteAdmin)
Scenario: To create Site Admin and verify details on manage admins page with DATA AddSiteAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page
    And I CLICK dashboardBreadcrum link
    Then I am ON dashboard page

Scenario: To verify the Site Admin on View Public Profile page with DATA AddSiteAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page
    When I PERFORM clickOnAdminName to view Admins Profile details
    Then I am ON userViewProfile page
    And I VERIFY adminDetailsOnPublicProfile
    Then I am ON userViewProfile page
    And I CLICK dashboardBreadcrum link
    Then I am ON dashboard page

@Name(LoginAsSiteAdmin)
Scenario: To login as a created Site Admin and verify the same with DATA AddSiteAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page
    When I PERFORM logOut action
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    When I PERFORM loginAsAnAdmin
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    When I VERIFY loggedInAsSiteAdmin
    Then user is logged in as Site Admin 
 
Scenario: To create a Site Admin, search for the created Site Admin using search functionality and verify the Site Admin details with DATA AddSiteAdmin_Success
		Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM searchForCreatedAdmin
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    Then I VERIFY ifAdminCreated successfully
    Then search functionality is verified

Scenario: To select the the Site Admin filter and verify if all the admins displayed are Site Admin with DATA SiteAdminFilter_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSiteAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM selectAdminFilter with DATA SiteAdminFilter_Success
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY allDisplayedAdminRoles are Site Admin 
    Then Site Admin filter is verified
    
Scenario: To demote an added admin and verify that admin has been demoted from the list  in 'Manage Admins' page with DATA AddSiteAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin link
    Then I am ON addAdmin page
    And I ENTER all the required fields
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    When I PERFORM demoteAnAdmin action, which removes an admin from the admins list
    Then I am ON manageAdmins page
    And I VERIFY isAdminRemoved
    Then I am ON manageAdmins page
    And added admin has been removed from the admins list
    And I CLICK leftMenuButton
    And I CLICK manageUsers link 
    Then I am ON manageUser page
    And I VERIFY usersDisplayed on manage user page
    Then admin demoted becomes a user
