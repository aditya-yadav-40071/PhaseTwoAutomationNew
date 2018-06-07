package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


/* Class to take care of operations related to all click actions */
class ClickAction {

	private static Map<String, String> xPathMapping;

	private static final def LINK = "linkname"

	static {

		xPathMapping = new HashMap<String, String>();

		//List of buttons/links with its XPath in the path
		xPathMapping.put("login", ".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']");
		xPathMapping.put("signIn", "//input[@type='submit']");
		xPathMapping.put("leftMenuButton", "//button[@aria-expanded='false'][@role='button']");
		xPathMapping.put("registerFrmLftMenu", "//a[contains(text(), 'Register')]");
		xPathMapping.put("registerFrmPage", "//button[contains(text(), 'REGISTER')]");
		xPathMapping.put("organizationLnk", "//h4[contains(text(), 'ORGANIZATION')]");
		xPathMapping.put("nextBtn", "//button[contains(text(),'NEXT')]");
		xPathMapping.put("loginFromLeftMenu", ".//a[@href='#/account/login']");
		xPathMapping.put("individualRegister", ".//*[@class='kp-individual kp-large-two-tone-icons']/span[@class='path3']")
		xPathMapping.put("resgisterFromHome", ".//button[contains(@class, 'margin-auto display-block btn btn-primary btn-xlarge get-started-btn') and text() = 'REGISTER']")
		xPathMapping.put("podWorksLogo", "//a[@href='#/home']/img");

		//Organization Dashboard
		xPathMapping.put("editOrgOnDashboard", "//a[@class='profile-edit-link display-block ng-scope']/h5");
		xPathMapping.put("viewAllRecommendedTrng", "//div[@id='dashboard']/div//button[text()='Recommended Trainings']/following-sibling::div[2]//a[text()='View All']");
		xPathMapping.put("viewAllOngoing","//div[@id='dashboard']/div//button[text()='Ongoing Trainings']/following-sibling::div[2]//a[text()='View All']");


		//Individual User Dashboard
		xPathMapping.put("viewAllOngoingPods", ".//div[@class='hidden-xs hidden-sm']//a[@href='/pods/ongoing']")
		xPathMapping.put("viewAllRecommendedPods", "//div[@id='dashboard']/div/div/div[5]/div/div[1]/div/div/div[1]/div[2]/div[2]/dashboard-list-widget/div/div/div/a")
		xPathMapping.put("viewAllCompletedPods", ".//*[@id='dashboard']/div/div/div[5]/div/div[1]/div/div/div[2]/div[1]/div[2]/dashboard-list-widget/div/div/div/a")

		//Sales admin
		xPathMapping.put("licenseDetails", "//a[text()='License Details']")


		//Change Password
		xPathMapping.put("changePassword", "//a[text()='Change Password']");

		//Search Pods
		xPathMapping.put("searchPods", "//a[text()='Search Pods']");
		xPathMapping.put("searchPodArrow", "//div[@class='col-xs-12 mb10 no-padding']//i");
		xPathMapping.put("searchOrgArrow", "//div[@class='col-xs-12 mb10 no-padding ml_15 visible-lg mt_10 pr_25']//i")
		xPathMapping.put("firstPod", ".//div[@class='ng-scope']/div[1]//h3/a");
		xPathMapping.put("podWishlist", "//a[contains(text(), 'Pod Wishlist')]");
		xPathMapping.put("dashBoardLink", ".//*[@id='breadcrumbox']/ol/li[1]/a");
		xPathMapping.put("dashboardSearchPodBtn", "//button[text()='Search']")
		xPathMapping.put("searchPodDashBtnForUser","//a[text()='Search']")
		xPathMapping.put("searchPodBreadcrumb", ".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Ongoing Pods
		xPathMapping.put("viewBatchProgress","//a[text()='View Batch Progress']")
		xPathMapping.put("viewProgress", "//div[@id='main_page']/div[2]/div[1]/div/div[3]/div[3]/div[1]/div[3]//a")
		xPathMapping.put("firstViewProgress", "//div[contains((@class),'clearfix content individual-pod mt10')][1]//a[text()='View Progress']")

		//Completed Pods
		xPathMapping.put("viewCertficate", "//a[normalize-space(text())='View certificate']")

		//Pod Overview page elements
		xPathMapping.put("enterClassroom", "//button[text()='Enter Classroom']")

		//Forgot Password
		xPathMapping.put("forgotPassword", ".//*[@id='forgot_password']")
		xPathMapping.put("privacyPolicy", "//a[text()='Privacy Policy']");
		xPathMapping.put("termsOfUse", "//a[text()='Terms Of Use']");
		xPathMapping.put("aboutUs", "//a[text()='About Us']");
		xPathMapping.put("contactSupport", "//a[text()='Contact Support']");
		xPathMapping.put("contactSupportHeader",".//h2[@class='fontsize-13 text-transform-none']");
		xPathMapping.put("goToLoginPage", ".//*[@href='#/account/login']");
		xPathMapping.put("brandImageLink", ".//img[@class='brand_bLogin']");
		xPathMapping.put("privacyPolicy", "//a[contains(text(),'Privacy Policy')]");
		xPathMapping.put("termsOfUse", "//a[contains(text(),'Terms Of Use')]");
		xPathMapping.put("goToLoginPage", "//form[@id='forgotPasswordMobile']/div[3]/div[2]/a");
		xPathMapping.put("brandImageLink", ".//img[@class='brand']");

		//Manage Admin
		xPathMapping.put("manageAdmins", ".//li[@ng-show='getAdminListUser']/a");
		xPathMapping.put("manageAdminBreadCrumb", ".//*[@id='breadcrumbox']/a[@href='#/orgadmin/listuser']");
		xPathMapping.put("addAdmin", ".//input[@value='Add New Admin']");
		xPathMapping.put("fiftyResultsPerPage", ".//label[@for='item5']");
		xPathMapping.put("lastOfItemsPerPage", ".//div[@class='items-per-page-section pull-right pos-r']/label[last()]");
		xPathMapping.put("addAnotherAdmin", ".//button[contains(@ng-click,'showConfirm')]");
		xPathMapping.put("saveToAddAnotherAdmin", ".//button[@ng-click='dialog.hide()']/span");

		//Edit Organization Profile
		xPathMapping.put("editOrganizationProfile", ".//a[@ui-sref='kp.editProfileOrg']");
		xPathMapping.put("addLatestNewsButton", ".//div[@class='col-md-12']//a/span");
		xPathMapping.put("dashboardBreadcrum", "//a[@href='/dashboard']")

		//Organization logo
		xPathMapping.put("orgLogo", "//div[@class='no-padding']/img")

		//View Organization Profile
		xPathMapping.put("viewOrganizationProfile", "//a[contains(text(), 'View Organization')]");

		//Post Job
		xPathMapping.put("postJob", "//a[text()='Post Job']")
		xPathMapping.put("viewAllPostings", ".//a[@href='#/jobs/postlist']");
		xPathMapping.put("postJobBreadcrumb",".//*[@id='breadcrumbox']/ol/li[1]");
		xPathMapping.put("hireTab",".//li[@id='hireTab']/a");
		xPathMapping.put("currentOpeningViewAll",".//div[@class='hidden-xs hidden-sm']//a[@ui-sref='kp.postlistJob']");
		xPathMapping.put("firstApplicantView",".//*[@id='main_page']//div[@style='']/div[3]//a[4]");

		//Jobs
		xPathMapping.put("jobsTab", ".//a[@ui-sref='.jobDashboard']");
		xPathMapping.put("recommendedJobViewAll", ".//*[@id='dashboard']/div/div/div[4]/div/div[1]/div/div/div[1]/div[2]/div[2]/dashboard-list-widget/div/div/div/a");

		//Pod details Page
		xPathMapping.put("buyThePod", "//div[@ng-if='buyCourse']/button");
		xPathMapping.put("forumButton", "//button[text()='Forum']")
		xPathMapping.put("renewLicense", "//button[text()='Renew Licenses']")

		//Participant Result List page
		xPathMapping.put("inviteUser", "//div[button[text()='Back']]/preceding-sibling::div[1]/button")

		//License Details
		xPathMapping.put("licenseDetails", "//li[@ng-show='requestedLicenseDetail']/a")
		xPathMapping.put("pendingButton", "//a[contains(text(),'Pending')]")
		xPathMapping.put("rejectedButton", "//li[a[text()='Rejected'] and @role='button']")

		//Logout
		xPathMapping.put("logoutLink", "//a[contains(text(), 'Logout')]")

		//Followed Organizations List Page
		xPathMapping.put("followedOrganization","//a[text()='Followed Organization List']");
		xPathMapping.put("search", "//input[@value='SEARCH']");
		xPathMapping.put("primarySearch","//label[text()='Search']/following-sibling :: button");
		xPathMapping.put("firstOrgNameCick",".//div[@class='list-section col-sm-9 col-md-9']/div[4]//a[@ng-click='getCompanyDetails(company.orgId)']");
		xPathMapping.put("firstOrganization",".//div[@class='list-section col-sm-9 col-md-9']/div[5]//a[@class='content-name job-title pointer title-name blue ng-binding']");
		xPathMapping.put("bredcrumBack", ".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Pod Customization
		xPathMapping.put("podCustomization","//a[text()='Pods Customization']")
		xPathMapping.put("podsCustomizationBack", ".//*[@id='breadcrumbox']/ol/li[1]")

		//Profile Wishlist
		xPathMapping.put("profileWishlist","//a[text()='Profile Wishlists']")

		//Users Connections
		xPathMapping.put("connectionLink", "//p[contains(text(),'Connections')]")
		xPathMapping.put("searchConnection","//input[@value='SEARCH']")
		xPathMapping.put("firstConnectedUser","//div[@class='list-section col-sm-9 col-md-9']//div[contains(@ng-repeat,'connection in connectionList')][1]//h3/a")
		xPathMapping.put("firstConnectLink", "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[normalize-space(text())='Connect']")

		//Post Job
		xPathMapping.put("postJob", "//a[text()='Post Job']")
		xPathMapping.put("viewAllPostings", ".//a[@href='/jobs/postlist']");
		xPathMapping.put("postJobBreadcrumb",".//*[@id='breadcrumbox']/ol/li[2]/a");
		xPathMapping.put("fiftyResults",".//label[@for='item5']");

		//User Edit Profile
		xPathMapping.put("userEditProfile",".//div[@class='pos-a full-width full-height']/a");
		xPathMapping.put("basicInfoArrowDown","//*[contains(text(), 'Basic Information*')]");
		xPathMapping.put("basicInfoArrowUp","//*[contains(text(), 'Basic Information*')]");
		xPathMapping.put("viewPublicProfile",".//li[@ng-show='viewUserProfile']/a");
		xPathMapping.put("educationDetailsArrowDown","//*[contains(text(), 'Education Qualifications')]");
		xPathMapping.put("cancelLink",".//a[contains(text(), 'Cancel')]");
		xPathMapping.put("workExperienceArrowDown",".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[3]/div/button");
		xPathMapping.put("workExperienceAddMoreButton","//div[@class='work-exp-container']//button[@class='btn button-secondary']");
		xPathMapping.put("skillsArrowDown",".//button[@class='skills accordion']");
		xPathMapping.put("editProfilelink",".//*[@id='breadcrumbox']/ol/li[2]/a");
		xPathMapping.put("certificateArrowDown",".//div[@class='certificates-container']/button[@class='accordion']");
		xPathMapping.put("certificateAddMore",".//div[@class='certificates-container']//button[@ng-click='addDocument()']");

		//Manage Users
		xPathMapping.put("manageUsers", "//a[text()='Manage Users']")
		xPathMapping.put("inviteLearner","//button[text()='Invite Learner']")
		xPathMapping.put("firstUser","//div[@class='clearfix content individual-job']/div/div[1]//h3/a")
		xPathMapping.put("back", ".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Participant List
		xPathMapping.put("addParticipant", "//button[text()='Add Participant']");
		xPathMapping.put("inviteLearner", ".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']");
		xPathMapping.put("inviteLearnerBack", ".//button[contains(text(),'Back')]");
		xPathMapping.put("ongoingBreadcrumb",".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Manage Sites
		xPathMapping.put("addSite",".//input[@value='Add Site']")
		xPathMapping.put("manageSites",".//li[@ng-show='manageOrgSites']/a")

		xPathMapping.put("otpButton", "//button[text()='Send OTP']")
		xPathMapping.put("userDashbrdSearchPodBtn", "//a[text()='Search']")

		//View Public profile page of User
		xPathMapping.putAt("viewPublicProfileMenu", "//a[contains(text(), 'View Public Profile')]")

		//Pod Progress Page
		xPathMapping.put("firstPodFromList", "//div[@ng-repeat='pods in userCourseList'][1]/div[1]//a");

		//Course License elements
		xPathMapping.put("licenseAllTab", "//a[text()='All']")
		xPathMapping.put("licenseExpiredTab", "//a[text()='Expired']")
		xPathMapping.put("licenseActiveTab", "//a[text()='Active']")

		//Post Feed Page
		xPathMapping.put("postFeed", "//a[text()='Post Feed']")
		xPathMapping.put("firstFeedTitle", "//div[@ng-repeat='post in postResults '][1]//h3/a")
		xPathMapping.put("sharesLink","//div[@ng-repeat='post in postResults '][1]//span[i[contains(@class,'kp-share')]]")
		xPathMapping.put("shareCloseMark","//button[@data-dismiss='modal']")
		xPathMapping.put("wishListTab","//li[a[text()='WishList']]")

		//Manage Pod License
		xPathMapping.put("managePodLicense", "//a[text()='Manage Pod License']")
		xPathMapping.put("approveTab", "//li[a[text()='Approved'] and @role='button']")


		//Bulk License Page
		xPathMapping.put("requestBulkLicense","//input[@value='Request Bulk License']")
		xPathMapping.put("bulkBuy", "//input[@value='Buy']")
		xPathMapping.put("firstReviewLink","//form/div/table/tbody/tr[2]//a")
		
		//View All in trainer list
		xPathMapping.put("viewAllTrainers",".//div[@class='hidden-xs hidden-sm']//a[@href='/manageadmin/listuser']")
		
		//Edit Job
		xPathMapping.put("editJoblink",".//div[@ng-repeat='job in jobPostingsList'][1]//a[1]")
		
		xPathMapping.put("repostJoblink",".//div[@ng-repeat='job in jobPostingsList'][1]//a[2]")
		
		xPathMapping.put("closeFirstJob",".//div[@ng-repeat='job in jobPostingsList'][1]//a[3]")
		
		//Manage Pod License
		xPathMapping.put("managePodLicense",".//li[@ng-show='managePods']/a")
		
		xPathMapping.put("podNameBreadcrumb",".//a[@href='/pod/details/']")
		
		xPathMapping.put("addParticipantTab",".//li[@ng-if='showPartcipantList']/a")
		
		//Request bulk license 
		xPathMapping.put("requestBulkLicense",".//input[@class='button-primary']")
	}

	// Main function to take care of click actions
	def static performClick (def browser, def clickKey) {

		println ("Performing Click for key: " + clickKey);
		if(clickKey ==null) return;

		def xpath = xPathMapping.get(clickKey);
		println ("xpath for click is " + xpath);

		if(browser.getElement(Browser.XPATH, xpath) == null || !browser.checkEnabled(xpath)){
			def message = clickKey + "  not found/NotEnabled"
			println "Message   :" +message
			def fileName = clickKey+"Issue"
			return KPCommonPage.returnFailureOutcome(browser, fileName, message)
		}else{
			waitBeforeClick(browser, xpath)
			browser.scrollToElement(browser.getElement(Browser.XPATH, xpath))
			browser.click xpath
			waitAfterClick(browser, xpath)
			return new SuccessOutcome();
		}
	}


	//Delay before click
	def static waitBeforeClick(def browser, def xpath){
		if(xpath.equals("//button[contains(text(), 'REGISTER')]")){
			browser.delay(4000)
			browser.scrollToElement2("//button[contains(text(), 'REGISTER')]")
		}else if(xpath.equals(".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']")){
			browser.delay(2000)
			browser.scrollToElement2(".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']")
		}
		if(xpath.equals(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")){
			browser.delay(3000)
			browser.scrollToElement2(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")
		}
		if(xpath.equals("//div[2]/div[1]/div/div/h3/a")){
			browser.delay(1000)
			KPCommonPage.podName = browser.gettext("//div[2]/div[1]/div/div/h3/a")
		}
		if(xpath.equals(".//a[@href='#/orgadmin/listuser']")){
			KPCommonPage.adminEmailId.clear()
		}
		if(xpath.equals(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")){
			browser.delay(2000)
		}
		if(xpath.equals("//button[text()='Enter Classroom']")){
			browser.scrollToElement2("//button[text()='Enter Classroom']")
		}
		if(xpath.equals("//*[contains(text(), 'Education Qualifications')]")){
			browser.scrollToElement2("//*[contains(text(), 'Education Qualifications')]")
		}
		if(xpath.equals(".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[3]/div/button")){
			browser.scrollToElement2(".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[3]/div/button")
		}
		if(xpath.equals("//a[text()='Manage Pod License']")){
			browser.scrollToElement2("//a[text()='Manage Pod License']")
		}
		if(xpath.equals("//input[@value='Buy']")){
			browser.scrollToElement2("//input[@value='Buy']")
		}
		if(xpath.equals("//li[a[text()='Pending'] and @role='button']")){
			browser.delay(2000)
		}
		browser.delay(2000)
	}

	//Delay after click
	def static waitAfterClick(def browser, def xpath){
		if(xpath.equals("//button[@class='full-width button-primary']") || xpath.equals(".//li[@class='menu-name-default mb30']/a[@href='#/pods/search']")
		|| xpath.equals("//button[@class='full-width button-primary']") || xpath.equals(".//a[@href='#/privacypolicy']") || xpath.equals(".//a[@href='#/termsofuse']")
		|| xpath.equals(".//a[@href='#/aboutus']") || xpath.equals(".//a[@href='#/contactus']")
		|| xpath.equals("//div[contains((@class),'clearfix content individual-pod mt10')][1]//a[text()='View Progress']")
		|| xpath.equals("//a[text()='Post Feed']") || xpath.equals("//li[a[text()='WishList']]") || xpath.equals("//input[@value='SEARCH']")
		|| xpath.equals(".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a")
		|| xpath.equals("//button[text()='Enter Classroom']")
		|| xpath.equals(".//div[@class='pos-a full-width full-height']/a")
		|| xpath.equals(".//div[@class='certificates-container']//button[@ng-click='addDocument()']")
		|| xpath.equals("//a[text()='Manage Pod License']")
		|| xpath.equals("//input[@value='Request Bulk License']") || xpath.equals("//input[@value='Buy']")
		|| xpath.equals("//li[a[text()='Pending'] and @role='button']")
		|| xpath.equals("//li[a[text()='Rejected'] and @role='button']")
		|| xpath.equals("//form/div/table/tbody/tr[2]//a")
		|| xpath.equals(".//div[@class='ng-scope']/div[1]//h3/a")
		|| xpath.equals(".//*[@id='breadcrumbox']/ol/li[2]/a")){
			browser.delay(2000)
		}
		browser.delay(3000)
	}

}
