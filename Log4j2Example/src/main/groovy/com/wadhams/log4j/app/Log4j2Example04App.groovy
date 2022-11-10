package com.wadhams.log4j.app

import com.wadhams.log4j.biz.ExampleBiz
import com.wadhams.log4j.service.ExampleService
import groovy.util.logging.Log4j2

@Log4j2 (value = 'logger')
class Log4j2Example04App {
	ExampleService service = new ExampleService()
	ExampleBiz biz = new ExampleBiz()
	
	static main(args) {
		println 'Log4j2Example04App started...'
		println ''

		// VM Arguments:
		// -Dlog4j.configurationFile=config/log4j2-example04.xml
		
		Log4j2Example04App app = new Log4j2Example04App()
		app.execute()
		
		println 'Log4j2Example04App ended.'
	}
	
	def execute() {
		println "logger.getName()...: ${logger.getName()}"
		println ''
		
		logger.fatal('execute()...fatal...')
		logger.error('execute()...error...')
		logger.warn('execute()...warn...')
		logger.info('execute()...info...')
		logger.debug('execute()...debug...')
		logger.trace('execute()...trace...')
		println ''
		
		service.calculate()
		
		biz.audit()
	}
}
