package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.app.pages.user.LoginPage
import com.KPPhaseTwo.app.pages.admins.ManageAdminsPage
import com.KPPhaseTwo.app.pages.user.DashboardPage
import com.KPPhaseTwo.app.pages.admins.EditOrganizationProfilePage
import com.KPPhaseTwo.app.pages.admins.ViewOrganizationProfilePage
import com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage
import com.KPPhaseTwo.app.pages.user.UserEditProfilePage
import com.KPPhaseTwo.app.pages.admins.AddAdminPage
import com.KPPhaseTwo.app.pages.admins.PostJobPage
import com.KPPhaseTwo.app.pages.admins.SalesAdminLicenseDetailsPage
import com.KPPhaseTwo.app.pages.user.ViewUserPublicProfilePage
import com.KPPhaseTwo.app.pages.admins.ParticipantListPage
import com.KPPhaseTwo.app.pages.admins.InviteUserPage
import com.KPPhaseTwo.app.pages.pods.SearchPodPage
import com.KPPhaseTwo.app.pages.admins.JobDetailPage
import com.KPPhaseTwo.app.pages.admins.JobPostingListPage
import com.KPPhaseTwo.app.pages.admins.ProfileWishlistPage
import com.KPPhaseTwo.app.pages.user.UserConnectionPage
import com.KPPhaseTwo.app.pages.user.SearchConnectionPage
import com.KPPhaseTwo.app.pages.user.IndividualRegisterPage
import com.KPPhaseTwo.app.pages.user.CompanyRegistrationPage
import com.KPPhaseTwo.app.pages.admins.ApplicantProfileListPage
import com.KPPhaseTwo.app.pages.admins.ManageUserPage
import com.KPPhaseTwo.app.pages.admins.ManageSitePage
import com.KPPhaseTwo.app.pages.admins.SiteDetailPage
import com.KPPhaseTwo.app.pages.admins.AddSitePage
import com.KPPhaseTwo.app.pages.pods.OngoingTrainingPage
import com.KPPhaseTwo.app.pages.admins.MigrateUserPage
import com.KPPhaseTwo.app.pages.admins.RePostJobPage
import com.KPPhaseTwo.app.pages.admins.ParticipantListTabPage

/**
 * Created by Sandhya on 27/9/2015
 */
class UserManager {

	//Marks the trainer create status true when trainer is created successfully
	def trainerCreateStatus = { browser, formData ->
		AddAdminPage.trainerCreateStatus(browser, formData)
	}

	//Marks the user create status as true when user iscreated successully
	def changeUserCreateStatus = { browser, formData ->
		InviteUserPage.changeUserCreateStatus(browser, formData)
	}

	def searchParticipant = { browser, formData ->
		ParticipantListPage.searchParticipant(browser,formData)
	}

	//To verify that the logged in user is displayed in the edit profile page

	def loginAsSiteAdmin = { browser, formData ->
		LoginPage.loginAsSiteAdmin(browser,formData)
	}

	//To login as an enrolled user
	def loginWithEnroledUser = { browser,formData->
		LoginPage.loginWithEnroledUser browser, formData
	}

	def correctUserLoggedIn = { browser, formData ->
		LoginPage.correctUserLoggedIn browser, formData
	}

	//Login to the application with removed admin's credential
	def loginAsAnAdmin = { browser, formData ->
		LoginPage.loginAsAnAdmin browser,formData
	}

	//Ongoing Trainings
	def searchOngoingTrainings = { browser, formData ->
		OngoingTrainingPage.searchOngoingTrainings browser,formData
	}

	//EDIT PROFILE

	def profilePercentagecompletion = { browser, formData ->
		ViewUserPublicProfilePage.profilePercentagecompletion browser, formData
	}

	def leftSideDetailsOfEditProfile = { browser, formData ->
		UserEditProfilePage.leftSideDetailsOfEditProfile browser, formData
	}

	def leftSideDetailsOfViewProfile = { browser, formData ->
		ViewUserPublicProfilePage.leftSideDetailsOfViewProfile browser, formData
	}

	def educationDetailsMatch = { browser, formData ->
		ViewUserPublicProfilePage.educationDetailsMatch browser, formData
	}

