package com.newt.JacocoMutationDemo.logger;

import com.newt.JacocoMutationDemo.constants.ApplicationConstants;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jWrapper implements ApplicationConstants {
	
	public static final org.apache.log4j.Logger logger = Logger.getLogger(Log4jWrapper.class);
	
	public Log4jWrapper(){
		initializeLogger();
	}
	
	public static void initializeLogger(){		
		PropertyConfigurator.configure(LOG_FILENAME);
		logger.info("Log4jWrapper Logging initialized");
	}

}
