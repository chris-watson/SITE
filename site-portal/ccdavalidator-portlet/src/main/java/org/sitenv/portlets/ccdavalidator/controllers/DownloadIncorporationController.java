package org.sitenv.portlets.ccdavalidator.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;
import org.sitenv.common.statistics.manager.StatisticsManager;
import org.sitenv.common.utilities.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.servlet.HttpHeaders;


@Controller
@RequestMapping("VIEW")
public class DownloadIncorporationController extends BaseController {	
	
	@Autowired
	private StatisticsManager statisticsManager;
	
	private static Logger logger = Logger.getLogger(DownloadIncorporationController.class);
	
    private static final Map<String, String> negativeTestingCCDAFileNames;
    static
    {
    	negativeTestingCCDAFileNames = new HashMap<String, String>();
    	negativeTestingCCDAFileNames.put("0", "AllNegativeTestingCCDAs.zip");
    	negativeTestingCCDAFileNames.put("1", "Ambulatory_IncorrectImmunization.zip");
    	negativeTestingCCDAFileNames.put("2", "Ambulatory_IncorrectLabResults.zip");
    	negativeTestingCCDAFileNames.put("3", "Ambulatory_IncorrectProcedures.zip");
    	negativeTestingCCDAFileNames.put("4", "Ambulatory_IncorrectVitalSigns.zip");
    	negativeTestingCCDAFileNames.put("5", "Ambulatory_InvalidCS.zip");
    	negativeTestingCCDAFileNames.put("6", "Ambulatory_invalidDataTypes.zip");
    	negativeTestingCCDAFileNames.put("7", "Ambulatory_missingMU2elements.zip");
    	negativeTestingCCDAFileNames.put("8", "Ambulatory_missingNarrative.zip");
    	negativeTestingCCDAFileNames.put("9", "Inpatient_Code_Not_in_ValueSet.zip");
    	negativeTestingCCDAFileNames.put("10", "Inpatient_IncorrectAllergies.zip");
    	negativeTestingCCDAFileNames.put("11", "Inpatient_IncorrectMedication.zip");
    	negativeTestingCCDAFileNames.put("12", "Inpatient_IncorrectProblems.zip");
    	negativeTestingCCDAFileNames.put("13", "Inpatient_missingTemplateIds.zip");
    	negativeTestingCCDAFileNames.put("14", "Inpatient_PooprlyFormed.zip");
    	negativeTestingCCDAFileNames.put("15", "Inpatient_wrongTemplateIds.zip");
    	
    }
	
	
    private static final Map<String, String> referenceCCDAFileNames;
    static
    {
    	referenceCCDAFileNames = new HashMap<String, String>();
    	referenceCCDAFileNames.put("1", "Ambulatory_Summary-no_errors.zip");
    	referenceCCDAFileNames.put("2", "Inpatient_Summary-no_errors.zip");
    	referenceCCDAFileNames.put("2", "Inpatient_Summary-no_errors.zip"); 	
    }
	
	public void copyStream(InputStream in, OutputStream out){
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = in.read(buffer)) != -1) {
			    out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@ResourceMapping("downloadNegativeTesting")
	public void serveNegativeTestingSamples(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String ccdaVal = resourceRequest.getParameter("getCCDA");
		
		String fileName = DownloadIncorporationController.negativeTestingCCDAFileNames.get(ccdaVal);
		
		
		String downloadPath = props.getProperty("CcdasForNegativeTesting") + "/" + fileName;
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/zip");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadReferenceIncorporation")
	public void serveReferenceSamples(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String ccdaVal = resourceRequest.getParameter("getCCDA");
		
		String fileName = DownloadIncorporationController.referenceCCDAFileNames.get(ccdaVal);
		
		String downloadPath = props.getProperty("referenceCcdasForIncorporation") + "/" + fileName;
		
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/zip");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadVendorIncorporation")
	public void serveSamplesFromVendors(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("samplesFromVendorsForIncorporation") + "/" + resourceRequest.getParameter("incorpfilepath");
		
		
		String[] downloadPathTokens = downloadPath.split("[/\\\\]");
		String fileName = downloadPathTokens[downloadPathTokens.length-1];
		
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/xml");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	

	@ResourceMapping("downloadReconciledBundle")
	public void serveReconciledBundle(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("ReconciledFileBundles") + "/" + resourceRequest.getParameter("reconciledBundleFilepath");
		
		
		String[] downloadPathTokens = downloadPath.split("[/\\\\]");
		String fileName = downloadPathTokens[downloadPathTokens.length-1];
		
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/xml");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	
	@ResourceMapping("downloadReferenceTestData")
	public void serveReferenceTestData(ResourceRequest resourceRequest, ResourceResponse res) throws PortletException, IOException {
		
		if (this.props == null)
		{
			this.loadProperties();
		}
		
		String downloadPath = props.getProperty("ReferenceDownloadFiles") + "/" + resourceRequest.getParameter("referenceDownloadFilepath");
		
		
		String[] downloadPathTokens = downloadPath.split("[/\\\\]");
		String fileName = downloadPathTokens[downloadPathTokens.length-1];
		
		
		File downloadFile = new File(downloadPath);
		InputStream in = new FileInputStream(downloadFile);
		
		res.setContentType("application/xml");
		res.addProperty(HttpHeaders.CACHE_CONTROL,
				"max-age=3600, must-revalidate");
		res.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
				+ fileName + "\"");
		// Use this to directly download the file
		res.addProperty("Set-Cookie", "fileDownload=true; path=/");
		
		
		OutputStream out = res.getPortletOutputStream();
		
		copyStream(in, out);
		out.flush();
		out.close();
		in.close();
		statisticsManager.addReferenceCcdaDownload(fileName);
		
	}
	
	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}
	
}
