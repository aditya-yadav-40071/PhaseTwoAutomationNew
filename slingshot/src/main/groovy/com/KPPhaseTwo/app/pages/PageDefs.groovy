package com.KPPhaseTwo.app.pages

import groovy.transform.ToString;

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

class PageDefs {

	private static List<PageDefEntry> pageDefEntries = new ArrayList();
	static {

		// Define the Pages available...  (page-key, page-name, page-className)
		pageDefEntries.add( new PageDefEntry("home", "POD.WORKS", null))
		pageDefEntries.add( new PageDefEntry("login", "Login", "com.KPPhaseTwo.app.pages.user.LoginPage"))
		pageDefEntries.add( new PageDefEntry("bifurcateUser", "Register", null))
		pageDefEntries.add( new PageDefEntry("companyRegistration", "Company Registration", "com.KPPhaseTwo.app.pages.user.CompanyRegistrationPage"))

		//User, dashboard
		pageDefEntries.add( new PageDefEntry("dashboard", "Dashboard", "com.KPPhaseTwo.app.pages.user.DashboardPage"))
		pageDefEntries.add( new PageDefEntry("individaulRegister", "Register User", "com.KPPhaseTwo.app.pages.user.IndividualRegisterPage"))

		//Forgot password
		pageDefEntries.add( new PageDefEntry("forgotPassword", "Forgot Password", "com.KPPhaseTwo.app.pages.user.ForgotPasswordPage"))

		//manage Admins
		pageDefEntries.add( new PageDefEntry("manageAdmins", "Manage Admins", "com.KPPhaseTwo.app.pages.admins.ManageAdminsPage"))
		pageDefEntries.add( new PageDefEntry("addAdmin", "Add Admin", "com.KPPhaseTwo.app.pages.admins.AddAdminPage"))

		//Change password
		pageDefEntries.add( new PageDefEntry("changePassword", "Change Password", "com.KPPhaseTwo.app.pages.user.ChangePasswordPage"))

		//Edit Organization Profile
		pageDefEntries.add( new PageDefEntry("editOrganizationProfile", "Edit Organization Profile", "com.KPPhaseTwo.app.pages.admins.EditOrganizationProfilePage"))

		//View Organization Profile
		pageDefEntries.add( new PageDefEntry("viewOrganizationProfile", "Organization Profile", "com.KPPhaseTwo.app.pages.admins.ViewOrganizationProfilePage"))

		//Search Pods
		pageDefEntries.add( new PageDefEntry("searchPods", "Search Pods", "com.KPPhaseTwo.app.pages.pods.SearchPodPage"))


		//Pod Wishlist
		pageDefEntries.add( new PageDefEntry("podWishList", "Pods Wishlist", "com.KPPhaseTwo.app.pages.pods.PodWishlistPage"))

		//Terms and Privacy
		pageDefEntries.add( new PageDefEntry("privacyPolicy", "Privacy Policy", null))
		pageDefEntries.add( new PageDefEntry("termsOfUse", "Terms of Use", null))
		pageDefEntries.add( new PageDefEntry("aboutUs", "About Us", null))
		pageDefEntries.add( new PageDefEntry("contactSupport", "Contact Support", null))

		//Recommended Training
		pageDefEntries.add( new PageDefEntry("recommendedTraining", "Recommended Training", "com.KPPhaseTwo.app.pages.pods.RecommendedTrainingPage"))

		//Ongoing Training
		pageDefEntries.add( new PageDefEntry("ongoingTraining", "Ongoing Training", "com.KPPhaseTwo.app.pages.pods.OngoingTrainingPage"))

		//Recommended Pods
		pageDefEntries.add( new PageDefEntry("recommendedPods", "Recommended Pods", "com.KPPhaseTwo.app.pages.pods.RecommendedPodsPage"))


		//Ongoing Pods
		pageDefEntries.add( new PageDefEntry("ongoingPods","Ongoing Pods","com.KPPhaseTwo.app.pages.pods.OngoingPodsPage"))

		//Completed Pods
		pageDefEntries.add( new PageDefEntry("completedPods","Completed Pods","com.KPPhaseTwo.app.pages.pods.CompletedPodsPage"))

		//Change Privlages page
		pageDefEntries.add( new PageDefEntry("changePrivilages", "Change privileges", "com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage"))

		//Post Job
		pageDefEntries.add( new PageDefEntry("postJob","Job Post","com.KPPhaseTwo.app.pages.admins.PostJobPage"))
		pageDefEntries.add( new PageDefEntry("jobPostList","Job Postings List","com.KPPhaseTwo.app.pages.admins.JobPostingListPage"))
		pageDefEntries.add( new PageDefEntry("jobDetail","Job Details","com.KPPhaseTwo.app.pages.admins.JobDetailPage"))
		pageDefEntries.add( new PageDefEntry("repostJob","Repost Job","com.KPPhaseTwo.app.pages.admins.RePostJobPage"))

		//Purchase Details Page
		pageDefEntries.add ( new PageDefEntry("paymentRequest","Payment Request", "com.KPPhaseTwo.app.pages.pods.BuyAPodPage"))

		//Sales Admin License Details Page
		pageDefEntries.add( new PageDefEntry("licenseDetails", "License Details", "com.KPPhaseTwo.app.pages.admins.SalesAdminLicenseDetailsPage"))

		//Followed Organization List
		pageDefEntries.add( new PageDefEntry("followedOrganizationList","Followed Organizations List","com.KPPhaseTwo.app.pages.admins.FollowedOrganizationsListPage"))
		pageDefEntries.add( new PageDefEntry("searchOrganizations","Search Organizations Result","com.KPPhaseTwo.app.pages.admins.FollowedOrganizationsListPage"))
		pageDefEntries.add( new PageDefEntry("viewFollowedOrg","View Followed Organizations",null))

		//Manage Users
		pageDefEntries.add( new PageDefEntry("manageUser","Manage Users","com.KPPhaseTwo.app.pages.admins.ManageUserPage"))
		pageDefEntries.add( new PageDefEntry("participantList","Participate List",null))

		//Pod Customization
		pageDefEntries.add( new PageDefEntry("podCustomization","Pods Customization","com.KPPhaseTwo.app.pages.pods.PodCustomizationPage"))


		//Profile Wishlist page
		pageDefEntries.add( new PageDefEntry("profileWishList","Viweing Wish List Candidates","com.KPPhaseTwo.app.pages.admins.ProfileWishlistPage"))

		//Pod Overview page
		pageDefEntries.add( new PageDefEntry("overview","Overview", "com.KPPhaseTwo.app.pages.pods.PodDetailsPage"))

		//Participant result list page
		pageDefEntries.add( new PageDefEntry("participantResultList","Participant Result List",null))

		//Pod Progress details page
		pageDefEntries.add( new PageDefEntry("podProgressDetails","Pod Progress Details", "com.KPPhaseTwo.app.pages.pods.PodProgressDetailsPage"))

		//Course Licenses details page
		pageDefEntries.add( new PageDefEntry("courseLicenses","Course Licenses","com.KPPhaseTwo.app.pages.pods.CourseLicensesPage"))

		//Approve License page
		pageDefEntries.add( new PageDefEntry("approveLicense","Approve License", "com.KPPhaseTwo.app.pages.admins.ApproveLicensePage"))

		//Pod Forum page
		pageDefEntries.add( new PageDefEntry("podForum","Pod Forum",null))

		//Classroom page
		pageDefEntries.add( new PageDefEntry("classroom","Classroom","com.KPPhaseTwo.app.pages.pods.ClassRoomPage"))

		//Certificate Page
		pageDefEntries.add( new PageDefEntry("certificate","Certificate",null))

		//User Connections
		pageDefEntries.add( new PageDefEntry("userConnection","User Connections","com.KPPhaseTwo.app.pages.user.UserConnectionPage"))
		pageDefEntries.add( new PageDefEntry("searchForConnection","Search For Connection Result","com.KPPhaseTwo.app.pages.user.SearchConnectionPage"))

		//Applicant Profile List
		pageDefEntries.add( new PageDefEntry("applicantProfileList","Applicant Profiles List","com.KPPhaseTwo.app.pages.admins.ApplicantProfileListPage"))

		//User Edit Profile
		pageDefEntries.add( new PageDefEntry("userViewProfile","View Public Profile", "com.KPPhaseTwo.app.pages.user.ViewUserPublicProfilePage"))
		pageDefEntries.add( new PageDefEntry("editProfile", "Edit Profile", "com.KPPhaseTwo.app.pages.user.UserEditProfilePage"))

		//Add Participant
		pageDefEntries.add( new PageDefEntry("inviteUser","Invite User","com.KPPhaseTwo.app.pages.admins.InviteUserPage"))

		//Edit User Email
		pageDefEntries.add( new PageDefEntry("editUserEmail","Edit Email And Send ActivationLink","com.KPPhaseTwo.app.pages.admins.EditUserEmailIdPage"))

		//Manage Sites
		pageDefEntries.add( new PageDefEntry("manageSites","Manage Sites","com.KPPhaseTwo.app.pages.admins.ManageSitePage"))

		//Add Site
		pageDefEntries.add( new PageDefEntry("addSite","Add Site","com.KPPhaseTwo.app.pages.admins.AddSitePage"))

		//Site Details
		pageDefEntries.add( new PageDefEntry("siteDetails","Site Details",null))

		//Edit Site
		pageDefEntries.add( new PageDefEntry("editSite","Edit Site","com.KPPhaseTwo.app.pages.admins.AddSitePage"))

		//News Feeds page
		pageDefEntries.add( new PageDefEntry("newsFeeds","News Feeds","com.KPPhaseTwo.app.pages.admins.PostFeedPage"))
		pageDefEntries.add( new PageDefEntry("newsArticle","News Article",null))

		//Bulk License Page
		pageDefEntries.add( new PageDefEntry("bulkLicense","Bulk License","com.KPPhaseTwo.app.pages.pods.BulkLicensePage"))

		//Bulk License payment Page
		pageDefEntries.add( new PageDefEntry("bulkLicensePayment","Bulk License Payment","com.KPPhaseTwo.app.pages.pods.BulkLicensePaymentPage"))

		pageDefEntries.add( new PageDefEntry("editJobPost","Edit Job Post",null))

		//pageDefEntries.add( new PageDefEntry("repostJob","Repost Job",null))

		pageDefEntries.add( new PageDefEntry("migrateUser","Migrate User",null))

		//Manage Pod License Page
		pageDefEntries.add( new PageDefEntry("managePodLicense", "Manage Pods License","com.KPPhaseTwo.app.pages.pods.ManagePodLicensePage"))

		pageDefEntries.add( new PageDefEntry("participantListTab","Participant List Tab",null))
		
		pageDefEntries.add( new PageDefEntry("manageSiteLicense","Manage Site License",null))
		
	}

