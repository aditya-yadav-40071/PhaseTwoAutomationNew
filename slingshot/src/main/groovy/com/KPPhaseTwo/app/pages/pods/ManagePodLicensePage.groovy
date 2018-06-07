package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


/**
 * Created by Bishnu on 04/04/2018
 */

final class ManagePodLicensePage extends WebPage {

	def static correctPodContentsDisplayed = { browser, formData ->
		new ManagePodLicenseForm().correctPodContentsDisplayed browser, formData
	}

	def static correctMsgForAlreadyRequested = { browser, formData ->
		new ManagePodLicenseForm().correctMsgForAlreadyRequested browser, formData
	}

	def static correctRejectedPodLicense = { browser, formData ->
		new ManagePodLicenseForm().correctRejectedPodLicense browser, formData
	}

	def static correctApproveResult = { browser, formData ->
		new ManagePodLicenseForm().correctApproveResult browser, formData
	}

	def static ifAllPurchedPodsAreDisplayed = { browser, formData ->
		new ManagePodLicenseForm().ifAllPurchedPodsAreDisplayed browser, formData
	}

	def static searchForPod = { browser, formData ->
		new ManagePodLicenseForm().searchForPod browser, formData
	}

	def static clickOnThePodName = { browser, formData ->
		new ManagePodLicenseForm().clickOnThePodName browser, formData
	}

	def static clickOnAddSiteLicense =  { browser, formData ->
		new ManagePodLicenseForm().clickOnAddSiteLicense browser, formData
	}

	def static managePodLicenseSortedNewly = { browser, formData ->
		new ManagePodLicenseForm().managePodLicenseSortedNewly browser, formData
	}

	def static approvedTabIsHighlighted = { browser, formData ->
		new ManagePodLicenseForm().approvedTabIsHighlighted browser, formData
	}

	static final class ManagePodLicenseForm extends WebForm {

		//Manage Pod License page form elements as an Organization

		private static final def POD_LIST_NAMES = "//a[@ng-if='approvedLicense']"

		private static final def FIRST_POD = "//div[@class='clearfix content individual-pod ng-scope'][1]//a"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def FIRST_POD_NAME = "//div[@class='clearfix content individual-pod ng-scope'][1]//a"

		private static final def SORT_BY = "//div[contains(@ng-init,'listOrder')]/select"

		private static final def SEARCH_FIELD = ".//*[@id='main_page']/div[2]/div[2]/div[1]/div/input"//".//div[@class='col-xs-12 mb10 no-padding ml_15 mt_10 pr_25 visible-lg']//input[@placeholder='Search']"

		private static final def SEARCH_POD_GO = "//i[@class='fa fa-arrow-right blue']"

		private static final def NO_OF_LEARNERS = "//div[@class='clearfix content individual-pod ng-scope'][1]/div[1]//span[1]"

		private static final def REQUESTED_DATE = "//div[@class='clearfix content individual-pod ng-scope'][1]/div[1]//span[2]"

		private static final def AMOUNT = "//div[@class='clearfix content individual-pod ng-scope'][1]/div[1]//span[3]"

		private static final def POD_ALREADY_REQST_MSG = "//div[@class='clearfix content individual-pod ng-scope'][1]/span"

		private static final def ACT_POD_ALREADY_REQST_MSG = "pod is already requested by an organization."

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope disabled']"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"

		private static final def SITELICENSE_LINK = ".//span[@ng-if='approvedLicense']"

		private static final def SORTBY_FILTER =  ".//div[@class='pull-left col-md-12 col-xs-12 no-padding']/select"

		private static final def FIFTY_RESULTS = ".//label[@for='item5']"

		private static final def HIGHLIGHTED_TAB = ".//li[@class='col-xs-4  pull-left pointer selected']"


		def static final correctPodContentsDisplayed = {browser, formData->
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				browser.selectDropdownValue(SORT_BY,"Newest")
				browser.delay(3000)
				if(browser.isDisplayed(FIRST_POD_NAME) && browser.gettext(FIRST_POD_NAME).trim().equalsIgnoreCase(KPCommonPage.podNameForBulkLicense)){
					def noOfLearners = browser.gettext(NO_OF_LEARNERS).trim().split(":")[1].trim().toInteger()
					def requestedYear = browser.gettext(REQUESTED_DATE).trim().split(":")[1].trim().split("-")[0].toInteger()
					def requestedMonth = browser.gettext(REQUESTED_DATE).trim().split(":")[1].trim().split("-")[1].toInteger()
					def requestedDay = browser.gettext(REQUESTED_DATE).trim().split(":")[1].trim().split("-")[2].toInteger()
					def amountInPendingTab = browser.gettext(AMOUNT).trim().split(":")[1].trim().toFloat()
					int expReqDateInDays = browser.CurrentDate().split("/")[0].trim().toInteger()
					int expReqDateInMonths = browser.CurrentDate().split("/")[1].trim().toInteger()
					int expReqDateInYears = Integer.parseInt(browser.CurrentDate().split("/")[2].trim())
					if(noOfLearners==KPCommonPage.podLearnersInput.toInteger()
					&& expReqDateInDays==requestedDay && expReqDateInMonths==requestedMonth
					&& expReqDateInYears==requestedYear && amountInPendingTab==(KPCommonPage.podAmountInDetailsPage * KPCommonPage.podLearnersInput.toInteger())){
						println "Successfully Matched Pod Contents"
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodContentNotMatching", "Pod Contents Does Not Match.")
					}
				}
			}
		}


