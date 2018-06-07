package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 01/12/2017
 */

final class ViewUserPublicProfilePage extends WebPage {

	//To verify image displayed in View Profile page

	def static profilePercentagecompletion = { browser, formData ->
		new ViewUserPublicProfilePageForm().profilePercentagecompletion browser, formData
	}

	def static leftSideDetailsOfViewProfile = { browser, formData ->
		new ViewUserPublicProfilePageForm().leftSideDetailsOfViewProfile browser, formData
	}

	def static userBasicInformationMatch = { browser, formData ->
		new ViewUserPublicProfilePageForm().userBasicInformationMatch(browser,formData)
	}
	

	def static getUserProfileDetails = { browser, formData ->
		new ViewUserPublicProfilePageForm().getUserProfileDetails(browser,formData)
	}

	def static uploadUserImgDisplay = { browser, formData ->
		new ViewUserPublicProfilePageForm().uploadUserImgDisplay(browser,  formData)
	}

	def static educationDetailsMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().educationDetailsMatch(browser,formData)
	}

	def static workExperienceDetailsMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().workExperienceDetailsMatch(browser,formData)
	}

	def static ifWorkExperienceDisplayed = { browser, formData ->
		new ViewUserPublicProfilePageForm().ifWorkExperienceDisplayed browser,  formData
	}

	def static skillsDisplayed = { browser,formData ->
		new ViewUserPublicProfilePageForm().skillsDisplayed(browser,formData)
	}

	def static certificateMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().certificateMatch(browser,formData)
	}

	def static ifcertificateDeleted = { browser,formData ->
		new ViewUserPublicProfilePageForm().ifcertificateDeleted(browser,formData)
	}


	static final class ViewUserPublicProfilePageForm extends WebForm {

		private static final def NAME_ON_EDIT_PROFILE    = ".//*[@id='edit_profile']/h3"

		private static final def NAME_ON_VIEW_PROFILE    = ".//div[@class='col-md-4']/h3"

		private static final def EMAIL_ON_EDIT_PROFILE   = "//h5[i[contains(@ng-show,'emailId')]]"

		private static final def BASIC_INFO_OF_USER      = ".//div[@class='row']/div[2]/span"

		private static final def LEFT_BASIC_INFO_OF_USER = ".//div[@class='col-md-4']/h5"

		private static final def EMAIL_ON_VIEW_PROFILE   = ".//h5 [@class='p_5 text-center profile-text word-wr ng-binding']"

		private static final def VIEW_PROFILE_LINK       = ".//div[@class='user-profile-container']//button[@ng-click='viewUserProfile()']"

		private static final def IMAGE_SOURCE            = ".//img[@class='profile-pic']"

		private static final def EDU_DETAILS             = ".//div[@aria-hidden='false']//p"

		private static final def WORKEXPERIENCE_DETAILS  = ".//div[@ng-repeat='userProfiles in userProfile.kpUserExperienceJSON ']//p"

		private static final def DOCNAME_VIEWPROFILE     = ".//label[@name='docName']"

		private static final def SCROLL_TO_VIEW_DOC      = ".//div[@class='col-md-12 col-xs-12']/h4"

		private static final def SCROLL_TO_ELEMENT       = ".//h4[@class='heading']"

		private static final def SCROLL_TO_VIEW_SKILLS   = ".//h4[@class='eduHdr word-wr']"

		private static final def SKILLS_ON_VIEW_PROFILE  = ".//li[@class='tagSkills prfleDetails capitalize word-wr ng-binding']"

		private static final def PERCENTAGE              = ".//h4[@id='profile_percent']"

		private static final def LEFTSIDE_NAME           = ".//h3[@class='profile-name text-center profile-text']"

		private static final def LEFTSIDE_EMAIL          = ".//h5[@class='p_5 text-center profile-text word-wr ng-binding']"

		private static final def LEFTSIDE_MOBNUM         = ".//h5[@class='p_5 text-center profile-text ng-binding']"

		private static final def LEFTSIDE_DOB            = ".//h5[@ng-if='userProfile.cloudUserJson.dob']"


		def static profilePercentagecompletion = { browser,formData ->
			browser.delay(3000)
			def percentageOnDashboard = browser.gettext(PERCENTAGE).split("%")[0].trim()
			if(percentageOnDashboard.trim().equals(formData[0].trim())){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "percentageCompletionIssue", "The profile percentage completion does not match")
			}
		}


		def static getUserProfileDetails = { browser,formData ->
			if(browser.isDisplayed(NAME_ON_VIEW_PROFILE) && browser.isDisplayed(EMAIL_ON_VIEW_PROFILE) || browser.isDisplayed(LEFTSIDE_MOBNUM)){
				def nameOnViewProfile = browser.gettext(NAME_ON_VIEW_PROFILE)
				def emailOnViewProfile = browser.gettext(EMAIL_ON_VIEW_PROFILE)
				def mobOnViewProfile = browser.gettext(LEFTSIDE_MOBNUM)
				KPCommonPage.userFullNameonProfile = nameOnViewProfile
				KPCommonPage.userEmail = emailOnViewProfile
				KPCommonPage.userMobileNo = mobOnViewProfile
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "userDetailsDisplayIssue", "Mobile Number or Email or Name was not displayed")
			}
		}

		def static leftSideDetailsOfViewProfile = { browser,formData ->
			browser.delay(2000)
			def actualList = []
			def expectedList = KPCommonPage.actualDataList
			actualList.add(browser.gettext(LEFTSIDE_NAME).trim())
			actualList.add(browser.gettext(LEFTSIDE_EMAIL).trim())
			actualList.add(browser.gettext(LEFTSIDE_MOBNUM).trim())
			actualList.add(browser.gettext(LEFTSIDE_DOB).trim())
			if(actualList.size()==expectedList.size() && actualList.containsAll(expectedList)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "leftSideDetailsMismatchIssue", "The details on the left side do not match on View Profile")
			}

		}

		def static userBasicInformationMatch = { browser,formData ->
			browser.delay(7000)
			def list = []
			def actualBasicInfo = new ViewUserPublicProfilePageForm().getActualBasicInfoDetails(browser)
			actualBasicInfo = actualBasicInfo.sort()
			def expectedBasicInfo = KPCommonPage.basicInfo.sort()
			if(actualBasicInfo.size()==expectedBasicInfo.size() && actualBasicInfo.containsAll(expectedBasicInfo)){
				def infoList = browser.getLists(LEFT_BASIC_INFO_OF_USER)
				infoList = infoList.sort()
				list.add(KPCommonPage.userAge.trim())
				list.add(KPCommonPage.userMobileNo.trim())
				list.add(KPCommonPage.userName.trim())
				list = list.sort()
				if(infoList.size()==list.size() && infoList.containsAll(list)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "emailIdDisplayIssue", "The basic information of the user on left side do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "BasicInfoMissmatchIssue", "The basic information of the user do not match OR size of actual and expected info do not match")
			}
		}

		def getActualBasicInfoDetails(def browser){
			def basicDetails = []
			def basicDetailsList = browser.getLists(BASIC_INFO_OF_USER)
			for(int i=0;i<basicDetailsList.size();i++){
				if(basicDetailsList[i].contains("|")){
					def splittedValue = basicDetailsList[i].split("\\|")
					basicDetails.add(splittedValue[0].trim())
				}else if(basicDetailsList[i].contains(" ")){
					def tempVar = basicDetailsList[i].split(" ")
					for(int j=0;j<tempVar.size();j++){
						basicDetails.add(tempVar[j].trim())
					}
				}else{
					basicDetails.add(basicDetailsList[i].trim())
				}
			}
			return basicDetails
		}

		//Verify image displayed in View Organization Profile page
		def static final uploadUserImgDisplay = { browser, formData ->
			def srcImagePath = []
			def srcViewUserProfilePic = browser.gettext(IMAGE_SOURCE, "src")
			srcImagePath = srcViewUserProfilePic.split("\\?")
			if((KPCommonPage.srcUserProfilePic!=null) && (srcImagePath[0]!=null)){
				if(srcImagePath[0].equals(KPCommonPage.srcUserProfilePic)){
					println "Uploaded Image Verified Successfully."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "srcUrlMismatch", "Image was not uploaded properly.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "nullSrcUrl", "Image has null src attribute.")
		}

		def static educationDetailsMatch = { browser, formData ->
			def result = false
			def eduDetailList = []
			def sortedFormData = []
			def sortededuDetailList = []
			def splittedValue
			browser.scrollToElement2(".//h5[@class='p_5 text-center profile-text ng-binding']")
			browser.delay(1500)
			def userEduDetails= browser.getLists(EDU_DETAILS)
			browser.delay(2000)
			for(int i=0;i<userEduDetails.size();i++){
				if(userEduDetails[i] != null && userEduDetails[i].size() > 0 && userEduDetails[i].charAt(userEduDetails[i].size()-1)=='.') {
					userEduDetails[i] = userEduDetails[i].substring(0, userEduDetails[i].size()-1).trim()
				}
				userEduDetails[i] = userEduDetails[i].trim();
				if((userEduDetails[i]).contains("-")){
					splittedValue = userEduDetails[i].split("-")
					eduDetailList.add(splittedValue[0].trim())
					eduDetailList.add(splittedValue[1].trim())
				}else{
					eduDetailList.add(userEduDetails[i].trim())
				}
			}
			if(formData.size()==(eduDetailList.size())){
				sortedFormData = formData.sort()
				sortededuDetailList = eduDetailList.sort()
				for(int k=0;k<sortededuDetailList.size();k++){
					if(sortedFormData[k].equalsIgnoreCase(sortededuDetailList[k])){
						result = true
					}
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EducationDetailsMismatchIssue", "Education details on View profile page do not matchwith the data provided")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "EducationDetailsSizeMismatchIssue", "Education details count on View profile page do not matchwith the data provided")
			}
		}

		def static final workExperienceDetailsMatch = { browser, formData ->
			browser.delay(6000)
			def result = false
			def expectedWorkExprience = []
			def actualWorkExprience = []
			browser.delay(1000)
			browser.scrollToElement2(".//i[@class='kp-experience blue mr_5']")
			browser.delay(2000)
			if(browser.isDisplayed(WORKEXPERIENCE_DETAILS)){
				browser.delay(2000)
				def workExperienceOnViewProfile = browser.getLists(WORKEXPERIENCE_DETAILS)
				def workExperienceOnViewProfileList = []
				def splittedValue
				for(int i=0;i<workExperienceOnViewProfile.size();i++){
					if(workExperienceOnViewProfile[i] != null && workExperienceOnViewProfile[i].size() > 0 && workExperienceOnViewProfile[i].charAt(workExperienceOnViewProfile[i].size()-1)=='.'){
						workExperienceOnViewProfile[i] = workExperienceOnViewProfile[i].substring(0, workExperienceOnViewProfile[i].size()-1).trim()
					}
					workExperienceOnViewProfile[i] = workExperienceOnViewProfile[i].trim();
					if((workExperienceOnViewProfile[i]).contains("-")){
						splittedValue = workExperienceOnViewProfile[i].split("-")
						for(int j=0;j<splittedValue.size();j++){
							if(splittedValue[j].matches(".*\\d+.*")){
								def value = splittedValue[j].split(" ")
								for(int k=0;k<value.size();k++){
									if(value[k]!=""){
										workExperienceOnViewProfileList.add(value[k].trim())
									}
								}
							}else {
								workExperienceOnViewProfileList.add(splittedValue[j].trim())
							}
						}
					}else{
						workExperienceOnViewProfileList.add(workExperienceOnViewProfile[i].trim())
					}
				}
				println "The DATA GOT IN VIEW PROFILE KP COMMON PAGE-----> "+KPCommonPage.WorkExperienceDetails
				println "The DATA GOT IN VIEW PROFILE ACTUAL DATA   -----> "+workExperienceOnViewProfileList
				if(KPCommonPage.WorkExperienceDetails.size()== workExperienceOnViewProfileList.size()){
					expectedWorkExprience = KPCommonPage.WorkExperienceDetails.sort()
					actualWorkExprience = workExperienceOnViewProfileList.sort()
					for(int k=0;k<actualWorkExprience.size();k++){
						if(expectedWorkExprience[k].equalsIgnoreCase(actualWorkExprience[k])){
							result = true
						}
					}
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "WorkExperienceDisplayIssue", "The work experience was not displayed on the View Profile page")
			}

			if(result){
				println "-------------------------WORK EXPERIENCE SUCCESS------------------------"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "WorkExperienceDetailsMismatchIssue", "The actual work experience details do not match with expected details")
			}
		}

		def static final ifWorkExperienceDisplayed = { browser, formData ->
			browser.delay(3000)
			def result = browser.isDisplayed(WORKEXPERIENCE_DETAILS)
			if(result == null){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "WorkExperienceDeleteIssue", "The work experience details were displayed even when work experiene was deleted")
			}
		}

		def static skillsDisplayed = { browser, formData ->
			browser.delay(3000)
			def result = false
			def formDataSkills = []
			browser.delay(2000)
			browser.scrollToElement2(SCROLL_TO_VIEW_SKILLS)
			browser.delay(1000)
			if(browser.isDisplayed(SKILLS_ON_VIEW_PROFILE)){
				def skillsOnViewProfile = browser.getLists(SKILLS_ON_VIEW_PROFILE)
				def commonSkills = KPCommonPage.skills
				for(int k=0;k<commonSkills.size();k++){
					formDataSkills.add(commonSkills[k].trim())
				}

				skillsOnViewProfile = skillsOnViewProfile.sort()
				formDataSkills = formDataSkills.sort()
				if(skillsOnViewProfile.size()==formDataSkills.size() && skillsOnViewProfile.containsAll(formDataSkills)){
					result = true
				}else{
					result = false
				}
				/*for(int l=0;l<skillsOnViewProfile.size();l++){
				 if(skillsOnViewProfile[l].trim().equalsIgnoreCase(formDataSkills[l].trim())){
				 result = true
				 }else{
				 result = false
				 break
				 }
				 }*/
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SkillsDisplayIssue", "Skills on View profile are not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "SkillsMismatchIssue", "Skills on View profile page do not match")
			}
		}

		def static certificateMatch = { browser,formData ->
			browser.delay(8000)
			def result = false
			browser.scrollToElement2(SCROLL_TO_VIEW_DOC)
			browser.delay(1000)
			def docNameList = browser.getLists(DOCNAME_VIEWPROFILE)
			def sortedDocName = docNameList.sort()
			def expectedDocName = KPCommonPage.docName.sort()
			if(browser.isDisplayed(DOCNAME_VIEWPROFILE)){
				for(int i=0;i<docNameList.size();i++){
					if(sortedDocName[i].equalsIgnoreCase(expectedDocName[i])){
						result = true
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "DocumentNameDisplayIssue", "The document name on view profile page was not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "DocumentNameMismatchIssue", "The document name does not match on View Public profile page")
			}
		}

		def static ifcertificateDeleted = { browser,formData ->
			browser.delay(8000)
			browser.scrollToElement2(SCROLL_TO_VIEW_DOC)
			browser.delay(1000)
			def outcome = browser.isDisplayed(DOCNAME_VIEWPROFILE)
			if(outcome==null){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "DocumentNameMismatchIssue", "The document was not deleted")
			}
		}

	}
}

