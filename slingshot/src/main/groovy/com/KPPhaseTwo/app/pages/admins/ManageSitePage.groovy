package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/** 
 * @author Aditya
 */
final class ManageSitePage extends WebPage {

	/**
	 * To check if the created site is displayed on the manage site page
	 */
	def static correctSiteDisplayed = { browser, formData ->
		new ManageSiteForm().correctSiteDisplayed browser,formData
	}

	/**
	 * Search for a created site  using the search bar on Manage Site page 
	 */
	def static searchForSite  = { browser, formData ->
		new ManageSiteForm().searchForSite browser,formData
	}

	/**
	 * Click on the site name which is created to go to site detail page
	 */
	def static clickOnSiteName = { browser, formData ->
		new ManageSiteForm().clickOnSiteName browser,formData
	}

	/**
	 * Click on the edit site link of site
	 */
	def static clickEditLink  = { browser, formData ->
		new ManageSiteForm().clickEditLink browser,formData
	}

	/**
	 * To verify if the Default site is created automatically when an org is created
	 */
	def static defaultSiteDisplayed  = { browser, formData ->
		new ManageSiteForm().defaultSiteDisplayed browser,formData
	}

	/**
	 * To check if the site list is sorted in A-Z or Z-A format 
	 */
	def static manageSiteListSorted = { browser, formData ->
		new ManageSiteForm().manageSiteListSorted browser,formData
	}

	/**
	 * To select the location filter on manage site page
	 */
	def static selectLocationFilter = { browser, formData ->
		new ManageSiteForm().selectLocationFilter browser,formData
	}


	static final class ManageSiteForm extends WebForm {

		private static final def SITE_NAME = ".//a[@ng-controller='ManageOrgSitesController']"

		private static final def PAGINATION_NEXT = ".//li[@class='pagination-next ng-scope']"

		private static final def PAGE_COUNT = "//a[@ng-click='selectPage(page + 1, \$event)']//preceding::a[1]"

		private static final def SEARCH_TEXT_BOX = ".//div[@class='col-xs-12 mb10 no-padding ml_15 visible-lg mt_10 pr_25']//input"

		private static final def SEARCH_BUTTON = ".//div[@class='col-xs-12 mb10 no-padding ml_15 visible-lg mt_10 pr_25']//button"

		private static final def SITE_EDIT_LINK = ".//a[@ui-sref='kp.editOrgSite']"

		private static final def SORTBY_FILTER = ".//div[@class='pull-left col-md-12 col-xs-12 no-padding']/select"

		private static final def FIFTY_RESULTS = ".//label[@for='item5']"

		private static final def LOCATION_FILTER = ".//*[@id='city']"

		private static final def LOCATION_NAME_LIST = ".//div[@aria-hidden='false']//*[@ng-value='location.city']//div[@class='md-text ng-binding']"

		/**
		 * To check if the created site is displayed on the Manage Site page
		 * Comapres the site names on Manage Site with the saved name in KPCommonPage.addedSiteName while site creation
		 */
		def static correctSiteDisplayed = { browser, formData ->
			browser.delay(2000)
			def result = false
			def pageCount = Integer.parseInt(browser.gettext(PAGE_COUNT))
			outerloop: for(int j=0;j<pageCount;j++){
				browser.delay(2000)
				def siteName = browser.getLists(SITE_NAME)
				for(int i=0;i<siteName.size();i++){
					if(siteName[i].trim().equalsIgnoreCase(KPCommonPage.addedSiteName)){
						result = true
						break outerloop
					}
				}
				if(!result && pageCount>1){
					browser.click(PAGINATION_NEXT)
					browser.delay(2000)
				}
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteDisplayIssue", "The site was not created or it is not displayed")
			}
		}


