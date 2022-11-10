package com.wadhams.log4j.app

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.Logger
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.config.Configuration
import org.apache.logging.log4j.core.config.ConfigurationFactory
import org.apache.logging.log4j.core.config.ConfigurationSource
import org.apache.logging.log4j.core.config.LoggerConfig
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory
import org.apache.logging.log4j.core.layout.PatternLayout

class Log4j2Example03eApp {
	static Logger logger
	
	static main(args) {
		println 'Log4j2Example03eApp started...'
		println ''

		logger = configureLog4j()
		
		Log4j2Example03eApp app = new Log4j2Example03eApp()
		app.execute()
		
		println 'Log4j2Example03eApp ended.'
	}
	
	static Logger configureLog4j() throws FileNotFoundException, IOException {
		//Get instance of configuration factory. Your options are default ConfigurationFactory, XMLConfigurationFactory,
		// 	YamlConfigurationFactory & JsonConfigurationFactory
		ConfigurationFactory factory =  XmlConfigurationFactory.getInstance()
 
		// Locate the source of this configuration, this located file is a dummy file contains just an empty configuration Tag
		ConfigurationSource configurationSource = new ConfigurationSource(new FileInputStream(new File("config/log4j2-empty.xml")))
 
		// Get context instance
		LoggerContext context = new LoggerContext("ExampleAppLoggerContext")
 
		// Get a reference from configuration
		Configuration configuration = factory.getConfiguration(context, configurationSource)
 
		// Create default console appender
		ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout())
 
		// Add console appender into configuration
		configuration.addAppender(appender)
 
		// Create loggerConfig
		LoggerConfig loggerConfig = new LoggerConfig("com.wadhams.log4j.app", Level.INFO, false)
 
		// Add appender
		loggerConfig.addAppender(appender, null, null)
 
		// Add logger and associate it with loggerConfig instance
		configuration.addLogger("com.wadhams.log4j.app", loggerConfig)
		
		// Start logging system
		context.start(configuration)

		return context.getLogger("com.wadhams.log4j.app")
	}
	
	def execute() {
		println "logger.getName().....: ${logger.getName()}"
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
