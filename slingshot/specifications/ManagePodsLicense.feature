Feature: Successful and Unsuccessful Manage Pod License functionality to the application
As an user of the application
I want to test Manage Pod License feature
  
Background: Login:LoginAsAdmin

Scenario: To Verify the page title for Manage Pod License page
  Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page

@Group(hello6e)
Scenario: To Verify the page title after clicking the Request Bulk License button
  Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page	
  And I CLICK requestBulkLicense button
  Then I am ON bulkLicense page

Scenario: To verify if all the pods which are in ongoing pods are displayed on Manage Pod License page 
  Given I am ON dashboard page
  When I CLICK viewAllOngoing
  Then I am ON ongoingTraining page
  And I PERFORM getAllOngoingPodNames
	When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
	And I VERIFY approvedTabIsHighlighted
	And I VERIFY ifAllPurchedPodsAreDisplayed on manage pod license page
	Then it is verified that all purchased pods are displayed in the manage pod license page
	
Scenario: To click on the pod name in the manage pod license page and verify the page title with DATA PodSearch_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
	And I PERFORM searchForPod with DATA PodSearch_Success
	Then I PERFORM clickOnThePodName 
	And I am ON courseLicenses page
	
Scenario: To search for a pod and verify if the same pod is displayed on the Manage Pod License page with DATA PodSearch_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
	And I PERFORM searchForPod with DATA PodSearch_Success
	Then I VERIFY correctPodsDisplayed on the page
	And it is verified that the searched pod is displayed on the page
	
Scenario: To click on the add site license level link and the verify the page name with DATA PodSearch_Success
  Given I am ON dashboard page
  When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
	And I PERFORM searchForPod with DATA PodSearch_Success
	Then I PERFORM clickOnAddSiteLicense link
	And I am ON manageSiteLicense page

Scenario: To verify sorting of pods in A to Z order in 'Manage Pod License' page with DATA ListSorted_AtoZ
  Given I am ON dashboard page
  When I CLICK leftMenuButton 
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
  And I VERIFY managePodLicenseListSorted alphabatically

@Group(ManagePodLicense1)
Scenario: To request for a pod and approve the requested pod by the IT admin with DATA PodSorted_Newly
	Given I am ON dashboard page
  And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I ENTER searchInputs for search with DATA PodSorted_Newly
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asAdmin the number of users with DATA NoOfUsersOrg_Success
	And I SUBMIT asAdmin the form
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
	And the pod purchase request is sent
	When I CLICK dashBoardLink
	Then I am ON dashboard page
	And I CLICK editOrganizationProfile link
	Then I am ON editOrganizationProfile page
	Then I PERFORM getAdminFirstName
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
	And I ENTER the mandatory details with DATA ApproveLicense_Success
	Then I SUBMIT the form
	Then a pod is successfully bought and approved.

@Group(ManagePodLicense1)
Scenario: To verify the sorting of the pod according to newly bought pod with DATA PodSorted_Newly	
	Given I am ON dashboard page
	And I CLICK leftMenuButton
	And I CLICK managePodLicense
	Then I am ON managePodLicense page
	And I VERIFY approvedTabIsHighlighted
	And I VERIFY managePodLicenseSortedNewly with DATA PodSorted_Newly