package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 11/08/2017
 */

final class PodCustomizationPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new PodCustomizationForm().populateFields(browser, formData);
	}
	
	def static podsAvailability = {browser, formData ->
		new PodCustomizationForm().podsAvailability browser, formData
	}
	
	
	/*def podIconUpload = {browser, formData ->
		new PodCustomizationForm().podIconUpload browser, formData
	}*/
	
	
	static final class PodCustomizationForm extends WebForm {

		//Pod Customization form elements
		
		private static final def VIEWING_PODS = "//h3[text()='Viewing all Pods']"
		
		private static final def ITEMS_PERPAGE = "//div[@class='items-per-page-section pull-right pos-r row no-margin']"
		
		private static final def TEN_PERPAGE = "//label[@for='item1']"
		
		private static final def POD_LIST = "//a[@class='content-name job-title pointer title-name blue ng-binding']"
		
		private static final def CHANGE_COURSE_ICONLINK = "//a[@data-target='#iconModal']"
		
		private static final def JOB_TITLE = "//input[@aria-label='Enter Job Title']"
		
		private static final def JOB_TITLE_LIST = "//md-virtual-repeat-container[contains(@class,'autocomplete')][1]//ul//span"

		private static final def NO_OF_POSITIONS = ".//input[@id='noOfPositions']"

		private static final def ADD_BUTTON = ".//button[text()='Add']"
		
		private static final def FIELDS = [JOB_TITLE, NO_OF_POSITIONS]
		
		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"
		
		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"
		
		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1,FIELD_ERROR_2]
		
		//error message map (Key-Value Pair)
		def static final PostJobPageErrorMessageMap = [
			jobtitle_req              :  'Select Job Title.',
			orgLogo_Success           : 'Organizaion Logo uploaded successfully']  //can'+"'"+'t

		//To enter data
		def static final populateFields = { browser, formData ->
			
			println ("PostJobForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size(); i++){
					if(FIELDS[i].equals(JOB_TITLE) && formData[i]!= ""){
						KPCommonPage.jobTitle = formData[i]
						browser.delay(2000)
						browser.populateField(JOB_TITLE,formData[i])
						browser.delay(2500)
						KPCommonPage.selectAutoComplete(browser, JOB_TITLE_LIST, formData[i].trim())
						browser.delay(2500)
						
					}
					
					
					if(FIELDS[i].equals(LOCATION) && formData[i]!= ""){
						KPCommonPage.city = formData[i]
						browser.scrollToElement(browser.getElement(Browser.XPATH, LOCATION))
						browser.delay(2000)
						browser.populateField(LOCATION,formData[i])
						browser.delay(5500)
						browser.pressDownArrow(LOCATION)
						browser.delay(2000)
						browser.pressEnter(LOCATION)
						//KPCommonPage.selectAutoComplete(browser, LOCATION_LIST, formData[i].trim())
						browser.delay(2000)
						
					}else if(FIELDS[i].equals(INDUSTRY)){
						if(formData[i].equals("")){
							browser.pressTab(FIELDS[i])
						}else{
							KPCommonPage.jobIndustry = formData[i]
							browser.delay(2000)
							browser.populateField(INDUSTRY,formData[i])
							browser.delay(2000)
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOSELECT, formData[i].trim())
							browser.delay(2000)
						}
					}else{
						def tagName = browser.getTagName(FIELDS[i])
						browser.scrollToElement2(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
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
			
			def actualValidationMsg = submitForm browser, FIELDS, ADD_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, PostJobPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			
			browser.click submitButton // submit the form.
			browser.scrollToElement2(JOB_TITLE)
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		
		def static final podsAvailability = {browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(VIEWING_PODS)){
				if(browser.isDisplayed(ITEMS_PERPAGE)){
					if(browser.isDisplayed(POD_LIST)){
						println "....Pod list is displaying......"
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "PodListDisplayedIssue", "Please check that No pods are displaying.")
					}
				}
				return KPCommonPage.returnFailureOutcome(browser, "ItemsPerPageDisplayedIssue", "Items Per page is not displaying.")
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ViewingPodsDisplayedIssue", "Viewing pods not displayed.")
			}
		}
		
		
		/*def static podIconUpload = {browser, formData ->
			println "....Inside pod icon method....."
		}
		*/
		
		
		
		
	}
}
