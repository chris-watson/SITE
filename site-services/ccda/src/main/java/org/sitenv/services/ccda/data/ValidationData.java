package org.sitenv.services.ccda.data;


import org.springframework.web.multipart.MultipartFile;



public interface ValidationData {
	
	public void addParameter(String identifier, String parameter);
	public String getParameter(String identifier);
	
	public void addFile(String identifier, MultipartFile file);
	public MultipartFile getFile(String identifier);

}
