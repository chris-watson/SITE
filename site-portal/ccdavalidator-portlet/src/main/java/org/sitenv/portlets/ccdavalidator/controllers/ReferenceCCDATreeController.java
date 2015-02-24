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
public class ReferenceCCDATreeController extends TreeController {
	
	private static Logger _log = Logger.getLogger(ReferenceCCDATreeController.class);
	
	@Override
	public String getControllerName() {
		return "RefCCDA";
	}
	
	
	@ActionMapping(params = "javax.portlet.action=referenceCCDATree")
	public synchronized void CCDASampleResponse(ActionRequest request, ActionResponse response)
			throws IOException {
		if (this.props == null) {
			this.loadProperties();
		}
		
		String sampleDir = props.getProperty("ReferenceDownloadFiles");
		this.traverseDir(sampleDir);
		response.setRenderParameter("javax.portlet.action", "referenceCCDATree");
	}
	

	@RequestMapping(params = "javax.portlet.action=referenceCCDATree")
	public ModelAndView processReferenceDownload(RenderRequest request, Model model)
			throws IOException {
		
		Map map = new HashMap();
		Gson gson = new Gson();
		String json = gson.toJson(this.getTree().getRoots());
		map.put("jsonRoot", json);
		
		return new ModelAndView("referenceCCDATreeJsonView", map);
	}
	
	
}