package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 07/02/2018
 */

final class ApplicantProfileListPage extends WebPage {

	/**
	 * @author Aditya
	 * To check the applicant details when user applies for a job
	 */
	def static correctApplicantDetails  = { browser, formData ->
		new ApplicantProfileListPageForm().correctApplicantDetails browser ,formData
	}

	static final class ApplicantProfileListPageForm extends WebForm {

		private static final def USER_FULLNAME 			   = ".//span[@class='blue ng-binding']"

		private static final def USER_EMAIL 			   = ".//h5[@class='p_5 text-center ng-binding'][1]"

		private static final def USER_MOBILENUM			   = ".//h5[@class='p_5 text-center ng-binding'][2]"

		private static final def JOB_TITLE                 = ".//h3[@class='content-name job-title mt10 mb_5 ng-binding']"

		/**
		 * @author Aditya
		 * To check the applicant details match when user applies for a job
		 */
		def static correctApplicantDetails = { browser, formData ->
			if(browser.gettext(JOB_TITLE).equalsIgnoreCase(KPCommonPage.jobTitle)){
				def fullName = browser.gettext(USER_FULLNAME)
				def email = browser.gettext(USER_EMAIL)
				def mobileNumber = browser.gettext(USER_MOBILENUM)
				if(fullName.equalsIgnoreCase(KPCommonPage.userFullNameonProfile) && email.equalsIgnoreCase(KPCommonPage.userEmail) || mobileNumber.equalsIgnoreCase(KPCommonPage.userMobileNo)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "userDetailsMismatchIssue", "Mobile Number or Email or Name did not match")
				}
			}
		}
	}
}