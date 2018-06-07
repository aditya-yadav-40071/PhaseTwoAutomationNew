package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


final class AddAdminPage extends WebPage {

	def populateData = { browser, formKey, formData ->
		new AddAdminForm().populateFields(browser, formData);
	}

	def submit = { browser, formKey, formData ->
		new AddAdminForm().submit(browser, formData)
	}

	/**
	 * @author Aditya
	 * This method compares the error message displayed when user tries to add another admin
	 * without filling all the mandatory field on add admin page
	 */
	def static addAnotherAdminErrorMessage = { browser, formData ->
		new AddAdminForm().addAnotherAdminErrorMessage browser,formData
	}

	/**
	 * @author Aditya
	 * The trainer create status is marked as true when trainer is created successfully
	 * By default the trainer create status is marked as false in populate field of Add Admin Page
	 */
	def static trainerCreateStatus = { browser, formData ->
		new AddAdminForm().trainerCreateStatus browser,formData
	}

	static final class AddAdminForm extends WebForm {

		//Login form elements
		private static final def  FIRST_NAME = ".//*[@name='fname']"

		private static final def  MIDDLE_NAME = ".//*[@name='mname']"

		private static final def  LAST_NAME = ".//*[@name='lname']"

		private static final def  EMAIL = ".//*[@id='subAdminMailId']"

		private static final def  SEND_INVITATION = ".//md-checkbox[@ng-model='subadminform.sendinvitationstatus']"

		private static final def  PASSWORD = ".//*[@id='subAdminPassword']"

		private static final def  CONFIRM_PASSWORD = ".//*[@id='confirmPassword']"

		private static final def  SELECT_ADMIN_ROLES = ".//input[@type='radio'][@ng-model='subadminform.changePrivileges']"

		private static final def  POD_ACCESS = ".//*[@name='podAccessAllowedForTrainer']//following-sibling::label"

		private static final def  TRAINER_ADMIN_SITE = ".//*[@ng-model='subadminform.selectedSiteIds']"

		private static final def  POD_ACCESS_YES = ".//label[@id='podAccessAllowedForTrainerLabel']"

		private static final def  POD_ACCESS_NO = ".//label[@id='podAccessNotAllowedForTrainerLabel']"

		private static final def  SITE_NAMES = ".//md-option[@ng-repeat='site in orgSitesList']/div[@class='md-text ng-binding']"

		private static final def  SITE_ADMIN_SITE = ".//select[@name='siteName']"

		private static final def SAVE = ".//button[@ng-click='submitRegistrationDetails(subadminform)']"

		private static final def ADDANOTHERADMIN_MESSAGE = ".//div[@ng-transclude='']/span"

		private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL, SELECT_ADMIN_ROLES, SITE_NAMES, POD_ACCESS_YES, SEND_INVITATION, PASSWORD, CONFIRM_PASSWORD]
		// the error fields.
		private static final def FIELD_ERROR_1 = "//span[@class='error_message'][@aria-hidden='false']"

		private static final def FIELD_ERROR_2 = ".//span[@class='error_message']/span[3]"

		private static final def SUCCESS_MESSAGE = ".//div[@type='success']//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FIELD_ERROR_1, FIELD_ERROR_2, SUCCESS_MESSAGE]

		//error message map (Key-Value Pair)
		def static final addAdminPageErrorMessageMap = [fname_req :'First Name is required.',
			email_req :'Email ID is required.',
			invalid_email :'Enter a valid email ID',
			email_already_exist :'EmailId already exists',
			pass_req :'Password is required',
			confirm_pass_req :'Re-enter Password is required',
			pass_combination_error :'Password should be a combination of minimum 8 characters containing a capital letter,special character and a digit',
			pass_mismatch :'Password does not match',
			enter_corr_pass :'Enter correct password',
			success_message : 'Admin registered succcessfully'
		]

