package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.portlets.ccdavalidator.JSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.multipart.MultipartActionRequest;

@Controller
@RequestMapping("VIEW")
public class CCDAValidatorController extends BaseController {

	
	@Autowired
	private JSONResponse responseJSON;
	
	@Autowired
	private StatisticsManager statisticsManager;

	@ActionMapping(params = "javax.portlet.action=uploadCCDA1.1")
	public void responseCCDA1_1(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		String ccda_type_value = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		// handle the files:
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDA1.1");
		MultipartFile file = request.getFile("file");
		
		responseJSON.setFileJson(new JSONArray());
		
		try {

				JSONObject jsono = new JSONObject();
				jsono.put("name", file.getOriginalFilename());
				jsono.put("size", file.getSize());
				
				responseJSON.getFileJson().put(jsono);
				
				ccda_type_value = request.getParameter("ccda_type_val");
				
				
				if(ccda_type_value == null)
				{
					ccda_type_value = "";
				}
				
				HttpClient client = new DefaultHttpClient();
				
				String ccdaURL = this.props.getProperty("CCDAValidationServiceURL");
				ccdaURL += "/r1.1/";
				
				HttpPost post = new HttpPost(ccdaURL);

				MultipartEntity entity = new MultipartEntity();
				// set the file content
				entity.addPart("file", new InputStreamBody(file.getInputStream() , file.getOriginalFilename()));
				
				// set the CCDA type
				entity.addPart("type_val",new StringBody(ccda_type_value));
				
				post.setEntity(entity);
				
				HttpResponse relayResponse = client.execute(post);
				
				//create the handler
				ResponseHandler<String> handler = new BasicResponseHandler();
				
				int code = relayResponse.getStatusLine().getStatusCode();
				
				if(code != HttpStatus.SC_OK) 
				{
					//do the error handling.
					statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true, "r1.1");
				}
				else
				{
					boolean ccdaHasErrors = true, ccdaHasWarnings = true, ccdaHasInfo = true;
					boolean extendedCcdaHasErrors = true, extendedCcdaHasWarnings = true, extendedCcdaHasInfo = true;
					
					
					String json = handler.handleResponse(relayResponse);
					JSONObject jsonbody = new JSONObject(json);
					
					if (jsonbody.getJSONObject("ccdaResults").has("error") || 
							jsonbody.getJSONObject("ccdaExtendedResults").has("error")){
						//TODO: Make sure the UI handles this gracefully.
						responseJSON.setJSONResponseBody(jsonbody);
						statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, false, "r1.1");
					} else {
						
						JSONObject ccdaReport = jsonbody.getJSONObject("ccdaResults").getJSONObject("report");
						ccdaHasErrors = ccdaReport.getBoolean("hasErrors");
						ccdaHasWarnings = ccdaReport.getBoolean("hasWarnings");
						ccdaHasInfo = ccdaReport.getBoolean("hasInfo");
						
						JSONObject extendedCcdaReport = jsonbody.getJSONObject("ccdaExtendedResults").getJSONObject("report");
						extendedCcdaHasErrors = extendedCcdaReport.getBoolean("hasErrors");
						extendedCcdaHasWarnings = extendedCcdaReport.getBoolean("hasWarnings");
						extendedCcdaHasInfo = extendedCcdaReport.getBoolean("hasInfo");
						
						boolean hasErrors = (ccdaHasErrors || extendedCcdaHasErrors);
						boolean hasWarnings = (ccdaHasWarnings || extendedCcdaHasWarnings);
						boolean hasInfo = (ccdaHasInfo || extendedCcdaHasInfo);
						
						
						responseJSON.setJSONResponseBody(jsonbody);
						statisticsManager.addCcdaValidation(ccda_type_value, hasErrors, hasWarnings, hasInfo, false, "r1.1");
					}
				}				
				
		} catch (Exception e) {
			statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true, "r1.1");
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
	
	
	@ActionMapping(params = "javax.portlet.action=uploadCCDA2.0")
	public void responseCCDA2_0(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDA2.0");
		MultipartFile file = request.getFile("file");
		
		responseJSON.setFileJson(new JSONArray());
		
		try {

				JSONObject jsono = new JSONObject();
				jsono.put("name", file.getOriginalFilename());
				jsono.put("size", file.getSize());
				
				responseJSON.getFileJson().put(jsono);
				
				
				
				HttpClient client = new DefaultHttpClient();
				
				String ccdaURL = this.props.getProperty("CCDAValidationServiceURL");
				ccdaURL += "/r2.0/";
				
				HttpPost post = new HttpPost(ccdaURL);
				
				MultipartEntity entity = new MultipartEntity();
				// set the file content
				entity.addPart("file", new InputStreamBody(file.getInputStream() , file.getOriginalFilename()));
				
				// set the CCDA type
				post.setEntity(entity);
				
				HttpResponse relayResponse = client.execute(post);
				//create the handler
				ResponseHandler<String> handler = new BasicResponseHandler();
				
				int code = relayResponse.getStatusLine().getStatusCode();
				
				
				if(code != HttpStatus.SC_OK) 
				{
					//do the error handling.
					statisticsManager.addCcdaValidation("NonSpecificCCDAR2", false, false, false, true, "R2.0");
				}
				else
				{
					boolean ccdaHasErrors = true, ccdaHasWarnings = true, ccdaHasInfo = true;
					boolean extendedCcdaHasErrors = true, extendedCcdaHasWarnings = true, extendedCcdaHasInfo = true;
					
					
					String json = handler.handleResponse(relayResponse);
					JSONObject jsonbody = new JSONObject(json);
					
					if (jsonbody.getJSONObject("ccdaResults").has("error") || 
							jsonbody.getJSONObject("ccdaExtendedResults").has("error")){
						//TODO: Make sure the UI handles this gracefully.
						responseJSON.setJSONResponseBody(jsonbody);
						statisticsManager.addCcdaValidation("NonSpecificCCDAR2", false, false, false, false, "R2.0");
					} else {
						
						JSONObject ccdaReport = jsonbody.getJSONObject("ccdaResults").getJSONObject("report");
						ccdaHasErrors = ccdaReport.getBoolean("hasErrors");
						ccdaHasWarnings = ccdaReport.getBoolean("hasWarnings");
						ccdaHasInfo = ccdaReport.getBoolean("hasInfo");
						
						JSONObject extendedCcdaReport = jsonbody.getJSONObject("ccdaExtendedResults").getJSONObject("report");
						extendedCcdaHasErrors = extendedCcdaReport.getBoolean("hasErrors");
						extendedCcdaHasWarnings = extendedCcdaReport.getBoolean("hasWarnings");
						extendedCcdaHasInfo = extendedCcdaReport.getBoolean("hasInfo");
						
						boolean hasErrors = (ccdaHasErrors || extendedCcdaHasErrors);
						boolean hasWarnings = (ccdaHasWarnings || extendedCcdaHasWarnings);
						boolean hasInfo = (ccdaHasInfo || extendedCcdaHasInfo);
						
						
						responseJSON.setJSONResponseBody(jsonbody);
						statisticsManager.addCcdaValidation("NonSpecificCCDAR2", hasErrors, hasWarnings, hasInfo, false, "R2.0");
					}
				}
				
		} catch (JSONException e) {
			statisticsManager.addCcdaValidation("NonSpecificCCDAR2", false, false, false, true, "R2.0");
			throw new RuntimeException(e);
		}
	}
	
	

	@ActionMapping(params = "javax.portlet.action=uploadCCDAReconciled")
	public void responseCCDAReconciled(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		String ccda_type_value = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDAReconciled");
		MultipartFile file = request.getFile("file");
		MultipartFile CEHRTFile = request.getFile("CEHRTFile");
		MultipartFile reconciliationFile = request.getFile("ReconciliationFile");
		
		
		try {
			responseJSON.setFileJson(new JSONArray("[]"));
			responseJSON.setJSONResponseBody(new JSONObject("{}"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	

	@ActionMapping(params = "javax.portlet.action=uploadCCDAReference")
	public void responseCCDAReference(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		String ccda_type_value = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		// handle the files:
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDAReference");
		MultipartFile file = request.getFile("file");
		MultipartFile CEHRTFile = request.getFile("CEHRTFile");
		
		
		try {
			responseJSON.setFileJson(new JSONArray("[]"));
			responseJSON.setJSONResponseBody(new JSONObject("{}"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	

	@ActionMapping(params = "javax.portlet.action=uploadCCDASuper")
	public void responseCCDASuper(MultipartActionRequest request, ActionResponse response) throws IOException {
		
		String ccda_type_value = null;
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		// handle the files:
		
		response.setRenderParameter("javax.portlet.action", "uploadCCDA1.1");
		MultipartFile file = request.getFile("file");
		
		responseJSON.setFileJson(new JSONArray());
		
		try {

				JSONObject jsono = new JSONObject();
				jsono.put("name", file.getOriginalFilename());
				jsono.put("size", file.getSize());
				
				responseJSON.getFileJson().put(jsono);
				
				ccda_type_value = request.getParameter("ccda_type_val");
				
				
				if(ccda_type_value == null)
				{
					ccda_type_value = "";
				}
				
				HttpClient client = new DefaultHttpClient();
				
				String ccdaURL = this.props.getProperty("CCDAValidationServiceURL");
				ccdaURL += "/r1.1/";
				
				HttpPost post = new HttpPost(ccdaURL);

				MultipartEntity entity = new MultipartEntity();
				// set the file content
				entity.addPart("file", new InputStreamBody(file.getInputStream() , file.getOriginalFilename()));
				// set the CCDA type
				
				entity.addPart("type_val",new StringBody(ccda_type_value));
				
				post.setEntity(entity);
				
				HttpResponse relayResponse = client.execute(post);
				
				//create the handler
				ResponseHandler<String> handler = new BasicResponseHandler();
				
				int code = relayResponse.getStatusLine().getStatusCode();
				
				if(code != HttpStatus.SC_OK) 
				{
					//do the error handling.
					statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true, "Super");
				}
				else
				{
					boolean hasErrors = true, hasWarnings = true, hasInfo = true;
					
					String json = handler.handleResponse(relayResponse);
					JSONObject jsonbody = new JSONObject(json);
					
					JSONObject report = jsonbody.getJSONObject("report");
					hasErrors = report.getBoolean("hasErrors");
					hasWarnings = report.getBoolean("hasWarnings");
					hasInfo = report.getBoolean("hasInfo");
					
					responseJSON.setJSONResponseBody(jsonbody);
					
					statisticsManager.addCcdaValidation(ccda_type_value, hasErrors, hasWarnings, hasInfo, false, "Super");
					
				}
				

		} catch (Exception e) {
			statisticsManager.addCcdaValidation(ccda_type_value, false, false, false, true, "Super");
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} 
		
	}
	
	
	private Map getResultMap(){
		Map map = new HashMap();
		map.put("files", responseJSON.getFileJson());
		map.put("body", responseJSON.getJSONResponseBody());
		return map;
		
	}
	
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDA1.1")
	public ModelAndView processCCDA1_1(RenderRequest request, Model model)
			throws IOException {
		return new ModelAndView("cCDAValidatorJsonView", getResultMap());
	}
	
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDA2.0")
	public ModelAndView processCCDA2_0(RenderRequest request, Model model)
			throws IOException {
		return new ModelAndView("cCDAValidatorJsonView", getResultMap());
	}
	
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDAReconciled")
	public ModelAndView processCCDAReconciled(RenderRequest request, Model model)
			throws IOException {
		return new ModelAndView("cCDAValidatorJsonView", getResultMap());
	}
	
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDAReference")
	public ModelAndView processCCDAReference(RenderRequest request, Model model)
			throws IOException {
		return new ModelAndView("cCDAValidatorJsonView", getResultMap());
	}
	
	
	@RequestMapping(params = "javax.portlet.action=uploadCCDASuper")
	public ModelAndView processCCDASuper(RenderRequest request, Model model)
			throws IOException {
		return new ModelAndView("cCDAValidatorJsonView", getResultMap());
	}
	
	
	@RenderMapping()
	public ModelAndView handleRenderRequest(RenderRequest request,
			RenderResponse response) throws IOException {

		
		if (this.props == null)
		{
				this.loadProperties();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("view");
		
		modelAndView.addObject("showVocabulary", this.props.getProperty("showVocabularyValidation"));
		
		return modelAndView;
	}

	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
	
	public JSONResponse getResponseJSON() {
		return responseJSON;
	}
}
