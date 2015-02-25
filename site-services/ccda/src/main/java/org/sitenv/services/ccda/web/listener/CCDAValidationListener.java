package org.sitenv.services.ccda.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class CCDAValidationListener implements ServletContextListener 
{
	private static Logger logger = Logger.getLogger(CCDAValidationListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		logger.debug("CCDA Validation Engine stopped...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		logger.debug("CCDA Validation Engine initialized...");
	}
}
