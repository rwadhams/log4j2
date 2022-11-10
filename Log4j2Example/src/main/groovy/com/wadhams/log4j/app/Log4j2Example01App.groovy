package com.wadhams.log4j.app

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.Logger

class Log4j2Example01App {
	static main(args) {
		println 'Log4j2Example01App started...'
		println ''

		Log4j2Example01App app = new Log4j2Example01App()
		app.execute()
		
		println 'Log4j2Example01App ended.'
	}
	
	def execute() {
		println 'This application is NOT reading any Log4j2 configuration or programatically configuring any loggers.'
		println 'The "Root" logger is being provided by default with its logging level set to ERROR.'
		println 'Each non-root logger has the Root logger as its parent. There is no heirarchy.'
		println 'Note: The Logger import is: "org.apache.logging.log4j.core.Logger". The core package may be required for more details.'
		100.times {print '-'}
		3.times {println ''}
		
		Logger logger1 = LogManager.getLogger(Log4j2Example01App.class)
		println "logger1.getName()...............: ${logger1.getName()}"
		println "logger1.getParent().getName()...: ${logger1.getParent().getName() ?: 'blank name = ROOT Logger'}"
		logger1.fatal('execute()...fatal...')
		logger1.error('execute()...error...')
		logger1.warn('execute()...warn...')
		println ''
		
		Logger logger2 = LogManager.getLogger('com.wadhams.log4j.app')
		println "logger2.getName()...............: ${logger2.getName()}"
		println "logger2.getParent().getName()...: ${logger2.getParent().getName() ?: 'blank name = ROOT Logger'}"
		logger2.fatal('execute()...fatal...')
		logger2.error('execute()...error...')
		logger2.warn('execute()...warn...')
		println ''
		
		Logger logger3 = LogManager.getLogger('com.wadhams')
		println "logger3.getName()...............: ${logger3.getName()}"
		println "logger3.getParent().getName()...: ${logger3.getParent().getName() ?: 'blank name = ROOT Logger'}"
		logger3.fatal('execute()...fatal...')
		logger3.error('execute()...error...')
		logger3.warn('execute()...warn...')
		println ''
		
		Logger logger4 = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME)	//or LogManager.getrootLogger()
		println "logger4.getName()...............: ${logger4.getName() ?: 'blank name = ROOT Logger'}"
		println "logger4.getParent().getName()...: ${logger4.getParent()?.getName()}"
		logger4.fatal('execute()...fatal...')
		logger4.error('execute()...error...')
		logger4.warn('execute()...warn...')
		println ''
	}
}
