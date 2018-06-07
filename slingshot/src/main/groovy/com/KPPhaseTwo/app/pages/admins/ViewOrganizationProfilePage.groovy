package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Krithika on 30/02/2017
 */

final class ViewOrganizationProfilePage extends WebPage {

	//To verify the edit profile and view profile data match
	def static editDataMatch= {browser, formData ->
		new ViewOrganizationProfileForm().editDataMatch browser, formData
	}

	//To verify image displayed in View Profile page
	def static uploadImgDisplay = { browser, formData ->
		new ViewOrganizationProfileForm().uploadImgDisplay(browser,  formData)
	}

	//To verify About Organization data display.
	def static aboutOrgDataDisplay = { browser, formData ->
		new ViewOrganizationProfileForm().aboutOrgDataDisplay browser, formData
	}

	//To verify Latest News data display.
	def static aboutLatestNewsDisplay = { browser, formData ->
		new ViewOrganizationProfileForm().aboutLatestNewsDisplay browser, formData
	}

	static final class ViewOrganizationProfileForm extends WebForm {

		//View Organization Profile page elements
		private static final def ABOUT_ORG = "//div[@class='row']/div/div[2]/div[1]"

		private static final def LATEST_NEWS = "//div[@class='row']/div/div[2]/div[3]/p"

		private static final def ABOUT_VIEW = "//div[@class='row']/div/div[2]/div[1]/p/a"

		private static final def LATEST_VIEW = "//div[@class='row']/div/div[2]/div[3]/p[1]"

		private static final def VIEW_PROFILE_DATA = "//div[@class='main-image-container']/div/span[@class='ng-binding']"

		private static final def VIEW_MORE_INFO = ".//div[@class='public-profile-text']//a"

		//To verify Organization data displayed after editing
		def static final editDataMatch = {browser, formData ->
			browser.delay(3000)
			def viewProfileData = browser.getLists(VIEW_PROFILE_DATA)
			if(viewProfileData.sort().equals(KPCommonPage.ViewOrgDataVerify.sort())){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "dataMismatch", "Organization data mismatch in View Profile page.")
		}

		//Verify image displayed in View Organization Profile page
		def static final uploadImgDisplay = {browser, formData ->
			def srcViewOrgLogo = browser.gettext(KPCommonPage.IMAGE_LOGO_ORG, "src").split("\\?")[0].trim()
			if((KPCommonPage.srcOrgLogoImage!=null) && (srcViewOrgLogo!=null)){
				if(srcViewOrgLogo.equalsIgnoreCase(KPCommonPage.srcOrgLogoImage)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "srcUrlMismatch", "Image was not uploaded properly.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "nullSrcUrl", "Image has null src attribute.")
		}

		//Verify About Organization data in View Org Profile page
		def static final aboutOrgDataDisplay = { browser, formData ->
			browser.delay(1000)
			def aboutOrgData
			if(browser.isDisplayed(VIEW_MORE_INFO)){
				browser.click(VIEW_MORE_INFO)
				browser.delay(1000)
			}
			if(browser.isDisplayed(ABOUT_ORG)){
				browser.delay(1000)
				aboutOrgData = browser.gettext(ABOUT_ORG)
				if(browser.isDisplayed(ABOUT_VIEW)){
					aboutOrgData = aboutOrgData.substring(0, aboutOrgData.indexOf(KPCommonPage.viewSize))
				}
				if(aboutOrgData.trim().equalsIgnoreCase(formData[0].trim())){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "dataMismatch", "About Organization data mismatch.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "elementNotFound", "About Organization element not displayed")
		}

		//Verify Latest News data in View Org Profile page.
		def static final aboutLatestNewsDisplay = {browser, formData ->
			def latestNewsData
			if(browser.isDisplayed(LATEST_NEWS)){
				browser.delay(1000)
				if(browser.isDisplayed(LATEST_VIEW)){
					browser.click LATEST_VIEW
					latestNewsData = browser.gettext(LATEST_NEWS)
					latestNewsData = latestNewsData.substring(0, latestNewsData.lastIndexOf(KPCommonPage.viewSize))
				}
				if(latestNewsData.trim().equalsIgnoreCase(formData[0].trim())){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "dataMismatch", "Latest News data mismatch.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "elementNotFound", "Latest News element not found.")
		}
	}
}

