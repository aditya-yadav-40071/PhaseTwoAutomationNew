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

final class BulkLicensePage extends WebPage {

	def static searchForAPod = {browser, formData ->
		new BulkLicenseForm().searchForAPod browser, formData
	}
	
	def static bulkBuyOperation = {browser, formData ->
		new BulkLicenseForm().bulkBuyOperation browser, formData
	}
	
	
	
	static final class BulkLicenseForm extends WebForm {

		//Bulk License page form elements as an Organization
		
		private static final def POD_LIST_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
		
		private static final def FIRST_POD_NAME = "//div[@class='clearfix content individual-pod ng-scope'][1]//a"
		
		private static final def FIRST_POD_CHECKBOX = "//div[@class='clearfix content individual-pod ng-scope'][1]//md-checkbox" 
		
		private static final def SELECTALL_CHECKBOX = "//div[@class='clearfix content individual-pod ng-scope']//md-checkbox/div[1]"
		
		private static final def POD_ALREADY_REQ_MSG = "//div[@class='clearfix content individual-pod ng-scope'][1]/span"
		
		private static final def SEARCH_FILED = "//div[@class='list-container']/div[2]/div[1]//input"
		
		private static final def SEARCH_POD_GO = "//i[@class='fa fa-arrow-right blue']"
		
		private static final def POD_AMOUNT = "//div[h5[text()='Price']]/following-sibling::div//span"
		
		private static final def BREDCRUMB_BACK = ".//*[@id='breadcrumbox']/ol/li[3]/a"
		
		
		
		
		
		def static searchForAPod = {browser, formData ->
			if(browser.isDisplayed(POD_LIST_NAMES)){
				KPCommonPage.podNameForBulkLicense = formData[0].trim()
				    println "---podName--"+KPCommonPage.podNameForBulkLicense
					browser.populateField(SEARCH_FILED,KPCommonPage.podNameForBulkLicense)
					browser.click SEARCH_POD_GO
					browser.delay(4000)
					if(browser.isDisplayed(FIRST_POD_NAME) && browser.gettext(FIRST_POD_NAME).trim().equalsIgnoreCase(KPCommonPage.podNameForBulkLicense)){
						browser.click FIRST_POD_NAME
						browser.delay(3500)
						browser.scrollToElement2(POD_AMOUNT)
						KPCommonPage.podAmountInDetailsPage = browser.gettext(POD_AMOUNT).split(" ")[0].trim().toFloat()
						println "-----KPCommonPage.podAmountInDetailsPage----"+KPCommonPage.podAmountInDetailsPage
						browser.scrollToElement2(BREDCRUMB_BACK)
						browser.delay(1000)
						browser.click BREDCRUMB_BACK
						browser.delay(3000)
						browser.click FIRST_POD_CHECKBOX
						browser.delay(2000)
						return new SuccessOutcome()
						println "Successfully searched for input Pod." 
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodNotFoundOnSearchIssue", "Pod Not Found on search")
					}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayIssue", "Pod List Does Not Appear.")
			}
			
			
		}
		
		
		def static final bulkBuyOperation = {browser, formData ->
			
			if(browser.isDisplayed(POD_LIST_NAMES)){
				
				
				if(formData[0].contains(";")){
					def data = formData[0].split(";")
					KPCommonPage.totalPodSelectedCount = data.length
					for(int j = 0; j < data.length; j++){
						KPCommonPage.bulkPodList.add(data[j])
						browser.populateField(SEARCH_FILED, data[j])
						browser.delay(2000)
						browser.click SEARCH_POD_GO
						browser.delay(4000)
						BulkLicenseForm.selectTheInputPods(browser,data[j])
					}
				}else{
					KPCommonPage.bulkPodList.add(formData[0])
					browser.populateField(SEARCH_FILED,formData[0])
					browser.delay(1000)
					browser.click SEARCH_POD_GO
					browser.delay(4000)
					BulkLicenseForm.selectTheInputPods(browser,formData[0])
				}
				println "Successfully Selected the pods"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayIssue", "Pod List Does Not Appear.")
			}
			
		}
		
		
		def static final selectTheInputPods(def browser,def podName){
			if(browser.isDisplayed(FIRST_POD_NAME)){
				if(browser.isDisplayed(FIRST_POD_NAME) && browser.gettext(FIRST_POD_NAME).equalsIgnoreCase(podName.toString().trim())){
					browser.click FIRST_POD_CHECKBOX
					browser.delay(1000)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "InputPodNotFound", "Input Pod Not found in top of the list.")
				}
				
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNotFoundOnSearchIssue", "Pod Not Found on search")
			}
		} 
		
		
		
		
		
		
		
	
			
	}
	
	
	
}
