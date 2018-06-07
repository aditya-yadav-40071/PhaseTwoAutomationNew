package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Aditya on 28/11/2017
 */

final class UserEditProfilePage extends WebPage {

	//To populate the datas
	def populateData = { browser, formKey, formData ->
		if(formKey.equalsIgnoreCase("basicInformation")) {
			new BasicInformationForm().populateFields(browser, formData)
		} else if(formKey.equalsIgnoreCase("eductionQualification")) {
			new EductionQualificationForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("workExperience")){
			new WorkExperienceForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("skills")){
			new SkillsForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("certificate")){
			new CertificateForm().populateFields(browser, formData)
		}
	}

	//Override
	def submit = { browser, formKey, formData ->
		if(formKey.equalsIgnoreCase("basicInformation")) {
			new BasicInformationForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("eductionQualification")) {
			new EductionQualificationForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("workExperience")){
			new WorkExperienceForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("skills")){
			new SkillsForm().submit(browser, formData)
		}else if(formKey.equalsIgnoreCase("certificate")){
			new CertificateForm().submit(browser, formData)
		}
	}

	def static uploadProfilepic = { browser,formData ->
		new BasicInformationForm().uploadProfilepic(browser,formData)
	}

	def static leftSideDetailsOfEditProfile = { browser,formData ->
		new BasicInformationForm().leftSideDetailsOfEditProfile(browser,formData)
	}

	def static deleteExistingSkills = { browser,formData ->
		new SkillsForm().deleteExistingSkills(browser,formData)
	}

	def static skillsRetained = { browser,formData ->
		new SkillsForm().skillsRetained(browser,formData)
	}

	def static removeASkill = { browser, formData ->
		new SkillsForm().removeASkill(browser,formData)
	}

	def static skillsRemoved = { browser, formData ->
		new SkillsForm().skillsRemoved(browser,formData)
	}

	def static deleteWorkExperience = { browser,formData ->
		new WorkExperienceForm().deleteWorkExperience(browser,formData)
	}

	def static editWorkExperienceDetails = { browser,formData ->
		new WorkExperienceForm().editWorkExperienceDetails(browser,formData)
	}

	def static editDocumentsDetails = { browser,formData ->
		new CertificateForm().editDocumentsDetails(browser,formData)
	}

	def static deleteCertificate = { browser,formData ->
		new CertificateForm().deleteCertificate(browser,formData)
	}


	static final class BasicInformationForm extends WebForm {

		//Basic Information form elements

		private static final def FIRST_NAME                	  = ".//div[@class='basic-info-container ']//input[@id='fname']"

		private static final def MIDDLE_NAME                  = ".//div[@class='basic-info-container ']//input[@id='mname']"

		private static final def LAST_NAME                    = ".//div[@class='basic-info-container ']//input[@id='lname']"

		private static final def AGE                          = ".//div[@class='basic-info-container ']//input[@id='age_new']"

		private static final def GENDER                       = ".//div[@class='basic-info-container ']//select[@id='gender_new']"

		private static final def CAL_BTN                      =  ".//div[@class='basic-info-container ']//button[@ng-click='opendobcalendar()']"

		private static final def SELECT_DOB                   = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"

		private static final def MARITAL_STATUS               = ".//div[@class='basic-info-container ']//select[@name='marital_new']"

		private static final def ADD_LINE_1                   = ".//div[@class='basic-info-container ']//input[@id='address1']"

		private static final def ADD_LINE_2  			      = ".//div[@class='basic-info-container ']//input[@id='address2']"

		private static final def ADD_LINE_3   			      = ".//div[@class='basic-info-container ']//input[@id='address3']"

		private static final def CITY       		          = ".//div[@class='basic-info-container ']//input[@id='city']"

		private static final def CITY_AUTOSELCT    			  = ".//div[@class='pac-container pac-logo'][last()]/descendant::span[@class='pac-matched']"

		private static final def PIN_CODE    			      = ".//div[@class='basic-info-container ']//input[@name='pincode']"

		private static final def EMAIL_ID        		      = ".//div[@class='basic-info-container ']//input[@id='emailId']"

		private static final def MOBILE_NUM       			  = ".//div[@class='basic-info-container ']//input[@id='mobileno']"

		private static final def SEND_OTP_BTN  			      = ".//div[@class='col-md-3']/button[@class='full-width button-secondary btn-small']"

		private static final def RESEND_OTP       			  = ".//div[@class='col-md-3']/h4[@class='col-md-12 link-secondary pt_13']"

		private static final def OTP_TEXTBOX     			  = ".//div[@class='basic-info-container ']//input[@id='otp']"

		private static final def UPDATE_BUTTON     			  = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def UPLOAD_PROFILE_IMAGE_LINK    = ".//*[@id='edit_profile']/div[2]/label/span[1]"

		private static final def CHANGE_PROFILE_IMAGE_LINK    = ".//span[contains(text(), 'Upload Image')]"

		private static final def UPLOAD_PROFILE_IMAGE_BUTTON  = ".//button[@id='upload_image']"

		private static final def UPLOAD_IMAGE_SUBMIT 		  = ".//*[@id='btnCrop']"

		private static final def SUCCESS_MESSAGE			  = ".//*[@id='main_page']/div[1]/div/span"

