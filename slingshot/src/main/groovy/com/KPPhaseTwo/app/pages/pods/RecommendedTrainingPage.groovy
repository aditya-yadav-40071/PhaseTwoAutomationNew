package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu Das on 12/04/2017
 */

final class RecommendedTrainingPage extends WebPage {

	
	
	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new RecommendedTrainingsSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new RecommendedTrainingForm().populateFields(browser, formData);
		}

	}
	
	
	//To get the number of pods in Recommended training page
	def static verifyPodsInRecommended = { browser, formData ->
		new RecommendedTrainingForm().verifyPodsInRecommended browser, formData
	}
	
	//To verify if the skills are entered.
	def static skillsEntered = {browser, formData ->
		new RecommendedTrainingForm().skillsEntered browser, formData
	}
	
	//To verify number of pods displayed for every click of number in ruler
	def static podsAndRulerNoMatch = {browser, formData ->
		new RecommendedTrainingForm().podsAndRulerNoMatch browser, formData
	}
	
	//To verify wishlisted pod displayed in the wishlist page
	def static wishPodDisplayed = {browser, formData ->
		new RecommendedTrainingForm().wishPodDisplayed(browser,  formData)
	}
	
	//To click on a particular pod in the list page
	def static clickPodToBuy = {browser, formData ->
		new RecommendedTrainingForm().clickPodToBuy browser, formData
	}
	
	
	
	static final class RecommendedTrainingsSearchForm extends WebForm {
		
				//SearchPod form elements
				private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"
		
				private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
				
				private static final def PODNAME_PODPROGRESS = "//div[@class='col-sm-6 col-md-6 mb_10']/p"
		
				private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
				private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"
		
				private static final def OVERVIEW = "//a[text()='Overview']"
		
				private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
				private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"
		
				private static final def ADD_TO_WISHLIST = "//div[@ng-hide='isListEmpty']/div[3]/div/span"
		
				private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"
		
				private static final def WISHLISTED_POD = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"
		
				private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"
		
				private static final def FIELDS = [SEARCH_TEXT]// the error fields.
				
				//error message map (Key-Value Pair)
				def static final searchPodPageErrorMessageMap = []
		
				//To enter data
				def static final populateFields = { browser, formData ->
					println ("RecommendedTrainingsSearchForm.populateFields - data: " + formData)
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
	
	
	
	
	
	
	static final class RecommendedTrainingForm extends WebForm {

		//Recommended Training Form elements
		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"
		
		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"
		
		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"
		
		private static final def FILTER_INDUSTRY = ".//md-select[@placeholder='Industry']"
		
		private static final def FILTER_LEVEL = ".//md-select[@placeholder='Level']"
		
		private static final def FILTER_MIN_DURATION = "//md-select[@placeholder='Min Duration']"
		
		private static final def FILTER_MAX_DURATION = "//md-select[@placeholder='Max Duration']"
		
		private static final def FILTER_MIN_DURATION_LIST = "//md-option[@ng-value='minduration']/div"
		
		private static final def FILTER_MAX_DURATION_LIST = "//md-option[@ng-value='maxDuration']/div"
		
		private static final def FILTER_SORT_BY = "//select[@ng-model='sortBy']"
		
		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def FILTER_INDUSTRY_LIST = "//md-option[@ng-value='opt.industryId']/div[2]"
		
		private static final def FILTER_LEVEL_LIST = "//md-option[@ng-repeat='opt in levels']/div[2]"
		
		private static final def CLICK_ELEMENT = "html/body/md-backdrop"
		
		private static final def LIST_OF_SKILLS = "//span[@class='ng-scope']/p"
		
		private static final def FILTER_DURATION_LIST = "//md-option[@ng-value='duration']"
		
		//pagination elements
		private static def LASTBUTTON = "//li[@class='pagination-last ng-scope']/a"
	
		private static def FIRSTBUTTON = "//li[@class='pagination-first ng-scope']/a"
	
		private static def NEXTBUTTON = "//li[@class='pagination-next ng-scope']/a"
		
		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"
		
		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"  //"//li[@class='pagination-page ng-scope active']/a"
				
		//Items per page Ruler elements
		private static final def ITEMS_IN_RULER = "//div[@ng-repeat='item in itemsPerPageList']/label"
		
		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]
		
		private static final def FIELDSLIST = [FILTER_SKILL, FILTER_INDUSTRY_LIST, FILTER_LEVEL_LIST, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		//To enter data
		def static final populateFields = { browser, formData ->
			println ("RecommendedTrainingFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							/*def viewingCount = RecommendedTrainingPage.getTotalViewingPodsCount(browser,formData)
							KPCommonPage.viewCount = viewingCount*/
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
							browser.click(ELEMENT)
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

		//To verify the number of pods in Recommended Training page
		def static final verifyPodsInRecommended = {browser, formData ->
			def podList = []
			def listOfPods = browser.getLists(POD_LIST)
			podList.addAll(listOfPods)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				def podsInNextPage = browser.getLists(POD_LIST)
				podList.addAll(podsInNextPage)
			}
			if(podList.size().equals(KPCommonPage.noOnDashboard)){
				println "Pods Count Matched Successfully"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "mismatchInNumber", "No of pods not the same.")
		}
		
		//To verify number of pods displayed for every click of number in ruler
		def static final podsAndRulerNoMatch = {browser, formData ->
			def itemsRulerElements = []
			def flag
			itemsRulerElements = browser.getListElements(ITEMS_IN_RULER)
			def rulerOptions = browser.getLists(ITEMS_IN_RULER)
			for(int i=0;i<itemsRulerElements.size();i++){
				def noOfPodsPerPage = []
				browser.scrollToElement(browser.getElement(Browser.XPATH, ITEMS_IN_RULER))
				if(browser.isDisplayed(ITEMS_IN_RULER)){
					browser.clickElement(itemsRulerElements[i])
				}else
			        return KPCommonPage.returnFailureOutcome(browser, "rulerNotDisplayed", "Ruler not displayed")
				browser.delay(3000)
				def listOfPods = browser.getLists(POD_NAMES)
				noOfPodsPerPage.add(listOfPods.size())
				while(browser.checkEnabled(PAGINATION_NEXT)){
					browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
					browser.click PAGINATION_NEXT
					browser.delay(1500)
					def podsInNextPage = browser.getLists(POD_NAMES)
					noOfPodsPerPage.add(podsInNextPage.size())
				}
				browser.delay(1500)
				if(Collections.max(noOfPodsPerPage)<= (rulerOptions[i].toInteger())){
					flag = true
				}else{
					flag=false
					return KPCommonPage.returnFailureOutcome(browser, "itemPodMismatch", "No of items and pods number have a mismatch.")
				}
			}
			if(flag){
				return new SuccessOutcome()
			}
		}
		
		//To verify if pod displayed in wshlist page after adding to wishlist from Recommended Trainings page
		def static final wishPodDisplayed = {browser, formDate ->
			if(browser.isDisplayed(POD_NAMES)){
				def listOfwishPods = KPCommonPage.paginationData(browser, POD_NAMES, "textData")
				println KPCommonPage.firstPodName
				if(listOfwishPods.contains(KPCommonPage.firstPodName)){
					println KPCommonPage.firstPodName
					println "Added to wishlist list"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "podMissingInWishlist", "Pod not reflecting in the wishlist.")
			}
		}
		
		//To verify if skills are entered after selecting.
		def static final skillsEntered = {browser, formData ->
			if(browser.isDisplayed(LIST_OF_SKILLS)){
				def listOfSkills = browser.getLists(LIST_OF_SKILLS)
				if(KPCommonPage.skillListsFilter.containsAll(listOfSkills)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "missingSkill", "Few skills missing in list.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "noElementFound", "Element Skill list not found.")
		}
		
		def static final clickPodToBuy  = {browser, formData ->
			println "Clicking pod-pagination"
			def flag
			if(KPCommonPage.getFirstPodName!=null){
				KPCommonPage.podToBuy_Entry = KPCommonPage.getFirstPodName
			}else{
				KPCommonPage.podToBuy_Entry = formData[0]
			}
			
			int lastPage=1
			if(browser.checkEnabled(PAGINATION_NEXT)){
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
			def listDataPerPage = browser.getLists(POD_LIST)
			def listElementPerPage = browser.getListElements(POD_LIST)
				for(int j=0;j<listDataPerPage.size();j++){
					if(listDataPerPage[j].trim().equalsIgnoreCase(KPCommonPage.podToBuy_Entry.trim())){
						KPCommonPage.allBoughtPodsList.add(KPCommonPage.podToBuy_Entry.trim())
						flag = true
						browser.clickElement listElementPerPage[j]
						browser.delay(3000)
						println "Pod Clicked"
						break
					}else
						flag = false
				}
				if(flag==true){
					break
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
					browser.delay(1000)
				}
			}
			if(flag){
				println "Successfully Pod Found and Clicked."
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "podNotFound", "Pod not found in list.")
		}
	}
}
