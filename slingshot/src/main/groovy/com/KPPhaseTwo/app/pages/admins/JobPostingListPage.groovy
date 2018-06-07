package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 20-04-2018
 */

final class JobPostingListPage extends WebPage {

	/**
	 * @author aditya
	 * Correct job displayed after job is posted on the job posting list page
	 */
	def static correctJobDisplayed = { browser, formData ->
		new JobPostingListForm().correctJobDisplayed browser ,formData
	}

	/**
	 * @author aditya
	 * To click  on the first job on the job post list page
	 */
	def static clickOnFirstJob = { browser,formData->
		new JobPostingListForm().clickOnFirstJob browser ,formData
	}

	/**
	 * @author aditya
	 * To click on the close job link of the first job  on the job post list page
	 */
	def static closeFirstJob = { browser, formData ->
		new JobPostingListForm().closeFirstJob(browser, formData)
	}

	/**
	 * @author aditya
	 * To check the first job status is closed after closing the job
	 */
	def static ifJobStatusIsClosed = { browser, formData ->
		new JobPostingListForm().ifJobStatusIsClosed(browser, formData)
	}

	/**
	 * @author aditya
	 * To search for the posted job
	 */
	def static searchforPostedJob = { browser, formData ->
		new JobPostingListForm().searchforPostedJob(browser, formData)
	}

	/**
	 * @author aditya
	 * To check if the closed job is not displayed in the Recommended job list for enrolled or Individual User
	 */
	def static ifClosedJobNotDisplayed = { browser, formData ->
		new JobPostingListForm().ifClosedJobNotDisplayed(browser, formData)
	}

	/**
	 * @author aditya
	 * To check if the job status is not publised when job posting is greater than the current date on job posting list page
	 */
	def static ifJobStatusIsNotPublished = { browser, formData ->
		new JobPostingListForm().ifJobStatusIsNotPublished(browser, formData)
	}

	/**
	 * @author aditya
	 * To check if the job status is publised when job posting is current date on job posting list page
	 */
	def static ifJobStatusIsPublished = { browser, formData ->
		new JobPostingListForm().ifJobStatusIsPublished(browser, formData)
	}

	static final class JobPostingListForm extends WebForm {

		private static final def SORT_BY_FILTER          = ".//select[@ng-model='functionCall.sortBy']"

		private static final def JOBPOSTING_LIST         = ".//h3[@role='button']"

		private static final def FIRST_JOB_TITLE         = ".//div[@ng-repeat='job in jobPostingsList'][1]//h3"

		private static final def JOB_EDIT_LINK           = ".//div[@ng-repeat='job in jobPostingsList'][1]//a[1]"

		private static final def JOB_REPOST_LINK         = ".//div[@ng-repeat='job in jobPostingsList'][1]//a[2]"

		private static final def JOB_CLOSE_LINK          = ".//div[@ng-repeat='job in jobPostingsList'][1]//a[3]"

		private static final def CLOSEJOB_YES            = "//button[@ng-click='dialog.hide()']"

		private static final def FIRST_JOB_STATUS              = ".//div[@ng-repeat='job in jobPostingsList'][1]//b[@aria-hidden='false']"

		private static final def JOBSEARCH_TEXTBOX       = ".//div[@class='col-md-12 resp_pr_15 pr_15 visible-lg']//input"

		private static final def NO_RECOMMENDEDJOBS_MSG   = ".//div[@class='col-xs-12 col-md-12 clearfix content mt20 text_center ng-binding']"

		/**
		 * @author aditya
		 * Correct job displayed after job is posted on the job posting list page
		 */
		def static correctJobDisplayed = { browser, formData ->
			def result = new JobPostingListForm().getFirstJobName(browser)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobPostIssue", "Job posted is not at the first position")
			}
		}

