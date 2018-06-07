Feature: To test all the feature of Post Job List page
  As an user of the application
  I want to test various functionalities of Post Job List page
  
  Background: Login:LoginAsOrgAdmin,PostJob:PostJob_Success

Scenario: To verify the navigation from Post Job List page page to Edit Job page
    Given I am ON jobPostList page
    When I CLICK editJoblink 
    Then I am ON editJobPost page
 
Scenario: To verify the navigation from Post Job List page page to Repost Job page
    Given I am ON jobPostList page
    When I CLICK repostJoblink 
    Then I am ON repostJob page

Scenario: To verify if the job closed by organization has status closed in the post job list user with DATA PostJob_Success
    Given I am ON jobPostList page
    When I PERFORM closeFirstJob 
    Then I am ON jobPostList page
    Then I VERIFY ifJobStatusIsClosed
    And it is verified that the job has been closed

Scenario: To verify if the closed job is not displayed in individual user Recommended list with DATA PostJob_Success
    Given I am ON jobPostList page
    When I PERFORM closeFirstJob 
    Then I am ON jobPostList page
    Then I VERIFY ifJobStatusIsClosed
    And it is verified that the job has been closed
    And I PERFORM logOut operation
    Then I am On home page
    And I CLICK login button
    Then I am ON login page
    And I ENTER valid individual user details with DATA Login_Success
    When I SUBMIT the form
    Then I am ON dashboard page
    And I CLICK jobsTab
    When I CLICK recommendedJobViewAll link
    Then I am ON jobPostList page 
    And I PERFORM searchforPostedJob using search
    Then I VERIFY ifClosedJobNotDisplayed in individual user recommended jobs

Scenario: To verify if the repost job page has same data which was user for posting the job with DATA RePostJob_Success
    Given I am ON jobPostList page
    When I CLICK repostJoblink
    Then I am ON repostJob page
    And I PERFORM changeJobListClearStatus
    And I PERFORM getRepostJobFieldDetails
    And I VERIFY ifRepostJobHasSameDetails on repost job page 
    Then I am ON repostJob page

Scenario: To repost a job and verify if the job is displayed in the job list with DATA RePostJob_Success
    Given I am ON jobPostList page
    When I CLICK repostJoblink
    Then I am ON repostJob page
    And I PERFORM getRepostJobFieldDetails
    And I ENTER valid details to repost the job with DATA RePostJob_Success
    Then I SUBMIT the form
    And I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    And I VERIFY ifJobStatusIsPublished
    And it is verfied that reposted job was displayed on post job list
    
Scenario: To repost a job and click on the reposted job to verify the job details with DATA RePostJob_Success
    Given I am ON jobPostList page
    When I CLICK repostJoblink
    Then I am ON repostJob page
    And I ENTER valid details to repost the job with DATA RePostJob_Success
    And I PERFORM getRepostJobFieldDetails
    Then I SUBMIT the form
    And I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    And I VERIFY ifJobStatusIsPublished
    Then I PERFORM clickOnFirstJob 
    And I am ON jobDetail page
    Then I VERIFY jobDetails match on job detail page
    And I am ON jobDetail page and job details are verified
    
Scenario: To verify if reposted job is displayed for an individual user with DATA RePostJob_Success
		Given I am ON jobPostList page
    When I CLICK repostJoblink
    Then I am ON repostJob page
    And I ENTER valid details to repost the job with DATA RePostJob_Success
    And I PERFORM getRepostJobFieldDetails
    Then I SUBMIT the form
    And I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    And I VERIFY ifJobStatusIsPublished
    When I PERFORM logOut operation
    Then I am On home page
    And I CLICK login button
    Then I am ON login page
    And I ENTER valid individual user details with DATA Login_Success
    When I SUBMIT the form
    Then I am ON dashboard page
    And I CLICK jobsTab
    When I CLICK recommendedJobViewAll link
    Then I am ON jobPostList page 
    And I PERFORM searchforPostedJob using search
    Then I VERIFY correctJobDisplayed on job list page
    And it is verified that individual has the reposted job
    
Scenario: To verify if reposted job is displayed for an enrolled user user with DATA RePostJob_Success
		Given I am ON jobPostList page
    When I CLICK repostJoblink
    Then I am ON repostJob page
    And I ENTER valid details to repost the job with DATA RePostJob_Success
    And I PERFORM getRepostJobFieldDetails
    Then I SUBMIT the form
    And I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    And I VERIFY ifJobStatusIsPublished
    Then I CLICK leftMenuButton
    And I CLICK searchPods link
  	Then I am ON searchPods page
	  And I ENTER searchInputs for search with DATA PodSearch_Success
	  Then I CLICK searchPodArrow 
	  And I VERIFY correctPodsDisplayed
	  Then the correct search pod result is displayed
	  When I CLICK firstPod
	  Then I VERIFY podName
	  And the pod name is same as the first pod clicked
    Then I VERIFY forumAndEnterClsRmButtonDisplayed or not
	  And I am ON overview page 
	  When I CLICK addParticipant button
    And I CLICK inviteLearner button
    Then I am ON inviteUser page
    And I ENTER valid user details with DATA InviteUser_Success
    Then I SUBMIT the form
    And I am ON participantResultList page
    Then I PERFORM searchParticipantList
    And I VERIFY userDisplayedOnPage
    When I PERFORM logOut operation
    Then I am On home page
    And I CLICK login button
    Then I am ON login page
    When I PERFORM loginWithInvitedUser
    Then I am ON changePassword page
    And I ENTER valid password details with DATA ChangePassword_Success
	  Then I SUBMIT the form
    Then I am ON dashboard page
    And I CLICK jobsTab
    When I CLICK recommendedJobViewAll link
    Then I am ON jobPostList page 
    And I PERFORM searchforPostedJob using search
    Then I VERIFY correctJobDisplayed on job list page
    And it is verified that individual has the reposted job 
    