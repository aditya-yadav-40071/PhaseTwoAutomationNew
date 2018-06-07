package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage



final class SearchPodPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new SearchPodForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new FilterPodForm().populateFields(browser, formData);
		}
	}


	//To verify that the pod list is displayed on clicking search button
	def static podsDisplayed = { browser, formData ->
		new SearchPodForm().podsDisplayed browser, formData
	}


	//To verify that the result displayed for the searched pods are shown correctly
	def static correctPodsDisplayed = { browser, formData ->
		new SearchPodForm().correctPodsDisplayed browser, formData
	}

	//To verify that the pod name displayed in the pod details is same as the pod clicked
	def static podName = { browser, formData ->
		new SearchPodForm().podName browser, formData
	}


	//To add the first pod to wishlist
	def static addToWishlist = { browser, formData ->
		new SearchPodForm().addToWishlist browser, formData
	}

	//To verify that the wishlisted pod is displayed in wishlist page
	def static wishlistPodDsplyd = { browser, formData ->
		new SearchPodForm().wishlistPodDsplyd browser, formData
	}

	//To remove the first pod to wishlist
	def static removeFrmWshList = { browser, formData ->
		new SearchPodForm().removeFrmWshList browser, formData
	}

	//To verify that the wishlisted pod is removed from the pod wishlist page
	def static wishListedPodRemoved = { browser, formData ->
		new SearchPodForm().wishListedPodRemoved browser, formData
	}

	//To verify result shown per page is the same number pods displayed per page
	def static resultDisplayedPerPage = { browser, formData ->
		new SearchPodForm().resultDisplayedPerPage browser, formData
	}



	//To fetch the pod names if they are displaying
	def static getPodTextList = { browser, formData ->
		new FilterPodForm().getPodTextList browser, formData
	}

	/*//To remove selected skills from skill filter
	 def static removeSelectedSkills = {browser, formData ->
	 new FilterPodForm().removeSelectedSkills browser, formData
	 }*/


	//To get total number of viewing pod's count.
	def static getTotalViewingPodsCount = {browser, formData ->
		new FilterPodForm().getTotalViewingPodsCount browser, formData

	}

	static final class SearchPodForm extends WebForm {

		//SearchPod form elements
		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def OVERVIEW = "//a[text()='Overview']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"

		private static final def ADD_TO_WISHLIST = "//span[contains(text(),'wishlist')]"

		private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"

		private static final def WISHLISTED_POD = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"

		private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def LANGUAGE_DROPDOWN = "//md-select[@ng-model='languageIdType2Classroom']"

		private static final def LANGUAGE_LIST = "//md-option[@ng-value='language.languageId']/div"

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
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



		//To verify that the pod list is displayed on clicking search button
		def static final podsDisplayed = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(POD_LIST)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsNotDisplayedIssue", "Pods are not displayed on search")
			}
		}

		//To verify that the result displayed for the searched pods are shown correctly
		def static final correctPodsDisplayed = { browser, formData ->
			browser.delay(4000)
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.delay(1000)
					def allResult = browser.getLists(POD_LIST)
					def allResultElement = browser.getListElements(POD_LIST)
					browser.delay(2000)
					if(allResult[0]!=null && allResultElement[0]!=null){
						if(allResult[0].trim().equalsIgnoreCase(formData[0].trim())){
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "SearchedPodMismatchIssue", "The actual pod name do not match with the expected pod name")
						}
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
		}



		//To verify that the pod name displayed in the pod details is same as the pod clicked
		def static final podName = { browser, formData ->
			browser.scrollToElement2(ELEMENT)
			browser.delay(3000)
			def langList
			def langElement
			if(browser.isDisplayed(POD_NAME)){
				if(KPCommonPage.podName.equalsIgnoreCase("Knowledge Podium Tarang - Digital Literacy")){
					browser.click(LANGUAGE_DROPDOWN)
					browser.delay(500)
					langList = browser.getLists(LANGUAGE_LIST)
					langElement = browser.getListElements(LANGUAGE_LIST)
					for(int i=0;i<langList.size();i++) {
						if(langList[i].trim().equalsIgnoreCase("English")) {
							browser.clickElement(langElement[i])
							browser.delay(500)
							break
						}
					}
				}
				def podName = browser.gettext(POD_NAME)
				println "........podName.... "+podName+ "\nKPCommonPage.podName....... "+KPCommonPage.podName
				if(podName.equalsIgnoreCase(KPCommonPage.podName)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMatchIssue", "Pod name does not match in pod details page.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNameDisplayIssue", "Pod name is not displayed.")
			}
		}

		//To add the first pod to wishlist
		def static final addToWishlist = { browser, formData ->
			browser.delay(4000)
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME).trim()
				if(browser.gettext(ADD_TO_WISHLIST).contains("Add to wishlist")){
					browser.click ADD_TO_WISHLIST
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Add to wishlist link is not displayed.")
			}
		}

		//To verify that the wishlisted pod is displayed in wishlist page
		def static final wishlistPodDsplyd = { browser, formData ->
			browser.delay(3500)
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.gettext(WISHLISTED_POD)
				if(KPCommonPage.firstPodName.equalsIgnoreCase(name)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "WishlistedPodMatchIssue", "Wishlisted pod is not same in the wishlist page.")
				}

			}else{
				return KPCommonPage.returnFailureOutcome(browser, "WishlistDisplayIssue", "Wishlisted pod is not displayed in the wishlist page.")
			}
		}

		//To remove the first pod to wishlist
		def static final removeFrmWshList = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				//KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME)
				if(browser.gettext(ADD_TO_WISHLIST).contains("Remove from wishlist")){
					browser.click ADD_TO_WISHLIST
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
			}
		}

		//To verify that the wishlisted pod is removed from the pod wishlist page
		def static final wishListedPodRemoved = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.getLists(ALL_WISHLISTED_POD)
				def result = false
				for(int i = 0; i < name.size(); i++){
					if(KPCommonPage.firstPodName.equalsIgnoreCase(name[i])){
						result = false
						break
					}else{
						result = true
					}
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
				}
			}else{
				return new SuccessOutcome()
			}
		}

		//To verify result shown per page is the same number pods displayed per page
		def static final resultDisplayedPerPage = { browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				def totalPodPerPage = browser.getLists(POD_LIST)
				def totalResult = browser.gettext("//p[@class='clearfix content output-count-header mt10 ng-binding']").split("-")[1].subSequence(0, 2).trim()
				def viewPerPage = Integer.parseInt(totalResult);
				if(totalPodPerPage.size()==viewPerPage){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ResultPerPageDisplayIssue", "Result for per page does not match in displayed result.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsListDisplayIssue", "Pod List is not Appearing")
			}
		}
	}

	static final class FilterPodForm extends WebForm {

		//SearchPod form elements with filters
		//private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"

		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"

		private static final def FILTER_INDUSTRY = "//md-select[@ng-model='functionCall.filter.industry']"

		private static final def FILTER_LEVEL = "//md-select[@ng-model='functionCall.filter.level']"

		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"

		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"

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

		private static final def NO_OF_PEOPLE = "//h5[contains(text(),'No of People in the Batch')]"

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
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def viewingCount = SearchPodPage.getTotalViewingPodsCount(browser,formData)
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
							browser.scrollToElement2(FILTER_SKILL)
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
		public static def getPodTextList = { browser, element ->
			def allResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(3000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.delay(3000)
					allResult = browser.getLists(POD_LIST)
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