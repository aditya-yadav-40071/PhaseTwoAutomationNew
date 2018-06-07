package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class EditOrganizationProfilePage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("editProfileDataSubmit")){
			new EditOrganizationProfileForm().populateFields(browser, formData);
		}else
		if(formKey.equals("editAboutOrgSubmit")){
			new EditAboutOrganizationForm().populateFields(browser, formData);
		}else
		if(formKey.equals("orgLatestNewsSubmit")){
			new EditLatestNewsForm().populateFields(browser, formData);
		}
	}

	//Override
	def submit = {browser, formKey, formData ->
		if(formKey.equals("editProfileDataSubmit")){
			new EditOrganizationProfileForm().submit(browser, formData);
		}else
		if(formKey.equals("editAboutOrgSubmit")){
			new EditAboutOrganizationForm().submit(browser, formData);
		}else
		if(formKey.equals("orgLatestNewsSubmit")){
			new EditLatestNewsForm().submit(browser, formData);
		}
	}

	//To verify that the necessary fields are entered in Edit profile page with correct data.
	def static profileDataDisplayMatch = { browser, formData ->
		new EditOrganizationProfileForm().profileDataDisplayMatch browser, formData
	}

	//To remove all industries.
	def static removeAllIndustry= { browser, formData ->
		new EditOrganizationProfileForm().removeAllIndustry browser, formData
	}

	//To verify the error message.
	def static indErrorMessageMap = { browser, formData ->
		new EditOrganizationProfileForm().indErrorMessageMap browser, formData
	}

	//To remove industries specified
	def static removeIndustries = { browser, formData ->
		new EditOrganizationProfileForm().removeIndustries browser, formData
	}

	//To verify if the industry is removed.
	def static industryRemoved = { browser, formData ->
		new EditOrganizationProfileForm().industryRemoved browser, formData
	}

	//To perform upload of logo of Organization
	def static orgProfileImgUpload = { browser, formData ->
		new EditOrganizationProfileForm().orgProfileImgUpload browser, formData
	}

	//To get First name of the Org Admin
	def static getOrgFirstName = { browser, formData ->
		new EditOrganizationProfileForm().getOrgFirstName browser, formData
	}

	static final class EditOrganizationProfileForm extends WebForm {

		//Edit Organization Profile Page form elements
		private static final def ORG_NAME = ".//*[@id='orgName']"

		private static final def INDUSTRY = "//input[@ng-change='onChange()'][@name='name']"

		private static final def INDUSTRY_VALUES = "//p[@class='pull-left mb_0 ml_0 mr_10 ng-binding']"

		private static final def ADDRESS_1 = ".//*[@id='address1']"

		private static final def ADDRESS_2 = ".//*[@id='address2']"

		private static final def ADDRESS_3 = ".//*[@id='address3']"

		private static final def CITY = ".//*[@id='city']"

		private static final def PINCODE = "//input[@name='pincode']"

		private static final def ORG_EMAIL_ID = ".//*[@id='officialEmailId']"

		private static final def ORG_NUMBER = "//label[@for='officialContactNo']/following-sibling::input"

		private static final def FIRST_NAME = ".//*[@id='fname']"

		private static final def MIDDLE_NAME = ".//*[@id='mname']"

		private static final def LAST_NAME = ".//*[@id='lname']"

		private static final def EMAIL_ID = ".//*[@id='emailId']"

		private static final def MOBILE_NUMBER = "//label[@for='contactNo']/following-sibling::input"

		private static final def UPDATE_DETAILS_FORM = "//button[@ng-click='updateProfile(company)']"

		private static final def CITY_AUTOSELECT = ".//div[@class='pac-container pac-logo hdpi'][last()]/descendant::span[@class='pac-matched']"

		private static final def INDUSTRY_AUTOSELECT = "//div/ul/li[@ng-click='onSuggestedItemsClick(suggestion)']"

		private static final def INDUSTRY_CLOSE = "//span[@class='remove']/i"

		private static final def UPLOAD_LOGO = ".//*[@id='edit_profile_sidebar']/div/div[2]/label"

		private static def FIND_IND_CLOSE = "//p[contains(text(),'industryName')]/../span/i" 

		private static final def FIELDS = [ORG_NAME, INDUSTRY, ADDRESS_1, ADDRESS_2, ADDRESS_3, CITY, PINCODE, ORG_EMAIL_ID, ORG_NUMBER, FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL_ID, MOBILE_NUMBER,]

		private static final def COMPARE_FIELDS = [ORG_NAME, INDUSTRY_VALUES, ADDRESS_1, CITY, PINCODE, FIRST_NAME, EMAIL_ID, MOBILE_NUMBER]

		// the error fields.
		private static final def FIELD_ERROR = "//span[@class='error_message']/span"

		private static final def SUCCESS_MESSAGE = ".//*[@id='main_page']/div[1]/div/span"

		private static final def ERROR_MESSAGE_FIELDS = [FIELD_ERROR, SUCCESS_MESSAGE]

		//error message map (Key-Value Pair)
		def static final EditOrgProfileFormErrorMessageMap = [
			orgName_req : "Name is required",
			sel_industry : "Select Industry.",
			addr_req : "Address Line 1 is required",
			city_req : "City Name is required.",
			pin_req : "Pin code is required",
			pin_size : "Pin code should be of 6 digits",
			Email_req : "Email ID is required.",
			invalid_email : "Not a valid Email ID",
			mobileNo_req : "Mobile No is required",
			invalid_mobNo : "Not a valid Mobile No",
			adminName_req : "First Name is required.",
			unRegistered_mobileNo : "Entered mobile number is not registered with any user. Please check the mobile number",
			orgProfile_Success : "Organization Profile Updated Successfully",
			orgLogo_Success : "Organization logo uploaded successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def industryList = []
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<=FIELDS.size()-1;i++){
					if(FIELDS[i].equals(CITY)){
						if(formData[i].equals("")){
							browser.scrollToElement(browser.getElement(Browser.XPATH, CITY))
							browser.populateField(CITY,formData[i])
						}else{
							KPCommonPage.city = formData[i]
							browser.scrollToElement(browser.getElement(Browser.XPATH, CITY))
							browser.populateField(CITY,formData[i])
							KPCommonPage.selectAutoComplete(browser, CITY_AUTOSELECT, formData[i].trim())
						}
					}else if(FIELDS[i].equals(INDUSTRY)){
						if(formData[i].equals("")){
							browser.scrollToElement2(INDUSTRY)
							browser.populateField(INDUSTRY,formData[i])
						}else if(formData[i].contains(",")){
							industryList = formData[i].split(",")
							for(int j=0;j<industryList.size();j++){
								browser.scrollToElement2(INDUSTRY)
								KPCommonPage.industry = industryList[j]
								browser.populateField(INDUSTRY,industryList[j])
								browser.delay(2000)
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOSELECT, industryList[j].trim())
							}
						}else{
							KPCommonPage.industry = formData[i]
							browser.populateField(INDUSTRY,formData[i])
							browser.delay(2000)
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOSELECT, formData[i].trim())
						}
					}else if(FIELDS[i].equals(ORG_NAME)||FIELDS[i].equals(ORG_EMAIL_ID) || (FIELDS[i].equals(ORG_NUMBER))){
						KPCommonPage.ViewOrgDataVerify.add(formData[i])
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
						browser.populateField(FIELDS[i],formData[i])
					}else{
						def tagName = browser.getTagName(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						browser.delay(500)
					}
					outcome = new SuccessOutcome()
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_DETAILS_FORM, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, EditOrgProfileFormErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement2(submitButton)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton
				browser.delay(1000)
			}else{
				browser.scrollToElement2(ORG_NAME)
			}
			browser.delay(500)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		//To verify if data displayed initially is a match
		def static final profileDataDisplayMatch = { browser, formData ->
			def getListOfData = [], formDataValues = []
			def industryData = [], flag
			for(int i=0; i<COMPARE_FIELDS.size();i++){
				if(COMPARE_FIELDS[i].equals(INDUSTRY_VALUES) && formData[i]!= ""){
					browser.scrollToElement2(INDUSTRY_VALUES)
					def industryValues = browser.getLists(COMPARE_FIELDS[i])
					getListOfData[i]= industryValues.collect()
				}else
					getListOfData[i] = browser.gettext(COMPARE_FIELDS[i], "value")
			}
			formDataValues = KPCommonPage.registrationData.values()
			for(int i=0;i<getListOfData.size();i++){
				if(getListOfData[i].equals(formDataValues[i])){
					flag=true;
				}else
					flag=false;
				break;
			}
			if(flag){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "dataMismatchinEditProfile", "Edit Org profile page does not display same data.")
		}

		//To remove all industries to capture the error message
		def static final removeAllIndustry = {browser, formData ->
			def closeIndElements = []
			//browser.scrollToElement(browser.getElement(Browser.XPATH, INDUSTRY))
			if(browser.isDisplayed(INDUSTRY_CLOSE)){
				closeIndElements = browser.getListElements(INDUSTRY_CLOSE)
				for(int i=0;i<closeIndElements.size()-1;i++){
					browser.clickElement(closeIndElements[i])
				}
			}
			browser.click INDUSTRY
			browser.pressTab(INDUSTRY)
			if(!browser.isDisplayed(INDUSTRY_CLOSE)){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "industryNotRemoved", "Industry not removed.")
		}

		//Verifying the error message after removing industries
		def static final indErrorMessageMap = {browser, formData ->
			def industryError = browser.gettext(FIELD_ERROR)
			def actualErrorMsg = EditOrgProfileFormErrorMessageMap.get('sel_industry')
			if(industryError.trim().equalsIgnoreCase(actualErrorMsg)){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "dataMismatchinIndMsg", "Industry error msg mismatch")
		}

		//To remove specific industries
		def static final removeIndustries = {browser, formData ->
			def industryList
			def result = false
			if(formData[0].equals("")){
				result = true
			}else if(formData[0].contains(",")){
				industryList = formData[0].split(",")
				for(int i=0;i<industryList.size();i++){
					FIND_IND_CLOSE = FIND_IND_CLOSE.replaceAll("industryName", industryList[i].trim())
					browser.scrollToElement2(INDUSTRY)
					browser.click FIND_IND_CLOSE
				}
				result = true
			}else{
				FIND_IND_CLOSE = FIND_IND_CLOSE.replaceAll("industryName", formData[0].trim())
				browser.scrollToElement2(INDUSTRY)
				browser.click FIND_IND_CLOSE
				browser.delay(2000)
				result = true
			}
			if(result){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "noIndustryDisplayed", "Industry to remove not displayed.")
		}

		//To verify if the industry is removed after updation
		def static final industryRemoved = { browser, formData ->
			def industryNamesOnPage = []
			def industryValues
			browser.scrollToElement2(INDUSTRY)
			if(browser.isDisplayed(INDUSTRY_VALUES)){
				industryNamesOnPage = browser.getLists(INDUSTRY_VALUES)
			}else
				return new SuccessOutcome()
			if(formData[0].contains(",")){
				industryValues = formData[0].split(",")
				if(!industryNamesOnPage.containsAll(industryValues)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "IndustryDisplayed", "Industries not removed after update.")
			}else if(!industryNamesOnPage.contains(formData[0])){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "IndustryDisplayed", "Industry not removed after update.")
		}

		//To perform uploading of the image in Organization profile
		def static final orgProfileImgUpload = {browser, formData ->
			def dataToEnter=""
			browser.scrollToElement2(UPLOAD_LOGO)
			browser.click UPLOAD_LOGO
			dataToEnter = browser.getCurrentDirectory()+formData[0]
			browser.uploadFile(dataToEnter)
			browser.delay(1000)
			def orgUploadText = browser.gettext(SUCCESS_MESSAGE)
			def actualUploadMsg = EditOrgProfileFormErrorMessageMap.get('orgLogo_Success')
			KPCommonPage.srcOrgLogoImage = browser.gettext(KPCommonPage.IMAGE_LOGO_ORG, "src").split("\\?")[0].trim()
			if(orgUploadText.trim().equalsIgnoreCase(actualUploadMsg.trim())){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "uploadError", "Organization Logo upload error.")
			}
		}

		//Get the name of Admin from this page
		def static final getOrgFirstName = {browser, formData ->
			def valueFirstName
			browser.scrollToElement2(FIRST_NAME)
			if(browser.isDisplayed(FIRST_NAME)){
				valueFirstName = browser.gettext(FIRST_NAME, "value")
				if(valueFirstName!=null){
					KPCommonPage.firstNameAdmin = valueFirstName
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "firstNameNull", "FIrst name is null.")
			}
		}
	}

	static final class EditAboutOrganizationForm extends WebForm {

		//Edit About Organization Profile Page form elements
		private static final def ABOUT_ORG_INFO = ".//*[@id='company_profile_form_container']/div[17]/div/textarea"

		private static final def UPDATE_ABOUT_ORG = ".//*[@id='company_profile_form_container']/div/div/div/div/button[@value='UPDATE']"

		private static final def FIELDS = [ABOUT_ORG_INFO]

		// the error fields.
		private static final def FORM_ERROR = "//span[@class='error_message']/span"

		private static final def SUCCESS_MESSAGE = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, SUCCESS_MESSAGE]

		//error message map (Key-Value Pair)
		def static final EditAboutOrgFormErrorMessageMap = [aboutOrg_Success : "About Organization Information Updated Successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<=FIELDS.size()-1;i++){
					if(FIELDS[i].equals(ABOUT_ORG_INFO)){
						browser.scrollToElement2(ABOUT_ORG_INFO)
						browser.populateField(ABOUT_ORG_INFO,formData[i])
					}else{
						def tagName = browser.getTagName(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						browser.delay(500)
					}
					outcome = new SuccessOutcome()
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_ABOUT_ORG, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, EditAboutOrgFormErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(200)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
	static final class EditLatestNewsForm extends WebForm {

		//Edit Latest News Page form elements
		private static final def ADD_NEWS = "//form[@name='companyInfoForm']/textarea"

		private static final def UPDATE_ADDED_NEWS = "//button[@ng-click='AddCompanyNews(company)']"

		private static final def FIELDS = [ADD_NEWS]

		// the error fields.
		private static final def FORM_ERROR = "//span[@class='error_message']/span"

		private static final def SUCCESS_MESSAGE = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, SUCCESS_MESSAGE]

		//error message map (Key-Value Pair)
		def static final EditLatestNewsFormErrorMessageMap = [latestNews_Success : "Organization News Information Updated Successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, UPDATE_ADDED_NEWS, WAIT_REQ_FIELDS)
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_ADDED_NEWS, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, EditLatestNewsFormErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton
				browser.delay(500)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
}