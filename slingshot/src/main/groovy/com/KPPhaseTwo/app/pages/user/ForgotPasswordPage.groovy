package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage



final class ForgotPasswordPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new ForgotPasswordForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in ForgotPasswordPage")
		new ForgotPasswordForm().submit(browser, formData)
	}


	static final class ForgotPasswordForm extends WebForm {

		//Forgot Password Page form elements
		private static final def  EMAIL_CHECKBOX = ".//*[@for='email']"

		private static final def  EMAIL = ".//*[@id='input_email']"

		private static final def  MOBILE_CHECKBOX =	".//*[@for='mobile']"

		private static final def  MOBILE_NO =	".//*[@id='mobileno']"

		private static final def SUBMIT_MOB = ".//*[@id='forgotPasswordMobile']/div[2]/div/button"

		private static final def SUBMIT_EMAIL = ".//*[@id='forgotPasswordEmail']/div[2]/div/button"

		private static final def FIELDS = [MOBILE_CHECKBOX, MOBILE_NO, EMAIL_CHECKBOX, EMAIL]

		private static final def COPYRIGHT = ".//legal"

		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='userFP-span error_message']"

		private static final def FORM_ERROR1 = ".//*[@class='error_message']"

		private static final def SUCCESS_MESSAGE = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FORM_ERROR1, SUCCESS_MESSAGE]

		//error message map (Key-Value Pair)
		def static final forgotPwdPageErrorMessageMap = [emailId_req : "Email ID is required",
			invalid_mailId : "Enter a valid Email ID",
			unRegistered_mailId : "Entered email is not registered with any user. Please check the email",
			resertPwd_success : "Reset password link sent successfully",
			mobileNo_req : "Mobile Number is required",
			invalid_mobNo : "Enter a valid Mobile Number",
			unRegistered_mobileNo : "Entered mobile number is not registered with any user. Please check the mobile number"]		
		
		//To enter data
		def static final populateFields = { browser, formData ->
			println ("ForgorPasswordForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<=FIELDS.size()-1;i++){
					def tagName = browser.getTagName(FIELDS[i])
					WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
					browser.delay(500)
				}
				outcome = new SuccessOutcome()
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, SUBMIT_MOB, SUBMIT_EMAIL, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, forgotPwdPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton1, submitButton2, data, errFields ->
			if(browser.checkEnabled(submitButton1)){
				browser.click submitButton1 // submit the form.
				browser.delay(1500)
			} else if(browser.checkEnabled(submitButton2)){
				browser.click submitButton2 // submit the form.
				browser.delay(1500)
			} else {
				browser.click(COPYRIGHT)
				browser.delay(2000)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}


	}
}
