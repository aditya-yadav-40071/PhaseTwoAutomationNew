package com.KPPhaseTwo.tools

import com.gargoylesoftware.htmlunit.ElementNotFoundException
//import com.KPPhaseTwo.app.Page
import groovy.transform.Immutable
import org.openqa.selenium.By
import org.openqa.selenium.Capabilities
import org.openqa.selenium.Cookie
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.Keys
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.HasInputDevices
import org.openqa.selenium.interactions.Mouse
import org.openqa.selenium.internal.Locatable
import org.openqa.selenium.remote.Augmenter
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.OutputType
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.commons.io.FileUtils
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.Assert;
import com.KPPhaseTwo.utils.*
import java.text.DateFormat;
import java.text.SimpleDateFormat
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException
import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import org.openqa.selenium.JavascriptExecutor
import static java.lang.Thread.sleep as wait
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import java.text.DateFormatSymbols

/**
 * Created by sandhya on 12/9/13.
 */
public final class Browser {

	//private WebDriver driver

	private final static def DEF_GRID_URL = ""
	private final static def DEF_CAPABILITY = null
	public static final def XPATH = "xpath"

	private static def capabality

	/*
	 private final def capabilities
	 private final def gridUrl
	 private final def props
	 */
	private final def WebDriver driver

	/*public Browser(def capabilities,
	 def gridUrl,
	 def props) {
	 capabality = capabilities.toString()
	 driver = new RemoteWebDriver(new URL(gridUrl),capabilities)	
	 // driver = new ChromeDriver() 
	 if (props.timeout) {
	 driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS)
	 }
	 if (props.maximize) {
	 driver.manage().window().maximize()
	 }
	 }*/	

