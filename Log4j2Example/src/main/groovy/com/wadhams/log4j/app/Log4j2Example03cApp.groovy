package com.wadhams.log4j.app

import groovy.util.logging.Log4j2

@Log4j2 (value = 'logger')
class Log4j2Example03cApp {
	static main(args) {
		println 'Log4j2Example03cApp started...'
		println ''

		// VM Arguments:
		// -Dlog4j.configurationFile=config/log4j2-example03c.xml
		
		Log4j2Example03cApp app = new Log4j2Example03cApp()
		app.execute()
		
		println 'Log4j2Example03cApp ended.'
	}
	
	def execute() {
		println 'This application uses a configuration file to configure the Log4j2 environment.'
		println 'The location of the configuration file is provided via a system property set via a VM Argument.'
		println ''
		println 'The only configuration provided is the root logger set to log at the WARN level.'
		println ''
		println 'This application used the groovy.util.logging.Log4j2 annotation @Log4j2'
		println 'It also changes the name of the injected logger from the default \'log\' to \'logger\'.'
		100.times {print '-'}
		3.times {println ''}
		
		println "logger.getName().....: ${logger.getName() ?: 'blank name = ROOT Logger'}"
		println "Configuration File...: ${System.getProperty("log4j.configurationFile")}"
		println ''
		
		logger.fatal('execute()...fatal...')
		logger.error('execute()...error...')
		logger.warn('execute()...warn...')
		logger.info('execute()...info...')
		logger.debug('execute()...debug...')
		logger.trace('execute()...trace...')
		println ''
	}
}
