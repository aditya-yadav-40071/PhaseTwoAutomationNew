package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * 
 * @author Aditya Yadav
 *
 */
final class ParticipantListPage extends WebPage {

	/**
	 * To check if the created user is enrolled to the pod
	 */
	def static ifUserIsEnrolled = { browser,formData ->
		new ParticipantListPageForm().ifUserIsEnrolled(browser, formData);
	}

	/**
	 * Checks the the learner list is sorted in A-Z or Z-A format
	 */
	def static learnerListSorted = { browser,formData ->
		new ParticipantListPageForm().learnerListSorted(browser, formData);
	}

	/**
	 * Searching the user by enrolled or unenrolled or site name filter
	 */
	def static searchUserByFilter = { browser, formData ->
		new ParticipantListPageForm().searchUserByFilter browser, formData
	}

	/**
	 * Enrolling one user to multiple pods
	 */
	def static enrollUserToAnotherPod = { browser, formData->
		new ParticipantListPageForm().enrollUserToAnotherPod browser, formData
	}

	/**
	 * Checks if  the user has status removed on participant list page after user is removed 
	 * from the Manage  user page
	 */
	def static userRemovedStatus = { browser, formData->
		new ParticipantListPageForm().userRemovedStatus browser, formData
	}

	/**
	 * Checks if licence count is greater than 1  to enroll a learner
	 */
	def static ifLicenceAvailable = { browser, formData->
		new ParticipantListPageForm().ifLicenceAvailable browser, formData
	}

	/**
	 * Searching the created participant from the participant list by using the search bar
	 */
	def static searchParticipantList = { browser, formData->
		new ParticipantListPageForm().searchParticipantList browser, formData
	}

	/**
	 * Check if the created user is displayed on the page
	 */
	def static userDisplayedOnPage = { browser, formData->
		new ParticipantListPageForm().userDisplayedOnPage browser, formData
	}

	/**
	 * Enroll a user to the podby using the enroll button
	 */
	def static enrollUserToPod = { browser, formData ->
		new ParticipantListPageForm().enrollUserToPod browser, formData
	}

	/**
	 * Searching the created participant from the participant list by using the search bar
	 */
	def static searchParticipant = { browser, formData ->
		new ParticipantListPageForm().searchParticipant browser, formData
	}

	static final class ParticipantListPageForm extends WebForm {

		//Login form elements
		private static final def  PARTICIPANT_SEARCH        = ".//div[@ng-hide='isListEmpty && hideSearch']//input[@ng-model='searchValue']"

		private static final def  SEARCH_ARROW              = ".//button[@ng-click='searchAddParticipantsList(searchValue) ']/i"

		private static final def  USER_EMAIL_ID             = ".//h4[@class='ng-binding']"

		private static final def  LEARNER_NAMES             = ".//span[@class='capitalize ng-binding']"

		private static final def  USER_ENROLLED_STATUS      = ".//md-checkbox[@role='checkbox'][@aria-disabled='true']"

		private static final def  SORT_BY_RELEVENCE         = ".//div[@class='pull-left col-md-12 col-xs-12 no-padding']/select"

		private static final def  FILTER_BY_ENROLLED_STATUS = ".//select[@placeholder='Select Enrolled/Un Enrolled'][@ng-change='changeFilter(filterBy,siteid)']"

		private static final def  FILTER_BY_SITES           = ".//select[@placeholder='Select Site'][@ng-change='changeFilter(filterBy,siteid)']"

		private static final def  ENROLL_BUTTON             = ".//*[@id='main_page']//button[@ng-click='enrollUserToCourse()']"

		private static final def FIFTY_RESULTS              = ".//label[@for='item5']"

		private static final def ALL_CHECKBOXES             = ".//md-checkbox[@aria-label='Checkbox No Ink']/div[@class='md-container']"

		private static final def NEXT_BTN                   = ".//li[@ng-class='{disabled: noNext()||ngDisabled}']"

		private static final def SEARCH_PART_BTN            = "//button[@ng-click='searchAddParticipantsList(searchValue) ']/i[@class='fa fa-arrow-right blue']"

		private static final def USER_REMOVED_MSG           = ".//span[@ng-show='enrolleduser.active==0']"

		private static final def SEATS_REAMAINING           = ".//div[@class='col-xs-4 pull-right nmt20']//span[@class='pull-right mt_5 ng-binding']"

