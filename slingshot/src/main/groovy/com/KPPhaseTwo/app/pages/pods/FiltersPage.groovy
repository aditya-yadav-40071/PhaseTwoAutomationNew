package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage
import com.KPPhaseTwo.app.pages.pods.SearchPodPage

/**
 * Created by Bishnu das on 12/04/2017
 */

final class FiltersPage extends WebPage {


	//To fetch the pod names if they are displaying
	def static getPodTextList = { browser, element, podList, fiftyRes ->
		new FiltersForm().getPodTextList browser, element, podList, fiftyRes
	}

	def static getPageCount = { browser ->
		new FiltersForm().getPageCount browser
	}

	//To verify if the skills are entered.
	def static displayedSkillFilter = {browser, formData ->
		new FiltersForm().displayedSkillFilter browser, formData
	}

	def static displayedIndustryFilter ={browser, formData ->
		new FiltersForm().displayedIndustryFilter browser, formData
	}

	def static displayedLevelFilter ={browser, formData ->
		new FiltersForm().displayedLevelFilter browser, formData
	}

	def static displayedMinAndMaxDurationFilter ={browser, formData ->
		new FiltersForm().displayedMinAndMaxDurationFilter browser, formData
	}

	def static displayedSortByFilter ={browser, formData ->
		new FiltersForm().displayedSortByFilter browser, formData
	}

	def static removeSelectedSkills ={browser, formData ->
		new FiltersForm().removeSelectedSkills browser, formData
	}




	static final class FiltersForm extends WebForm {


		//SearchPod form elements with filters
		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def PODOVERVIEW_INDUSTRY = "//div[div[h5[contains(text(),'Industry')]]]/div[2]/span"

		private static final def PODOVERVIEW_LEVEL = "//div[div[h5[contains(text(),'Level')]]]/div[2]/span"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def ADD_TO_WISHLIST = "//span[contains(text(),' wishlist')]"

		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"

		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"

		private static final def FILTER_SELECTED_SKILLS = "//input[@placeholder='Skills']//following-sibling::ul//p"

		private static final def FILTER_INDUSTRY = ".//md-select[@ng-model='functionCall.filter.industry']"

		private static final def FILTER_LEVEL = ".//md-select[@ng-model='functionCall.filter.level']"

		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"

		private static final def FILTER_SELECTED_INDUSTRY = "//md-select[@ng-model='functionCall.filter.industry']//span[1]/div"

		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"

		private static final def FILTER_SELECTED_LEVEL = "//md-select[@ng-model='functionCall.filter.level']//span[1]/div"

		private static final def FILTER_ORGANIZATION = "//input[@placeholder='Organisation']"

		private static final def FILTER_ORGANIZATION_LIST = "//div[@class='autocomplete-list']/ul/li"

		private static final def POD_CREATED_BY = "//span[i[@class='kp-pod blue mr_5']]"

		private static final def DURATION_LIST = "//span[i[@class='kp-timer blue mr_5']]"

		private static final def FILTER_MIN_DURATION = "//md-select[@ng-model='functionCall.filter.minDuration']"

		private static final def FILTER_MIN_DURATION_LIST = "//md-option[@ng-value='minduration']"

		private static final def FILTER_SELECTED_MINMAX_TIME = "//filter-list/div[1]/div[contains(@class,'duration')]//span[1]/div"

		private static final def FILTER_MAX_DURATION = "//md-select[@ng-model='functionCall.filter.maxDuration']"

		private static final def FILTER_MAX_DURATION_LIST = "//md-option[@ng-value='maxduration']"

		private static final def FILTER_SORT_BY = "//select[@ng-model='functionCall.sortBy']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def ELEMENT2 = "html/body/md-backdrop"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def OVERVIEW = "//a[@data-target='#overview']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def SKILL_REMOVE_MARK = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//i"

		private static final def SELCTED_SKILLLIST = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//p"

		private static final def SELECTED_ORGLIST = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//p"

		private static final def ORG_REMOVE_MARK = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//i"

		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/h3"

		private static final def NO_OF_PEOPLE = "//h5[contains(text(),'No of People in the Batch')]"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"  //"//li[@class='pagination-page ng-scope active']/a"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def LAST_BUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []


