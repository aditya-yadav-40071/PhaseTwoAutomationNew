Feature: To check successful and unsuccessful Add Participant functionality by Org Admin in the application
  As an user of the application
  I want to test Add Participant Org Admin feature

Background: Login:LoginAsOrgAdmin,SearchPods:SearchForPod

Scenario: To Verify the page-title of 'Participant List' page
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page

Scenario: To Verify the page-title of 'Add User' page
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    And I CLICK inviteLearner button
    Then I am ON inviteUser page

Scenario: To Verify the page-title after navigating to previous page from 'Participant List' page
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    And I CLICK inviteLearnerBack button to go back to the previous page
    Then I am ON overview page of the pod

@Name(AddUserToPodSuccess)
Scenario: To verify successful scenario for adding and enrolling a user to a pod with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY ifLicenceAvailable to enroll user to pod
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY userDisplayedOnPage
    Then correct user is displayed on the page
    And I VERIFY ifUserIsEnrolled to the pod
    Then it is verified that user is enrolled to the pod
    And I am ON participantResultList page
    
 @Name(AddUserToPodAndNavigateToDashboard)
Scenario: To verify successful scenario for adding and enrolling a user to a pod with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY ifLicenceAvailable to enroll user to pod
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY userDisplayedOnPage
    Then correct user is displayed on the page
    And I VERIFY ifUserIsEnrolled to the pod
    Then it is verified that user is enrolled to the pod
    And I am ON participantResultList page
    When I CLICK dashboardBreadcrum link
    Then I am ON dashboard page

Scenario: To verify successful scenario for adding, enrolling and login with the created user with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I CLICK leftMenuButton icon
    And I CLICK logoutLink from menu options
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I PERFORM loginWithInvitedUser
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
    Then I SUBMIT the form
    Then I am ON dashboard page
    And I VERIFY correctUserLoggedIn
    Then it is verified that user is logged in with correct credentials

Scenario: To verify the pod name to which user was enrolled is displayed in ongoing pods of the enrolled user with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I CLICK leftMenuButton icon
    And I CLICK logoutLink from menu options
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I PERFORM loginWithInvitedUser
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
    Then I SUBMIT the form
    Then I am ON dashboard page
    And I VERIFY correctUserLoggedIn
    When I CLICK dashBoardLink 
    Then I am ON dashboard
    Then I CLICK viewAllOngoingPods
    And I am ON ongoingPods page
    And I ENTER searchInputs with DATA PodSearch_Success
    Then I VERIFY podsDisplayed
    And I VERIFY correctPodsDisplayed with DATA PodSearch_Success
    Then it is verified that the user has correct pod in ongoing trainings 

Scenario: To verify successful scenario for searching the enrolled user using filters with DATA FilterEnrollSearch_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I PERFORM searchUserByFilter based upon site name
    Then correct user is displayed on the page
    And I am ON participantResultList page

Scenario: To verify successful scenario for searching the enrolled user using filters with DATA FilterUnEnrollSearch_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I PERFORM searchUserByFilter based upon site name
    Then correct user is displayed on the page
    And I am ON participantResultList page

Scenario: To verify sorting of participant names in A to Z order in 'Participant List' page with DATA ListSorted_AtoZ
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY learnerListSorted alphabatically

Scenario: To verify sorting of participant names in Z to A order in 'Participant List' page with DATA ListSorted_ZtoA
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY learnerListSorted in decending order

Scenario: To verify enroling of student by using Enroll button in 'Participant List' page with DATA EnrollUserToOtherPod_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK ongoingBreadcrumb
    And I am ON searchPods page
    And I ENTER searchInputs for search with DATA EnrollUserToOtherPod_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    And I CLICK firstPod
    Then I am ON overview page
    And I VERIFY podName
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I PERFORM enrollUserToAnotherPod on participant list page
    Then I am ON overview page of the pod
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY ifUserIsEnrolled to the pod
    Then user is enrolled to the pod

Scenario: To verify if the created user is displayed on the manage users page of org admin with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK leftMenuButton
    And I CLICK manageUsers to go to manage users page
    Then I am ON manageUser page
    And I VERIFY ifCorrectUserDisplayed
    Then it is verified that created user was displayed in manage users page

Scenario: To enroll a user to a pod and then create a Sub Admin with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddSubAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page

