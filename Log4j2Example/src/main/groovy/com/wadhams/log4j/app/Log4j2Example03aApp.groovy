package com.wadhams.log4j.app

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import com.wadhams.log4j.service.ExampleService
import groovy.util.logging.Log4j2

class Log4j2Example03aApp {
	static main(args) {
		println 'Log4j2Example03aApp started...'
		println ''

		System.setProperty('log4j.configurationFile','config/log4j2-example03a.xml')
		
		Log4j2Example03aApp app = new Log4j2Example03aApp()
		app.execute()
		
		println 'Log4j2Example03aApp ended.'
	}
	
	def execute() {
		println 'This application uses a configuration file to configure the Log4j2 environment.'
		println 'The location of the configuration file is provided via a system property set programatically.'
		println ''
		println 'The only configuration provided is the root logger set to log at the INFO level'
		100.times {print '-'}
		3.times {println ''}
		
		Logger logger = LogManager.getRootLogger()
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
