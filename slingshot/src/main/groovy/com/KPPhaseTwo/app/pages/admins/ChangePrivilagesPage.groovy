package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage



final class ChangePrivilagesPage extends WebPage {

	/**
	 * @author Aditya
	 * Populate the Change privilages form for an  admin
	 */
	def populateData = { browser, formKey, formData ->
		new ChangePrivilagesPageForm().populateFields(browser, formData);
	}

	/**
	 * @author Aditya
	 * Submit the Change privilages form for an  admin
	 */
	def submit = { browser, formKey, formData ->
		new ChangePrivilagesPageForm().submit(browser, formData)
	}

	/**
	 * @author Aditya
	 * To check if admin privilages are changed after a login of the respective admin
	 */
	def static adminPrivilageChanged = { browser, formData ->
		new ChangePrivilagesPageForm().adminPrivilageChanged(browser,formData)
	}

	static final class ChangePrivilagesPageForm extends WebForm {


		private static final def  TRAINER_ADMIN_SITE = ".//md-select[@ng-model='subadminform.selectedSiteIds']"

		private static final def  POD_ACCESS = ".//*[@name='podAccessAllowedForTrainer']//following-sibling::label"

		private static final def  POD_ACCESS_YES = ".//label[@id='podAccessAllowedForTrainerLabel']"

		private static final def  SITE_ADMIN_SITE = ".//select[@name='siteName']"

		private static final def  SITE_NAMES = ".//md-option[@ng-repeat='site in orgSitesList']/div[@class='md-text ng-binding']"

		private static final def  HR_ADMIN = ".//*[@id='hr']"

		private static final def  SELECT_ADMIN_ROLES = ".//input[@type='radio'][@ng-model='subadminform.changePrivileges']"

		private static final def UPDATE = ".//div[@class='row mb_20']/div/button"

		private static final def FIELDS= [SELECT_ADMIN_ROLES, SITE_NAMES, POD_ACCESS_YES]

		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		def static final changePrivilagesPageErrorMessageMap = [success_message : 'Sub-admin/Trainer/HR set privileges updated successfully.']

		/**
		 * @author Aditya
		 * Populate the Change privilages form for an  admin
		 */
		def static final populateFields = { browser, formData ->
			browser.delay(2500)
			def siteToSelect
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
					if(FIELDS[i].equals(SELECT_ADMIN_ROLES) && formData[i]!=""){
						def tagName = browser.getTagName(FIELDS[i])
						if(formData[i].equals("1") && !browser.isDisplayed(HR_ADMIN) && browser.isDisplayed(TRAINER_ADMIN_SITE)){
							browser.scrollToElement2(TRAINER_ADMIN_SITE)
							browser.delay(1000)
							browser.click(TRAINER_ADMIN_SITE)
							browser.delay(1000)
							browser.click(SITE_NAMES)
							browser.click(".//label[@for='privileges']")
							browser.delay(1000)
							break
						}
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						if(formData[i].equals("1")){
							KPCommonPage.adminRole = "Trainer"
							if(browser.isDisplayed(TRAINER_ADMIN_SITE)){
								browser.click(TRAINER_ADMIN_SITE)
								if(formData[i+1].contains(",")){
									siteToSelect = formData[i+1].split(",")
									KPCommonPage.adminSite.clear()
									for(int j=0;j<siteToSelect.size();j++){
										KPCommonPage.adminSite.add(siteToSelect[j].trim())
										new ChangePrivilagesPageForm().selectTrainerSite(browser,siteToSelect[j].trim())
									}
								}else{
									KPCommonPage.adminSite.clear()
									KPCommonPage.adminSite.add(formData[i+1].trim())
									new ChangePrivilagesPageForm().selectTrainerSite(browser,formData[i+1].trim())
								}
								browser.click(".//label[@for='privileges']")
								browser.delay(1000)
							}
							if(browser.isDisplayed(POD_ACCESS)){
								browser.delay(500)
								if(formData[i+2].trim().equals("1")){
									browser.click(POD_ACCESS_YES)
								}
							}
						}else if(formData[i].equals("2")){
							KPCommonPage.adminRole = "HR"
						}else if(formData[i].equals("3")){
							KPCommonPage.adminRole = "Site Admin"
							KPCommonPage.adminSite.add(formData[i+1].trim())
							browser.selectDropdownValue(SITE_ADMIN_SITE,formData[i+1])
						}else if(formData[i].equals("4")){
							KPCommonPage.adminRole = "Sub Admin"
						}
					}
				}
			}
			return outcome
		}

		/**
		 * @author Aditya
		 * Submit the Change privilages form for an  admin
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE, data, ERROR_MESSAGE_FIELDS
			browser.delay(2000)
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, changePrivilagesPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton
				browser.delay(3000)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		/**
		 * @author Aditya
		 * This method is user to select site for a trainer
		 * @param browser - Browser class Instance 
		 * @param siteToSelect - Site to be selected for a trainer
		 */

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
		 * To check if admin privilages are changed after a login of the respective admin
		 */
		def static final adminPrivilageChanged = { browser,formData ->
			def result
			def adminPrivilages = KPCommonPage.Privilage
			for(int i=0;i<adminPrivilages.size()-1;i++){
				if(adminPrivilages[i] == "0" && adminPrivilages[i+1] == "1") {
					println "Inside Data 0 and 1"
					if(browser.isDisplayed(ADMIN_HIRE) && browser.gettext(ADMIN_HIRE).equalsIgnoreCase("Hire") && !browser.isDisplayed(ADMIN_TRAIN)){
						result=true
					}else{
						result= false
						break
					}
				}else if(adminPrivilages[i]== "1" && adminPrivilages[i+1]== "0") {
					println "Inside Data 1 and 0"
					if(browser.isDisplayed(ADMIN_TRAIN) && browser.gettext(ADMIN_TRAIN).equalsIgnoreCase("Train") && !browser.isDisplayed(ADMIN_HIRE)){
						result=true
					}else{
						result= false
						break
					}
				}else {
					println "Inside Data 1 and 1"
					def hireText=browser.gettext(ADMIN_TRAIN)
					def trainText=browser.gettext(ADMIN_HIRE)
					def profileText=browser.gettext(EDIT_TEXT)

					if(hireText.isDisplayed(ADMIN_TRAIN) && trainText.isDisplayed(ADMIN_HIRE) && !profileText.equalsIgnoreCase("Edit Organization Profile")){
						if(hireText.equalsIgnoreCase("Hire") && trainText.equalsIgnoreCase("Train")){
							result=true
						}else{
							result= false
							break
						}
					}
				}
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AdminChangePrivilageIssue", "Admin Privilages does not match")

			}
		}
	}
}