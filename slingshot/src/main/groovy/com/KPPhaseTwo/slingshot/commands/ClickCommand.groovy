package com.KPPhaseTwo.slingshot.commands

import groovy.util.logging.Log4j

@Log4j
class ClickCommand implements ICommand {

    def slingShotWeb

    def knowledgePodiumDsl    
	
	public CommandResult execute(CommandData commandData) {
		def clickKey = commandData.get('parameter')
		log.info "Click Key is ${clickKey}"
		def outcome = knowledgePodiumDsl.clickAction(slingShotWeb?.browser, clickKey)
		log.info("CLICK outcome  :" +outcome)
		if(outcome.isSuccess()) {
			def cmdResult = new CommandResult(ResultType.SUCCESS);
			return cmdResult;
		}else if(outcome.isFailure()) {
			def cmdResult = new CommandResult(ResultType.FAILURE);
			cmdResult.message = outcome.getFailureMessage();
			return cmdResult;
		}
	}
}