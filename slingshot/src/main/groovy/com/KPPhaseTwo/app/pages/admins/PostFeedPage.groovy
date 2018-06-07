package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu Das on 20/02/2018
 */

final class PostFeedPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("comment")){
			new PostFeedForm().populateFields(browser, formData);
		}else if(formKey.equals("reply")){
			new PostFeedReplyForm().populateFields(browser, formData);
		}
	}


	//Override
	def submit = {browser, formKey, formData ->
		if(formKey.equals("comment")){
			new PostFeedForm().submit(browser, formData)
		}else if(formKey.equals("reply")){
			new PostFeedReplyForm().submit(browser, formData);
		}
	}


	def static postsListDisplayed = {browser, formData ->
		new PostFeedForm().postsListDisplayed(browser, formData)
	}

	def static storeFirstPostTitle = {browser, formData ->
		new PostFeedForm().storeFirstPostTitle browser, formData
	}


	def static getCharCountOfPostDescription = {browser, formData ->
		new PostFeedForm().getCharCountOfPostDescription browser, formData
	}


	def static correctFeedDisplayed = {browser, formData ->
		new PostFeedForm().correctFeedDisplayed browser, formData
	}

	def static viewMoreLink = {browser, formData ->
		new PostFeedForm().viewMoreLink browser, formData
	}


	def static getTheLikesCount = {browser, formData ->
		new PostFeedForm().getTheLikesCount(browser, formData)
	}

	def static clickOnLikeIcon = {browser, formData ->
		new PostFeedForm().clickOnLikeIcon(browser,formData)
	}

	def static isUserAlreadyLikedThisPost = {browser, formData ->
		new PostFeedForm().isUserAlreadyLikedThePost(browser,formData)
	}


	def static correctCommentDisplayed = {browser, formData ->
		new PostFeedForm().correctCommentDisplayed browser, formData
	}

	def static correctReplyDisplayed = {browser, formData ->
		new PostFeedReplyForm().correctReplyDisplayed browser, formData
	}

	def static correctReplyCountDisplayed = {browser, formData ->
		new PostFeedReplyForm().correctReplyCountDisplayed browser, formData
	}

	def static likeOperationOnReply = {browser, formData ->
		new PostFeedReplyForm().likeOperationOnReply browser, formData
	}

	def static getFullNameOfUser = {browser, formData ->
		new PostFeedReplyForm().getFullNameOfUser browser, formData
	}

	def static correctPostNameInDialogbox = {browser, formData ->
		new PostFeedSharesForm().correctPostNameInDialogbox browser, formData
	}

	def static checkFollowedFunctionality = {browser, formData ->
		new PostFeedSharesForm().checkFollowedFunctionality browser, formData
	}

	def static checkChangeOfFollowToUnfollow = {browser, formData ->
		new PostFeedSharesForm().checkChangeOfFollowToUnfollow browser, formData
	}

	def static followedOrgDisplayed = {browser, formData ->
		new PostFeedSharesForm().followedOrgDisplayed browser, formData
	}
	
	def static addToWishListAPostFeed = {browser, formData ->
		new PostFeedSharesForm().addToWishListAPostFeed browser, formData
	}
	
	def static wishListedPostDisplayed = {browser, formData ->
		new PostFeedSharesForm().wishListedPostDisplayed browser, formData
	}
	
	def static sortByAsRecentlyAdded = {browser, formData ->
		new PostFeedSharesForm().sortByAsRecentlyAdded browser, formData
	}
	









	static final class PostFeedForm extends WebForm {

		//Post Feed form Elements

		private static final def NAME = "//div[label[normalize-space(text())='Name :']]/following-sibling::div"

		private static final def POSTS_LIST = "//h3[@class='primary_heading']/a"

		private static final def POSTNAME_IN_DETAIL = "//h3[contains(@class,'primary_heading')]"

		private static final def ADD_TO_WISHLIST = "//span[contains(text(),'wishlist')]"

		private static final def COMMENTS_COUNT = "//span[i[contains(@class,'kp-comment')]]"

		private static final def SHARE_COUNT = "//span[i[contains(@class,'kp-share')]]"

		private static final def LIKES_COUNT = "//span[i[a[contains(@class,'kp-thumbs-up')]]]"

		private static final def FIRST_VIEW_MORE = "//div[@ng-repeat='post in postResults '][1]//a[text()='(View more)']"

		private static final def FIRST_COMMENTBOX = "//div[@ng-repeat='post in postResults '][1]//input"

		private static final def FIRST_COMMENT_BTN = "//div[@ng-repeat='post in postResults '][1]//button[text()='Comment']"

		private static final def LATEST_COMMENT_PROFILENAME = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[1]"

		private static final def LATEST_COMMENT_CONTENT = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[2]"

		private static final def FIRST_SHOWMORE = "//div[@ng-repeat='post in postResults '][1]//div[div[input]]/following-sibling::div//a"

		private static final def POST_DESCRIPTION = "//form[@name='newsArticle']/div[2]//p"

		private static final def ERROR_MSG = ".//*[@id='main_page']/div[1]/div/span"

		private static final def ACT_ERROR_MSG = "Post/Comment/Reply can be liked only once"


		private static final def FIELDS = [FIRST_COMMENTBOX]

		private static final def ERROR_MESSAGE_FIELDS = []

		//error message map (Key-Value Pair)
		def static final postFeedFormErrorMessageMap = []



		//To enter data
		def static final populateFields = { browser, formData ->
			println ("PostFeedForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				//outcome = WebForm.enterData(browser, formData, FIELDS, FIRST_COMMENT_BTN, WAIT_REQ_FIELDS)
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FIRST_COMMENTBOX)){
						def comments = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.userComments = comments
						println "Comments Entered"
					}
					browser.delay(1000)
				}

			}
			return outcome
		}




		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			submitForm browser, FIELDS, FIRST_COMMENT_BTN, data, ERROR_MESSAGE_FIELDS
			def outcome = new SuccessOutcome();
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			} else {
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_COMMENTBOX))
			}
		}






		def static final postsListDisplayed = {browser, formData ->
			if(browser.getListElements(POSTS_LIST).size()>0){
				println "Posts Feed List Displayed."
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "PodFeedListDisplayIssue", "Pods Feed List Does not Display")
		}


		def static final storeFirstPostTitle = {browser, formData ->
			if(browser.isDisplayed(POSTS_LIST)){
				if(browser.getLists(POSTS_LIST)[0].toString().length()>0){
					KPCommonPage.firstFeedName = browser.getLists(POSTS_LIST)[0].trim()
					println "First Post Name Stored Successfully."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "FirstPostHeadingIsEmptyIssue", "First Post Heading does not Display.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "PodFeedListDisplayIssue", "Pods Feed List Does not Display")
		}


		//to check correct Post details page is displayed
		def static final correctFeedDisplayed = {browser, formData ->
			if(browser.isDisplayed(POSTNAME_IN_DETAIL)){
				def postNameInPostDetails = browser.gettext(POSTNAME_IN_DETAIL).trim()
				if(postNameInPostDetails.equalsIgnoreCase(KPCommonPage.firstFeedName)){
					println "Correct Post Details Page."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "PostTitleDoesNotMatchIssue", "Post Title Does Not Match.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "PodTitleDisplayIssue", "Post Title does not display.")
		}


		def static final getCharCountOfPostDescription = {browser, formData ->
			if(!browser.isDisplayed(POST_DESCRIPTION)){
				println " POST desc does not display."
				return KPCommonPage.returnFailureOutcome(browser, "PostTitleDoesNotMatchIssue", "Post Title Does Not Match.")
			}else{
				KPCommonPage.charCountOfPostDesc =  browser.gettext(POST_DESCRIPTION).trim().length().toInteger()
				println "Character Count is Stored."
				return new SuccessOutcome()
			}
		}


		def static final viewMoreLink = {browser, formData ->
			if(KPCommonPage.charCountOfPostDesc.toInteger() > 700){
				if(browser.isDisplayed(FIRST_VIEW_MORE)){
					browser.click(FIRST_VIEW_MORE)
					browser.delay(4000)
					if(browser.isDisplayed(POST_DESCRIPTION)){
						println "Successfully View More Link is Displayed and clicked."
						return new SuccessOutcome()
					}
				}else
					return KPCommonPage.returnFailureOutcome(browser, "ViewCountDoesNotAppearIssue", "Plz check, View More link Does not appear.")
			}else{
				if(!browser.isDisplayed(FIRST_VIEW_MORE)){
					println "Successfully View More link does not Display."
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "ViewCountShouldNotAppear", "Charcter count is less than 700, View count should not display.")
			}
		}


		def static getTheLikesCount = {browser, formData ->
			if(browser.isDisplayed(LIKES_COUNT)){
				KPCommonPage.likesCount = browser.gettext(LIKES_COUNT).trim().split(" ")[0].trim()
				println "Successfully saved likes count"
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "LikesCountDisplayIssue", "Likes Count does not Display.")
			}
		}

		def static clickOnLikeIcon = {browser, formData ->

			browser.click(LIKES_COUNT)
			browser.delay(2000)
			def afterClickCount = browser.gettext(LIKES_COUNT).trim().split(" ")[0].trim()
			if(afterClickCount.toInteger() > KPCommonPage.likesCount.toInteger()){
				KPCommonPage.usersWhoLikedThePost = formData[0].toString().trim()
				println "Successfully Liked a Post."
				return new SuccessOutcome()
			}else{
				browser.delay(300)
				if(browser.isDisplayed(ERROR_MSG) && browser.gettext(ERROR_MSG).trim().equalsIgnoreCase(ACT_ERROR_MSG)){
					println "Error msg on Already liked Post is Verified."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ErrorMsgOnLikeIssue", "Error Message does not Appear on click of Like.")
				}
			}

		}

		//Latest Posted Comment Content Verification
		def static final correctCommentDisplayed = {browser, formData ->
			if(browser.isDisplayed(LATEST_COMMENT_CONTENT)){
				def latestComment =  browser.gettext(LATEST_COMMENT_CONTENT).trim()
				def latestCommentProfileName = browser.gettext(LATEST_COMMENT_PROFILENAME).trim().split(":")[0].trim()
				if(latestComment.equalsIgnoreCase(KPCommonPage.userComments) &&
				latestCommentProfileName.equalsIgnoreCase(KPCommonPage.fullNameOfUser)){
					println "Successfully Verified Posted Comment and Profile Name"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PostedCommentContentNotEqual", "Displayed Comment Or Profile Name is not Verified Successful.")
				}
			}
		}




	}

	static final class PostFeedReplyForm extends WebForm {


		private static final def FIRST_REPLY_LINK  = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[text()='Reply']"

		private static final def FIRST_REPLY_LIKE_COUNT = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[2]/div[1]/div[2]/span[text()=' Likes ']/preceding-sibling::span[1]"

		private static final def FIRST_REPLY_LIKE_LINK = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[2]/div[1]/div[2]//span[text()=' Likes ']"

		private static final def FIRST_REPLY_COUNT = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[text()='Reply']/preceding-sibling::span[1]"

		private static final def REPLY_BOX = "//div[@ng-repeat='post in postResults '][1]//input[@ng-model='reply']"

		private static final def FIRST_REPLY_BTN = "//div[@ng-repeat='post in postResults '][1]//button[text()='Reply']"

		private static final def LATEST_COMMENT_PROFILENAME = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[1]"

		private static final def LATEST_COMMENT_CONTENT = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[1]/div[2]/span[2]"

		private static final def LATEST_REPLY_CONTENT = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[2]/div[1]/div[2]//span[2]"

		private static final def LATEST_REPLY_PROFILENAME = "//div[@ng-repeat='post in postResults '][1]//div[@ng-model='news'][1]/div[2]/div[1]/div[2]//span[1]"

		private static final def USER_FULL_NAME = "//h3[@class='profile-name text-center profile-text']/span"

		private static final def ERROR_MSG = ".//*[@id='main_page']/div[1]/div/span"

		private static final def ACT_ERROR_MSG = "Post/Comment/Reply can be liked only once"

		private static final def FIELDS = [REPLY_BOX]


		private static final def ERROR_MESSAGE_FIELDS = []

		//error message map (Key-Value Pair)
		def static final postFeedReplyFormErrorMessageMap = []



		//To enter data
		def static final populateFields = { browser, formData ->
			println ("PostFeedReplyForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				//outcome = WebForm.enterData(browser, formData, FIELDS, FIRST_REPLY_BTN, WAIT_REQ_FIELDS)
				KPCommonPage.firstReplyOldCount = browser.gettext(FIRST_REPLY_COUNT).trim().toInteger()
				browser.click(FIRST_REPLY_LINK)
				browser.delay(1000)
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(REPLY_BOX)){
						def reply = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.firstReplyFisrtComment = reply
						println "Reply Entered"
					}
					browser.delay(1000)
				}
			}
			return outcome
		}



		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			submitForm browser, FIELDS, FIRST_REPLY_BTN, data, ERROR_MESSAGE_FIELDS
			def outcome = new SuccessOutcome();
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			} else {
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_REPLY_COUNT))
			}
		}



		def static final correctReplyDisplayed = {browser, formData ->
			if(browser.isDisplayed(LATEST_REPLY_CONTENT) && browser.isDisplayed(LATEST_REPLY_PROFILENAME)){
				def latestReplied =  browser.gettext(LATEST_REPLY_CONTENT).trim()
				def latestRepliedProfileName = browser.gettext(LATEST_REPLY_PROFILENAME).trim().split(":")[0].trim()
				if(latestReplied.equalsIgnoreCase(KPCommonPage.firstReplyFisrtComment) &&
				latestRepliedProfileName.equalsIgnoreCase(KPCommonPage.fullNameOfUser)){
					println "Successfully Verified Replied Comment and Profile Name."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PostedCommentContentNotEqual", "Displayed Comment is not Verified Successful.")
				}
			}
		}


		def static final correctReplyCountDisplayed = {browser, formData ->

			if(browser.isDisplayed(FIRST_REPLY_COUNT)){
				def newReplyCount = browser.gettext(FIRST_REPLY_COUNT).trim().toInteger()
				if(newReplyCount==KPCommonPage.firstReplyOldCount+1){
					println "Successfully Verified Replied Count."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ReplyCountIssue", "Reply Count Display is Not proper")
				}
			}
		}


		def static final likeOperationOnReply = {browser, formData ->

			if(browser.isDisplayed(FIRST_REPLY_LIKE_LINK)){
				def beforeClickLikesCount = browser.gettext(FIRST_REPLY_LIKE_COUNT).trim().toInteger()
				browser.click(FIRST_REPLY_LIKE_LINK)
				browser.delay(2000)
				def afterClickLikesCount = browser.gettext(FIRST_REPLY_LIKE_COUNT).trim().toInteger()
				if(afterClickLikesCount > beforeClickLikesCount){
					println "Successfully Liked the First Reply."
					return new SuccessOutcome()
				}else{
					browser.delay(300)
					if(browser.isDisplayed(ERROR_MSG) && browser.gettext(ERROR_MSG).trim().equalsIgnoreCase(ACT_ERROR_MSG)){
						println "Error msg on Already liked Post is Verified."
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "ErrorMsgOnLikeIssue", "Error Message does not Appear on click of Like.")
				}
			}
		}



		def static getFullNameOfUser = {browser, formData ->
			def fullName=""
			if(browser.isDisplayed(USER_FULL_NAME)){
				def nameText = browser.getLists(USER_FULL_NAME)
				for(int i=0;i<nameText.size();i++){
					if(nameText[i]!=""){
						fullName = fullName+nameText[i].trim()+" "
					}
				}
				KPCommonPage.fullNameOfUser = fullName.trim()
				println "Successfully Saved Full Name of a user."
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "UserNameDisplayIssue", "User Name does not displayed.")
		}



	}

	static final class PostFeedSharesForm extends WebForm {

		//Sharing Elements
		private static final def POSTS_LIST = "//h3[@class='primary_heading']/a"
		
		private static final def SHARE_POST_TITLE= "//h4[@class='modal-title ng-scope']"

		private static final def ACT_POST_TITLE_IN_POPUP = "//div[@id='chooseConnectionsModal']//a"

		private static final def FOLLOW_LINK = "//div[@ng-repeat='post in postResults '][1]//a[i[@class='kp-follow orange mr_5']]"

		private static final def FOLLOWED_ORG = "//a[@class='content-name job-title pointer title-name blue capitalize ng-binding']"
		
		private static final def ADD_TO_WISHLIST = "//div[@ng-repeat='post in postResults '][1]//span[contains(text(),'wishlist')]"
		
		private static final def SORT_BY = "//select[@ng-model='sortBy']"




		def static final correctPostNameInDialogbox = {browser, formData ->
			if(browser.isDisplayed(SHARE_POST_TITLE)){
				if(browser.isDisplayed(ACT_POST_TITLE_IN_POPUP)){
					def title = browser.gettext(ACT_POST_TITLE_IN_POPUP).trim()
					if(title.equalsIgnoreCase(KPCommonPage.firstFeedName)){
						println "Successfully Verified Post Name in Share popup"
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "PostNameNotMatchIssueInShare", "Post Name Does Not Match in Share Popup")
				}
			}
		}


		def static final checkFollowedFunctionality = {browser, formData ->
			if(browser.isDisplayed(FOLLOW_LINK)){
				def orgNameText
				def followOrUnfollow = browser.gettext(FOLLOW_LINK).split(" ")[0].trim()
				if(followOrUnfollow.equalsIgnoreCase("unfollow")){
					orgNameText = browser.gettext(FOLLOW_LINK).split(" ",2)[1].trim()
					KPCommonPage.orgNameInPostFeed = orgNameText
					println "Already Followed"
					return new SuccessOutcome()
				}else if(followOrUnfollow.equalsIgnoreCase("follow")){
					orgNameText = browser.gettext(FOLLOW_LINK).split(" ",2)[1].trim()
					KPCommonPage.orgNameInPostFeed = orgNameText
					browser.click(FOLLOW_LINK)
					browser.delay(5000)
					def restult = PostFeedPage.checkChangeOfFollowToUnfollow(browser, formData)
					if(restult.equals("UnFollow")){
						println "Successfully Changed from Follow to Unfollow."
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "FollowToUnfollowDoesnotChange", " Follow UnFollow Link is not Dynamic")
					}
				}

			}
		}



		def static final checkChangeOfFollowToUnfollow(def browser,def formData){
			def followOrUnfollowText = null
			if(browser.isDisplayed(FOLLOW_LINK)){
				followOrUnfollowText = browser.gettext(FOLLOW_LINK).split(" ")[0].trim()
			}
			return followOrUnfollowText
		}


		def static final followedOrgDisplayed = {browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(FOLLOWED_ORG)){
				def name = browser.gettext(FOLLOWED_ORG)
				if(KPCommonPage.orgNameInPostFeed.equalsIgnoreCase(name)){
					println "Followed Organization displayed Successfully"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FollowedOganizationIssue", "Followed Organization is not same in the followed org page.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FollowedDisplayIssue", "Followed Organization is not displayed in the followed Organization page.")
			}
		}
		
		
		def static final  addToWishListAPostFeed = {browser, formData ->
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				if(browser.gettext(ADD_TO_WISHLIST).trim().contains("Add")){
					browser.click(ADD_TO_WISHLIST)
					browser.delay(4000)
					KPCommonPage.postAddedToWishList = "Post Added To Wislist"
					println "First Post Add to Wishlist link is Clicked."
					return new SuccessOutcome()
				}else{
					KPCommonPage.postAddedToWishList = "Post Already Added to Wishlist"
					println "First Post is Already Added to Add to wishlist."
					return new SuccessOutcome()
				}
			}
			
		}
		
		
		def static final wishListedPostDisplayed = {browser, formData ->
			if(KPCommonPage.postAddedToWishList.equals("Post Added To Wislist")){
				if(browser.isDisplayed(POSTS_LIST)){
					def firstPostTitle = browser.getLists(POSTS_LIST)[0].trim()
					if(firstPostTitle.equalsIgnoreCase(KPCommonPage.firstFeedName)){
						println "Correct Post is Wislisted."
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "PostTitleDoesNotMatchInWishlist", "Post TiTle Mismatch Issue.")
				}
			}else{
				if(browser.isDisplayed(POSTS_LIST)){
					def firstPostTitle = browser.getLists(POSTS_LIST)[0].trim()
					if(firstPostTitle.equalsIgnoreCase(KPCommonPage.firstFeedName)){
						browser.click ADD_TO_WISHLIST
						browser.delay(3000)
						println "The Title Already Wishlist Post is Correct"
						return new SuccessOutcome()
					}else
						return KPCommonPage.returnFailureOutcome(browser, "PostTitleDoesNotMatchInWishlist", "Post TiTle Mismatch Issue.")
				}
			}
		}
		
		
		
		def static sortByAsRecentlyAdded = {browser, formData ->
			if(browser.isDisplayed(SORT_BY)){
				browser.delay(2000)
				browser.selectDropdownValue(SORT_BY,"Recently added")
				println "Sort By Recently Added is Selected"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "SortByDropdownDisplayIssue", "Sort By Dropdown does not display.")
		
		}






	}
}
