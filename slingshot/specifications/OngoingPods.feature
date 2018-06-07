Feature: Successful and Unsuccessful scenarios Ongoing Pods functionality to the application  
  As an user of the application
  I want to test Ongoing Training feature
  
Background: Login:loginAsUserSuccess


Scenario: To verify the page title of the Ongoing Pods page
	Given I am ON dashboard page 
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page


Scenario: To verify that clicking on search button displays all the pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed


Scenario: To verify the result displayed for search pods is correct with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	Then the correct search pod result is displayed


Scenario: To verify that clicking on pods navigate to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I CLICK searchPodArrow
	Then the list is displayed
	When I CLICK firstPod
	Then I am ON overview page


Scenario: To verify the name of pod after navigating to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	And I CLICK firstPod
	Then I am ON overview page
	When I VERIFY podName
	Then the pod name is same as the first pod clicked
	
	
Scenario: To verify the number of pods displayed in each page is matching with the number shown with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	And I CLICK searchPodArrow
	Then I am ON ongoingPods page
	And I VERIFY resultDisplayedPerPage
	Then the result shown per page is the same number pods displayed per page	

	
Scenario: To verify skill filter with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	And I CLICK searchPodArrow icon
	Then I am ON ongoingPods page
	When I ENTER filterDetails in skills
	And I VERIFY displayedSkillFilter result set
	Then the filtered pod name displayed are same as the skills selected

	
Scenario: To verify industry filter with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I ENTER filterDetails in Industry
	And I VERIFY displayedIndustryFilter result set
	Then the filtered pod name is same as the pod clicked

	
Scenario: To verify level filter with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I ENTER filterDetails in level filter
	And I VERIFY displayedLevelFilter result set
	Then the filtered pod name is same as the pod clicked
	
	
Scenario: To verify min and max filter with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked
	

Scenario: To verify sort by filter with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked
	
	
Scenario: To verify the view progress page with correct pod name
    Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I PERFORM getFirstPodName from the list
	And I CLICK viewProgress link 
	Then I am ON podProgressDetails page
	When I VERIFY correctPodProgressPage displayed
	Then it is verified successfully	
	

	
Scenario: To verify the pod count displayed is same when we try to select and remove the same item with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I VERIFY podsDisplayed
	Then pods are displayed
	When I ENTER filterDetails in skills 
	And I VERIFY removeSelectedSkills from the auto suggest
	Then the Pod count is correct
	
	
	

	

	
	
	


	
	
	
	

	
	




	