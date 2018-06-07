Feature: To test all the feature of Site Admin Profile page
As an user of the application
I want to test various functionalities of Site Admin Profile page

Background: Login:LoginAsSiteAdmin

Scenario: To Verify the page-title of 'User Edit Profile' page
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page

Scenario: To Verify the navigation from edit profile page to Dashboard using Cancel link 
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page			 
	And I CLICK cancelLink to cancel the form submission
	Then I am ON dashboard page

Scenario: To Verify the page-title of 'User View Profile' page
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	When I CLICK viewPublicProfile link
	Then I am ON userViewProfile page

Scenario: To verify 'User Edit Profile' link from breadcrumb menu
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK dashboardBreadcrum to got to dashboard
	Then I am ON dashboard page

Scenario: To Verify the navigation from View Public Profile page to Edit Profile page using bredcrumb link
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	Then I CLICK editProfilelink breadcrumb
	Then I am ON editProfile page

Scenario: To Verify the navigation from View Public Profile page to Dashboard page using bredcrumb link
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	Then I CLICK dashBoardLink breadcrumb
	Then I am ON dashboard page

Scenario: To edit the Basic Information of a user and verify the same is displayed on View Profile page with DATA SiteAdminBaisicInfo_Success 	
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I ENTER basicInformation valid details with DATA SiteAdminBaisicInfo_Success
	Then I SUBMIT basicInformation form details
	And I am ON userViewProfile page 
	Then I VERIFY userBasicInformationMatch on view profile page
	Then I am ON userViewProfile page
	And I CLICK editProfilelink to go to edit profile page
	Then I VERIFY leftSideDetailsOfEditProfile on view profile page
	Then I am ON editProfile page
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	And I VERIFY leftSideDetailsOfViewProfile
	Then details are verified
	And I CLICK dashboardBreadcrum to go to dashboard
	Then I VERIFY profilePercentagecompletion on dashboard page
	And profile completion is verified
	
Scenario: To verify profile pic upload success scenario with DATA UserProfilePicUpload_Success 
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I PERFORM uploadProfilepic to upload user profile picture with DATA UserProfilePicUpload_Success
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	And I VERIFY uploadUserImgDisplay 
	Then I am ON userViewProfile page
	
Scenario: To verify success scenario for 'User Edit Profile Education Qualification' page with DATA UserEduQualification_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I CLICK educationDetailsArrowDown
	Then I am ON editProfile page
	And  I ENTER eductionQualification valid details
	When I SUBMIT eductionQualification form 
	Then I am ON userViewProfile page
	And I CLICK dashboardBreadcrum to go to dashboard
	Then I VERIFY profilePercentagecompletion on dashboard page
	And profile completion is verified

Scenario: To verify the Education details in View Public Profile page with DATA UserEduQualification_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I CLICK educationDetailsArrowDown
	Then I am ON editProfile page
	And  I ENTER eductionQualification valid details UserEduQualification_Success
	When I SUBMIT eductionQualification form 
	Then I am ON userViewProfile page
	And I VERIFY educationDetailsMatch on View Profile page
	Then I am ON userViewProfile page 
	
Scenario: To verify success scenario for adding 'User Work Experience' with DATA WorkExperience_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	Then I ENTER workExperience valid details with DATA WorkExperience_Success
	And I SUBMIT workExperience form
	And I VERIFY workExperienceDetailsMatch
	Then I am ON userViewProfile page
	And I CLICK dashboardBreadcrum to go to dashboard
	Then I VERIFY profilePercentagecompletion on dashboard page
	And profile completion is verified
	
Scenario: To verify success scenario for editing 'User Work Experience' with DATA EditWorkExperience_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	Then I PERFORM editWorkExperienceDetails with DATA EditWorkExperience_Success
	And I SUBMIT workExperience form
	And I VERIFY workExperienceDetailsMatch
	Then I am ON userViewProfile page

Scenario: To verify success scenario for deleting 'User Work Experience' details with DATA DeleteWorkExperience_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	And I PERFORM deleteWorkExperience 
	When I CLICK viewPublicProfile link
	And I VERIFY ifWorkExperienceDisplayed after delete on View Profile Page 
	Then I am ON userViewProfile page
	
Scenario: To verify success scenario for adding multiple work experience for 'User Work Experience' with DATA WorkExperienceAddMore_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	And  I ENTER workExperience valid details with DATA WorkExperience_Success
	Then I CLICK workExperienceAddMoreButton to add multiple work experience
	And  I ENTER workExperience valid details with DATA WorkExperienceAddMore_Success
	And I SUBMIT workExperience form 
	Then I VERIFY workExperienceDetailsMatch 
	Then I am ON userViewProfile page
	
Scenario: To verify success scenario for adding 'User Skills' with DATA Skills_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	And  I ENTER skills valid details with DATA Skills_Success
	And I SUBMIT skills form
	Then I am ON userViewProfile page
	And I CLICK dashboardBreadcrum to go to dashboard
	Then I VERIFY profilePercentagecompletion on dashboard page
	And profile completion is verified
	
Scenario: To verify added skills on 'User View Profile' page with DATA Skills_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	Then I PERFORM deleteExistingSkills to delete existing user skills
	And  I ENTER skills valid details with DATA Skills_Success
	And I SUBMIT skills form 
	Then I am ON userViewProfile page
	Then I VERIFY skillsDisplayed on View Public Profile page
	And I CLICK editProfilelink breadcrumb
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	Then I VERIFY skillsRetained on edit profile pages
	And I am ON editProfile page
	
Scenario: To remove a user skill and verify if skill is removed on 'User View Profile' with DATA RemoveSkill_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	And I PERFORM removeASkill to remove a specific skill from user profile with DATA RemoveSkill_Success
	And I SUBMIT skills form 
	Then I am ON userViewProfile page
	Then I VERIFY skillsRemoved after update on 'View Public Profile' page	
	Then I am ON userViewProfile page

Scenario: To verify success scenario for adding a 'User Certificate' with DATA Certificate_Success
  Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK certificateArrowDown
	And  I ENTER certificate valid details with DATA Certificate_Success
	And I SUBMIT certificate form 
	And I VERIFY certificateMatch
	Then I am ON userViewProfile page
	And I CLICK dashboardBreadcrum to go to dashboard
	Then I VERIFY profilePercentagecompletion on dashboard page
	And profile completion is verified

Scenario: To edit the certificate name and verify on 'User View Profile' page with DATA CertificateEdit_Success
  Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK certificateArrowDown
	Then I PERFORM editDocumentsDetails with DATA CertificateEdit_Success
	And I SUBMIT certificate form 
	And I VERIFY certificateMatch on View Profile page
	Then I am ON userViewProfile page

Scenario: To delete a certificate and verify if the certicicate is deleted on 'User View Profile' page with DATA CertificateDelete_Success
  Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK certificateArrowDown
	Then I PERFORM deleteCertificate with DATA CertificateDelete_Success
	And I SUBMIT certificate form 
	And I VERIFY ifcertificateDeleted on View Profile page
	Then I am ON userViewProfile page
	
Scenario: To verify success scenario for adding multiple certificate and verify on 'User View Profile' page with DATA Certificate_Success
  Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK certificateArrowDown
	And I ENTER certificate valid details with DATA Certificate_Success
	Then I CLICK certificateAddMore button to add multiple certificate
	And ENTER certificate valid details with DATA CertificateAddMore_Success
	And I SUBMIT certificate form 
	And I VERIFY certificateMatch on View Profile page
	Then I am ON userViewProfile page