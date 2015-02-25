package org.sitenv.portlets.directtransport;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("TrustBundleService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class TrustBundleImpl implements TrustBundle {

	private JSONArray fileJson = null;
	private JSONObject result = null;

	public TrustBundleImpl() {}
	
	public JSONArray getFileJson() {
		return fileJson;
	}

	public void setFileJson(JSONArray fileJson) {
		this.fileJson = fileJson;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
