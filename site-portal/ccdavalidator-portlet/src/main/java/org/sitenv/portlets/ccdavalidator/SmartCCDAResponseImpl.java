package org.sitenv.portlets.ccdavalidator;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;



@Service("SmartCCDAResponseService")
@Scope(value="request",proxyMode= ScopedProxyMode.INTERFACES)
public class SmartCCDAResponseImpl implements SmartCCDAResponse {

	private String smartCcdaResponse = null;
	private String smartCcdaRubricResponse = null;
	
	public SmartCCDAResponseImpl() {}

	public String getSmartCcdaRubricResponse() {
		return smartCcdaRubricResponse;
	}

	public void setSmartCcdaRubricResponse(String smartCcdaRubricResponse) {
		this.smartCcdaRubricResponse = smartCcdaRubricResponse;
	}

	public String getSmartCcdaResponse() {
		return smartCcdaResponse;
	}

	public void setSmartCcdaResponse(String smartCcdaResponse) {
		this.smartCcdaResponse = smartCcdaResponse;
	}
}
