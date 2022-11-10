package com.wadhams.log4j.service

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class ExampleService {
	def calculate() {
		Logger logger = LogManager.getLogger(ExampleService.class)
		
		println "logger.getName()...: ${logger.getName()}"
		println ''
		
		logger.fatal('calculate()...fatal...')
		logger.error('calculate()...error...')
		logger.warn('calculate()...warn...')
		logger.info('calculate()...info...')
		logger.debug('calculate()...debug...')
		logger.trace('calculate()...trace...')
		println ''
	}
}