	def workExperienceDetailsMatch = { browser, formData ->
		ViewUserPublicProfilePage.workExperienceDetailsMatch browser, formData
	}

	def deleteExistingSkills = { browser, formData ->
		UserEditProfilePage.deleteExistingSkills browser, formData
	}

	def skillsRetained = { browser, formData ->
		UserEditProfilePage.skillsRetained browser, formData
	}

	def removeASkill = { browser, formData ->
		UserEditProfilePage.removeASkill browser, formData
	}

	def skillsRemoved = { browser, formData ->
		UserEditProfilePage.skillsRemoved browser, formData
	}

	def uploadProfilepic = { browser, formData ->
		UserEditProfilePage.uploadProfilepic browser, formData
	}

	def uploadUserImgDisplay = { browser, formData ->
		ViewUserPublicProfilePage.uploadUserImgDisplay browser,  formData
	}

	def deleteWorkExperience = { browser, formData ->
		UserEditProfilePage.deleteWorkExperience browser,  formData
	}

	def workExperienceDeleted = { browser, formData ->
		ViewUserPublicProfilePage.workExperienceDeleted browser,  formData
	}

	def ifWorkExperienceDisplayed = { browser, formData ->
		ViewUserPublicProfilePage.ifWorkExperienceDisplayed browser,  formData
	}

	def editWorkExperienceDetails = { browser, formData ->
		UserEditProfilePage.editWorkExperienceDetails browser,  formData
	}

	def certificateMatch = { browser, formData ->
		ViewUserPublicProfilePage.certificateMatch browser,  formData
	}

	def editDocumentsDetails = { browser,formData ->
		UserEditProfilePage.editDocumentsDetails browser, formData
	}

	def deleteCertificate = { browser, formData ->
		UserEditProfilePage.deleteCertificate browser,  formData
	}

	def skillsDisplayed = { browser,formData ->
		ViewUserPublicProfilePage.skillsDisplayed(browser,formData)
	}

	def getUserProfileDetails = { browser, formData ->
		ViewUserPublicProfilePage.getUserProfileDetails(browser,formData)
	}

	def userBasicInformationMatch = { browser, formData ->
		ViewUserPublicProfilePage.userBasicInformationMatch(browser,formData)
	}

	def ifcertificateDeleted = { browser, formData ->
		ViewUserPublicProfilePage.ifcertificateDeleted(browser,formData)
	}

	//DASHBOARD PAGE

	//To logout from the application
	def logOut = { browser, formData ->
		DashboardPage.logOut browser,formData
	}

	//Organization name verification
	def orgNameChangeOnDashboard = {browser, formData ->
		DashboardPage.orgNameChangeOnDashboard browser, formData
	}

	//To get the number of pods in Recommended from dashboard
	def getPodNumber = { browser,formData ->
		DashboardPage.getPodNumber browser,formData
	}

	def loggedInAsTrainer = { browser, formData ->
		DashboardPage.loggedInAsTrainer browser, formData
	}

	def loggedInAsJobAdmin = { browser, formData ->
		DashboardPage.loggedInAsJobAdmin browser, formData
	}

	def loggedInAsSubAdmin = { browser, formData ->
		DashboardPage.loggedInAsSubAdmin browser, formData
	}

	def loggedInAsSiteAdmin = { browser, formData ->
		DashboardPage.loggedInAsSiteAdmin browser, formData
	}

	//EDIT ORGANIZATION PROFILE

	//To verify that the necessary fields are entered in Edit profile page with correct data.
	def profileDataDisplayMatch = { browser, formData ->
		EditOrganizationProfilePage.profileDataDisplayMatch browser, formData
	}

	//To remove all industries selected.
	def removeAllIndustry= { browser, formData ->
		EditOrganizationProfilePage.removeAllIndustry browser, formData
	}

	//To verify the error message after removing industries.
	def indErrorMessageMap = { browser, formData ->
		EditOrganizationProfilePage.indErrorMessageMap browser, formData
	}

	//To verify the removal of industries
	def removeIndustries = { browser, formData ->
		EditOrganizationProfilePage.removeIndustries browser, formData
	}

