function getXMLHTTPRequest() {
	try {
		req = new XMLHttpRequest();
	} catch (err1) {
		try {
			req = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (err2) {
			try {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (err3) {
				req = false;
			}
		}
	}
	return req;
}
function hideUniversityValues() {
	var flag = document.getElementById('flag').value;
	if (flag != 'true') {
		document.getElementById("state").style.display = '';
		document.getElementById("search").style.display = '';
	} else {
		document.getElementById("state").style.display = '';
		document.getElementById("search").style.display = '';
	}
	var stateId = document.getElementById("stateId").value;
	if (stateId == '-1')
		document.getElementById('university').style.display = 'none';
	else
		document.getElementById('university').style.display = '';

}

function changeUniversity() {

	document.getElementById('showResult').style.display = 'none';
	document.getElementById('error').style.display='none';	

}
function viewUniversityForFormUpload1(stateId, typeId) {	
	//alert(typeId);
	var flag ='report';
	var isReportType = 'nationalReport';
	document.getElementById("showResult").style.display = 'none';
	if (typeId != '2')
		document.getElementById("search").style.display = '';
	else
		document.getElementById("search").style.display = 'none';

	document.getElementById('university').style.display = '';

	var myurl = 'viewUniversitypopulateForFormUpload?stateId=' + stateId + '&typeId='
			+ typeId + '&flag='+flag  + '&isReportType='+isReportType;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = viewUniversityResponse;
	httpObj.send(null);
	return true;

}

function viewUniversity(stateId, typeId) {
    
	
	//alert(typeId);
	var flag ='report';
	var isReportType = 'nationalReport';
	document.getElementById("showResult").style.display = 'none';
	if (typeId != '2')
		document.getElementById("search").style.display = '';
	else
		document.getElementById("search").style.display = 'none';

	document.getElementById('university').style.display = '';

	var myurl = 'viewUniversitypopulate?stateId=' + stateId + '&typeId='
			+ typeId + '&flag='+flag + '&isReportType='+isReportType;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = viewUniversityResponse;
	httpObj.send(null);
	return true;

}

function viewUniversityResponse() {
	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;

		document.getElementById("showuniversity").innerHTML = superstr;

	}
}
function hideCollegeValues() {
	var flag = document.getElementById('flag').value;
	if (flag != 'true') {
		document.getElementById("state").style.display = '';
		document.getElementById('university').style.display = 'none';
		document.getElementById('college').style.display = 'none';
		document.getElementById("search").style.display = 'none';
	} else {
		document.getElementById("state").style.display = '';
		document.getElementById('university').style.display = '';
		document.getElementById('college').style.display = '';
		document.getElementById("search").style.display = '';
	}
}

function viewCollege() {

	var isReportType = 'nationalReport';
	var universityId = document.getElementById("universityId").value;
	// alert("universityId>>"+universityId);
	document.getElementById("search").style.display = '';
	document.getElementById('university').style.display = '';

	var myurl = 'viewCollegepopulate?universityId=' + universityId+'&isReportType='+isReportType;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = viewCollegeResponse;
	httpObj.send(null);
	return true;
}

function viewCollegeResponse() {

	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		// alert(superstr);
		document.getElementById("showCollege").innerHTML = superstr;
		document.getElementById('college').style.display = '';
	}
}
function hideInstitutesValues() {
	var flag = document.getElementById('flag').value;
	if (flag != 'true') {
		document.getElementById("state").style.display = '';
		document.getElementById('institute').style.display = 'none';
		document.getElementById("search").style.display = 'none';
	} else {
		document.getElementById("state").style.display = '';
		document.getElementById('institute').style.display = '';
		document.getElementById("search").style.display = '';
	}
}
function viewInstitue(stateId) {
    
	var isReportType = 'nationalReport';
	document.getElementById("search").style.display = '';
	document.getElementById('institute').style.display = '';
	var myurl = 'viewInstitutepopulate?stateId=' + stateId+'&isReportType='+isReportType;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = viewInstituteResponse;
	httpObj.send(null);
	return true;
}
function viewInstitueForFormUpload(stateId) {
    
	var isReportType = 'nationalReport';
	document.getElementById("search").style.display = '';
	document.getElementById('institute').style.display = '';
	var myurl = 'viewInstitutepopulateForFormUpload?stateId=' + stateId+'&isReportType='+isReportType;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = viewInstituteResponse;
	httpObj.send(null);
	return true;
}

function viewInstituteResponse() {
	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		document.getElementById("showinstitute").innerHTML = superstr;
	}
}

function changeState() {
	document.getElementById('showResult').style.display = 'none';
}

function changeStateError()
{
	 document.getElementById('error').style.display='none';
}

function submitUniversityReport()
{
	if(document.getElementById('stateId').value=='-1')
	{
		alert("Please select State");
		return false;
	}

	if(document.getElementById('universityId').value=='-1')
	{
		alert("Please select University");
		return false;
	}
}
