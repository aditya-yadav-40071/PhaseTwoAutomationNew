package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

final class MigrateUserPage extends WebPage {

	def static clickMigrateUserlink = { browser, formData ->
		new MigrateUserForm().clickMigrateUserlink browser, formData
	}

	def static migratePopUpDisplayed = { browser, formData ->
		new MigrateUserForm().migratePopUpDisplayed browser, formData
	}

	def static userDetailsOnMigratePopUp = { browser, formData ->
		new MigrateUserForm().userDetailsOnMigratePopUp browser, formData
	}

	def static migrateUserToAnotherSite = { browser, formData ->
		new MigrateUserForm().migrateUserToAnotherSite browser, formData
	}

	static final class MigrateUserForm extends WebForm {

		//Manage Users form elements
		private static final def USERNAME = ".//a[@ng-click='setSubAdminDetail(user.subAdminId)']"

		private static final def SITE_TO_SELECT = ".//select[@ng-model='selectedSite']"

		private static final def USEREMAIL_VALUE = ".//span[@class='col-md-12 col-xs-12 no-padding content-name smallText node-text1 width_100 ng-binding'][1]"//".//*[@id='emailId']"

		private static final def SEARCH_USER_TEXT_BOX = ".//div[@class='col-xs-12 visible-lg mb10 no-padding ml_15 mt_10 pr_25']//input"

		private static final def SUBMIT_BTN = ".//button[@class='perform-search r_30 pr_25']/i"

		private static final def MIGRATEUSER_LINK = "//a[contains(text(),'Migrate User')][@aria-hidden='false']"

		private static final def MIGRATE_POPUP = ".//div[@class='modal-content']"

		private static final def USERNAME_ON_MIGRATE_POPUP = ".//h4[@class='modal-title ng-binding']"

		private static final def SITENAME_ON_MIGRATE_POPUP = ".//div[@class='col-md-6 ng-binding']"

		private static final def SUBMIT = ".//input[@value='Migrate User']"

		private static final def MIGRATE_SUCCESS_MESSAGE = ".//div[@type='success']//span[@class='ng-binding ng-scope']"

		def static clickMigrateUserlink = { browser, formData ->
			browser.delay(1000)
			browser.scrollToElement2(SEARCH_USER_TEXT_BOX)
			browser.populateField(SEARCH_USER_TEXT_BOX,KPCommonPage.invitedUserEmailId)
			browser.pressEnter(SEARCH_USER_TEXT_BOX)
			browser.delay(1000)
			if(browser.isDisplayed(USERNAME)){
				def email = browser.gettext(USEREMAIL_VALUE).split(":")[1].trim()
				if(email!= null && KPCommonPage.invitedUserEmailId.trim().equalsIgnoreCase(email)){
					if(browser.isDisplayed(MIGRATEUSER_LINK)){
						browser.click(MIGRATEUSER_LINK)
						browser.delay(1000)
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "MigrateUserDisplayIssue", "The migrate user link was not displayed")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "UserEmailMismatchIssue", "The users email did not match or it is null")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDisplayIssue", "The user was not displayed")
			}
		}

		def static migratePopUpDisplayed = { browser, formData ->
			browser.delay(500)
			if(browser.isDisplayed(MIGRATE_POPUP)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "MigratePopUpDisplayIssue", "The migrate pop up was not displayed")
			}
		}

		def static userDetailsOnMigratePopUp = { browser, formData ->
			browser.delay(500)
			if(browser.isDisplayed(USERNAME_ON_MIGRATE_POPUP) && browser.isDisplayed(SITENAME_ON_MIGRATE_POPUP)){
				def userName = browser.gettext(USERNAME_ON_MIGRATE_POPUP).split(":")[1].trim()
				def siteName = browser.gettext(SITENAME_ON_MIGRATE_POPUP).trim()
				def userFullName = KPCommonPage.getFullName(KPCommonPage.invitedUserFirstName,KPCommonPage.invitedUserMiddleName,KPCommonPage.invitedUserLastName)
				if(userName.equalsIgnoreCase(userFullName) && siteName.equalsIgnoreCase(KPCommonPage.invitedUserSiteName)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "UserDetailsMisMatchIssue", "The username or site name do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "NameOrSiteDisplayIssue", "Username or site was not displayed on migrate pop-up")
			}
		}

		def static migrateUserToAnotherSite = { browser, formData ->
			browser.delay(500)
			if(browser.isDisplayed(SITE_TO_SELECT)){
				browser.delay(500)
				browser.selectDropdownValue(SITE_TO_SELECT,KPCommonPage.addedSiteName)
				if(browser.isDisplayed(SUBMIT) && browser.checkEnabled(SUBMIT)){
					browser.click(SUBMIT)
					browser.delay(1000)
					if(browser.isDisplayed(MIGRATE_SUCCESS_MESSAGE)){
						def messageText = browser.gettext(MIGRATE_SUCCESS_MESSAGE).trim()
						if(messageText.equalsIgnoreCase("User Migrated Successfully")){
							KPCommonPage.invitedUserSiteName = KPCommonPage.addedSiteName
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "SuccessMessageMismatchIssue", "The success message do not match with the expected one")
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "SuccessMessageDisplayIssue", "User migrated success message was not displayed")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SubmitButtonIssue", "The submit button was not displayed or is not enabled")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteDropDownDisplayIssue", "Site to select drop down was not displayed")
			}
		}
	}
}


