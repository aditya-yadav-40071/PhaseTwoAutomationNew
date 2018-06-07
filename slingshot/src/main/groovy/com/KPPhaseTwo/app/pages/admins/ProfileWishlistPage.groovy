package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage




final class ProfileWishlistPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		println ".....Inside Populate Overrided method....."
		if(formKey.equals("inputFilter")){
			new ProfileWishListForm().populateFields(browser, formData);
		}
		
	}
	
	
	def static isProfileWishlistDisplayed = { browser, formData ->
		new ProfileWishListForm().isProfileWishlistDisplayed browser, formData
	}
	
	/*def static getTotalViewingPodsCount ={ browser, formData ->
		new ProfileWishListForm().getTotalViewingPodsCount browser, formData
	}*/
	
	def static isCorrectProfileDisplayed = {browser, formData ->
		new ProfileWishListForm().isCorrectProfileDisplayed browser, formData
	}
	
	def static isCorrectProfileWithOrg = {browser, formData ->
		new ProfileWishListForm().isCorrectProfileWithOrg browser, formData
	}
	
	
	
	


	static final class ProfileWishListForm extends WebForm {

		//Profile whishlist form elements
		private static final def PROFILE_NAMES_LIST = "//h3[contains(@class,'content-name job-title')]"
		
		private static final def PROFILE_DETAIL_PAGE = "//h3[contains(@class,'content-name job-title')]"
		
		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"
		
		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
				
		private static final def FILTER_ORGANIZATION = "//md-select[@placeholder='Select Organization']"
		
		private static final def FILTER_ORGANIZATION_LIST = "//md-option[@ng-value='company.orgId']"
		
		private static final def PROFILE_ORG_NAME = "//span[contains(@ng-if,'organizationName')]"
		
		private static final def FILTER_SORT_BY = "//select[@ng-model='sortBy']"
		
		private static final def CLICK_PROFILE_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
		private static final def ELEMENT2 = "html/body/md-backdrop"
		
		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"
		
		private static final def CLICK_DASHBOARD_BACK = "//a[text()='Dashboard']"
				
		private static final def SKILL_REMOVE_MARK = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//i"
		
		private static final def SELCTED_SKILLLIST = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//p"
							
		private static final def ORG_REMOVE_MARK = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//i"
		
		private  static final def REMOVE_FROM_WISHLIST = "//span[contains(text(),'Remove From Wishlist')]"
							
		private static final def PAGINATION_NEXT = "//li[@class='pagination-last ng-scope disabled']"
		
		private static final def FIELDS = [FILTER_SKILL, FILTER_SORT_BY, FILTER_ORGANIZATION]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("profileWishlist.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def viewingCount = ProfileWishlistPage.getTotalViewingPodsCount(browser,formData)
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
					}
					
					if(FIELDS[i].equals(FILTER_ORGANIZATION)){
						if(!formData[i].equals("")){
							browser.click FILTER_ORGANIZATION
							browser.delay(1000)
							def orgList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int j = 0; j < data.length; j++){
									orgList.add(data[j])
									KPCommonPage.selectAutoComplete(browser, FILTER_ORGANIZATION_LIST, data[j])
								}
							}else{
								orgList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, FILTER_ORGANIZATION_LIST , formData[i])
							}
							browser.click(ELEMENT)
							KPCommonPage.orgListsFilter = orgList
						}
					}
					if(FIELDS[i].equals(FILTER_SORT_BY)){
						if(!formData[i].equals("")){
							def sortData
							browser.click FILTER_SORT_BY
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(FILTER_SORT_BY,formData[i])
							browser.delay(2000)
							KPCommonPage.sortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}
		
		


		
		
		
		
		
		
		//To get the total viewing Pod's count in profile wishlist page.
		/*def static final getTotalViewingPodsCount = {browser, formData ->
			
			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(PROFILE_NAMES_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split(" ")[3].trim()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return totalResult
			
		}*/
		
		
		def static final isProfileWishlistDisplayed = {browser, formData ->
			browser.delay(2500)
			if(browser.isDisplayed(PROFILE_NAMES_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					return new SuccessOutcome() 
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ViewPerPageIssue", "View Per Page is not Appearing")
				}
				
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ProfileNamesDisplayIssue", "Profile Name list does not display")
			}
		}
		
		//To check that onclick of profile name , it is redirecting to the correct profile 
		def static final isCorrectProfileDisplayed = {browser, formData ->
			
			browser.delay(2000)
			def profileNamesEle=[]
			def profileNameList = []
			if(browser.isDisplayed(PROFILE_NAMES_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					profileNameList = browser.getLists(PROFILE_NAMES_LIST)
					profileNamesEle = browser.getListElements(PROFILE_NAMES_LIST)
				    def firstName = profileNameList[0].trim()
					browser.clickElement profileNamesEle[0]
					browser.delay(4000)
					def profileNameInDetailPage = browser.gettext(PROFILE_DETAIL_PAGE).trim()
					 if(profileNameInDetailPage!=null){
						if(profileNameInDetailPage.equalsIgnoreCase(firstName)){
							return new SuccessOutcome()
						}else{
						    return KPCommonPage.returnFailureOutcome(browser, "ProfileNameMismatchIssue", "Profile name Does not match in Detail Page ")
						}
					 }else{
					 	return KPCommonPage.returnFailureOutcome(browser, "ProfileNameNULLIssue", "Profile name is appearing as NULL ")
					 }
						
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ViewPerPageIssue", "View Per Page is not Appearing")
				}
				
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ProfileNamesDisplayIssue", "Profile Name list does not display")
			}
		}
		
		
		
		
		def static final isCorrectProfileWithOrg  = {browser, formData ->
			browser.delay(2000)
			def profileNamesEle=[]
			def profileNameList = []
			if(browser.isDisplayed(PROFILE_NAMES_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					profileNameList = browser.getLists(PROFILE_NAMES_LIST)
					profileNamesEle = browser.getListElements(PROFILE_NAMES_LIST)
					def inputOrgList = KPCommonPage.orgListsFilter
					def matchingResult = false
					
						for(int i = 0; i < profileNameList.size()-1; i++){
							
							def allResultElement = browser.getListElements(PROFILE_NAMES_LIST)
							browser.scrollToElement(allResultElement[i])
							browser.delay(2000)
							browser.clickElement allResultElement[i]
							browser.delay(2000)
							def actOrgName = browser.gettext(PROFILE_ORG_NAME)
						
								if(inputOrgList != null || !inputOrgList.isEmpty()){
									for(int k = 0; k < inputOrgList.size(); k++){
											if(inputOrgList[k].trim().equalsIgnoreCase(actOrgName)){
												matchingResult = true
											}
									}
								}else{
									return KPCommonPage.returnFailureOutcome(browser, "OrganizationListIssue", "Actual  List is Either Null or Empty ")
								}
							browser.click CLICK_PROFILE_BACK
							browser.delay(2000)
						}
						if(matchingResult){
							return new SuccessOutcome()
						}else{
						    return KPCommonPage.returnFailureOutcome(browser, "OrganizationNameMismatchIssue", "Input Organization is not matching with Detail Page ")
						}	 
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ViewPerPageIssue", "View Per Page is not Appearing")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ProfileNamesDisplayIssue", "Profile Name list does not display")
			}
			
			
		}
		
		
		
		
		
		
		
		
	}

	
}