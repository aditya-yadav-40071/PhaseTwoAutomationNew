Feature: To test all the feature of Manage HR Admins page
  As an user of the application
  I want to test various functionalities of Manage HR Admins page

  Background: Login:LoginAsOrgAdmin

  Scenario: To create HR admin admin and verify details on manage admins page with DATA AddJobAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddJobAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page

  Scenario: To verify the HR admin details on View Public Profile page with DATA AddJobAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddJobAdmin_Success
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

  Scenario: To login as a created HR admin and verify the same with DATA AddJobAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddJobAdmin_Success
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
    When I VERIFY loggedInAsJobAdmin
    Then user is logged in as HR admin
    
Scenario: To create a HR admin, search for the created HR admin using search functionality and verify the HR admin details with DATA AddJobAdmin_Success
		Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddJobAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM searchForCreatedAdmin
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    Then I VERIFY ifAdminCreated successfully
    Then search functionality is verified

  Scenario: To select the the HR admin filter and verify if all the admins displayed are HR admin with DATA HRAdminFilter_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddJobAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM selectAdminFilter with DATA HRAdminFilter_Success
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY allDisplayedAdminRoles are HR admin 
    Then HR admin filter is verified

Scenario: To demote an added admin and verify that admin has been demoted from the list  in 'Manage Admins' page with DATA AddJobAdmin_Success
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