	public Browser(def browserName, def props) {

		//driver = new RemoteWebDriver(new URL(gridUrl),capabilities)
		if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver()
		}else if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver()
		}else if(browserName.equalsIgnoreCase("internetExplorer")){
			driver = new InternetExplorerDriver()
		}

		if (props.timeout) {
			driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS)
		}

		if (props.maximize) {
			driver.manage().window().maximize()
		}
	}

	//Open Application Url
	public void openUrl(def url) {
		driver.get(url)
		delay(2000)
	}

	//To get Page title
	public getTitle() {
		def Title = driver.getTitle()
		Title
	}

	//To close the browser
	public void close() {
		driver.quit()
	}

	//To close current window
	public void closeWindow(){
		driver.close()
	}

	//To wait for certain time
	public void delay(def period) {
		wait period
	}

	//Explicit wait Condition
	public void explicitWait(def element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getElement(XPATH, element)));
	}

	//To get the element
	public void getLinks(def element){
		driver.get(element)
	}

	// Get URL for the current page
	public getCurrentUrl() {
		return driver.getCurrentUrl()
	}

	//Current working directory
	public def getCurrentDirectory(){
		final String currDir = System.getProperty("user.dir");
		return currDir
	}

	//On mouse over method
	public onMouseOver(String mainMenu, String subMenu){
		WebElement ele = getElement(XPATH, mainMenu)
		WebElement menuToClick = getElement(XPATH, subMenu)
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform()
		delay(5000)
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", menuToClick);
	}

	//To click hidden element
	public void clickHideElement(def element){
		//WebElement ele = getElement(XPATH, element)
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * To get the tagName of an element
	 * @param element
	 * @return tagName
	 */
	public getTagName(String element) {
		def tagname
		WebElement ele = getElement(XPATH, element)
		if(ele != null){
			tagname = getElement(XPATH, element).tagName
			if(tagname.equals("input")){
				tagname = getElement(XPATH, element).getAttribute("type")
			}
		}else{
			tagname = "NotFound"
		}
		tagname
	}

	/** Takes snapshot
	 * @param filename(String) filename of the snapshot
	 */
	public void takeScreenShot(String filename) {

		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\Screenshots\\" + filename + System.currentTimeMillis() + ".png"))
		} catch (IOException e) {
			e.printStackTrace()
		}
	}

	//Upload a file
	public uploadFile(def element,def location){
		driver.setFileDetector(new LocalFileDetector());
		delay(3000)
		WebElement fileInput = driver.findElement(By.xpath(element));
		fileInput.sendKeys(location);
	}

	//Upload a file
	public uploadFile(def location){
		StringSelection ss = new StringSelection(location);
		delay(2000)
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		delay(2000)
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * To enter tab
	 * @param element
	 * @return
	 */
	public pressTab(String element){
		WebElement ele = getElement(XPATH, element)
		ele.sendKeys(Keys.TAB)
	}

	//To press ENTER button
	public pressEnter(String element){
		WebElement ele = getElement(XPATH,element)
		ele.sendKeys(Keys.ENTER)
		delay(1000)
	}

	//To press DOWN ARROW key
	public pressDownArrow(String element){
		WebElement ele = getElement(XPATH,element)
		ele.sendKeys(Keys.DOWN)
		delay(1000)
	}

	//To click an element
	public def clickElement(WebElement element){
		delay(2000)
		element.click()
	}

	//Get element text
	public def getTexts(WebElement element){
		String text
		if(element != null){
			text = element.getText()
		}
		text
	}

	public def getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11 ) {
			month = months[num].substring(0, 3);
		}
		return month;
	}

	//To click checkbox
	public void clickCheckBox(String element, String data){
		def isSelected = getElement(XPATH, element).isSelected()
		if(data.trim().equals("1") && !isSelected){
			getElement(XPATH, element).click()
		}else{
			if(data.trim().equals("0") && isSelected){
				getElement(XPATH, element).click()
			}
		}
	}

	//To check weather element is selected
	public boolean isSelected(WebElement element){
		return element.isSelected()
	}

	public void clickMdCheckBox(String element, String data){
		String value
		WebElement Element = getElement(XPATH, element)
		if(Element != null){
			value = Element.getAttribute("aria-checked")
		}
		if(data.trim().equals("1") && value.equalsIgnoreCase("false")){
			getElement(XPATH, element).click()
		}else{
			if(data.trim().equals("0") && value.equalsIgnoreCase("true")){
				getElement(XPATH, element).click()
			}
		}
	}

	/**
	 * To Get the selected value from the dropdown
	 * @param browser browser instance
	 * @param element
	 */
	public getDropdownValue(String element){
		def selectedOption
		WebElement option = getElement(XPATH,element)
		if(option != null){
			selectedOption = new Select(option).getFirstSelectedOption().getText()
		}
		selectedOption
	}

	/**
	 * To select from radio button
	 */

	public void selectRadioButton(def element){
		//WebElement ele = getElement(XPATH, element)
		click element
	}

	/**
	 * To Delete Cookies before loging into the Application
	 */
	public void deleteCookies() {
		List <Cookie> cookie = driver.manage().deleteAllCookies()
		System.out.println("Deleted all cookies before login to the application")
	}

	/**
	 * To get current Date and convert to String
	 * @return String
	 */
	public def CurrentDate(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat SDF = new SimpleDateFormat("MM/DD/yyyy");
		def s = SDF.format(calendar.getTime());
		return s
	}

	//To scroll to an element
	public def scrollToElement(def element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	//To scroll to an element
	public def scrollToElement2(def element){
		WebElement ele = driver.findElement(By.xpath(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	//scrolldown window
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	//To Switch to iframe
	public void switchToIframe(def frame){
		driver.switchTo().frame(frame)
	}

	//To get Number of windows
	public void switchTowindow(def winHandle){
		driver.switchTo().window(winHandle);
	}

	//To get Current window
	public def getWindowHandle(){
		driver.getWindowHandle()
	}

	public def getWindows(){
		driver.getWindowHandles()
	}

	/**
	 *
	 * @param locator   locator of the element(ie., id/xpath/....)
	 * @param element   element on the page
	 * @return  PageName
	 */
	public String gettext(String element){
		String text
		WebElement Element = getElement(XPATH, element)
		if(Element != null){
			text = Element.getText()
		}
		text
	}

	//To get text based on attribute(EX: id,src, value...)
	public String gettext(String element, String attribute){
		String value
		WebElement Element = getElement(XPATH, element)
		if(Element != null){
			value = Element.getAttribute(attribute)
		}
		value
	}

	//To get text based on attribute(EX: id,src, value...)
	public String gettexts(WebElement element, String attribute){
		String value
		//WebElement Element = getElement(XPATH, element)
		if(element != null){
			value = element.getAttribute(attribute)
		}
		value
	}



	/**
	 * Collect validation messages from error message fields
	 * @param fields array of error message fields
	 * @param locatorType locator for error message fields
	 */
	public def getValidationMessages(def fields){
		delay(500)
		def validationMessages = []
		for(errMessageFields in fields) {
			if(getElement(XPATH, errMessageFields) != null){
				ArrayList<WebElement> validationMsgs = driver.findElements(By.xpath(errMessageFields))
				for(int i =0;i<=validationMsgs.size()-1;i++){
					if(validationMsgs.get(i).getText() != null && validationMsgs.get(i).getText() != ""){
						validationMessages.add(validationMsgs.get(i).getText().trim())
					}
				}
			}
		}
		return validationMessages
	}

	/**
	 * Mouse over action
	 * @param WebElement
	 * @param String
	 */
	private void mousemoveoverAction(WebElement element, String appElement ) {
		if(element!=null){
			Actions builder = new Actions(new Augmenter().augment(driver))
			builder.moveToElement(element).build().perform()
			delay(500)
		} else {
			takeScreenShot(appElement+"issue")
			println "Element Not found in the page...."
		}
	}

	/**
	 * Selects from drop down based on value
	 * @param String
	 * @param String
	 */
	public void selectDropdownValue(String element, String value ){
		boolean flag = false
		try{
			WebElement dropDownListBox = getElement(XPATH, element)
			List<WebElement> lists = dropDownListBox.findElements(By.tagName("option"))
			for(int i=0; i<= lists.size()-1;i++){
				String dropdownValue = lists.get(i).getText().trim()
				if(value.equalsIgnoreCase(dropdownValue)){
					flag = true
					break
				}
			}
			if(!flag){
				value = "Select"
			}
			if(dropDownListBox != null){
				Select clickThis = new Select(dropDownListBox)
				clickThis.selectByVisibleText(value)
			}
		}catch (NoSuchElementException e){
			System.out.println("Unable to select list item from the dropdown");
		}
	}

	/**
	 * Selects from drop down based on index
	 * @param String
	 * @param String
	 */
	public void selectDropdownValue(String element){
		WebElement dropDownListBox = getElement(XPATH, element)
		ArrayList<String> npsTerminalValue

		if(dropDownListBox != null){
			npsTerminalValue = dropDownListBox.findElements(By.tagName("option"))
			Select clickThis = new Select(dropDownListBox)
			if(npsTerminalValue.size() > 1){
				clickThis.selectByIndex(1)
			}else{
				clickThis.selectByIndex(0)
			}
		}else{
			println "Element not found....."
			takeScreenShot("ElementNotFound")
		}
	}

	/**
	 * To select through index from a dropdown
	 */
	public void selectOptionFromDropdown(def element, def indexvalue){
		WebElement mySelectElm = getElement(XPATH, element)
		Select mySelect= new Select(mySelectElm);
		mySelect.selectByIndex(indexvalue);
	}

	//To get the list based on the tagname
	public def getListByTagName(String element, String tag){
		WebElement dropDownListBox = getElement(XPATH, element)
		List<WebElement> lists = dropDownListBox.findElements(By.tagName(tag))
		lists
	}

	//To get all Links on the page
	public def getAllLinks(){
		delay(2000)
		def links = []
		def allLinks = driver.findElements(By.tagName("a"))

		for (WebElement myElement : allLinks){
			if(myElement.getText() != "")
				links.add(myElement.getText().trim())
		}
		links
	}

	//To verify element is enabled
	public checkEnabled(String element){
		def Element = getElement(XPATH, element)
		def isEnabled
		if(Element != null){
			isEnabled = Element.isEnabled()
		}
		isEnabled
	}

	//To verify element is displayed
	public isDisplayed(String element){
		def Element = getElement(XPATH, element)
		def isDisplayed
		if(Element != null){
			isDisplayed = Element.isDisplayed()
		}
		isDisplayed
	}

	//To get the text of the lists
	public def getLists(String element){
		def list = []
		List<WebElement> lists = driver.findElements(By.xpath(element))
		for(int i=0;i<lists.size();i++){
			String text = lists.get(i).getText()
			list.add(text.trim())
		}
		list
	}

	//To get the attribute values
	public def getLists(String element, String value){
		def list = []
		List<WebElement> lists = driver.findElements(By.xpath(element))
		for(int i=0;i<lists.size();i++){
			String text = lists.get(i).getAttribute(value)
			list.add(text.trim())
		}
		list
	}

	//To get all elements from the list
	public def getListElements(String element){
		List<WebElement> elementList = driver.findElements(By.xpath(element))
		elementList
	}

	//To navigate back to original page
	public void navigateBack(){
		driver.navigate().back()
	}

	//To refresh the page
	public void refreshPage(){
		driver.navigate().refresh()
	}

	//Get current month
	public getCurrentMonth(){
		Calendar c = Calendar.getInstance();
		String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		return month
	}

	//Get the name of the month using the month' number
	public def getMonthFromNo(int monthNumber) {
		String monthName = "";
		String[] months = new DateFormatSymbols().getMonths();
		if (monthNumber >= 1 && monthNumber <= 12) {
			monthName = months[monthNumber-1];
		}
		return monthName;
	}

	public def selectDate(String day,String value){
		def allDaysElemets = getListElements(day)
		def allDaysList = getLists(day)
		if(value!=null){
			for(int i=0;i<allDaysElemets.size();i++){
				if(value.equalsIgnoreCase(allDaysList[i])){
					clickElement(allDaysElemets[i])
				}
			}
		}
	}

	//To click element
	public void click(element) {
		click(XPATH, element)
	}

	@Override
	public void click(String locator,String element) {
		clickAction(getElement(locator,element), element)
	}
	private void clickAction(WebElement element, String appElement ) {
		try {
			if(element!=null){
				Actions selAction = new Actions(driver)
				selAction.click(element).perform()
				driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS)
			} else {
				String message="Element not found in the  : "
				String show= appElement +" "+message + " " + " page "
			}
		} catch(ElementNotFoundException e){
			println "Unbale to click on the button"
		}
	}

	//To enter data
	public def populateField(element, value) {
		println """$element $value"""
		populateField(XPATH, element, value)
	}

	def populateField(def locator,def element, def value){
		try {
			this.inputField(this.getElement(locator,element), element,value)
		} catch(ElementNotFoundException e){
			System.out.println("Input element not found for element is" + element)
		}
	}


	//TODO Have to move the element check to page level
	def inputField(def element, def appElement,def value) {
		if(element!=null){
			WebElement field=element
			if((field.isDisplayed())& (field.isEnabled()))	{
				field.click()
				delay(500)
				field.clear()
				field.sendKeys(value)
			}
		}else{
			String message="Input Element not found in the  : "
			String show= appElement +" "+message + " " + " page "
			System.out.println(show)
		}
	}

	//To check element is present or not
	def WebElement getElement(def locator,def element ){
		By byElement
		WebElement query
		switch(locator){
			case "xpath":    			byElement = By.xpath(element)
				break
			case "id":	 				byElement = By.id(element)
				break
			case "name": 				byElement = By.name(element)
				break
			case "classname":			byElement = By.className(element)
				break
			case "linkname":			byElement = By.linkText(element)
				break
			case "paritallinkname":	 	byElement = By.partialLinkText(element)
				break
			case "tagname":		 		byElement = By.tagName(element)
				break
			case "css":		      		byElement = By.cssSelector(element)
				break
			default:		 	  		throw new RuntimeException("Invalid locator")
		}
		try {
			//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS)

			query = driver.findElement(byElement)
			return query
		}catch(ElementNotFoundException e){
			return query = null
		}catch(NoSuchElementException e){
			return query = null
		}
	}


}
