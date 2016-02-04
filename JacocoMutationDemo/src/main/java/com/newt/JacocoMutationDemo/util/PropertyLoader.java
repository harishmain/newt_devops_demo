package com.newt.JacocoMutationDemo.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.newt.JacocoMutationDemo.constants.ApplicationConstants;
import com.newt.JacocoMutationDemo.dao.AccountSummaryDaoImpl;
import com.newt.JacocoMutationDemo.logger.Log4jWrapper;

/*
* CODE CHANGES HISTORY
* ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* 	DATE		AUTHOR				METHODS MODIFIED/ADDED				CODE CHANGES DESCRIPTION
* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*  12/09/2015  Harish Main      										Class initial creation. 	
*  
*/
/**
 * Class that loads Properties from properties files
 * @date 12/09/2015
 * @author Harish Main
 */
public class PropertyLoader implements ApplicationConstants {
	


	private final static Logger logger = Logger.getLogger(AccountSummaryDaoImpl.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	
	
	
	/**
	 * @date 12/09/2015
	 * @author Harish Main
	 * @description Method used to read the environment property file and get property value for property key passed
	 * @param String as propertyKey for which needs to return property value
	 * @return propertyValues as String
	 */
	public String getPropertyValue(String propertyKey){
		
		
		logger.debug("Inside  PropertyLoader :: getPropertyValue, invoked...");
		String propertyValue=null;
		
		try {
			
				InputStream input =  null;
				
				Properties prop = new Properties();
				input = new FileInputStream(ENVIRONMENT_FILENAME);
				
				prop.load(input);					
				propertyValue=prop.getProperty(propertyKey);
				logger.debug("PropertyLoader :: getPropertyValue, propertyKey:"+propertyKey+", propertyValue:"+propertyValue);

		    }catch (IOException e) {
		    	logger.error("Inside  PropertyLoader :: getPropertyValue, Exception:"+e);
		   }

		return propertyValue;
		
	}	
	
	
}
