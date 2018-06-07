package com.KPPhaseTwo.app.pages

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.lang.*;
import java.util.Random;
import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.tools.Browser
import java.util.Calendar;

/**
 * @author Samir
 * Date: 20/2/2017
 * Common actions 
 */
class KPCommonPage {

	private static def IMAGE_LOGO_ORG = ".//div[@class='row image-container']//img"

	private static def RADIOID = ".//*[@value='radioValue'][@name='radioName']//following-sibling::label"

	private static def RADIO_BTN_XPATH1 = ".//input[@type='radio']"

	private static def SELECT_MONTH = ".//*[@class='ui-datepicker-month']"

	private static def SELECT_YEAR  = ".//*[@class='ui-datepicker-year']"

	private static def DAY = ".//*[@id='ui-datepicker-div']//a[@href='#']"

	private static def JOB_LAST_DATE = ".//*[@id='jobLastDate']"

	private static def JOB_EXPIRY_DATE = ".//*[@id='jobExpiryDate']"

	private static def JOB_POSTING_DATE = ".//*[@id='jobPostingDate']"

	private static def SELECT_DATE = "//a[.='value']"

	private static final def EDU_QUALIFICATION = ".//input[@name='multipleSelectEducation']"

	private static final def EDU_QUALIFICATION_AUTOSELECT = "//div[@class='autocomplete-list']/ul/li"

	private static final def SKILLS = ".//input[@name='multipleSelectSkills']"

	private static final def SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"

	private static final def PRE_CERTIFICATE = ".//input[@name='multipleSelectCertificates']"

	private static final def PRE_CERTIFICATE_LIST = "//div[@class='autocomplete-list']/ul/li"

	private static final def USER_EMAIL_ID_ON_VIEW_PROFILE = ".//h5[@class='p_5 text-center profile-text word-wr ng-binding']"

	private static final def USER_NAME_ON_VIEW_PROFILE = ".//h3[@class='profile-name text-center profile-text']"

	private static final def PAGINATION_NEXT = ".//li[@class='pagination-next ng-scope']" //

	//Registration
	private static def city,industry
	private static def userRegOTP = []
	private static def orgRegOTP = []
	private static def orgEmailToEnter
	private static def individualUserEmailId = []
	private static def mobNoToEnterForReg

	public static def registrationData = [:]

	//Login
	private static def userName,passWord

	//Pods
	private static def levelLists, industryLists, podName, firstPodName, locationList, firstOrgName

	//Ongoing Pods
	private static def getFirstPodName
	private static def allOngoingPodsList = []

	//Dashboard
	private static def profileName,profileType,adminFirstName

	//Pods using filters
	private static def skillListsFilter,levelListsFilter,industryListsFilter,organizationListsFilter
	private static def minDurationListsFilter,maxDurationListsFilter,sortByListsFilter

	//Followed Organizations
	private static def followedLocationListsFilter,searchOrgSortByData,followedIndustryListsFilter
	private static def firstFollowedOrgName,orgName,followingCountDisplay

	//Manage Users
	private static def firstUserInList,profileEmailId,profilePassword,usersList,manageUsersSortByData


	//Add Admin
	private static def adminRole,adminPassword
	private static def adminSite = []
	private static def adminFirstNamee
	private static def adminMiddleName
	private static def adminLastName
	private static def adminEmail_Id
	private static def siteAdminEmail
	private static def siteAdminPassword

	//User
	private static def firstName
	private static def middleName
	private static def lastName
	private static def userAge
	private static def userMobileNo
	private static def userFullNameonProfile
	private static def userEmail

	//Created site name
	private static def addedSiteName
	private static def siteCity
	private static def siteCreateStatus = false

	//Organization
	private static def ViewOrgDataVerify = []
	private  static def podWishlistedList = []
	private static def podLst = []
	private static def OrgMobileNumber
	private static def adminEmailId

	private static def USER_PROFILE_PIC = ".//img[@class='profilePosition user-profile-picture']"
	private static def srcOrgLogoImage
	private static def srcUserProfilePic
	private static def viewSize = " ...View less"

	//Post a job
	private static def jobTitle,jobIndustry,eduQualification,jobSkills,prefferedCertificates
	private static def jobDetailList = []
	private static def repostJobFieldsData = []
	private static def clearJobDetailList = false

	//Recommended Training
	private static def noOnDashboard

	//Pagination
	private static def SELECT_FIFTY = ".//*[@id='item_label50']"
	private static def sortByData,minTimeData,maxTimeData,viewCount,viewCountOrg

