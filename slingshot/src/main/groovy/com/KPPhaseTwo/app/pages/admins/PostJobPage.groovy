package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 03-01-2018
 */

final class PostJobPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		new PostJobForm().populateFields(browser, formData);
	}

	//Override
	def submit = { browser, formKey, formData ->
		new PostJobForm().submit(browser, formData)
	}

	def static changeSelectNextDateStatus = { browser, formData ->
		new PostJobForm().changeSelectNextDateStatus(browser, formData)
	}

	static final class PostJobForm extends WebForm {

		//Job post form elements
		private static final def JOB_TITLE                    = "//input[@aria-label='Enter Job Title']"

		private static final def JOB_TITLE_LIST               = ".//ul[@class='md-autocomplete-suggestions']//span[@md-highlight-text='searchText']"

		private static final def CREATENEW_JOB_LINK           = ".//li[@ng-if='\$mdAutocompleteCtrl.notFoundVisible()']/a"

		private static final def CREATENEW_JOB 		          = ".//span[@md-highlight-text='searchText']/span"

		private static final def CREATENEW_INDUSTRY_LINK      = ".//a[@ng-click='addIndustry(industrySearchText)']"

		private static final def SELECT_INDUSTRY_LIST 	      = ".//span[@md-highlight-text='industrySearchText']"

		private static final def INDUSTRY_AUTOCOMPLETE        = ".//md-autocomplete-parent-scope[@class='ng-scope']"

		private static final def AUTOCOMPLETE                 = ".//div[@class='autocomplete-list']/ul/li"

		private static final def NO_OF_POSITIONS              = ".//*[@id='noOfPositions']"

		private static final def MIN_AGE                      = ".//input[@id='minAge']"

		private static final def MAX_AGE                      = ".//input[@id='maxAge']"

		private static final def MIN_EXPERIENCE               = ".//*[@id='minExperience']"

		private static final def MAX_EXPERIENCE               = ".//*[@id='maxExperience']"

		private static final def MIN_SALARY                   = ".//*[@id='minSalary']"

		private static final def MAX_SALARY                   = ".//*[@id='maxSalary']"

		private static final def INDUSTRY                     = "//input[@aria-label='Select Industry']"

		private static final def LOCATION                     = ".//*[@id='jobLocation']"

		private static final def LOCATION_LIST                = ".//div[@class='pac-container pac-logo']/div/span[2]/span[@class='pac-matched']"//".//div[@class='pac-item']"//".//div[@class='pac-item']//span[@class='pac-matched']" //".//div[@class='pac-item']//span[@class='pac-matched']"//

		private static final def INDUSTRY_AUTOSELECT           = "html/body/md-virtual-repeat-container[2]/div//li"

		private static final def JOB_TYPE                      = ".//select[@name='jobType']"

		private static final def JOB_LAST_DATE 				   = ".//*[@id='jobLastDate']"

		private static final def JOB_POSTING_DATE 			   = ".//*[@id='jobPostingDate']"

		private static final def JOB_EXPIRY_DATE			   = ".//*[@id='jobExpiryDate']"

		private static final def EDU_QUALIFICATION			   = ".//input[@placeholder='Enter Education Qualification']"

		private static final def EDU_QUALIFICATION_AUTOSELECT  = "//div[@class='autocomplete-list']/ul/li"

		private static final def SKILLS                        = ".//input[@placeholder='Enter Skills']"

		private static final def SKILLS_LIST                   = "//div[@class='autocomplete-list']/ul/li"

		private static final def PRE_CERTIFICATE               = ".//input[@placeholder='Enter Preferred Certificates']"

		private static final def PRE_CERTIFICATE_LIST          = "//div[@class='autocomplete-list']/ul/li"

		private static final def JOB_DESCRIPTION               = ".//*[@id='jobDescription']"

		private static final def ADD_BUTTON                    = ".//button[text()='Add']"

		private static final def JOBPOSTING_LIST               = ".//div[@ng-repeat='job in jobPostingsList']//h3"

		private static final def JOBSEARCH_TEXTBOX             = ".//input[@class='form-control pl_30 pr_30 ng-pristine ng-untouched ng-valid'][@placeholder='Search']"

		private static final def AUTO_CITY_LIST                = ".//div[@class='pac-container pac-logo'][last()]/descendant::span[@class='pac-matched']"

		private static final def REMOVE_LINK                   = ".//span[@class='remove']"

		private static final def FIELDS = [JOB_TITLE, NO_OF_POSITIONS, MIN_AGE, MAX_AGE, MIN_EXPERIENCE, MAX_EXPERIENCE, MIN_SALARY, MAX_SALARY, INDUSTRY, LOCATION, JOB_TYPE, JOB_LAST_DATE, JOB_POSTING_DATE, JOB_EXPIRY_DATE, EDU_QUALIFICATION, SKILLS, PRE_CERTIFICATE, JOB_DESCRIPTION]

		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"

		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1, FIELD_ERROR_2]

		//error message map (Key-Value Pair)
		def static final PostJobPageErrorMessageMap = [
			jobtitle_req              :  'Select Job Title.',
			noofpositions_req         :  'No of positions is required.',
			minage_req                :  'Min age is required.',
			maxage_req                :  'Max age is required.',
			minexperience_req         :  'Min experience is required.',
			maxexperience_req         :  'Max experience is required.',
			minsalary_req             :  'Min salary is required.',
			maxsalary_req             :  'Max salary is required.',
			industry_req              :  'Industry is required',
			location_req              :  'Location is required.',
			jobtype_req               :  'Job Type is required',
			joblastdate_req           :  'Job last date is required.',
			jobpostdate_req           :  'Date of Job posting is required.',
			jobexpirydate_req         :  'Expiry date of Job posting required.',
			eduqualification_req      :  'Education Qualification is required',
			skill_req                 :  'Skills is required',
			jobdesc_req               :  'Job Description is required.',
			lastAfterPosting_date     :  'Last date of application should be after Date of Job Posting',
			lastAfterExpiry_date      :  'Last date of application can\'t be after Expiry Date of Job Posting',
			postingBeforeExpiry_date  :  'Date of Job Posting should be before Expiry of Job Posting',
			jobpostingBeforeLast_date :  'Date of Job Posting should be before Last Date of Job Application',
			expiryAfterPosting_date   :  'Expiry of Job Posting should be after Date of Job Posting',
			expiryAfterLast_date      :  'Expiry of Job Posting should be after Last Date of job Application',
			minAgeGreaterMax          :  'Min age can\'t be greater than max age',
			minExpGreaterMax          :  'Min experience can\'t be greater than max experience',
			minSalGreaterMax          :  'Min salary can\'t be greater than max salary',
			success_postJob           :   'Job added successfully']

		//To enter data
		def static final populateFields = { browser, formData ->
			def text
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				KPCommonPage.jobDetailList.clear()
				/*if(browser.isDisplayed(REMOVE_LINK)){
					def removeEle = browser.getListElements(REMOVE_LINK)
					for(int k=0;k<removeEle.size();k++){
						browser.clickElement(removeEle[k])
						browser.delay(1500)
					}
				}*/
				for(int i = 0; i < FIELDS.size(); i++){
					if(FIELDS[i].equals(JOB_TITLE)){
						browser.scrollToElement2(JOB_TITLE)
						browser.delay(500)
						if(formData[i] != ""){
							text = KPCommonPage.generateRandomString()
							formData[i] = formData[i]+text
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(1000)
							KPCommonPage.jobTitle = formData[i].trim()
							if(browser.isDisplayed(CREATENEW_JOB_LINK)){
								browser.click(CREATENEW_JOB_LINK)
								browser.delay(1000)
								browser.click(CREATENEW_JOB)
								browser.delay(1000)
							}else{
								KPCommonPage.selectAutoComplete(browser, JOB_TITLE_LIST, formData[i].trim())
							}
						}else{
							browser.populateField(FIELDS[i], formData[i])
						}
					}
					else if(FIELDS[i].equals(INDUSTRY)){
						browser.scrollToElement2(INDUSTRY)
						browser.delay(500)
						if(formData[i] != ""){
							KPCommonPage.jobDetailList.add(formData[i].trim())
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(1000)
							if(browser.isDisplayed(CREATENEW_INDUSTRY_LINK)){
								browser.click(CREATENEW_INDUSTRY_LINK)
								browser.delay(500)
								browser.click(SELECT_INDUSTRY_LIST)
							}else{
								KPCommonPage.selectAutoComplete(browser, SELECT_INDUSTRY_LIST, formData[i].trim())
							}
						}else{
							browser.populateField(FIELDS[i], formData[i])
						}
					}
					else if(FIELDS[i].equals(LOCATION) && formData[i]!= ""){
						def newlist
						browser.delay(1000)
						browser.populateField(LOCATION,formData[i])
						KPCommonPage.jobDetailList.add(formData[i].trim())
						browser.delay(3000)
						KPCommonPage.selectAutoComplete(browser, AUTO_CITY_LIST, formData[i].trim())
						browser.delay(2000)
					}
					else if(FIELDS[i].equals(JOB_LAST_DATE) || FIELDS[i].equals(JOB_POSTING_DATE) || FIELDS[i].equals(JOB_EXPIRY_DATE)){
						if(formData[i]!=""){
							if(FIELDS[i].equals(JOB_POSTING_DATE)){
								if(KPCommonPage.selectNextDateStatus == true){
									formData[i] = KPCommonPage.getFutureDate(Integer.parseInt(formData[i].trim()))
									if(formData[i].contains("-")){
										def nextDate = formData[i].split("-")
										for(int j=0;j<nextDate.size();j++){
											KPCommonPage.jobDetailList.add(nextDate[j].trim())
										}
									}
									KPCommonPage.selectNextDateStatus = false
								}else{
									def currentDate = KPCommonPage.getCurrentDate()
									formData[i] = currentDate.trim()
									KPCommonPage.jobDetailList.add("Posted today")
								}
							}else if(FIELDS[i].equals(JOB_LAST_DATE)){
								formData[i] = KPCommonPage.getFutureDate(Integer.parseInt(formData[i].trim()))
								if(formData[i].contains("-")){
									def data = formData[i].split("-")
									for(int j=0;j<data.size();j++){
										KPCommonPage.jobDetailList.add(data[j].trim())
									}
								}
							}else if(FIELDS[i].equals(JOB_EXPIRY_DATE)){
								formData[i] = KPCommonPage.getFutureDate(Integer.parseInt(formData[i].trim()))
								if(formData[i].contains("-")){
									def data = formData[i].split("-")
									for(int j=0;j<data.size();j++){
										KPCommonPage.jobDetailList.add(data[j].trim())
									}
								}
							}
							browser.click FIELDS[i]
							browser.delay(600)
							KPCommonPage.datePicker(browser,formData[i])
							browser.delay(500)
						} else {
							browser.click FIELDS[i]
						}
					}
					else if(FIELDS[i].equals(EDU_QUALIFICATION) || FIELDS[i].equals(SKILLS) ||  FIELDS[i].equals(PRE_CERTIFICATE)){
						if(!formData[i].equals("")){
							if(formData[i].contains(",")){
								def splittedValue = formData[i].split(",")
								for(int j=0;j<splittedValue.size();j++){
									new PostJobForm().selectSuggestion(browser,FIELDS[i],splittedValue[j].trim())
									if(!FIELDS[i].equals(PRE_CERTIFICATE)){
										KPCommonPage.jobDetailList.add(splittedValue[j].trim())
									}
								}
							}else{
								new PostJobForm().selectSuggestion(browser,FIELDS[i],formData[i].trim())
								if(!FIELDS[i].equals(PRE_CERTIFICATE)){
									KPCommonPage.jobDetailList.add(formData[i].trim())
								}
							}
						}else{
							browser.populateField(FIELDS[i],formData[i])
						}
					}else{
						if(FIELDS[i].equalsIgnoreCase(MIN_EXPERIENCE) && formData[i]!=""){
							KPCommonPage.jobDetailList.add(formData[i].trim())
						}else if(FIELDS[i].equalsIgnoreCase(MAX_EXPERIENCE) && formData[i]!=""){
							KPCommonPage.jobDetailList.add(formData[i].trim()+" years")
						}else if(FIELDS[i].equalsIgnoreCase(MIN_SALARY) || FIELDS[i].equalsIgnoreCase(MAX_SALARY) && formData[i]!=""){
							def salary
							if(formData[i].length()<=4){
								salary = ((Float.parseFloat(formData[i]))/1000)
								KPCommonPage.jobDetailList.add(salary.toString().trim()+"0K")
							}
							else if(formData[i].length()==5){
								salary = ((Float.parseFloat(formData[i]))/10000)
								KPCommonPage.jobDetailList.add(salary.toString().trim()+"0K")
							}
							else if(formData[i].length()==6){
								salary = ((Float.parseFloat(formData[i]))/100000)
								KPCommonPage.jobDetailList.add(salary.toString().trim()+"0L")
							}
						}else if(FIELDS[i].equalsIgnoreCase(NO_OF_POSITIONS) && formData[i]!=""){
							KPCommonPage.jobDetailList.add(formData[i].trim())
						}else if(FIELDS[i].equalsIgnoreCase(JOB_TYPE) && formData[i]!=""){
							KPCommonPage.jobDetailList.add(formData[i].trim())
						}else if(FIELDS[i].equalsIgnoreCase(JOB_DESCRIPTION) && formData[i]!=""){
							KPCommonPage.jobDetailList.add(formData[i].trim())
						}
						def tagName = browser.getTagName(FIELDS[i])
						browser.scrollToElement2(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
					}
				}
			}
			println "KPCommonPage.jobDetailList------in Post Job page-------SIZE----> "+KPCommonPage.jobDetailList.size()
			println "KPCommonPage.jobDetailList------in Post Job page--------    ---> "+KPCommonPage.jobDetailList
			return outcome
		}

		def static selectSuggestion(def browser,def fieldToPopulate, def value){
			browser.populateField(fieldToPopulate,value)
			if(browser.isDisplayed(AUTOCOMPLETE)){
				browser.delay(500)
				browser.click(AUTOCOMPLETE)
				browser.delay(500)
			}
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
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton
			}
			browser.delay(2000)
			browser.scrollToElement2(".//label[@for='jobtitle']")
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static changeSelectNextDateStatus = { browser, formData ->
			KPCommonPage.selectNextDateStatus = true
			return new SuccessOutcome()
		}
	}
}