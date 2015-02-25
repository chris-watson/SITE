package org.sitenv.services.ccda.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.sitenv.services.ccda.data.ValidationData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;





@Service(value="CCDA2_0")
public class CCDAService2_0 extends VocabularyCCDAService {
	
	Logger logger = LogManager.getLogger(CCDAService2_0.class.getName());	
    
	
	public CCDAService2_0() throws IOException {}
	
	
	@Override
	public String getValidatorID() {
		return "2.0";
	}
	
	
	@Override
	public String validate(ValidationData validationData) {
		
		Date requestStart = new Date();
		JSONObject json = new JSONObject();
		JSONObject ccdaJSON = null;
		JSONObject extendedCcdaJSON = null;
		
		
	    try {
			
	    	if (this.props == null)
			{
				this.loadProperties();
			}
			
	    } catch (IOException e) {
				
	    	logger.error("Error while accessing CCDA service: ",  e);
			try {
				json = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
		}
	    
	    try {
	    	
	    	ccdaJSON = getCCDAResult(validationData);
	    	
	    } catch (JSONException e) {
	    	
	    	logger.error("Error while accessing CCDA validation service: ",  e);
	    	try {
	    		ccdaJSON = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
	    	
	    } catch (IOException e) {
	    	
	    	logger.error("Error while accessing CCDA validation service: ",  e);
	    	try {
	    		ccdaJSON = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
	    }
	    
	    
	    try {
	    	
	    	extendedCcdaJSON = getVocabularyResult(validationData);
			
	    } catch (JSONException e) {
	    	
	    	logger.error("Error while accessing extended CCDA validation service: ",  e);
	    	try {
	    		
	    		String errormsg = "{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}";
	    		
	    		extendedCcdaJSON = new JSONObject(errormsg);
				
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
	    	
	    } catch (IOException e) {
	    	
	    	logger.error("Error while accessing extended CCDA validation service: ",  e);
	    	try {
	    		extendedCcdaJSON = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
			} catch (JSONException e1) {
				logger.error("Error while creating error JSON output: ",  e1);
			}
	    }
	    
	    
	    try {
			JSONObject statsError = writeCcdaAndVocabStatistics(ccdaJSON, extendedCcdaJSON, validationData.getParameter("type_val"));
			
			if (statsError != null){
				json = statsError;
			}
			
		} catch (JSONException e) {
			logger.error("Error recording statistics: ",  e);
		}
	    
	    
	    if ((ccdaJSON != null) && (extendedCcdaJSON != null)){
	    	
			try {
				if (!json.has("error")){
					json.put("ccdaResults", ccdaJSON);
					json.put("ccdaExtendedResults", extendedCcdaJSON);
				}
				
			} catch (JSONException e) {
		    	logger.error("Error while accessing CCDA service: ",  e);
		    	try {
					json = new JSONObject("{ \"error\" : {\"message\":"+"\""+e.getMessage()+"\""+"}}");
				} catch (JSONException e1) {
					logger.error("Error while creating error JSON output: ",  e1);
				}
			}
	    }
		
		Date requestFinish = new Date();
		logSuccessOrFailure(json, requestStart ,requestFinish);
		return json.toString();
	}
	
	
	private JSONObject getCCDAResult(ValidationData validationData) throws IOException, JSONException
	{
		JSONObject json = null;
		String ccda_type_value = "NonSpecificCCDAR2";
		ccda_type_value = validationData.getParameter("type_val");
		
		
		
		MultipartFile file = validationData.getFile("file");
		
		String ccdaR2URL = null;
		
		ccdaR2URL = this.props.getProperty("CCDAv2ValidationServiceURL");
			
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(ccdaR2URL);
			
		MultipartEntity entity = new MultipartEntity();
		
		// set the file content
		entity.addPart("file", new InputStreamBody(file.getInputStream(), 
				file.getName()));
			
		// Do we still need to set CCDA Type?
		entity.addPart("ccda_type",new StringBody(ccda_type_value));
		entity.addPart("return_json_param", new StringBody("true"));
		entity.addPart("debug_mode", new StringBody("true"));
			
		post.setEntity(entity);
		HttpResponse relayResponse = client.execute(post);
		json = handleCCDAResponse(relayResponse, ccda_type_value);
		
		return json;
	}
	
	
	private JSONObject handleCCDAResponse(HttpResponse relayResponse, 
			String ccda_type_value) throws ClientProtocolException, 
			IOException, JSONException{
		
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			int code = relayResponse.getStatusLine().getStatusCode();
			
			JSONObject jsonbody = null;
			String errorMessage = null;
			
			if(code != HttpStatus.SC_OK)
			{
				
				//do the error handling.
				logger.log(Level.ERROR, "Error while accessing CCDA service: "
				+ code + ": "
				+ relayResponse.getStatusLine().getReasonPhrase());
				
				jsonbody = new JSONObject("{ \"error\" : {\"message\": Error while accessing CCDA service - "
				+"\""+code +"-"+relayResponse.getStatusLine().getReasonPhrase() +"\""+"}}");
				
			}
			else
			{
				
				org.apache.http.Header[] errorHeaders = relayResponse.getHeaders("error_message");
				
		    	if (errorHeaders.length > 0){
		    		org.apache.http.Header errorHeader = errorHeaders[0];
		    		errorMessage = errorHeader.getValue();
		    	}
		    	
				
		    	org.apache.http.Header[] timeAndDateHeaders = relayResponse.getHeaders("response_time_and_date");
		    	
		    	String timeAndDate = "";
		    	
		    	if (timeAndDateHeaders.length > 0){
		    		org.apache.http.Header timeAndDateHeader = timeAndDateHeaders[0];
		    		timeAndDate = timeAndDateHeader.getValue();
		    	}
		    	
		    	
		    	org.apache.http.Header[] processingTimeHeaders = relayResponse.getHeaders("round_trip_response_time");
		    	String processingTime = "";
		    	
		    	if (timeAndDateHeaders.length > 0){
		    		org.apache.http.Header processingTimeHeader = processingTimeHeaders[0];
		    		processingTime = processingTimeHeader.getValue();
		    	}
		    	
		    	JSONObject performance_object = new JSONObject().put("dateTimeOfRequest", timeAndDate);
		    	performance_object.put("processingTime", processingTime);
				
				String body = handler.handleResponse(relayResponse);
				Document doc = Jsoup.parseBodyFragment(body);
				org.jsoup.nodes.Element json = doc.select("pre").first();
				
				if (json == null){
					
					if (errorMessage != null){
						
						jsonbody = new JSONObject("{ \"error\" : {\"message\": "+errorMessage+"\""+"}}");
					} else {
						
						jsonbody = new JSONObject("{ \"error\" : {\"message\": \"The web service has encountered an unknown error. Please try again. If this issue persists, please contact the SITE team."+"\""+"}}");
					}
					
				} else {
					
					jsonbody = new JSONObject(json.text());
					
					
					// Get rid of message objects in the case when there is no error found
					if (jsonbody.has("errors")){
						JSONArray errors = jsonbody.getJSONArray("errors");
						JSONObject firstElement = errors.getJSONObject(0);
						if (firstElement.has("message")){
							String message = firstElement.getString("message");
							if (message.equals("no_data_found")){
								ArrayList emptyList = new ArrayList();
								jsonbody.remove("errors");
								jsonbody.put("errors", emptyList);//.put("errors", []);
							}
						}
					}
					
					
					if (jsonbody.has("warnings")){
						JSONArray errors = jsonbody.getJSONArray("warnings");
						JSONObject firstElement = errors.getJSONObject(0);
						if (firstElement.has("message")){
							String message = firstElement.getString("message");
							if (message.equals("no_data_found")){
								ArrayList emptyList = new ArrayList();
								jsonbody.remove("warnings");
								jsonbody.put("warnings", emptyList);//.put("errors", []);
							}
						}
					}
					
					
					if (jsonbody.has("info")){
						JSONArray errors = jsonbody.getJSONArray("info");
						JSONObject firstElement = errors.getJSONObject(0);
						if (firstElement.has("message")){
							String message = firstElement.getString("message");
							if (message.equals("no_data_found")){
								ArrayList emptyList = new ArrayList();
								jsonbody.remove("info");
								jsonbody.put("info", emptyList);//.put("errors", []);
							}
						}
					}
					
					jsonbody.put("performance", performance_object);
				}
			}
			return jsonbody;
		}
}

	