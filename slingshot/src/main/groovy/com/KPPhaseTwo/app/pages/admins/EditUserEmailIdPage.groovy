package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class EditUserEmailIdPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		new EditUserEmailIdPageForm().populateFields(browser, formData);
	}

	//Override
	def submit = { browser, formKey, formData ->
		new EditUserEmailIdPageForm().submit(browser, formData)
	}

	static final class EditUserEmailIdPageForm extends WebForm {

		//Login form elements
		private static final def  EMAIL = ".//input[@id='emailId']"
		
		private static final def  SUBMIT = ".//input[@value='Update & Resend']"

		private static final def FIELDS = [EMAIL]
		
		private static final def FORM_ERROR = ".//div[@type='success']//span[@class='ng-binding ng-scope']"
		
		private static final def FIELD_ERROR = "//span[@class='error_message'][@aria-hidden='false']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR,FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final editUserEmailErrorMessageMap = [
			Invalid_Email:"Enter a valid Email ID",
			Email_Exists :"Email ID already exists",
			Success_Message:"Acivation Link Sent On Updated EmailId Successfully"
		]

		/**
		 * @author aditya
		 * To populate the Edit Email form
		 */
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					def tagName = browser.getTagName(FIELDS[i])
					def emailToEnter = KPCommonPage.generateRandomEmailAddress(formData[i])
					WebForm.inputData(browser,FIELDS[i],tagName,emailToEnter)
					KPCommonPage.invitedUserEmailId = emailToEnter.trim()
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
			def actualValidationMsg = submitForm browser, FIELDS, SUBMIT, data, ERROR_MESSAGE_FIELDS
			println "The validation message is "+actualValidationMsg
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, editUserEmailErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		/**
		 * @author aditya
		 * To submit the Edit Email form
		 */
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.delay(1000)
			browser.click submitButton 
			browser.delay(8000)
			browser.getValidationMessages errFields
		}
	}
}