	//Pod details
	private static def pod_amount,podTypeIs

	//Pod to buy
	private static def podToBuy_Entry
	private static def typeOfUser = "ADMIN"
	private static def allBoughtPodsList = []

	//Edit Organization Profile
	private static def firstNameAdmin

	//Profile wishlist
	private static def orgListsFilter

	//Serch User Connection Page
	private static def searchUserName,selectedStatus,firstConnectedUserName,firstConnectedEmailID,userToBeBlocked
	private static def emailIDNewEnrolledUser
	private static def newPassword,oldPassword,firstRecvRequestEmailID,firstRecvRequestUserName,userFullName,userEmailId
	private static def enrollUserFullName
	private static def userLocationFilter = []
	private static def OrgsListsFilter = []


	//Search Connection page
	private static def searchEmailId

	//Invited Users
	private static def invitedUserMiddleName,invitedUserLastName

	private static def invitedUserEmailIdDefault

	private static def userCreateSuccess = false

	//Edit User Profile
	private static def dataList = []
	private static def basicInfo = []
	private static def actualDataList = []
	private static def skills = []
	private static def allEducationDetails = []
	private static def WorkExperienceDetails = []
	private static def WorkExperienceJobRole = []
	private static def docName = []

	//Manage Sites
	private static def siteDetails = []
	private static boolean trainerStatus = false
	private static def siteEmail
	private static def sitePhone

	//Admin flag
	private static boolean adminFlag = false
	private static boolean inviteUserFlag = false

	//View Progress Page
	private static def ongoingCount,firstPodNameInGraph,firstPodPercent,firstPodNameInList,podPercentInGraph
	private static def podProgressSortBy,RecentlyBoughtPod

	//Invited Users
	private static def invitedUserFirstName,invitedUserEmailId,invitedUserPassword,invitedUserSiteName

	//Approve License Page
	private static def getEndDateInYears
	private static def allTabLicenseList = []
	private static def activeTabLicenseList = []
	private static def renewStatus

	//Post Feed
	private static def firstFeedName,charCountOfPostDesc,likesCount
	private static def usersWhoLikedThePost,userComments
	private static def firstReplyFisrtComment,firstReplyOldCount,orgNameInPostFeed,postAddedToWishList

	//View Public profile page
	private static def fullNameOfUser

	//Bulk License
	private static def podNameForBulkLicense,podLearnersInput,podAmountInDetailsPage
	private static def bulkPodList = []
	private static def totalPodSelectedCount

	//Select next date
	private static def selectNextDateStatus = false

	//To return Failure outcome
	static def returnFailureOutcome(def browser, def fileName, def message){
		println message
		browser.takeScreenShot(fileName)
		return new FailureOutcome(message)
	}