		def static final correctMsgForAlreadyRequested = {browser, formData ->
			if(browser.isDisplayed(POD_LIST_NAMES)){
				if(browser.isDisplayed(SEARCH_FIELD)){
					browser.populateField(SEARCH_FIELD,KPCommonPage.podNameForBulkLicense)
					browser.click SEARCH_POD_GO
					browser.delay(4000)
					if(browser.isDisplayed(FIRST_POD_NAME) &&
					browser.gettext(POD_ALREADY_REQST_MSG).trim().equalsIgnoreCase(ACT_POD_ALREADY_REQST_MSG)
					&& browser.gettext(POD_ALREADY_REQST_MSG,"ng-show").contains("true")){
						println "Correct Error Message Displayed for Already Requested "
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "AlreadyPodRequestMsgIssue", "Already Request Pod Message Does not appear.")
					}
				}
			}

		}


		def static final correctRejectedPodLicense = {browser, formData ->
			if(browser.isDisplayed(POD_LIST_NAMES)){
				browser.selectDropdownValue(SORT_BY,"Newest")
				browser.delay(4000)
				if(browser.isDisplayed(FIRST_POD_NAME) && browser.gettext(FIRST_POD_NAME).trim().equalsIgnoreCase(KPCommonPage.podNameForBulkLicense)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameDoesNotMatch", "Incorrect pod in Rejected List.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "RejectedPodListDisplayIssue", "Rejected Pod List Does not  Display.")
			}
		}

		def static final correctApproveResult = { browser, formData ->
			def matchingResult=false
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(1500)
				browser.selectDropdownValue(SORT_BY,"Newest")
				browser.delay(4000)
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
				browser.delay(500)
				for(int w=0;w<lastPage;w++){
					if(browser.isDisplayed(POD_LIST_NAMES)){
						def actualpodList = browser.getLists(POD_LIST_NAMES)
						println "---actualpodList-----"+actualpodList
						def expectedPodList = KPCommonPage.bulkPodList
						println "----expectedPodList---"+expectedPodList
						if(expectedPodList != null || !expectedPodList.isEmpty()){
							for(int k = 0; k < expectedPodList.size(); k++){
								for(int r = 0; r < actualpodList.size(); r++){
									if(expectedPodList[k].equalsIgnoreCase(actualpodList[r])){
										matchingResult = true
									}
								}
							}
						}
					}
					if(!browser.gettext(PAGINATION_NEXT,"class").contains("disabled")){
						browser.click PAGINATION_NEXT
						browser.delay(2000)
					}
				}
			}
			if(matchingResult){
				println "Successfully Correct Approved Pods List Displayed"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IncorrectPodsDisplayIssue", "Approved Pods list does not match with the input filter Location.")
			}
		}

		def static ifAllPurchedPodsAreDisplayed = { browser, formData ->
			browser.delay(1000)
			def result = false
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click(FIFTY_RESULT)
				browser.delay(1000)
				if(browser.isDisplayed(POD_LIST_NAMES)){
					def podNameList = browser.getLists(POD_LIST_NAMES).sort()
					KPCommonPage.allOngoingPodsList = KPCommonPage.allOngoingPodsList.sort()
					if(KPCommonPage.allOngoingPodsList.size() == podNameList.size()){
						for(int i=0;podNameList.size();i++){
							if(KPCommonPage.allOngoingPodsList[i].trim().equalsIgnoreCase(podNameList[i].trim())){
								result = true
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "podNameMimatch", "The ongoing pod name do not match")
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "podListSizeMismatch", "The Ongoing pods list size do not match")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "podListDisplayIssue", "The pod list was not displayed")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FifityResultTabDisplayIssue", "Fifty results tab was not displayed")
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ResultMismatch", "The actual pod name do not math with expected")
			}
		}

		def static searchForPod = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(SEARCH_FIELD)){
				browser.populateField(SEARCH_FIELD,formData[0])
				browser.click(SEARCH_POD_GO)
				browser.delay(1000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchFieldDisplayIssue", "The search text box was not displayed")
			}
		}

		def static clickOnThePodName = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(POD_LIST_NAMES)){
				def podName = browser.gettext(POD_LIST_NAMES).trim()
				if(podName.equalsIgnoreCase(formData[0].trim())){
					browser.click(POD_LIST_NAMES)
					browser.delay(1500)
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMismatchIssue", "The pod name do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayIssue", "The pod list was not displayed")
			}
		}

		def static clickOnAddSiteLicense = { browser, formData ->
			browser.delay(500)
			def result = false
			if(browser.isDisplayed(POD_LIST_NAMES)){
				def podNameList = browser.getLists(POD_LIST_NAMES)
				def siteLevelLicenseElement = browser.getListElements(SITELICENSE_LINK)
				for(int i=0;i<podNameList.size();i++){
					if(podNameList[i].trim().equalsIgnoreCase(formData[0].trim())){
						browser.clickElement(siteLevelLicenseElement[i])
						browser.delay(1500)
						result = true
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayIssue", "The pod list was not displayed")
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNameMismatchIssue", "The pod name do not match")
			}
		}

		def static managePodLicenseSortedNewly = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(FIFTY_RESULTS)){
				browser.click(FIFTY_RESULTS)
				browser.delay(1000)
				KPCommonPage.selectSortByValue(browser,SORTBY_FILTER,"Newest")
				def firstPodName = browser.gettext(FIRST_POD).trim()
				if(firstPodName.equalsIgnoreCase(formData[0].trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMismatchIssue", "The pod name do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FiftyResultsDisplayIssue", "The fifty result bar was not displayed")
			}
		}

		def static approvedTabIsHighlighted = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(HIGHLIGHTED_TAB) && browser.gettext(HIGHLIGHTED_TAB).trim().equalsIgnoreCase("Approved")){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "HighlightedTabIssue", "The tab was not displayed or approved tab is not highlighted")
			}
		}
	}
}
