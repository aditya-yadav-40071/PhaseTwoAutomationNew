Feature: Successful and Unsuccessful scenarios of Bulk License as an Organization Admin in the application  
  As an Organization admin of the application
  I want to test the Bulk License feature

Background: Login:LoginAsAdmin


Scenario: To verify the failure scenario by adding invalid number of user in payment page with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK managePodLicense link
	Then I am ON managePodLincense page
	And I CLICK requestBulkLicense button
	Then I am ON bulkLicense page
	And I PERFORM searchForAPod
	Then I CLICK bulkBuy button
	Then I am ON bulkLicensePayment page
	And I ENTER invalid number of license with DATA NoOfUsers_Failure
	Then I SUBMIT the form
	Then form error message is verified
	
	

Scenario: To verify the error message for already requested and that pod should appear in pending tab of Manage Pod License with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK managePodLicense link
	Then I am ON managePodLincense page
	And I CLICK requestBulkLicense button
	Then I am ON bulkLicense page
	And I PERFORM searchForAPod
	Then I CLICK bulkBuy button
	Then I am ON bulkLicensePayment page
	And I ENTER valid inputs with DATA NoOfUsersOrg_Success
	Then I SUBMIT the form
	Then I am ON managePodLincense page
	And I CLICK pendingButton 
	When I VERIFY correctPodContentsDisplayed
	Then I CLICK requestBulkLicense button
	And I VERIFY correctMsgForAlreadyRequested pod
	Then pod error message and content is verified successfully
	
	

Scenario: To verify that sales admin got a request from an org and gets rejects  with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page
	And I CLICK editOrganizationProfile link
	Then I am ON editOrganizationProfile page
	Then I PERFORM getAdminFirstName
	And I CLICK orgLogo to come back to dashboard page 
	Then I am ON dashboard
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
	Then I VERIFY podsRejectedBySalesAdmin
	And I am ON approveLicense page
	Then I VERIFY correctEmailIdDisplayed
	And I ENTER reject input with DATA RejectLicense_Success
	When I SUBMIT reject form
	Then I am ON licenseDetails page
	
	  
	
		
Scenario: To verify the rejected pod is under rejected tab in Manage License page with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK managePodLicense link
	Then I am ON managePodLincense page
	And I CLICK rejectedButton
	Then I VERIFY correctRejectedPodLicense 
	And it is verified successfully
	
	
	
Scenario: To verify buying pods in bulk from Bulk License page with DATA PodsToBuyInBulk_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK managePodLicense link
	Then I am ON managePodLincense page
	And I CLICK requestBulkLicense button
	Then I am ON bulkLicense page
	And I PERFORM bulkBuyOperation
	Then I CLICK bulkBuy button
	Then I am ON bulkLicensePayment page
	And I VERIFY correctPodListInPayement page 
	And I ENTER valid inputs with DATA NoOfUsersOrg_Success
	Then I SUBMIT the form
	Then I am ON managePodLincense page
	
	
	
Scenario: To verify that sales admin got a request from an org and it gets approved  with DATA PodsToBuyforLicense_Success
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
	And I VERIFY pendingTabSelected
	Then I VERIFY correctBulkPodRequestsDisplayed
	And I CLICK firstReviewLink
	Then I am ON approveLicense page
	When I VERIFY correctBulkPodApproveList displayed
	And I ENTER the mandatory details with DATA ApproveLicense_Success
	Then I SUBMIT the form
	Then I am ON licenseDetails page
	Then a pod is successfully bought and approved
	
	
	
	
Scenario: To verify bought pods are available in available tab as well as in ongoing trainings  with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK managePodLicense link
	Then I am ON managePodLincense page
	And I CLICK approveTab 
	Then I VERIFY correctApproveResult displayed
	
	
	 
		
	
	
	
	

	
	
	
	

	
	

	

	
	
	
	