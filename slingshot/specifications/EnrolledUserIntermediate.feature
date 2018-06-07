Feature: To Buy a pod in the organization and enroll a user to the pod
  As an user of the application
  I want to test Add Participant feature

Background: Login:LoginAsOrgAdmin,SearchPods:SearchForPod

@Group(HelloWorld1)
Scenario: To verify successful scenario for adding, enrolling and login with the created user with DATA InviteUser_Success
    Given I am ON overview page of the pod
    When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I VERIFY ifLicenceAvailable to enroll user to pod
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I PERFORM changeUserCreateStatus
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    And I PERFORM searchParticipant
    And I VERIFY userDisplayedOnPage
    Then correct user is displayed on the page
    And I VERIFY ifUserIsEnrolled to the pod
    Then it is verified that user is enrolled to the pod

@Group(HelloWorld2) 
Scenario: To enroll a single user to multiple pods and login with the enrolled user with DATA PodsToBuy_Success
    Given I am ON overview page of the pod
    And I CLICK searchPodBreadcrumb
    Then I am ON searchPods page
    And I ENTER searchInputs for search with DATA PodsToBuy_Success
    Then I CLICK searchPodArrow
    And I VERIFY correctPodsDisplayed
    Then the correct search pod result is displayed
    When I CLICK firstPod
    When I VERIFY podName
    Then the pod name is same as the first pod clicked
    And I VERIFY forumAndEnterClsRmButtonDisplayed or not
    Then I am ON overview page
     When I CLICK addParticipant button
    Then I am ON participantResultList page
    And I PERFORM searchParticipant
    And I VERIFY ifLicenceAvailable to enroll user to pod
    And I PERFORM enrollUserToPod 
    And User is enrolled to pod successfully
    Then I am ON overview page of the pod
 
Scenario: To login with the enrolled user for the first time and change the password with DATA ChangePassword_Success
    Given I am ON overview page of the pod
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
    
    
    
    