		private static final def FIELDS						  = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, GENDER, CAL_BTN, MARITAL_STATUS, ADD_LINE_1, ADD_LINE_2, ADD_LINE_3, CITY, PIN_CODE, EMAIL_ID, MOBILE_NUM]

		private static final def FORM_ERROR                   = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR_1                = ".//div[@class='basic-info-container ']//span[@class='error_message'][@aria-hidden='false']/span[@aria-hidden='false']"//".//div[@class='basic-info-container ']//span[@class='error_message'][@aria-hidden='false']/span"//".//span[@class='error_message']"

		private static final def FIELD_ERROR_2                = ".//div[@class='basic-info-container ']//span[@class='error_message'][@ng-show='validateGender']"

		private static final def ERROR_MESSAGE_FIELDS = [FIELD_ERROR_1, FIELD_ERROR_2, FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final BasicInformationErrorMessageMap = [
			fname_req            :  'First name is required.',
			age_req              :  'Age is required.',
			invalid_age          :  'Enter a valid age',
			gender_req           :  'Select Gender.',
			dob_req              :  'Date Of Birth is required.',
			maritalstatus_req    :  'Select Marital Status.',
			addline1_req         :  'Address Line 1 is required.',
			city_req             :  'City Name is required.',
			pin_req              :  'Pin code is required',
			invalid_pin          :  'Pin code should be of 6 digits',
			emailid_req          :  'Email ID is required.',
			invalid_emailid      :  'Not a valid Email ID',
			emailid_exists       :  'Email ID already exists',
			mobile_req           :  'Mobile Number is required',
			mobile_exist         :  'Mobile Number already exists',
			invalid_mobile       :  'Enter a valid Mobile Number',
			startgreater_endtime :  'Start Time Period cannot be greater than End Time Period',
			startend_periodsame  :  'Start Time Period cannot be same as End Time Period',
			invalidimg_file	     :  'Not a valid file please upload supported file ex: doc, docx, pdf, rtf, jpeg, png ,jpg',
			invalid_size         :  'File size should less then 2 MB.',
			invalid_DOB 		 :  'Enter a valid Date Of Birth.',
			update_success 		 :  'User data updated successfully',
			profileUploadSuccess :  'Profile Pic uploaded successfully']

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("BasicInformationForm.populateFields - data: " + formData)
			browser.delay(4000)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size()-1; i++){
					def tagName = browser.getTagName(FIELDS[i])

					if(FIELDS[i].equals(FIRST_NAME)&& formData[i]!="" && formData[i]!=null){
						KPCommonPage.firstName = formData[i].trim()
						KPCommonPage.basicInfo.add(formData[i].trim())
						browser.populateField(FIELDS[i], formData[i])
					}
					else if(FIELDS[i].equals(MIDDLE_NAME)&& formData[i]!="" && formData[i]!=null){
						KPCommonPage.middleName = formData[i].trim()
						KPCommonPage.basicInfo.add(formData[i].trim())
						browser.populateField(FIELDS[i], formData[i])
					}
					else if(FIELDS[i].equals(LAST_NAME)&& formData[i]!="" && formData[i]!=null){
						KPCommonPage.lastName = formData[i].trim()
						KPCommonPage.basicInfo.add(formData[i].trim())
						browser.populateField(FIELDS[i], formData[i])
						browser.delay(200)
					}
					else if(FIELDS[i].equals(CAL_BTN) ){
						if(formData[i]!=""){
							browser.click(CAL_BTN)
							browser.delay(600)
							KPCommonPage.datePicker(browser,formData[i])
							KPCommonPage.basicInfo.add(browser.gettext(AGE,"value").trim())
							browser.delay(500)
							KPCommonPage.userAge = formData[i].trim()
						} else {
							browser.click FIELDS[i]
						}
					}
					else if(FIELDS[i].equals(CITY) ){
						browser.scrollToElement2(FIELDS[i])
						browser.delay(2000)
						if(formData[i] != ""){
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(3000)
							KPCommonPage.selectAutoComplete(browser, CITY_AUTOSELCT, formData[i].trim())
							KPCommonPage.basicInfo.add(formData[i].trim())
						} else{
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(2000)
						}
					}
					else if(FIELDS[i].equals(EMAIL_ID)){
						if (formData[i].equals("adiyada9@") || formData[i] == "") {
							if(formData[i].contains("@")){
								KPCommonPage.userName = formData[i]
								browser.populateField(FIELDS[i], KPCommonPage.userName)
							}else{
								browser.populateField(FIELDS[i],formData[i])
							}
						}else{
							if(formData[i].equals("anjum.ns@sakhatech.com")) {
								formData[i] = KPCommonPage.individualUserEmailId[0]
							}else{
								formData[i] = KPCommonPage.individualUserEmailId[1]
							}
							if(formData[i].contains("@")){
								KPCommonPage.userName = formData[i]
								browser.populateField(FIELDS[i], KPCommonPage.userName)
							}else{
								browser.populateField(FIELDS[i],formData[i])
							}
						}
					}
					else if(FIELDS[i].equals(MOBILE_NUM)){
						if (formData[i].equals("973922528") || formData[i] == "") {
							KPCommonPage.userMobileNo = formData[i]
							WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
							browser.pressTab(MOBILE_NUM)
						}else{
							KPCommonPage.userMobileNo = KPCommonPage.mobNoToEnterForReg
							formData[i] = KPCommonPage.mobNoToEnterForReg
							WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
							browser.pressTab(MOBILE_NUM)
						}
					}
					else if(FIELDS[i].equals(GENDER) || FIELDS[i].equals(MARITAL_STATUS)){
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
						if(formData[i]!="" && formData[i]!=null){
							KPCommonPage.basicInfo.add(formData[i].trim())
						}
					}else{
					
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, BasicInformationErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement2(submitButton)
			browser.delay(1000)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			browser.delay(1000)
			browser.scrollToElement2(FIRST_NAME)
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.

		}


		def static leftSideDetailsOfEditProfile = { browser,formData ->
			KPCommonPage.dataList.clear()
			KPCommonPage.actualDataList.clear()
			def xpathName = ".//*[@id='edit_profile']/h3"
			def xpathEmail = "//h5[i[contains(@ng-show,'emailId')]]"
			def xpathmobNum = ".//h5[@ng-show='userProfile.cloudUserJson.mobileNo']"
			def xpathDOB = ".//h5[@ng-show='userDOB']"
			def fName = browser.gettext(FIRST_NAME,"value")
			def mName = browser.gettext(MIDDLE_NAME,"value")
			def lName = browser.gettext(LAST_NAME,"value")

			KPCommonPage.dataList.add(KPCommonPage.getFullName(fName,mName,lName).trim())
			KPCommonPage.dataList.add(browser.gettext(EMAIL_ID,"value"))
			KPCommonPage.dataList.add(browser.gettext(MOBILE_NUM,"value"))
			KPCommonPage.dataList.add(browser.gettext(".//*[@id='dobdatepicker']","value"))

			KPCommonPage.actualDataList.add(browser.gettext(xpathName).trim())
			KPCommonPage.actualDataList.add(browser.gettext(xpathEmail).split("\n")[0].trim())
			KPCommonPage.actualDataList.add(browser.gettext(xpathmobNum).trim())
			KPCommonPage.actualDataList.add(browser.gettext(xpathDOB).trim())

			if(KPCommonPage.dataList.size()==KPCommonPage.actualDataList.size() && KPCommonPage.dataList.containsAll(KPCommonPage.actualDataList)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "leftSideDetailsMismatchIssue", "The details on the left side do not match on Edit Profile")
			}
		}

