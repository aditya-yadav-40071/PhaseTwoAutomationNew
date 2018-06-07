package com.KPPhaseTwo.app.factory

import com.KPPhaseTwo.app.pages.PageDefEntry
import com.KPPhaseTwo.app.pages.PageDefs
import com.KPPhaseTwo.web.WebPage

class WebPageFactory {
	/* Factory Method to create appropriate WebPage, based on key passed in */
	public static WebPage createWebPage (def pageKey) {
		WebPage webpage;
		if(pageKey != null) {
			//webpage = new LoginPage();
			PageDefEntry pageDefEntry = PageDefs.getPageDefEntry(pageKey)
			if(pageDefEntry!=null) {
				if(pageDefEntry.className != null) {
					def classRef = Class.forName(pageDefEntry.className);
					webpage = classRef.newInstance()
				}
			}
		}
		if(webpage == null) webpage = new WebPage(); // if no page found, create default WebPage instance
		webpage.pageKey = pageKey;
		return webpage;
	}
	
}
