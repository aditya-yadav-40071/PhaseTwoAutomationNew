package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 31/01/2018
 */

final class PodProgressDetailsPage extends WebPage {

	//overriden methods
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("sortBy")){
			new PodProgressDetailsForm().populateFields(browser, formData);
		}
	}


	//To check Ongoing Pods count is not Zero
	def static ongoingPodsCountIsNotZero ={browser, formData ->
		new PodProgressDetailsForm().ongoingPodsCountIsNotZero browser, formData
	}

	def static correctPodsCountDisplayed = {browser, formData ->
		new PodProgressDetailsForm().correctPodsCountDisplayed browser, formData
	}

	def static getPodPercentage = {browser, formData ->
		new PodProgressDetailsForm().getPodPercentage browser, formData
	}

	def static getFirstPodWithPercentage = {browser, formData ->
		new PodProgressDetailsForm().getFirstPodWithPercentage browser, formData
	}

	def static correctPercentageDisplayed = {browser, formData ->
		new PodProgressDetailsForm().correctPercentageDisplayed browser, formData
	}

	def static correctSortByList  = {browser, formData ->
		new PodProgressDetailsForm().correctSortByList browser, formData
	}

	def static getPageCount = {browser, formData ->
		new PodProgressDetailsForm().getPageCount browser, formData
	}
	
	def static isSortedByName = {browser, formData ->
		new PodProgressDetailsForm().isSortedByName browser, formData
	}
	
	def static isSortedByDateOfJoining = {browser, formData ->
		new PodProgressDetailsForm().isSortedByDateOfJoining browser, formData
	}



	static final class PodProgressDetailsForm extends WebForm {

		//Pod Progress details form elements
		private static final def POD_LIST = "//a[@class='content-name job-title pointer title-name ng-binding']"

		private static final def ONGOING_COUNT = "//div[@id='dashboard']/div/div/div[5]/div/div[1]/div/div/div[1]/div[1]/div[2]/dashboard-list-widget/div//span[@ng-if='showOngoingPods']"

		private static final def VIEWCOUNT = "//h4[contains(text(),'You have viewing')]"

		private static final def NO_OTHER_ONGOING = "//div[@class='row mt_20' and @aria-hidden='false']"

		private static final def POD_PERCENT_SVG = ".//*[@id='gauge']//*[name()='text'][2]/*[name()='tspan']"

		private static final def POD_NAME_SVG = "//p[@class='ng-binding']"

		private static final def FIRST_PERCENT_INLIST = "//div[@ng-repeat='pods in userCourseList'][1]/div[3]//div[b]"

		private static final def FIRST_POD_INLIST = "//div[@ng-repeat='pods in userCourseList'][1]/div[1]//a"

		private static final def SORTBY = "//select[contains(@ng-change,'changeSortBy')]"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"

		private static final def PAGINATION_NEXT = "//li[contains(@class,'pagination-next ng-scope')]"

		private static final def BREDCRUMB_BACK = ".//*[@id='breadcrumbox']/ol/li[3]/a"

		private static final def EXP_ERR_MSG = "No Other Ongoing / Completed Pod List Enrolled By User"

		private static final def FIELDS = [SORTBY]


		//To enter data
		def static final populateFields = { browser, formData ->
			println ("PodProgressDetailsForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SORTBY)){
						if(!formData[i].equals("")){
							def sortByInput
							browser.click SORTBY
							browser.delay(700)
							sortByInput = formData[i]
							browser.selectDropdownValue(SORTBY,formData[i])
							browser.delay(800)
							KPCommonPage.podProgressSortBy = sortByInput
						}
					}
					browser.delay(1000)
				}
			}
			return outcome
		}


		//to get the last page number
		def static final getPageCount(def browser, def formData){
			println "-------PAGE COUNT---------"
			int lastPage=1
			if(!browser.gettext(PAGINATION_NEXT,"class").contains("disabled")){
				browser.delay(1000)
				def pageText = browser.gettext(LAST_PAGE_NO)
				browser.delay(1000)
				lastPage = pageText.toInteger()
				println "...lastPage No is......"+lastPage
			}else{
				browser.delay(1000)
				lastPage
			}
		}


		def static final ongoingPodsCountIsNotZero = { browser, formData ->
			def flag = false
			if(browser.isDisplayed(ONGOING_COUNT)){
				def text = browser.gettext(ONGOING_COUNT).trim()
				KPCommonPage.ongoingCount = Integer.parseInt(text.substring(text.indexOf('(')+1,text.lastIndexOf(')')).trim())
				if(KPCommonPage.ongoingCount!=0 && !(KPCommonPage.ongoingCount<0)){
					flag = true
				}
				if(flag){
					println "Successfully Ongoing Pods are Available"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "OngoingPodCountZeroIssue", "Ongoing Pod List is Zero.")
				}

			}
		}


		def static final correctPodsCountDisplayed = {browser, formData ->
			def flag = false
			if(browser.isDisplayed(VIEWCOUNT)){
				if(KPCommonPage.ongoingCount == 1){
					browser.scrollToElement2(NO_OTHER_ONGOING)
					if(browser.isDisplayed(NO_OTHER_ONGOING)){
						if(EXP_ERR_MSG.equals(NO_OTHER_ONGOING)){
							flag = true
						}
					}
				}else{
					def totalPodCount = (browser.gettext(VIEWCOUNT).trim().split("of")[1].trim().split(" ")[0].trim().toInteger())+1
					println "=====totalPodCount====="+totalPodCount
					if(totalPodCount == KPCommonPage.ongoingCount){
						flag = true
					}
				}
			}
			if(flag){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "OngoingPodCountZeroOrErrorMsgIssue", "Ongoing Pod List is Zero Or Error Msg Issue.")

		}


		def static final getPodPercentage = {browser, formData ->
			def podPercent = null
			browser.delay(2000)
			if(browser.gettext(POD_PERCENT_SVG).trim().length()>0){
				KPCommonPage.firstPodNameInGraph = browser.gettext(POD_NAME_SVG).trim()
				KPCommonPage.podPercentInGraph = browser.gettext(POD_PERCENT_SVG).trim()
				return new SuccessOutcome()
			}
		}


		def static final getFirstPodWithPercentage = {browser, formData ->
			browser.delay(1500)
			browser.scrollToElement2(FIRST_POD_INLIST)
			def podNameWithPercent
			if(browser.isDisplayed(FIRST_PERCENT_INLIST) && browser.isDisplayed(FIRST_POD_INLIST)){
				KPCommonPage.firstPodPercent = browser.gettext(FIRST_PERCENT_INLIST).trim().split(":")[1].trim()
				KPCommonPage.firstPodNameInList = browser.gettext(FIRST_POD_INLIST).trim()
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "FirstPodNameAndPercentIssue", "First Pod name or percent is not Displayed")
		}



		def static final correctPercentageDisplayed = {browser, formData ->
			browser.scrollToElement2(POD_PERCENT_SVG)
			browser.delay(2000)
			if(browser.gettext(POD_PERCENT_SVG).trim().length()>0){
				KPCommonPage.firstPodNameInGraph = browser.gettext(POD_NAME_SVG).trim()
				KPCommonPage.podPercentInGraph = browser.gettext(POD_PERCENT_SVG).trim()
				if((KPCommonPage.firstPodPercent).toInteger()==KPCommonPage.podPercentInGraph.toInteger() &&
				KPCommonPage.firstPodNameInList.equalsIgnoreCase(KPCommonPage.firstPodNameInGraph)){
					println "Correct Pod Displayed."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "GraphValuesAreNotDynamicIssue", "Progress graph value is not the same as the one clicked.")
			}

		}



		def static final correctSortByList = {browser, formData ->
			
			def sortBy = KPCommonPage.podProgressSortBy
			browser.delay(1000)
			if(sortBy.equalsIgnoreCase("Sort by Name")){
				def sortByName = PodProgressDetailsPage.isSortedByName(browser,formData)
				println ""
				if(sortByName){
					println "Sort By Name is Successfully Working"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "SortByNameIssue", "Sort By Name is not Working")

			}else if(sortBy.equalsIgnoreCase("Sort by Date Of Joining")){
				def result = PodProgressDetailsPage.isSortedByDateOfJoining(browser,formData)
				if(result){
					println "Sort by Date Of Joining is Successfully Working"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortByNoOfPeopleIssue", "Sort By Number of people is not Working.")
				}
			}
		}


		
		def static final isSortedByName(def browser, def formData){
			def flag
			browser.scrollToElement2(FIFTY_RESULT)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = PodProgressDetailsPage.getPageCount(browser, formData)
				for(int i=0;i<lastPage;i++){
					def listDataPerPage = browser.getLists(POD_LIST)
					println "=====KPCommonPage.isSorted(listDataPerPage)===="+KPCommonPage.isSorted(listDataPerPage)
					if(KPCommonPage.isSorted(listDataPerPage)){
						flag = true
					}else{
						flag = false
						break
					}
					if(!browser.gettext(PAGINATION_NEXT,"class").contains("disabled")){
						browser.click PAGINATION_NEXT
						browser.delay(2000)
					}
				}
			}
			return flag
		}

		def static final isSortedByDateOfJoining(def browser, def formData){
			def result
			browser.scrollToElement2(FIFTY_RESULT)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = PodProgressDetailsPage.getPageCount(browser, formData)
				def podName = KPCommonPage.podName 
				if(lastPage==1){
					def listDataPerPage = browser.getLists(POD_LIST)
					def listDataElements = browser.getListElements(POD_LIST)
					println "====listDataPerPage[(listDataPerPage.size())-1]==="+listDataPerPage[(listDataPerPage.size())-1]
					browser.scrollToElement2(listDataElements[(listDataElements.size())-1])
					if(listDataPerPage[(listDataPerPage.size())-1].equalsIgnoreCase(podName)){
						result = true
					}else{
						result = false
					}
				}else{
					browser.click(LAST_PAGE_NO)
					browser.delay(2000)
					def listDataPerPage = browser.getLists(POD_LIST)
					if(listDataPerPage[(listDataPerPage.size())-1].equalsIgnoreCase(podName)){
						result = true
					}else{
						result = false
					}
				}
			}
			return result
		}























	}
}