		def static uploadProfilepic = { browser,formData ->
			browser.delay(8000)
			def dataToEnter=""
			//def splittedimageSource
			browser.click(CHANGE_PROFILE_IMAGE_LINK)
			browser.delay(1500)
			browser.click(UPLOAD_PROFILE_IMAGE_BUTTON)
			browser.delay(1000)
			dataToEnter = browser.getCurrentDirectory()+formData[0]
			browser.uploadFile(dataToEnter)
			browser.delay(2000)
			browser.click(UPLOAD_IMAGE_SUBMIT)
			browser.delay(3000)
			def userUploadText = browser.gettext(SUCCESS_MESSAGE)
			def actualUploadMsg = BasicInformationErrorMessageMap.get('profileUploadSuccess')
			def imageSource = browser.gettext(KPCommonPage.USER_PROFILE_PIC, "src")
			def splittedimageSource = imageSource.split("\\?")
			KPCommonPage.srcUserProfilePic = splittedimageSource[0]

			if(userUploadText.equals(actualUploadMsg)){
				println " Profile Pic Uploaded Successfully."
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "uploadError", "User profile upload error")
			}
		}
	}

	static final class EductionQualificationForm extends WebForm {

		//Education qualification form Elements

		private static final def X_PERCENTAGE              = ".//div[@class='edu-qual-container ']//input[@id='xPercentage']"

		private static final def X_SCHOOL_NAME            = ".//div[@class='edu-qual-container ']//input[@id='xSchoolName']"

		private static final def X_YEAR_PASS              = ".//div[@class='edu-qual-container ']//select[@name='xYearOfPass']"

		private static final def XII_PERCENTAGE           = ".//div[@class='edu-qual-container ']//input[@id='xiiPercentage']"

		private static final def XII_SCHOOL_NAME          = ".//div[@class='edu-qual-container ']//input[@id='xiiSchoolName']"

		private static final def XII_YEAR_PASS            = ".//div[@class='edu-qual-container ']//select[@name='xiiYearOfPass']"

		private static final def DIPLOMA_PERCENTAGE       = ".//div[@class='edu-qual-container ']//input[@id='diplomaPercentage']"

		private static final def DIPLOMA_COLLEGE          = ".//div[@class='edu-qual-container ']//input[@id='diplomaCollegeName']"

		private static final def DIPLOMA_COURSE           = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Course Name']"

		private static final def DIPLOMA_START            = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='diplomaStartYear']"

		private static final def DIPLOMA_END              = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='diplomaEndYear']"

		private static final def UG_PERCENTGE             = ".//div[@class='edu-qual-container ']//input[@id='undergraduatePercentage']"

		private static final def UG_COLLEGE               = ".//div[@class='edu-qual-container ']//input[@id='undergraduateSchoolName']"

		private static final def UG_FIELD                 = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Field Of Study']"

		private static final def UG_START                 = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateStartYear']"

		private static final def UG_END                   = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateEndYear']"

		private static final def PG_PERCENTGE             = ".//div[@class='edu-qual-container ']//input[@id='postgraduatePercentage']"

		private static final def PG_COLLEGE               = ".//div[@class='edu-qual-container ']//input[@id='postgraduateSchoolName']"

		private static final def PG_SPECIALIZATION        = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Specialization']"

		private static final def PG_START                 = ".//div[@class='edu-qual-container ']//select[@name='postgraduateStartYear']	"

		private static final def PG_END                   = ".//div[@class='edu-qual-container ']//select[@name='postgraduateEndYear']"

		private static final def SCROLL                   = ".//*[@id='main_page']/div[2]/div/div[2]/div[3]/div/h4"

		private static final def ADDNEWCOURSELINK   	  = ".//a[@ng-click='addQualification(eduText)']"

		private static final def ADDNEWFIELDOFSTUDYLINK   = ".//a[@ng-click='addQualification(fieldOfStudyText)']"

		private static final def ADDNEWSPECIALIZATIONLINK = ".//a[@ng-click='addQualification(specializationText)']"

		private static final def CREATENEWCOURSE          = ".//li[@ng-click=\'\$mdAutocompleteCtrl.select(\$index)\'][@role='button']"

		private static final def CREATENEWFIELDOFSTUDY    = ".//span [@md-highlight-text='fieldOfStudyText']"

		private static final def CREATENEWSPECIALIZATION  = ".//span [@md-highlight-text='specializationText']"

		private static final def EXISTINGCOURSENAME       = ".//span[@md-highlight-text='eduText']/span[@class='highlight']"

		private static final def EXISTINGFIELDOFSTUDY     = ".//span[@md-highlight-text='fieldOfStudyText']"

		private static final def EXISTINGSPECIALIZATION   = ".//span[@md-highlight-text='specializationText']"

		private static final def UPDATE_BUTTON            = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[6]/div/button"

		private static final def FIELDS                   = [X_PERCENTAGE, X_SCHOOL_NAME, X_YEAR_PASS, XII_PERCENTAGE, XII_SCHOOL_NAME, XII_YEAR_PASS, DIPLOMA_PERCENTAGE, DIPLOMA_COLLEGE, DIPLOMA_COURSE, DIPLOMA_START, DIPLOMA_END, UG_PERCENTGE, UG_COLLEGE, UG_FIELD, UG_START, UG_END, PG_PERCENTGE, PG_COLLEGE, PG_SPECIALIZATION, PG_START, PG_END]

		private static final def FIELD_ERROR              = ".//*[@class='edu-qual-container ']//span[@class='error_message']/span[@aria-hidden='false']"

		private static final def FORM_ERROR               = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS     = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final EducationQualificationErrorMessageMap = [
			percent_req      		     :  'Percentage is required',
			invalid_percent 		     :  'Percentage is invalid.Use ex: 40, 56.23',
			schoolname_req  		     :  'School Name is required',
			year_req          			 :  'Year is required',
			collegename_req   			 :  'College Name is required',
			coursename_req  			 :  'Course Name is required.',
			startyear_req  				 :  'Start Year is required',
			endyear_req   				 :  'End Year is required',
			endyear_beforestart			 :  'End Year can\'t be Before Start Year',
			startendyear_same            :  'Start Year and End Year can\'t be Same',
			yearPassGreaterThanBornYear	 :	'Year of Pass should be greater than Born year',
			yearLessThanXYear			 :  'Year should be greater than the SSLC Passing Year',
			yearStartLessThanXYear		 :  'Year of Start should not be less than X Standard Passing year',
			nameofcoll_req     			 :  'Name of the College is required',
			fieldname_req     		     :  'Field Of Study is required.',
			specilization_req  			 :  'Specialization is required.',
			update_success    			 :  'User data updated successfully',
			startYearLessThanXiiDiploma  :  'Start Year shoud not be less than XII Standard/Diploma Pass Year',
			startYearLessThanUg          :  'start year should not be less than Undergraduate End year',
			update_success               :  'User data updated successfully']


		def static final populateFields = { browser, formData ->
			println ("EductionQualificationForm.populateFields - data: " + formData)
			browser.delay(4000)
			def outcome= WebForm.checkFormFieldsData(formData,FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
					def tagName= browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(DIPLOMA_PERCENTAGE) || FIELDS[i].equals(UG_COLLEGE)){
						browser.scrollToElement(browser.getElement(Browser.XPATH,FIELDS[i]))
					}
					WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
					if(FIELDS[i].equals(DIPLOMA_COURSE) && browser.isDisplayed(ADDNEWCOURSELINK)){
						browser.click(ADDNEWCOURSELINK)
						browser.delay(1000)
						browser.click(CREATENEWCOURSE)
					}else if(browser.isDisplayed(EXISTINGCOURSENAME)){
						browser.click(EXISTINGCOURSENAME)
					}
					if(FIELDS[i].equals(UG_FIELD) && browser.isDisplayed(ADDNEWFIELDOFSTUDYLINK)){
						browser.click(ADDNEWFIELDOFSTUDYLINK)
						browser.delay(1000)
						browser.click(CREATENEWFIELDOFSTUDY)
					}else if(browser.isDisplayed(EXISTINGFIELDOFSTUDY)){
						browser.click(EXISTINGFIELDOFSTUDY)
					}
					if(FIELDS[i].equals(PG_SPECIALIZATION) && browser.isDisplayed(ADDNEWSPECIALIZATIONLINK)){
						browser.click(ADDNEWSPECIALIZATIONLINK)
						browser.delay(1000)
						browser.click(CREATENEWSPECIALIZATION)
					}else if(browser.isDisplayed(EXISTINGSPECIALIZATION)){
						browser.click(EXISTINGSPECIALIZATION)
					}

					KPCommonPage.allEducationDetails.add(formData[i])
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, EducationQualificationErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			//browser.scrollToElement(browser.getElement(Browser.XPATH, X_PERCENTAGE))
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			browser.getValidationMessages errFields
		}
	}


	static final class WorkExperienceForm extends WebForm {

		//Work Experience form elements

		private static def INDUSTRY     		                 = "//div[@class='work-exp-container']//input[@class='ng-pristine ng-untouched ng-scope ng-valid ng-valid-required'][@aria-invalid='false']"//"//div[@class='work-exp-container']//input[@class='ng-pristine ng-untouched ng-scope ng-valid ng-valid-required']"//".//div[@class='work-exp-container']//md-autocomplete-wrap/input[@placeholder='Select Industry'][@aria-invalid='true']" //.//div[@class='work-exp-container']//input[@class='ng-pristine ng-untouched ng-scope ng-valid ng-valid-required']

		private static def INDUSTRY1							 = ".//div[@class='work-exp-container']//input[@name='industry0']"//".//div[@class='work-exp-container']//input[@class='ng-scope ng-valid ng-valid-required ng-dirty ng-valid-parse ng-touched']"

		private static final def CREATENEW_INDUSTRY_LINK         = ".//a[@ng-click='addIndustry(searchText)']"

		private static final def CREATENEW_INDUSTRY 			 = ".//span[@md-highlight-text='searchText']"

		private static final def INDUSTRY_AUTOCOMPLETE           = ".//md-autocomplete-parent-scope[@class='ng-scope']"

		private static final def JOB_ROLE     				     = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.jobRole'][@aria-invalid='true']"

		private static final def JOB_ROLE1     				     = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.jobRole'][@aria-invalid='false']"

		private static final def COMPANY_NAME  					 = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.companyName'][@aria-invalid='true']"

		private static final def COMPANY_NAME1 					 = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.companyName'][@aria-invalid='false']"

		private static final def TIMEPERIOD_STARTMONTH 		     = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.startMonth'][@aria-invalid='true']"

		private static final def TIMEPERIOD_STARTMONTH1 	     = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.startMonth'][@aria-invalid='false']"

		private static final def TIMEPERIOD_STARTYEAR	         = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.startYear'][@aria-invalid='true']"

		private static final def TIMEPERIOD_STARTYEAR1	         = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.startYear'][@aria-invalid='false']"

		private static final def TIMEPERIOD_ENDMONTH             = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.endMonth'][@aria-invalid='true']"

		private static final def TIMEPERIOD_ENDMONTH1            = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.endMonth'][@aria-invalid='false']"

		private static final def TIMEPERIOD_ENDYEAR   	         = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.endYear'][@aria-invalid='true']"

		private static final def TIMEPERIOD_ENDYEAR1             = ".//div[@class='work-exp-container']//select[@ng-model='userProfiles.endYear'][@aria-invalid='false']"

		private static final def CURRENTLY_WORKING_HERE 		 = ".//div[@class='work-exp-container']//md-checkbox[@type='checkbox']"

		private static final def DELETE_WORK_EXPERIENCE          = "//div[@class='work-exp-container']//a[contains(text(),'DELETE')]"

		private static final def DELETE_OPTION_NO 				 = ".//button[@class='md-primary md-cancel-button md-button ng-scope md-default-theme md-ink-ripple']"

		private static final def DELETE_OPTION_YES               =  ".//button[@ng-click='dialog.hide()']/span[@class='ng-binding ng-scope']"

		private static final def UPDATE_BUTTON                   = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def FIELDS 						 = [INDUSTRY, JOB_ROLE, COMPANY_NAME, TIMEPERIOD_STARTMONTH, TIMEPERIOD_STARTYEAR, TIMEPERIOD_ENDMONTH, TIMEPERIOD_ENDYEAR, CURRENTLY_WORKING_HERE]

		private static final def FIELDS1                         = [INDUSTRY1, JOB_ROLE1, COMPANY_NAME1, TIMEPERIOD_STARTMONTH1, TIMEPERIOD_STARTYEAR1, TIMEPERIOD_ENDMONTH1, TIMEPERIOD_ENDYEAR1, CURRENTLY_WORKING_HERE]

		private static final def FIELDS_AFTER_UNCHECK            = [TIMEPERIOD_ENDMONTH, TIMEPERIOD_ENDYEAR]

		public static def FIELDS_TO_ENTER						 = FIELDS
		// the error fields.

		public static def SUCCESS_MESSAGE 						 = ".//*[@id='main_page']/div[1]/div/span"

		private static final def FORM_ERROR                      = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR                     = ".//div[@class='work-exp-container']//span[@class='error_message']/span[@aria-hidden='false']"

		private static final def ERROR_MESSAGE_FIELDS            = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final workExperienceErrorMessageMap = [
			industry_req                  :  'Select Industry.',
			job_role_req		          :  'Job Role is required ',
			org_name_req  			      :  'Organization Name is required. ',
			start_month_req 			  :  'Start month is required',
			start_year_req  			  :  'Start year is required',
			end_month_req 			      :  'End month is required ',
			end_year_req    		      :  'End year is required',
			start_end_time_same			  :  'Start Time Period cannot be same as End Time Period',
			start_time_greater_end_time   :  'Start Time Period cannot be greater than End Time Period',
			update_success 				  :  'User data updated successfully',
			workExperienceRemoved_Success :  'User Experience removed successfully.']

		//To enter data
		def static final populateFields = { browser, formData ->
			browser.delay(4000)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS_TO_ENTER)
			if(outcome.isSuccess()){
				for(int j = 0; j < FIELDS_TO_ENTER.size(); j++){
					def ele = browser.isDisplayed(FIELDS_TO_ENTER[j])
					browser.delay(2000)
					def tagName = browser.getTagName(FIELDS_TO_ENTER[j])
					if(FIELDS_TO_ENTER[j].equals(INDUSTRY) ||FIELDS_TO_ENTER[j].equals(INDUSTRY1)){
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS_TO_ENTER[j]))
						browser.delay(500)
						if(formData[j] != ""){
							browser.populateField(FIELDS_TO_ENTER[j], formData[j])
							browser.delay(1000)
							if(browser.isDisplayed(CREATENEW_INDUSTRY_LINK)){
								browser.click(CREATENEW_INDUSTRY_LINK)
								browser.delay(500)
								browser.click(CREATENEW_INDUSTRY)
							}else{
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOCOMPLETE, formData[j].trim())
							}
						} else{
							browser.populateField(FIELDS_TO_ENTER[j], formData[j])
						}
					}else if(FIELDS_TO_ENTER[j].equals(CURRENTLY_WORKING_HERE)){

						if(formData[j].equals("1")){
							WebForm.inputData(browser,FIELDS_TO_ENTER[j],tagName,formData[j])
							KPCommonPage.WorkExperienceDetails.add("Till Date")
						}else if(formData[j].equals("0")){
							WebForm.inputData(browser,FIELDS_TO_ENTER[j],tagName,formData[j])
							browser.delay(2000)
							for(int k=2;k>=1;k--){
								browser.selectDropdownValue(FIELDS[j-k], formData[j-k])
								KPCommonPage.WorkExperienceDetails.add(formData[j-k].trim())
							}
						}
					}else if(FIELDS_TO_ENTER[j].equals(JOB_ROLE) || FIELDS_TO_ENTER[j].equals(JOB_ROLE1)){
						WebForm.inputData(browser,FIELDS_TO_ENTER[j],tagName,formData[j])
						KPCommonPage.WorkExperienceDetails.add(formData[j].trim())
						KPCommonPage.WorkExperienceJobRole.add(formData[j].trim())
					}else if(FIELDS_TO_ENTER[j].equals(TIMEPERIOD_ENDMONTH) || FIELDS_TO_ENTER[j].equals(TIMEPERIOD_ENDYEAR) || FIELDS_TO_ENTER[j].equals(TIMEPERIOD_ENDMONTH1) || FIELDS_TO_ENTER[j].equals(TIMEPERIOD_ENDYEAR1)){
						def fieldEnabled = browser.checkEnabled(FIELDS_TO_ENTER[j])
						if(formData[j]!="" && fieldEnabled == true){
							WebForm.inputData(browser,FIELDS_TO_ENTER[j],tagName,formData[j])
							KPCommonPage.WorkExperienceDetails.add(formData[j].trim())
						}
					}else{
						WebForm.inputData(browser,FIELDS_TO_ENTER[j],tagName,formData[j])
						KPCommonPage.WorkExperienceDetails.add(formData[j].trim())
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, workExperienceErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			browser.getValidationMessages errFields
		}

		def static deleteWorkExperience = { browser, formData ->
			browser.delay(1500)
			def actMessage
			def result
			def jobRoleElements = browser.getLists(JOB_ROLE1,"value")
			def deleteWorkExpList = browser.getListElements(DELETE_WORK_EXPERIENCE)
			for(int i=0;i<jobRoleElements.size();i++){
				if(jobRoleElements[i].equalsIgnoreCase(formData[0])){
					browser.clickElement(deleteWorkExpList[i])
					//browser.scrollToElement2(browser.getElement(Browser.XPATH,DELETE_OPTION_YES))
					browser.delay(2000)
					browser.click(DELETE_OPTION_YES)
					browser.delay(4000)
					actMessage = browser.gettext(SUCCESS_MESSAGE)
					if(browser.isDisplayed(SUCCESS_MESSAGE) && actMessage.equalsIgnoreCase(workExperienceErrorMessageMap.get('workExperienceRemoved_Success'))){
						result = true
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "workExperienceDeleteSuccessMessageIssue", "The delete success message was not displayed or the success message do not match")
					}
				}
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "workExperienceDeleteIssue", "Work Experience to delete was not displayed")
			}
		}

		def static editWorkExperienceDetails = { browser, formData ->
			KPCommonPage.WorkExperienceDetails.clear()
			if(browser.isDisplayed(JOB_ROLE1)){
				WorkExperienceForm.FIELDS_TO_ENTER = FIELDS1
				WorkExperienceForm.populateFields(browser, formData)
			}
		}
	}


	static final class SkillsForm extends WebForm {

		//Skills form elements

		private static final def SKILLS				       = ".//div[@class='skills-container']//input[@name='multipleSelectSkills']"

		private def static final SKILLS_ADD_NEW       	   = ".//*[@id='edit_profile_container']//form//multiple-autocomplete/div[2][@class='autocomplete-list']"

		private def static final SKILLS_AUTOSUGGEST        = "//div[@class='skills-container']//ul/li[@class='ng-binding ng-scope autocomplete-active']"

		private static final def UPDATE_BUTTON		       = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def SKILLS_TEXT			   = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[4]/div/div/div/div/div/multiple-autocomplete/div/ul/li/span/p"

		private static final def SKILLS_REMOVE_BUTTON      = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[4]/div/div/div/div/div/multiple-autocomplete/div/ul/li/span/span/i"

		private static final def SCROLL_TO_VIEW_SKILLS     = ".//ul[@class='footer-secondary-list']/li/h4[@class='heading']"

		private static final def SKILLS_ON_VIEW_PROFILE    = ".//li[@class='tagSkills prfleDetails capitalize ng-binding']"

		private static final def FIELDS = [SKILLS]

		// the error fields.
		private static final def FORM_ERROR = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final skillsErrorMessageMap = [update_success : "User data updated successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					def tagName = browser.getTagName(FIELDS[i])
					KPCommonPage.skills.clear()
					if(formData[i].contains(",")){
						def splittedValue = formData[i].split(",")
						for(int j=0;j<splittedValue.size();j++){
							WebForm.inputData(browser,FIELDS[i],tagName,splittedValue[j])
							if(browser.isDisplayed(SKILLS_AUTOSUGGEST)){
								browser.delay(500)
								browser.click(SKILLS_AUTOSUGGEST)
							}else if(browser.isDisplayed(SKILLS_ADD_NEW)){
								browser.delay(500)
								browser.click(SKILLS_ADD_NEW)
							}
							KPCommonPage.skills.add(splittedValue[j].trim())
						}
					}else{
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						if(browser.isDisplayed(SKILLS_AUTOSUGGEST)){
							browser.delay(500)
							browser.click(SKILLS_AUTOSUGGEST)
						}else if(browser.isDisplayed(SKILLS_ADD_NEW)){
							browser.delay(500)
							browser.click(SKILLS_ADD_NEW)
						}
						KPCommonPage.skills.add(formData[i].trim())
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, skillsErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(3000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}



		def static removeASkill = { browser, formData ->
			def result = false
			def allSkills = browser.getLists(SKILLS_TEXT)
			def allSkillEle = browser.getListElements(SKILLS_REMOVE_BUTTON)
			if(formData[0].equals("")){
				result = true
			}else if(formData[0].contains(",")){
				def splitFormData = formData[0].split(",")
				for(int i=0;i<allSkills.size();i++){
					for(int j=0;j<splitFormData.size();j++){
						if(splitFormData[j].equalsIgnoreCase(allSkills[i])){
							browser.clickElement(allSkillEle[i])
							result = true
						}
					}
				}
			}else {
				for(int i=0;i<allSkills.size();i++){
					for(int j=0;j<formData.size();j++){
						if(formData[j].equalsIgnoreCase(allSkills[i])){
							browser.clickElement(allSkillEle[i])
							result = true
						}
					}
				}
			}

			if(result){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "noSkillsDisplayed", "Skill to remove is not displayed.")
			}
		}


		def static final skillsRemoved = { browser, formData ->
			browser.delay(4000)
			def skillsNamesOnPage = []
			def skillValues
			browser.scrollToElement2(SCROLL_TO_VIEW_SKILLS)
			if(browser.isDisplayed(SKILLS_ON_VIEW_PROFILE)){
				skillsNamesOnPage = browser.getLists(SKILLS_ON_VIEW_PROFILE)
			}else
				return new SuccessOutcome()
			if(formData[0].contains(",")){
				skillValues = formData[0].split(",")
				if(!skillsNamesOnPage.containsAll(skillValues)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "SkillDisplayed", "Skill not removed after update.")
			}else if(!skillsNamesOnPage.contains(formData[0])){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "SkillDisplayed", "Skill not removed after update.")
		}

		def static deleteExistingSkills = { browser, formData ->
			browser.delay(1000)
			def result = false
			try{
				if(browser.isDisplayed(SKILLS_TEXT)){
					def skillsCountOnPage = browser.getListElements(SKILLS_REMOVE_BUTTON)
					for(int j=0;j<skillsCountOnPage.size();j++){
						browser.clickElement(skillsCountOnPage[j])
						browser.delay(1000)
						result = true
					}
				}
			}catch(Exception e){
				println "The exception is-----> "+e
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SkillDisplayIssue", "No skill displayed to delete")
			}
		}

		def static skillsRetained = { browser, formData ->
			browser.delay(4000)
			def formDataSkillss = []
			def skillsFromCommon = KPCommonPage.skills
			def skillsOnEditPage = browser.getLists(SKILLS_TEXT)
			for(int k=0;k<skillsOnEditPage.size();k++){
				formDataSkillss.add(skillsOnEditPage[k].trim())
			}
			if(skillsOnEditPage.containsAll(formDataSkillss)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "SkillsMismatchIssue", "Skills on View profile page do not match")
			}
		}
	}

	static final class CertificateForm extends WebForm {

		private static def CHOOSE_FILE_BUTTON           	= ".//label[@class='button-pri'][@for='uploadFilenum']"//.//div[@class='mb_10 ng-binding']/label[@class='button-pri'][@aria-hidden='false']

		private static final def CERTIFICATE_NAME      		= ".//div[@class='certificates-container']//input[@class='ng-pristine ng-untouched ng-scope ng-invalid ng-invalid-required']"//".//*[@class='certificates-container']/descendant::md-autocomplete-wrap/input"

		private static final def CERTIFICATE_NAME1			= ".//div[@class='certificates-container']//input[@placeholder='Enter Certificate Name'][@aria-invalid='false']"

		private static final def CREATENEW_CERTIFICATE_LINK = ".//a[@ng-click='addCertificate(searchText)']"

		private static final def CREATENEW_CERTIFICATE	    = ".//span[@md-highlight-text='searchText']"

		private static final def CERTIFICATE_AUTOCOMPLETE   = ".//md-autocomplete-parent-scope[@class='ng-scope']"

		private static final def DELETE_CERTIFICATE_LINK	= ".//div[@class='certificates-container']/descendant::a[contains(text(), 'DELETE')]"

		private static final def FORM_ERROR                 = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR                = "//div[@class='work-exp-container']//span[@class='error_message']/span"

		private static final def DELETE_OPTION_YES          =  ".//button[@ng-click='dialog.hide()']/span[@class='ng-binding ng-scope']"

		private static final def DELETE_OPTION_NO 			= ".//button[@class='md-primary md-cancel-button md-button ng-scope md-default-theme md-ink-ripple']"

		private static final def UPDATE_BUTTON              = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def DATA                       = ""

		private static final def FIELDS 			        = [DATA, CHOOSE_FILE_BUTTON, CERTIFICATE_NAME]

		private static final def FIELDS1					= [CERTIFICATE_NAME1]

		private static  def FIELDS_TO_ENTER1			    = FIELDS

		// the error fields.
		private static final def ERROR_MESSAGE_FIELDS       = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final certificateErrorMessageMap = [
			update_success   :"User data updated successfully",
			cert_req         : "Certificate is required",
			certName_req     : "Certificate Name is required",
			invalid_File     : "Not a valid file please upload supported file ex: doc, docx, pdf, rtf, jpeg, png ,jpg",
			invalidFile_Size : "File size should less then 2 MB.",
			docRemoveSuccess : "User Document removed successfully."
		]

		//To enter data
		def static final populateFields = { browser, formData ->
			browser.delay(1500)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS_TO_ENTER1)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS_TO_ENTER1.size(); i++){
					def tagName = browser.getTagName(FIELDS_TO_ENTER1[i])


					if(FIELDS_TO_ENTER1[i].equalsIgnoreCase(CHOOSE_FILE_BUTTON)){
						if(formData[i]!=null && !formData[i].equals("")){
							def newXpath = CHOOSE_FILE_BUTTON.replace("num",formData[0])
							browser.click(newXpath)
							browser.uploadFile(browser.getCurrentDirectory()+formData[i])
							browser.delay(500)
						}
					}else if(FIELDS_TO_ENTER1[i].equalsIgnoreCase(CERTIFICATE_NAME) || FIELDS_TO_ENTER1[i].equalsIgnoreCase(CERTIFICATE_NAME1)){
						browser.delay(3000)
						WebForm.inputData(browser,FIELDS_TO_ENTER1[i],tagName,formData[i])
						if(browser.isDisplayed(CREATENEW_CERTIFICATE_LINK)){
							browser.click(CREATENEW_CERTIFICATE_LINK)
							browser.delay(500)
							browser.click(CREATENEW_CERTIFICATE)
							browser.delay(500)
							KPCommonPage.docName.add(formData[i].trim())
						}else{
							KPCommonPage.selectAutoComplete(browser, CERTIFICATE_AUTOCOMPLETE, formData[i].trim())
							KPCommonPage.docName.add(formData[i].trim())
						}
					}else{
						println "Comes here"
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, certificateErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static editDocumentsDetails = { browser,formData ->
			KPCommonPage.docName.clear()
			browser.delay(2000)
			if(browser.isDisplayed(CERTIFICATE_NAME1)){
				FIELDS_TO_ENTER1 = FIELDS1
				CertificateForm.populateFields(browser, formData)
				FIELDS_TO_ENTER1 = FIELDS
			}
		}

		def static deleteCertificate = { browser,formData ->
			browser.delay(2000)
			def certificateDeletelink = browser.getListElements(DELETE_CERTIFICATE_LINK)
			def certificateToDelete = browser.getLists(CERTIFICATE_NAME1,"value")
			for(int i=0;i<certificateToDelete.size();i++){
				if(certificateToDelete[i].trim().equalsIgnoreCase(formData[0].trim())){
					browser.clickElement(certificateDeletelink[i])
					browser.delay(2000)
					browser.click(DELETE_OPTION_YES)
					browser.delay(1000)
					def deleteDocMessage = browser.gettext(".//*[@id='main_page']/div[1]/div/span")
					def actualUploadMsg = certificateErrorMessageMap.get('docRemoveSuccess')
					if(actualUploadMsg.equalsIgnoreCase(deleteDocMessage)){
						browser.delay(5000)
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "CertificateDeleteMessageMismatchIssue", "Delete certificate messgae do not match")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "NoCertificateToDelete", "Certificate to delete was not found")
				}
			}
		}
	}
}
