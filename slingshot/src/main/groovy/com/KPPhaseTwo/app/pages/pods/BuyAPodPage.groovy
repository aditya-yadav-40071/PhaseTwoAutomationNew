package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 20/12/2017
 */

final class BuyAPodPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("asAdmin")){
			new BuyAPodOrgForm().populateFields(browser, formData);
		}else if(formKey.equals("asUser")){
			new BuyAPodUserForm().populateFields(browser, formData);
		}
	}
	
	//Override
	def submit = {browser, formKey, formData ->
		if(formKey.equals("asAdmin")){
			println ("Submit method as an admin in Buy a pod page")
			new BuyAPodOrgForm().submit(browser, formData)
		}else if(formKey.equals("asUser")){
			println ("Submit method as an user in Buy a pod page")
			new BuyAPodUserForm().submit(browser, formData)
		}
	}
	
	//To verify the amount of the pod from pod details page
	def static equalAmountStatus = {browser, formData ->
		new BuyAPodOrgForm().equalAmountStatus browser, formData
	}
	
	//To verify the number of users
	def static noOfUsersEntered = {browser, formData ->
		new BuyAPodOrgForm().noOfUsersEntered browser, formData
	}
	
	//To verify the number of users
	def static noOfUsersDisplayedAsUser = {browser, formData ->
		new BuyAPodUserForm().noOfUsersDisplayedAsUser browser, formData
	}
	
	def static getAdminFirstName = {browser, formData ->
		new BuyAPodOrgForm().getAdminFirstName browser, formData
	}
	
	static final class BuyAPodOrgForm extends WebForm {

		//Buy a pod page form elements as an Organization
		private static final def POD_LIST_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
		
		private static final def ADMIN_FIRST_NAME = "//h3[text()='Admin']/following-sibling::div[1]//input"
		
		private static final def ADMIN_EMAILID = "//div[h3[text()='Admin']]//input[@id='emailId']"
		
		private static final def PODWORK_LOGO = "//div[@class='no-padding']/img"
		
		private static final def NO_OF_USERS = "//input[@type='number']"
		
		private static final def USER_REMARKS = "//textarea[@id=' remarks']"
		
		private static final def PURCHASE_REQUEST = "//input[@value='Purchase Request']"
		
		private static final def SINGLE_AMOUNT = "//div[span[contains(text(),'Cost for Single User')]]/following-sibling::div/span"
		
		private static final def USER_TOTAL_EXP = "//div[@class='row']/div[2]/span"
		
		private static final def FIELDS = [NO_OF_USERS,USER_REMARKS]
		
		private static final def SUCCESS_MESSAGE = ".//div[@ng-repeat='alert in alerts']//span[@class='ng-binding ng-scope']"
		
		private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]
		
		private static final def buyAPodErrorMessageMap = [buyPodRequest_Success:'Successfully submitted the request, Soon you will get purchase details to your mail',
														   invalidNo_Failure: 'Enter Valid Number of Learner']
		

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
				outcome = browser.populateField(FIELDS[i], formData[i])
				println "Entered"
				}
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
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(900)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		//To verify the amount of the pod from pod details page
		def static final equalAmountStatus = { browser, formData ->
			def amountOnBuyPage,numberOfUser
			amountOnBuyPage = (browser.gettext(SINGLE_AMOUNT).substring(1)).split("x")[0].trim().toFloat()
			//numberOfUser = (browser.gettext(SINGLE_AMOUNT).substring(1)).split("x")[1].trim().toInteger()
			if(amountOnBuyPage == KPCommonPage.pod_amount){
				println "Amount is Verified Successfully."
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "amountNotEqual", "Amount displayed not equal.")
		}
		
		//To verify the number of users entered for a pod
		def static final noOfUsersEntered = { browser, formData ->
			def amountCalc = browser.gettext(USER_TOTAL_EXP)
			println amountCalc
			def numberOfUsers = (browser.gettext(SINGLE_AMOUNT).substring(1)).split("x")[1].trim().toInteger()
			def numberEntered = Integer.parseInt(formData[0])
			if(numberOfUsers == numberEntered){
				println "Success Same No. Users Displayed"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "mismatchInNo", "No of users entered not similar to the calculation.")
		}
		
		
		def static final getAdminFirstName = {browser, formData ->
			browser.scrollToElement2(ADMIN_FIRST_NAME)
			if(browser.isDisplayed(ADMIN_FIRST_NAME)){
				//def firstName = browser.getTextAt(ADMIN_FIRST_NAME,"fname")
				def firstName = browser.gettext(ADMIN_FIRST_NAME,"value")
				browser.scrollToElement2(ADMIN_EMAILID)
				def adminEmailiD = browser.gettext(ADMIN_EMAILID,"value")
				browser.delay(700)
				browser.scrollToElement2(PODWORK_LOGO)
				println "====firstName===="+firstName
					if(firstName!=null){
						KPCommonPage.profileName = firstName
						KPCommonPage.profileType = "ADMIN"
						KPCommonPage.adminEmailId= adminEmailiD
						return new SuccessOutcome() 
					}else
						return KPCommonPage.returnFailureOutcome(browser, "AdminFirstNameIsNULLIssue", "Admin First Name does not appear.")
			}
		}
	}
	
	
	static final class BuyAPodUserForm extends WebForm {
		
				//Buy a pod page form elements as a user
				private static final def POD_LIST_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
				
				private static final def NO_OF_USERS = "//input[@type='number']"
				
				private static final def USER_REMARKS = "//textarea[@id=' remarks']"
				
				private static final def PURCHASE_REQUEST = "//input[@value='Purchase Request']"
				
				private static final def SINGLE_AMOUNT = "//div[span[contains(text(),'Cost for Single User')]]/following-sibling::div/span"
				
				private static final def USER_TOTAL_EXP = "//div[@class='row']/div[2]/span"
				
				private static final def FIELDS = [USER_REMARKS]
				
				private static final def SUCCESS_MESSAGE = ".//*[@id='main_page']/div[1]/div/span"
				
				private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]
				
				private static final def buyAPodErrorMessageMap = [buyPodRequest_Success:'Successfully submitted the request, Soon you will get purchase details to your mail',
																   invalidNo_Failure: 'Enter Valid Number of Learner']
				
		
				//To enter data
				def static final populateFields = { browser, formData ->
					def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
					if(outcome.isSuccess()){
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
				
				//To verify the amount of the pod from pod details page
				def static final equalAmountStatus = {browser, formData ->
					def amountOnBuyPage,numberOfUser
					amountOnBuyPage = (browser.gettext(SINGLE_AMOUNT).substring(1)).split("x")[0].trim().toFloat()
					if(amountOnBuyPage == KPCommonPage.pod_amount){
						println "Amount is Verified Successfully."
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "amountNotEqual", "Amount displayed not equal.")
				}
				
				//To verify the number of users entered for a pod is always 1.
				def static final noOfUsersDisplayedAsUser = { browser, formData ->
					def amountCalc = browser.gettext(USER_TOTAL_EXP)
					println amountCalc
					def numberOfUsers = (browser.gettext(SINGLE_AMOUNT).substring(1)).split("x")[1].trim().toInteger()
					if(numberOfUsers == 1){
						println "Success Same No. Users Displayed"
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "MismatchInNo", "No of users entered not similar to the calculation.")
				}
				
			}
	
	
	
}
