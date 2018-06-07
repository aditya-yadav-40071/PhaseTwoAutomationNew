package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class InviteUserPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		new InviteUserForm().populateFields(browser, formData);
	}

	//Override
	def submit = { browser, formKey, formData ->
		new InviteUserForm().submit(browser, formData)
	}

	/**
	 * Before creating the user the status is maked as true 
	 * The status is used in populated data 
	 */
	def static changeUserCreateStatus = { browser, formData ->
		new InviteUserForm().changeUserCreateStatus(browser, formData)
	}

	static final class InviteUserForm extends WebForm {

		//Login form elements
		private static final def  FIRST_NAME = ".//input[@name='fname']"

		private static final def  MIDDLE_NAME = ".//*[@name='mname']"

		private static final def  LAST_NAME = ".//*[@name='lname']"

		private static final def  EMAIL = ".//*[@id='subAdminMailId']"

		private static final def  SEND_INVITATION = ".//md-checkbox[@role='checkbox']"

		private static final def  PASSWORD = ".//*[@id='subAdminPassword']"

		private static final def  CONFIRM_PASSWORD = ".//*[@id='confirmPassword']"

		private static final def  SELECT_SITE = ".//select[@placeholder='Select Site']"

		private static final def  INVITE_BUTTON = ".//button[contains(text(),'INVITE')]"

		private static final def INVITE_ANOTHER_USER = ".//button[contains(text(),'Invite another User')]"

		private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL, SEND_INVITATION, PASSWORD, CONFIRM_PASSWORD, SELECT_SITE]

		// the error fields.
		private static final def FIELD_ERROR = ".//span[@class='error_message']"

		private static final def FORM_ERROR = ".//div[@role='alert']//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS = [FIELD_ERROR, FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final inviteUserPageErrorMessageMap = [
			fname_req              :'First Name is required.',
			email_req              :'Email ID is required.',
			invalid_email          :'Enter a valid Email ID',
			email_already_exist    :'Email ID already exists',
			pass_req               :'Password is required',
			confirm_pass_req       :'Re-enter Password is required',
			pass_combination_error :'Password should be a combination of minimum 8 characters containing a capital letter,special character and a digit ',
			pass_missmatch         :'Password does not match',
			enter_corr_pass        :'Enter correct password',
			allFieldsReq           :'All the fields are mandatory',
			enrollment_success     :'Learner Enrolled to Pod Successfully',
			learnerAdd_success     :'User added Successfully'
		]

		/**
		 * To populate the invite user form
		 * KPCommonPage.invitedUserEmailIdDefault is used to save the email of an invited user and 
		 * can be used to login as an enrolled user after using changeUserCreateStatus method
		 */
		def static final populateFields = { browser, formData ->
			def emailToEnter
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				browser.delay(2000)
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
				for(int i=0;i<=FIELDS.size()-1;i++){
					def tagName = browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(FIRST_NAME)){
						def data = KPCommonPage.generateRandomString()
						KPCommonPage.invitedUserFirstName = formData[i]+""+data
						WebForm.inputData(browser, FIELDS[i], tagName,  KPCommonPage.invitedUserFirstName)
					}else if(FIELDS[i].equals(MIDDLE_NAME)){
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						KPCommonPage.invitedUserMiddleName = formData[i].trim()
					}else if(FIELDS[i].equals(LAST_NAME)){
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						KPCommonPage.invitedUserLastName = formData[i].trim()
					}else if(FIELDS[i].equals(EMAIL)){
						if(formData[i].contains("@")){
							emailToEnter = KPCommonPage.generateRandomEmailAddress(formData[i])
							WebForm.inputData(browser, FIELDS[i], tagName,  emailToEnter)
							KPCommonPage.invitedUserEmailId = emailToEnter.trim()
							if(KPCommonPage.userCreateSuccess){
								KPCommonPage.invitedUserEmailIdDefault = emailToEnter.trim()
								KPCommonPage.userCreateSuccess = false
							}
						} else {
							WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						}
					} else if(FIELDS[i].equals(PASSWORD)){
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						KPCommonPage.invitedUserPassword = formData[i]
					}else if(FIELDS[i].equals(SELECT_SITE)){
						browser.delay(1000)
						if(KPCommonPage.siteCreateStatus){
							formData[i] = KPCommonPage.addedSiteName
							KPCommonPage.siteCreateStatus = false
						}else{
							formData[i] = "Default Online Site"
						}
						KPCommonPage.invitedUserSiteName = formData[i].trim()
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
					}else{
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
					}
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
			def actualValidationMsg = submitForm browser, FIELDS, INVITE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, inviteUserPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.delay(2000)
				KPCommonPage.inviteUserFlag = true
				browser.click submitButton
				browser.delay(500)
			} else {
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		/**
		 * @author Aditya
		 * Before creating the user the status is maked as true 
		 * The status is used in populated data 
		 */
		def static changeUserCreateStatus = { browser, formData ->
			KPCommonPage.userCreateSuccess = true
			return new SuccessOutcome()
		}
	}
}