	//To verify removal of particular industries
	def industryRemoved = { browser, formData ->
		EditOrganizationProfilePage.industryRemoved browser, formData
	}

	//To perform upload of logo of Organization
	def orgProfileImgUpload = {browser, formData ->
		EditOrganizationProfilePage.orgProfileImgUpload browser, formData
	}

	//To get First name of the Org Admin
	def getOrgFirstName = {browser, formData ->
		EditOrganizationProfilePage.getOrgFirstName browser, formData
	}

	//VIEW ORGANIZATION PROFILE

	//To verify data after editing data
	def editDataMatch = { browser, formData ->
		ViewOrganizationProfilePage.editDataMatch browser, formData
	}

	//To verify About Organization data display.
	def aboutOrgDataDisplay = { browser, formData ->
		ViewOrganizationProfilePage.aboutOrgDataDisplay browser, formData
	}

	//To verify image displayed in View Profile page
	def uploadImgDisplay = { browser, formData ->
		ViewOrganizationProfilePage.uploadImgDisplay browser,  formData
	}

	//To verify Latest News data display.
	def aboutLatestNewsDisplay = { browser, formData ->
		ViewOrganizationProfilePage.aboutLatestNewsDisplay browser, formData
	}

	//MANAGE ADMINS PAGE

	//To select the admin filter  with the data from the formData
	def selectAdminFilter = { browser, formData ->
		ManageAdminsPage.selectAdminFilter browser,formData
	}

	//To search for the created admin on the Manage admins page
	def searchForCreatedAdmin = { browser, formData ->
		ManageAdminsPage.searchForCreatedAdmin browser,formData
	}

	//To verify if the selected filter value mathes with the admin role displayed
	def allDisplayedAdminRoles = { browser, formData ->
		ManageAdminsPage.allDisplayedAdminRoles browser,formData
	}

	//To click on the Admin name to view admin public profile
	def clickOnAdminName = { browser, formData ->
		ManageAdminsPage.clickOnAdminName browser,formData
	}

	//To verify the admin details on the view public profile page
	def adminDetailsOnPublicProfile = { browser, formData ->
		ManageAdminsPage.adminDetailsOnPublicProfile browser,formData
	}

	//This method verifies the trainer admin should not have a site when privilages(removes the site) are changed by site admin
	def ifRemovedAdminHasSite = { browser, formData ->
		ManageAdminsPage.ifRemovedAdminHasSite browser,formData
	}

	//To demote an admin from the admins list to make him a  user
	def demoteAnAdmin = { browser, formData ->
		ManageAdminsPage.demoteAnAdmin browser,formData
	}

	//Trainer admin is created and displayed in the Manage admin list of site admin
	def ifTrainerAdminCreated = { browser, formData ->
		ManageAdminsPage.ifTrainerAdminCreated browser,formData
	}

	//To check when trainer is removed should not be displayed on the Manage Admins in Site Admin role
	def ifTrainerIsDisplayedAfterRemove = { browser, formData ->
		ManageAdminsPage.ifTrainerIsDisplayedAfterRemove browser,formData
	}

	//Manage user
	def usersDisplayed = { browser, formData ->
		ManageUserPage.usersDisplayed browser,formData
	}

	def ifCorrectUserDisplayed = { browser, formData ->
		ManageUserPage.ifCorrectUserDisplayed browser,formData
	}

	def searchForUser = { browser, formData ->
		ManageUserPage.searchForUser browser,formData
	}

	def clickOnUserName = { browser, formData ->
		ManageUserPage.clickOnUserName browser,formData
	}

	def userDetailsOnPublicProfile = { browser, formData ->
		ManageUserPage.userDetailsOnPublicProfile browser,formData
	}

	def removeUserFromOrg = { browser, formData ->
		ManageUserPage.removeUserFromOrg browser,formData
	}

	def ifUserIsRemoved = { browser, formData ->
		ManageUserPage.ifUserIsRemoved browser,formData
	}

	def clickToEditUserEmail = { browser, formData ->
		ManageUserPage.clickToEditUserEmail browser,formData
	}

