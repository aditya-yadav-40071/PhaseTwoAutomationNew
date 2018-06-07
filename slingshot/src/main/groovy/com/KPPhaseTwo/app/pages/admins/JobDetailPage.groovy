package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class JobDetailPage extends WebPage {

	/** 
	 * @author Aditya
	 * To match the job details with the details of the job which was posted by organization admin
	 */
	def static jobDetails = { browser, formData ->
		new JobDetailForm().jobDetails browser ,formData
	}

	/**
	 * @author Aditya
	 *  To apply for the job by enrolled user or Individual user
	 */
	def static applyForJob = { browser, formData ->
		new JobDetailForm().applyForJob browser ,formData
	}

	static final class JobDetailForm extends WebForm {

		private static final def JOB_TITLE 			   = ".//h4[@class='content-name job-title blue mb10 ng-binding']"

		private static final def JOB_LOCATION          = ".//div[@class='col-xs-12 col-md-12 smallText created-by-label']/span[@class='pull-left ng-binding']"

		private static final def JOB_OPENINGS          = ".//div[@class='col-xs-12 col-md-4 smallText created-by-label ng-binding']"

		private static final def JOB_DESCRIPTION       = ".//span[@class='job-description smallText ng-binding ng-scope']"

		private static final def EXP_AND_COMP_LIST     = ".//div[@class='col-xs-12 col-md-4 smallText created-by-label']/span"

		private static final def ALL_JOBDATES          = ".//div[@class='col-xs-12 col-sm-4 col-md-4 certified-by smallText ng-binding']"

		private static final def OTHER_JOB_DETAILS     = ".//div[@class='col-xs-12 col-md-9 text-justify content-description smallText']"

		private static final def JOB_APPLY_BUTTON      = ".//button[@ng-click='showModal(\$event)']"

		private static final def JOB_SUCCESS_MESSAGE   = ".//div[@class='md-dialog-content-body ng-scope']/p"

		/**
		 * @author Aditya
		 *To match the job details with the details of the job which was posted by organization admin
		 */
		def static jobDetails = { browser, formData ->
			browser.delay(1000)
			def tempList = []
			def actualJobDetail = []
			def result = false
			def actualSortedJobDetail
			def expectedSortedJobDetail
			browser.scrollToElement2("//a[text()='Overview']")
			browser.delay(1000)
			def jobTitle = browser.gettext(JOB_TITLE).trim()
			if(jobTitle.equalsIgnoreCase(KPCommonPage.jobTitle)){
				tempList.add(browser.gettext(JOB_LOCATION))
				tempList.add(browser.gettext(JOB_OPENINGS))
				tempList.add(browser.gettext(JOB_DESCRIPTION))
				tempList.addAll(browser.getLists(EXP_AND_COMP_LIST))
				tempList.addAll(browser.getLists(ALL_JOBDATES))
				tempList.addAll(browser.getLists(OTHER_JOB_DETAILS))
				for(int i=0;i<tempList.size();i++){
					if(tempList[i].contains(":") && tempList[i].contains("-")){
						def temp = tempList[i].split(":")[1].trim()
						temp = temp.split("-")
						for(int j=0;j<temp.size();j++){
							actualJobDetail.add(temp[j].trim())
						}
					}else if(tempList[i].contains(":") && !tempList[i].contains("-")){
						actualJobDetail.add(tempList[i].split(":")[1].trim())
					}else if(tempList[i].contains(",")){
						def data = tempList[i].split(",")
						for(int k=0;k<data.size();k++){
							actualJobDetail.add(data[k].trim())
						}
					}else{
						actualJobDetail.add(tempList[i].trim())
					}
				}
				actualSortedJobDetail = actualJobDetail.sort()
				expectedSortedJobDetail = KPCommonPage.jobDetailList.sort()
				if(actualSortedJobDetail.size() == expectedSortedJobDetail.size()){
					for(int l=0;l<actualSortedJobDetail.size();l++){
						if(actualSortedJobDetail[l].equalsIgnoreCase(expectedSortedJobDetail[l])){
							result = true
						}else{
							result
							break
						}
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "actaulExpectedListSizeIssue", "The actual and expected list size do not match")
				}

				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "JobDetailsMismatchIssue", "The job details do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobTitleMismatchIssue", "The job title do not match")
			}
		}

		
		/**
		 * @author Aditya
		 * To apply for the job by enrolled user or Individual user
		 */
		def static applyForJob = { browser, formData ->
			if(browser.checkEnabled(JOB_APPLY_BUTTON)){
				browser.click(JOB_APPLY_BUTTON)
				browser.delay(1000)
				def successMessage = browser.gettext(JOB_SUCCESS_MESSAGE).trim()
				if(browser.isDisplayed(JOB_SUCCESS_MESSAGE) && successMessage.equalsIgnoreCase("Hello!! Your Application has been sent!! All the best!!")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "JobApplySuccessMessage", "The job applied success message was not displayed or did not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobApplyButtonEnableiSSUE", "The job apply button is not enabled")
			}
		}
	}
}

