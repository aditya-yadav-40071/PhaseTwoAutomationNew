Feature: Successful and Unsuccessful Scenario of Pod details or pod overview functionality to the application  
  As an user of the application
  I want to test Pod Details feature
  
Background: Login:loginAsIndividualUserSuccess


Scenario: To verify the Pod Overview landing page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow icon
	When I CLICK firstPod
	Then I am ON overview page


Scenario: To verify that Buy button is available in overview page when user navigates through recommended pods
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY isBuyAndEnterClassroomButtons displayed
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
	And I VERIFY checkEnterClsroomBtnEnabled and clickable or not
	Then its verified successfully



Scenario: To verify that Renew Licenses page is available in overview page when navigates through ongoing pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed or not
	Then I am ON courseLicenses page
	Then it is displayed and verified successfully
	
	
Scenario: To verify that Renew Licenses page is available in overview page when navigates through ongoing pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I CLICK forumButton is displayed or not
	Then I am ON podForum page
	Then it is displayed and verified successfully


Scenario: To verify that Renew Licenses button is available in overview page when navigates through ongoing pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed or not
	Then it is displayed and verified successfully


Scenario: To verify that introductory video or image changes based on the pod clicked on ongoing pods
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed or not
	Then it is displayed and verified successfully

Scenario: To verify that clicking on pods navigate to pod overview page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	Then the list is displayed
	When I CLICK firstPod
	Then I am ON overview page


Scenario: To verify the name of pod after navigating to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
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


Scenario: To verify clicking on Pod Wishlist from left menu navigates to Pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page


Scenario: To wishlist a pod and verify the pod is displaying in the wishlist page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	And I PERFORM addToWishlist to add the first pod to wishlist
	And I CLICK dashBoardLink to navigate to dashboard page
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY wishlistPodDsplyd in wishlist page
	Then the wishlisted pod is displayed in the wishlist page
	
	
Scenario: To remove a wishlist and verify that the wishlist has been removed with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodArrow
	And I PERFORM addToWishlist to add the first pod to wishlist
	And I PERFORM removeFrmWshList to remove the first pod to wishlist
	And I CLICK dashBoardLink to navigate to dashboard page
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY wishListedPodRemoved
	Then the wishlisted pod is removed from the page


Scenario: To verify the number of pods displayed in each page is matching with the number shown with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodArrow
	Then I am ON searchPods page
	And I VERIFY resultDisplayedPerPage
	Then the result shown per page is the same number pods displayed per page


Scenario: To verify the Search Pod landing page from dashboard
	Given I am ON dashboard page
	When I CLICK dashboardSearchPodBtn button from dashboard
	Then I am ON searchPods page


Scenario: To verify the result displayed for skill filter is displaying correctly with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in skills
	And I VERIFY displayedSkillFilter result set
	Then the filtered pod name displayed are same as the skills selected


Scenario: To verify the result displayed for Industry filter is correct with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in Industry
	And I VERIFY displayedIndustryFilter result set
	Then the filtered pod name is same as the pod clicked 
	
	

Scenario: To verify the result displayed for level filter is correct with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails for Level
	And I VERIFY displayedLevelFilter result set
	Then the filtered pod name is same as the pod clicked 



Scenario: To verify the result displayed for min and max filter is correct with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked 


Scenario: To verify the result displayed for sort by filter is correct with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked



Scenario: To verify the pod count displayed is same when we try to select and remove the same item with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodArrow
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in skills 
	And I VERIFY removeSelectedSkills from the auto suggest
	Then the Pod count is correct

