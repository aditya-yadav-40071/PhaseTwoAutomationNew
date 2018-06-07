package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created By Bishnu Das
 */

final class ClassRoomPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new ClassRoomForm().populateFields(browser, formData);
		}
	}

	//to get the class room type
	def static getClassroomType = {browser, formData ->
		new ClassRoomForm().getClassroomType browser, formData
	}

	//to check when pod is not bought ,error message is displayed or not
	def static errorMsgDisplayed = {browser, formData ->
		new ClassRoomForm().errorMsgDisplayed browser, formData
	}

	//to check that navigated page is the correct classroom type page
	def static correctClassRoomType = {browser, formData ->
		new ClassRoomForm().correctClassRoomType browser, formData
	}

	//wait till video timmer is displayed and will wait till video plays 04 seconds
	def static waitTillVideoIsDisplayed = {browser, formData ->
		new ClassRoomForm().waitTillVideoIsDisplayed browser, formData
	}



	static final class ClassRoomForm extends WebForm {

		//Class room form elements
		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def PODNAME_PODPROGRESS = "//div[@class='col-sm-6 col-md-6 mb_10']/p"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def OVERVIEW = "//a[text()='Overview']"

		private static final def ENTER_CLASROOM = "//button[text()='Enter Classroom']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"

		private static final def MEDIA_TYPE = "//div[contains(@ng-if,'introductionMediaType')]"

		private static final def IMAGE_POD_TYPE = "//img[contains(@src,'deskBG.png')]"

		private static final def ENTERBTN_IMAGE_TYPE = "//area[@shape='poly']"

		private static final def NXVIDEO_FRAME = "//div[@class='container ng-scope']//div[@id='interactive_content_load']"

		private static final def WITHOUT_BUY_ERRPATH = "//div[@id='main_page']/div[2]/div[3]/span"

		private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"

		private static final def FIRST_VIDEO_LINK = "//div[@id='siemensCourseContainer']/div[1]/div[3]/div[2]/div/div/div[1]/div[1]/span/div/div[1]//a/span"

		private static final def FIRST_IMAGE_LINK = "//div[@class='list-group panel']/ul[1]/li[1]/a"

		private static final def CHAPER_1 = "//div[@id='siemensCourseContainer']/div[1]/div[3]/div[2]/div/div/div[1]"

		private static final def VIDEO_START_TIME = "//div[@class='videogular-container']//vg-controls//vg-time-display[1]"

		private static final def FIRST_CHAPTER_IMAGE = ".//*[@id='sbImage']"

		private static final def FIRST_CHAPTER = "//div[@id='siemensCourseContainer']/div[1]/div[3]/div[2]/div/div/div[1]/a/following-sibling::div"

		private static final def FIRST_CHAP_SUBTOPICS = "//div[@id='siemensCourseContainer']/div[1]/div[3]/div[2]/div/div/div[1]/a/following-sibling::div/span/div/div"

		private static final def WITHOUT_BUY_ERRMSG = "You can view only 2 links without buying the pod"

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		//error message map (Key-Value Pair)
		def static final classRoomPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						def searchInput = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.podName = searchInput
					}

					browser.delay(1000)
				}
			}
			return outcome
		}


		//to get the classroom type
		public static final def getClassroomType = {browser, formData ->
			def podType
			browser.delay(2000)
			if(browser.isDisplayed(MEDIA_TYPE)){
				podType = browser.gettext(MEDIA_TYPE,"ng-if").trim()
				KPCommonPage.podTypeIs = podType
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UnableToFetchMediaType", "Not able to Identify Media Type")
			}
		}

		//to verify the error message when pod is not bought
		public static final def errorMsgDisplayed = { browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(WITHOUT_BUY_ERRPATH)){
				def actualErrMsg = browser.gettext(WITHOUT_BUY_ERRPATH).trim()
				if(actualErrMsg.equalsIgnoreCase(WITHOUT_BUY_ERRMSG)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "InCorrectErrorMsgIssue", "Incorrect error message is appearing.")
				}
			}
		}

		//to check the classroom type
		public static final def correctClassRoomType = {browser, formData ->
			if(KPCommonPage.podTypeIs.equalsIgnoreCase("courseDetail.introductionMediaType == 3")){
				def isType3Verified = false
				browser.scrollToElement2(ENTER_CLASROOM)
				if(browser.checkEnabled(ENTER_CLASROOM)){
					browser.click ENTER_CLASROOM
					browser.delay(8000)
					def firstChapSubTopicsHidden = browser.gettext(FIRST_CHAPTER,"class")
					def subTopicsPresent = browser.checkEnabled(FIRST_CHAP_SUBTOPICS)
					if(!firstChapSubTopicsHidden.contains("hide")){
						if(browser.isDisplayed(FIRST_VIDEO_LINK)){
							browser.click FIRST_VIDEO_LINK
							browser.delay(3000)
							ClassRoomPage.waitTillVideoIsDisplayed browser, formData
							isType3Verified = true
						}
					}else{
						if(subTopicsPresent==null){
							browser.delay(500)
							browser.click FIRST_VIDEO_LINK
							browser.delay(3000)
						}else{
							browser.click CHAPER_1
							browser.delay(500)
							browser.click FIRST_VIDEO_LINK
							browser.delay(3000)
						}
						ClassRoomPage.waitTillVideoIsDisplayed(browser, formData)
						isType3Verified = true
					}
				}
				if(isType3Verified){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "MP4TypePodVideoDisplayIssue", "MP4 Video classroom structure display is not Appearing.")
			}else if(KPCommonPage.podTypeIs.equalsIgnoreCase("courseDetail.introductionMediaType == 1")){
				def imageTypeVerified = false
				browser.scrollToElement2(ENTER_CLASROOM)
				if(browser.checkEnabled(ENTER_CLASROOM)){
					browser.click ENTER_CLASROOM
					browser.delay(4000)
					browser.click FIRST_IMAGE_LINK
					browser.delay(2000)
					browser.switchToIframe(0)
					if(browser.isDisplayed(FIRST_CHAPTER_IMAGE)){
						browser.delay(4000)
						imageTypeVerified = true
					}
					if(imageTypeVerified){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "ImageTypePodStructureDisplayIssue", "In Image Type Pod Structure is not Verified.")
					}
				}
			}else if(KPCommonPage.podTypeIs.equalsIgnoreCase("!courseDetail.introductionMediaType")){
				def nXTypeVerified = false
				browser.scrollToElement2(ENTER_CLASROOM)
				if(browser.checkEnabled(ENTER_CLASROOM)){
					browser.click ENTER_CLASROOM
					browser.delay(7000)
					def firstChapSubTopicsHidden = browser.gettext(FIRST_CHAPTER,"class")
					def subTopicsPresent = browser.checkEnabled(FIRST_CHAP_SUBTOPICS)
					if(!firstChapSubTopicsHidden.contains("hide")){
						if(browser.isDisplayed(FIRST_VIDEO_LINK)){
							browser.click FIRST_VIDEO_LINK
							browser.delay(12000)
							if(browser.isDisplayed(NXVIDEO_FRAME)){
								nXTypeVerified = true
							}
						}
					}else{
						if(subTopicsPresent!=null){
							browser.click CHAPER_1
							browser.delay(500)
							browser.click FIRST_VIDEO_LINK
							browser.delay(3000)
						}else{
							browser.delay(500)
							browser.click FIRST_VIDEO_LINK
							browser.delay(3000)
						}
						if(browser.isDisplayed(NXVIDEO_FRAME)){
							nXTypeVerified = true
						}
					}
				}
				if(nXTypeVerified){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "NXVideoFrameDisplayIssue", "NX Video Frame is not Appearing")
				}
			}
		}


		public static def waitTillVideoIsDisplayed = { browser ,formData ->

			for(int i=0;i<20;i++){
				if (browser.isDisplayed(VIDEO_START_TIME) && Integer.parseInt(browser.gettext(VIDEO_START_TIME).split(":")[1])>=04) {
					return new SuccessOutcome()
					break;
				} else {
					browser.delay(500)
				}
			}
		}
	}
}