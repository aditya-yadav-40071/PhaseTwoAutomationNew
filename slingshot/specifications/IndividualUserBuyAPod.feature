Feature: Successful and Unsuccessful scenarios of buying a pod as an user in the application  
  As an Individual user of the application
  I want to test the Buy a Pod feature

Background: IndividualRegister:loginAsIndividualUserSuccess

	
Scenario: To verify request of pod buy as an User with DATA PodsToBuy_Success
	Given I am ON dashboard page of Individual User
	And I CLICK userEditProfile link 
	Then I am ON editProfile page
	And I PERFORM getProfileName
	And I CLICK orgLogo to come back to dashboard page 
	Then I am ON dashboard
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	And I ENTER searchInputs for search with DATA PodsToBuy_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedPods page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asUser remarks with DATA PaymentRemarkIndividual_Success
	And I VERIFY noOfUsersDisplayedAsUser for calculation
	And I SUBMIT asUser 
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	Then I am ON home page
	And I CLICK login
	Then I am ON login page
	And I ENTER the details with DATA LoginAsSalesAdminForBuy_Success
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
	

	
	
	
	