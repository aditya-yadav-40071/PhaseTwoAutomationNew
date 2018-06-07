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

final class BulkLicensePaymentPage extends WebPage {

	//Override
	def populateData = {browser,formKey, formData ->
		new BulkLicensePaymentForm().populateFields(browser, formData)
	}
	
	//Override
	def submit = {browser,formKey, formData ->
		new BulkLicensePaymentForm().submit(browser, formData)
	}
	
	def static correctPodListInPayement = {browser, formData ->
		new BulkLicensePaymentForm().correctPodListInPayement browser, formData
	}
	
	
	
	static final class BulkLicensePaymentForm extends WebForm {

		//Bulk License Payment page form elements as an Organization
		
		private static final def COURSES_NAME_LIST = "//h5[text()='Course and Amount details']/following-sibling::div//span[@class='pull-left smallText ng-binding']"
		
		private static final def NUM_OF_LEARNERS = "//h5[text()='Course and Amount details']/following-sibling::div//input"

		private static final def POD_LEARNERS = "//input[@ng-model='learnerCount']"
		
		private static final def USER_REMARKS = "//textarea[@id=' remarks']"
		
		private static final def PURCHASE_REQUEST = "//input[@value='Purchase Request']"
		
		private static final def SINGLE_AMOUNT = "//div[span[contains(text(),'Cost for Single User')]]/following-sibling::div/span"
		
		private static final def TOTAL_AMOUNT = "//div[span[text()='Total']]/following-sibling::div[1]/span"
		
		private static final def USER_TOTAL_EXP = "//div[@class='row']/div[2]/span"
		
		private static final def FIELDS = [POD_LEARNERS,USER_REMARKS]
		
		private static final def SUCCESS_MESSAGE = ".//*[@id='main_page']/div[1]/div/span"
		
		private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]
		
		private static final def buyAPodErrorMessageMap = [buyPodRequest_Success : "Successfully submitted the request, Soon you will get purchase details to your mail",
														   invalidNo_Failure : "Enter Valid Number of Learner"]
		

		//To enter data
		def static final populateFields = { browser, formData ->
		println ("BulkLicensePaymentForm.populateFields - data: " +formData)
			browser.delay(2000)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
					if(FIELDS[i].equals(POD_LEARNERS)){
						println "Hello"
						KPCommonPage.podLearnersInput = formData[i]
						
						
					}
				}
				outcome = WebForm.enterData(browser, formData, FIELDS, PURCHASE_REQUEST, WAIT_REQ_FIELDS)
				println "Entered"
			}
			return outcome
		}
		
		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, PURCHASE_REQUEST, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, buyAPodErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(500)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		
		
		def static final correctPodListInPayement = {browser, formData ->
			if(browser.isDisplayed(COURSES_NAME_LIST)){
				if(KPCommonPage.totalPodSelectedCount.toInteger()==browser.getLists(COURSES_NAME_LIST).size()){
					def  flag = false
					def courseNameList = browser.getLists(COURSES_NAME_LIST)
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
		
		

		
		
		
	}
	
	

	
}
