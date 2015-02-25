package org.sitenv.portlets.directtransport;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("DirectReceiveService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class DirectReceiveResultsImpl implements DirectReceiveResults {

	
	private JSONArray fileJson = null;
	private JSONObject uploadResult = null;
	private JSONObject precannedResult = null;
	
	public DirectReceiveResultsImpl(){}

	public JSONArray getFileJson() {
		return fileJson;
	}

	public void setFileJson(JSONArray fileJson) {
		this.fileJson = fileJson;
	}

	public JSONObject getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(JSONObject uploadResult) {
		this.uploadResult = uploadResult;
	}

	public JSONObject getPrecannedResult() {
		return precannedResult;
	}

	public void setPrecannedResult(JSONObject precannedResult) {
		this.precannedResult = precannedResult;
	}

}
