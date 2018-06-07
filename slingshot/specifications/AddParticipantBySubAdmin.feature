Feature: To check successful and unsuccessful Add Participant functionality by Sub Admin in the application
  As an user of the application
  I want to test Add Participant by Sub Admin feature

  Background: Login:LoginAsOrgAdmin,SearchPods:NavigateToDashFromPodOverview,ManageSubAdmin:CreateSubAdminAndLogin,SearchPods:SearchForPod,AddParticipant:AddUserToPodSuccess

  Scenario: To verify success scenario for login with the user created by sub admin with DATA ChangePassword_Success
    Given I am ON participantResultList page
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

  Scenario: To verify search functionality for enrolled user using filter with DATA FilterEnrollSearch_Success
    Given I am ON participantResultList page
    When I PERFORM searchUserByFilter to search enrolled user using filter with DATA FilterEnrollSearch_Success
    Then I VERIFY userDisplayedOnPage
    And correct user is displayed on the page 
    Then I am ON participantResultList page

  Scenario: To verify if the user created by sub admin has same pod in ongoing pods with DATA PodSearch_Success
    Given I am ON participantResultList page
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
    And I CLICK dashBoardLink
    Then I am ON dashboard page
    And I CLICK viewAllOngoingPods
    Then I am ON ongoingPods page
    And I ENTER searchInputs with DATA PodSearch_Success
    Then I VERIFY correctPodsDisplayed
    And it is verified that user is enrolled to correct pod

  Scenario: To verify if the user enrolled to pod by sub admin is displayed in Manage user page of organization with DATA InviteUser_Success
    Given I am ON participantResultList page
    Then I VERIFY ifUserIsEnrolled to the pod
    And I PERFORM searchUserByFilter based upon site name with DATA FilterEnrollSearch_Success
    Then correct user is displayed on the page
    And I am ON participantResultList page
    Then I CLICK leftMenuButton icon
    And I CLICK logoutLink from menu options
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER the details with DATA LoginAsAdmin_Success
    When I SUBMIT the form
    And I am ON dashboard page logged in as org admin
    Then I CLICK leftMenuButton icon
    And I CLICK manageUsers link
    Then I am ON manageUser page
    And I PERFORM searchForUser
    Then I VERIFY ifCorrectUserDisplayed
    And it is verified that users creaed by sub admin are displayed in org admin manage user page

  Scenario: To verify if the user enrolled by sub admin is displayed in the participant list page a pod in org admin with DATA InviteUser_Success
    Given I am ON participantResultList page
    Then I VERIFY ifUserIsEnrolled to the pod
    And I PERFORM searchUserByFilter based upon site name with DATA FilterEnrollSearch_Success
    Then correct user is displayed on the page
    And I am ON participantResultList page
    Then I CLICK leftMenuButton icon
    And I CLICK logoutLink from menu options
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER the details with DATA LoginAsAdmin_Success
    When I SUBMIT the form
    And I am ON dashboard page logged in as org admin
    Then I CLICK leftMenuButton icon
    And I CLICK searchPods link
    Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodSearch_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    Then the correct search pod result is displayed
    When I CLICK firstPod
    Then I am ON overview page
    And I CLICK addParticipant button
    Then I am ON participantResultList page
    And I PERFORM ifUserIsEnrolled
    Then correct user is displayed on the page
    And I am ON participantResultList page

Scenario: To verify if the user created by sub admin is removed by the org admin and status of the user is removed in participant list page of the pod in Sub Admin with DATA InviteUser_Success
    Given I am ON participantResultList page
    Then I VERIFY ifUserIsEnrolled to the pod
    Then correct user is displayed on the page
    And I am ON participantResultList page
    Then I CLICK leftMenuButton icon
    And I CLICK logoutLink from menu options
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER the details with DATA LoginAsAdmin_Success
    When I SUBMIT the form
    And I am ON dashboard page logged in as org admin
    Then I CLICK leftMenuButton icon
    Then I CLICK manageUsers link
    And I am ON manageUser page
    Then I PERFORM searchForUser
    And user is displayed
    When I PERFORM removeUserFromOrg which removes the user from the organization
    Then I am ON manageUser page
    When I PERFORM searchForUser
    Then I VERIFY ifUserIsRemoved from the list
    When I CLICK leftMenuButton
    And I CLICK logoutLink
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I PERFORM loginAsAnAdmin
    Then I am ON dashboard
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
