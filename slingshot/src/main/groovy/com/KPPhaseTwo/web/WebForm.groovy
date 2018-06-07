package com.KPPhaseTwo.web

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


/**
 * Created by sandhya on 12/10/13.
 */
abstract class WebForm {

	public static def WAIT_REQ_FIELDS = []//To fillData
	def static final enterData(def browser,def formData,def FIELDS, def submitButton, def WAIT_REQ_FIELDS){
		def tagName,outcome
		for(int i=0; i<= FIELDS.size()-1;i++){
			tagName = browser.getTagName(FIELDS[i])
			if(!tagName.equals("NotFound")){
				if(WAIT_REQ_FIELDS.size() > 0){
					if(WAIT_REQ_FIELDS.contains(FIELDS[i])){
						browser.delay(2000)
					}
				}
				inputData(browser, FIELDS[i], tagName, formData.get(i))
				if(WAIT_REQ_FIELDS.size() > 0){
					if(WAIT_REQ_FIELDS.contains(FIELDS[i])){
						browser.delay(3000)
						browser.pressTab(FIELDS[i])
					}
				}
				outcome = new SuccessOutcome()
			}else{
				def message = "Input Element not Found :"+FIELDS[i]
				outcome = KPCommonPage.returnFailureOutcome(browser, "ElementNotFound", message)
				break
			}
		}
		if(browser.getElement(Browser.XPATH, submitButton) == null){
			return KPCommonPage.returnFailureOutcome(browser, "SubmitButtonIssue", "Submit button not found")
		}
		return outcome
	}

	//Verify Input fields and data from excel
	def static final checkFormFieldsData(def formData, def FIELDS){
		def outcome
		println "formData-----------------> "+formData.size()
		println "FIELDS-------------------> "+FIELDS.size()
		println "formData-----------------> "+formData
		println "FIELDS-------------------> "+FIELDS
		if(formData.size() == FIELDS.size()){
			outcome = new SuccessOutcome()
		}else{
			if(formData.size() > FIELDS.size()){
				outcome = new FailureOutcome("Excel Data has more fields than the actual Form Fields")
			}else{
				outcome = new FailureOutcome("Excel Data has less fields than the actual Form Fields")
			}
		}
		return outcome
	}


	/**
	 * To submit the form
	 * @param browser browser instance
	 * @param formFields Fields of form
	 * @param submitButton submit button
	 * @param data    array containing test data
	 * @param errFields error display fields
	 */
	def static submitForm = {browser, formFields, submitButton, formData, errFields ->
		browser.click submitButton // submit the form.
		browser.getValidationMessages errFields // get the validation messages from the current page.
	}

	/**
	 * To get the actual message keys 
	 * @param actualMessages       actual validation messages from the page
	 * @param pageErrorMessageMap  Error map containing key-value pair
	 * @return  actualValidationMsgKeys  keys of the actual messages
	 */
	def static final getActualErrorMessageKeys(def actualMessages, def pageErrorMessageMap){
		def actualValidationMsgKeys = []
		for(validationMessage in actualMessages) {
			pageErrorMessageMap.each { key, value ->
				if(value == validationMessage) {
					actualValidationMsgKeys.add(key)
				}
			}
		}
		actualValidationMsgKeys
	}

	//To enter the data
	def static final inputData(def browser, def field, def tagName, def data){
		switch(tagName){
			case "number":
				browser.scrollToElement2(field)
				browser.populateField(field, data)
				break

			case "text"	:
				browser.scrollToElement2(field)
				browser.populateField(field, data)

				break

			case "email"	:
				browser.scrollToElement2(field)
				browser.populateField(field, data)
				break

			case "radio"	:
				browser.scrollToElement2(field)
				field = KPCommonPage.getRadioButtonField(browser, field, data)
				browser.click field
				break

			case "select"  :
				browser.scrollToElement2(field)
				browser.click field
				browser.selectDropdownValue(field, data)
				break

			case "password"	:
				browser.scrollToElement2(field)
				browser.populateField(field, data)
				break

			case "checkbox" :
				browser.scrollToElement2(field)
				browser.clickCheckBox(field, data)
				break

			case "textarea"	:
				browser.scrollToElement2(field)
				browser.populateField(field, data)
				break

			case "md-checkbox" :
				browser.clickMdCheckBox(field,data)
				break

			case "label"	:
				if(data.equals("1")){
					browser.click(field)
				}
				break

			case "search" :
				browser.scrollToElement2(field)
				browser.populateField(field, data)
				break
		}
	}
}