		/**
		 * @author aditya
		 * Get the first name of the job on the job posting list page 
		 */
		def static getFirstJobName(def browser){
			browser.delay(2000)
			browser.selectDropdownValue(SORT_BY_FILTER,"Latest")
			browser.delay(2000)
			if(browser.isDisplayed(FIRST_JOB_TITLE)){
				def firstJobName = browser.gettext(FIRST_JOB_TITLE).trim()
				if(firstJobName.equalsIgnoreCase(KPCommonPage.jobTitle.trim())){
					return true
				}else{
					return false
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FirstJobDisplayIssue", "No jobs were displayed")
			}
		}

		/**
		 * @author aditya
		 * To click  on the first job on the job post list page
		 */
		def static clickOnFirstJob = { browser,formData ->
			def result
			result = new JobPostingListForm().getFirstJobName(browser)
			if(result){
				browser.click(FIRST_JOB_TITLE)
				browser.delay(1000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobClickIssue", "Unable to click on the job link")
			}
		}

		/**
		 * @author aditya
		 * To click on the close job link of the first job  on the job post list page
		 */
		def static closeFirstJob = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(FIRST_JOB_TITLE)){
				def firstJobText =  browser.gettext(FIRST_JOB_TITLE).trim()
				if(firstJobText!= null && KPCommonPage.jobTitle.equalsIgnoreCase(firstJobText)){
					if(browser.isDisplayed(JOB_CLOSE_LINK)){
						browser.click(JOB_CLOSE_LINK)
						browser.delay(1000)
						browser.click(CLOSEJOB_YES)
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "JobCloseLinkDisplayIssue", "Job close link was not displayed")
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobPostListDisplayIssue", "Job list was not displayed")
			}
		}

		/**
		 * @author aditya
		 * To check the first job status is closed after closing the job
		 */
		def static ifJobStatusIsClosed = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(FIRST_JOB_TITLE)){
				def firstJobText =  browser.gettext(FIRST_JOB_TITLE).trim()
				if(firstJobText!= null && KPCommonPage.jobTitle.equalsIgnoreCase(firstJobText)){
					if(browser.isDisplayed(FIRST_JOB_STATUS)){
						def jobStatusText = browser.gettext(FIRST_JOB_STATUS).trim()
						if(jobStatusText!= null && jobStatusText.equalsIgnoreCase("Closed")){
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "JobStatusMismatchIssue", "Job status is not closed")
						}
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobPostListDisplayIssue", "Job list was not displayed")
			}
		}

		/**
		 * @author aditya
		 * To search for the posted job
		 */
		def static searchforPostedJob = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(JOBSEARCH_TEXTBOX) && KPCommonPage.jobTitle!= null){
				browser.populateField(JOBSEARCH_TEXTBOX,KPCommonPage.jobTitle)
				browser.delay(1000)
				browser.pressEnter(JOBSEARCH_TEXTBOX)
				browser.delay(1000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobSearchTextBoxDisplayIssue", "The search job text box was not displayed or data is null")
			}
		}

		/**
		 * @author aditya
		 * To check if the closed job is not displayed in the Recommended job list for enrolled or Individual User
		 */
		def static ifClosedJobNotDisplayed = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(FIRST_JOB_TITLE)){
				return KPCommonPage.returnFailureOutcome(browser, "JobCloseIssue", "The job was not closed and is displayed for users")
			}else{
				if(browser.isDisplayed(NO_RECOMMENDEDJOBS_MSG)){
					def msg = browser.gettext(NO_RECOMMENDEDJOBS_MSG).trim()
					if(msg.equalsIgnoreCase("No Records To Display in Recommended Jobs")){
						return new SuccessOutcome()
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "NoJobsMsgDisplayIssue", "No jobs to display message was not displayed")
				}
			}
		}

		/**
		 * @author aditya
		 * To check if the first job status is not publised when job posting is greater than the current date on job posting list page
		 */
		def static ifJobStatusIsNotPublished = { browser, formData ->
			def jobStatusText = browser.gettext(FIRST_JOB_STATUS).trim()
			if(jobStatusText.equalsIgnoreCase("Not Published")){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobStatusMismatchIssue", "The job status do not match")
			}
		}

		/**
		 * @author aditya
		 * To check if the job status is publised when job posting is current date on job posting list page
		 */
		def static ifJobStatusIsPublished = { browser, formData ->
			def jobStatusText = browser.gettext(FIRST_JOB_STATUS).trim()
			if(jobStatusText.equalsIgnoreCase("Published")){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobStatusMismatchIssue", "The job status do not match")
			}
		}
	}
}
