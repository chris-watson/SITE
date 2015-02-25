package org.sitenv.services.ccda.service;

import java.util.Properties;

import org.sitenv.services.ccda.data.ValidationData;
import org.springframework.web.multipart.MultipartFile;

public interface CCDAService 
{
	/*
	 * 	Service call
	 */
	public String validate(ValidationData data);
	
	/*
	 * 	properties
	 */
	public Properties getProperties();
	public void setProperties(Properties properties);
}
