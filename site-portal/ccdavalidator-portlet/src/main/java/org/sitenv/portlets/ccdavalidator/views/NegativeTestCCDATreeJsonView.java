package org.sitenv.portlets.ccdavalidator.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("NegativeTestCCDATreeJsonView")
public class NegativeTestCCDATreeJsonView extends AbstractView{

	private Logger logger = Logger.getLogger(NegativeTestCCDATreeJsonView.class);
	
	public NegativeTestCCDATreeJsonView() {
		super();
		
		setContentType("text/plain");
	}
	
	@Override
    protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    logger.info("Resolving ajax request view - " + map);
    
    logger.info("content Type = " + getContentType());
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(map.get("jsonRoot").toString());
    response.getWriter().flush();
    }
	
}
