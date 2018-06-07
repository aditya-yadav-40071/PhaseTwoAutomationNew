package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage



final class UserConnectionPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new UserConnectionSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new UserConnectionFilterForm().populateFields(browser, formData);
		}
	}
	
	def static getTotalViewingUsersCount = {browser, formData ->
		new UserConnectionFilterForm().getTotalViewingUsersCount browser, formData
	}
	
	def static userListDisplayed = {browser,formData ->
		new UserConnectionSearchForm().userListDisplayed browser, formData
	}
	
	def static getFirstConnectedName = {browser, formData ->
		new UserConnectionSearchForm().getFirstConnectedName browser, formData
	}
	
	def static correctUserProfile = {browser, formData ->
		new UserConnectionSearchForm().correctUserProfile browser, formData
	}
	
	def static correctUserDisplayed = {browser, formData ->
		new UserConnectionSearchForm().correctUserDisplayed browser, formData
	}
	
	def static correctOrgDisplayed = {browser, formData ->
		new UserConnectionFilterForm().correctOrgDisplayed browser, formData
	}
	
	def static correctStatusResultDisplayed = {browser, formData ->
		new UserConnectionFilterForm().correctStatusResultDisplayed browser, formData
	}
	
	def static correctSortByResultDisplayed = {browser, formData ->
		new UserConnectionFilterForm().correctSortByResultDisplayed browser, formData
	}
	
	def static getUserToBeBlocked = {browser, formData ->
		new UserConnectionFilterForm().getUserToBeBlocked browser, formData
	}
	
	def static correctPendingRequest = {browser, formData ->
		new UserConnectionFilterForm().correctPendingRequest(browser, formData)
	}
	
	def static correctBlockedUser = {browser, formData ->
		new UserConnectionFilterForm().correctBlockedUser(browser, formData)
	}
	
	def static correctReceivedRequest = {browser, formData ->
		new UserConnectionFilterForm().correctReceivedRequest(browser, formData)
	}
	
	def static inputParticipantDetails = {browser, formData ->
		new UserConnectionFilterForm().inputParticipantDetails browser, formData
	}
	
	def static inputPodName = {browser, formData ->
		new UserConnectionFilterForm().inputPodName browser, formData
	}
	
	def static inputOfEnrolledUser = {browser, formData ->
		new UserConnectionFilterForm().inputOfEnrolledUser browser, formData
	}
	
	def static changePasswordOperation = {browser, formData ->
		new UserConnectionFilterForm().changePasswordOperation browser, formData
	}
	
	def static getFirstRequestingName = {browser, formData ->
		new UserConnectionSearchForm().getFirstRequestingName browser, formData
	}

	def static getProfileNameAndEmailId = {browser, formData ->
		new UserConnectionSearchForm().getProfileNameAndEmailId browser, formData
	}
	
	def static searchForInputUser = {browser, formData ->
		new UserConnectionSearchForm().searchForInputUser browser, formData
	}

	

	static final class UserConnectionSearchForm extends WebForm {

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

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		
		//error message map (Key-Value Pair)
		def static final userSearchConnectionPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("UserConnectionSearchForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						def searchInput = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.searchUserName = searchInput
					}
					browser.delay(1000)
				}
			}
			return outcome
		}

		

		
		def static final userListDisplayed = {browser, formData ->
			browser.delay(14000)
			if(browser.isDisplayed(USER_LIST)){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "UsersDisplayedIssue", "Users list does not displayed.")
		}
		
		
		def static final getFirstConnectedName = {browser, formData ->
			if(browser.isDisplayed(FIRST_CONNECTED_USER)){
				KPCommonPage.firstConnectedUserName = browser.gettext(FIRST_CONNECTED_USER).trim()
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "UsersDisplayedIssue", "Users list does not displayed.")
		}
		
		def static final getFirstRequestingName = {browser, formData ->
			if(browser.isDisplayed(FIRST_CONNECTED_USER)){
				KPCommonPage.firstRecvRequestUserName = browser.gettext(FIRST_CONNECTED_USER).trim()
				KPCommonPage.firstRecvRequestEmailID = browser.gettext(FIRST_CONNECTED_EMAILID).trim().split(":")[1].trim()
				browser.click FIRST_CONNECT_LINK
				browser.delay(2000)
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "UsersDisplayedIssue", "Users list does not displayed.")
		}
		
		
		def static final correctUserProfile = {browser, formData ->
			browser.delay(14000)
			def userInViewProfile = browser.gettext(USERNAME_VIEWPROFILE).trim()
			if(userInViewProfile!=null && KPCommonPage.firstConnectedUserName !=null){
				if(KPCommonPage.firstConnectedUserName.equalsIgnoreCase(userInViewProfile)){
					println "Correct User Profile"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "DifferentUserProfileDisplayIssue", "Clicked User profile page is Not Displaying.")
			}
		}
		
		
		def static final correctUserDisplayed = {browser, formData ->
			def flag = false
			if(KPCommonPage.searchUserName !=null){
				def firstName = browser.gettext(FIRST_CONNECTED_USER).trim()
				if(firstName.equalsIgnoreCase(KPCommonPage.searchUserName)){
					KPCommonPage.firstConnectedEmailID = browser.gettext(FIRST_CONNECTED_EMAILID).split(":")[1].trim()
					browser.populateField(SEARCH_TEXT, KPCommonPage.firstConnectedEmailID)
					browser.pressEnter(SEARCH_TEXT)
					browser.delay(3000)
					if(browser.isDisplayed(USER_LIST)){
						def emailIDSearch = browser.gettext(FIRST_CONNECTED_EMAILID).split(":")[1].trim()
						if(firstName.equals(browser.gettext(FIRST_CONNECTED_USER).trim()) 
							&& emailIDSearch.equals(KPCommonPage.firstConnectedEmailID)){
							flag = true
						}else
							flag = false
					}else
						return KPCommonPage.returnFailureOutcome(browser, "EmailIdSearchIssue", "The Same User is not searchable when tried to search using his/her EmailID")
				}
			}
			if(flag){
				println "Name and EmailId Search Working"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "EmailIDOrNameMisMatchIssue", "EamilId Or User name does not Match")
		}
		
		def static final getProfileNameAndEmailId = {browser, formData ->
			browser.delay(5000)
			browser.scrollToElement2(PROFILE_EMAILID)
			if(browser.isDisplayed(PROFILE_NAME) && browser.isDisplayed(PROFILE_EMAILID)){
				KPCommonPage.userFullName = browser.gettext(PROFILE_NAME).trim()
				KPCommonPage.userEmailId = browser.gettext(PROFILE_EMAILID).split("\n")[0].trim()
			}
		}
		
		def static final searchForInputUser = {browser, formData ->
			browser.delay(2000) 
			if(browser.isDisplayed(SEARCH_TEXT) && KPCommonPage.userFullName !=null){
				browser.populateField(SEARCH_TEXT, KPCommonPage.userEmailId)
				browser.delay(1000)
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "SearchTextFieldDisplayeOrNullIssue", "Search Text Filed diaplay issue or KPCommonPage.userFullName is NULL.")
 
		}
		

		
	}

	static final class UserConnectionFilterForm extends WebForm {

		// User Connection filters elements with filters

		private static final def USER_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
		
		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"
		
		private static final def FIRST_ACCEPT = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[normalize-space(text())='Accept']"
		
		private static final def FIRST_CONNECTED_USER = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/a"
		
		private static final def BLOCK_UNBLOCK = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//a[contains(text(),'block')]"
		
		private static final def FIRST_USER_EMAILID_SEARCHCONN = "//div[@class='list-section col-sm-9 col-md-9']//div[contains(@class,'clearfix content individual-pod ng-scope')][1]//h3/following-sibling::span[1]"
		
		private static final def FIRST_BLOCK_LINK = "//div[@class='list-section col-sm-9 col-md-9']//div[@class='clearfix content individual-pod ng-scope'][1]//a[normalize-space(text())='Block']"
		
		private static final def FILTER_LOCATION = "//md-select[@ng-model='functionCall.filter.location']"
		
		private static final def FILTER_LOCATION_LIST = ".//*[@ng-value='opt.placeId']"

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

		private static final def FIELDS = [FILTER_LOCATION, FILTER_ORGANIZATION, FILTER_STATUS, FILTER_SORTBY]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final userConnectionPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("UserConnectionFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_LOCATION)){
						if(!formData[i].equals("")){
							browser.click FILTER_LOCATION
							browser.delay(2000)
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
							def viewingCount = UserConnectionPage.getTotalViewingUsersCount(browser,formData)
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
							if(FIELDS[i].equals(FILTER_STATUS)){
								def statusData
								browser.click FILTER_STATUS
								browser.delay(1000)
								statusData = formData[i]
								browser.selectDropdownValue(FILTER_STATUS,formData[i])
								browser.delay(2000)
								KPCommonPage.selectedStatus = statusData
							}else{
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
		
		
		
		//To get the total viewing User count in search pod page.
		def static final getTotalViewingUsersCount = {browser, formData ->
			
			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(USER_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split(" ")[3].trim()
					
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return totalResult
			
		}
		
		//to verify correct Organization is displayed 
		def static final correctOrgDisplayed = {browser, formData ->
			browser.delay(2000)
			def nextButton = browser.gettext(PAGINATION_NEXT,"class").contains("disabled")
			if(browser.isDisplayed(FILTER_ORG_RES_LIST)){
				def flag
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
				for(int i=0;i<lastPage;i++){
				def listDataPerPage = browser.getLists(FILTER_ORG_RES_LIST)
					for(int j=0;j<listDataPerPage.size();j++){
						if(listDataPerPage[j].trim().equalsIgnoreCase(KPCommonPage.OrgsListsFilter.trim())){
							flag = true
						}else
							flag = false
							break
					}
					if(!browser.gettext(PAGINATION_NEXT,"class").contains("disabled")){
						browser.click PAGINATION_NEXT
						browser.delay(2000)
					}
				}
				if(flag){
					println "Successfully Verified Org Filter."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "OrgFilterNoProperResultIssue", "Filter By Org is not working as aspected")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationsNamesDisplayIssue", "Org names are not appearing in result set.")
		}
		
		
		
		def static final correctStatusResultDisplayed = {browser, formData ->
			def status = KPCommonPage.selectedStatus
			browser.delay(1000)
				if(status.equalsIgnoreCase("Pending Invitations")){
					def pendingSats = UserConnectionPage.correctPendingRequest(browser,formData)
				}else if(status.equalsIgnoreCase("Received Invitations")){
					def recvdStats = UserConnectionPage.correctReceivedRequest(browser,formData)
					if(recvdStats){
						browser.click FIRST_ACCEPT
						browser.delay(2000)
					}
			   }else if(status.equalsIgnoreCase("Blocked Connections")){
					 def blockStats = UserConnectionPage.correctBlockedUser(browser,formData)
					 if(blockStats){
						 browser.click BLOCK_UNBLOCK
						 browser.delay(1000)
					 }
			   }
			}
		
		def static final correctSortByResultDisplayed = {browser, formData ->
			def sortBy = KPCommonPage.sortByData
			def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,USER_LIST,FIFTY_RESULT)
			browser.delay(1000)
				if(sortBy.equalsIgnoreCase("Alphabetical")){
					def result = KPCommonPage.isSorted(allResult)
					if(result){
						println "Success for Alphabetically Sort"
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical A-Z is not Working.")
					}
			   }else if(sortBy.equalsIgnoreCase("Recently added")){
					 def firstUserName = browser.gettext(FIRST_CONNECTED_USER).trim()
					 def firstUserEmailID = browser.gettext(FIRST_USER_EMAILID_SEARCHCONN).split(":")[1].trim()
					 if(firstUserEmailID.equals(KPCommonPage.invitedUserEmailId) /*&&
						firstUserName.equalsIgnoreCase(KPCommonPage.enrollUserFullName) */){
						 println "Success for Recently Added Sort"
						 return new SuccessOutcome()
					 }else{
						 return KPCommonPage.returnFailureOutcome(browser, "RecentlyAddedResultIssue", "Recently Added is not giving proper result.")
					 }
			   }
		}
		
		
		def static final correctPendingRequest = {browser, formData ->
			def flag = false
				def firstName = browser.gettext(FIRST_CONNECTED_USER).trim()
				if(firstName.equals(KPCommonPage.firstConnectedUserName.trim())){
					println "Correct Pending User"
					flag = true
				}
				return flag
			
		}
		
		def static final getUserToBeBlocked = {browser, formData ->
			if(browser.isDisplayed(FIRST_CONNECTED_USER)){
				def blockMeUser = browser.gettext(FIRST_CONNECTED_USER).trim()
				KPCommonPage.userToBeBlocked = blockMeUser
				browser.click FIRST_BLOCK_LINK
				browser.delay(2000)
				return new SuccessOutcome()
			}
		}
		
		def static final correctBlockedUser = {browser, formData ->
			def flag = false
			def firstNameInBlockedUsersList = browser.gettext(FIRST_CONNECTED_USER).trim()
			if(firstNameInBlockedUsersList.equals(KPCommonPage.userToBeBlocked.trim())){
				println "Correct Blocked User"
				flag = true
			}
			return flag
			
		}
		
		def static final correctReceivedRequest = {browser, formData ->
			def flag = false
			def firstNameInReceivedUsersList = browser.gettext(FIRST_CONNECTED_USER).trim()
			def firstEmailIDInReceivedUsersList = browser.gettext(FIRST_USER_EMAILID_SEARCHCONN).split(":")[1].trim()
			if(firstNameInReceivedUsersList.equalsIgnoreCase(KPCommonPage.enrollUserFullName.trim()) && 
				firstEmailIDInReceivedUsersList.equals(KPCommonPage.emailIDNewEnrolledUser.trim())){
				println "Correct Received User"
				flag = true
			}
			return flag
		}
		
		def static final inputPodName = {browser, formData ->
			def podName = "Tarang - Digital Literacy"
			if(browser.isDisplayed(SEARCH_TEXT)) {
				browser.delay(2000)
				browser.populateField(SEARCH_TEXT,podName)
				browser.pressEnter(SEARCH_TEXT)
				browser.delay(4000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchFieldDisplayIssue", "Search field is not Appearing.")
			
			}
		}
		
		def static final inputParticipantDetails = {browser, formData ->
			browser.delay(3000)
			def firstName = "Saumya"
			def lastName = "Sinha"
			KPCommonPage.oldPassword = "Password@123"
			KPCommonPage.newPassword = "Welcome@123"
			def expMsg = "Learner Enrolled to Pod Successfully"
			def emailID = firstName+KPCommonPage.generateRandomCellNo()+"@"+"gm.com"
			KPCommonPage.enrollUserFullName = firstName+" "+lastName
			KPCommonPage.emailIDNewEnrolledUser = emailID
			browser.populateField(FIRST_NAME_IUSER,firstName)
			browser.populateField(LAST_NAME_IUSER,lastName)
			browser.populateField(EMAILID_IUSER,emailID)
			browser.click SET_PSWD_CHECKBOX
			browser.populateField(CREATE_PSWD,KPCommonPage.oldPassword)
			browser.populateField(CONFIRM_PSWD,KPCommonPage.oldPassword)
			browser.selectDropdownValue(SELECT_SITE,"Default Online Site")
			browser.delay(2000)
			if(browser.checkEnabled(INVITE_BTN))browser.click INVITE_BTN
			def actMsg = browser.gettext(".//*[@id='main_page']/div[2]/div/span").trim()
			if(actMsg.equalsIgnoreCase(expMsg)){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "AddParticipantFormErrorMsgIssue", "While adding form message is not Correct")
		
		}
		
		
		def static final inputOfEnrolledUser = {browser, formData ->
			browser.delay(4000)
			if(browser.isDisplayed(USERNAME) && browser.isDisplayed(PASSWORD)){
				browser.populateField(USERNAME, KPCommonPage.emailIDNewEnrolledUser)
				browser.populateField(PASSWORD, KPCommonPage.oldPassword)
				if(browser.isDisplayed(SIGNIN)){
					browser.click SIGNIN
					browser.delay(4000)
					return new SuccessOutcome()
				} else {
					return KPCommonPage.returnFailureOutcome(browser, "LoginButtonIssue", "Unable to click login button, its not displayed ")
				}
			}
		}
		
		
		def static final changePasswordOperation = {browser, formData ->
			KPCommonPage.newPassword = "Password@123"
			browser.populateField(OLD_PSWD,formData[5].trim())
			browser.populateField(NEW_PSWD, KPCommonPage.newPassword)
			browser.populateField(CONF_PSWD, KPCommonPage.newPassword)
			if(browser.isDisplayed(SUBMIT)){
				browser.click SUBMIT
				browser.delay(4000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "LoginButtonIssue", "Unable to click login submit button ")
			}
		}
		
		

		
		
		
	}
}