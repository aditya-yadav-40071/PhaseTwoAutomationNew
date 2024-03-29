package com.KPPhaseTwo.slingshot.commands

import groovy.util.logging.Log4j

import com.KPPhaseTwo.web.WebPage

@Log4j
class PageCommand implements ICommand{
	def knowledgePodiumDsl
	def slingShotWeb
	
    public CommandResult execute(CommandData commandData) {
        log.info("PageCommand executing " + commandData)
        def inPage = knowledgePodiumDsl.getCurrentPage(slingShotWeb?.browser)
		log.info("inPage " + inPage + " : " + commandData.get("parameter"))
		
		if(inPage!=null && inPage instanceof WebPage) {
			def expectedPage = commandData.get("parameter");
			if(expectedPage.equals(inPage.getPageKey()) ) {
				def cmdResult = new CommandResult(ResultType.SUCCESS);
				cmdResult.activePage = inPage;
				return cmdResult;
			} else {
				def cmdResult = new CommandResult(ResultType.FAILURE);
				cmdResult.expectedOutcome = "to be in page: " + commandData.get("parameter");
				cmdResult.actualOutcome =   "is in page   : " + inPage.getPageKey();
				return cmdResult;
			}
		}
    }

}