		/**
		 * Search for the created site name with data input from KPCommonPage.addedSiteName
		 */
		def static searchForSite = { browser, formData ->
			if(browser.isDisplayed(SEARCH_TEXT_BOX)){
				browser.populateField(SEARCH_TEXT_BOX,KPCommonPage.addedSiteName)
				browser.delay(4000)
				browser.click(SEARCH_BUTTON)
				browser.delay(2000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "searchTextBoxDisplayIssue", "The search text box was not displayed")
			}
		}

		/**
		 * Click on the created site name by comparing the site name and KPCommonPage.addedSiteName
		 */
		def static clickOnSiteName = { browser, formData ->
			browser.delay(1000)
			def result = false
			if(browser.isDisplayed(SITE_NAME)){
				def siteName = browser.getLists(SITE_NAME)
				def siteNameElement = browser.getListElements(SITE_NAME)
				for (int i=0;i<siteNameElement.size();i++){
					if(siteName[i].trim().equalsIgnoreCase(KPCommonPage.addedSiteName)){
						browser.clickElement(siteNameElement[i])
						browser.delay(5000)
						result = true
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "siteNameDisplayIssue", "The site name was not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "siteNameClickIssue", "Unable to click on the site name")
			}
		}

		/**
		 * Click on the edit site link of the created site
		 */
		def static clickEditLink = { browser, formData ->
			def result = false
			if(browser.isDisplayed(SITE_NAME)){
				def siteName = browser.getLists(SITE_NAME)
				def siteEditElement = browser.getListElements(SITE_EDIT_LINK)
				for(int i=0;i<siteName.size();i++){
					if(siteName[i].trim().equalsIgnoreCase(KPCommonPage.addedSiteName)){
						browser.clickElement(siteEditElement[i])
						result = true
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "siteDisplayIssue", "The site was not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "editLinkClickIssue", "The edit link was not clicked")
			}
		}

		/**
		 * To verify if the Default site is created automatically when an org is created
		 */
		def static defaultSiteDisplayed = { browser, formData ->
			browser.delay(500)
			if(browser.isDisplayed(SITE_NAME)){
				browser.populateField(SEARCH_TEXT_BOX,"Default Online Site")
				browser.click(SEARCH_BUTTON)
				browser.delay(500)
				def siteName = browser.gettext(SITE_NAME).trim()
				if(siteName.equalsIgnoreCase("Default Online Site")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "editLinkClickIssue", "The edit link was not clicked")
				}
			}
		}

		/**
		 * To check if the site list is sorted in A-Z or Z-A format 
		 */
		def static manageSiteListSorted = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(FIFTY_RESULTS)){
				browser.click(FIFTY_RESULTS)
				browser.delay(1000)
				KPCommonPage.selectSortByValue(browser,SORTBY_FILTER,formData[0])
				def siteNames = browser.getLists(SITE_NAME)
				def sortResult = KPCommonPage.isSorted(siteNames)
				def dropDownValue = browser.getDropdownValue(SORTBY_FILTER).trim()
				if(sortResult && dropDownValue.equalsIgnoreCase("Alphabetical A-Z")){
					return new SuccessOutcome()
				}else if(!sortResult && dropDownValue.equalsIgnoreCase("Alphabetical Z-A")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortingFilterIssue", "Sorting filter is not working properly")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FiftyResultsDisplayIssue", "The fifty result bar was not displayed")
			}
		}

		/**
		 * To select the location filter on manage site page
		 */
		def static selectLocationFilter = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(LOCATION_FILTER)){
				browser.click(LOCATION_FILTER)
				browser.delay(600)
				def locationNameList = browser.getLists(LOCATION_NAME_LIST)
				def locationEleList = browser.getListElements(LOCATION_NAME_LIST)
				for(int i=0;i<locationNameList.size();i++){
					if(locationNameList[i].trim().equalsIgnoreCase(KPCommonPage.siteCity.trim())){
						browser.clickElement(locationEleList[i])
						browser.delay(2000)
						browser.click("html/body/md-backdrop")
						browser.delay(200)
						return new SuccessOutcome()
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteFilterDisplayIssue", "The site filter was not displayed")
			}
		}
	}
}

