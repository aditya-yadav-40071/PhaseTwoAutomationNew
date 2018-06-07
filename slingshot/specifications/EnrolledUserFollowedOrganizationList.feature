Feature: Successful and Unsuccessful Followed Organizations functionality to the application
  As an Enrolled User of the application
  I want to test Followed Organizations feature

  Background: Login:loginAsEnrolledUserSuccess

Scenario: To verify the page title of the Followed Organization List page
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page

Scenario: To verify the page title of the Search Organization List page
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page

Scenario: To verify the Error message being displayed when the followed organization list is empty
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I VERIFY isErrorMessageCorrect in followed organization page
    Then it is verified successfully

Scenario: To verify that clicking on search button in Search Organization page displays all the Organizations
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    When I VERIFY organizationsDisplayed
    Then Organizations are displayed successfully

Scenario: To verify the result displayed for search organizations is correct with DATA SearchOrg_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I ENTER searchInputs with DATA SearchOrg_Success
    Then I CLICK searchOrgArrow icon
    And I VERIFY correctResultDisplayed
    Then I CLICK bredcrumBack url
    Then I am ON followedOrganizationList page
    And I ENTER searchInputs with DATA SearchOrg_Success
    Then I CLICK searchOrgArrow icon
    And I VERIFY correctResultDisplayed
    Then the correct search organizations result is displayed

Scenario: To verify that onclick of organization name it should navigate to organization profile page with DATA SearchOrg_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I ENTER searchInputs with DATA SearchOrg_Success
    Then I CLICK searchOrgArrow icon
    When I VERIFY organizationsDisplayed
    When I CLICK firstOrganization name
    Then I am ON viewOrganizationProfile page

Scenario: To verify that onclick of organization name should navigate to organization profile page with correct org name with DATA SearchOrg_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I ENTER searchInputs with DATA SearchOrg_Success
    Then I CLICK searchOrgArrow icon
    When I VERIFY organizationsDisplayed
    When I CLICK firstOrganization name
    Then I am ON viewOrganizationProfile page
    When I VERIFY correctOrgProfileDisplayed for the clicked org
    Then correct organization is displayed correctly

Scenario: To verify that the results displayed for location filter is correct with DATA SearchOrgLocationFilter_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I VERIFY organizationsDisplayed
    When I ENTER filterDetails with DATA SearchOrgLocationFilter_Success
    And I VERIFY correctLocationDisplayed
    Then the result set is verified correctly

Scenario: To verify that a user is able to filter the result using Industry in Search Organizations page with DATA SearchOrgIndustryFilter_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I VERIFY organizationsDisplayed
    Then I ENTER filterDetails with DATA SearchOrgIndustryFilter_Success
    And I VERIFY isCorrectIndustryList displayed
    Then the result set is verified correctly

Scenario: To verify the results displayed for SortBy filter is correct with DATA SearchOrgSortByFilter_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    Then I CLICK searchOrgArrow icon
    And I VERIFY organizationsDisplayed
    When I ENTER filterDetails with DATA SearchOrgSortByFilter_Success
    And I VERIFY isCorrectSortingList displayed
    Then the result set is verified correctly

Scenario: To verify that a user is able to perform follow operation in Search organization page
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    Then I CLICK searchOrgArrow icon
    And I VERIFY organizationsDisplayed
    And I PERFORM addToFollowedOrgPage for an Organization
    Then it was successfully performed

Scenario: To verify that onclick of org name should navigate to org profile page with correct org name with DATA SearchOrg_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    When I VERIFY organizationsDisplayed
    And I CLICK firstOrgNameCick to view org profile
    Then I am ON viewOrganizationProfile page

Scenario: To verify that following count gets increased on following any organization in dasboard page
    Given I am ON dashboard page
    And I PERFORM getFollowingCount
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    When I VERIFY correctFollowingCount displayed
    Then it is verified successfully

Scenario: To verify the followed organizations are successfully displayed in followed organization page or not
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    When I VERIFY followedOrgDsplyd or not
    Then it is displayed successfully

Scenario: To verify that the user is able to perform unfollow operation
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    When I PERFORM removeFrmFollowedList
    Then that action is completed successfully

Scenario: To verify the organizations are removed when user tries to performs on unfollow link
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    When I VERIFY isFollowedOrgRemoved
    Then that organization is removed successfully

Scenario: To verify that a user is able to filter the result using Location in Followed Organizations with DATA SearchOrgLocationFilter_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I CLICK search button
    Then I am ON searchOrganizations page
    And I CLICK searchOrgArrow
    And I PERFORM followOrg operation for some org
    And I CLICK bredcrumBack url
    Then I am ON followedOrganizationList page
    Then I ENTER filterDetails with DATA SearchOrgLocationFilter_Success
    And I VERIFY correctLocationDisplayed displayed
    Then the result set is verified correctly

Scenario: To verify that a user is able to filter the result using Industry in Followed Organizations with DATA SearchOrgIndustryFilter_Success
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    Then I ENTER filterDetails with DATA SearchOrgIndustryFilter_Success
    And I VERIFY isCorrectIndustryList displayed
    Then the result set is verified correctly

Scenario: To verify bulk unfollow works in followed organization page or not
    Given I am ON dashboard page
    When I CLICK leftMenuButton
    And I CLICK followedOrganization link
    Then I am ON followedOrganizationList page
    And I PERFORM bulkUnfollowOrgs in current page
    Then all the organizations are unfollowed successfully
