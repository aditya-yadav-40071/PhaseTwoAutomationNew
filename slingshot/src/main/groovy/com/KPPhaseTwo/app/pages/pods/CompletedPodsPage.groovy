package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created By Bishnu Das
 */


final class CompletedPodsPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new CompletedPodsSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new CompletedPodsFilterForm().populateFields(browser, formData);
		}

	}
	

	def static getFirstPodName = {browser, formData ->
		new CompletedPodsSearchForm().getFirstPodName browser, formData
	}
	
	
	//To fetch the pod names if they are displaying
	def static getPodTextList = { browser, element, podList, fiftyRes ->
		new CompletedPodsFilterForm().getPodTextList browser, element, podList, fiftyRes
	}
	
	
	//To get the total count of pods viewing per page
	def static getTotalViewingPodsCount ={ browser, formData ->
		new CompletedPodsFilterForm().getTotalViewingPodsCount browser, formData
	}
	
	
	def static getProfileName = {browser, formData ->
		 new CompletedPodsSearchForm().getProfileName browser, formData
	}
	
	//to switch to new window
	def static clickAndSwitchToCertificateTab = {browser, formData ->
		new CompletedPodsSearchForm().clickAndSwitchToCertificateTab browser, formData
	}
	
	//to check in certificate correct profile name and pod name is displayed.
	def static correctCertificateContents = {browser, formData ->
		new CompletedPodsSearchForm().correctCertificateContents browser, formData
	}
	
	//to verify the Error message displayed onclick of add skills links
	def static correctErrorMsgAddSkills = {browser, formData ->
		new CompletedPodsSearchForm().correctErrorMsgAddSkills browser, formData
	}
	
	
	
	
	static final class CompletedPodsSearchForm extends WebForm {
		
				//Completed pods form elements
				private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"
				
				private static final def FIRST_NAME = ".//*[@id='edit_profile']/h3/span[1]"
		
				private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
				
				private static final def VIEW_CERTIFICATE = "//a[normalize-space(text())='View certificate']"
				
				private static final def USERNAME_CERTIFICATE = ".//*[@id='certificate_content']/div/table[2]/tbody/tr[2]/td/h3"
				
				private static final def PODNAME_CERTIFICATE = ".//*[@id='certificate_content']/div/table[2]/tbody/tr[4]/td/h3"
				
				private static final def ADDSKILLS_TOPROFILE = "//a[normalize-space(text())='Add skills to your profile']"
				
				private static final def ERROR_ADDSKILLS = ".//*[@id='main_page']/div[1]/div/span"
				
				private static final def PODNAME_PODPROGRESS = "//div[@class='col-sm-6 col-md-6 mb_10']/p"
		
				private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
				private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"
		
				private static final def OVERVIEW = "//a[text()='Overview']"
		
				private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
				private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"
		
				private static final def ADD_TO_WISHLIST = "//div[@ng-hide='isListEmpty']/div[3]/div/span"
		
				private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"
				
				private static final def ADDSKILLS_ERRMSG = "Selected set of skills are already in your profile"
						
				private static final def FIELDS = [SEARCH_TEXT]// the error fields.
				
				//error message map (Key-Value Pair)
				def static final searchPodPageErrorMessageMap = []
		
				//To enter data
				def static final populateFields = { browser, formData ->
					println ("CompletedPodsSearchForm.populateFields - data: " + formData)
					def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
					if(outcome.isSuccess()){
						for(int i = 0; i < FIELDS.size(); i++){
							browser.delay(1000)
							if(FIELDS[i].equals(SEARCH_TEXT)){
								def searchInput = formData[i].trim()
								browser.populateField(FIELDS[i],formData[i].trim())
								KPCommonPage.podName = searchInput
							}
							browser.delay(1000)
						}
					}
					return outcome
				}
				
			
				
				public static def getProfileName = {browser, formData ->
					def userName
					def userType = "USER"
					if(browser.getElement(Browser.XPATH, FIRST_NAME)!=null && browser.isDisplayed(FIRST_NAME)){
						userName = (browser.gettext(FIRST_NAME)).trim()
						KPCommonPage.profileName = userName
						KPCommonPage.profileType = userType
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "FirstNameDisplayIssue", "First Name is not appearing in Dashboard.")
					}
				}
				
				public static def clickAndSwitchToCertificateTab = {browser, formData ->
					def firstCertificate
					if(browser.isDisplayed(POD_LIST)){
						if(browser.isDisplayed(VIEW_CERTIFICATE)){
							browser.delay(1000)
							firstCertificate = (browser.getListElements(VIEW_CERTIFICATE))[0]
							if(firstCertificate!=null){
								browser.delay(500)
								browser.clickElement firstCertificate
								browser.delay(3000)
								KPCommonPage.waitAndSwitchToNewWindow(browser, formData)
								browser.delay(2000)
								return new SuccessOutcome()
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
					}
				}
				
				public static def correctCertificateContents = {browser , formData ->
					
					if(browser.getElement(Browser.XPATH,USERNAME_CERTIFICATE)!=null && browser.getElement(Browser.XPATH,PODNAME_CERTIFICATE)!=null){
						if(KPCommonPage.profileName!=null && KPCommonPage.getFirstPodName !=null){
							def certName = browser.gettext(USERNAME_CERTIFICATE).trim()
							def podName = browser.gettext(PODNAME_CERTIFICATE).trim()
							int pDisplayed = podName.lastIndexOf("-")
							def podNameDisplayed=podName.substring(0,pDisplayed).trim()
							if(certName.equalsIgnoreCase(KPCommonPage.profileName) && podNameDisplayed.equalsIgnoreCase(KPCommonPage.getFirstPodName)){
								browser.delay(5000)
								println "Successfully Verified Certificate"
								browser.closeWindow()
								return new SuccessOutcome()
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "CertificateDetailsMismatch","Either UserName or Pod Name is incorrect in Certificate.")
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "ProfileOrPodNameIsNULLIssue", "Either Profile name or pod name is Null")
					}
				}
				
			public static def correctErrorMsgAddSkills =  {browser, formData ->
				def addSkillsLink
				if(browser.isDisplayed(POD_LIST)){
					if(browser.isDisplayed(ADDSKILLS_TOPROFILE)){
						browser.delay(1000)
						addSkillsLink = (browser.getListElements(ADDSKILLS_TOPROFILE))[0]
						if(addSkillsLink!=null){
							browser.delay(500)
							browser.clickElement addSkillsLink
							def actualErrMsg
							for (int i = 0; i < 20; i++) {
								if (browser.isDisplayed(ERROR_ADDSKILLS)) {
									println "Form Message is Displayed Successfully."
									actualErrMsg = browser.gettext(ERROR_ADDSKILLS).trim()
									break;
								} else {
									browser.delay(500)
								}
							}
							if(actualErrMsg.equalsIgnoreCase(ADDSKILLS_ERRMSG)){
								println "Successfully Verified Error Message"
								 return new SuccessOutcome()
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "InCorrectErrorMsgIssue", "Incorrect error message is appearing.")
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
				}
			}
					
				
	}
	
	


	static final class CompletedPodsFilterForm extends WebForm {

		
		//Ongoing Pod filter form elements
		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
				
		private static final def FILTER_SKILL = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']//input" //"//div[@class='list-section resp_ml_20 resp_mr_20 ng-scope']/filter-list//input[@placeholder='Skills']"
		
		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def FILTER_INDUSTRY = "//md-select[@ng-model='functionCall.filter.industry']"
		
		private static final def FILTER_LEVEL = "//md-select[@ng-model='functionCall.filter.level']"
		
		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"
		
		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"
	
		private static final def POD_CREATED_BY = "//span[i[@class='kp-pod blue mr_5']]"
		
		private static final def DURATION_LIST = "//span[i[@class='kp-timer blue mr_5']]"
		
		private static final def FILTER_MIN_DURATION = "//md-select[@ng-model='functionCall.filter.minDuration']"
		
		private static final def FILTER_MIN_DURATION_LIST = "//md-option[@ng-value='minduration']"
		
		private static final def FILTER_MAX_DURATION = "//md-select[@ng-model='functionCall.filter.maxDuration']"
		
		private static final def FILTER_MAX_DURATION_LIST = "//md-option[@ng-value='maxDuration']"
		
		private static final def FILTER_SORT_BY = "//select[@ng-model='functionCall.sortBy']"
		
		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
		private static final def ELEMENT2 = "html/body/md-backdrop"
		
		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"
		
		private static final def OVERVIEW = ".//a[@class='select individual-tab']"
		
		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
				
		private static final def SKILL_REMOVE_MARK = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//i"
		
		private static final def SELCTED_SKILLLIST = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//p"
		
		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/h3"
				
		private static final def PAGINATION_NEXT = "//li[@class='pagination-last ng-scope disabled']"
		
		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		private static final def FIELDSLIST = [FILTER_SKILL, FILTER_INDUSTRY_LIST, FILTER_LEVEL_LIST, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final ongoingPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("CompletedPodsFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def viewingCount = CompletedPodsPage.getTotalViewingPodsCount(browser,formData)
							KPCommonPage.viewCount = viewingCount
							browser.delay(1000)
							def skillsList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int j = 0; j < data.length; j++){
									skillsList.add(data[j])
									browser.populateField(FIELDS[i], data[j])
									browser.delay(4000)
									KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST, data[j])
								}
							}else{
								skillsList.add(formData[i])
								browser.populateField(FIELDS[i],formData[i])
								browser.delay(4000)
								KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST , formData[i])
							}
							KPCommonPage.skillListsFilter = skillsList
						}
					}else if(FIELDS[i].equals(FILTER_MIN_DURATION) && FIELDS[i+1].equals(FILTER_MAX_DURATION)){
                        def minHrsData,maxHrsData
						browser.scrollToElement2(FILTER_MIN_DURATION)
						if(!formData[i].equals("") && !formData[i+1].equals("") ){
							
							browser.click FILTER_MIN_DURATION
							browser.delay(1000)
							minHrsData = formData[i]
							KPCommonPage.selectAutoComplete(browser, FILTER_MIN_DURATION_LIST , formData[i])
							browser.delay(1000)
							browser.click(ELEMENT2)
							browser.scrollToElement2(FILTER_MAX_DURATION)
							browser.delay(1000)
							browser.click FILTER_MAX_DURATION
							browser.delay(1000)
							maxHrsData = formData[i+1]
							KPCommonPage.selectAutoComplete(browser, FILTER_MAX_DURATION_LIST , formData[i+1])
							browser.delay(2000)
							KPCommonPage.minTimeData = minHrsData
							KPCommonPage.maxTimeData = maxHrsData
						}
					}else if(FIELDS[i].equals(FILTER_SORT_BY)){
						if(!formData[i].equals("")){
							def sortData
							browser.click FILTER_SORT_BY
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(FILTER_SORT_BY,formData[i])
							browser.delay(2000)
							KPCommonPage.sortByData = sortData
						}
					}else {
					
						if(!formData[i].equals("")){
							if(FIELDS[i].equals(FILTER_INDUSTRY)){
								browser.click FILTER_INDUSTRY
								browser.delay(2000)
								def industryList = []
								if(formData[i].contains(",")){
									def data = formData[i].split(",")
									for(int k = 0; k < data.length; k++){
										industryList.add(data[k])
										KPCommonPage.selectAutoComplete(browser, FIELDSLIST[i] , data[k])
									}
								}else{
									industryList.add(formData[i])
									KPCommonPage.selectAutoComplete(browser, FIELDSLIST[i] , formData[i])
								}
								KPCommonPage.industryListsFilter = industryList
								browser.click(ELEMENT)
							}else if(FIELDS[i].equals(FILTER_LEVEL)){
								browser.click FILTER_LEVEL
								browser.delay(2000)
								def industryList = []
								if(formData[i].contains(",")){
									def data = formData[i].split(",")
									for(int k = 0; k < data.length; k++){
										industryList.add(data[k])
										KPCommonPage.selectAutoComplete(browser, FIELDSLIST[i] , data[k])
									}
								}else{
									industryList.add(formData[i])
									KPCommonPage.selectAutoComplete(browser, FIELDSLIST[i] , formData[i])
								}
								KPCommonPage.industryListsFilter = industryList
								browser.click(ELEMENT)
							}
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}
		
		
		//to get all the pod names from Search pod list page in a list
		public static def getPodTextList(def browser,def element, def podList,def fiftyRes){
			def allResult
			browser.scrollToElement2(element)
			browser.delay(3000)
			if(browser.isDisplayed(podList)){
				if(browser.isDisplayed(fiftyRes)){
					browser.delay(3000)
					allResult = browser.getLists(podList)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return allResult
		}
		
		
		
		//To get the total viewing Pod's count in search pod page.
		def static final getTotalViewingPodsCount = {browser, formData ->
			
			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					//browser.click FIFTY_RESULT
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header mt10 ng-binding']").split(" ")[3].trim()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return totalResult
			
		}
		
	}

	
}