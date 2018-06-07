package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import java.awt.Component.BaselineResizeBehavior
import com.KPPhaseTwo.app.pages.KPCommonPage
import java.lang.*

/**
 *@author aditya
 */

final class ManageAdminsPage extends WebPage {

	/**
	 *Verify that added admin is displayed in the list on manage admins page
	 */
	def static ifAdminCreated = { browser, formData ->
		new ManageAdminsForm().ifAdminCreated browser, formData
	}

	/**
	 * Trainer admin is created and displayed in the Manage admin list of site admin
	 */
	def static ifTrainerAdminCreated = { browser, formData ->
		new ManageAdminsForm().ifTrainerAdminCreated browser, formData
	}

	/**
	 * To search for the created admin on the Manage admins page
	 */
	def static searchForCreatedAdmin = { browser, formData ->
		new ManageAdminsForm().searchForCreatedAdmin browser, formData
	}

	/**
	 * To verify if the selected filter value mathes with the admin role displayed 
	 */
	def static allDisplayedAdminRoles = { browser, formData ->
		new ManageAdminsForm().allDisplayedAdminRoles browser, formData
	}

	/**
	 * To select the admin filter  with the data from the formData
	 */
	def static selectAdminFilter = { browser, formData ->
		new ManageAdminsForm().selectAdminFilter browser, formData
	}

	/**
	 * To demote an admin from the admins list to make him a user
	 */
	def static demoteAnAdmin = { browser, formData ->
		new ManageAdminsForm().demoteAnAdmin browser, formData
	}

	/**
	 * Verify that admin has been removed from the admins list
	 */
	def static isAdminRemoved = { browser, formData ->
		new ManageAdminsForm().isAdminRemoved browser, formData
	}

	/**
	 * To click on the Admin name to view admin public profile
	 */
	def static clickOnAdminName = { browser, formData ->
		new ManageAdminsForm().clickOnAdminName browser,formData
	}

	/**
	 * To verify the admin details on the view public profile page 
	 */
	def static  adminDetailsOnPublicProfile = { browser, formData ->
		new ManageAdminsForm().adminDetailsOnPublicProfile browser,formData
	}

	/**
	 * To click on the the change privilage link of an admin
	 */
	def static  clickOnChangePrivilages = { browser, formData ->
		new ManageAdminsForm().clickOnChangePrivilages browser,formData
	}

	/**
	 * To click on the the change privilage link of an admin
	 */
	def static clickAdminChangePrivilageLink = { browser, formData ->
		new ManageAdminsForm().clickAdminChangePrivilageLink browser, formData
	}

	/**
	 * To check when trainer is removed should not be displayed on the Manage Admins in Site Admin role
	 */
	def static ifTrainerIsDisplayedAfterRemove = {  browser, formData ->
		new ManageAdminsForm().ifTrainerIsDisplayedAfterRemove browser,formData
	}

	/**
	 * This method verifies the trainer admin should not have a site when privilages(removes the site) are changed by site admin
	 */
	def static ifRemovedAdminHasSite = { browser, formData ->
		new ManageAdminsForm().ifRemovedAdminHasSite browser,formData
	}


	static final class ManageAdminsForm extends WebForm {

		//Manage Admins Page elements
		private static final def ADMIN_LIST = ".//a[@ui-sref='kp.viewUserProfile']"

		private static final def ADMIN_ROLE_LIST = ".//span[@class='content-name smallText text_allign limit_text ng-binding']"

		private static final def ADMIN_SITE_LIST = ".//span[@ng-if='showSites(subadmin)']"

		private static final def DEMOTE_ADMINS = "//a[contains(@ng-click, 'removeSubAdminConfirmation')]"

		private static final def CHANGE_PRIVILEGES = "//a[@href='#/orgadmin/edituser']"

		private static final def ADMIN_SITE_TO_MATCH = "//span[@class='content-name smallText text_allign width_100 ng-binding'][.='Email: adminEmail']/../span[3]"

