<% String rootContext = request.getRequestURL().toString().replace("/About", ""); %>

<html>
<head>
	<title>CCDA Validation Services API</title>
	
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<style>
	
		.row {
			margin-left: 30px;
			margin-right:30px;
		}
			
		.page
		{
			margin-left: 20px;
		}
		
	
	</style>
	
</head>

<body>
<div class="jumbotron">
  <h1>CCDA Validation Services API</h1>
  <p>The goal of the SITE C-CDA Validator is to validate conformance of C-CDA documents to the standard in order to promote interoperability. It is a resource to validate the conformance of C-CDA documents to Meaningful Use Stage 2 (MU2) criteria, or the base HL7 C-CDA Implementation Guide.</p>
</div>

<div class="row">
	<div class="col-lg-9 col-md-9 col-sm-9">
		<div id="validateCode" class="page-header">
  			<h1>Validate C-CDA</h1>
		</div>
		
		<div id="validateCodePage" class="page">
  			
  			
  			<h2>URL</h2>
  			<p><%= rootContext %>/&lt;Version code&gt;  			  			
  			<h2>Validator Versions</h2>
  			<p>The following versions are supported:</p>
			<table>
				<tr>
					<td><b>C-CDA Validator</b></td><td><b>Version code</b></td>
				</tr>
				<tr>
					<td>C-CDA Version 1.1 and MU2</td><td>r1.1</td>
				</tr>
				<tr>
					<td>C-CDA Version 2.0</td><td>r2.0</td>
				</tr>
				<!--<tr>
					<td>Reconciled C-CDA</td><td>Reconciled</td>
				</tr>
				<tr>
					<td>Reference C-CDA</td><td>Reference</td>
				</tr>
				
				<tr>
					<td>Super C-CDA</td><td>Super</td>
				</tr>-->
				
			</table>
  			<br>
  			<br>
  			<br>
  			<br>
  			<h2>C-CDA Release 1.1 and MU2 Validator</h2>
  			
  			<h3>Parameters</h3>
  			
			<p>file = The name of the multipart/form-data parameter that contains attachments must be &quot;file&quot;</p>

			<p>type_val = a parameter indicating the type of document being analyzed<br><br></p>
  			
  			<h3>Type Values</h3>
  			
  			
  			<table>
			 <tr>
			 	<td>
			 		<p><b><span>Document Type</span></b></p>
			 	</td>
			 	<td>
			 		<p><b><span>type_val</span></b></p>
			  	</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<p><span>Clinical Office Visit Summary - MU2 170.314(e)(2) - Clinical Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>ClinicalOfficeVisitSummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary - MU2 170.314(b)(2) Transition of Care/Referral Summary - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb2</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary - MU2 170.314(b)(7) Data Portability - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb7</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Ambulatory Summary -
			  			MU2 170.314(b)(1) Transition of Care Receive - For Ambulatory Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareAmbulatorySummaryb1</span></p>
			  		</td>
			 	</tr>
				<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			  			170.314(b)(2) Transition of Care/Referral Summary - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb2</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			 			170.314(b)(7) Data Portability - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb7</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>Transitions Of Care Inpatient Summary - MU2
			  			170.314(b)(1) Transition of Care Receive - For Inpatient Care</span></p>
			  		</td>
			  		<td>
			  			<p><span>TransitionsOfCareInpatientSummaryb1</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>VDT Ambulatory Summary - MU2 170.314 (e)(1)
			  			Ambulatory Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>VDTAmbulatorySummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			  		<td>
			  			<p><span>VDT Inpatient Summary - MU2 170.314 (e)(1)
			  			Inpatient Summary</span></p>
			  		</td>
			  		<td>
			  			<p><span>VDTInpatientSummary</span></p>
			  		</td>
			 	</tr>
			 	<tr>
			 		<td>
			  			<p><span>Non-specific CCDA</span></p>
			  		</td>
			  		<td>
			  			<p><span>NonSpecificCCDA</span></p>
			  		</td>
			 	</tr>
			</table>
  			
  			
  			<div>
  			
  			<br><h3>Example Usage</h3>
  			<p>
  				The following example will validate the file "myfile.xml", with validation type "TransitionsOfCareInpatientSummary":</p>
  			<p >curl -D- -X POST -F &quot;file=@myfile.xml&quot; -F &quot;type_val=TransitionsOfCareInpatientSummary&quot; <%= request.getRequestURL().toString().replace("About", "r1.1/") %></p>
  			
  			</div>
  			
  			
  			
  			<h3><br><br>Validation Response</h3>
  			<div>
  			<p>The C-CDA r1.1 and MU2 validator will return a JSON object with two members: C-CDA r1.1 and MU2 validation results, named ccdaResults, 
  			and an extended results containing vocabulary validation, denoted as extendedCcdaResults.
  			</p>
  			
  			 <p><b>ccdaResults</b> Contains the following members:
  			 	<ul>
  			 		<li><b>performance:</b> An object detailing processing time and time of request.</li>
  			 		<li><b>report:</b> An object containing details about the validation, including document type selection,
  			 		whether the object has errors (<b>hasErrors</b>), has warnings(<b>hasWarnings</b>), and/or has info messages (<b>hasInfo</b>), 
  			 		as well as counts errors, warnings, and info messages (<b>warningCount, errorCount, infoCount</b>). Report also contains the 
  			 		name of the file uploaded (<b>uploadedFileName</b>) and informational statements about the validation results (<b>validationResults1, validationResults2</b>). 
  			 		</li>
  			 		<li><b>errors: </b>An array of error messages.</li>
  			 		<li><b>warnings: </b>An array of warning messages.</li>
  			 		<li><b>info:</b> An array of info messages.</li>
  			 	</ul>
  			</p>
  			
  			<p><b>ccdaExtendedResults</b> Contains the following members:
  			 	<ul>
  			 		<li><b>report:</b> An object containing details about the validation, including document type selection,
  			 		whether the object has errors (<b>hasErrors</b>), has warnings(<b>hasWarnings</b>), and/or has info messages (<b>hasInfo</b>).
  			 		</li>
  			 		<li><b>errorList: </b>An array of error messages.</li>
  			 		<li><b>warningList: </b>An array of warning messages.</li>
  			 		<li><b>infoList:</b> An array of info messages.</li>
  			 	</ul>
  			</p>
  			
  			
  			
