package org.sitenv.services.ccda.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.sitenv.services.ccda.service.CCDAService;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.services.ccda.data.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseCCDAService implements CCDAService {

	public static final String DEFAULT_PROPERTIES_FILE = "environment.properties";
	
	protected Properties props;
	
	@Autowired
	private StatisticsManager statisticsManager;
	
	public BaseCCDAService() throws IOException {
        loadProperties();
    }
	
	public abstract String getValidatorID();
	public abstract String validate(ValidationData data);

	protected void loadProperties() throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE);
		
		if (in == null)
		{
			props = null;
			throw new FileNotFoundException("Environment Properties File not found in class path.");
		}
		else
		{
			props = new Properties();
			props.load(in);
		}
	}

	@Override
	public Properties getProperties() {
		return props;
	}

	@Override
	public void setProperties(Properties properties) {
		this.props = properties;
	}
	
	
	protected StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	protected void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
    
	public void recordStatistics(String testType, Boolean hasErrors, Boolean hasWarnings, Boolean hasInfo, Boolean hasHttpError){
		getStatisticsManager().addCcdaServiceCall(testType, hasErrors, hasWarnings, hasInfo, hasHttpError, getValidatorID());
	}

}