	//get coloumns from excel filed which has data
	static def getFinalFeilds(def data, def FEILDS){
		def formFeild = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formFeild.add(FEILDS[i])
			}
		}
		formFeild
	}

	//get coloumns from excel filed which has data
	static def getFinalData(def data, def FIELDS){
		def formData = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formData.add(data[i])
			}
		}
		formData
	}

	//To get Radio Button Id
	static def getRadioButtonField(def browser, def field, def data){
		def radioId
		def radioValue = browser.getLists(field, "value")
		def radioName = browser.getLists(field,"name")
		for(int i=0; i<= radioValue.size() - 1; i++){
			if(radioValue[i].trim().equalsIgnoreCase(data.trim())){
				def value = radioValue[i].trim()
				radioId = RADIOID.replace("radioValue", value)
				radioId = radioId.replace("radioName", radioName[i].trim())
				break
			}
		}
		return radioId
	}

	//To select from the auto complete
	static def selectAutoComplete(def browser, def autoCompleteList, def dataToSelect){
		def xpathToSelect
		def lists = browser.getLists(autoCompleteList)
		xpathToSelect = browser.getListElements(autoCompleteList)
		for(int i =0; i<= lists.size()-1;i++){
			if(lists[i].trim().equalsIgnoreCase(dataToSelect.trim())){
				browser.delay(3000)
				browser.clickElement(xpathToSelect[i])
				browser.delay(2500)
				break
			}
		}
	}


	//To select city with state from the auto complete
	static def selectAutoCompleteForCity(def browser, def autoCompleteList, def stateAutoCompleteList, def dataToSelect){
		def xpathToSelect, stateSelect
		def lists = browser.getLists(autoCompleteList)
		xpathToSelect = browser.getListElements(autoCompleteList)
		stateSelect = browser.getLists(stateAutoCompleteList)
		for(int i =0; i<= lists.size()-1;i++){
			def wholeCityName = lists[i].trim() + ", "+  stateSelect[i].trim()
			if(wholeCityName.trim().equalsIgnoreCase(dataToSelect.trim())){
				browser.delay(3000)
				browser.clickElement(xpathToSelect[i])
				browser.delay(1500)
				break
			}
		}
	}

	//to generate unique email address
	public static def  generateRandomEmailAddress(def data){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		data = new StringBuilder(data).insert(data.indexOf("@"), randomInt).toString();
		return data;
	}

	//To generate a random string
	public static def generateRandomString(){
		def CHAR_LIST ="abcdefghijklmnopqrstuvwxyz";
		def RANDOM_STRING_LENGTH = 3;
		StringBuffer randStr = new StringBuffer();
		for(int i=0; i<RANDOM_STRING_LENGTH; i++){
			int number = getRandomNumber(CHAR_LIST);
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static def getRandomNumber(def charList) {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(charList.length())
		if (randomInt - 1 == -1) {
			return randomInt
		} else {
			return randomInt - 1
		}
	}



	//to generate unique phone no up to 10 digits
	public static def generateRandomCellNo(){
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return number;
	}

	//To get all the data of pagination
	static def paginationData(def browser, def xpathForData, def attributeValue){
		def listDataPerPage = []
		def allDataList = []
		if(browser.isDisplayed(SELECT_FIFTY)){
			browser.click SELECT_FIFTY
		}
		if(attributeValue.equals("textData")){
			listDataPerPage = browser.getLists(xpathForData)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getLists(xpathForData)
				allDataList.addAll(listDataPerPage)
			}
		}
		else if(attributeValue.equals("xpaths")){
			listDataPerPage = browser.getListElements(xpathForData)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getListElements(xpathForData)
				allDataList.addAll(listDataPerPage)
			}
		}
		else {
			listDataPerPage = browser.getLists(xpathForData,attributeValue)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getLists(xpathForData,attributeValue)
				allDataList.addAll(listDataPerPage)
			}
		}
		println allDataList
		allDataList
	}

	//try to remove the selected skill and organization from the filter pod search
	public static def removeSelectedAutosuggest(def browser,def autoSuggestList,def removeMarkList,def dataToRemove){
		def xpathToRemove
		def lists = browser.getLists(autoSuggestList)
		xpathToRemove = browser.getListElements(removeMarkList)
		for(int i =0; i<= lists.size()-1;i++){
			if(lists[i].trim().equalsIgnoreCase(dataToRemove.trim())){
				browser.delay(3500)
				browser.clickElement(xpathToRemove[i])
				browser.delay(3500)
				break
			}
		}
	}

	//to get all the pod names from Search pod list page in a list
	public static def getPodTextList(def browser,def element, def podList,def fiftyRes){
		def allResult
		browser.scrollToElement2(element)
		browser.delay(2000)
		if(browser.isDisplayed(podList)){
			if(browser.isDisplayed(fiftyRes)){
				//browser.click fiftyRes
				browser.delay(3000)
				allResult = browser.getLists(podList)
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
			}
		}else{
			return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
		}

		return allResult
	}

	//To check the list is alphabetically sorted or not
	public static def isSorted(def lst){
		def sorted = true;
		def temp
		if(lst.size()>0){

			for (int i = 1; i < lst.size(); i++) {
				/*if(lst.get(i-1).length()>lst.get(i).length()){
				 temp     = lst[i-1]
				 lst[i-1] = lst[i]
				 lst[i]   = temp
				 }*/
				println "The result is::::: "+lst.get(i-1).compareToIgnoreCase(lst.get(i))
				if(lst.get(i-1).compareToIgnoreCase(lst.get(i)) > 0)
				{
					println " Comparing => "+lst.get(i-1)+" with "+lst.get(i)
					sorted = false;
				}
			}
		}
		return sorted
	}


	public static def isPodWishlistPageEmpty(def browser, def formMsgPath){
		def errorMsg = browser.isDisplayed(formMsgPath)
		if(errorMsg){
			return errorMsg
		}else{
			return errorMsg
		}

	}

	//to check number of people are sorted in descending order or not
	public static def isSortedByNoOfPeople(def browser, def NO_OF_PEOPLE){
		browser.delay(1000)
		if(browser.isDisplayed(NO_OF_PEOPLE)){
			def result = false
			def noOfPeople=[]
			def allResult = browser.getLists(NO_OF_PEOPLE)
			def allResultElement = browser.getListElements(NO_OF_PEOPLE)
			outerloop:for(int i = 0; i < allResult.size(); i++){
				browser.scrollToElement(allResultElement[i])
				browser.delay(500)
				String[] duration = allResult[i].split(":")
				noOfPeople.add(Integer.parseInt(duration[1].trim()))
			}
			for (int i = 0; i < noOfPeople.size(); i++) {
				if (noOfPeople.get(i) < noOfPeople.get(i+1)) {
					return false
				}
			}
			return true
		}
		else{
			return KPCommonPage.returnFailureOutcome(browser, "NumberOfPeopleIssue", "Sort By No. of people is not working correct")
		}

	}


	//To Pick the date
	public static def datePicker(def browser, def formDate){
		def dateValue= formDate.split ("-")
		def dayValue = Integer.parseInt(dateValue.last()).toString()
		def monthValue = Integer.parseInt(dateValue[1])-1
		def yearValue = dateValue.first()
		browser.selectDropdownValue(SELECT_YEAR,yearValue)
		def monthInString=  browser.getMonthForInt(monthValue)
		browser.selectDropdownValue(SELECT_MONTH,monthInString)
		browser.selectDate(DAY,dayValue)
	}



	//To select the date from calender popup
	static def userDatePicker (def browser, def date){
		if(date.contains("/"))
		{
			def splittedDate=date.split("/")
			def dayValue = splittedDate[0]
			def monthValue = splittedDate[1]
			def yearValue = splittedDate[2]
			browser.selectDropdownValue(SELECT_YEAR, yearValue)

			//selecting the month
			def monthNo = Integer.parseInt(monthValue)
			def	monthName= browser.getMonthFromNo(monthNo)
			def month = monthName.substring(0, Math.min(monthName.length(), 3));
			browser.selectDropdownValue(SELECT_MONTH, month)
			browser.delay(2000)
			//selecting the date

			def CHANGED_DATE = SELECT_DATE.replace('value', dayValue)
			browser.click CHANGED_DATE
			browser.delay(1500)
			//browser.selectingDate(SELECT_DATE, dayValue)
		}
		else
			println "Not a valid date"
	}


	public def static getFullName(def firstName,def middleName,def lastName){
		def fullName
		if(!firstName.equals("") && firstName!=null){
			if(!middleName.equals("") && middleName!=null){
				fullName = firstName +" "+ middleName
			}else{
				fullName = firstName
			}
			if(!lastName.equals("") && lastName!=null){
				fullName = fullName +" "+ lastName
			}else{
				fullName
			}
		}
		return fullName.trim()
	}



	//Method which will be used to click on add to wishlist only in Search pods page.
	public static def clickAddToWishlist(def browser, def ADD_TO_WISHLIST, def wishlistIcon){

		if(browser.getTitle().equals("Search Pods")){
			if(browser.isDisplayed(ADD_TO_WISHLIST)){

				browser.clickElement wishlistIcon
			}
		}
	}


	//Wait for new Window and switch to it
	public static def waitAndSwitchToNewWindow(def browser, def formData){
		def currentHandle = browser.getWindowHandle()
		def newHandle = null
		def allWindowHandles = browser.getWindows()
		//Wait for 20 seconds for the new window and throw exception if not found
		for (int i = 0; i < 20; i++) {
			if (allWindowHandles.size() > 1) {
				for (String allHandlers : allWindowHandles) {
					if (!allHandlers.equals(currentHandle))
						newHandle = allHandlers;
				}
				browser.switchTowindow(newHandle)
				def title=browser.getTitle()
				break;
			} else {
				browser.delay(1000)
			}
		}
		if (currentHandle == newHandle) {
			return KPCommonPage.returnFailureOutcome(browser, "NewWindowNotFoundIssue", "Time out - No window found");
		}
	}

	//To get the current date
	public static def getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return formatter.format(date)
	}

	//To get the future date
	def static getFutureDate(def data){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
		Calendar c = Calendar.getInstance()
		c.setTime(new Date())
		c.add(Calendar.DATE, data)
		def futureDate = sdf.format(c.getTime())
		return futureDate
	}

	def static selectSortByValue(def browser, def dropDownElement , def valueToSelect){
		browser.delay(500)
		browser.selectDropdownValue(dropDownElement,valueToSelect)
		browser.delay(4000)
	}
}




