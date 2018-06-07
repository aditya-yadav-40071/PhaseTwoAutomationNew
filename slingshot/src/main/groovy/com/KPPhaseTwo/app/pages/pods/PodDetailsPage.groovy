package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 06/12/2017
 */

final class PodDetailsPage extends WebPage {

	//To verify the logged in user is displayed
	def static getCostOfPod = {browser, formData ->
		new PodDetailsForm().getCostOfPod browser, formData
	}

	//To verify buying a pod
	def static podBuyRequest = {browser, formData ->
		new PodDetailsForm().podBuyRequest browser, formData
	}

	//to check buy and enter classroom buttons are present
	def static isBuyAndEnterClassroomButtons = {browser, formData ->
		new PodDetailsForm().isBuyAndEnterClassroomButtons browser, formData
	}

	def static forumAndEnterClsRmButtonDisplayed = {browser, formData ->
		new PodDetailsForm().forumAndEnterClsRmButtonDisplayed browser, formData
	}

	def static checkEnterClsroomBtnEnabled = {browser, formData ->
		new PodDetailsForm().checkEnterClsroomBtnEnabled browser, formData
	}

	def static podIsUnderValidLicense = {browser, formData ->
		new PodDetailsForm().podIsUnderValidLicense browser, formData
	}

	def static renewLicensesBtnDisplayed = {browser, formData ->
		new PodDetailsForm().renewLicensesBtnDisplayed browser, formData
	}

	def static buyButtonUnavailable = { browser, formData ->
		new PodDetailsForm().buyButtonUnavailable browser, formData
	}


	static final class PodDetailsForm extends WebForm {

		//Pod details form elements
		private static final def OVERVIEW_TAB = "//a[contains(text(), 'Overview')]"

		private static final def POD_AMOUNT = "//div[h5[normalize-space(text())='Amount']]/following-sibling::div/span"

		private static final def BUY_BUTTON = "//div[@ng-if='buyCourse']/button"

		private static final def ENTER_CLASSROOM_BTN = "//button[text()='Enter Classroom']"

		private static final def RENEW_LICENSES_BTN = "//button[text()='Renew Licenses']"

		private static final def FORUM_BTN = "//button[text()='Forum']"

		private static final def RENEW_LICENSE_STATUS = "//form[@name='userProfileForm']//tr[2]/td[7]"

		private static final def BREDCRUMB_BACK = ".//*[@id='breadcrumbox']/ol/li[3]/a"

		//To verify the logged in user is displayed
		def static final getCostOfPod = {browser, formData ->

			if(browser.isDisplayed(POD_AMOUNT)){
				browser.scrollToElement2(POD_AMOUNT)
				def amount_text = browser.gettext(POD_AMOUNT)
				KPCommonPage.pod_amount = ((amount_text.split("\\s+"))[0]).toFloat()
				println "KPCommonPage.pod_amount:"+KPCommonPage.pod_amount
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "noAmountDisplayed", "Amount not displayed.")
		}

		//To verify if the request is sent for buying a pod
		def static final podBuyRequest = {browser, formData ->
			browser.delay(3000)
			if(!browser.checkEnabled(BUY_BUTTON)){
				println "Pod is Already Bought"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "podNotBought", "Request to buy not sent.")
		}


		//To check buy and Enter Classroom button is displayed or not
		def static final isBuyAndEnterClassroomButtons = {browser, formData ->

			if(browser.isDisplayed(BUY_BUTTON)){
				browser.scrollToElement2(ENTER_CLASSROOM_BTN)
				if(browser.isDisplayed(ENTER_CLASSROOM_BTN)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EnterClassRoomButtonDisplayIssue", "Enter Class room button does not appear.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "BuyButtonDisplayIssue", "Buy Button does not Appear")
			}
		}

		def static buyButtonUnavailable = { browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(BUY_BUTTON)){
				return KPCommonPage.returnFailureOutcome(browser, "BuyButtonDisplayIssue", "Buy Button is displayed for enrolled user")
			}else{
				browser.scrollToElement2(ENTER_CLASSROOM_BTN)
				if(browser.isDisplayed(ENTER_CLASSROOM_BTN)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EnterClassRoomButtonDisplayIssue", "Enter Class room button does not appear.")
				}
			}
		}


		//To check forum and Renew License buttons are appearing
		def static final checkEnterClsroomBtnEnabled  = {browser, formData ->
			browser.delay(2000)
			browser.scrollToElement2(RENEW_LICENSES_BTN)
			if(browser.isDisplayed(RENEW_LICENSES_BTN) && browser.checkEnabled(RENEW_LICENSES_BTN)){
				browser.click RENEW_LICENSES_BTN
				def isValidLicense = PodDetailsPage.podIsUnderValidLicense browser, formData
				if(isValidLicense){
					browser.click BREDCRUMB_BACK
					browser.delay(2000)
					println "Pod is under valid license"
					browser.scrollToElement2(ENTER_CLASSROOM_BTN)
					if(browser.checkEnabled(ENTER_CLASSROOM_BTN)){
						browser.click ENTER_CLASSROOM_BTN
						browser.delay(4000)
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "EnterClassroomButtonEnableIssue", " Enter Classroom button is not Enabled ")
					}
				}else{
					browser.click BREDCRUMB_BACK
					browser.delay(2000)
					println "Pod is not Under Valid License"
					browser.scrollToElement2(ENTER_CLASSROOM_BTN)
					if(browser.checkEnabled(ENTER_CLASSROOM_BTN)==false){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "EnterClassroomButtonEnableIssue", "Enter Classroom should not be enable when License is expired.")
					}
				}
			}else if(browser.isDisplayed(ENTER_CLASSROOM_BTN)){
				if(browser.checkEnabled(ENTER_CLASSROOM_BTN)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EnterClassRoomBtnEnableIssue", "The enter classroom button was not enabled")
				}
			}
			else{
				return KPCommonPage.returnFailureOutcome(browser, "RenewLicensesButtonDisplayIssue", "Renew licence Button does not Appear")
			}
		}

		def static final podIsUnderValidLicense ={browser, formData ->

			browser.delay(2000)
			def res = false
			if(browser.isDisplayed(RENEW_LICENSE_STATUS)){
				def licenseStatus = browser.gettext(RENEW_LICENSE_STATUS)
				if(licenseStatus.contains("Active") || licenseStatus.contains("Grace Period")){
					println "---Success---"
					res = true
				}else{
					res = false
				}
			}
			return res
		}

		def static final forumAndEnterClsRmButtonDisplayed = {browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(FORUM_BTN)){
				browser.scrollToElement2(ENTER_CLASSROOM_BTN)
				if(browser.isDisplayed(ENTER_CLASSROOM_BTN) && browser.checkEnabled(FORUM_BTN)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EnterClassRoomButtonDisplayIssue", "Enter Class room button does not appear.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ForumButtonDisplayIssue", "Forum Button does not Appear")
			}
		}


		def static final renewLicensesBtnDisplayed = {browser, fromData ->
			browser.scrollToElement2(ENTER_CLASSROOM_BTN)
			if(browser.isDisplayed(RENEW_LICENSES_BTN)){
				if(browser.checkEnabled(RENEW_LICENSES_BTN)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "RenewLicenseButtonEnableIssue", "Renew License Button is not Enabled.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "RenewLicenseButtonDisplayIssue", "Renew License Button is not Appearing.")
			}
		}
	}
}
