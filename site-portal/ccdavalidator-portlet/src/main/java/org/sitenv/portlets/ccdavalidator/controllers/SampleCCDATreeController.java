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
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.portlets.ccdavalidator.models.SampleCCDATreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.google.gson.Gson;


@Controller
@RequestMapping("VIEW")
public class SampleCCDATreeController extends TreeController {
	
	private static Logger _log = Logger.getLogger(SampleCCDATreeController.class);
	
	@Override
	public String getControllerName() {
		return "SampleCCDATree";
	}
	
	@ActionMapping(params = "javax.portlet.action=sampleCCDATree")
	public synchronized void CCDASampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		if (this.props == null) {
			this.loadProperties();
		}
		String CCDASampleDir = props.getProperty("samplesFromVendorsForIncorporation");
		this.traverseDir(CCDASampleDir);
		response.setRenderParameter("javax.portlet.action", "sampleCCDATree");
	}
	
	
	@RequestMapping(params = "javax.portlet.action=sampleCCDATree")
	public ModelAndView processVendorCCDA(RenderRequest request, Model model)
			throws IOException {
		Map map = new HashMap();
		
		Gson gson = new Gson();
		String json = gson.toJson(this.getTree().getRoots());
		map.put("jsonRoot", json);
		
		return new ModelAndView("sampleCCDATreeJsonView", map);
	}

}