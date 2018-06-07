package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

final class SiteDetailPage extends WebPage {

	def static ifTrainerAssignedToSite = { browser, formData ->
		new SiteDetailForm().ifTrainerAssignedToSite browser,formData
	}

	def static correctSiteDetails = { browser, formData ->
		new SiteDetailForm().correctSiteDetails browser,formData
	}

	def static correctSiteDetailsOnLeftSide = { browser, formData ->
		new SiteDetailForm().correctSiteDetailsOnLeftSide browser,formData
	}

	def static sameLocationAsFilter = { browser, formData ->
		new SiteDetailForm().sameLocationAsFilter browser,formData
	}

	static final class SiteDetailForm extends WebForm {

		private static final def TRAINER_NAME = ".//span[@ng-show='!siteDetail.trainersList.length']"

		private static def SITE_DETAILS = ".//div[@class='row']/div[1]/h6[.='Value']/../../div[2]/span"

		private static final def  SITE_TRAINER = ".//ul[@ng-repeat='trainer in siteDetail.trainersList']/li"

		private static final def LEFTSIDE_SITENAME = ".//span[@class='capitalize ng-binding']"

		private static final def LEFTSIDE_SITEEMAIL = ".//div[@class='col-md-4']//h5[1]"

		private static final def LEFTSIDE_SITEPHONE = ".//div[@class='col-md-4']//h5[2]"

		private static final def SITEDETAIL_LIST = ["Address", "City", "State", "Country", "Pin Code", "Phone Number", "Email Id"]

		def static ifTrainerAssignedToSite = { browser, formData ->
			browser.delay(1000)
			def trainerName = browser.gettext(TRAINER_NAME).trim()
			if(trainerName.equalsIgnoreCase("N/A")){
				KPCommonPage.trainerStatus = true
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "TrainerDisplayIssue", "The trainer is already assigned or nothing was displayed")
			}
		}

		def static correctSiteDetails = { browser, formData ->
			def result
			def siteDetailList = []
			if(KPCommonPage.trainerStatus){
				KPCommonPage.siteDetails.add(KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName).split(" ")[0].trim())
				def siteTrainerValue = browser.gettext(SITE_TRAINER).trim()
				siteDetailList.add(siteTrainerValue)
			}
			for(int i =0;i<SITEDETAIL_LIST.size();i++){
				def siteValueXpath = SITE_DETAILS.replace("Value",SITEDETAIL_LIST[i])
				def siteDetailValue = browser.gettext(siteValueXpath).trim()
				siteDetailList.add(siteDetailValue)
			}
			KPCommonPage.siteDetails = KPCommonPage.siteDetails.sort()
			siteDetailList = siteDetailList.sort()

			for(int j=0;j<siteDetailList.size();j++){
				if(siteDetailList[j].trim().equalsIgnoreCase(KPCommonPage.siteDetails[j].trim())){
					result = true
				}else{
					result = false
					break
				}
			}

			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteDetailsMismatchIssue", "The site details do not match")
			}
		}

		def static correctSiteDetailsOnLeftSide = { browser, formData ->
			browser.delay(1000)
			def siteName  = browser.gettext(LEFTSIDE_SITENAME).trim()
			def siteEmail = browser.gettext(LEFTSIDE_SITEEMAIL).trim()
			def sitePhone = browser.gettext(LEFTSIDE_SITEPHONE).trim()
			if(siteName.trim().equalsIgnoreCase(KPCommonPage.addedSiteName)){
				if(siteEmail.trim().equalsIgnoreCase(KPCommonPage.siteEmail)){
					if(sitePhone.trim().equalsIgnoreCase(KPCommonPage.sitePhone)){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "sitePhoneMismatchIssue", "The phone name do not match on site detail page")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "siteEmailMismatchIssue", "The site email do not match on site detail page")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "siteNameMismatchIssue", "The site name do not match on site detail page")
			}
		}

		def static sameLocationAsFilter = { browser, formData ->
			browser.delay(200)
			def siteCityXPath = SITE_DETAILS.replace("Value","City")
			def siteCityName = browser.gettext(siteCityXPath).trim()
			if(siteCityName!=null){
				if(siteCityName.equalsIgnoreCase(KPCommonPage.siteCity)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SiteCityNameMismatchIssue", "The site city name do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteCityNameNullIssue", "The site city name is null")
			}
		}
	}
}