		private static final def CONFIRM_DELETE = "//span[contains(@class, 'ng-binding ng-scope') and text() = 'Yes']";

		private static final def PAGINATION_NEXT = ".//li[@class='pagination-next ng-scope']"

		private static final def PAGE_COUNT = "//a[@ng-click='selectPage(page + 1, \$event)']//preceding::a[1]"

		private static final def PAGINATION_FIRST = ".//ul[@ng-change='pageChangedforAdminList(pageno)']/li[2]/a"

		private static final def PAGINATION_LAST = "//li[@class='pagination-last ng-scope']/a"

		private static final def CHANGE_PRIVILAGES= ".//a[@ui-sref='kp.editUser']"

		private static final def POP_YES ="html/body/div[3]/md-dialog/md-dialog-actions/button[2]"

		private static final def ADMIN_FILTER = ".//select[@placeholder='Select Admin']"

		private static final def TEXT_MSG = ".//div[@class='col-md-12 col-xs-12 text-center mt_45']/p"

		private static final def ADMIN_SEARCH_TEXT_BOX = ".//div[@class='col-xs-12 mb10 no-padding ml_15 visible-lg mt_10 pr_25']//input"

		private static final def ADMIN_SEARCH_BUTTON = ".//div[@class='col-xs-12 mb10 no-padding ml_15 visible-lg mt_10 pr_25']//button"


		/**
		 * This method checks if the admin is created and displayed in the Manage admin list
		 * Matches the details(Name,Email,Role,Site(if present)) of the created admin with the data saved in KPCommonPage
		 */
		def static final ifAdminCreated = { browser, formData ->
			browser.delay(1000)
			def newList = []
			def result = false
			def adminFullName
			def pageCount = Integer.parseInt(browser.gettext(PAGE_COUNT))
			for(int k=0;k<pageCount;k++){
				def adminList = []
				browser.delay(2000)
				def listOfAdminsEmail = browser.getLists(ADMIN_LIST,"title")
				def listOfAdminName = browser.getLists(ADMIN_LIST)
				def listOfAdminRoles = browser.getLists(ADMIN_ROLE_LIST)
				adminFullName = KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName)
				adminList.addAll(listOfAdminsEmail);
				browser.delay(1000)
				for(int i=0;i<adminList.size();i++){
					if(listOfAdminName[i].trim().equalsIgnoreCase(adminFullName.trim())){
						if(adminList[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
							if(listOfAdminRoles[i].split(":")[1].trim().equalsIgnoreCase(KPCommonPage.adminRole.trim())){
								if(KPCommonPage.adminRole.trim().equalsIgnoreCase("Trainer") || KPCommonPage.adminRole.trim().equalsIgnoreCase("Site Admin")){
									listOfAdminsEmail.clear()
									browser.delay(1500)
									def xpathForSite = ADMIN_SITE_TO_MATCH.replace("adminEmail",KPCommonPage.adminEmail_Id.trim())
									if(browser.isDisplayed(xpathForSite)){
										def text = browser.gettext(xpathForSite).split(":")[1]
										if(text.contains(",")){
											listOfAdminsEmail = text.trim().split(",")
										}else{
											listOfAdminsEmail.add(text.trim())
										}
									}else{
										return KPCommonPage.returnFailureOutcome(browser, "AdminSiteDisplayIssue", "The admin site was not displayed")
									}
									for(int j=0;j<listOfAdminsEmail.size();j++){
										listOfAdminsEmail = listOfAdminsEmail.sort()
										KPCommonPage.adminSite = KPCommonPage.adminSite.sort()
										if(listOfAdminsEmail[j].trim().equalsIgnoreCase(KPCommonPage.adminSite[j].trim())){
											result = true
										}else{
											result = false
											break
										}
									}
								}
								if(KPCommonPage.adminRole.trim().equalsIgnoreCase("Sub Admin") || KPCommonPage.adminRole.trim().equalsIgnoreCase("HR")){
									result = true
								}
							}
						}
					}
				}
				if(!result){
					browser.scrollToElement2(PAGINATION_NEXT)
					browser.click(PAGINATION_NEXT)
					browser.delay(2000)
				}else{
					break
				}
			}
			if(result){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "AdminCreationIssue", "Created Admin's details do not match")
			}
		}