		//to get all the pod names from Search pod list page in a list
		public static def getPodTextList(def browser,def element, def podList,def fiftyRes){
			def allResult
			browser.scrollToElement2(element)
			browser.delay(3000)
			if(browser.isDisplayed(podList)){
				if(browser.isDisplayed(fiftyRes)){
					//browser.click fiftyRes
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

		def static getPageCount(def browser){
			int lastPage=1
			if(browser.checkEnabled(PAGINATION_NEXT)){
				browser.delay(1000)
				def pageText = browser.gettext(LAST_PAGE_NO)
				browser.delay(1000)
				lastPage = pageText.toInteger()
				println "...lastPage......"+lastPage

			}else{
				browser.delay(1000)
				lastPage
			}
		}

		//To verify that the result displayed for the entered skills through filter are shown correctly
		def static displayedSkillFilter = { browser, formData ->
			browser.delay(2000)
			def allResult = FiltersPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			println "....allResult....."+allResult
			def matchingResult = false
			int lastPage = FiltersPage.getPageCount(browser)
			for(int w=0;w<lastPage;w++){
				outerloop:for(int i = 0;i<allResult.size();i++){
					def allResultElement = browser.getListElements(POD_LIST)
					def allAddToWishlist = browser.getListElements(ADD_TO_WISHLIST)
					browser.scrollToElement(allResultElement[i])
					browser.delay(1000)
					KPCommonPage.clickAddToWishlist(browser,ADD_TO_WISHLIST,allAddToWishlist[i])
					browser.delay(500)
					browser.clickElement allResultElement[i]
					browser.delay(4000)
					if(browser.isDisplayed(OVERVIEW)){
						browser.scrollToElement2(OVERVIEW)
						browser.delay(2000)
						def label = browser.getLists("//div[@class='row']/div/h5")
						def value = browser.getLists("//div[@class='row']/div[2]/span")
						for(int j = 0;j < label.size();j++){
							if(label[j].equalsIgnoreCase("Skills")){
								def inputSkillList = KPCommonPage.skillListsFilter
								def actualSkillList = []
								if(value[j].contains(",")){
									def data = value[j].split(",")
									for(int m = 0; m < data.length; m++){
										actualSkillList.add(data[m].trim())
									}
								}else{
									actualSkillList.add(value[j])
								}
								if(inputSkillList != null || !inputSkillList.isEmpty()){
									for(int k = 0; k < inputSkillList.size(); k++){
										for(int r = 0; r < actualSkillList.size(); r++){
											if(inputSkillList[k].equalsIgnoreCase(actualSkillList[r])){
												matchingResult = true
											}
										}
									}
								}else{
									return KPCommonPage.returnFailureOutcome(browser, "SkillListIssue", "Actual Skill List is Either Null or Empty ")
								}
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
					}
					browser.click CLICK_PODS_BACK
					browser.delay(2000)
					if(!browser.isDisplayed(FILTER_SELECTED_SKILLS)){
						return KPCommonPage.returnFailureOutcome(browser, "FilterSkillsRetainingIssue", "Unable to retain the selected skills in filter.")
					}
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
			if(matchingResult){
				println "Success for Skills filter"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SkillsDisplayIssue", "Skills does not match.")
			}
		}




		//To verify that the result displayed for the entered Industry through filter are shown correctly
		def static displayedIndustryFilter = { browser, formData ->
			browser.delay(3000)
			def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			def matchingResult = false
			outerloop:for(int i = 0; i < allResult.size(); i++){
				def allResultElement = browser.getListElements(POD_LIST)
				def allAddToWishlist = browser.getListElements(ADD_TO_WISHLIST)
				browser.scrollToElement(allResultElement[i])
				browser.delay(1000)
				KPCommonPage.clickAddToWishlist(browser,ADD_TO_WISHLIST,allAddToWishlist[i])
				browser.delay(500)
				browser.clickElement allResultElement[i]
				browser.delay(3000)
				if(browser.isDisplayed(OVERVIEW)){
					browser.scrollToElement2(OVERVIEW)
					browser.delay(2000)
					browser.scrollToElement2(PODOVERVIEW_INDUSTRY)
					browser.delay(500)
					def podIndustry = browser.gettext(PODOVERVIEW_INDUSTRY)
					def inputIndustryList = KPCommonPage.industryListsFilter
					def actualIndustryList = []
					if(podIndustry.contains(",")){
						def data = podIndustry.split(",")
						for(int m = 0; m < data.length; m++){
							actualIndustryList.add(data[m].trim())
						}
					}else{
						actualIndustryList.add(podIndustry)
					}
					if(inputIndustryList != null || !inputIndustryList.isEmpty()){
						for(int k = 0; k < inputIndustryList.size(); k++){
							for(int r = 0; r < actualIndustryList.size(); r++){
								if(inputIndustryList[k].equalsIgnoreCase(actualIndustryList[r])){
									matchingResult = true
								}
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "IndustryListIssue", "Actual Industry List is Either Null or Empty ")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
				}
				browser.click CLICK_PODS_BACK
				browser.delay(3000)
				if(!browser.isDisplayed(FILTER_SELECTED_INDUSTRY)){
					return KPCommonPage.returnFailureOutcome(browser, "FilterIndustryRetainingIssue", "Unable to retain the selected Industry in filter.")
				}
			}
			if(matchingResult){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IndustryNotMatchIssue", "Industry  does not match.")
			}

		}





		//To verify that the result displayed for the entered Level through filter are shown correctly
		def static displayedLevelFilter = { browser, formData ->
			browser.delay(2000)
			def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			def matchingResult = false
			outerloop:for(int i = 0; i < allResult.size(); i++){
				def allResultElement = browser.getListElements(POD_LIST)
				browser.scrollToElement(allResultElement[i])
				def allAddToWishlist = browser.getListElements(ADD_TO_WISHLIST)
				browser.delay(2000)
				KPCommonPage.clickAddToWishlist(browser,ADD_TO_WISHLIST,allAddToWishlist[i])
				browser.delay(500)
				browser.clickElement allResultElement[i]
				browser.delay(3000)
				if(browser.isDisplayed(OVERVIEW)){
					browser.scrollToElement2(OVERVIEW)
					browser.delay(2000)
					browser.scrollToElement2(PODOVERVIEW_LEVEL)
					browser.delay(500)
					def podLevel = browser.gettext(PODOVERVIEW_LEVEL)
					println "====KPCommonPage.industryListsFilter==="+KPCommonPage.industryListsFilter
					def inputLevelList = KPCommonPage.industryListsFilter
					def actualLevelList = []
					if(podLevel.contains(",")){
						def data = podLevel.split(",")
						for(int m = 0; m < data.length; m++){
							actualLevelList.add(data[m].trim())
						}
					}else{
						actualLevelList.add(podLevel)
					}
					if(inputLevelList != null || !inputLevelList.isEmpty()){
						for(int k = 0; k < inputLevelList.size(); k++){
							for(int r = 0; r < actualLevelList.size(); r++){
								println "===inputLevelList[k]===="+inputLevelList[k]+"===actualLevelList[r]==="+actualLevelList[r]
								if(inputLevelList[k].equalsIgnoreCase(actualLevelList[r])){
									matchingResult = true
								}
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "LevelListIssue", "Actual Level List is Either Null or Empty ")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
				}
				browser.scrollToElement2(CLICK_PODS_BACK)
				browser.click CLICK_PODS_BACK
				browser.delay(4000)
				if(!browser.isDisplayed(FILTER_SELECTED_LEVEL)){
					return KPCommonPage.returnFailureOutcome(browser, "FilterLevelRetainingIssue", "Unable to retain the selected Level in filter.")
				}
			}
			if(matchingResult){
				println "Success for Level filter"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "LevelNotMatchIssue", "Level  does not match.")
			}
		}




		//To verify that the result displayed for the selected Min and Max Duration through filter are shown correctly
		def static displayedMinAndMaxDurationFilter = { browser, formData ->
			browser.delay(2000)
			def minHrs = KPCommonPage.minTimeData
			def maxHrs = KPCommonPage.maxTimeData
			//def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			def result = false
			int lastPage = FiltersPage.getPageCount(browser)
			for(int w=0;w<lastPage;w++){
				def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
				outerloop:for(int i = 0; i < allResult.size(); i++){
					def allResultElement = browser.getListElements(POD_LIST)
					def allAddToWishlist = browser.getListElements(ADD_TO_WISHLIST)
					browser.scrollToElement(allResultElement[i])
					browser.delay(1000)
					KPCommonPage.clickAddToWishlist(browser,ADD_TO_WISHLIST,allAddToWishlist[i])
					browser.delay(500)
					browser.clickElement allResultElement[i]
					browser.delay(3000)
					if(browser.isDisplayed(OVERVIEW)){
						browser.scrollToElement2(OVERVIEW)
						browser.delay(2000)
						String[] duration = browser.gettext("//div[h5[text()='Duration']]/following-sibling::div//span").split(" ")
						println "....Duration on web is ......."+duration[0]
						def hours = Integer.parseInt(duration[0])
						def minHours = Integer.parseInt(minHrs)
						def maxHours = Integer.parseInt(maxHrs)
						if(hours>=minHours && hours<=maxHours){
							result = true
						}else{
							result = false
							break outerloop
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
					}
					browser.scrollToElement2(CLICK_PODS_BACK)
					browser.click CLICK_PODS_BACK
					browser.delay(3000)
					if(!browser.isDisplayed(FILTER_SELECTED_MINMAX_TIME)){
						return KPCommonPage.returnFailureOutcome(browser, "FilterMinMaxDurationRetainingIssue", "Unable to retain the selected Min Max Duration in filter.")
					}
				}

				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
			if(result){
				println "MinMaxDuration Success"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "HoursDisplayIssue", "Hours does not match with the input filters.")
			}

		}



		//To verify that the result displayed for the selected Sort By through filter are shown correctly
		def static final displayedSortByFilter = { browser, formData ->
			def sortBy = KPCommonPage.sortByData
			def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			//def noOfPeopleList = browser.getLists("//h5[contains(text(),'No of People in the Batch')]")
			browser.delay(1000)
			if(sortBy.equalsIgnoreCase("Alphabetical Z-A")){
				def result = KPCommonPage.isSorted(allResult)
				if(!result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical Z-A is not Working.")
				}
			}else if(sortBy.equalsIgnoreCase("Alphabetical A-Z") || sortBy.equalsIgnoreCase("Sort by Name")
			|| sortBy.equalsIgnoreCase("Alphabetically")){
				def result = KPCommonPage.isSorted(allResult)
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical A-Z is not Working.")
				}
			}else if(sortBy.equalsIgnoreCase("Sort by No. of People")){
				def result = KPCommonPage.isSortedByNoOfPeople(browser,NO_OF_PEOPLE)
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortByNoOfPeopleIssue", "Sort By Number of people is not Working.")
				}
			}
		}




		//to remove the selected skill from the autosuggest in search pod page
		def static final removeSelectedSkills = { browser, formData ->

			def dataToRemoveList = KPCommonPage.skillListsFilter
			FiltersPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			println "===KPCommonPage.viewCount==="+KPCommonPage.viewCount
			def beforeSelectCount = KPCommonPage.viewCount
			println ".....beforeSelectCount......"+beforeSelectCount
			def beforeAdding =  Integer.parseInt(beforeSelectCount)
			if(dataToRemoveList != null || !dataToRemoveList.isEmpty()){
				for(int i=0;i<dataToRemoveList.size();i++){
					browser.delay(3500)
					KPCommonPage.removeSelectedAutosuggest( browser, SELCTED_SKILLLIST,SKILL_REMOVE_MARK, dataToRemoveList[i])
				}
				browser.delay(3000)
				def viewingCount = SearchPodPage.getTotalViewingPodsCount(browser,formData)
				def afterAdding =  Integer.parseInt(viewingCount)
				println "......afterAdding......"+afterAdding
				if(beforeAdding==afterAdding){
					println "Successfully Skills Removed"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "RemoveSelectedAutosuggestIssue", "After Removing autosuggest,the count is not proper.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FetchingSkillListIssue", "Unable to fetch Skill List from KPCommon Page. ")
			}

		}






	}
}
