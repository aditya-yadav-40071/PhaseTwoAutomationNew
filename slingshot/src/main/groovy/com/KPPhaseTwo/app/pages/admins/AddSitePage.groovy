package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya 
 */

final class AddSitePage extends WebPage {

	/**
	 * Add a site 
	 */
	def populateData = { browser, formKey, formData ->
		new AddSiteForm().populateFields(browser, formData);
	}

	def submit = { browser, formKey, formData ->
		new AddSiteForm().submit(browser, formData)
	}

	static final class AddSiteForm extends WebForm {

		private static final def  SITE_NAME = ".//*[@ng-model='siteDetail.siteName']"

		private static final def ADDRESS_1 = ".//*[@placeholder='Enter Address 1']"

		private static final def ADDRESS_2 = ".//*[@placeholder='Enter Address 2']"

		private static final def CITY = ".//*[@placeholder='Enter City name']"

		private static final def PIN_CODE = ".//*[@placeholder='Enter Pincode']"

		private static final def CONTACT_NUM = ".//*[@placeholder='Enter contactNumber']"

		private static final def SITE_EMAIL = ".//*[@placeholder='Enter Email Id']"

		private static final def AUTO_CITY_LIST = ".//div[@class='pac-container pac-logo'][last()]/descendant::span[@class='pac-matched']"

		private static final def STATE = ".//*[@placeholder='Enter state name']"

		private static final def COUNTRY = ".//*[@placeholder='Enter Country name']"

		private static final def SUBMIT = ".//button[@type='button']"

		private static final def FORM_ERROR = ".//div[@role='alert']//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR = ".//span[@class='error_message col-md-12']/span[@aria-hidden='false']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		private static final def FIELDS = [SITE_NAME, ADDRESS_1, ADDRESS_2, CITY, PIN_CODE, CONTACT_NUM, SITE_EMAIL]

		def static final addSitePageErrorMessageMap = [
			siteAdd_Success    : "Site Added Successfully",
			siteUpdate_Success : "Site Details updated successfully.",
			siteName_req       : "Site Name is required",
			address1_req       : "Address line 1 is required",
			city_req           : "City is required",
			pin_req            : "Pincode is required",
			invalid_pin        : "Enter a valid Pincode",
			invalid_Contact    : "Enter a valid contact Number",
			invalid_Email      : "Enter a valid Email ID"]


		/**
		 * Add a site in an organization or Site Admin
		 */
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				KPCommonPage.siteDetails.clear()
				for(int i = 0; i < FIELDS.size(); i++){
					def tagName = browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(SITE_NAME)){
						if(formData[i]!=null && formData[i]!=""){
							def data = KPCommonPage.generateRandomString()
							KPCommonPage.addedSiteName = formData[i]+""+data
							WebForm.inputData(browser,FIELDS[i],tagName,KPCommonPage.addedSiteName)
						}
					}else if(FIELDS[i].equals(CITY)){
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
						browser.delay(2000)
						if(formData[i] != ""){
							KPCommonPage.siteCity = formData[i].trim()
							KPCommonPage.siteDetails.add(formData[i].trim())
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(3000)
							KPCommonPage.selectAutoComplete(browser, AUTO_CITY_LIST, formData[i].trim())
						}
					}else if(FIELDS[i].equals(SITE_EMAIL) && formData[i]!=""){
						KPCommonPage.siteDetails.add(formData[i].trim())
						KPCommonPage.siteEmail = formData[i].trim()
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
					}else if(FIELDS[i].equals(CONTACT_NUM) && formData[i]!=""){
						KPCommonPage.siteDetails.add(formData[i].trim())
						KPCommonPage.sitePhone = formData[i].trim()
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
					}else{
						if(formData[i]!=""){
							KPCommonPage.siteDetails.add(formData[i].trim())
						}
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						browser.delay(2000)
					}
				}
				browser.pressTab(SITE_EMAIL)
				def stateValue = browser.gettext(STATE,"value")
				KPCommonPage.siteDetails.add(stateValue.trim())
				def countryValue = browser.gettext(COUNTRY,"value")
				KPCommonPage.siteDetails.add(countryValue.trim())
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
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, addSitePageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		/**
		 * To submit the add site form 
		 * When site is created sucessfully then  the siteCreateStatus is marked true in KPCommonPAge
		 */
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				KPCommonPage.siteCreateStatus = true
				browser.delay(2000)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
}