Feature: Successful and Unsuccessful scenarios of buying a pod as an user in the application  
  As an Organization admin of the application
  I want to test the Buy a Pod feature

Background: Login:LoginAsAdmin

Scenario: To verify the amount of the pod as an Organization admin with DATA PodsToBuy_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I ENTER searchInputs for search with DATA PodsToBuy_Success
	Then I CLICK searchPodArrow
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I PERFORM getCostOfPod from details page
	Then I CLICK buyThePod button
	Then I am ON paymentRequest page
	And I VERIFY equalAmountStatus
	And the same amount used for calculation

	
Scenario: To verify request of payment for zero users as Organization admin with DATA PodsToBuy_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I ENTER searchInputs for search with DATA PodsToBuy_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asAdmin the number of users with DATA NoOfUsers_Failure
	And I SUBMIT asAdmin the form
	Then I am ON paymentRequest page	 
	
	
Scenario: To verify request of pod buy as an Organization with DATA PodsToBuy_Success
	Given I am ON dashboard page
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	Then I am ON home page
	And I CLICK login
	Then I am ON login page
	And I ENTER the details with DATA LoginAsAdmin_Success
	When I SUBMIT the form
	Then I am ON dashboard page of Organization admin
	And I CLICK editOrganizationProfile link
	Then I am ON editOrganizationProfile page
	Then I PERFORM getAdminFirstName
	And I CLICK orgLogo to come back to dashboard page 
	Then I am ON dashboard
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I ENTER searchInputs for search with DATA PodsToBuy_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asAdmin the number of users with DATA NoOfUsersOrg_Success
	And I VERIFY noOfUsersEntered for calculation
	And I SUBMIT asAdmin the form
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
	And the pod purchase request is sent
	
	
	
Scenario: To verify sales admin has got an access to approve the pod request with DATA LoginAsSalesAdmin_Success
	Given I am ON dashboard page 
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
	Then a pod is successfully bought and approved
	

	
Scenario: To verify request of pod buy as an Organization with DATA PodsToBuy_Success
	Given I am ON dashboard page of Organization admin
	And I CLICK editOrganizationProfile link
	Then I am ON editOrganizationProfile page
	Then I PERFORM getAdminFirstName
	And I CLICK orgLogo to come back to dashboard page 
	Then I am ON dashboard
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I ENTER searchInputs for search with DATA PodsToBuy_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asAdmin the number of users with DATA NoOfUsersOrg_Success
	And I VERIFY noOfUsersEntered for calculation
	And I SUBMIT asAdmin the form
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
	And the pod purchase request has been sent
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
	And I VERIFY pendingTabSelected 
	Then I VERIFY podRequestsDisplayed 
	And I PERFORM checkTypeAndName of pod request 
	Then I am ON approveLicense page
	And I VERIFY licenseDetails in approve license page
	And I ENTER the mandatory details with DATA ApproveLicense_Success
	Then I SUBMIT the form
	Then a pod is successfully bought and approved.
	