Feature: Successful and Unsuccessful scenarios of Recommended Training page of the application  
As an Trainer admin of the application
I want to test the Recommended Training feature

Background: Login:LoginAsTrainerAdmin

Scenario: : To verify the page title of the Recommended Training page
	Given I am ON dashboard page 
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	
Scenario: To verify the number of pods in Recommended Training page
	Given I am ON dashboard page
	And I PERFORM getPodNumber action
	Then I CLICK viewAllRecommendedTrng
	Then I VERIFY verifyPodsInRecommended are equal
	And the number of pods displayed are same

Scenario: To verify the number of pods displayed in each page is matching the number in the ruler
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I VERIFY podsAndRulerNoMatch action
	Then the number displayed is less than or equal to the ruler number

Scenario: To verify on click of add to wishlist the same pods are added to pod wishlist page or not
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	And I PERFORM addingPodsToWishList to add few pods to wishlist page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY isPodWishlistDisplayed in wishlist page
	Then the wishlisted pod is displayed in the wishlist page

Scenario: To verify the result displayed on applying filter on skills is correct with DATA PodSkillFilter_Success
    Given I am ON dashboard page
    And I CLICK viewAllRecommendedTrng
    Then I am ON recommendedTraining page
    When I ENTER filterDetails in skills
    Then I VERIFY displayedSkillFilter verification
    And the pods displayed are of the same skills


Scenario: To verify the result displayed on applying filter on industries is correct with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	When I ENTER filterDetails in industry
	Then I VERIFY displayedIndustryFilter  verification
	And the pods displayed are of the same industries


Scenario: To verify the result displayed on applying filter on level is correct with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	When I ENTER filterDetails in level
	Then I VERIFY displayedLevelFilter verification
	And the pods displayed are of the same level


Scenario: To verify min and max filter with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked
	
	
Scenario: To verify sort by filter with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommendedTrng
	Then I am ON recommendedTraining page
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked
	
