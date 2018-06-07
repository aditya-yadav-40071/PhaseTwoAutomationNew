Feature: To test all the feature of Manage Sites page
  As an user of the application
  I want to test various functionalities of Manage Sites page

Background: Login:LoginAsOrgAdmin

Scenario: To verify if the Default Online site is displayed in the Manage Sites page of an org admin with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I VERIFY defaultSiteDisplayed
    Then it is verified that site displayed is Default Online Site

@Name(AddSiteSuccess)
Scenario: To create a site in an organization and verify if the site is on Manage Site page created with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY correctSiteDisplayed
    Then I am ON manageSites page
    And created site is displayed on the manage site page
    And I CLICK dashBoardLink
    Then I am ON dashboard page

Scenario: To verify all failure scenario while adding a site with DATA AddSite_Failure
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Failure
    When I SUBMIT the form
    Then error messages are displayed

Scenario: To search for the created site and click on the site name to verify if same site details on the left side is displayed with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I PERFORM searchForSite
    And I VERIFY correctSiteDisplayed on manage site page
    When I PERFORM clickOnSiteName
    Then I am ON siteDetails page
    And I VERIFY correctSiteDetailsOnLeftSide on site details page
    Then site name is verified on site details page

Scenario: To verify the added site details on site detail page by adding trainers to the site with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I PERFORM searchForSite
    And I VERIFY correctSiteDisplayed on manage site page
    When I PERFORM clickOnSiteName
    Then I am ON siteDetails page
    Then I VERIFY ifTrainerAssignedToSite
    And I CLICK leftMenuButton
    And I CLICK manageAdmins link
    Then I am ON manageAdmins page
    When I CLICK addAdmin button
    Then I am ON addAdmin page
    And I ENTER all the required fields with DATA AddTrainerAdmin_Success
    When I SUBMIT the form
    Then I am ON manageAdmins page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I VERIFY ifAdminCreated successfully
    Then I am ON manageAdmins page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I PERFORM searchForSite
    When I PERFORM clickOnSiteName
    Then I am ON siteDetails page
    Then I VERIFY correctSiteDetails on site details page
    And site details are veified

Scenario: To edit the newly created site and verify the site details on site detail page with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I PERFORM searchForSite
    And I VERIFY correctSiteDisplayed on manage site page
    And I PERFORM clickEditLink to edit the site details
    Then I am ON editSite page
    And I ENTER valid site details to edit the created site with DATA EditSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I PERFORM searchForSite which was edited
    Then I VERIFY correctSiteDisplayed on manage site page
    When I PERFORM clickOnSiteName
    Then I am ON siteDetails page
    And I VERIFY correctSiteDetailsOnLeftSide
    Then I VERIFY correctSiteDetails on site details page
    And edited site details are verified

Scenario: To verify sorting of site names in A to Z order in 'Manage Site' page with DATA ListSorted_AtoZ
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    Then I VERIFY manageSiteListSorted A-Z order 
    And it is verified that user list is sorted in A-Z order

Scenario: To verify sorting of participant names in Z to A order in 'Manage Site' page with DATA ListSorted_ZtoA
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    Then I VERIFY manageSiteListSorted in Z-A order 
    And it is verified that user list is sorted in Z-A order

Scenario: To verify if site displayed is accoring to location filter selected on 'Manage Site' page with DATA AddSite_Success
    Given I am ON dashboard page
    And I CLICK leftMenuButton link
    And I CLICK manageSites link
    Then I am ON manageSites page
    And I CLICK addSite button
    Then I am ON addSite page
    And I ENTER valid site details with DATA AddSite_Success
    When I SUBMIT the form
    Then I am ON manageSites page
    And I CLICK fiftyResultsPerPage link which displays fifty admins on single page
    And I PERFORM selectLocationFilter 
    Then I PERFORM searchForSite
    And I VERIFY correctSiteDisplayed on manage site page
    When I PERFORM clickOnSiteName
    Then I am ON siteDetails page
    And I VERIFY sameLocationAsFilter 
    