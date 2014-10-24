package org.sitenv.services.ccda.web.controller;


import javax.annotation.Resource;

import org.sitenv.services.ccda.data.ValidationData;
import org.sitenv.services.ccda.data.ValidationDataImpl;
import org.sitenv.services.ccda.service.CCDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="", produces=MediaType.TEXT_XML_VALUE)
public class CCDAValidationController 
{	

	@Autowired
	@Resource(name="CCDA1_1")
	private CCDAService ccda1_1service;

	// Constructor
	public CCDAValidationController(){}
	
	@RequestMapping(value="/CCDAv1_1/", headers = "content-type=multipart/*", method= RequestMethod.POST, produces="application/json; charset=utf-8")
	public String validate(@RequestParam(value="type_val", required=false) String type_val, @RequestParam(value="file", required=false) MultipartFile file)  
	{
		ValidationData data = new ValidationDataImpl();
		data.addParameter("type_val", type_val);
		data.addFile("file", file);
		return ccda1_1service.validate(data);
	}
	
}
