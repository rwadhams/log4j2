package com.wadhams.log4j.app

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.Logger

class Log4j2Example02App {
	static main(args) {
		println 'Log4j2Example02App started...'
		println ''

		System.setProperty('log4j.configurationFile','config/log4j2-example02.xml')
		
		Log4j2Example02App app = new Log4j2Example02App()
		app.execute()
		
		println 'Log4j2Example02App ended.'
	}
	
	def execute() {
		println 'This application uses a configuration file to configure the Log4j2 environment.'
		println 'The location of the configuration file is provided via a system property.'
		println ''
		println 'There is a hierarchy of loggers:'
		println '\'Root\' then \'com.wadhams\' then \'com.wadhams.log4j.app\'.'
		println 'Note the level of each logger and what messages are logged.'
		100.times {print '-'}
		3.times {println ''}
		
		Logger logger1 = LogManager.getLogger(Log4j2Example02App.class)
		println "logger1.getName()...............: ${logger1.getName()}"
		println "logger1.getParent().getName()...: ${logger1.getParent().getName() ?: 'blank name = ROOT Logger'}"
		println "logger1.getLevel()..............: ${logger1.getLevel()}"
		logger1.getLevel()
		logger1.fatal('execute()...fatal...')
		logger1.error('execute()...error...')
		logger1.warn('execute()...warn...')
		logger1.info('execute()...info...')
		logger1.debug('execute()...debug...')
		logger1.trace('execute()...trace...')
		println ''
		
		Logger logger2 = LogManager.getLogger('com.wadhams.log4j.app')
		println "logger2.getName()...............: ${logger2.getName()}"
		println "logger2.getParent().getName()...: ${logger2.getParent().getName() ?: 'blank name = ROOT Logger'}"
		println "logger2.getLevel()..............: ${logger2.getLevel()}"
		logger2.fatal('execute()...fatal...')
		logger2.error('execute()...error...')
		logger2.warn('execute()...warn...')
		logger2.info('execute()...info...')
		logger2.debug('execute()...debug...')
		logger2.trace('execute()...trace...')
		println ''
		
		Logger logger3 = LogManager.getLogger('com.wadhams')
		println "logger3.getName()...............: ${logger3.getName()}"
		println "logger3.getParent().getName()...: ${logger3.getParent().getName() ?: 'blank name = ROOT Logger'}"
		println "logger3.getLevel()..............: ${logger3.getLevel()}"
		logger3.fatal('execute()...fatal...')
		logger3.error('execute()...error...')
		logger3.warn('execute()...warn...')
		logger3.info('execute()...info...')
		logger3.debug('execute()...debug...')
		logger3.trace('execute()...trace...')
		println ''
		
		Logger logger4 = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME)	//or LogManager.getrootLogger()
		println "logger4.getName()...............: ${logger4.getName() ?: 'blank name = ROOT Logger'}"
		println "logger4.getParent().getName()...: ${logger4.getParent()?.getName()}"
		println "logger4.getLevel()..............: ${logger4.getLevel()}"
		logger4.fatal('execute()...fatal...')
		logger4.error('execute()...error...')
		logger4.warn('execute()...warn...')
		logger4.info('execute()...info...')
		logger4.debug('execute()...debug...')
		logger4.trace('execute()...trace...')
		println ''
	}
}
