Feature: To test all the feature of Manage Trainer Admins page
  As an user of the application
  I want to test various functionalities of Manage Admins page

  Background: Login:LoginAsOrgAdmin

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
    When I CLICK addAdmin link
    Then I am ON addAdmin page
    And I CLICK addAnotherAdmin link
    Then a pop up is displayed
    Then I CLICK saveToAddAnotherAdmin button
    And I VERIFY addAnotherAdminErrorMessage with DATA AnotherAdminErrorMsg_Success
    Then I am ON addAdmin page

Scenario: To verify all failure scenarios of add admin page with DATA AddAdmin_Failure
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin link
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddAdmin_Failure
    When I SUBMIT the form
    Then error messages are displayed on the page
 
Scenario: To create trainer admin and verify details on manage admins page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page

Scenario: To verify the trainer admin details on View Public Profile page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
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

Scenario: To login as a created trainer admin and verify the same with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
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
    When I VERIFY loggedInAsTrainer
    Then user is logged in as Trainer Admin

Scenario: To create a trainer admin, search for the created trainer admin using search functionality and verify the trainer details with DATA AddTrainerAdmin_Success
		Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM searchForCreatedAdmin
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    Then I VERIFY ifAdminCreated successfully
    Then search functionality is verified

Scenario: To select the the trainer filter and verify if all the admins displayed are trainers with DATA TrainerAdminFilter_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I PERFORM selectAdminFilter with DATA TrainerAdminFilter_Success
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY allDisplayedAdminRoles are trainers 
    Then trainer filter is verified

Scenario: To demote an added admin and verify that admin has been demoted from the list  in 'Manage Admins' page with DATA AddTrainerAdmin_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin link
    Then I am ON addAdmin page
    And I ENTER all the required fields
    When I SUBMIT the form
    Then I am ON manageAdmins page
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

Scenario: To change the privilage of a trainer admin to all other admin and check if the privilages are changed with DATA AddTrainerAdmin_Success
		Given I am ON dashboard page
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin link
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    And I PERFORM clickAdminChangePrivilageLink
    Then I am ON changePrivilages page
    And I ENTER admin privilage details with DATA ChangePrivilages_Success
		Then I SUBMIT the form
		And I am ON manageAdmins page
		And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
