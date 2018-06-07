package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu Das on 26/12/2017
 */

final class ApproveLicensePage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("reject")){
			new RejectLicenseForm().populateFields(browser, formData);
		}else{
			new ApproveLicenseForm().populateFields(browser, formData);
		}
	}

	//Override
	def submit = {browser, formKey, formData ->
		if(formKey.equals("reject")){
			new RejectLicenseForm().submit(browser, formData)
		}else{
			new ApproveLicenseForm().submit(browser, formData)
		}
	}



	def static licenseDetails = {browser, formData ->
		new ApproveLicenseForm().licenseDetails browser, formData
	}

	def static correctEmailIdDisplayed = {browser, formData ->
		new RejectLicenseForm().correctEmailIdDisplayed browser, formData
	}

	def static correctBulkPodApproveList = {browser, formData ->
		new ApproveLicenseForm().correctBulkPodApproveList browser, formData
	}





	static final class ApproveLicenseForm extends WebForm {

		//Approve License Form Elements

		private static final def NAME = "//div[label[normalize-space(text())='Name :']]/following-sibling::div"

		private static final def TYPE = "//div[label[normalize-space(text())='Type :']]/following-sibling::div"

		private static final def COURSE_NAME = "//div[label[normalize-space(text())='Course Name']]/../following-sibling::div[1]/div[1]"

		private static final def COURSES_IN_BULK = "//div[@ng-repeat='data in licensePod.courseData']/div[1]"

		private static final def END_DATE_RADIOBTN = "//input[@id='year']/following-sibling::label"

		private static final def ENDDATE_INPUT = "//input[@id='year']/following-sibling::div/input"

		private static final def REMARKS = "//textarea[@name='remarks']"

		private static final def APPROVE_BTN = "//button[text()='Approve']"

		private static final def FIELDS = [REMARKS, END_DATE_RADIOBTN, ENDDATE_INPUT]

		private static final def SUCCESS_MESSAGE = "//*[@id='main_page']/div[1]/div/span"

		private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]

		private static final def approveLicenseErrorMessageMap = [licenseApprove_Success:'License Created Successfully',
			licenseRejected_Success :'License Rejected Successfully',
			failedToApprove_Failure :'Failed to approve the license.']


		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(500)
					if(FIELDS[i].equals(ENDDATE_INPUT)){
						KPCommonPage.getEndDateInYears = formData[i]
					}
					browser.delay(500)
				}
				outcome = WebForm.enterData(browser, formData, FIELDS, APPROVE_BTN, WAIT_REQ_FIELDS)
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
			def actualValidationMsg = submitForm browser, FIELDS, APPROVE_BTN, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, approveLicenseErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, approveButton, data, errFields ->
			browser.click approveButton // submit the form.
			browser.delay(600)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}


		def static final licenseDetails = {browser, formData ->
			if(KPCommonPage.profileName!=null && KPCommonPage.profileType!=null){
				if(browser.isDisplayed(NAME) && browser.isDisplayed(TYPE) && browser.isDisplayed(COURSE_NAME)){
					def name = browser.gettext(NAME).trim()
					def userType = browser.gettext(TYPE).trim()
					def courseName = browser.gettext(COURSE_NAME).trim()
					if(KPCommonPage.profileName.trim().equalsIgnoreCase(name) && KPCommonPage.profileType.equalsIgnoreCase(userType)
					&& KPCommonPage.podToBuy_Entry.trim().equalsIgnoreCase(courseName)){
						println "User Name and Type is Verified"
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "UserTypeAndNameMismatchIssue", "Either UserName or User Type or Course Name does Not Match")
				}else
					return KPCommonPage.returnFailureOutcome(browser, "NameAndTypeDisplayIssue", "Either Name or Type value is not appearing.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "FirstNameOrTypeKPNullDisplay", "KPCommonPage User name or type is Null")

		}

		def static final correctBulkPodApproveList = {browser, formData ->
			if(browser.isDisplayed(COURSES_IN_BULK)){
				if(KPCommonPage.totalPodSelectedCount.toInteger()==browser.getLists(COURSES_IN_BULK).size()){
					def  flag = false
					def courseNameList = browser.getLists(COURSES_IN_BULK)
					for(int k = 0; k < courseNameList.size(); k++){
						for(int r = 0; r < KPCommonPage.bulkPodList.size(); r++){
							if(courseNameList[k].equalsIgnoreCase(KPCommonPage.bulkPodList[r])){
								flag = true
							}
						}
					}
					if(flag){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodListDoesNotMatch", "Pod List Does Not Match in Approve license page.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AllSelectedPodNotAppear", "All Selected Pods does not Appear in Approve license page.")
				}
			}

		}
	}

	static final class RejectLicenseForm extends WebForm {

		private static final def ACT_ADMIN_EMAIL = "//div[label[text()='Email : ']]/following-sibling::div"

		private static final def REMARKS = "//textarea[@name='remarks']"

		private static final def REJECT_BTN = "//button[text()='Reject']"

		private static final def FIELDS = [REMARKS]

		private static final def SUCCESS_MESSAGE = "//*[@id='main_page']/div[1]/div/span"

		private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]

		private static final def approveLicenseErrorMessageMap = [
			licenseApprove_Success:'License Created Successfully',
			licenseRejected_Success :'License Rejected Successfully',
			failedToApprove_Failure :'Failed to approve the license.']


		//To enter data
		def static final populateFields = { browser, formData ->
			println ("RejectLicenseForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, REJECT_BTN, WAIT_REQ_FIELDS)
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, REJECT_BTN, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, approveLicenseErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, approveButton, data, errFields ->
			browser.click approveButton // submit the form.
			browser.delay(600)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}




		def static final correctEmailIdDisplayed = { browser, formData ->
			if(browser.isDisplayed(ACT_ADMIN_EMAIL)){
				if(browser.gettext(ACT_ADMIN_EMAIL).trim().equals(KPCommonPage.adminEmailId)){
					println "Successfully Email ID matched"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "IncorrectUserIssue", "Incorret User Displayed, email id not matched")
				}

			}
		}

	}
}
