package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

final class ManageUserPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		if(formKey.equals("manageUser")){
			new ManageUserForm().populateFields(browser, formData)
		}
	}

	def static usersDisplayed = { browser, formData ->
		new ManageUserForm().usersDisplayed browser,formData
	}

	def static ifCorrectUserDisplayed = { browser, formData ->
		new ManageUserForm().ifCorrectUserDisplayed browser,formData
	}

	def static searchForUser = { browser, formData ->
		new ManageUserForm().searchForUser browser,formData
	}

	def static clickOnUserName = { browser, formData ->
		new ManageUserForm().clickOnUserName browser,formData
	}

	def static userDetailsOnPublicProfile = { browser, formData ->
		new ManageUserForm().userDetailsOnPublicProfile browser,formData
	}

	def static ifUserIsRemoved = { browser, formData ->
		new ManageUserForm().ifUserIsRemoved browser,formData
	}

	def static clickToEditUserEmail = { browser, formData ->
		new ManageUserForm().clickToEditUserEmail browser, formData
	}

	def static ifSameEmailIsDisplayed = { browser, formData ->
		new ManageUserForm().ifSameEmailIsDisplayed browser, formData
	}

	def static removeUserFromOrg = { browser, formData ->
		new ManageUserForm().removeUserFromOrg browser, formData
	}

	def static selectUserSiteFromFilter = { browser, formData ->
		new ManageUserForm().selectUserSiteFromFilter browser,formData
	}

	def static manageUserListSorted = { browser, formData ->
		new ManageUserForm().manageUserListSorted browser,formData
	}

	static final class ManageUserForm extends WebForm {

		//Manage Users form elements
		private static final def USERNAME = ".//a[@ng-click='setSubAdminDetail(user.subAdminId)']"

		private static final def USER_SITE = ".//div[@class='profile-info col-md-8 col-xs-8']//span[2]"

		private static final def USER_SITE_TO_MATCH = ".//span[@class='col-md-12 col-xs-12 no-padding content-name smallText node-text1 width_100 ng-binding'][.='Email: userEmail']/../span[2]"

		private static final def PAGE_COUNT = "//a[@ng-click='selectPage(page + 1, \$event)']//preceding::a[1]"

		private static final def PAGINITION_NEXT = ".//li[@class='pagination-next ng-scope']"

		private static final def USEREMAIL_VALUE = ".//span[@class='col-md-12 col-xs-12 no-padding content-name smallText node-text1 width_100 ng-binding'][1]"//".//*[@id='emailId']"

		private static final def SEARCH_USER_TEXT_BOX = ".//div[@class='col-xs-12 visible-lg mb10 no-padding ml_15 mt_10 pr_25']//input"

		private static final def SUBMIT_BTN = ".//button[@class='perform-search r_30 pr_25']/i"

		private static final def REMOVE_USER_LINK = ".//a[@ng-click='deactiveUserConfirmation(\$event,user.id)']"

		private static final def REMOVE_USER_YES = ".//button[@ng-click='dialog.hide()']/span"

		private static final def USER_REMOVED_SUCCESS_MESSSAGE = ".//div[@ng-transclude='']/span"

		private static final def NO_RESULT_FOUND_MSG = ".//div[@ng-show='isListEmpty && hideFilter']/p"

		private static final def USER_EDIT_EMAIL_LINK = ".//a[@ng-click='openEditEmail(user)']"

		private static final def SORTBY_FILTER = ".//div[@class='pull-left col-md-12 col-xs-12 no-padding']/select"

		private static final def FIFTY_RESULTS = ".//label[@for='item5']"

		private static final def SITE_FILTER = ".//select[@ng-change='changeFilterBySiteId(siteid)']"

		private static final def FIELDS = [SORTBY_FILTER]// the error fields.
		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)

					if(FIELDS[i].equals(SORTBY_FILTER)){
						if(!formData[i].equals("")){
							def sortData
							browser.click SORTBY_FILTER
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(SORTBY_FILTER,formData[i])
							browser.delay(2000)
							KPCommonPage.manageUsersSortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}


		/**
		 * @author Aditya
		 * This method matches the details(name,email,site) of the user which was demoted from admin role
		 */
		def static final usersDisplayed = { browser, formData ->
			browser.delay(2000)
			def result = false
			browser.populateField(SEARCH_USER_TEXT_BOX,KPCommonPage.adminEmail_Id)
			browser.delay(1000)
			browser.click(SUBMIT_BTN)
			browser.delay(3000)
			def userNameList = browser.getLists(USERNAME)
			def userEmailList = browser.getLists(USERNAME,"title")
			def fullNameOfUser = KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName)
			for(int i=0;i<userNameList.size();i++){
				if(userEmailList[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
					if(userNameList[i].trim().equalsIgnoreCase(fullNameOfUser.trim())){
						def xpathForSite = USER_SITE_TO_MATCH.replace("userEmail",KPCommonPage.adminEmail_Id.trim())
						if(browser.isDisplayed(xpathForSite)){
							def text = browser.gettext(xpathForSite).trim()
							if(text.contains(":")){
								def siteName = text.split(":")[1].trim()
								if(siteName.trim().equalsIgnoreCase("Default Online Site")){
									result = true
									break
								}
							}
						}
					}
				}
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UsersDisplayedIssue", "Users details mismatch issue")
			}
		}


		def static  ifCorrectUserDisplayed = { browser, formData->
			browser.delay(2000)
			def result = false
			if(browser.isDisplayed(USERNAME)){
				def pageCount = Integer.parseInt(browser.gettext(PAGE_COUNT))
				for(int i=0;i<pageCount;i++){
					def userNameOnPage = browser.getLists(USERNAME)
					def userEmailOnPage = browser.getLists(USERNAME,"title")
					def userSiteOnPage = browser.getLists(USER_SITE)
					def fullNameOfUser = KPCommonPage.getFullName(KPCommonPage.invitedUserFirstName,KPCommonPage.invitedUserMiddleName,KPCommonPage.invitedUserLastName)
					for(int j=0;j<userNameOnPage.size();j++){
						if(userEmailOnPage[j].trim().equalsIgnoreCase(KPCommonPage.invitedUserEmailId.trim())){
							if(userNameOnPage[j].trim().equalsIgnoreCase(fullNameOfUser.trim())){
								if(userSiteOnPage[j].split(":")[1].trim().equalsIgnoreCase(KPCommonPage.invitedUserSiteName.trim())){
									result = true
									break
								}
							}
						}
					}
					if(!result){
						browser.scrollToElement2(PAGINITION_NEXT)
						browser.delay(2000)
						browser.click(PAGINITION_NEXT)
						browser.delay(2000)
					}else{
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserListDisplayIssue", "User list is not displayed")
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDisplayDetailMisMatchIssue", "User details did not match")
			}
		}


		def static searchForUser = { browser,formData ->
			browser.delay(1000)
			if(browser.isDisplayed(SEARCH_USER_TEXT_BOX)){
				browser.delay(1000)
				browser.populateField(SEARCH_USER_TEXT_BOX,KPCommonPage.invitedUserEmailId.trim())
				browser.delay(2000)
				browser.click(SUBMIT_BTN)
				browser.delay(3000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchTextBoxDisplayIssue", "The search text box was not displayed")
			}
		}

		def static correctUser = { def browser ->
			def userEmail = browser.gettext(USERNAME,"title")
			if(browser.isDisplayed(USERNAME) && userEmail!=null){
				if(userEmail.trim().equalsIgnoreCase(KPCommonPage.invitedUserEmailId.trim())){
					return true
				}else{
					return false
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDisplayIssue", "The user was not displayed")
			}
		}

		def static clickOnUserName = { browser, formData ->
			def result = new ManageUserForm().correctUser(browser)
			if(result){
				browser.click(USERNAME)
				browser.delay(2000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailMismatchIssue", "The users email did not match")
			}
		}

		def static removeUserFromOrg = { browser, formData ->
			def result = new ManageUserForm().correctUser(browser)
			if(result){
				if(browser.isDisplayed(REMOVE_USER_LINK)){
					browser.click(REMOVE_USER_LINK)
					browser.delay(2000)
					browser.click(REMOVE_USER_YES)
					return new SuccessOutcome()
					browser.delay(2000)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "UserRemoveLinkDisplayissue", "The users remove link was not displayed")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailMismatchIssue", "The users email did not match")
			}
		}

		def static userDetailsOnPublicProfile = { browser, formData ->
			browser.delay(8000)
			def userEmailOnProfile = browser.gettext(KPCommonPage.USER_EMAIL_ID_ON_VIEW_PROFILE).trim()
			def userFullName = browser.gettext(KPCommonPage.USER_NAME_ON_VIEW_PROFILE).trim()
			if(userEmailOnProfile!=null && userFullName!=null){
				if(userFullName.trim().equalsIgnoreCase(KPCommonPage.getFullName(KPCommonPage.invitedUserFirstName,KPCommonPage.invitedUserMiddleName,KPCommonPage.invitedUserLastName).trim())){
					if(KPCommonPage.invitedUserEmailId.trim().equalsIgnoreCase(userEmailOnProfile)){
						return new SuccessOutcome()
					}else {
						return KPCommonPage.returnFailureOutcome(browser, "UserEmailIdMatchIssue", "User email Id does not match")
					}
				}else {
					return KPCommonPage.returnFailureOutcome(browser, "UserNameMatchIssue", "User name do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailOrNameDisplayIssue", "User name or email was not displayed")
			}
		}

		def static ifUserIsRemoved = { browser, formData ->
			browser.delay(4000)
			if(browser.isDisplayed(NO_RESULT_FOUND_MSG) && !browser.isDisplayed(USERNAME)){
				def actualmessage = browser.gettext(NO_RESULT_FOUND_MSG).trim()
				if(actualmessage.equalsIgnoreCase("No results found")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "MessageMismatchIssue", "The actual message do not match with the expected one")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "NoResultMessageIssue", "The no results found message was not displayed")
			}
		}

		def static clickToEditUserEmail = { browser, formData ->
			browser.delay(1000)
			def result = new ManageUserForm().correctUser(browser)
			if(result){
				browser.click(USER_EDIT_EMAIL_LINK)
				browser.delay(1500)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailMismatchIssue", "The users email did not match")
			}
		}

		def static ifSameEmailIsDisplayed = { browser, formData ->
			browser.delay(1000)
			def email = browser.gettext(USEREMAIL_VALUE,"value").trim()
			if(email!=null && KPCommonPage.invitedUserEmailId.trim().equalsIgnoreCase(email)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailMismatchIssue", "The users email did not match or it is null")
			}
		}

		def static selectUserSiteFromFilter = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(SITE_FILTER)){
				browser.selectDropdownValue(SITE_FILTER,KPCommonPage.invitedUserSiteName)
				browser.delay(1000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteFilterDisplayIssue", "The site filter was not displayed")
			}
		}

		def static manageUserListSorted = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(FIFTY_RESULTS)){
				browser.click(FIFTY_RESULTS)
				browser.delay(1000)
				KPCommonPage.selectSortByValue(browser,SORTBY_FILTER,formData[0])
				def userNames = browser.getLists(USERNAME)
				def sortResult = KPCommonPage.isSorted(userNames)
				def dropDownValue = browser.getDropdownValue(SORTBY_FILTER).trim()
				if(sortResult && dropDownValue.equalsIgnoreCase("Alphabetical A-Z")){
					return new SuccessOutcome()
				}else if(!sortResult && dropDownValue.equalsIgnoreCase("Alphabetical Z-A")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortingFilterIssue", "Sorting filter is not working properly")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FiftyResultsDisplayIssue", "The fifty result bar was not displayed")
			}
		}
	}
}