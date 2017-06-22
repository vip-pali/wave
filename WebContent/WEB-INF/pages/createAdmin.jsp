<%@ page contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>:::Create User Credential:::</title>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">


<script src="<s:url value='js/ajax.js'/>" type="text/javascript"></script>
<script src="<s:url value='/js/login.algorithm.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="/aishe/jquery/css/smoothness/jquery-ui-1.9.2.css">
<script src="<s:url value='jquery/js/jquery-1.8.3.js' />"></script>
<script src="<s:url value='jquery/js/jquery-ui-1.9.2.js' />"></script>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/high.css" rel="alternate stylesheet" type="text/css" />
<link href="css/displaytagstyles.css" rel="stylesheet" type="text/css" />



<script type="text/javascript" src="<s:url value='/js/JSON.js'/>"></script>




<link id="bs-css" href="css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript">
	function submitLogoutForm() {

		document.getElementById('logoutform').action = 'logout';
		document.getElementById('logoutform').submit();
	}
</script>

<style>
.mandy {
	color: red;
	font-size: 15px;
	font-weight: normal;
}
</style>

<script type="text/javascript">
	function populateDistrictForState(stateCode) {

		$
				.ajax({

					type : "POST",
					url : "getDistrictList",
					data : '&stateCode=' + stateCode,
					success : function(districtMap) {

						selectOptions = "";
						var selectStart = "<select name='districtCodeId' id='districtCodeId' class='form-control' onchange= 'populateCenterForStateAndDistrict(this.value)'>"
								+ "<option value='-1'>---Select District---</option>";

						for (keyValue in districtMap) {

							var value = districtMap[keyValue];
							selectOptions = selectOptions
									+ "<option value='"+keyValue+"'>"
									+ districtMap[keyValue] + "</option>";
						}

						var selectEnd = "</select>";
						var finalDropDown = selectStart.concat(selectOptions)
								.concat(selectEnd);

						//alert(finalDropDown);

						$("#districtCodeId").empty();
						$("#districtCodeId").append(finalDropDown);

					},
					datatype : 'json'
				});

	}
</script>

<script type="text/javascript">
	function populateCenterForStateAndDistrict(districtId) {

		//alert("hi");
		var stateCode = document.getElementById('stateId').value;
		
		//alert("State id is: "+stateCode);
		//alert("District Id is: "+districtCode);
		//alert("Again district Id is: "+districtId);
		
		
		$
				.ajax({

					type : "POST",
					url : "getCenterList",
					data : '&stateCode=' + stateCode+"&districtCode="+districtId,
					success : function(districtMap) {

						selectOptions = "";
						var selectStart = "<select name='centerId' id='centerId' class='form-control'>"
								+ "<option value='-1'>---Select Center---</option>";

						for (keyValue in districtMap) {

							var value = districtMap[keyValue];
							selectOptions = selectOptions
									+ "<option value='"+keyValue+"'>"
									+ districtMap[keyValue] + "</option>";
						}

						var selectEnd = "</select>";
						var finalDropDown = selectStart.concat(selectOptions)
								.concat(selectEnd);

						//alert(finalDropDown);

						$("#centerNameId").empty();
						$("#centerNameId").append(finalDropDown);

					},
					datatype : 'json'
				});

	}
</script>

</head>

<s:form action="logout" method="get" id="logoutform" name="logoutform">
	<s:hidden name="rndToken" value="%{session.rndToken}"></s:hidden>
	<s:token></s:token>
</s:form>

<body>


	<div class="container">
		<div class="row">

			<div id="content" class="col-lg-12 col-sm-12">

				<div class="box">
					<div class="alerts text-left">
						<img alt="logo" src="images/wave_logo.jpg">						
						
					</div> 
					<div class="alert alert-info text-left">Welcome Super Admin!
						
							<div class="pull-right">
							<span class="pull-center fields"><%= new java.util.Date() %> </span>
      							 
        
      						</div>
						</div>
					
				</div>
				
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
							Create Admin Credential
							
									
							</div>
							<div class="box-content">					
						
						
						
							
						
						
						<br>
						
						
						<s:actionerror/>	
						

						<s:if test="#request.createCredential == 'yes'">
								
								<s:if test="#request.credentialAlreadyCreated == 'yes'">
								
									<font color="red"><strong>Admin has been already created!</strong></font>
									
								</s:if>
								
								<s:if test="#request.operationSuccuss == 'yes'">
								
									<font color="green"><strong>Admin crendential has been created successfully!</strong></font>
									
								</s:if>
								<s:elseif test="#request.createCenter == 'no'">
								
									<font color="red"><strong>Error while creating admin's credentail, please contact site designer!</strong></font>
								</s:elseif>
								
								
								
								<form action="createAdminCredential" method="POST" onSubmit="return changeOnlyPasswordMd5Hash();" autocomplete="off">
								
																		
									<div class="col-lg-12">
										
										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Enter email id:</label>
												<s:textfield name="adminEmailId" cssClass="form-control" placeholder="enter admin user email id" maxlength="100"></s:textfield>
											</div>
										</div>
										
										<div class="col-lg-4">
										<span class="mandy" id="invalidPassword"></span> 
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Create Password:</label>
												
												<s:password name="password" id="password" cssClass="form-control" placeholder="enter admin user password" maxlength="20"></s:password>
											</div>
										</div>
									
									</div>

									<div class="col-lg-12" id="search">

										<div class="pull-left"></div>
										<div class="pull-right">
											<button type="submit" class="btn btn-default" name="search"	value="Submit" />Submit</button>
											<button type="button" class="btn btn-default " name="Reset"	value="Reset" onClick="window.location.reload()" />
											Reset
											</button>
										</div>

									</div>
									<s:token></s:token>
									</form>
									
									
						

			</s:if>

							</div>



							<div class="clearfix"></div>

						</div>
					</div>
				</div>
			</div>

		</div>
		<hr>
	</div>





</body>
</html>