	//Get Key for the current page
	public static getCurrentPageKey(def browser){
		def pageName
		browser.delay(1000)
		pageName = browser.getTitle()
		if(browser.isDisplayed(".//button[@ng-click='downloadProgressReportForParticipantList()']") && browser.gettext(".//li[@ng-if='showPartcipantList']/a").trim().equalsIgnoreCase("Participant List")){
			pageName = "Participant List Tab"
		}else if(!pageName.equalsIgnoreCase("Job Details") && browser.getElement(Browser.XPATH, "//a[text()='Overview']")!= null && browser.isDisplayed("//a[text()='Overview']")){
			pageName = "Overview"
		}else if(pageName.equalsIgnoreCase("Recommended Jobs")){
			pageName = "Job Postings List"
		}else if(pageName.equalsIgnoreCase("Manage Users") && browser.isDisplayed(".//h4[@class='modal-title']")){
			pageName = "Edit Email And Send ActivationLink"
		}else if(pageName.equalsIgnoreCase("Manage Users") && browser.isDisplayed(".//h4[@class='modal-title ng-binding']")){
			pageName = "Migrate User"
		}
		println "Page Name :" +pageName
		return findKeyByPageName(pageName.trim())
	}


	/* Get PageDefEntry object for specified key */
	public static PageDefEntry getPageDefEntry (def pageKey) {
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.key.equalsIgnoreCase(pageKey))
				return pageDefEntry;
		}
		return null;
	}

	private static findKeyByPageName(String pageName){
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.name.equalsIgnoreCase(pageName))
				return pageDefEntry.key;
		}
		return null;
	}
}
