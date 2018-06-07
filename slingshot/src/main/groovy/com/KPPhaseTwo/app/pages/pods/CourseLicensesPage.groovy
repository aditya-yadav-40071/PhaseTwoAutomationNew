package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 10/12/2018
 */

final class CourseLicensesPage extends WebPage {

	//Override
	/*def populateData = {browser, formKey, formData ->
		new CourseLicensesPageForm().populateFields(browser, formData);
	}*/
	
	
	//To verify correct pod name is appearing in bredcrumb url
	def static correctPodNameInBredcrumb = {browser, formData ->
		new CourseLicensesForm().correctPodNameInBredcrumb browser, formData
	} 
	
	def static startAndEndLicenseDate = {browser,formData ->
		new CourseLicensesForm().startAndEndLicenseDate browser, formData
	}
	
	def static correctTotalAndEnrolledStudents = {browser, formData ->
		new CourseLicensesForm().correctTotalAndEnrolledStudents browser, formData
	}
	
	def static getCompleteLicenseDetials = {browser, formData ->
		new CourseLicensesForm().getCompleteLicenseDetials browser, formData
	}
	
	def static correctLicenseListDisplayed = {browser,formData ->
		new CourseLicensesForm().correctLicenseListDisplayed browser, formData
	}
	
	
	def static renewLinkDisabled = {browser,formData ->
		new CourseLicensesForm().renewLinkDisabled browser, formData
	}
	
	def static correctExpiredLicense = {browser, formData ->
		new CourseLicensesForm().correctExpiredLicense browser,formData
	}
	
	
	
	
	
	
	static final class CourseLicensesForm extends WebForm {

		//Course Licenses page form elements as an User
		private static final def POD_LIST_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
		
		private static final def PODNAME_IN_BREDCRUMB = "//h4[@id='breadcrumbox']/ol/li[3]/a"
		
		private static final def LICENSE_DETAILS_LIST = "//tbody/tr[2]/td"
		
		private static final def START_DATE = "//tbody/tr[2]/td[4]"
		
		private static final def EXPIRY_DATE = "//tbody/tr[2]/td[5]"
		
		private static final def TOTAL_STUDENT_ALLOWED = "//tbody/tr[2]/td[2]"
		
		private static final def ENROLLED_STUDENTS = "//tbody/tr[2]/td[3]"
		
		private static final def GRACE_PERIOD = "//tbody/tr[2]/td[6]"
		
		private static final def RENEW_LINK = "//tbody/tr[2]/td[8]/a"
		
		private static final def NORECORDS_MSG = "//p[text()='No Records To Display']"
		
		private static final def EXP_ERRORMSG = "No Records To Display"
		
		private static final def FIELDS = []
		
