package org.sitenv.portlets.statistics.controllers;


import java.io.IOException;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.sitenv.common.utilities.controller.BaseController;
import org.sitenv.statistics.constants.StatisticsConstants;
import org.sitenv.statistics.dto.CcdaWeeklyCounts;
import org.sitenv.statistics.manager.StatisticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;



@Controller("statisticsController")

@RequestMapping("VIEW")
public class StatisticsController extends BaseController {
	
	
	private static final Logger logger = Logger.getLogger(StatisticsController.class);

	@Autowired
	private StatisticsManager statisticsManager;
	
	@RenderMapping()
	public ModelAndView handleRenderRequest(RenderRequest request,
			RenderResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		
		PortletPreferences prefs = request.getPreferences();
		String page = prefs.getValue("viewPage", "all-stats");
		
		
		if (page.equals("ccda-stats"))
		{	
			modelAndView.setViewName("ccda");
		}
		else if (page.equals("qrda-stats"))
		{
			modelAndView.setViewName("qrda");
		}
		else if (page.equals("pdti-stats"))
		{
			modelAndView.setViewName("pdti");
		}
		else if (page.equals("direct-stats"))
		{
			modelAndView.setViewName("direct");
		}
		else
		{
			/*
				C-CDA Statistics
			*/
			modelAndView.addObject("totalCcdas", statisticsManager.getTotalCcdaValidationCount(null));
			modelAndView.addObject("totalCcdas30", statisticsManager.getTotalCcdaValidationCount(30));
			modelAndView.addObject("totalCcdas60", statisticsManager.getTotalCcdaValidationCount(60));
			modelAndView.addObject("totalCcdas90", statisticsManager.getTotalCcdaValidationCount(90));
			
			modelAndView.addObject("successfulCcdas", statisticsManager.getSuccessfulCcdaValidationCount(null));
			modelAndView.addObject("successfulCcdas30", statisticsManager.getSuccessfulCcdaValidationCount(30));
			modelAndView.addObject("successfulCcdas60", statisticsManager.getSuccessfulCcdaValidationCount(60));
			modelAndView.addObject("successfulCcdas90", statisticsManager.getSuccessfulCcdaValidationCount(90));
			
			modelAndView.addObject("warningCcda", statisticsManager.getWarningCcdaValidationCount(null));
			modelAndView.addObject("warningCcda30", statisticsManager.getWarningCcdaValidationCount(30));
			modelAndView.addObject("warningCcda60", statisticsManager.getWarningCcdaValidationCount(60));
			modelAndView.addObject("warningCcda90", statisticsManager.getWarningCcdaValidationCount(90));
			
			modelAndView.addObject("infoCcda", statisticsManager.getInfoCcdaValidationCount(null));
			modelAndView.addObject("infoCcda30", statisticsManager.getInfoCcdaValidationCount(30));
			modelAndView.addObject("infoCcda60", statisticsManager.getInfoCcdaValidationCount(60));
			modelAndView.addObject("infoCcda90", statisticsManager.getInfoCcdaValidationCount(90));
			
			modelAndView.addObject("failedCcdas", statisticsManager.getFailedCcdaValidationCount(null));
			modelAndView.addObject("failedCcdas30", statisticsManager.getFailedCcdaValidationCount(30));
			modelAndView.addObject("failedCcdas60", statisticsManager.getFailedCcdaValidationCount(60));
			modelAndView.addObject("failedCcdas90", statisticsManager.getFailedCcdaValidationCount(90));
			
			modelAndView.addObject("ccdaDownloads", statisticsManager.getCcdaDownloadCount(null));
			modelAndView.addObject("ccdaDownloads30", statisticsManager.getCcdaDownloadCount(30));
			modelAndView.addObject("ccdaDownloads60", statisticsManager.getCcdaDownloadCount(60));
			modelAndView.addObject("ccdaDownloads90", statisticsManager.getCcdaDownloadCount(90));
			
			modelAndView.addObject("smartCcdas", statisticsManager.getSmartCcdaCount(null));
			modelAndView.addObject("smartCcdas30", statisticsManager.getSmartCcdaCount(30));
			modelAndView.addObject("smartCcdas60", statisticsManager.getSmartCcdaCount(60));
			modelAndView.addObject("smartCcdas90", statisticsManager.getSmartCcdaCount(90));
			
			/*
			 * End of C-CDA statistics
			 */
			
			/*
			 * QRDA statistics
			 */
			
			modelAndView.addObject("totalQrda", statisticsManager.getTotalQrdaValidationCount(null, null));
			modelAndView.addObject("totalQrda30", statisticsManager.getTotalQrdaValidationCount(null, 30));
			modelAndView.addObject("totalQrda60", statisticsManager.getTotalQrdaValidationCount(null, 60));
			modelAndView.addObject("totalQrda90", statisticsManager.getTotalQrdaValidationCount(null, 90));
		
			modelAndView.addObject("totalCat1Qrda", statisticsManager.getTotalQrdaValidationCount(1, null));
			modelAndView.addObject("totalCat1Qrda30", statisticsManager.getTotalQrdaValidationCount(1, 30));
			modelAndView.addObject("totalCat1Qrda60", statisticsManager.getTotalQrdaValidationCount(1, 60));
			modelAndView.addObject("totalCat1Qrda90", statisticsManager.getTotalQrdaValidationCount(1, 90));
			
			modelAndView.addObject("totalCat3Qrda", statisticsManager.getTotalQrdaValidationCount(3, null));
			modelAndView.addObject("totalCat3Qrda30", statisticsManager.getTotalQrdaValidationCount(3, 30));
			modelAndView.addObject("totalCat3Qrda60", statisticsManager.getTotalQrdaValidationCount(3, 60));
			modelAndView.addObject("totalCat3Qrda90", statisticsManager.getTotalQrdaValidationCount(3, 90));
			
			modelAndView.addObject("successQrda", statisticsManager.getSuccessfulQrdaValidationCount(null, null));
			modelAndView.addObject("successQrda30", statisticsManager.getSuccessfulQrdaValidationCount(null, 30));
			modelAndView.addObject("successQrda60", statisticsManager.getSuccessfulQrdaValidationCount(null, 60));
			modelAndView.addObject("successQrda90", statisticsManager.getSuccessfulQrdaValidationCount(null, 90));
		
			modelAndView.addObject("successCat1Qrda", statisticsManager.getSuccessfulQrdaValidationCount(1, null));
			modelAndView.addObject("successCat1Qrda30", statisticsManager.getSuccessfulQrdaValidationCount(1, 30));
			modelAndView.addObject("successCat1Qrda60", statisticsManager.getSuccessfulQrdaValidationCount(1, 60));
			modelAndView.addObject("successCat1Qrda90", statisticsManager.getSuccessfulQrdaValidationCount(1, 90));
			
			modelAndView.addObject("successCat3Qrda", statisticsManager.getSuccessfulQrdaValidationCount(3, null));
			modelAndView.addObject("successCat3Qrda30", statisticsManager.getSuccessfulQrdaValidationCount(3, 30));
			modelAndView.addObject("successCat3Qrda60", statisticsManager.getSuccessfulQrdaValidationCount(3, 60));
			modelAndView.addObject("successCat3Qrda90", statisticsManager.getSuccessfulQrdaValidationCount(3, 90));
			
			modelAndView.addObject("failedQrda", statisticsManager.getFailedQrdaValidationCount(null, null));
			modelAndView.addObject("failedQrda30", statisticsManager.getFailedQrdaValidationCount(null, 30));
			modelAndView.addObject("failedQrda60", statisticsManager.getFailedQrdaValidationCount(null, 60));
			modelAndView.addObject("failedQrda90", statisticsManager.getFailedQrdaValidationCount(null, 90));
		
			modelAndView.addObject("failedCat1Qrda", statisticsManager.getFailedQrdaValidationCount(1, null));
			modelAndView.addObject("failedCat1Qrda30", statisticsManager.getFailedQrdaValidationCount(1, 30));
			modelAndView.addObject("failedCat1Qrda60", statisticsManager.getFailedQrdaValidationCount(1, 60));
			modelAndView.addObject("failedCat1Qrda90", statisticsManager.getFailedQrdaValidationCount(1, 90));
			
			modelAndView.addObject("failedCat3Qrda", statisticsManager.getFailedQrdaValidationCount(3, null));
			modelAndView.addObject("failedCat3Qrda30", statisticsManager.getFailedQrdaValidationCount(3, 30));
			modelAndView.addObject("failedCat3Qrda60", statisticsManager.getFailedQrdaValidationCount(3, 60));
			modelAndView.addObject("failedCat3Qrda90", statisticsManager.getFailedQrdaValidationCount(3, 90));
			
			/*
			 * End of QRDA statistics
			 */
			
			/*
			 * PDTI statistics
			 */
			
			modelAndView.addObject("totalPdtiCount", statisticsManager.getTotalPdtiTestCount(null));
			modelAndView.addObject("totalPdtiCount30", statisticsManager.getTotalPdtiTestCount(30));
			modelAndView.addObject("totalPdtiCount60", statisticsManager.getTotalPdtiTestCount(60));
			modelAndView.addObject("totalPdtiCount90", statisticsManager.getTotalPdtiTestCount(90));
			
			modelAndView.addObject("successPdtiCount", statisticsManager.getSuccessfulPdtiTestCount(null));
			modelAndView.addObject("successPdtiCount30", statisticsManager.getSuccessfulPdtiTestCount(30));
			modelAndView.addObject("successPdtiCount60", statisticsManager.getSuccessfulPdtiTestCount(60));
			modelAndView.addObject("successPdtiCount90", statisticsManager.getSuccessfulPdtiTestCount(90));
			
			modelAndView.addObject("failedPdtiCount", statisticsManager.getFailedPdtiTestCount(null));
			modelAndView.addObject("failedPdtiCount30", statisticsManager.getFailedPdtiTestCount(30));
			modelAndView.addObject("failedPdtiCount60", statisticsManager.getFailedPdtiTestCount(60));
			modelAndView.addObject("failedPdtiCount90", statisticsManager.getFailedPdtiTestCount(90));
			
			/*
			 * End of PDTI statistics
			 */
			
			/*
			 * Direct statistics
			 */
			
			modelAndView.addObject("totalDirectCount", statisticsManager.getSuccessfulDirectReceiveCount(null));
			modelAndView.addObject("totalDirectCount30", statisticsManager.getSuccessfulDirectReceiveCount(30));
			modelAndView.addObject("totalDirectCount60", statisticsManager.getSuccessfulDirectReceiveCount(60));
			modelAndView.addObject("totalDirectCount90", statisticsManager.getSuccessfulDirectReceiveCount(90));
			
			modelAndView.addObject("precannedDirectCount", statisticsManager.getSuccessfulPrecannedDirectReceiveCount(null));
			modelAndView.addObject("precannedDirectCount30", statisticsManager.getSuccessfulPrecannedDirectReceiveCount(30));
			modelAndView.addObject("precannedDirectCount60", statisticsManager.getSuccessfulPrecannedDirectReceiveCount(60));
			modelAndView.addObject("precannedDirectCount90", statisticsManager.getSuccessfulPrecannedDirectReceiveCount(90));
			
			modelAndView.addObject("uploadedDirectCount", statisticsManager.getSuccessfulUploadedDirectReceiveCount(null));
			modelAndView.addObject("uploadedDirectCount30", statisticsManager.getSuccessfulUploadedDirectReceiveCount(30));
			modelAndView.addObject("uploadedDirectCount60", statisticsManager.getSuccessfulUploadedDirectReceiveCount(60));
			modelAndView.addObject("uploadedDirectCount90", statisticsManager.getSuccessfulUploadedDirectReceiveCount(90));
			
			/*
			 * End of Direct statistics
			 */
			
			
			/*
			 * JIRA Statistics
			 */
			modelAndView.addObject("jiraIssuesCreated", statisticsManager.getJiraIssuesCreatedCount(0));
			modelAndView.addObject("jiraIssuesCreated30", statisticsManager.getJiraIssuesCreatedCount(30));
			modelAndView.addObject("jiraIssuesCreated60", statisticsManager.getJiraIssuesCreatedCount(60));
			modelAndView.addObject("jiraIssuesCreated90", statisticsManager.getJiraIssuesCreatedCount(90));
			
			modelAndView.addObject("jiraIssuesResolved", statisticsManager.getJiraIssuesResolvedCount(0));
			modelAndView.addObject("jiraIssuesResolved30", statisticsManager.getJiraIssuesResolvedCount(30));
			modelAndView.addObject("jiraIssuesResolved60", statisticsManager.getJiraIssuesResolvedCount(60));
			modelAndView.addObject("jiraIssuesResolved90", statisticsManager.getJiraIssuesResolvedCount(90));
			
			/*
			 * End JIRA Statistics
			 */
			
			
			/*
			 * Google Analytics Statistics
			 */
			
			if (this.props == null)
			{
				
				try {
					this.loadProperties();
				} catch (IOException e) {
					logger.error("Properties could not be loaded:" ,e);
				}
			}
			
			String p12CertPath = this.props.getProperty("googleAnalyticsp12CertPath");
			
			modelAndView.addObject("GoogleAnalyticsSessions", statisticsManager.getGoogleAnalyticsSessionCount(0, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsSessions30", statisticsManager.getGoogleAnalyticsSessionCount(30, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsSessions60", statisticsManager.getGoogleAnalyticsSessionCount(60, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsSessions90", statisticsManager.getGoogleAnalyticsSessionCount(90, p12CertPath));
			
			modelAndView.addObject("GoogleAnalyticsPageViews", statisticsManager.getGoogleAnalyticsPageViewCount(0, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsPageViews30", statisticsManager.getGoogleAnalyticsPageViewCount(30, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsPageViews60", statisticsManager.getGoogleAnalyticsPageViewCount(60, p12CertPath));
			modelAndView.addObject("GoogleAnalyticsPageViews90", statisticsManager.getGoogleAnalyticsPageViewCount(90, p12CertPath));
			/*
			 * End Google Analytics Statistics
			 */
			
			modelAndView.setViewName("view");
		}
		
		return modelAndView;
	}

	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
	
	
	
}