	def ifSameEmailIsDisplayed = { browser, formData ->
		ManageUserPage.ifSameEmailIsDisplayed browser,formData
	}

	def selectUserSiteFromFilter = { browser, formData ->
		ManageUserPage.selectUserSiteFromFilter browser,formData
	}

	def manageUserListSorted = { browser, formData ->
		ManageUserPage.manageUserListSorted browser,formData
	}

	//Migrate User
	def clickMigrateUserlink = { browser, formData ->
		MigrateUserPage.clickMigrateUserlink browser,formData
	}

	def migratePopUpDisplayed = { browser, formData ->
		MigrateUserPage.migratePopUpDisplayed browser,formData
	}

	def userDetailsOnMigratePopUp = { browser, formData ->
		MigrateUserPage.userDetailsOnMigratePopUp browser,formData
	}

	def migrateUserToAnotherSite = { browser, formData ->
		MigrateUserPage.migrateUserToAnotherSite browser,formData
	}

	//Verify that admin has been removed from the admins list
	def isAdminRemoved = { browser, formData ->
		ManageAdminsPage.isAdminRemoved browser,formData
	}

	//To click on the the change privilage link of an admin
	def clickOnChangePrivilages = { browser, formData ->
		ManageAdminsPage.clickOnChangePrivilages browser, formData
	}

	//To click on the the change privilage link of an admin
	def clickAdminChangePrivilageLink = { browser, formData ->
		ManageAdminsPage.clickAdminChangePrivilageLink browser, formData
	}

	//To verify job title after posting a job
	def correctJobDisplayed = { browser, formData ->
		JobPostingListPage.correctJobDisplayed browser ,formData
	}
	
	//To click  on the first job on the job post list page
	def clickOnFirstJob = { browser, formData->
		JobPostingListPage.clickOnFirstJob browser ,formData
	}
	
	//To check if the job status is not publised when job posting is greater than the current date on job posting list page
	def ifJobStatusIsNotPublished = { browser, formData->
		JobPostingListPage.ifJobStatusIsNotPublished browser ,formData
	}
	
	//To check if the job status is publised when job posting is current date on job posting list page
	def ifJobStatusIsPublished = { browser, formData->
		JobPostingListPage.ifJobStatusIsPublished browser ,formData
	}

	//Match the job details which was posted by organization admin
	def jobDetails = { browser, formData ->
		JobDetailPage.jobDetails browser ,formData
	}

	def correctApplicantDetails = { browser, formData ->
		ApplicantProfileListPage.correctApplicantDetails browser ,formData
	}

	//Apply  for the posted job
	def applyForJob = { browser, formData ->
		JobDetailPage.applyForJob browser ,formData
	}

	def changeSelectNextDateStatus = { browser, formData ->
		PostJobPage.changeSelectNextDateStatus browser ,formData
	}

	def getOrgName = { browser, formData ->
		DashboardPage.getOrgName browser ,formData
	}


	//Verify that added admin is displayed in the list
	def ifAdminCreated = { browser, formData ->
		ManageAdminsPage.ifAdminCreated browser,formData
	}

	//CHANGE PRIIVILEGES PAGE

	def adminPrivilageChanged = { browser, formData ->
		ChangePrivilagesPage.adminPrivilageChanged browser, formData
	}

	//ADD ADMIN PAGE

	def addAnotherAdminErrorMessage = { browser, formData ->
		AddAdminPage.addAnotherAdminErrorMessage browser, formData
	}

	//SALES ADMIN

	def clickPodReview = {browser, formData ->
		SalesAdminLicenseDetailsPage.clickPodReview browser, formData
	}

	//Participant list page

	def enrollUserToPod = { browser, formData ->
		ParticipantListPage.enrollUserToPod(browser,formData)
	}

	def ifUserIsEnrolled = { browser, formData ->
		ParticipantListPage.ifUserIsEnrolled browser, formData
	}


	def learnerListSorted = { browser, formData ->
		ParticipantListPage.learnerListSorted browser, formData
	}

	def searchUserByFilter = { browser, formData ->
		ParticipantListPage.searchUserByFilter browser, formData
	}

	def enrollUserToAnotherPod = { browser, formData ->
		ParticipantListPage.enrollUserToAnotherPod browser, formData
	}

