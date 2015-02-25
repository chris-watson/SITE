package org.sitenv.portlets.directtransport;

import org.json.JSONArray;
import org.json.JSONObject;

public interface TrustBundle {

	public JSONArray getFileJson();
	public void setFileJson(JSONArray fileJson);
	public JSONObject getResult();
	public void setResult(JSONObject result);

}

