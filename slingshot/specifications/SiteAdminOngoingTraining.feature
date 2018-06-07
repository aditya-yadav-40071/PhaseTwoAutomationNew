Feature: Successful and Unsuccessful scenarios of Ongoing Training functionality to the application  
  As an Site admin of the application
  I want to test Ongoing Training feature
  
Background: Login:LoginAsSiteAdmin

Scenario: To verify the page title of the Ongoing Training page
	Given I am ON dashboard page 
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page

Scenario: To verify the batch progress feature
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I CLICK viewBatchProgress link
	Then I am ON overview page

Scenario: To verify skill filter with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in skills
	And I VERIFY displayedSkillFilter result set
	Then the filtered pod name displayed are same as the skills selected


Scenario: To verify industry filter with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in Industry
	And I VERIFY displayedIndustryFilter result set
	Then the filtered pod name is same as the pod clicked


Scenario: To verify level filter with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in level filter
	And I VERIFY displayedLevelFilter result set
	Then the filtered pod name is same as the pod clicked
	
Scenario: To verify min and max filter with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked
	

Scenario: To verify sort by filter with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked

Scenario: To verify the pod count displayed is same when we try to select and remove the same item with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	And I CLICK viewAllOngoing
	Then I am ON ongoingTraining page
	When I ENTER filterDetails in skills 
	And I VERIFY removeSelectedSkills from the auto suggest
	Then the Pod count is correct
	
	
	
	
	
	
	

	
	
	

	

	
	
	


	
	
	
	

	
	




	