Example JSON output:
<code>
<pre>
{
	"ccdaResults":{
	   "performance":{
		  "processingTime":"13.014",
		  "dateTimeOfRequest":"Fri Aug 22 14:13:03 EDT 2014"
	   },
	   "report":{
		  "docTypeSelected":"Clinical Office Visit Summary",
		  "uploadedFileName":"CCDA_Ambulatory.xml",
		  "validationResults2":"The file has encountered 1 error(s). The file has encountered 3 warning(s). The file has encountered 1 info message(s).",
		  "validationResults1":"Failed Validation",
		  "hasErrors":"true",
		  "errorCount":1,
		  "hasWarnings":"true",
		  "warningCount":3,
		  "hasInfo":"true",
		  "infoCount":1
		  
	   },
	   "errors":[
		  {
			 "message":"Mu2consol Clinical Office Visit Summary SHALL contain exactly one [1..1] component Contains exactly one [1..1] Consol Instructions Section (templateId: 2.16.840.1.113883.10.20.22.2.45)",
			 "source":"MU2 Certification related (cda.mu2consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  }
	   ],
	   "warnings":[
		  {
			 "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) patient Role SHALL contain exactly one [1..1] patient, where  (CONF:5283) each SHOULD contain zero or more [0..*] languageCommunication, where  (CONF:5406) languageCommunication SHOULD contain zero or one [0..1] proficiencyLevelCode, which SHALL be selected from ValueSet LanguageAbilityProficiency 2.16.840.1.113883.1.11.12199 STATIC (CONF:9965)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/patient/languageCommunication",
			 "lineNumber":"129"
		  },
		  {
			 "message":"Consol Provider Organization SHALL contain at least one [1..*] addr with @xsi:type=\"USRealmAddress\" (CONF:5422) addr SHOULD contain zero or one [0..1] @use (CONF:7290), which SHALL be selected from ValueSet PostalAddressUse 2.16.840.1.113883.1.11.10637 STATIC",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization/addr",
			 "lineNumber":"140"
		  },
		  {
			 "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) each MAY contain zero or one [0..1] providerOrganization, where  (CONF:5416) providerOrganization The id SHOULD include zero or one [0..1] id where id/@root =\"2.16.840.1.113883.4.6\" National Provider Identifier (CONF:9996) (CONF:9996)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization",
			 "lineNumber":"136"
		  }
	   ],
	   "info":[
		  {
			 "message":"Consol US Realm Header MAY contain zero or one [0..1] setId (CONF:5261)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or one [0..1] versionNumber (CONF:5264)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or more [0..*] inFulfillmentOf (CONF:9952)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or more [0..*] authorization (CONF:16792)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  }
	   ]
	},
   "ccdaExtendedResults":{  
      "report":{  
         "hasErrors":true,
         "hasWarnings":true,
         "hasInfo":true
      },
      "warningList":[  
         {  
            "message":"DisplayName 'Pnuemonia' does not (fully) exist in vocabulary 'SNOMED-CT' (2.16.840.1.113883.6.96)",
            "codeSystemName":"SNOMED-CT",
            "xpathExpression":"ClinicalDocument/documentationOf/serviceEvent/code",
            "codeSystem":"2.16.840.1.113883.6.96",
            "code":"233604007",
            "displayName":"Pnuemonia",
            "nodeIndex":0
         },
         {  
            "message":"DisplayName 'Allergies, adverse reactions, alerts' does not (fully) exist in vocabulary 'LOINC' (2.16.840.1.113883.6.1)",
            "codeSystemName":"LOINC",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/entry/act/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2",
            "displayName":"Allergies, adverse reactions, alerts",
            "nodeIndex":0
         }
      ],
      "errorList":[  
         {  
            "message":"Code '48765-2xxx' does not exist in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2xxx",
            "displayName":"",
            "nodeIndex":0
         }
      ],
      "informationList":[  
         {  
            "message":"DisplayName for code '48765-2xxx' does not exist in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2xxx",
            "displayName":"",
            "nodeIndex":0
         },
         {  
            "message":"DisplayName '' for code '47420-5' did not (fully) match the anticipated value of 'FUNCTIONAL STATUS ASSESSMENT NOTE' in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.14']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"47420-5",
            "displayName":"",
            "nodeIndex":0
         }
      ]
   }
}
</pre>
</code>

</div>


<h2>C-CDA Release 2.0 Validator</h2>
  			
  			<h3>Parameters</h3>
  			
			<p>file = The name of the multipart/form-data parameter that contains attachments must be &quot;file&quot;</p>
  			
  			<div>
  			
  			<br><h3>Example Usage</h3>
  			<p>
  				The following example will validate the file "myfile.xml":</p>
  			<p >curl -D- -X POST -F &quot;file=@myfile.xml&quot;  <%= request.getRequestURL().toString().replace("About", "r2.0/") %></p>
  			
  			</div>
  			
  			
  			
  			<h3><br><br>Validation Response</h3>
  			<div>
Example JSON output:
<code>
<pre>
{
	"ccdaResults":{
	   "performance":{
		  "processingTime":"13.014",
		  "dateTimeOfRequest":"Fri Aug 22 14:13:03 EDT 2014"
	   },
	   "report":{
		  "docTypeSelected":"Non-specific C-CDA R2 (Validated as: Continuity Of Care Document)",
		  "uploadedFileName":"CCDA_Ambulatory.xml",
		  "validationResults2":"The file has encountered 1 error(s). The file has encountered 3 warning(s). The file has encountered 1 info message(s).",
		  "validationResults1":"Failed Validation",
		  "hasErrors":"true",
		  "errorCount":1,
		  "hasWarnings":"true",
		  "warningCount":3,
		  "hasInfo":"true",
		  "infoCount":1
		  
	   },
	   "errors":[
		  {
			 "message":"Mu2consol Clinical Office Visit Summary SHALL contain exactly one [1..1] component Contains exactly one [1..1] Consol Instructions Section (templateId: 2.16.840.1.113883.10.20.22.2.45)",
			 "source":"MU2 Certification related (cda.mu2consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  }
	   ],
	   "warnings":[
		  {
			 "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) patient Role SHALL contain exactly one [1..1] patient, where  (CONF:5283) each SHOULD contain zero or more [0..*] languageCommunication, where  (CONF:5406) languageCommunication SHOULD contain zero or one [0..1] proficiencyLevelCode, which SHALL be selected from ValueSet LanguageAbilityProficiency 2.16.840.1.113883.1.11.12199 STATIC (CONF:9965)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/patient/languageCommunication",
			 "lineNumber":"129"
		  },
		  {
			 "message":"Consol Provider Organization SHALL contain at least one [1..*] addr with @xsi:type=\"USRealmAddress\" (CONF:5422) addr SHOULD contain zero or one [0..1] @use (CONF:7290), which SHALL be selected from ValueSet PostalAddressUse 2.16.840.1.113883.1.11.10637 STATIC",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization/addr",
			 "lineNumber":"140"
		  },
		  {
			 "message":"Consol US Realm Header SHALL contain at least one [1..*] recordTarget (CONF:5266) each SHALL contain exactly one [1..1] patientRole, where  (CONF:5268) each MAY contain zero or one [0..1] providerOrganization, where  (CONF:5416) providerOrganization The id SHOULD include zero or one [0..1] id where id/@root =\"2.16.840.1.113883.4.6\" National Provider Identifier (CONF:9996) (CONF:9996)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument/recordTarget/patientRole/providerOrganization",
			 "lineNumber":"136"
		  }
	   ],
	   "info":[
		  {
			 "message":"Consol US Realm Header MAY contain zero or one [0..1] setId (CONF:5261)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or one [0..1] versionNumber (CONF:5264)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or more [0..*] inFulfillmentOf (CONF:9952)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  },
		  {
			 "message":"Consol US Realm Header MAY contain zero or more [0..*] authorization (CONF:16792)",
			 "source":"C-CDA Validation related (cda.consol)",
			 "path":"/ClinicalDocument",
			 "lineNumber":"23"
		  }
	   ]
	},
   "ccdaExtendedResults":{  
      "report":{  
         "hasErrors":true,
         "hasWarnings":true,
         "hasInfo":true
      },
      "warningList":[  
         {  
            "message":"DisplayName 'Pnuemonia' does not (fully) exist in vocabulary 'SNOMED-CT' (2.16.840.1.113883.6.96)",
            "codeSystemName":"SNOMED-CT",
            "xpathExpression":"ClinicalDocument/documentationOf/serviceEvent/code",
            "codeSystem":"2.16.840.1.113883.6.96",
            "code":"233604007",
            "displayName":"Pnuemonia",
            "nodeIndex":0
         },
         {  
            "message":"DisplayName 'Allergies, adverse reactions, alerts' does not (fully) exist in vocabulary 'LOINC' (2.16.840.1.113883.6.1)",
            "codeSystemName":"LOINC",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/entry/act/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2",
            "displayName":"Allergies, adverse reactions, alerts",
            "nodeIndex":0
         }
      ],
      "errorList":[  
         {  
            "message":"Code '48765-2xxx' does not exist in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2xxx",
            "displayName":"",
            "nodeIndex":0
         }
      ],
      "informationList":[  
         {  
            "message":"DisplayName for code '48765-2xxx' does not exist in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"48765-2xxx",
            "displayName":"",
            "nodeIndex":0
         },
         {  
            "message":"DisplayName '' for code '47420-5' did not (fully) match the anticipated value of 'FUNCTIONAL STATUS ASSESSMENT NOTE' in vocabulary '' (2.16.840.1.113883.6.1)",
            "codeSystemName":"",
            "xpathExpression":"/ClinicalDocument/component/structuredBody/component/section/templateId[@root='2.16.840.1.113883.10.20.22.2.14']/ancestor::section[1][0]/code",
            "codeSystem":"2.16.840.1.113883.6.1",
            "code":"47420-5",
            "displayName":"",
            "nodeIndex":0
         }
      ]
   }
}
</pre>
</code>


</div>



		
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 hidden-xs">
		
		<ul class="nav nav-pills nav-stacked">
			<li class="page-header"><h1>Project Links</h1></li>
  			<li><a href="https://github.com/siteadmin/SITE">Project Repository</a></li>
		</ul>
	</div>
</div>



</body>

</html>