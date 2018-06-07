Feature: Successful and Unsuccessful Scenario of Pod details or Course Licenses functionality to the application  
  As an Individual user of the application
  I want to test Course Licenses feature
  
Background: IndividualRegister:loginAsIndividualUserSuccess


Scenario: To verify the title of Course Licenses page
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed 
	Then I CLICK renewLicense button
	And I am ON courseLicenses page
	Then course License page title is verified successfully


Scenario: To verify course license page is of correct pod with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	And I ENTER searchInputs for search with DATA PodSearch_Success
	Then I CLICK searchPodArrow 
	And I VERIFY podsDisplayed
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed 
	Then I CLICK renewLicense button
	And I am ON courseLicenses page 
	When I VERIFY correctPodNameInBredcrumb displayed
	Then successfully correct pod name is displayed in bredcrumb url
	
	

Scenario: To store the Expiry Date provided by IT Sales admin with DATA PodsToBuyforLicense_Success
	Given I am ON dashboard page of Individual User
	And I CLICK userEditProfile link 
	Then I am ON editProfile page
	And I PERFORM getProfileName
	And I CLICK orgLogo to come back to dashboard page 
	Then I am ON dashboard
	And I CLICK viewAllRecommendedPods
	Then I am ON recommendedPods page
	And I PERFORM getFirstPodName
	And I ENTER searchInputs for search with DATA PodsToBuyforLicense_Success
	Then I CLICK searchPodArrow icon
	Then I am ON recommendedPods page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON overview page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER asUser remarks with DATA PaymntRemrksForLicence_Success
	And I VERIFY noOfUsersDisplayedAsUser for calculation
	And I SUBMIT asUser
	Then I am ON overview page
	Then I VERIFY podBuyRequest action
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
	Then a pod is successfully bought and approved.


	
Scenario: To verify the license start date and end date is correct when compared with Sales admin record with DATA PodSearch_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	And I ENTER searchInputs with DATA PodSearch_Success
	Then I CLICK searchPodArrow icon
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed 
	Then I CLICK renewLicense button
	And I am ON courseLicenses page
	When I VERIFY startAndEndLicenseDate 
	Then start date and end date is verified
	When I VERIFY correctTotalAndEnrolledStudents displayed
	And I VERIFY renewLinkDisabled 
	Then it verified successfully
	
	
	
Scenario: To verify the correct course license list is displayed when user switch to active tab and Expiry tab
	Given I am ON dashboard page
	And I CLICK viewAllOngoingPods link
	Then I am ON ongoingPods page
	When I CLICK firstPod
	Then I am ON overview page
	And I VERIFY renewLicensesBtnDisplayed 
	Then I CLICK renewLicense button
	And I am ON courseLicenses page
	Then I PERFORM getCompleteLicenseDetials
	And I CLICK licenseActiveTab
	When I VERIFY correctLicenseListDisplayed in active tab
	Then the correct license list is displayed and verified
	Then I CLICK licenseExpiredTab
	When I VERIFY correctExpiredLicense displayed
	 
	
	
	

	 
	
	
	
	
	
	
	
