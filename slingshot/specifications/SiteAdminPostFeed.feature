Feature: Successful and Unsuccessful Scenario of Post Feed functionality to the application  
  As an Site admin of the application
  I want to test Post Feed feature
  
Background: Login:LoginAsSiteAdmin


Scenario: To verify post list are displayed
	Given I am ON dashboard page
	And I CLICK leftMenuButton
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	When I VERFIY postsListDisplayed
	Then posts are displayed on this page
	
	
Scenario: To verify the functionality of view more link and post details in a news feed page
	Given I am ON dashboard page
	And I CLICK leftMenuButton
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	When I VERFIY postsListDisplayed
	Then posts are displayed on this page
	And I PERFORM storeFirstPostTitle
	And I CLICK firstFeedTitle
	Then I am ON newsArticle page
	And I VERIFY correctFeedDisplayed page
	Then I PERFORM getCharCountOfPostDescription 
	And I CLICK bredcrumBack to come back
	When I VERIFY viewMoreLink feature
	Then successfully View more link is verified
	
	
	
Scenario: To verify the like and comment functionality in News feed Page with DATA PostFeed_Success
	Given I am ON dashboard page
	And I CLICK leftMenuButton link
	Then I CLICK viewPublicProfileMenu link 
	Then I am ON userViewProfile page
	And I PERFORM getFullNameOfUser 
	And I CLICK orgLogo to come back 
	Then I am ON dashboard
	And I CLICK leftMenuButton
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	When I VERIFY postsListDisplayed
	Then posts are displayed on this page
	And I PERFORM storeFirstPostTitle
	And I PERFORM getTheLikesCount of a user
	When I VERIFY clickOnLikeIcon with DATA LoginAsIndividualUser_Success
	And I verified Like functionality of comment
	Then I ENTER comment into commentbox with DATA PostFeed_Success
	And I SUBMIT comment 
	Then I VERIFY correctCommentDisplayed 
	When I ENTER reply on to the latest comment with DATA PostFeedReply_Success
	And I SUBMIT reply
	Then I VERIFY correctReplyDisplayed
	And I VERIFY correctReplyCountDisplayed
	Then I PERFORM likeOperationOnReply
	And I VERIFY likeOperationOnReply again for comparing form error msg
	
	

Scenario: To verify the share and follow functionality in News feed Page with DATA PostFeed_Success
	Given I am ON dashboard page
	And I CLICK leftMenuButton link
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	When I VERFIY postsListDisplayed
	Then posts are displayed on this page
	And I PERFORM storeFirstPostTitle
	Then I CLICK sharesLink
	And I VERIFY correctPostNameInDialogbox is displayed
	And I CLICK shareCloseMark to close the popup
	Then I VERIFY checkFollowedFunctionality 
	And I CLICK leftMenuButton link 
	Then I CLICK followedOrganization link
	And I am ON followedOrganizationList page
	And I PERFORM sortByAsRecentlyAdded
	When I VERFIY followedOrgDisplayed
	Then I PERFORM bulkUnfollowOrgs to remove organization from list
	And followed and share functionality is verified
	
	
	
Scenario: To verify the like and comment functionality in wishList tab of feed Page with DATA PostFeed_Success
	Given I am ON dashboard page
	And I CLICK leftMenuButton link
	Then I CLICK viewPublicProfileMenu link 
	Then I am ON userViewProfile page
	And I PERFORM getFullNameOfUser 
	And I CLICK orgLogo to come back 
	Then I am ON dashboard
	And I CLICK leftMenuButton
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	When I VERIFY postsListDisplayed
	Then posts are displayed on this page
	And I PERFORM storeFirstPostTitle
	And I PERFORM addToWishListAPostFeed
	Then I CLICK wishListTab
	When I PERFORM wishListedPostDisplayed 
	And I PERFORM getTheLikesCount of a user
	When I VERIFY clickOnLikeIcon with DATA LoginAsIndividualUser_Success
	And I verified Like functionality of comment
	Then I ENTER comment into commentbox with DATA PostFeed_Success
	And I SUBMIT comment 
	Then I VERIFY correctCommentDisplayed 
	When I ENTER reply on to the latest comment with DATA PostFeedReply_Success
	And I SUBMIT reply
	Then I VERIFY correctReplyDisplayed
	And I VERIFY correctReplyCountDisplayed
	Then I PERFORM likeOperationOnReply
	And I VERIFY likeOperationOnReply again for comparing form error message
	
	
	
Scenario: To verify the share and follow functionality in wishlist tab of News feed Page with DATA PostFeed_Success
	Given I am ON dashboard page
	And I CLICK leftMenuButton link
	And I CLICK postFeed link
	Then I am ON newsFeeds page
	And I PERFORM addToWishListAPostFeed
	Then I CLICK wishListTab
	When I VERFIY postsListDisplayed
	Then posts are displayed on this page
	And I PERFORM storeFirstPostTitle
	Then I CLICK sharesLink
	And I VERIFY correctPostNameInDialogbox is displayed
	And I CLICK shareCloseMark to close the popup
	Then I VERIFY checkFollowedFunctionality 
	And I CLICK leftMenuButton link 
	Then I CLICK followedOrganization link
	And I am ON followedOrganizationList page
	When I VERFIY followedOrgDisplayed
	Then I PERFORM bulkUnfollowOrgs to remove organization from list
	And followed and share functionality is verified
	
	
	
	
	