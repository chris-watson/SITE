package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.google.gson.Gson;

@Controller
@RequestMapping("VIEW")
public class NegativeTestCCDAController extends TreeController{
	
	private static Logger _log = Logger.getLogger(NegativeTestCCDAController.class);
	
	@Override
	public String getControllerName() {
		return "NegTestCCDATree";
	}
	
	@ActionMapping(params = "javax.portlet.action=negativeTestCCDATree")
	public synchronized void CCDASampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		if (this.props == null) {
			this.loadProperties();
		}
		String CCDANegTestDir = props.getProperty("CcdasForNegativeTesting");
		this.traverseDir(CCDANegTestDir);
		response.setRenderParameter("javax.portlet.action", "negativeTestCCDATree");
	}
	
	@RequestMapping(params = "javax.portlet.action=negativeTestCCDATree")
	public ModelAndView processReferenceDownload(RenderRequest request, Model model)
			throws IOException {
		
		Map map = new HashMap();
		Gson gson = new Gson();
		String json = gson.toJson(this.getTree().getRoots());
		map.put("jsonRoot", json);
		
		return new ModelAndView("NegativeTestCCDATreeJsonView", map);
	}

}