		//To enter data
		def static final populateFields = { browser, formData ->
			def siteToSelect
			def emailToEnter
			//KPCommonPage.adminFlag = true
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
					def tagName = browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(FIRST_NAME)){
						def data = KPCommonPage.generateRandomString()
						KPCommonPage.adminFirstNamee = formData[i]+""+data.trim()
						browser.populateField(FIELDS[i], formData[i])
					}else if(FIELDS[i].equals(MIDDLE_NAME)){
						KPCommonPage.adminMiddleName = formData[i].trim()
						browser.populateField(FIELDS[i], formData[i])
					}else if(FIELDS[i].equals(LAST_NAME)){
						KPCommonPage.adminLastName = formData[i].trim()
						browser.populateField(FIELDS[i], formData[i])
					}else if(FIELDS[i].equals(EMAIL)){
						if(formData[i].contains("@")){
							emailToEnter = KPCommonPage.generateRandomEmailAddress(formData[i])
							WebForm.inputData(browser, FIELDS[i], tagName,  emailToEnter)
							KPCommonPage.adminEmail_Id = emailToEnter
							KPCommonPage.adminEmailId = emailToEnter
						} else {
							WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						}
					}else if(FIELDS[i].equals(SELECT_ADMIN_ROLES) && formData[i]!="" && formData[i]!=null){
						browser.delay(2000)
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						if(formData[i].equals("1")){
							browser.delay(2000)
							KPCommonPage.adminRole = "Trainer"
							if(browser.isDisplayed(TRAINER_ADMIN_SITE)){
								browser.click(TRAINER_ADMIN_SITE)
								browser.delay(2000)
								if(KPCommonPage.trainerStatus){
									formData[i+1] = KPCommonPage.addedSiteName
									KPCommonPage.trainerStatus = false
								}
								if(formData[i+1].contains(",")){
									siteToSelect = formData[i+1].split(",")
									KPCommonPage.adminSite.clear()
									for(int j=0;j<siteToSelect.size();j++){
										KPCommonPage.adminSite.add(siteToSelect[j].trim())
										new AddAdminForm().selectTrainerSite(browser,siteToSelect[j].trim())
									}
								}else{
									KPCommonPage.adminSite.clear()
									KPCommonPage.adminSite.add(formData[i+1].trim())
									new AddAdminForm().selectTrainerSite(browser,formData[i+1].trim())
								}
								browser.click(".//label[@for='privileges']")
								browser.delay(1000)
							}
							if(browser.isDisplayed(POD_ACCESS)){
								browser.delay(1000)
								if(formData[i+2].trim().equals("1")){
									browser.click(POD_ACCESS_YES)
								}
							}
						}else if(formData[i].equals("2")){
							KPCommonPage.adminRole = "HR"
						}else if(formData[i].equals("3")){
							browser.delay(2000)
							KPCommonPage.siteAdminEmail = emailToEnter
							KPCommonPage.adminRole = "Site Admin"
							KPCommonPage.adminSite.clear()
							if(KPCommonPage.siteCreateStatus){
								siteToSelect = KPCommonPage.addedSiteName
								//KPCommonPage.siteCreateStatus = false
							}else{
								siteToSelect = "Default Online Site"
							}
							KPCommonPage.adminSite.add(siteToSelect.trim())
							browser.delay(2000)
							browser.selectDropdownValue(SITE_ADMIN_SITE,siteToSelect)
						}else if(formData[i].equals("4")){
							KPCommonPage.adminRole = "Sub Admin"
						}
					}else if(FIELDS[i].equals(SEND_INVITATION)){
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
					}else if(FIELDS[i].equals(PASSWORD)){
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						KPCommonPage.adminPassword = formData[i].trim()
					}else if(FIELDS[i].equals(CONFIRM_PASSWORD)){
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						browser.pressTab(CONFIRM_PASSWORD)
					}
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
			def actualValidationMsg = submitForm browser, FIELDS, SAVE, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, addAdminPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.delay(2000)
				browser.click submitButton // submit the form.
				browser.delay(1500)
			}else{
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static selectTrainerSite = { def browser, def siteToSelect->
			browser.delay(1000)
			def siteNames = browser.getLists(SITE_NAMES)
			def siteNameElements = browser.getListElements(SITE_NAMES)
			for(int j=0;j<siteNames.size();j++){
				if(siteToSelect.trim().equalsIgnoreCase(siteNames[j].trim())){
					browser.clickElement(siteNameElements[j])
					browser.delay(1000)
					break
				}
			}
		}

		/**
		 * @author Aditya
		 * The trainer create status is marked as true when trainer is created successfully
		 * By default the trainer create status is marked as false in populate field of Add Admin Page
		 */
		def static trainerCreateStatus = { browser, formDta ->
			KPCommonPage.trainerStatus = true
			return new SuccessOutcome()
		}

		/**
		 * @author Aditya
		 * This method compares the error message displayed when user tries to add another admin
		 * without filling all the mandatory field on add admin page
		 */
		def static addAnotherAdminErrorMessage = { browser, formData ->
			if(browser.isDisplayed(ADDANOTHERADMIN_MESSAGE)){
				def addAnotherAdminMessage = browser.gettext(ADDANOTHERADMIN_MESSAGE).trim()
				if(addAnotherAdminMessage.equalsIgnoreCase(formData[0].trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddAnotherAdminMessageMismatchIssue", "The error message do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddAnotherAdminMessageDisplayIssue", "The error message was not displayed")
			}
		}
	}
}
