Feature: Successful and Unsuccessful scenarios of Classroom page functionality to the application  
  As an user of the application
  I want to test ClassRoom feature
  
Background: Login:loginAsEnrolledUserSuccess

Scenario: To verify classroom page title
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I CLICK searchPodArrow
	Then the list is displayed
	When I CLICK firstPod
	Then I am ON overview page
	When I CLICK enterClassroom button
	Then I am ON classroom page

Scenario: To verify that onclick of enter classroom button should redirect to correct classroom type from search pod page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY podsDisplayed
	When I CLICK firstPod
	Then I am ON overview page
	And I PERFORM getClassroomType from introductory media
	When I VERIFY correctClassRoomType
	Then its verified Successfully
	
	
Scenario: To verify that onclick of enter classroom button should redirect to correct classroom type from ongoing pod page with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY podsDisplayed
	When I CLICK firstPod
	Then I am ON overview page
	And I PERFORM getClassroomType from introductory media
	When I VERIFY correctClassRoomType
	Then its verified Successfully
	
Scenario: To verify the Error message is appearing pod details when navigates through recommended pods page
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	Then I CLICK searchPodArrow 
	And I VERIFY podsDisplayed
	And I CLICK firstPod
	Then I am ON overview page
	And I VERIFY errorMsgDisplayed above enter classroom button
	Then its verified Successfully