		private static final def PODNAME_BREADCRUMB         = ".//a[@href='/pod/details/']"
		// the error fields.
		private static final def FORM_ERROR                 = ".//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS       = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final participantListPageErrorMessageMap = [
			enrollFail             :'Failed to enroll new Learners, You have already Enrolled num Learners',
			enrollment_success     :'Learner Enrolled to Pod Successfully',
			enrollToPod_success    :'User Enrolled Successfully',
			learnerAdd_success     :'User added Successfully'
		]

		/*
		 * Search for the created  participant using the search bar
		 */
		def static searchParticipantList = { browser, formData ->
			browser.delay(2000)
			def result = new ParticipantListPageForm().search(browser,KPCommonPage.invitedUserEmailId)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchTextBoxDisplayIssue", "Search text box was not displayeda")
			}
		}
		
		/* 
		 * Enters the created user email id and then performs search operation 
		 */
		def static search = { def browser, def email ->
			if(browser.isDisplayed(PARTICIPANT_SEARCH)){
				browser.populateField(PARTICIPANT_SEARCH,email)
				browser.delay(2000)
				browser.click(SEARCH_ARROW)
				browser.delay(2000)
				return true
			}else{
				return false
			}
		}

		def static searchParticipant = { browser, formData ->
			browser.delay(2000)
			def result = new ParticipantListPageForm().search(browser,KPCommonPage.invitedUserEmailIdDefault)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchTextBoxDisplayIssue", "Search text box was not displayeda")
			}
		}

		def static userDisplayedOnPage = { browser, formData ->
			browser.delay(2000)
			def result = false
			if(browser.isDisplayed(USER_EMAIL_ID)){
				def allUserEmailId = browser.getLists(USER_EMAIL_ID)
				for(int i=0;i<allUserEmailId.size();i++){
					if(allUserEmailId[i].split(":")[1].trim().equalsIgnoreCase(KPCommonPage.invitedUserEmailId.trim())){
						result = true
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailDisplayIssue", "User email was not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDetailMismatchIssue", "User details do not match")
			}
		}


		def static ifUserIsEnrolled = { browser, formData ->
			browser.delay(2000)
			//browser.populateField(PARTICIPANT_SEARCH,KPCommonPage.invitedUserEmailId)
			//browser.click(SEARCH_ARROW)
			//browser.delay(2000)
			def allUserEmailId = browser.gettext(USER_EMAIL_ID)
			if(KPCommonPage.invitedUserEmailId.equalsIgnoreCase(allUserEmailId.split(" ")[1])){
				def disabledStatus = browser.gettext(USER_ENROLLED_STATUS,"aria-disabled")
				def checkedStatus = browser.gettext(USER_ENROLLED_STATUS,"aria-checked")
				if(disabledStatus && checkedStatus){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "UserEnrollmentIssue", "User was not enrolled to the pod")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserCreationIssue", "The user was not created")
			}
		}

		def static learnerListSorted = { browser, formData ->
			def allLearnerName
			KPCommonPage.selectSortByValue(browser,SORT_BY_RELEVENCE,formData[0])
			allLearnerName = browser.getLists(LEARNER_NAMES)
			def sortResult = KPCommonPage.isSorted(allLearnerName)
			if(sortResult){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SortingFilterIssue", "Sorting filter is not working properly")
			}
		}

		def static searchUserByFilter = { browser, formData ->
			def result
			result = ParticipantListPageForm.selectFilter(browser,formData[0])
			if(formData[0].equalsIgnoreCase("Enrolled Students") && result == true){
				return new SuccessOutcome()
			} else if(formData[0].equalsIgnoreCase("UnEnrolled Students")  && result == false){
				return new SuccessOutcome()
			} else{
				return KPCommonPage.returnFailureOutcome(browser, "EnrollingFilterSearchIssue", "Enrolled/UnEnrolled filter is not working properly")
			}
		}

		def static selectFilter(def browser, def itemToSelect){
			def result = false
			browser.selectDropdownValue(FILTER_BY_ENROLLED_STATUS,itemToSelect)
			browser.delay(1500)
			browser.selectDropdownValue(FILTER_BY_SITES,KPCommonPage.invitedUserSiteName)
			browser.delay(1500)
			def learnerName = browser.getLists(LEARNER_NAMES)
			def allLearnerEmailId = browser.getLists(USER_EMAIL_ID)
			for(int i=0;i<learnerName.size();i++){
				allLearnerEmailId[i] = allLearnerEmailId[i].split(" ")[1].trim()
				if(learnerName[i].contains(KPCommonPage.invitedUserFirstName) && allLearnerEmailId[i].equalsIgnoreCase(KPCommonPage.invitedUserEmailId.trim())){
					result = true
					break;
				}
			}
			return result
		}

		def static enrollUserToAnotherPod = { browser, formData ->
			browser.delay(500)
			def userEmailList = []
			def actMessage
			def result
			def userToEnroll
			browser.populateField(PARTICIPANT_SEARCH,KPCommonPage.invitedUserFirstName)
			browser.click(SEARCH_PART_BTN)
			browser.delay(4000)
			browser.click(FIFTY_RESULTS)
			browser.delay(3000)
			def checkBoxToBeChecked = browser.getListElements(ALL_CHECKBOXES)
			def allUsersNameOnPage = browser.getLists(LEARNER_NAMES)
			def allUsersEmailOnPage = browser.getLists(USER_EMAIL_ID)
			for(int j=0;j<allUsersEmailOnPage.size();j++){
				userEmailList.add(allUsersEmailOnPage[j].split(" ")[1].trim())
			}
			userToEnroll = KPCommonPage.invitedUserEmailId
			result = new ParticipantListPageForm().enrollToPod(browser,allUsersNameOnPage,userEmailList,checkBoxToBeChecked,userToEnroll)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserMismatchIssue", "Username or Email ID did not match")
			}
		}

		def static enrollToPod(def browser,def allUsersNameOnList,def userEmailList,def checkBoxList, def userToEnroll){
			def outcome = false
			def actMessage
			//userToEnroll = KPCommonPage.invitedUserEmailId
			for(int i=0;i<allUsersNameOnList.size();i++){
				if(allUsersNameOnList[i].contains(KPCommonPage.invitedUserFirstName) && userEmailList[i].equalsIgnoreCase(userToEnroll)){
					browser.scrollToElement(checkBoxList[i])
					if(browser.gettext(USER_ENROLLED_STATUS,"aria-disabled")) {
						browser.click(PODNAME_BREADCRUMB)
						browser.delay(1000)
						outcome = true
						break
					}
					browser.clickElement(checkBoxList[i])
					browser.delay(500)
					if(browser.checkEnabled(ParticipantListPageForm.ENROLL_BUTTON)){
						browser.click(ParticipantListPageForm.ENROLL_BUTTON)
						browser.delay(1000)
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "EnrollButtonIssue", "Enrolled button is not enabled")
					}

					actMessage = browser.gettext(ParticipantListPageForm.FORM_ERROR)
					if(actMessage!=null && actMessage.equalsIgnoreCase(ParticipantListPageForm.participantListPageErrorMessageMap.get('enrollToPod_success'))){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "SuccessMessageIssue", "Success message was not displayed")
					}
					outcome = true
					break
				}
			}
			return outcome
		}

		def static enrollUserToPod = { browser, formData ->
			browser.delay(1000)
			def result
			def userEmailList = []
			def userToEnroll = KPCommonPage.invitedUserEmailIdDefault
			def checkBoxToBeChecked = browser.getListElements(ALL_CHECKBOXES)
			def allUsersNameOnPage = browser.getLists(LEARNER_NAMES)
			def allUsersEmailOnPage = browser.getLists(USER_EMAIL_ID)
			for(int j=0;j<allUsersEmailOnPage.size();j++){
				userEmailList.add(allUsersEmailOnPage[j].split(" ")[1].trim())
			}
			result = new ParticipantListPageForm().enrollToPod(browser,allUsersNameOnPage,userEmailList,checkBoxToBeChecked,userToEnroll)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserMismatchIssue", "Username or Email ID did not match")
			}
		}

		def static userRemovedStatus = { browser, formData ->
			browser.delay(1000)
			browser.populateField(PARTICIPANT_SEARCH,KPCommonPage.invitedUserEmailId)
			browser.click(SEARCH_ARROW)
			browser.delay(2000)
			if(browser.isDisplayed(USER_EMAIL_ID)){
				def allUserEmailId = browser.gettext(USER_EMAIL_ID)
				if(KPCommonPage.invitedUserEmailId.equalsIgnoreCase(allUserEmailId.split(" ")[1])){
					browser.delay(1000)
					if(browser.isDisplayed(USER_REMOVED_MSG) && browser.gettext(USER_REMOVED_MSG).trim().equalsIgnoreCase("User is removed")){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "UserRemoveMessageIssue", "User removed message was not displayed or message do not match")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EmailMisMatchIssue", "The email displayed do not match with the expected email")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDisplayIssue", "The searched user was not displayed")
			}
		}

		def static ifLicenceAvailable = { browser, formData ->
			browser.delay(4000)
			def seatCount = browser.gettext(SEATS_REAMAINING).split(":")[1].trim()
			if(Integer.parseInt(seatCount)>0){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SeatReaminingIssue", "No seats are available to enroll student")
			}
		}
	}
}


