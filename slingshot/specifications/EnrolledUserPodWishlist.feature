Feature: Successful and Unsuccessful Pod Wishlist functionality to the application
As an Enrolled user of the application
I want to test Pod wishlist features

Background: Login:loginAsEnrolledUserSuccess


Scenario: To verify clicking on Pod Wishlist from left menu navigates to Pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	
	
Scenario: To verify the result displayed for search pods is correct with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist link
	Then I am ON podWishList page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow icon
	And I VERIFY correctPodsDisplayed
	Then the correct search pod result is displayed


Scenario: To verify that clicking on pods navigate to pod overview page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist link
	Then I am ON podWishList page
	And I CLICK searchPodArrow icon
	Then the list is displayed
	When I CLICK firstPod
	Then I am ON overview page


Scenario: To verify the name of pod after navigating to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist link
	Then I am ON podWishList page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY correctPodsDisplayed
	And I CLICK firstPod
	Then I am ON overview page
	When I VERIFY podName
	Then the pod name is same as the first pod clicked	

	
Scenario: To verify the result displayed for skill filter is displaying correctly with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist link
	Then I am ON podWishList page
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in skills
	And I VERIFY displayedSkillFilter result set
	Then the filtered pod name displayed are same as the skills selected


Scenario: To verify adding pods to Pod Wishlist page from search pod page and test industry filter with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in Industry
	And I VERIFY displayedIndustryFilter result set
	Then the filtered pod name is same as the pod clicked
	

Scenario: To verify the result displayed for level filter is correct with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in Industry
	And I VERIFY displayedLevelFilter result set
	Then the filtered pod name is same as the pod clicked
	
		
Scenario: To verify the result displayed for min and max filter is correct with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked


Scenario: To verify the results results are correct when sort by filter is applied with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked


Scenario: To verify the pod count displayed is same when we try to select and remove the same item with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in skills 
	And I VERIFY removeSelectedSkills from the auto suggest
	Then the Pod count is correct
	
	
Scenario: To verify the number of pods displayed in each page is matching the number shown with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist link
	Then I am ON podWishList page
	And I VERIFY resultDisplayedPerPage
	Then the result shown per page is the same number pods displayed per page


Scenario: To verify the pods are successfully removed from pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I try to remove all the pods from pod wishlist page
	And I VERIFY removeAddedPodWishlist from the pod wishlist page
	Then pod wishlist page is not displaying any pods
	Then the Pod count should be correct


	