Scenario: To verify if the user created by org admin is displayed in the participant list of the pod in Sub Admin with DATA InviteUser_Success
    Given I am ON overview page of the pod
    Then I CLICK leftMenuButton
    And I CLICK logoutLink
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I PERFORM loginAsAnAdmin
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
    Then I SUBMIT the form
    And I am ON dashboard page
    And I CLICK viewAllOngoing
    Then I am ON ongoingTraining page
    When I PERFORM searchOngoingTrainings with DATA PodSearch_Success
    And I VERIFY correctPodsDisplayed with DATA PodSearch_Success
    Then I CLICK searchPodArrow 
	  And I VERIFY correctPodsDisplayed
	  And I CLICK firstPod
	  Then I am ON overview page
  	When I VERIFY podName
  	Then the pod name is same as the first pod clicked
	  And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	  Then I CLICK addParticipant button
	  And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY userDisplayedOnPage
    Then correct user is displayed on the page
    And I VERIFY ifUserIsEnrolled to the pod
    Then it is verified that the user created by org admin is displayed in Sub Admin pod participant list page


Scenario: To create a site, assign site admin to the site by creating a site admin with DATA AddSiteAdmin_Success
    Given I am ON overview page of the pod
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY correctSiteDisplayed
    Then I am ON manageSites page
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

Scenario: To verify if the user created by org admin is displayed in the participant list of the pod in Site Admin with DATA InviteUser_Success   
    Given I am ON overview page of the pod
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I CLICK leftMenuButton
    And I CLICK logoutLink
    Then I am ON home page
    And I CLICK login button
    Then I am ON login page
    And I PERFORM loginAsAnAdmin
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
    Then I SUBMIT the form
     And I am ON dashboard page
    And I CLICK viewAllOngoing
    Then I am ON ongoingTraining page
    When I PERFORM searchOngoingTrainings with DATA PodSearch_Success
    And I VERIFY correctPodsDisplayed with DATA PodSearch_Success
	  And I CLICK firstPod
	  Then I am ON overview page
  	When I VERIFY podName
  	Then the pod name is same as the first pod clicked
	  And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	  Then I CLICK addParticipant button
	  And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY userDisplayedOnPage
    Then correct user is displayed on the page
    And I VERIFY ifUserIsEnrolled to the pod
    Then it is verified that the user created by org admin is displayed in Sub Admin pod participant list page

Scenario: To verify the navigation between overview and participant list tabs on overview page of the pod
     Given I am ON overview page of the pod
     When I CLICK addParticipantTab
     Then I am ON participantListTab page
 
Scenario: To verify if the created user is displayed in the participant list tab on the overview page of the pod with DATA InviteUser_Success
    Given I am ON overview page of the pod
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK podNameBreadcrumb
    And I am ON overview page of the pod
    When I CLICK addParticipantTab
    Then I am ON participantListTab page
    And I VERIFY ifOngoingTabIsHighlighted
    Then I PERFORM searchForParticipant
    And I VERIFY participantDisplayedOnPage
    Then it is verified that the user is displayed in participant list tab

Scenario: To verify if the created user is displayed in participant list tab on the overview page of the pod using site filter with DATA InviteUser_Success
    Given I am ON overview page of the pod
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK podNameBreadcrumb
    And I am ON overview page of the pod
    When I CLICK addParticipantTab
    Then I am ON participantListTab page
    And I VERIFY ifOngoingTabIsHighlighted
    Then I PERFORM searchForParticipant
    Then I PERFORM selectSiteFilter
    And I VERIFY participantDisplayedOnPage
    Then it is verified that site filter filters the results according to site name

Scenario: To verify that the newly created user has pod progress as zero percent in participant list tab on the overview page of the pod using site filter with DATA InviteUser_Success
    Given I am ON overview page of the pod
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY ifUserIsEnrolled to the pod
    Then I CLICK podNameBreadcrumb
    And I am ON overview page of the pod
    When I CLICK addParticipantTab
    Then I am ON participantListTab page
    And I VERIFY ifOngoingTabIsHighlighted
    Then I PERFORM searchForParticipant
    And I VERIFY participantDisplayedOnPage
    Then I VERIFY podProgressPercentageIsZero for newlt created user
    Then it is verified that the newly created user has zero as pod completion percentage

Scenario: To verify sorting of participant names in A to Z order in 'Participant List Tab' page with DATA ListSorted_AtoZ
    Given I am ON overview page of the pod
    When I CLICK addParticipantTab
    Then I am ON participantListTab page
    And I VERIFY ifOngoingTabIsHighlighted
    Then I VERIFY participantTabListSorted in A-Z order
    And it is verified that the list is sorted in A-Z order
    
Scenario: To verify sorting of participant names in Z to A order in 'Participant List Tab' page with DATA ListSorted_ZtoA
    Given I am ON overview page of the pod
    When I CLICK addParticipantTab
    Then I am ON participantListTab page
    And I VERIFY ifOngoingTabIsHighlighted
    Then I VERIFY participantTabListSorted in Z-A order
    And it is verified that the list is sorted in Z-A order