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

function createUserId() {

	document.getElementById("autoUserId").innerHTML="";
	document.getElementById("userMaster.userId").value="";
	var roleId=document.getElementById("roleId").value;
	var roleStateId=document.getElementById("roleStateId").value;
	var roleDistricId=document.getElementById("roleDistricId").value;
	var myurl;

	if(roleId==1)
	{

		myurl ="createuserid?roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
		httpObj = new getXMLHTTPRequest();
		httpObj.open("GET", myurl, true);
		httpObj.onreadystatechange = 
			createUserIdResponse;
		httpObj.send(null);
	}
	else if(roleId==2)
	{

		myurl ="createuserid?roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
		httpObj = new getXMLHTTPRequest();
		httpObj.open("GET", myurl, true);
		httpObj.onreadystatechange = createUserIdResponse;
		httpObj.send(null);
	}
	else if(roleId==8)
	{

		myurl ="createuserid?roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
		httpObj = new getXMLHTTPRequest();
		httpObj.open("GET", myurl, true);
		httpObj.onreadystatechange = createUserIdResponse;
		httpObj.send(null);
	}
	else if((roleId==3||roleId==4)&&roleStateId!=0)
	{  

		myurl ="createuserid?roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
		httpObj = new getXMLHTTPRequest();
		httpObj.open("GET", myurl, true);
		httpObj.onreadystatechange = 
			createUserIdResponse;
		httpObj.send(null);
	}
	else if((roleId==5||roleId==6||roleId==7)&&roleStateId!=0&&roleDistricId!=0)
	{
		myurl ="createuserid?roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
		httpObj = new getXMLHTTPRequest();
		httpObj.open("GET", myurl, true);
		httpObj.onreadystatechange = 
			createUserIdResponse;
		httpObj.send(null);
	}
	return true;
}

function showRoleDsitrict(stateCode, x) {

	var roleId = document.getElementById("roleId").value;
	var myurl = "populatedistrict?stateCode=" + stateCode + "&x=" + x
	+ "&roleId=" + roleId;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = showRoleDsitrictResponse;
	httpObj.send(null);
	return true;
}

function showRoleDsitrictResponse() {
	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		document.getElementById("showRoleDsitrict").innerHTML = superstr;
		var roleId=document.getElementById("roleId").value;
		checkLevel(roleId);
		createUserId();
	}
}

//Used in user directory user list
function showUserListDistrict(stateCode) {

	var roleId = document.getElementById("roleId").value;
	var myurl = "userdirectorydistrictlist?stateCode=" + stateCode 
	+ "&roleId=" + roleId;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = showUserListDistrictResponse;
	httpObj.send(null);
	return true;
}

function showUserListDistrictResponse() {
	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		document.getElementById("showRoleDsitrict").innerHTML = superstr;
		var roleId=document.getElementById("roleId").value;
		checkLevel(roleId);
	}
}

function showAddressDsitrict(stateCode, x) {
	var myurl = "populatedistrict?stateCode=" + stateCode + "&x=" + x;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = showAddressDsitrictResponse;
	httpObj.send(null);
	return true;
}

function showAddressDsitrictResponse() {
	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		document.getElementById("showAddressDsitrict").innerHTML = superstr;
	}
}

function showDistrict(stateCode) {
	var myurl = "districtpopulate?stateCode=" + stateCode;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = showDistrictResponse;
	httpObj.send(null);
	return true;
}

function showDistrictResponse() {

	if (httpObj.readyState == 4) {
		var superstr = httpObj.responseText;
		document.getElementById("showDistrict").innerHTML = superstr;
	}
}

function showDistrictListing(stateCode) {

	var myurl = "populatedistrictsinlisting?stateCode=" + stateCode;
	httpObj = new getXMLHTTPRequest();
	httpObj.open("GET", myurl, true);
	httpObj.onreadystatechange = showDistrictResponse;
	httpObj.send(null);
	return true;
}

function checkLevel(roleId) {

	if(roleId==1||roleId==2||roleId==8)
	{
		document.getElementById("roleStateId").value="0";
		document.getElementById("roleDistricId").value="0";
		document.getElementById("roleStateId").disabled="true";
		document.getElementById("roleDistricId").disabled="true";
	}

	else if(roleId==3||roleId==4)
	{
		document.getElementById("roleStateId").disabled=false;
		document.getElementById("roleDistricId").disabled="true";
		document.getElementById("roleDistricId").value="0";
	}
	else
	{	
		document.getElementById("roleStateId").disabled=false;
		document.getElementById("roleDistricId").disabled=false;
	}
}

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
