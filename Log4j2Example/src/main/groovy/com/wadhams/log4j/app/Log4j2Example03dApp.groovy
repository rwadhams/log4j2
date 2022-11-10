package com.wadhams.log4j.app

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration

import com.wadhams.log4j.service.ExampleService

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.LogManager

class Log4j2Example03dApp {
	static final Logger logger = LogManager.getLogger(Log4j2Example03dApp.class)
	
	ExampleService service = new ExampleService()
	
	static main(args) {
		println 'Log4j2Example03dApp started...'
		println ''

		Log4j2Example03dApp.reconfigureLog4j2v1()		//Console at DEBUG
		//Log4j2Example03dApp.reconfigureLog4j2v2()		//File at DEBUG
		//Log4j2Example03dApp.reconfigureLog4j2v3()		//Console at DEBUG, Another logger at WARN
		
		logger.debug('Hello from Log4j2')
		logger.debug('This is a Debug Message!')
		logger.info('This is an Info Message!')
		try {
			System.out.println(100/0)
		}
		catch(Exception e) {
			logger.error('Error Occured', e)
		}

		println ''
		
		Log4j2Example03dApp app = new Log4j2Example03dApp()
		app.execute()

		println 'Log4j2Example03dApp ended.'
	}

	static void reconfigureLog4j2v1() {
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder()
		builder.setStatusLevel(Level.WARN)
		builder.setConfigurationName('DefaultLogger')

		// create the rootLogger
		RootLoggerComponentBuilder rootLoggerBuilder = builder.newRootLogger(Level.DEBUG)
		
		// create a Layout with a pattern
		LayoutComponentBuilder layoutBuilder = builder.newLayout('PatternLayout')
		layoutBuilder.addAttribute('pattern', '%d %p %c [%t] %m%n')

		// create a console appender
		AppenderComponentBuilder appenderBuilder = builder.newAppender('Console', 'CONSOLE')
		appenderBuilder.addAttribute('target', ConsoleAppender.Target.SYSTEM_OUT)
		appenderBuilder.add(layoutBuilder)
		
		// create appenderRef for 'Console'
		AppenderRefComponentBuilder appenderRefBuilder = builder.newAppenderRef('Console')
		rootLoggerBuilder.add(appenderRefBuilder)
		
		builder.add(appenderBuilder)
		builder.add(rootLoggerBuilder)
		
		builder.writeXmlConfiguration(System.out)
		println ''
		
		Configurator.reconfigure(builder.build())
	}

	static void reconfigureLog4j2v2() {
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder()
		builder.setStatusLevel(Level.WARN)
		builder.setConfigurationName('DefaultLogger')

		// create the rootLogger
		RootLoggerComponentBuilder rootLoggerBuilder = builder.newRootLogger(Level.DEBUG)
		
		// create a Layout with a pattern
		LayoutComponentBuilder layoutBuilder = builder.newLayout('PatternLayout')
		layoutBuilder.addAttribute('pattern', '%d %p %c [%t] %m%n')

		// create a rolling file appender
		ComponentBuilder sizeBasedTriggeringPolicy = builder.newComponent('SizeBasedTriggeringPolicy')
		sizeBasedTriggeringPolicy.addAttribute('size', '1KB')
		
		ComponentBuilder triggeringPolicy = builder.newComponent('Policies')
		triggeringPolicy.addComponent(sizeBasedTriggeringPolicy)
		
		AppenderComponentBuilder appenderBuilder = builder.newAppender('LogToRollingFile', 'RollingFile')
		appenderBuilder.addAttribute('fileName', 'app.log')
		appenderBuilder.addAttribute('filePattern', 'app.log'+'-%d{MM-dd-yy-HH-mm-ss}.log.')
		appenderBuilder.add(layoutBuilder)
		appenderBuilder.addComponent(triggeringPolicy)
		
		AppenderRefComponentBuilder appenderRefBuilder = builder.newAppenderRef('LogToRollingFile')
		rootLoggerBuilder.add(appenderRefBuilder)

		builder.add(appenderBuilder)
		builder.add(rootLoggerBuilder)
		
		builder.writeXmlConfiguration(System.out)
		println ''
		
		Configurator.reconfigure(builder.build())
	}

	static void reconfigureLog4j2v3() {
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder()
		builder.setStatusLevel(Level.WARN)
		builder.setConfigurationName('DefaultLogger')

		// create the rootLogger
		RootLoggerComponentBuilder rootLoggerBuilder = builder.newRootLogger(Level.DEBUG)
		
		// create a Layout with a pattern
		LayoutComponentBuilder layoutBuilder = builder.newLayout('PatternLayout')
		layoutBuilder.addAttribute('pattern', '%d %p %c [%t] %m%n')

		// create a console appender
		AppenderComponentBuilder appenderBuilder = builder.newAppender('Console', 'CONSOLE')
		appenderBuilder.addAttribute('target', ConsoleAppender.Target.SYSTEM_OUT)
		appenderBuilder.add(layoutBuilder)
		
		// create appenderRef for 'Console'
		AppenderRefComponentBuilder appenderRefBuilder = builder.newAppenderRef('Console')
		rootLoggerBuilder.add(appenderRefBuilder)
		
		LoggerComponentBuilder logger = builder.newLogger("com.wadhams.log4j.service", Level.WARN)
		logger.add(appenderRefBuilder)
		logger.addAttribute("additivity", false)
		
		builder.add(logger)
		builder.add(appenderBuilder)
		builder.add(rootLoggerBuilder)
		
		builder.writeXmlConfiguration(System.out)
		println ''
		
		Configurator.reconfigure(builder.build())
	}

	def execute() {
		Logger logger = LogManager.getLogger('com.wadhams.log4j.app')

		println ''
		println "logger.getName()...............: ${logger.getName()}"
		println "logger.getParent().getName()...: ${logger.getParent().getName() ?: 'blank name = ROOT Logger'}"
		println "logger.getLevel()..............: ${logger.getLevel()}"
		println ''

		logger.fatal('execute()...fatal...')
		logger.error('execute()...error...')
		logger.warn('execute()...warn...')
		logger.info('execute()...info...')
		logger.debug('execute()...debug...')
		logger.trace('execute()...trace...')
		println ''
		
		service.calculate()
	}

}
