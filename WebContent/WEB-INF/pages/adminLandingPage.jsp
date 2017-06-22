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

<title>:::Create Center:::</title>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">


<script src="<s:url value='js/ajax.js'/>" type="text/javascript"></script>
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
					url : "getDistrictListForState",
					data : '&stateCode=' + stateCode,
					success : function(districtMap) {

						selectOptions = "";
						var selectStart = "<select name='districtCodeId' id='districtCodeId' class='form-control'>"
								+ "<option value='-1'>---All District---</option>";

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
						
						<div class="pull-right">
							
      							 <a href="adminUserhome"><i class="glyphicon glyphicon-home"></i> Home</a>
        
      						</div>

					</div> 
					<div class="alert alert-info text-left">Welcome Admin!
						
							<div class="pull-right">
							<span class="pull-center fields"><%= new java.util.Date() %> </span>
      							 
        
      						</div>
						</div>
					
				</div>

				
				<div class="pull-right">
					<a href="#" onclick="submitLogoutForm();" class="btn btn-primary">Logout</a>
				</div>
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
							<a
									href='<s:url value="/gotoViewCenters"><s:param name="actionType" >2</s:param></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">View Centers</font></a>&nbsp;&nbsp;|

								<a
									href='<s:url value="/gotoCreateCenter"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">Create
										Center</font></a>&nbsp;&nbsp;|
										  
								 <a
									href='<s:url value="/gotoCreateUserCredential"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">Create
										User Credential</font></a>&nbsp;&nbsp;|
										
								<a
									href='<s:url value="/gotoViewAllStudentPaymentDetails"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">View Student Payment Details</font></a>	
							</div>
							<div class="box-content">

							
						
						
						
						<br>
						<br>				

						<s:if test="#request.createCenter == 'yes'">
								
								<s:if test="#request.operationSuccuss == 'yes'">
								
									<font color="green"><strong>Center <s:property value="centerName"/>'has been created successfully!</strong></font>
									
								</s:if>
								<s:elseif test="#request.createCenter == 'no'">
								
									<font color="red"><strong>Error while creating center, please contact site admin!</strong></font>
								</s:elseif>
								
								<s:actionerror/>
								
								<form action="createCenter" method="POST">
								
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>State:</label>
												<s:select list="refStateList" name="stateId" id="stateId"
													listValue="stateName" headerKey="-1"
													headerValue="---All States---" listKey="stateId"
													onchange="populateDistrictForState(this.value) "
													cssClass="form-control" />
											</div>
										</div>



										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>District</label>
												<div id="districtCodeId">
													<s:select list="refDistrictList" name="districtCodeId"
														id="districtCodeId" cssClass="form-control"
														listValue="name" headerKey="-1"
														headerValue="---Select Districts---" listKey="districtCode" />
												</div>

											</div>
										</div>

										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Center Name</label>
												<div id="centerNameId">
													<s:textfield name="centerName" cssClass="form-control"
														placeholder="enter center name" />
												</div>

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
									
									
						<table class="table-bordered table-responsive col-lg-12"
							style="font-size: 12px;">
							<s:if test="#request.yesRecordExist == 'yesRecordExist'">
								<tr id="dirContent">
									<td align="left" colspan="0"><display:table
											name="refCenterList"
											class="display-tag-table table-bordered table-responsive"
											id="rowIndex" pagesize="${requestScope.pageSize}"
											partialList="true" size="${requestScope.totalCountOfForms}"
											requestURI="/userDirectoryList">
											
											<display:setProperty name="basic.msg.empty_list"
												value='<span class="msg">Nothing found to display</span>'>
											</display:setProperty>
											
											<display:setProperty name="paging.banner.group_size"
												value='30'></display:setProperty>
											<display:setProperty name="paging.banner.placement"
												value="both" />

											<display:column title="State Name">
												<s:property value="#attr.rowIndex.stateId.stateName" />
											</display:column>
	
											<display:column title="District Name">
												<s:property value="#attr.rowIndex.districtId.name" />
											</display:column>
											
											<display:column title="Center Name">
												<s:property value="#attr.rowIndex.name" />
											</display:column>
											
											<display:column title="Center Code">
												<s:property value="#attr.rowIndex.centerCode" />
											</display:column>										

										</display:table></td>
								</tr>


								
							</s:if>

						</table>

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
