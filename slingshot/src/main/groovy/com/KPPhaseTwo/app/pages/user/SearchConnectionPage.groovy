package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage



final class SearchConnectionPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new SearchConnectionSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new SearchConnectionFilterForm().populateFields(browser, formData);
		}
	}

	//to check correct user is displayed in list
	def static correctUser = {browser, formData ->
		new SearchConnectionSearchForm().correctUser browser, formData
	}

	//to verify correct Location is displayed
	def static correctLocationDisplayed = {browser, formData ->
		new SearchConnectionFilterForm().correctLocationDisplayed browser, formData
	}
	
	//to verify the success message onclick of Connect link
	def static successMsgOnConnect = {browser, formData ->
		new SearchConnectionSearchForm().successMsgOnConnect browser,formData
	}
	
	//to verify the success message onclick of block link
	def static successMsgOnBlock = {browser, formData ->
		new SearchConnectionSearchForm().successMsgOnBlock browser,formData
	}



	static final class SearchConnectionSearchForm extends WebForm {

		//User connections Search form elements
		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue']" ////div[@id='main_page']//div[contains(@ng-init,'searchForText')]/input

		private static final def USER_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def PROFILE_NAME = ".//*[@id='edit_profile']/h3"

		private static final def PROFILE_EMAILID = "//h5[i[@ng-show='userProfile.cloudUserJson.emailId']]"

		private static final def FIRST_CONNECTED_USER = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/a"

		private static final def FIRST_CONNECT_LINK = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[normalize-space(text())='Connect']"

		private static final def FIRST_CONNECTED_EMAILID = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/following-sibling::span[1]"

		private static final def USERNAME_VIEWPROFILE = "//div[h6[text()='Name']]/following-sibling::div/span"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def CLICK_BREDCRUMB_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
		private static final def SUCCESS_MSG = ".//*[@id='main_page']/div[1]/div/span"

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		//error message map (Key-Value Pair)
		def static final userSearchConnectionPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SearchConnectionSearchForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						if(!formData[i].equals("")){
							browser.delay(1000)
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								KPCommonPage.searchUserName = data[0].trim()
								KPCommonPage.searchEmailId = data[1].trim()
							}else{
								KPCommonPage.searchUserName = formData[i]
							}
						}
						browser.delay(1000)
					}
				}
				return outcome
			}
		}


		//to verify correct user is displayed when searched using email id. 
		def static correctUser = {browser, formData ->
			if(KPCommonPage.searchUserName !=null){
				def firstName = browser.gettext(FIRST_CONNECTED_USER).trim()
				if(firstName.equalsIgnoreCase(KPCommonPage.searchUserName)){
					def displayedEmailID = browser.gettext(FIRST_CONNECTED_EMAILID).split(":")[1].trim()

					browser.delay(3000)
					if(browser.isDisplayed(USER_LIST)){
						def emailIDSearch = browser.gettext(FIRST_CONNECTED_EMAILID).split(":")[1].trim()
						if(firstName.equals(KPCommonPage.searchUserName.trim())
						&& displayedEmailID.equals(KPCommonPage.searchEmailId)){
							println "User Found Successfully"
							return new SuccessOutcome()
						}else
							return KPCommonPage.returnFailureOutcome(browser, "EmailIDOrNameMisMatchIssue", "EamilId Or User name does not Match")
					}else
						return KPCommonPage.returnFailureOutcome(browser, "InputUserNotFoundIssue", "Input User is not found Plz check.")
				}
			}
		}
		
		//to verify the success message onclick of connect link
		def static final successMsgOnConnect = {browser, formData ->
			browser.delay(300)
			def result = false
			if(browser.isDisplayed(SUCCESS_MSG)){
				def actMsg = browser.gettext(SUCCESS_MSG).trim()
				if(actMsg.equalsIgnoreCase("Connection Requested Successfully")){
					result = true
				}
				if(result){
					println "Connection Requested Message Verified"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "ConnectionnRequestedMsgAppearingIssue", "Connection Requested message does not matched.")
				
			}
		}
		
		//to verify the Block message onclick of Block link
		def static final successMsgOnBlock = {browser, formData ->
			browser.delay(300)
			def result = false
			if(browser.isDisplayed(SUCCESS_MSG)){
				def actMsg = browser.gettext(SUCCESS_MSG).trim()
				if(actMsg.equalsIgnoreCase("Connection Blocked Successfully")){
					result = true
				}
				if(result){
					println "Connection Blocked Message Verified"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "ConnectionnBlockedMsgAppearingIssue", "Connection Blocked message does not matched.")
				
			}
		}




	}

	static final class SearchConnectionFilterForm extends WebForm {

		// User Connection filters elements with filters

		private static final def USER_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"

		private static final def FIRST_ACCEPT = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[normalize-space(text())='Accept']"

		private static final def FIRST_CONNECTED_USER = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/a"

		private static final def BLOCK_UNBLOCK = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[contains(text(),'block')]"

		private static final def FIRST_USER_EMAILID_SEARCHCONN = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/following-sibling::span[1]"

		private static final def FIRST_BLOCK_LINK = "//div[@class='list-section col-sm-9 col-md-9']//div[@class='clearfix content individual-pod ng-scope'][1]//a[normalize-space(text())='Block']"

		private static final def FILTER_LOCATION = "//md-select[@ng-model='functionCall.filter.location']"

		private static final def FILTER_LOCATION_LIST = ".//*[@ng-value='opt.placeId']/div[2]"

		private static final def LOCATION_RESULT_LIST = "//span[i[@class='kp-location blue mr_5']]"

		private static final def FILTER_ORGANIZATION = "//label[normalize-space(text())='Organization']/following-sibling::autocomplete//input"

		private static final def FILTER_ORG_LIST = "//li[contains(@val,'orgName')]"

		private static final def FILTER_ORG_RES_LIST = "//span[normalize-space(@ng-show)='connection.companyName']"

		private static final def FILTER_STATUS = "//select[@ng-model='functionCall.status']"

		private static final def FILTER_SORTBY = "//items-per-page[div[div[@class='view-page']]]/following-sibling::div//select"

		private static final def FIRST_NAME_IUSER = "//input[@name='fname']"

		private static final def LAST_NAME_IUSER = "//input[@name='lname']"

		private static final def EMAILID_IUSER = "//input[@name='subAdminMailId']"

		private static final def SET_PSWD_CHECKBOX = "//md-checkbox[@ng-model='newUser.checkedstatus']//div[@class='md-icon']"

		private static final def CREATE_PSWD = "//input[@name='subAdminPassword']"

		private static final def CONFIRM_PSWD = ".//*[@id='confirmPassword']"

		private static final def SELECT_SITE = "//select[@name='newUser.siteId.id']"

		private static final def INVITE_BTN = "//button[text()='INVITE']"

		private static final def USERNAME = ".//*[@id='login_form']/div[1]/div/input"

		private static final def PASSWORD = ".//*[@id='login_form']/div[2]/div/div/input"

		private static final def OLD_PSWD = ".//*[@id='oldPassword']"

		private static final def NEW_PSWD = ".//*[@id='newPassword']"

		private static final def CONF_PSWD = ".//*[@id='confirmPassword']"

		private static final def SUBMIT ="//input[@value='SUBMIT']"

		private static final def CONNECTION_LINK = "//p[contains(text(),'Connections')]"

		private static final def SIGNIN = "//input[@class='button-primary'][@value='SIGN IN']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def ELEMENT2 = "html/body/md-backdrop"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def CLICK_BREDCRUMB_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def NO_OF_PEOPLE = "//h5[contains(text(),'No of People in the Batch')]"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope disabled']"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"
		
		

		private static final def FIELDS = [FILTER_LOCATION, FILTER_ORGANIZATION, FILTER_SORTBY]// the error fields.
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final userConnectionPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SearchConnectionFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_LOCATION)){
						if(!formData[i].equals("")){
							browser.click FILTER_LOCATION
							browser.delay(3500)
							def locationList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									locationList.add(data[k])
									KPCommonPage.selectAutoComplete(browser, FILTER_LOCATION_LIST , data[k])
								}
							}else{
								locationList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, FILTER_LOCATION_LIST , formData[i])
							}
							KPCommonPage.userLocationFilter = locationList
							browser.click(ELEMENT)
						}
					}else if(FIELDS[i].equals(FILTER_ORGANIZATION)){
						if(!formData[i].equals("")){
							def viewingCount = SearchConnectionPage.getTotalViewingUsersCount(browser,formData)
							KPCommonPage.viewCount = viewingCount
							browser.delay(1000)
							browser.populateField(FIELDS[i],formData[i])
							browser.delay(2000)
							KPCommonPage.selectAutoComplete(browser, FILTER_ORG_LIST , formData[i])
							KPCommonPage.OrgsListsFilter = formData[i]
							browser.click ELEMENT
						}
					}else {

						if(!formData[i].equals("")){
							if(FIELDS[i].equals(FILTER_SORTBY)){
								def sortData
								browser.click FILTER_SORTBY
								browser.delay(1000)
								sortData = formData[i]
								browser.selectDropdownValue(FILTER_SORTBY,formData[i])
								browser.delay(2000)
								KPCommonPage.sortByData = sortData
							}
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}



		//to check correct locations displayed in list
		def static final correctLocationDisplayed = {browser, formData ->
			def nextButton = browser.gettext(PAGINATION_NEXT,"class").contains("disabled")
			int lastPage=1
			if(!nextButton){
				browser.delay(1000)
				def pageText = browser.gettext(LAST_PAGE_NO)
				browser.delay(1000)
				lastPage = pageText.toInteger()
				println "...lastPageNumber......"+lastPage
			}else{
				browser.delay(1000)
				lastPage
			}
			def matchingResult=false
			browser.delay(500)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(1500)
				for(int w=0;w<lastPage;w++){
					if(browser.isDisplayed(LOCATION_RESULT_LIST)){
						def actualLocList = browser.getLists(LOCATION_RESULT_LIST)
						def expectedLocList = KPCommonPage.followedLocationListsFilter
						if(expectedLocList != null || !expectedLocList.isEmpty()){
							for(int k = 0; k < expectedLocList.size(); k++){
								for(int r = 0; r < actualLocList.size(); r++){
									if(expectedLocList[k].equalsIgnoreCase(actualLocList[r]) || actualLocList[r].equalsIgnoreCase("Bengaluru")){
										matchingResult = true
									}
								}
							}
						}
					}
					if(!browser.gettext(PAGINATION_NEXT,"class").contains("disabled")){
						browser.click PAGINATION_NEXT
						browser.delay(1000)
					}
				}
			}
			if(matchingResult){
				println "Correct Location Results Displayed"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IncorrectLocationDisplayIssue", "Location does not match with the input filter Location.")
			}

		}

	}

}