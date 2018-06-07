Feature: To test all the feature of Manage Trainer Admins in Site Admin role
  As an user of the application
  I want to test various functionalities of Manage Admins in Site Admin role
  
Background: Login:LoginAsOrgAdmin,ManageSites:AddSiteSuccess,ManageSiteAdmin:LoginAsSiteAdmin

Scenario: To Verify the page-title of 'Manage Admins' page
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page

Scenario: To verify 'Manage Admin' link from bread crumb menu
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I CLICK dashboardBreadcrum link
    Then I am ON dashboard page

Scenario: Verify the error message while adding another admin without entering the required details for the currnt admin form with DATA AnotherAdminErrorMsg_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin link00
    Then I am ON addAdmin page
    And I CLICK addAnotherAdmin link
    Then a pop up is displayed
    Then I CLICK saveToAddAnotherAdmin button
    And I VERIFY addAnotherAdminErrorMessage with DATA AnotherAdminErrorMsg_Success
    Then I am ON addAdmin page

Scenario: To create trainer admin and verify details on manage admins of site admin page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifTrainerAdminCreated successfully
    Then I am ON manageAdmins page

Scenario: To verify if the created trainer admin is displayed in the trainer list on the dashboard of site admin with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifTrainerAdminCreated successfully
    Then I am ON manageAdmins page
    And I CLICK dashboardBreadcrum link
    Then I am ON dashboard page
    And I CLICK viewAllTrainers link
    Then I am ON manageAdmins page
    And I VERIFY ifTrainerAdminCreated successfully
    Then it is verified that the trainer list has the created trainer admin
    
Scenario: To verify the trainer admin details on View Public Profile page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    And I PERFORM trainerCreateStatus
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifTrainerAdminCreated successfully
    Then I am ON manageAdmins page
    When I PERFORM clickOnAdminName to view Admins Profile details
    Then I am ON userViewProfile page
    And I VERIFY adminDetailsOnPublicProfile
    Then I am ON userViewProfile page
    And I CLICK dashboardBreadcrum link
    Then I am ON dashboard page

Scenario: To login as a created trainer admin and verify the same with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifTrainerAdminCreated successfully
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
    When I VERIFY loggedInAsTrainer
    Then user is logged in as Trainer Admin

Scenario: To verify if the trainer admin created by by site admin is displayed in the organization Manage Admins page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM searchForCreatedAdmin
    Then I VERIFY ifTrainerAdminCreated successfully
    When I PERFORM logOut action
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I ENTER valid details with DATA LoginAsAdmin_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    Then I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I PERFORM searchForCreatedAdmin
    And I PERFORM ifAdminCreated
    
    
Scenario: To create a trainer admin, search for the created trainer admin using search functionality and verify the trainer details with DATA AddTrainerAdmin_Success
		Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM searchForCreatedAdmin
    Then I VERIFY ifTrainerAdminCreated successfully
    Then search functionality is verified

Scenario: To demote a trainer admin by organization which was created by site admin and check if the trainer was not displayed in manage admins page of site admin with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM searchForCreatedAdmin
    Then I VERIFY ifTrainerAdminCreated successfully
    When I PERFORM logOut action
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I ENTER valid details with DATA LoginAsAdmin_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    And I PERFORM searchForCreatedAdmin
    When I PERFORM demoteAnAdmin action, which removes an admin from the admins list
    Then I am ON manageAdmins page
    And I VERIFY isAdminRemoved
    Then I am ON manageAdmins page
    And added admin has been removed from the admins list
    When I PERFORM logOut action
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I PERFORM loginAsSiteAdmin
    Then I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    And I VERIFY ifTrainerIsDisplayedAfterRemove
    Then it is verified that trainer demoted by org admin is not displayed in the Manage admin list of Site Admin

Scenario: To change the privilage of the trainer admin created by site admin and check if the trainer admin is not displayed in the manag admin list of site admin role with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM searchForCreatedAdmin
    Then I VERIFY ifTrainerAdminCreated successfully
    And I PERFORM clickOnChangePrivilages link
    Then I am ON changePrivilages page
    And I ENTER change privilage details with DATA ChangePrivilageTrainer_Success
    Then I SUBMIT the form
    And I am ON manageAdmins page
    Then I PERFORM ifTrainerIsDisplayedAfterRemove
    And it is verified that the trainer is not displayed on manage admins page in site admin role
    
Scenario: To change the privilage of the trainer admin created by site admin and check if the trainer admin is not displayed in the manag admin list od site admin role with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I PERFORM trainerCreateStatus
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM searchForCreatedAdmin
    Then I VERIFY ifTrainerAdminCreated successfully
    And I PERFORM clickOnChangePrivilages link
    Then I am ON changePrivilages page
    And I ENTER change privilage details with DATA ChangePrivilageTrainer_Success
    Then I SUBMIT the form
    And I am ON manageAdmins page
    Then I PERFORM ifTrainerIsDisplayedAfterRemove
    And it is verified that the trainer is not displayed on manage admins page in site admin role
    When I PERFORM logOut action
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I ENTER valid organization details with DATA LoginAsAdmin_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    And I CLICK leftMenuButton link
     And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    And I VERIFY ifRemovedAdminHasSite
    