	def userRemovedStatus = { browser, formData ->
		ParticipantListPage.userRemovedStatus browser,formData
	}

	def ifLicenceAvailable = { browser, formData ->
		ParticipantListPage.ifLicenceAvailable browser,formData
	}

	def searchParticipantList = { browser, formData ->
		ParticipantListPage.searchParticipantList browser,formData
	}

	def userDisplayedOnPage = { browser, formData->
		ParticipantListPage.userDisplayedOnPage browser, formData
	}

	//Login

	def loginWithInvitedUser = { browser,formData->
		LoginPage.loginWithInvitedUser browser, formData
	}

	def ifUserNotAllowedToLogin = { browser,formData->
		LoginPage.ifUserNotAllowedToLogin browser, formData
	}

	//PROFILE WISHLIST

	def isProfileWishlistDisplayed = { browser, formData ->
		ProfileWishlistPage.isProfileWishlistDisplayed browser, formData
	}

	def isCorrectProfileDisplayed = { browser, formData ->
		ProfileWishlistPage.isCorrectProfileDisplayed browser, formData
	}

	def isCorrectProfileWithOrg = {browser, formData ->
		ProfileWishlistPage.isCorrectProfileWithOrg browser, formData
	}


	//USER CONNECTIONS

	def userListDisplayed = {browser, formData ->
		UserConnectionPage.userListDisplayed browser,formData
	}

	def static getFirstConnectedName = {browser, formData ->
		UserConnectionPage.getFirstConnectedName browser, formData
	}

	def static correctUserProfile = {browser, formData ->
		UserConnectionPage.correctUserProfile browser, formData
	}

	def static correctUserDisplayed = {browser, formData ->
		UserConnectionPage.correctUserDisplayed browser, formData
	}

	def static correctOrgDisplayed = {browser, formData ->
		UserConnectionPage.correctOrgDisplayed browser, formData
	}

	def static correctStatusResultDisplayed = {browser, formData ->
		UserConnectionPage.correctStatusResultDisplayed browser, formData
	}


	def static getUserToBeBlocked = {browser, formData ->
		UserConnectionPage.getUserToBeBlocked browser, formData
	}

	def static inputParticipantDetails = {browser, formData ->
		UserConnectionPage.inputParticipantDetails browser, formData
	}

	def static inputPodName = {browser, formData ->
		UserConnectionPage.inputPodName browser, formData
	}

	def static inputOfEnrolledUser = {browser, formData ->
		UserConnectionPage.inputOfEnrolledUser browser, formData
	}

	def static changePasswordOperation = {browser, formData ->
		UserConnectionPage.changePasswordOperation browser, formData
	}

	def static getFirstRequestingName = {browser, formData ->
		UserConnectionPage.getFirstRequestingName browser, formData
	}

	def static getProfileNameAndEmailId = {browser, formData ->
		UserConnectionPage.getProfileNameAndEmailId browser, formData
	}

	def static searchForInputUser = {browser, formData ->
		UserConnectionPage.searchForInputUser browser, formData
	}


	//SEARCH CONNECTION
	def static correctUser = {browser, formData ->
		SearchConnectionPage.correctUser browser, formData
	}

	def static correctLocationDisplayed = {browser, formData ->
		SearchConnectionPage.correctLocationDisplayed browser, formData
	}

	def static successMsgOnConnect = {browser, formData ->
		SearchConnectionPage.successMsgOnConnect browser,formData
	}

	//to verify the success message
	def static successMsgOnBlock = {browser, formData ->
		SearchConnectionPage.successMsgOnBlock browser,formData
	}

	def static getOTP = {browser, formData ->
		IndividualRegisterPage.getOTP(browser,  formData)
	}

	def static getOrgOTP = {browser, formData ->
		CompanyRegistrationPage.getOrgOTP browser, formData
	}

	def static isEmailIDVerified = {browser, formData ->
		CompanyRegistrationPage.isEmailIDVerified browser, formData
	}



	//Manage Sites
	//To check if the created site is displayed on the manage site page
	def correctSiteDisplayed = { browser, formData ->
		ManageSitePage.correctSiteDisplayed browser,formData
	}

