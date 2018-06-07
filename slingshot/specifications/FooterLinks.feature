Feature: To check footer links navigation functionality of the application
  As an user of the application
  I want to test footer links feature

  Background: Login:LoginAsOrgAdmin

  Scenario: To Verify the page-title of Terms of Use page
    Given I am ON dashboard page
    When I CLICK termsOfUse button
    Then I am ON termsOfUse page

  Scenario: To Verify the navigation from Terms of User to dashboard using breadcrumb
    Given I am ON dashboard page
    When I CLICK termsOfUse button
    Then I am ON termsOfUse page
    When I CLICK dashboardBreadcrum
    Then I am ON dashboard page

  Scenario: To Verify the page-title of Privacy Policy page
    Given I am ON dashboard page
    When I CLICK privacyPolicy button
    Then I am ON privacyPolicy page

  Scenario: To Verify the navigation from Privacy Policy to dashboard using breadcrumb
    Given I am ON dashboard page
    When I CLICK privacyPolicy button
    Then I am ON privacyPolicy page
    When I CLICK dashboardBreadcrum
    Then I am ON dashboard page

  Scenario: To Verify the page-title of About Us page
    Given I am ON dashboard page
    When I CLICK aboutUs button
    Then I am ON aboutUs page

  Scenario: To Verify the navigation from About Us page to dashboard page using breadcrumb
   Given I am ON dashboard page
    When I CLICK aboutUs button
    Then I am ON aboutUs page
    When I CLICK dashboardBreadcrum
    Then I am ON dashboard page

  Scenario: To Verify the page-title of Contact Support page
    Given I am ON dashboard page
    When I CLICK contactSupport button
    Then I am ON contactSupport page

  Scenario: To Verify the navigation from Contact Support to dashboard using breadcrumb
    Given I am ON dashboard page
    When I CLICK contactSupport button
    Then I am ON contactSupport page
    When I CLICK dashboardBreadcrum
    Then I am ON dashboard page

 Scenario: To verify the page-title of Contact Support page while navigating from header link 
    Given I am ON dashboard page
    When I CLICK contactSupportHeader button
    Then I am ON contactSupport page