		private static final def buyAPodErrorMessageMap = []
		

		
		def static final correctPodNameInBredcrumb = {browser, formData ->
			if(browser.isDisplayed(PODNAME_IN_BREDCRUMB)){
				def podName = browser.gettext(PODNAME_IN_BREDCRUMB).trim()
				if(podName.equalsIgnoreCase(KPCommonPage.podName.trim())){
					println "Correct Pod Name Displayed In Bredcrumb Url"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "IncorrectPodNameIssue", "Pod Name is not coorect in bredcrumb URL")
			}
		}
		
		
		def static final startAndEndLicenseDate = {browser, formData ->
			
			if(browser.isDisplayed(START_DATE) && browser.isDisplayed(EXPIRY_DATE)){
				def startDate = browser.gettext(START_DATE).trim()
				def endDate = browser.gettext(EXPIRY_DATE).trim()
				
				int expStartDateInDays = browser.CurrentDate().split("/")[0].trim().toInteger()
				int expStartDateInMonths = browser.CurrentDate().split("/")[1].trim().toInteger()
				int expStartDateInYears = Integer.parseInt(browser.CurrentDate().split("/")[2].trim())
				int noOfYearsProvided = Integer.parseInt(KPCommonPage.getEndDateInYears)
				int actEndYear = Integer.parseInt(endDate.split("/")[2].trim())
				
				def actStartDateInDays = startDate.split("/")[0].trim().toInteger()
				def actStartDateInMonths = startDate.split("/")[1].trim().toInteger()
				def actStartDateInYears = startDate.split("/")[2].trim().toInteger()
				

				if(expStartDateInDays == actStartDateInDays && expStartDateInMonths == actStartDateInMonths
					&& expStartDateInYears == actStartDateInYears && 
					(expStartDateInYears+noOfYearsProvided) ==actEndYear){
					println "Start Date and End date is verified."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "IncorrectStartOrEndDateIssue", "Start Date or End Date is Incorrect displayed")
				
			}
		}
		
		
		def static final correctTotalAndEnrolledStudents = {browser, formData ->
			if(browser.isDisplayed(TOTAL_STUDENT_ALLOWED) && browser.isDisplayed(ENROLLED_STUDENTS) 
				&& browser.isDisplayed(GRACE_PERIOD)){
				
				def totalStudents = browser.gettext(TOTAL_STUDENT_ALLOWED).trim().toInteger()
				def enrolledStudents = browser.gettext(ENROLLED_STUDENTS).trim().toInteger()
				def gracePeriods = browser.gettext(GRACE_PERIOD).trim().toInteger()
				if(totalStudents==1 && enrolledStudents==1 && gracePeriods==15){
					println "Total students,enrolled and Grace period is Correct"
					return new SuccessOutcome() 
				}else
					return KPCommonPage.returnFailureOutcome(browser, "IncorrectStudentsCountIssue", "Students Count or Grace Period is incorrect.")
				
			}
		}
		
		
		def static final getCompleteLicenseDetials = {browser, formData ->
			if(!browser.isDisplayed(LICENSE_DETAILS_LIST) || (browser.getLists(LICENSE_DETAILS_LIST)).isEmpty()){
				return KPCommonPage.returnFailureOutcome(browser, "LicenseListIsNULL", "License List Details are not Appearing.")
			}else{
				KPCommonPage.allTabLicenseList = browser.getLists(LICENSE_DETAILS_LIST)
				return new SuccessOutcome()
			}
				
			
		}
		
		
		
		
		def static final correctLicenseListDisplayed = {browser,formData ->
			browser.delay(3000)
			if((!(KPCommonPage.allTabLicenseList).isEmpty()) && browser.isDisplayed(LICENSE_DETAILS_LIST)){
				def activeTabList = browser.getLists(LICENSE_DETAILS_LIST)
				def allTabList = KPCommonPage.allTabLicenseList
				KPCommonPage.activeTabLicenseList = browser.getLists(LICENSE_DETAILS_LIST)
				KPCommonPage.renewStatus = browser.gettext(RENEW_LINK,"class")
				if(allTabList.equals(activeTabList)){
					println "AllTab and ActiveTab,Both the Lists are Equal."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AllTabAndActiveTabListDisplayedIssue", "All Tab and Active tab details are not same.")
				}
			}
			
		}
		
		
		
		def static final renewLinkDisabled = {browser,formData ->
			if(browser.isDisplayed(RENEW_LINK)){
				def renewText = browser.gettext(RENEW_LINK,"disabled")
				if(renewText!=null && renewText){
					println "Renew Link is Successfully Disabled."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "RenewLinkIsNotDisabledIssue", "Renew Link is not Disabled,please check.")
				}
			}
		}
		
		def static final correctExpiredLicense = {browser, fromData ->
			if(!(KPCommonPage.renewStatus.contains("gray"))){
				if(browser.isDisplayed(LICENSE_DETAILS_LIST)){
					println "Expired List is Displayed"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "LicenseListNotDisplayedInExpiryTab", "Expired License List does Not Display In ExpiryTab")
				}
			}else{
			      if(browser.isDisplayed(NORECORDS_MSG) && browser.gettext(NORECORDS_MSG).trim().equalsIgnoreCase(EXP_ERRORMSG)){
					  println "No Records Msg Verified."
					  return new SuccessOutcome()
				  }else{
				  	  return KPCommonPage.returnFailureOutcome(browser, "NoRecordsMsgDisplayIssue", "No Records Msg does not Display Issue.")
				  }
			}
			
		}
		
		
		
		
		
		
		
	}
	
}
