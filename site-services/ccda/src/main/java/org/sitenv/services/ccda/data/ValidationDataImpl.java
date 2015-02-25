package org.sitenv.services.ccda.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ValidationDataImpl implements ValidationData {

	
	Map<String, String> parameters = new HashMap<String, String>();
	Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
	
	
	public ValidationDataImpl() {}

	@Override
	public void addParameter(String identifier, String parameter) {
		parameters.put(identifier, parameter);
	}

	@Override
	public String getParameter(String identifier) {
		return parameters.get(identifier);
	}

	@Override
	public void addFile(String identifier, MultipartFile file) {
		files.put(identifier, file);
	}

	@Override
	public MultipartFile getFile(String identifier) {
		return files.get(identifier);
	}

}