		//To remove an admin from the admins list
		def static final demoteAnAdmin = { browser, formData ->
			def result
			def xpathToRemoveAdmin
			def pageCount = Integer.parseInt(browser.gettext(PAGE_COUNT))
			for(int i=0;i<pageCount;i++){
				result = new ManageAdminsForm().demoteAdminFromList(browser,formData)
				if(result){
					break
				}
				browser.click(PAGINATION_NEXT)
				browser.delay(2500)
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "RemoveAdminIssue", "Admin was not removed from the list")
			}
		}

		/**
		 * To click on the demote admin when user name and email match with the expected data and then click on the demote link
		 */
		def static demoteAdminFromList(def browser,def formData){
			def xpathToRemoveAdmin;
			def result = false
			def listOfAdminsEmail = browser.getLists(ADMIN_LIST,"title")
			def removeAdminList =  browser.getListElements(DEMOTE_ADMINS)
			for(int i=0;i<listOfAdminsEmail.size();i++){
				if(listOfAdminsEmail[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
					browser.scrollToElement(removeAdminList[i])
					browser.clickElement(removeAdminList[i])
					browser.delay(2000)
					if(browser.isDisplayed(CONFIRM_DELETE)){
						browser.click CONFIRM_DELETE
						browser.delay(2000)
						result = true
					}else {
						return KPCommonPage.returnFailureOutcome(browser, "ConfirmDeleteAdminIssue", "Success option to delete admin was not displayed")
					}
				}
			}
			return result
		}

		/**
		 * Verify that admin has been removed from the admins list
		 */
		def static final isAdminRemoved = { browser, formData ->
			browser.delay(1000)
			def result
			def countText = browser.gettext(PAGE_COUNT)
			if(!countText.equals("")){
				def pageCount = Integer.parseInt(countText)
				browser.click(PAGINATION_FIRST)
				browser.delay(2000)
				for(int i=0;i<pageCount;i++){
					result = new ManageAdminsForm().checkDisplayedAdmin(browser,formData)
					if(result){
						break
					}
					browser.click(PAGINATION_NEXT)
					browser.delay(2500)
				}
			}else{
				result = false
			}
			if(result){
				return KPCommonPage.returnFailureOutcome(browser, "RemoveAdminIssue", "Admin supposed to be removed is still displaying in the list")
			} else {
				return new SuccessOutcome()
			}
		}

		/**
		 *To check if the created admin is displayed on the Manage Admins page
		 */
		def static checkDisplayedAdmin(def browser,def formData){
			def result = false ;
			def listOfAdminsEmail = browser.getLists(ADMIN_LIST,"title")
			for(int i=0;i<listOfAdminsEmail.size();i++){
				if(listOfAdminsEmail[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
					result = true;
					break;
				}
			}
			return result;
		}

		/**
		 *  To click on the Admin name to view admin public profile
		 */
		def static clickOnAdminName = { browser, formData ->
			def adminToBeClicked
			def adminEmailId= browser.getLists(ADMIN_LIST,"title")
			def pathOfAdmin= browser.getListElements(ADMIN_LIST)
			for(int i=0;i<adminEmailId.size();i++){
				if(adminEmailId[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
					browser.scrollToElement(pathOfAdmin[i])
					adminToBeClicked = pathOfAdmin[i]
					break;
				}
			}
			if(adminToBeClicked != null){
				browser.clickElement(adminToBeClicked)
				browser.delay(1000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickAdminNameIssue", "Admin name cannot be clicked")
			}
		}

		/**
		 * To verify the admin details on the view public profile page
		 */
		def static adminDetailsOnPublicProfile = { browser, formData ->
			browser.delay(9000)
			def adminEmailOnProfile = browser.gettext(KPCommonPage.USER_EMAIL_ID_ON_VIEW_PROFILE).trim()
			def adminFullName = browser.gettext(KPCommonPage.USER_NAME_ON_VIEW_PROFILE).trim()
			if(adminFullName.trim().equalsIgnoreCase(KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName))){
				if(KPCommonPage.adminEmail_Id.equalsIgnoreCase(adminEmailOnProfile)){
					return new SuccessOutcome()
				}else {
					return KPCommonPage.returnFailureOutcome(browser, "AdminEmailIdMatchIssue", "Admin email Id does not match")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "AdminNameMatchIssue", "Admin name do not match")
			}
		}

		/**
		 * To click on the the change privilage link of an admin
		 */
		def static clickOnChangePrivilages = { browser, formData->
			def adminPrivilagesToBeChanged
			def adminEmail= browser.getLists(ADMIN_LIST ,"title")
			def changePrivilagePath= browser.getListElements(CHANGE_PRIVILAGES)
			for(int i=0;i<adminEmail.size();i++){
				if(adminEmail[i].equalsIgnoreCase(KPCommonPage.adminEmailId)){
					adminPrivilagesToBeChanged = changePrivilagePath[i]
					break
				}
			}
			if(adminPrivilagesToBeChanged != null){
				browser.scrollToElement(adminPrivilagesToBeChanged)
				browser.clickElement(adminPrivilagesToBeChanged)
				browser.delay(2000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ChangePrivilagesLinkIssue", "Admin Change Privilages link cannot be clicked")
			}
		}

		/**
		 * To click on the the change privilage link of an admin
		 */
		def static clickAdminChangePrivilageLink = { browser, formData->
			def adminPrivilagelinkClick
			def emailIdOfAdmins = browser.getLists(ADMIN_LIST,"title")
			def changePrivilagePath = browser.getListElements(CHANGE_PRIVILAGES)
			for(int i=0; i<emailIdOfAdmins.size();i++){
				if(emailIdOfAdmins[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
					adminPrivilagelinkClick = changePrivilagePath[i]
					break
				}
			}
			if(adminPrivilagelinkClick !=null){
				browser.scrollToElement(adminPrivilagelinkClick)
				browser.clickElement(adminPrivilagelinkClick)
				browser.delay(2000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ChangePrivilagesLinkIssue", "Admin Change Privilages link cannot be clicked")
			}
		}

		/**
		 * To select the admin filter  with the data from the formData
		 */
		def static selectAdminFilter = { browser, formData ->
			if(browser.isDisplayed(ADMIN_FILTER)){
				browser.selectDropdownValue(ADMIN_FILTER,formData[0].trim())
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AdminFilterDisplayIssue", "The admin filter was not displayed")
			}
		}

		/**
		 * To verify if the selected filter value mathes with the admin role displayed
		 */
		def static allDisplayedAdminRoles = { browser, formData ->
			def result = false
			def actualRole
			def pageCount = browser.gettext(PAGE_COUNT)
			//outerloop:for(int i=0;i<pageCount;i++){
			def adminRoleList = browser.getLists(ADMIN_ROLE_LIST)
			for(int j=0;j<adminRoleList.size();j++){

				if(adminRoleList[j].contains(":")){
					actualRole = adminRoleList[j].split(":")[1].trim()
				}
				if(actualRole.trim().contains(KPCommonPage.adminRole.trim())){
					result = true
				}else{
					result = false
					break
				}
			}
			//}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "RoleMismatchIssue", "Admin roles displayed do not match with the filter selected")
			}
		}

		/**
		 * To search for the created admin on the Manage admins page
		 */
		def static searchForCreatedAdmin = { browser, formData ->
			browser.delay(2000)
			browser.scrollToElement2(ADMIN_SEARCH_TEXT_BOX)
			if(browser.isDisplayed(ADMIN_SEARCH_TEXT_BOX)){
				browser.populateField(ADMIN_SEARCH_TEXT_BOX,KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName).trim())
				browser.pressEnter(ADMIN_SEARCH_BUTTON)
				browser.delay(1000)
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AdminSearchTextBoxIssue", "Admin search text box was not displayed")
			}
		}

		/**
		 * @author Aditya
		 * This method checks if trainer admin is created and displayed in the Manage admin list of site admin
		 * Matches the details(Name,Email) of the created trainer admin with the data saved in KPCommonPage
		 */

		def static ifTrainerAdminCreated = { browser, formData ->
			def newList = []
			def result = false
			def adminFullName
			def pageCount = Integer.parseInt(browser.gettext(PAGE_COUNT))
			for(int k=0;k<pageCount;k++){
				def adminList = []
				browser.delay(2000)
				def listOfAdminsEmail = browser.getLists(ADMIN_LIST,"title")
				def listOfAdminName = browser.getLists(ADMIN_LIST)

				adminFullName = KPCommonPage.getFullName(KPCommonPage.adminFirstNamee,KPCommonPage.adminMiddleName,KPCommonPage.adminLastName)
				adminList.addAll(listOfAdminsEmail);
				browser.delay(1000)
				for(int i=0;i<adminList.size();i++){
					if(listOfAdminName[i].trim().equalsIgnoreCase(adminFullName.trim())){
						if(adminList[i].trim().equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim())){
							result = true
						}
					}
				}
				if(!result){
					browser.scrollToElement2(PAGINATION_NEXT)
					browser.click(PAGINATION_NEXT)
					browser.delay(2000)
				}else{
					break
				}
			}
			if(result){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "AdminCreationIssue", "Created Trainer Admin's details do not match")
			}
		}

		/**
		 * @author Aditya
		 * This method verifies if the trainer which is demoted by org admin should not be displayed in the Manage Admins page of site admin
		 */

		def static ifTrainerIsDisplayedAfterRemove =  { browser, formData ->
			browser.delay(2000)
			def text
			if(browser.isDisplayed(ADMIN_SEARCH_TEXT_BOX)){
				browser.scrollToElement2(ADMIN_SEARCH_TEXT_BOX)
				browser.populateField(ADMIN_SEARCH_TEXT_BOX,KPCommonPage.adminEmail_Id.trim())
				browser.click(ADMIN_SEARCH_BUTTON)
				browser.delay(1000)
				if(browser.isDisplayed(TEXT_MSG)){
					text = browser.gettext(TEXT_MSG).trim()
					if(text.equalsIgnoreCase("No Results Found") && text!=null){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "MessageMismatchIssue", "The message did not match or it is null")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AdminWasNotDemotedIssue", "Admin was not demoted by admin")
				}
			}else if(browser.isDisplayed(TEXT_MSG)){
				return new SuccessOutcome()
			}
		}

		/**
		 * @author Aditya
		 * This method verifies the trainer admin should not have a site when privilages(removes the site) are changed by site admin
		 */

		def static ifRemovedAdminHasSite = { browser, formData ->
			if(browser.isDisplayed(ADMIN_SEARCH_TEXT_BOX)){
				browser.scrollToElement2(ADMIN_SEARCH_TEXT_BOX)
				browser.populateField(ADMIN_SEARCH_TEXT_BOX,KPCommonPage.adminEmail_Id.trim())
				browser.delay(1000)
				browser.pressEnter(ADMIN_SEARCH_BUTTON)
				browser.delay(1000)
				if(browser.isDisplayed(ADMIN_LIST)){
					def email = browser.getLists(ADMIN_LIST,"title")[0].trim()
					def role = browser.gettext(ADMIN_ROLE_LIST).split(":")[1].trim()
					def site = browser.gettext(".//span[@ng-if='showSites(subadmin)']").trim()
					if(email.equalsIgnoreCase(KPCommonPage.adminEmail_Id.trim()) && role.equalsIgnoreCase(KPCommonPage.adminRole.trim())){
						if(site.equalsIgnoreCase("Sites :")){
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "AdminListDisplayIssue", "Admin list was not displayed")
						}
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AdminListDisplayIssue", "Admin list was not displayed")
				}
			}
		}
	}
}