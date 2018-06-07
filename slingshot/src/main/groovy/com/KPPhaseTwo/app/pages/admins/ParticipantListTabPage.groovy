package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

final class ParticipantListTabPage extends WebPage {

	def static ifOngoingTabIsHighlighted = { browser,formData ->
		new ParticipantListTabForm().ifOngoingTabIsHighlighted(browser, formData)
	}

	def static searchForParticipant = { browser,formData ->
		new ParticipantListTabForm().searchForParticipant(browser, formData)
	}

	def static participantDisplayedOnPage = { browser,formData ->
		new ParticipantListTabForm().participantDisplayedOnPage(browser, formData)
	}

	def static selectSiteFilter = { browser, formData ->
		new ParticipantListTabForm().selectSiteFilter(browser, formData)
	}

	def static podProgressPercentageIsZero = { browser,formData ->
		new ParticipantListTabForm().podProgressPercentageIsZero(browser, formData)
	}

	def static participantTabListSorted = { browser,formData ->
		new ParticipantListTabForm().participantTabListSorted(browser, formData)
	}
	
	static final class ParticipantListTabForm extends WebForm {

		//Login form elements
		private static final def  PARTICIPANT_SEARCH        = ".//div[@ng-hide='isListEmpty && hideSearch']//input[@ng-model='searchValue']"

		private static final def  USER_EMAIL_ID             = ".//h4[@class='mb_10 mt_10 ng-binding']"

		private static final def  LEARNER_NAMES             = ".//span[@class='capitalize ng-binding']"

		private static final def ONGOING_TAB 				= ".//li[@class='resp-line-height-15 pull-left pointer selected']"

		private static final def SITE_DROPDOWN 				= ".//div[@class='pull-left sector-division col-md-12 mb_10 mt_10']//select[@placeholder='Select Site']"

		private static final def  USER_ENROLLED_STATUS      = ".//md-checkbox[@role='checkbox'][@aria-disabled='true']"

		private static final def   USER_POD_PERCENTAGE      = ".//div[@class='col-md-6 col-xs-7 word-wrap pl_10 pr_0']//h4[@class='ng-binding']"

		private static final def   SORTBY_FILTER            = ".//div[@class='col-md-12 col-sm-12 pr_10 sort-section no-padding']/select"
		
		private static final def FIFTY_RESULTS              = ".//label[@for='item5']"
		//error message map (Key-Value Pair)
		def static final participantListPageErrorMessageMap = []

		def static searchForParticipant = { browser, formData ->
			browser.delay(2000)
			def result = new ParticipantListTabForm().search(browser,KPCommonPage.invitedUserEmailId)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SearchTextBoxDisplayIssue", "Search text box was not displayeda")
			}
		}

		def static search = { def browser, def email ->
			if(browser.isDisplayed(PARTICIPANT_SEARCH)){
				browser.populateField(PARTICIPANT_SEARCH,email)
				browser.delay(2000)
				browser.pressEnter(PARTICIPANT_SEARCH)
				browser.delay(2000)
				return true
			}else{
				return false
			}
		}

		def static participantDisplayedOnPage = { browser, formData ->
			browser.delay(2000)
			def result = false
			if(browser.isDisplayed(USER_EMAIL_ID)){
				def allUserEmailId = browser.getLists(USER_EMAIL_ID)
				for(int i=0;i<allUserEmailId.size();i++){
					if(allUserEmailId[i].split(":")[1].trim().equalsIgnoreCase(KPCommonPage.invitedUserEmailId.trim())){
						result = true
						break
					}
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserEmailDisplayIssue", "User email was not displayed")
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserDetailMismatchIssue", "User details do not match")
			}
		}

		def static ifOngoingTabIsHighlighted = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(ONGOING_TAB)){
				def tabText = browser.gettext(ONGOING_TAB).trim()
				if(tabText!=null && tabText.equalsIgnoreCase("Ongoing")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "TabTextMismatch", "The tab text do not match or it is null")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OngoingTabDisplayIssue", "The Ongoing tab is not displayed")
			}
		}

		def static selectSiteFilter = { browser, formData ->
			browser.delay(1000)
			if(browser.isDisplayed(SITE_DROPDOWN)){
				browser.delay(500)
				browser.selectDropdownValue(SITE_DROPDOWN,KPCommonPage.invitedUserSiteName)
				browser.delay(500)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SiteDropdownDisplayIssue", "The site dropdown was not displayed")
			}
		}

		def static podProgressPercentageIsZero = { browser,formData ->
			browser.delay(500)
			if(browser.isDisplayed(USER_POD_PERCENTAGE)){
				def percentageText = browser.gettext(USER_POD_PERCENTAGE).split(":")[1].trim()
				if(percentageText.equalsIgnoreCase("0 %")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PercentageMisMatchIssue", "User pod completion percentage do not match")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserPodCompletionPercentageDisplayIssue", "User pod completion percentage is not displayed")
			}
		}
		
		def static participantTabListSorted = { browser, formData ->
			browser.delay(600)
			if(browser.isDisplayed(FIFTY_RESULTS)){
				browser.click(FIFTY_RESULTS)
				browser.delay(1000)
				KPCommonPage.selectSortByValue(browser,SORTBY_FILTER,formData[0])
				def userNames  = browser.getLists(LEARNER_NAMES)
				def sortResult = KPCommonPage.isSorted(userNames)
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
	}
}


