package org.sitenv.portlets.directtransport;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DirectReceiveResults {

	public JSONArray getFileJson();
	public void setFileJson(JSONArray fileJson);
	public JSONObject getUploadResult();
	public void setUploadResult(JSONObject uploadResult);
	public JSONObject getPrecannedResult();
	public void setPrecannedResult(JSONObject precannedResult);

}
