package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage




final class PodWishlistPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new PodWishlistSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new PodWishlistFilterForm().populateFields(browser, formData);
		}
	}
	
	
	def static addingPodsToWishList = { browser, formData ->
		new PodWishlistFilterForm().addingPodsToWishList browser, formData
	}
	
	def static isPodWishlistDisplayed = { browser, formData ->
		new PodWishlistFilterForm().isPodWishlistDisplayed browser, formData
	}
	
	def static getTotalViewingPodsCount ={ browser, formData ->
		new PodWishlistFilterForm().getTotalViewingPodsCount browser, formData
	}
	
	def static removeAddedPodWishlist = { browser, formData ->
		new PodWishlistFilterForm().removeAddedPodWishlist browser, formData
		
	}
	
	
	
	static final class PodWishlistSearchForm extends WebForm {
		
				//Podwishlist search form elements
				private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"
		
				private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
		
				private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
				private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"
		
				private static final def OVERVIEW = "//a[text()='Overview']"
		
				private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
				private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"
				
				private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"
		
				private static final def WISHLISTED_POD = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"
		
				private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"
		
				private static final def FIELDS = [SEARCH_TEXT]// the error fields.
				
				//error message map (Key-Value Pair)
				def static final searchPodPageErrorMessageMap = []
		
				//To enter data
				def static final populateFields = { browser, formData ->
					println ("PodWishlistSearchForm.populateFields - data: " + formData)
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
				
		
		}
	
	
	static final class PodWishlistFilterForm extends WebForm {

		//Pod whishlist form elements
		private static final def SEARCH_TEXT = ".//*[@id='courseSearch']"
		
		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
		
		private static final def ADD_TO_WISHLIST = "//span[text()='Add to wishlist']"
		
		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"
		
		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def FILTER_INDUSTRY = ".//md-select[@ng-model='functionCall.filter.industry']"
		
		private static final def FILTER_LEVEL = ".//md-select[@ng-model='functionCall.filter.level']"
		
		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"
		
		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"
	
		private static final def FILTER_ORGANIZATION = "//input[@placeholder='Organisation']"
		
		private static final def FILTER_ORGANIZATION_LIST = "//div[@class='autocomplete-list']/ul/li"
		
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
				
		private static final def SELECTED_ORGLIST = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//p"
			
		private static final def ORG_REMOVE_MARK = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//i"
		
		private  static final def REMOVE_FROM_WISHLIST = "//span[contains(text(),'Remove From Wishlist')]"
						
		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/h3"
				
		private static final def PAGINATION_NEXT = "//li[@class='pagination-last ng-scope disabled']"
		
		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		private static final def FIELDSLIST = [FILTER_SKILL, FILTER_INDUSTRY_LIST, FILTER_LEVEL_LIST, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("PodWishlistFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def viewingCount = PodWishlistPage.getTotalViewingPodsCount(browser,formData)
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
		
		


		static def final addingPodsToWishList = { browser, formData ->
			def xpathToSelect
			def wishList = []
			def podLst = []
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					//browser.click FIFTY_RESULT
					browser.delay(3000)
					def lists = browser.getLists(POD_LIST)
					podLst.addAll(lists)
					KPCommonPage.podWishlistedList = podLst
					def podWishlists = browser.getLists(ADD_TO_WISHLIST)
					xpathToSelect = browser.getListElements(ADD_TO_WISHLIST)
					for(int i =0; i<= lists.size()-1;i++){
							browser.delay(800)
							browser.clickElement(xpathToSelect[i])
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
		}
		
		
		//to check all Wishlisted pods are added to PodWishlist page or not.
		def static final isPodWishlistDisplayed = { browser, formData ->
			browser.delay(3000)
			def podList = []
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					//browser.click FIFTY_RESULT
					browser.delay(3000)
					def lists = browser.getLists(POD_LIST)
					podList.addAll(lists)
					if(podList.containsAll(KPCommonPage.podLst)){
						println "PodWishlist contains the same pods which were added."
						return new SuccessOutcome()
					}else {
						return KPCommonPage.returnFailureOutcome(browser, "PodWishlistIssue", "All the added pods added to pod wishlist are not reflecting in PodWishList page.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
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
		
		
		def static final removeAddedPodWishlist = { browser, formData ->
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)
					while(browser.isDisplayed(REMOVE_FROM_WISHLIST)){
							browser.delay(500)
							browser.click REMOVE_FROM_WISHLIST
							browser.delay(1000)
					}
					if(browser.isDisplayed(REMOVE_FROM_WISHLIST)){
							return KPCommonPage.returnFailureOutcome(browser, "WishlistedpodRemoveIssue", "After Removing Wishlisted pods are still displaying")
					}else{
							println "Successfully all pods are removed from Pod Wishlist"
							return new SuccessOutcome()
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			
		}
		
		
		
		
		
	}

	
}