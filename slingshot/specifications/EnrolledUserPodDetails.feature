Feature: Successful and Unsuccessful Scenario of Pod details or pod overview functionality to the application  
  As an Enrolled user of the application
  I want to test Pod Details feature
  
Background: Login:loginAsEnrolledUserSuccess


Scenario: To verify the Pod Overview landing page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow icon
	When I CLICK firstPod
	Then I am ON overview page


Scenario: To verify that Buy button is not available in overview page when user navigates through recommended pods with DATA LoginAsEnrolledUser_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY buyButtonUnavailable with DATA LoginAsEnrolledUser_Success
	Then its verified successfully
	
	
Scenario: To verify that Forum button and Enter Classroom button is available in overview page when navigates through ongoing pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY forumAndEnterClsRmButtonDisplayed or not
	Then Successfully Forum button is present
	
	

Scenario: To verify that Enter classroom button is Enabled under valid license and its clickable in overview page
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY checkEnterClsroomBtnEnabled is clickable 
	Then its verified successfully

	
Scenario: To verify whether pod forum page is accessible or not 
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I CLICK forumButton is displayed or not
	Then I am ON podForum page
	Then forum page is displayed and verified successfully

