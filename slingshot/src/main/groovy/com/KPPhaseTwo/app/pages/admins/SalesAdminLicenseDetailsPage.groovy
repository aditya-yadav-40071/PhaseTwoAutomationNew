package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu Das on 26/12/2018
 */

final class SalesAdminLicenseDetailsPage extends WebPage {

	

	def static pendingTabSelected = {browser, formData ->
		new SalesAdminLicenseDetailsForm().pendingTabSelected browser, formData
	}

	def static podRequestsDisplayed = {browser, formData ->
		new SalesAdminLicenseDetailsForm().podRequestsDisplayed browser, formData
	}

	def static checkTypeAndName = {browser, formData ->
		new SalesAdminLicenseDetailsForm().checkTypeAndName browser, formData
	}

	def static verifyUserTypeAndName = {browser, element, formData ->
		new SalesAdminLicenseDetailsForm().verifyUserTypeAndName browser,element,formData
	}


	def static clickPodReview = {browser, formData ->
		new SalesAdminLicenseDetailsForm().clickPodReview browser, formData
	}

	def static correctBulkPodRequestsDisplayed = {browser, formData ->
		new SalesAdminLicenseDetailsForm().correctBulkPodRequestsDisplayed browser,formData
	}

	def static podsRejectedBySalesAdmin = {browser, formData ->
		new SalesAdminLicenseDetailsForm().podsRejectedBySalesAdmin browser, formData
	}








	static final class SalesAdminLicenseDetailsForm extends WebForm {

		//Sales Admin License Details Form Elements
		private static final def POD_REVIEW_TO_CLICK = "//tr[td[1][text()='Krithika'] and td[2][text()='ADMIN'] and td[3][text()='Assistant Elevator Installer']]/td/a"

		private static final def PENDING_TAB = "//li[a[text()='Pending']]"

		private static final def POD_REQUESTS_LIST = "//li[@class='ng-binding']"

		private static final def END_DATE_YEARS = "//input[@id='year']/following-sibling::label"

		private static final def ENDDATE_INPUT = "//input[@id='year']/following-sibling::div/input"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"

		private static final def BULK_POD_LIST = "//form/div/table/tbody/tr[2]//table//li"

		private static final def BULK_POD_LEARNERS_LIST = "//form/div/table/tbody/tr[2]//table//ul[@class='pl_0 ng-binding']"

		private static final def FIRST_NAME = "//form/div/table/tbody/tr[2]/td[1]"

		private static final def FIRST_TYPE = "//form/div/table/tbody/tr[2]/td[2]"

		private static final def SEARCH_FILED = "//div[@class='icon-addon addon-md visible-lg']/input"

		private static final def SEARCH_POD_GO = "//i[@class='fa fa-arrow-right blue']"

		private static final def FIRST_REVIEW_LINK = "//form/div/table/tbody/tr[2]//a"



		//private static final def PENDING_TAB_STATUS = ""

		//To perform action of clicking on the pod for which the pending request is accepted/rejected
		def static final clickPodReview = {browser, formData ->

			/*println KPCommonPage.podToBuy_Entry
			 println KPCommonPage.typeOfUser
			 println KPCommonPage.firstNameAdmin*/
		}


		def static final pendingTabSelected = {browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(PENDING_TAB)){
				browser.click PENDING_TAB
				browser.delay(1000)
				if(browser.gettext(PENDING_TAB,"class").contains("selected")){
					println "Successfully Pending Tab is Selected"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PendingTabNotSelected", "Pending Tab is not selected.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PendingTabDisplayIssue", "Pending Tab is not Appearing")
			}

		}


		def static final podRequestsDisplayed = { browser, formData ->
			browser.delay(4500)
			if(browser.isDisplayed(POD_REQUESTS_LIST)){
				println "Pod Requests Dispalyed"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "PendingRequestsDisplayIssue", "Pending Pod Requests does not Display")
		}



		def static final checkTypeAndName = {browser, formData ->
			def flag
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
				def listDataPerPage = browser.getLists(POD_REQUESTS_LIST)
				def listElementPerPage = browser.getListElements(POD_REQUESTS_LIST)
				for(int j=0;j<listDataPerPage.size();j++){
					if(listDataPerPage[j].trim().equalsIgnoreCase(KPCommonPage.podName)){
						flag = true
						SalesAdminLicenseDetailsPage.verifyUserTypeAndName browser,listDataPerPage[j], formData
						browser.clickElement listElementPerPage[j]
						browser.delay(3000)
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



		def static final verifyUserTypeAndName = { browser,element,formData ->
			
			String podName = "//li[normalize-space(text())='"+element+"']"
			def expUserType = browser.gettext(podName+"/./../../../../../../preceding-sibling::td[1]").trim()
			def expProfileName = browser.gettext(podName+"/./../../../../../../preceding-sibling::td[2]").trim()
			def firstName = KPCommonPage.profileName
			if(firstName!=null && KPCommonPage.profileType!=null){
				if(firstName.trim().equalsIgnoreCase(expProfileName) && KPCommonPage.profileType.equalsIgnoreCase(expUserType)){
					println "User Name and Type is Verified"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "UserTypeAndNameMismatchIssue", "Either UserName or User Type does Not Match")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "FirstNameOrTypeKPNullDisplay", "KPCommonPage User name or type is Null")
		}



		def static final correctBulkPodRequestsDisplayed = {browser, formData ->
			if(browser.isDisplayed(POD_REQUESTS_LIST)){
				if(KPCommonPage.totalPodSelectedCount.toInteger()==browser.getLists(BULK_POD_LIST).size()){
					def  flag = false
					def courseNameList = browser.getLists(BULK_POD_LIST)
					for(int k = 0; k < courseNameList.size(); k++){
						for(int r = 0; r < KPCommonPage.bulkPodList.size(); r++){
							if(courseNameList[k].equalsIgnoreCase(KPCommonPage.bulkPodList[r])){
								flag = true
							}
						}
					}
					if(flag){
						println "Successfully compared the Pod List"
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodListDoesNotMatch", "Pod List Does Not Match in Payement page.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AllSelectedPodNotAppear", "All Selected Pods does not Appear in Payment Page.")
				}
			}
			
		}


		def static final podsRejectedBySalesAdmin = {browser, formData ->
			browser.delay(4000)
			if(browser.isDisplayed(POD_REQUESTS_LIST)){
				println "---podName--"+KPCommonPage.podNameForBulkLicense
				browser.populateField(SEARCH_FILED,KPCommonPage.podNameForBulkLicense)
				browser.delay(6000)
				browser.click SEARCH_POD_GO
				browser.delay(3000)
				if(browser.isDisplayed(FIRST_NAME) && browser.isDisplayed(FIRST_TYPE)){
					if(browser.gettext(FIRST_NAME).trim().equalsIgnoreCase(KPCommonPage.profileName)
					&& browser.gettext(FIRST_TYPE).trim().equalsIgnoreCase(KPCommonPage.profileType)
					&& browser.gettext(BULK_POD_LIST).trim().equalsIgnoreCase(KPCommonPage.podNameForBulkLicense)
					&& browser.gettext(BULK_POD_LEARNERS_LIST).trim().toInteger()==KPCommonPage.podLearnersInput.toInteger()){
						browser.click FIRST_REVIEW_LINK
						browser.delay(4000)
						println "Successfully Clicked on Review Link"
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodNameOrTypeNotMatchIssue", "PodName or Type or count Not proper.")
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayIssue", "Pod List Does Not Display")
			}
		}
	}
}
