package com.wadhams.log4j.biz

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class ExampleBiz {
	def audit() {
		Logger logger = LogManager.getLogger(ExampleBiz.class)
		
		println "logger.getName()...: ${logger.getName()}"
		println ''
		
		logger.fatal('audit()...fatal...')
		logger.error('audit()...error...')
		logger.warn('audit()...warn...')
		logger.info('audit()...info...')
		logger.debug('audit()...debug...')
		logger.trace('audit()...trace...')
		println ''
	}
}
