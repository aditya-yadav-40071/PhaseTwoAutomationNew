package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


final class FollowedOrganizationsListPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new FollowedOrganizationsSearchForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new FollowedOrganizationsFilterForm().populateFields(browser, formData);
		}
	}


	def static getTotalViewingPodsCount = {browser, formData ->
		new FollowedOrganizationsSearchForm().getTotalViewingPodsCount browser, formData
	}

	def static isErrorMessageCorrect = {browser, formData ->
		new FollowedOrganizationsSearchForm().isErrorMessageCorrect browser, formData
	}

	def static sameFilterValuesAsSearch = {browser, formData ->
		new FollowedOrganizationsSearchForm().sameFilterValuesAsSearch browser, formData
	}

	def static organizationsDisplayed = {browser, formData ->
		new FollowedOrganizationsSearchForm().organizationsDisplayed browser, formData
	}


	def static getPageCount = {browser ->
		new FollowedOrganizationsSearchForm().getPageCount browser
	}

	def static isCorrectIndustryList = {browser, formData ->
		new FollowedOrganizationsFilterForm().isCorrectIndustryList browser, formData
	}

	def static isCorrectSortingList = {browser, formData ->
		new FollowedOrganizationsFilterForm().isCorrectSortingList browser, formData
	}

	def static addToFollowedOrgPage ={browser, formData ->
		new FollowedOrganizationsFilterForm().addToFollowedOrgPage browser, formData
	}

	def static followedOrgDsplyd ={browser, formData ->
		new FollowedOrganizationsFilterForm().followedOrgDsplyd browser, formData
	}

	def static removeFrmFollowedList ={browser, formData ->
		new FollowedOrganizationsFilterForm().removeFrmFollowedList browser, formData
	}

	def static isFollowedOrgRemoved ={browser, formData ->
		new FollowedOrganizationsFilterForm().isFollowedOrgRemoved browser, formData
	}

	def static followOrg ={browser, formData ->
		new FollowedOrganizationsFilterForm().followOrg browser, formData

	}

	def static correctResultDisplayed = {browser, formData ->
		new FollowedOrganizationsSearchForm().correctResultDisplayed browser, formData
	}

	def static correctOrgProfileDisplayed = {browser, formData ->
		new FollowedOrganizationsSearchForm().correctOrgProfileDisplayed browser, formData
	}

	def static getFollowingCount = {browser, formData ->
		new FollowedOrganizationsSearchForm().getFollowingCount browser, formData
	}

	def static correctFollowingCount = {browser, formData ->
		new FollowedOrganizationsSearchForm().correctFollowingCount browser, formData
	}

	def static bulkUnfollowOrgs = {browser, formData ->
		new FollowedOrganizationsFilterForm().bulkUnfollowOrgs browser, formData
	}



	static final class FollowedOrganizationsSearchForm extends WebForm {

		//Followed Organizations form elements

		private static final def FOLLOWING_COUNT = "//a[@ui-sref='kp.followedCompany']/p[1]"

		private static final def COUNT_IN_FOLLOWEDPAGE = "//p[@class='clearfix content output-count-header mt10 ng-binding']"

		private static final def SEARCH_TEXT = "//input[contains(@ng-model,'searchValue')]"

		private static final def FOLLOW = "//a[@class='pull-left link-primary smallText display-block orange pointer mr30 ng-binding']"

		private static final def FIRST_FOLLOW = "//div[@class='clearfix content individual-pod ng-scope'][1]//a[text()='Follow']"

		private static final def FIRST_UNFLLOW = "//div[@class='clearfix content individual-pod ng-scope'][1]//a[text()='UnFollow']"

		private static final def LOCATION = ".//md-select[@placeholder='Select Location']"

		private static final def LOCATION_LIST = "//md-option[@ng-value='location.placeId']"

		private static final def INDUSTRY = ".//md-select[@placeholder='Select Industry']"

		private static final def INDUSTRY_LIST = "//md-option[@ng-value='industry.industryId']"

		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def ORG_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def FILTER_INDUSTRY = "//md-select[@ng-model='functionCall.filter.industry']"

		private static final def FILTER_INDUSTRY_LIST = "//md-option[@ng-value='opt.industryId']"

		private static final def FILTER_LOCATION = "//md-select[@ng-model='functionCall.filter.location']"

		private static final def FILTER_LOCATION_LIST = "//md-option[@ng-value='opt.placeId']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav/div[2]/img"

		private static final def ERROR_MSG = "//div[@ng-show='isListEmpty']"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def ORG_PROFILE_NAME = "//h4[contains(@class,'primary_heading mb50 capitalize')]"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-next ng-scope']/preceding-sibling::li[1]/a"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("FollowedOrganizationsSearchForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						def searchInput = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.orgName = searchInput
					}
					browser.delay(1000)
				}
			}
			return outcome
		}




		//To get the total number of page count
		def static getPageCount(def browser){
			int lastPage=1
			if(browser.checkEnabled(PAGINATION_NEXT)){
				browser.delay(1000)
				def pageText = browser.gettext(LAST_PAGE_NO).trim()
				browser.delay(1000)
				lastPage = Integer.parseInt(pageText)
			}else{
				browser.delay(1000)
				lastPage
			}
			println "...lastPageNumber......"+lastPage
		}





		//To get the total viewing Pod's count in search pod page.
		def static final getTotalViewingPodsCount = {browser, formData ->

			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(ORG_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split(" ")[3].trim()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			return totalResult

		}


		//To verify the error message being displayed in followed organization list page
		def static final isErrorMessageCorrect = {browser, formData ->
			def actMsg
			def expMsg = "You are not following any organizations yet!!"
			if(browser.isDisplayed(ERROR_MSG)){
				actMsg = browser.gettext(ERROR_MSG)
				if(expMsg.equalsIgnoreCase(actMsg.trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageComparisionIssue", "After Comparision Error message is not displaying correctly")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageDisplayIssue", "Form Error Message is not Appearing.")
			}

		}


		//To verify that the Organization list is displayed on clicking search button
		def static final organizationsDisplayed = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(ORG_LIST)){
				println "Organizations list Displayed"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationNotDisplayedIssue", "Organizations are not displayed on search")
			}
		}



		//To verify that the primary searched location and industry are displaying in the filters too
		def static final sameFilterValuesAsSearch = { browser, formData ->
			if(browser.isDisplayed(FILTER_INDUSTRY) && browser.isDisplayed(FILTER_LOCATION)){
				browser.click FILTER_INDUSTRY
				browser.delay(1000)
				def allIndustry = browser.getLists(FILTER_INDUSTRY_LIST)
				browser.click(ELEMENT)
				browser.delay(1000)
				browser.click FILTER_LOCATION
				browser.delay(1000)
				def allLocation = browser.getLists(FILTER_LOCATION_LIST)
				if(allIndustry?.sort(false) == KPCommonPage.industryLists?.sort(false) && allLocation?.sort(false) == KPCommonPage.locationList?.sort(false)){
					KPCommonPage.industryLists = []
					KPCommonPage.locationList = []
					return new SuccessOutcome()
				}else{
					KPCommonPage.industryLists = []
					KPCommonPage.locationLists = []
					return KPCommonPage.returnFailureOutcome(browser, "Industry&LocationIssue", "Industry and location searched does not match with the displayed option in Filters.")
				}
			}
		}



		def static final correctResultDisplayed = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(ORG_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					def allResult = browser.getLists(ORG_LIST)
					def allResultElement = browser.getListElements(ORG_LIST)
					browser.delay(1000)
					if(allResult.size()>0){
						if(allResult[0].trim().equalsIgnoreCase(KPCommonPage.orgName)){
							if(browser.getTitle().equalsIgnoreCase("Search Organizations Result")){
								browser.click FIRST_FOLLOW
								browser.delay(1000)
							}else{
								browser.click FIRST_UNFLLOW
								browser.delay(1000)
							}
							println "Correct Organization Displayed"
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OrgNameMisMatchIssue", "The org name displayed do not match with the expected org name")
						}
					}else
						return KPCommonPage.returnFailureOutcome(browser, "NoRecordsDisplayedIssue", "No Organization Displayed for Input Search")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Org list is not displayed.")
			}
		}



		def static final correctOrgProfileDisplayed = {browser, formData ->
			if(browser.isDisplayed(ORG_PROFILE_NAME)){
				def text = browser.gettext(ORG_PROFILE_NAME)
				def actText = text.substring(0, text.lastIndexOf(" "));
				if(actText.equalsIgnoreCase(KPCommonPage.orgName.trim())){
					println "Correct Organization's profile Displayed."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "OrganizationProfileNameDisplayIssue", "The Name on Organization Profile page is displaying incorrect")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationsProfileNameNotDisplayIssue", "Organizations Profile Name is not appearing in profile page")
			}
		}


		def static final getFollowingCount = { browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(FOLLOWING_COUNT)){
				def followCount = Integer.parseInt(browser.gettext(FOLLOWING_COUNT).split(" ")[0].trim())
				if(followCount!=null && !followCount<0){
					KPCommonPage.followingCountDisplay = followCount
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FollwingCountNullIssue", "Following Countis appearing Null")
				}
			}
		}


		def static final correctFollowingCount = {browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(COUNT_IN_FOLLOWEDPAGE)){
				def countDisplayed = browser.gettext(COUNT_IN_FOLLOWEDPAGE).trim().split("of")[1].trim().split(" ")[0].trim()
				if(countDisplayed!=null && !countDisplayed.toInteger()<0){
					if(KPCommonPage.followingCountDisplay.toInteger()==countDisplayed.toInteger()){
						println "Following Count is Matched."
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "FollowingCountMistachIssue", "Dashboard Follow count is not matching with Follow org page count")
					}
				}else
					return KPCommonPage.returnFailureOutcome(browser, "FollwingCountNullIssue", "Following Count is appearing Null in Followed Page")
			}
		}
	}



	static final class FollowedOrganizationsFilterForm extends WebForm {

		//Filter form elements
		private static final def LOCATION_FILTER = "//md-select[@ng-model='functionCall.filter.location']"

		private static final def LOCATION_FILTER_LIST = "//md-option[@ng-value='opt.placeId']/div[2]"

		private static final def LOCATION_RESULT_LIST = "//span[i[@class='kp-location blue mr_5']]"

		private static final def INDUSTRY_RESULT_LIST = "//div[@class='clearfix content individual-pod ng-scope'][A]//span[@ng-if='industry']"

		private static final def INDUSTRY_FILTER = "//md-select[@ng-model='functionCall.filter.industry']"

		private static final def INDUSTRY_FILTER_LIST = "//md-option[@ng-value='opt.industryId']"

		private static final def SORTBY_FILTER = "//select[@ng-model='sortBy']"

		private static final def ORG_LIST =  "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav/div[2]/img"

		private static final def FILTER = "//div[@class='filter-container']"

		private static final def FOLLOW = "//a[@class='pull-left link-primary smallText display-block orange pointer mr30 ng-binding']"

		private static final def FIRST_ORG_NAME = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def FOLLOWED_ORG = "//a[@class='content-name job-title pointer title-name blue capitalize ng-binding']"

		private static final def UNFOLLOW = "//a[@class='pull-left link-primary smallText display-block orange pointer mr30 ng-binding']"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def TEN_RESULTS = "//input[@id='item1']/following-sibling::label"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def LAST_BUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"



		private static final def FIELDS = [LOCATION_FILTER, INDUSTRY_FILTER, SORTBY_FILTER]// the error fields.
		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(LOCATION_FILTER)){
						if(!formData[i].equals("")){
							browser.click LOCATION_FILTER
							browser.delay(2000)
							def followedLocationList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									followedLocationList.add(data[k])
									KPCommonPage.selectAutoComplete(browser, LOCATION_FILTER_LIST , data[k])
								}
							}else{
								followedLocationList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, LOCATION_FILTER_LIST , formData[i])
							}
							KPCommonPage.followedLocationListsFilter = followedLocationList
							browser.click(ELEMENT)
							browser.delay(1000)
						}
					}

					if(FIELDS[i].equals(INDUSTRY_FILTER)){
						if(!formData[i].equals("")){
							browser.click INDUSTRY_FILTER
							browser.delay(500)
							def followedIndustryLists = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									followedIndustryLists.add(data[k])
									KPCommonPage.selectAutoComplete(browser, INDUSTRY_FILTER_LIST , data[k])
								}
							}else{
								followedIndustryLists.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_FILTER_LIST , formData[i])
							}
							KPCommonPage.followedIndustryListsFilter = followedIndustryLists
							browser.click(ELEMENT)
							browser.delay(500)
						}
					}

					if(FIELDS[i].equals(SORTBY_FILTER)){
						if(!formData[i].equals("")){
							def sortData
							browser.click SORTBY_FILTER
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(SORTBY_FILTER,formData[i])
							browser.delay(500)
							KPCommonPage.searchOrgSortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}



		def static final isCorrectIndustryList = {browser, formData ->
			def matchingResult
			browser.delay(1000)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
				for(int w=0;w<lastPage;w++){
					if(browser.isDisplayed(ORG_LIST)){
						def totalOrg = browser.getLists(ORG_LIST)
						def expectedIndustryList = KPCommonPage.followedIndustryListsFilter
						if(expectedIndustryList != null || !expectedIndustryList.isEmpty()){
							def indLst=[]
							for(int k=1; k <= totalOrg.size(); k++){
								String indPerOrg = INDUSTRY_RESULT_LIST.replace("[A]","["+k+"]")
								def industriesPerOrg = browser.getLists(indPerOrg)
								for(int r=0;r<industriesPerOrg.size();r++){
									def	lst = industriesPerOrg[r].replace(",", "").trim()
									indLst.add(lst)
								}
							}
							if(indLst.containsAll(expectedIndustryList)){
								matchingResult = true
							}else{
								matchingResult = false
								break
							}
						}
					}
					if(browser.checkEnabled(PAGINATION_NEXT)){
						browser.click PAGINATION_NEXT
						browser.delay(1000)
					}
				}

			}
			if(matchingResult){
				println "Successfully Verified Industries"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IncorrectIndustryDisplayIssue", "Industry does not match with the input filter Location.")
			}
		}

		def static final isCorrectSortingList = {browser, formData ->
			def sortBy = KPCommonPage.searchOrgSortByData
			def matchingResult
			browser.delay(1000)
			if(browser.isDisplayed(FIFTY_RESULT)){
				//browser.click FIFTY_RESULT
				/*browser.delay(3000)
				 int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
				 println "....lastPage....."+lastPage*/
				//for(int w=0;w<lastPage;w++){
				def allResult = browser.getLists(ORG_LIST)
				browser.delay(1000)
				if(sortBy.equalsIgnoreCase("Alphabetical")){
					def result = KPCommonPage.isSorted(allResult)
					if(result){
						matchingResult = true
					}else{
						matchingResult = false
					}
				}
				/*if(browser.checkEnabled(PAGINATION_NEXT)){
				 browser.click PAGINATION_NEXT
				 browser.delay(600)
				 }*/
				//}

			}
			if(matchingResult){
				println "Successfully List is in Alphabetical Order"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical option is not Working.")
			}
		}




		//To add the first Organization to Followed Organization List page
		def static final addToFollowedOrgPage = { browser, formData ->
			browser.delay(500)
			if(browser.isDisplayed(FOLLOW)){
				KPCommonPage.firstFollowedOrgName = browser.getLists(FIRST_ORG_NAME)[0]
				def followLinks = browser.getLists(FOLLOW)
				if(browser.gettext(FOLLOW).contains("Follow")){
					browser.delay(1000)
					browser.click FOLLOW
					browser.delay(1000)
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FollowLinkDisplayIssue", "Follow Link is not displayed.")
			}
		}

		//To verify that the followed organization is displayed in followed Organization page
		def static final followedOrgDsplyd = { browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(FOLLOWED_ORG)){
				def name = browser.gettext(FOLLOWED_ORG)
				if(KPCommonPage.firstFollowedOrgName.equalsIgnoreCase(name)){
					println "Followed Organization displayed Successfully"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FollowedOganizationIssue", "Followed Organization is not same in the followed org page.")
				}

			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FollowedDisplayIssue", "Followed Organization is not displayed in the followed Organization page.")
			}
		}

		//To remove the first Organization from followed to search org page
		def static final removeFrmFollowedList = { browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(UNFOLLOW)){
				if(browser.gettext(UNFOLLOW).contains("UnFollow")){
					browser.click UNFOLLOW
					browser.delay(1000)
					println "Successfully Unfollowed the Organization"
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
			}
		}

		//To verify that the followed org is removed from the followed org page
		def static final isFollowedOrgRemoved = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(UNFOLLOW)){
				def name = browser.getLists(FOLLOWED_ORG)
				def result = false
				for(int i = 0; i < name.size(); i++){
					if(KPCommonPage.firstFollowedOrgName.equalsIgnoreCase(name[i])){
						result = false
						break
					}else{
						result = true
					}
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from followed org is not displayed.")
				}
			}else{
				return new SuccessOutcome()
			}

		}


		def static final followOrg = { browser, formData ->
			browser.delay(1500)
			//browser.click TEN_RESULTS
			if(browser.isDisplayed(ORG_LIST)){
				def orgNames = browser.getLists(ORG_LIST)
				def followLinks = browser.getLists(FOLLOW)
				def xpathToSelect = browser.getListElements(FOLLOW)
				if(orgNames!=null){
					def followCount = 0
					while(browser.isDisplayed(FOLLOW)){
						browser.delay(400)
						if(followCount<10){
							browser.click FOLLOW
							followCount++
						}else{
							break
						}
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationListDisplayIssue", "Organizations are not displaying in followed Organization page.")
			}

		}


		def static final bulkUnfollowOrgs = { browser, formData ->
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(ORG_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(1500)
					while(browser.isDisplayed(UNFOLLOW)){
						browser.delay(500)
						browser.click UNFOLLOW
						browser.delay(1000)
					}
					if(browser.isDisplayed(UNFOLLOW)){
						return KPCommonPage.returnFailureOutcome(browser, "BulkUnfollowIssue", "After Unfollow, Orgs are still displaying")
					}else{
						println "Successfully all Orgs are removed from Followed Organization page."
						return new SuccessOutcome()
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "OrgListDisplayedIssue", "Followed Organization list is not displayed.")
			}
		}
	}
}
