Feature: Successful and Unsuccessful scenarios of Edit Organization Profile of the application
  As an admin of the application
  I want to test the Edit Organization Profile feature

Background: Login:LoginAsAdmin

Scenario: To verify the page title of the Edit Organization profile page
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile option
    Then I am ON editOrganizationProfile page

Scenario: To verify successful editing of Organization profile with DATA EditOrgProfileForm_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I ENTER editProfileDataSubmit valid form details
    Then I SUBMIT editProfileDataSubmit the form
    Then I am ON editOrganizationProfile page
    And I CLICK dashboardBreadcrum
    Then I VERIFY orgNameChangeOnDashboard
    And the Organization name is updated
    And I CLICK leftMenuButton
    And I CLICK viewOrganizationProfile option
    Then I am ON viewOrganizationProfile page
    And I VERIFY editDataMatch in the page
    And the data displayed is a match

Scenario: To verify uploading of Organization Profile picture with DATA OrganizationUpload_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And VERIFY orgProfileImgUpload action
    And the image is uploaded
    And I CLICK leftMenuButton
    And I CLICK viewOrganizationProfile option
    Then I am ON viewOrganizationProfile page
    And I VERIFY uploadImgDisplay in the page
    And the image displayed is a match

Scenario: To verify unsuccessful editing of Organization profile with DATA EditOrgProfileForm_Failure
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I ENTER editProfileDataSubmit invalid form details with DATA EditOrgProfileForm_Failure
    Then I SUBMIT editProfileDataSubmit the form
    Then I am ON editOrganizationProfile page

Scenario: To verify industry error message of Organization profile with DATA RemoveIndustry_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I PERFORM removeAllIndustry action
    Then I VERIFY indErrorMessageMap
    And error message is accurate

@Group(orgEditProfile)
Scenario: To verify updating of removal of an industry with DATA RemoveIndustry_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I PERFORM removeIndustries action
    Then I SUBMIT editProfileDataSubmit form
    And I VERIFY industryRemoved action
    And the industry has been removed from the list

Scenario: To verify adding About Organization details with DATA EditAboutOrg_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I ENTER editAboutOrgSubmit valid form details
    Then I SUBMIT editAboutOrgSubmit form
    And I CLICK leftMenuButton
    And I CLICK viewOrganizationProfile option
    Then I am ON viewOrganizationProfile page
    Then I VERIFY aboutOrgDataDisplay
    And the information is same

Scenario: To verify adding Latest news with DATA LatestNews_Success
    Given I am ON dashboard page
    And I CLICK editOrganizationProfile
    Then I am ON editOrganizationProfile page
    And I CLICK addLatestNewsButton
    Then I ENTER orgLatestNewsSubmit valid form details
    Then I SUBMIT orgLatestNewsSubmit form
    And I CLICK leftMenuButton
    And I CLICK viewOrganizationProfile option
    Then I am ON viewOrganizationProfile page
    Then I VERIFY aboutLatestNewsDisplay
    And the information is same