	//Click on the site name which is created to go to site detail page
	def clickOnSiteName = { browser, formData ->
		ManageSitePage.clickOnSiteName browser,formData
	}

	//Search for a created site  using the search bar on Manage Site page 
	def searchForSite = { browser, formData ->
		ManageSitePage.searchForSite browser,formData
	}

	//Click on the edit site link of site
	def clickEditLink  = { browser, formData ->
		ManageSitePage.clickEditLink browser,formData
	}

	//To verify if the Default site is created automatically when an org is created
	def defaultSiteDisplayed = { browser, formData ->
		ManageSitePage.defaultSiteDisplayed browser,formData
	}

	//To check if the site list is sorted in A-Z or Z-A format
	def manageSiteListSorted = { browser, formData ->
		ManageSitePage.manageSiteListSorted browser,formData
	}

	//To select the location filter on manage site page
	def selectLocationFilter =  { browser, formData ->
		ManageSitePage.selectLocationFilter browser,formData
	}

	//Site Detail Page
	def ifTrainerAssignedToSite = { browser, formData ->
		SiteDetailPage.ifTrainerAssignedToSite browser,formData
	}

	def correctSiteDetails = { browser, formData ->
		SiteDetailPage.correctSiteDetails browser,formData
	}

	def correctSiteDetailsOnLeftSide = { browser, formData ->
		SiteDetailPage.correctSiteDetailsOnLeftSide browser,formData
	}

	def sameLocationAsFilter = { browser, formData ->
		SiteDetailPage.sameLocationAsFilter browser,formData
	}

	//Follow Organization
	def isErrorMessageCorrect = { browser, formData ->
		FollowedOrganizationsListPage.isErrorMessageCorrect browser,formData
	}

	//Job posting list page
	//To click on the close job link of the first job  on the job post list page
	def closeFirstJob = { browser, formData ->
		JobPostingListPage.closeFirstJob(browser, formData)
	}

	//To check the first job status is closed after closing the job
	def ifJobStatusIsClosed = { browser, formData ->
		JobPostingListPage.ifJobStatusIsClosed(browser, formData)
	}

	//To check if the closed job is not displayed in the Recommended job list for enrolled or Individual User
	def ifClosedJobNotDisplayed = { browser, formData ->
		JobPostingListPage.ifClosedJobNotDisplayed(browser, formData)
	}

	//To search for the posted job
	def searchforPostedJob =  { browser, formData ->
		JobPostingListPage.searchforPostedJob(browser, formData)
	}

	//Repost Job page
	def getRepostJobFieldDetails = { browser, formData ->
		RePostJobPage.getRepostJobFieldDetails(browser, formData)
	}

	def ifRepostJobHasSameDetails = { browser, formData ->
		RePostJobPage.ifRepostJobHasSameDetails(browser, formData)
	}

	def changeJobListClearStatus = { browser, formData ->
		RePostJobPage.changeJobListClearStatus(browser, formData)
	}

	def getAllOngoingPodNames =  { browser, formData ->
		RePostJobPage.ifAllPurchedPodsAreDisplayed(browser, formData)
	}

	//
	def ifOngoingTabIsHighlighted = { browser,formData ->
		ParticipantListTabPage.ifOngoingTabIsHighlighted(browser, formData)
	}

	def searchForParticipant =  { browser,formData ->
		ParticipantListTabPage.searchForParticipant(browser, formData)
	}

	def participantDisplayedOnPage = { browser,formData ->
		ParticipantListTabPage.participantDisplayedOnPage(browser, formData)
	}

	def ifParticipantIsEnrolled =  { browser,formData ->
		ParticipantListTabPage.ifParticipantIsEnrolled(browser, formData)
	}

	def selectSiteFilter =  { browser,formData ->
		ParticipantListTabPage.selectSiteFilter(browser, formData)
	}

	def podProgressPercentageIsZero =  { browser,formData ->
		ParticipantListTabPage.podProgressPercentageIsZero(browser, formData)
	}

	def participantTabListSorted = { browser,formData ->
		ParticipantListTabPage.participantTabListSorted(browser, formData)
	}
}
