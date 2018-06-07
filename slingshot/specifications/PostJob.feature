Feature: To test all the feature of Post Job page
  As an user of the application
  I want to test various functionalities of Post Job page

  Background: Login:LoginAsOrgAdmin

Scenario: To verify the navigation from Post Job page to dashboard page using breadcrumb
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    When I CLICK dashboardBreadcrum
    Then I am ON dashboard page

Scenario: To verify the navigation from Post Job page to view all Job Postings page
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    And I CLICK viewAllPostings link
    Then I am ON jobPostList page

Scenario: To verify the navigation from posting list page to post job page using breadcrumb
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    And I CLICK viewAllPostings link
    Then I am ON jobPostList page
    When I CLICK postJobBreadcrumb
    Then I am ON postJob page

@Name(PostJob_Success)    
Scenario: To verify success scenarios for Post Job page and verify job is displayed on post job list page with DATA PostJob_Success
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    When I ENTER valid details with DATA PostJob_Success
    Then I SUBMIT the form
    Then I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    Then I VERIFY ifJobStatusIsPublished
    And it is verfied that job was displayed on post job list
    
Scenario: To verify posted job description success scenario on job description page with DATA PostJob_Success
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    When I ENTER valid details with DATA PostJob_Success
    Then I SUBMIT the form
    Then I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    Then I VERIFY ifJobStatusIsPublished
    And I am ON jobPostList page
    Then I PERFORM clickOnFirstJob
    Then I am ON jobDetail page
    And I VERIFY jobDetails match on job detail page
    Then I am ON jobDetail page and job details are verified

Scenario: To select future date for job posting date and then verify the status of the job is not published with DATA PostJob_Success
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    And I PERFORM changeSelectNextDateStatus
    Then I ENTER valid details with DATA PostJob_Success
    And I SUBMIT the form
    Then I am ON jobPostList page
    And I VERIFY correctJobDisplayed 
    Then I VERIFY ifJobStatusIsNotPublished
    And it is verified that the status of job is unpublished when job is posted with future date
    
    
Scenario: To verify posted job description success scenario on job description page with DATA PostJob_Success
    Given I am ON dashboard page
    Then I CLICK leftMenuButton
    When I CLICK postJob link
    Then I am ON postJob page
    When I ENTER valid details with DATA PostJob_Success
    Then I SUBMIT the form
    And I am ON jobPostList page
    Then I VERIFY correctJobDisplayed on job list page
    And I VERIFY ifJobStatusIsPublished
    Then I am ON jobPostList page
    And I CLICK leftMenuButton
    When I CLICK logoutLink to log out from current user
    Then I am ON home page
    When I CLICK login link
    Then I am ON login page
    And I ENTER valid login details with DATA Login_Success
    And I SUBMIT the form
    Then I am ON dashboard page
    And I CLICK leftMenuButton
    Then I CLICK viewPublicProfile link to go to view profile
    And I am ON userViewProfile
    Then I PERFORM getUserProfileDetails on view profile page
    And I CLICK dashboardBreadcrum
    Then I am ON dashboard page
    And I CLICK jobsTab to go to jobs
    Then I CLICK recommendedJobViewAll link
    And I VERIFY correctJobDisplayed on job list page
    And I am ON jobPostList page
    Then I PERFORM clickOnFirstJob
    Then I am ON jobDetail page
    And I VERIFY jobDetails match on job detail page
    Then I am ON jobDetail page and job details are verified
    And I PERFORM applyForJob to apply for the job