function show() {

	document.getElementById("form1").style.display = 'none';
	document.getElementById("form2").style.display = 'none';
	document.getElementById("form3").style.display = 'none';
}

function populateDistrictForState(stateCode) {

	// document.getElementById("stateLevelBodyInstituteCaption").value='College';
	$
	.ajax({
		type : "POST",
		url : "getDistrictListForState",
		data : '&stateCode=' + stateCode,
		success : function(districtMap) {

			selectOptions = "";
			var selectStart = "<option value='0'>---Select District---</option>";
			for (keyValue in districtMap) {
				var value = districtMap[keyValue];

				selectOptions = selectOptions + "<option value='"
				+ keyValue + "'>" + districtMap[keyValue]
				+ "</option>";
			}

			var finalDropDown = selectStart.concat(selectOptions);
			$("#districtCode").empty();
			$("#districtCode").append(finalDropDown);

		},
		datatype : 'json'
	});
}

function populateRoleBasedStateListForSurveyYear(surveyYear, formType, dropDownId) {

	$.ajax({
		type: "POST",
		url: "getRoleBasedStateListForSurveyYear",
		data: 'surveyYear=' + surveyYear + '&formType=' + formType,
		success: function(result) {
			
			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + dropDownId).empty();
			$("#" + dropDownId).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateRoleBasedUniversityListForSurveyYearAndState(surveyYear, stateCode, universityDropDownID) {

	$.ajax({
		type: "POST",
		url: "getRoleBasedUniversityListForSurveyYearAndState",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode,
		success: function(result) {
				
			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + universityDropDownID).empty();
			$("#" + universityDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateRoleBasedDistrictListForSurveyYearAndState(surveyYear, stateCode, districtDropDownID) {

	$.ajax({
		type: "POST",
		url: "getRoleBasedDistrictListForSurveyYearAndState",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode,
		success: function(result) {

			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + districtDropDownID).empty();
			$("#" + districtDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateRoleBasedCollegeListForSurveyYearAndStateAndDistrict(surveyYear, stateCode, districtCode, collegeDropDownID) {

	$.ajax({
		type: "POST",
		url: "getRoleBasedCollegeListForSurveyYearAndStateAndDistrict",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode + '&districtCode=' + districtCode,
		success: function(result) {

			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + collegeDropDownID).empty();
			$("#" + collegeDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateRoleBasedStandaloneInstitutionListForSurveyYearAndState(surveyYear, stateCode, standaloneDropDownID, instituteType) {

	$.ajax({
		type: "POST",
		url: "getRoleBasedStandaloneInstitutionListForSurveyYearAndState",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode + '&instituteType=' + instituteType,
		success: function(result) {

			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + standaloneDropDownID).empty();
			$("#" + standaloneDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}


function populateInstitutionListForSurveyYearAndState(surveyYear, stateCode, institutionType, institutionListDropDownID) {
	
	$.ajax({
		type: "POST",
		url: "getInstitutionsForSurveyYearAndState",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode + '&institutionType=' + institutionType,
		success: function(result) {

			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + institutionListDropDownID).empty();
			$("#" + institutionListDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateInstitutionsForSurveyYearAndState(surveyYear, stateCode, institutionType, institutionListDropDownID) {
	
	$.ajax({
		type: "POST",
		url: "getInstitutionsForSurveyYearAndState",
		data: 'surveyYear=' + surveyYear + '&stateCode=' + stateCode + '&institutionType=' + institutionType,
		success: function(result) {

			var selectOptions = "";
			for (var i = 0; i < result.length; i++) {

				selectOptions = selectOptions + "<option value = '" + result[i].key + "'>" + result[i].value + "</option>";
			}

			$("#" + institutionListDropDownID).empty();
			$("#" + institutionListDropDownID).append(selectOptions);
		},
		datatype : 'json'
	});
}
function populateRoleBasedStandaloneTypeListForSurveyYear(surveyYear, standaloneInstituteTypeID) {

	$.ajax({
		type: "POST",
		url: "getStandaloneInstituteTypeDropDownList",
		data: 'surveyYear=' + surveyYear,
		success: function(result) {

			var selectOptions = "";
			for (keyValue in result) {

				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}

			$("#" + standaloneInstituteTypeID).empty();
			$("#" + standaloneInstituteTypeID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function populateInstituteTypeList(stateCode, instituteTypeID) {
	$.ajax({
		type: "POST",
		url: "getInstituteListForState",
		data: 'stateCode=' + stateCode,
		success: function(result) {

			var selectOptions = "";
			for (keyValue in result) {

				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}

			$("#" + instituteTypeID).empty();
			$("#" + instituteTypeID).append(selectOptions);
		},
		datatype : 'json'
	});
}

function showHideUniversityBodyListForOtherForms() {
	var instituteType=document.getElementById('selectInstitute').value;

	 if(instituteType == "C") {
		
		 $("#selectBodyforOtherForms").val("0");
		
		 
			document.getElementById("universityDetailforOtherForms").style.display = '';
			document.getElementById("bodyTypeDetailforOtherForms").style.display = 'none';

		}
	 else if(instituteType == "S") {
		
			$("#selectUniversityforOtherForms").val("allUniversities");
			
			document.getElementById("universityDetailforOtherForms").style.display = 'none';
			document.getElementById("bodyTypeDetailforOtherForms").style.display = '';

		}
	 else
		 {
		 
		 $("#selectBodyforOtherForms").val("0");
		 
			$("#selectUniversityforOtherForms").val("allUniversities");
			document.getElementById("universityDetailforOtherForms").style.display = 'none';
			document.getElementById("bodyTypeDetailforOtherForms").style.display = 'none';
		 }
}

function showHideUniversityBodyListForInconsistentMinorityForm() {
	
	var institute=document.getElementById('selectedInstitute').value;
	 if(institute == "C") {
		
		 $("#selectBody").val("0");
		 
			document.getElementById("universityDetails").style.display = '';
			document.getElementById("bodyTypeDetails").style.display = 'none';

		}
	 else if(institute == "S") {
		
			$("#selectUniversity").val("allUniversities");
			
			document.getElementById("universityDetails").style.display = 'none';
			document.getElementById("bodyTypeDetails").style.display = '';
		}
	 else
		 {
		 
		 	$("#selectBody").val("0");
			$("#selectUniversity").val("allUniversities");
			document.getElementById("universityDetails").style.display = 'none';
			document.getElementById("bodyTypeDetails").style.display = 'none';
		 }
}
function universityBodyListForInconsistentMinorityForm(stateCode,surveyYear,universityDropDownID) {
	
		
	var institute =document.getElementById("selectedInstitute").value;
	if(institute == "allInstitutes")
		{
			document.getElementById("universityDetails").style.display = 'none';
			document.getElementById("bodyTypeDetails").style.display = 'none';
		}
	else if(institute == "U")
		{
			document.getElementById("universityDetails").style.display = 'none';
			document.getElementById("bodyTypeDetails").style.display = 'none';
		}
	else if(institute == "C")
		{
			document.getElementById("universityDetails").style.display = '';
			document.getElementById("bodyTypeDetails").style.display = 'none';
		}
	else if(institute == "S")
	{
		document.getElementById("universityDetails").style.display = 'none';
		document.getElementById("bodyTypeDetails").style.display = '';
	}

	$.ajax( {

		type : "POST",
		url : "getUniversityListByStateAndSurveyYear",
		data : 'surveyYear=' + surveyYear + '&stateCode=' + stateCode,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectUniversity' id='selectUniversity' class='form-control>"
				+ "<option value='allUniversities'>-- All --</option>";
			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + universityDropDownID).empty();
			$("#" + universityDropDownID).append(finalDropDown);

		},
		datatype : 'json'
	});
}

function showHideUniversityBodyList() {
	
	var form=document.getElementById('selectFormType');

	
	if(form==null)
	{
		return;
	}
	else
	{
		formType=form.value;
	}
	

	if(formType == "allForms") {
		
		$("#selectBody").val("0");
		$("#selectUniversity").val("allUniversities");
		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
		
	}
	else if(formType == "form1") {
		
		$("#selectBody").val("0");
		$("#selectUniversity").val("allUniversities");
		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	
	}
	else if(formType == "form2") {

		$("#selectBody").val("0");
		document.getElementById("universityDetail").style.display = '';
		document.getElementById("bodyTypeDetail").style.display = 'none';
		
	}
	else if(formType == "form3") {
		
		$("#selectUniversity").val("allUniversities");
		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = '';

	}


}

function universityBodyListByStateCodeAndSurveyYear(stateCode,surveyYear,universityDropDownID) {
	
	var formType =document.getElementById("selectFormType").value;
	
	if(formType == "allForms") {

		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	}
	else if(formType == "form1") {

		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	}
	else if(formType == "form2") {

		document.getElementById("universityDetail").style.display = '';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	}
	else if(formType == "form3") {

		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = '';
	}
	else if(formType == "form4") {

		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	}
	else if(formType == "form5") {

		document.getElementById("universityDetail").style.display = 'none';
		document.getElementById("bodyTypeDetail").style.display = 'none';
	}

	$.ajax( {

		type : "POST",
		url : "getUniversityListByStateAndSurveyYear",
		data : 'surveyYear=' + surveyYear + '&stateCode=' + stateCode,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectUniversity' id='selectUniversity' style='width: 600px;margin: 5px 0px;'>"
				+ "<option value='allUniversities'>-- All --</option>";
			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + universityDropDownID).empty();
			$("#" + universityDropDownID).append(finalDropDown);

		},
		datatype : 'json'
	});
}



function universityBodyListByStateCodeAndSurveyYearforOtherForms(stateCode,surveyYear,universityDropDownID) {
		
	var instituteType =document.getElementById("selectInstitute").value;
	if(instituteType == "C")
		{
		document.getElementById("universityDetailforOtherForms").style.display = '';
		document.getElementById("bodyTypeDetailforOtherForms").style.display = 'none';
		}
	if(instituteType == "S")
	{
	document.getElementById("universityDetailforOtherForms").style.display = 'none';
	document.getElementById("bodyTypeDetailforOtherForms").style.display = '';
	}

	$.ajax( {

		type : "POST",
		url : "getUniversityListByStateAndSurveyYear",
		data : 'surveyYear=' + surveyYear + '&stateCode=' + stateCode,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectUniversityforOtherForms' id='selectUniversityforOtherForms' style='width: 600px;margin: 5px 0px;'>"
				+ "<option value='allUniversities'>-- All --</option>";
			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + universityDropDownID).empty();
			$("#" + universityDropDownID).append(finalDropDown);

		},
		datatype : 'json'
	});
}











function populateUniversityListByStateAndSourceAndTargetSurveyYear(sourceSurveyYear, targetSurveyYear, stateCode, universityDropDownID){
	
	$.ajax( {

		type : "POST",
		url : "getUniversityListByStateAndSourceAndTargetSurveyYear",
		data : 'sourceSurveyYear=' + sourceSurveyYear + '&targetSurveyYear=' + targetSurveyYear + '&stateCode=' + stateCode,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectedUniversityId' id='selectedUniversityId' multiple='true' size='4' title='Use ctrl+click to select multiple universities'> cssStyle=min-width: 250px;height: 20px;margin: 5px 0px;'";

			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + universityDropDownID).empty();
			$("#" + universityDropDownID).append(finalDropDown);
			
			if(selectOptions.length > 0){
				document.getElementById("noUniversityFound").style.display = 'none';
				document.getElementById("universityListCombo").style.display = '';			
			}
			else if(targetSurveyYear != "-1"){
				document.getElementById("universityListCombo").style.display = 'none';
				document.getElementById("noUniversityFound").style.display = '';
			}
		},
		datatype : 'json'
	});
}

function populateDistrictName(stateCode){

	$
	.ajax({
		type : "POST",
		url : "getDistrictListForState",
		data : '&stateCode=' + stateCode,
		success : function(districtMap) {

			selectOptions = "";
			var selectStart = "<option value='-1'>---SELECT DISTRICT---</option>";
			for (keyValue in districtMap) {
				var value = districtMap[keyValue];

				selectOptions = selectOptions + "<option value='"
				+ keyValue + "'>" + districtMap[keyValue]
				+ "</option>";
			}

			var finalDropDown = selectStart.concat(selectOptions);
			$("#districtName").empty();
			$("#districtName").append(finalDropDown);

		},
		datatype : 'json'
	});
	
	return true;		
}

function populateCollegeListByStateAndSourceAndTargetSurveyYear(sourceSurveyYear, targetSurveyYear, stateCode, districtCode, universityId, collegeDropDownID){
	
	$.ajax( {

		type : "POST",
		url : "getCollegeListByStateAndSourceAndTargetSurveyYear",
		data : 'sourceSurveyYear=' + sourceSurveyYear + '&targetSurveyYear=' + targetSurveyYear + '&stateCode=' + stateCode + '&districtCode=' + districtCode + '&universityId=' + universityId,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectCollegeId' id='selectCollegeId' multiple='true' size='4'  cssStyle='width: 250px;height: 20px;margin: 5px 0px;' title='Use ctrl+click to select multiple colleges'>";

			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + collegeDropDownID).empty();
			$("#" + collegeDropDownID).append(finalDropDown);
			
			if(selectOptions.length > 0){
				document.getElementById("noCollegeFound").style.display = 'none';
				document.getElementById("collegeListCombo").style.display = '';			
			}
			else if(targetSurveyYear != "-1"){
				document.getElementById("collegeListCombo").style.display = 'none';
				document.getElementById("noCollegeFound").style.display = '';
			}

		},
		datatype : 'json'
	});
}

function populateInstituteListByStateAndSourceAndTargetSurveyYear(sourceSurveyYear, targetSurveyYear, stateCode, instituteDropDownID){

	$.ajax( {

		type : "POST",
		url : "getInstituteListByStateAndSourceAndTargetSurveyYear",
		data : 'sourceSurveyYear=' + sourceSurveyYear + '&targetSurveyYear=' + targetSurveyYear + '&stateCode=' + stateCode,
		success : function(result) {

			var selectOptions = "";
			var selectStart = "<select name='selectInstituteId' id='selectInstituteId' multiple='true' size='4' cssStyle='width: 250px;height: 20px;margin: 5px 0px;' title='Use ctrl+click to select multiple institutes'>";

			for (keyValue in result) {                                 
				
				selectOptions = selectOptions + "<option value = '" + keyValue + "'>" + result[keyValue] + "</option>";
			}
			var selectEnd = "</select>";
			var finalDropDown = selectStart.concat(selectOptions).concat(selectEnd);
			$("#" + instituteDropDownID).empty();
			$("#" + instituteDropDownID).append(finalDropDown);
			
			if(selectOptions.length > 0){
				document.getElementById("noInstituteFound").style.display = 'none';
				document.getElementById("instituteListCombo").style.display = '';			
			}
			else if(targetSurveyYear != "-1"){
				document.getElementById("instituteListCombo").style.display = 'none';
				document.getElementById("noInstituteFound").style.display = '';
			}
		},
		datatype : 'json'
	});
}