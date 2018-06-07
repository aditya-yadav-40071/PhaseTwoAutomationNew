Feature: Successful and Unsuccessful Scenario of Pod Progress details functionality to the application  
  As an user of the application
  I want to test Pod Progress Details feature
  
Background: IndividualRegister:loginAsIndividualUserSuccess


Scenario: To verify the Pod Progress Details landing page
	Given I am ON dashboard page
	When I VERIFY ongoingPodsCountIsNotZero
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I CLICK firstViewProgress link
	Then I am ON podProgressDetails page
	

Scenario: To verify the pods count displayed is correct with Pod Progress Details landing page
	Given I am ON dashboard page
	When I VERIFY ongoingPodsCountIsNotZero
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I CLICK firstViewProgress link
	Then I am ON podProgressDetails page
	When I VERIFY correctPodsCountDisplayed in viewing count 
	Then viewing count is verified successfully
	
	
Scenario: To verify correct pod progress percentage is displayed in graph
	Given I am ON dashboard page
	When I VERIFY ongoingPodsCountIsNotZero
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I CLICK firstViewProgress link
	Then I am ON podProgressDetails page
	When I VERIFY correctPodsCountDisplayed in viewing count 
	Then I PERFORM getFirstPodWithPercentage from displayed list
	And I CLICK firstPodFromList displayed below
	When I VERIFY correctPercentageDisplayed in graph
	Then graph percentage is verified 
	
	
		
Scenario: To verify request of pod buy as an individual user with DATA PodForProgress_Success
	Given I am ON dashboard page 
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	And I ENTER searchInputs for search with DATA PodForProgress_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedPods page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	And I VERIFY noOfUsersDisplayedAsUser for calculation
	Then I ENTER asUser remarks with DATA UserRemarks_Success
	And I SUBMIT asUser of application
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
	And the pod purchase request is sent
	

Scenario: To verify sales admin has got an access to approve the pod request with DATA LoginAsSalesAdmin_Success
	Given I am ON dashboard page
	And I CLICK userEditProfile link 
	Then I am ON editProfile page
	And I PERFORM getProfileName
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	Then I am ON home page
	And I CLICK login
	Then I am ON login page
	And I ENTER the details with DATA LoginAsSalesAdmin_Success
	When I SUBMIT the form
	Then I am ON dashboard page of sales admin
	And I CLICK leftMenuButton 
	And I CLICK licenseDetails link
	Then I am ON licenseDetails page
	And I VERIFY pendingTabSelected or not
	Then I VERIFY podRequestsDisplayed or not
	And I PERFORM checkTypeAndName of pod request 
	Then I am ON approveLicense page
	And I VERIFY licenseDetails in approve license page
	And I ENTER the mandatory details with DATA ApproveLicenseForCourse_Success
	Then I SUBMIT the form
	Then pod is successfully approved.

	
Scenario: To verify correct pod list is displayed in view progress page with DATA PodProgress_Success
	Given I am ON dashboard page
	When I VERIFY ongoingPodsCountIsNotZero
	And I CLICK viewAllOngoingPods
	Then I am ON ongoingPods page
	When I CLICK firstViewProgress link
	Then I am ON podProgressDetails page
	And I VERIFY correctPodsCountDisplayed in viewing count 
	When I ENTER sortBy data with DATA PodProgress_Success
	Then I VERIFY correctSortByList displayed
	
	
	
	
	
	
	
	
	
	
