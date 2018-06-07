package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 28-04-2018
 */

final class RePostJobPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		new RePostJobForm().populateFields(browser, formData);
	}

	//Override
	def submit = { browser, formKey, formData ->
		new RePostJobForm().submit(browser, formData)
	}

	def static getRepostJobFieldDetails = { browser, formData ->
		new RePostJobForm().getRepostJobFieldDetails(browser, formData)
	}

	def static ifRepostJobHasSameDetails = { browser, formData ->
		new RePostJobForm().ifRepostJobHasSameDetails(browser, formData)
	}

	def static changeJobListClearStatus = { browser, formData ->
		new RePostJobForm().changeJobListClearStatus(browser, formData)
	}

	static final class RePostJobForm extends WebForm {

		//Job post form elements
		private static final def JOB_TITLE                    = "//input[@aria-label='Enter Job Title']"

		private static final def NO_OF_POSITIONS              = ".//*[@id='noOfPositions']"

		private static final def MIN_EXPERIENCE               = ".//*[@id='minExperience']"

		private static final def MAX_EXPERIENCE               = ".//*[@id='maxExperience']"

		private static final def MIN_SALARY                   = ".//*[@id='minSalary']"

		private static final def MAX_SALARY                   = ".//*[@id='maxSalary']"

		private static final def INDUSTRY                     = "//input[@aria-label='Select Industry']"

		private static final def LOCATION                     = ".//*[@id='jobLocation']"

		private static final def JOB_TYPE                      = ".//select[@name='jobType']//option[@selected='selected']"

		private static final def JOB_LAST_DATE 				   = ".//*[@id='jobLastDate']"

		private static final def JOB_POSTING_DATE 			   = ".//*[@id='jobPostingDate']"

		private static final def JOB_EXPIRY_DATE			   = ".//*[@id='jobExpiryDate']"

		private static final def JOB_DESCRIPTION               = ".//*[@id='jobDescription']"

		private static final def ADD_BUTTON                    = ".//button[text()='Add']"

		private static final def EDUCATION_DETAIL              = ".//input[@placeholder='Enter Education Qualification']/following-sibling::ul//p"

		private static final def SKILLS_DETAIL                 = ".//input[@placeholder='Enter Skills']/following-sibling::ul//p"

		private static final def DATA_FIELDS = [JOB_TITLE, NO_OF_POSITIONS, MIN_EXPERIENCE, MAX_EXPERIENCE, MIN_SALARY, MAX_SALARY, INDUSTRY, LOCATION, JOB_TYPE, EDUCATION_DETAIL, SKILLS_DETAIL, JOB_DESCRIPTION]

		private static final def FIELDS = [JOB_LAST_DATE, JOB_POSTING_DATE, JOB_EXPIRY_DATE]

		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"

		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1, FIELD_ERROR_2]

		//error message map (Key-Value Pair)
		def static final PostJobPageErrorMessageMap = [success_RepostJob           :   'Job Reposted Successfully']

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				KPCommonPage.jobDetailList.clear()
				for(int i = 0; i < FIELDS.size(); i++){
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
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton
			}
			browser.delay(2000)
			browser.scrollToElement2(".//label[@for='jobtitle']")
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static getRepostJobFieldDetails = { browser, formData ->
			browser.delay(1000)
			def value
			if(KPCommonPage.clearJobDetailList){
				for(int i=0;i<KPCommonPage.jobDetailList.size();i++){
					KPCommonPage.repostJobFieldsData.add(KPCommonPage.jobDetailList[i])
				}
				KPCommonPage.jobDetailList.clear()
				KPCommonPage.clearJobDetailList =  false
			}
			for(int k=0;k<DATA_FIELDS.size();k++){
				if(DATA_FIELDS[k].equals(JOB_TITLE)){
					KPCommonPage.jobTitle = browser.gettext(DATA_FIELDS[k],"value").trim()
				}else if(DATA_FIELDS[k].equals(INDUSTRY)){
					KPCommonPage.jobDetailList.add(browser.gettext(DATA_FIELDS[k],"value").trim())
				}else if(DATA_FIELDS[k].equals(LOCATION)){
					value = browser.gettext(DATA_FIELDS[k],"value").trim()
					if(value.contains(",")){
						KPCommonPage.jobDetailList.add(value.split(",")[0].trim())
					}
				}else if(DATA_FIELDS[k].equals(EDUCATION_DETAIL)){
					def educationValues = browser.getLists(EDUCATION_DETAIL)
					for(int l=0;l<educationValues.size();l++){
						KPCommonPage.jobDetailList.add(educationValues[l].trim())
					}
				}else if(DATA_FIELDS[k].equals(SKILLS_DETAIL)){
					def skillValues = browser.getLists(SKILLS_DETAIL)
					for(int m=0;m<skillValues.size();m++){
						KPCommonPage.jobDetailList.add(skillValues[m].trim())
					}
				}else if(DATA_FIELDS[k].equals(JOB_TYPE)){
					KPCommonPage.jobDetailList.add(browser.getDropdownValue(JOB_TYPE).trim())
				}else if(DATA_FIELDS[k].equalsIgnoreCase(MIN_SALARY) || DATA_FIELDS[k].equalsIgnoreCase(MAX_SALARY)){
					def salary = browser.gettext(DATA_FIELDS[k],"value")
					if(salary.length()<=4){
						salary = Float.parseFloat(salary)/1000
						KPCommonPage.jobDetailList.add(salary.toString().trim()+"0K")
					}
					else if(salary.length()==5){
						salary = Float.parseFloat(salary)/10000
						KPCommonPage.jobDetailList.add(salary.toString().trim()+"0K")
					}
					else if(salary.length()==6){
						salary = Float.parseFloat(salary)/100000
						KPCommonPage.jobDetailList.add(salary.toString().trim()+"0L")
					}
				}else if(DATA_FIELDS[k].equalsIgnoreCase(MAX_EXPERIENCE)){
					KPCommonPage.jobDetailList.add(browser.gettext(DATA_FIELDS[k],"value").trim()+" years")
				}else{
					KPCommonPage.jobDetailList.add(browser.gettext(DATA_FIELDS[k],"value").trim())
				}
			}
			return new SuccessOutcome()
		}

		def static changeJobListClearStatus = { browser, formData ->
			KPCommonPage.clearJobDetailList = true
			return new SuccessOutcome()
		}

		def static ifRepostJobHasSameDetails = { browser, formData ->
			def result = false
			println "KPCommonPage.jobDetailList-----------------> "+KPCommonPage.jobDetailList
			println "KPCommonPage.jobDetailList-----------------> "+KPCommonPage.jobDetailList.size()
			println "KPCommonPage.repostJobFieldsData-----------> "+KPCommonPage.repostJobFieldsData
			println "KPCommonPage.repostJobFieldsData-----------> "+KPCommonPage.repostJobFieldsData.size()
			for(int i=0;i<KPCommonPage.repostJobFieldsData.size();i++){
				if(KPCommonPage.jobDetailList.contains(KPCommonPage.repostJobFieldsData.get(i))){
					result = true
				}
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "RepostJobDataMismatchIssue", "The data on repost job page do not match")
			}
		}
	}
}
