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

<script type="text/javascript">
	
	function editStudentPaymentDetails(id){
		
		//alert("id is: "+id);
		var action = 'editStudentPaymentDetails?studentPaymentId=' + id;
		
		document.getElementById('frmEdit').action = action;
		document.getElementById('frmEdit').submit();
	}
	
</script>


<script type="text/javascript">
	
	function generateReceipt(id){
		
		var courseId = document.getElementById('courseId').value;
		//alert("course id is: "+courseId);
		var action = 'studentPaymentReceipt?studentPaymentId=' + id+'&courseId='+courseId;
		
		document.getElementById('frmReport').action = action;
		document.getElementById('frmReport').submit();
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

	function validate() {
	
	if(document.getElementById('courseId').value == '-1'){
		
		alert("Please select course");
		return false;
	}
	
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
									href='<s:url value="/gotoViewCenters"></s:url>'><font
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
						
						<s:actionerror/>	
						<br>		
						
										
								
								<form action="viewAllStudentPaymentDetailsList" method="POST" id="frmEdit">
								
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Select Course:</label>
												
												<s:select list="refCourseList" name="courseId" id="courseId"
													listValue="name" headerKey="-1"
													headerValue="---Select Course---" listKey="id"													
													cssClass="form-control" />
											</div>
										</div>
									</div>
										



										

									<div class="col-lg-12" id="search">

										<div class="pull-left"></div>
										<div class="pull-right">
											<button type="submit" class="btn btn-default" name="search"	value="Submit" onclick="return validate()"/>Submit</button>
											<button type="button" class="btn btn-default " name="Reset"	value="Reset" onClick="window.location.reload()" />Reset</button>
										</div>

									</div>
									<s:token></s:token>
						</form>
									
					<form action="" method="POST" id="frmReport">				
									
						<table class="table-bordered table-responsive col-lg-12"
							style="font-size: 12px;">
							<s:if test="#request.yesRecordExist == 'yesRecordExist'">
								<tr id="dirContent">
									<td align="left" colspan="0"><display:table
											name="studentPaymentDetailsList"
											class="display-tag-table table-bordered table-responsive"
											id="rowIndex" pagesize="${requestScope.pageSize}"
											partialList="true" size="${requestScope.totalCountOfForms}"
											requestURI="/viewStudentPaymentDetails">
											
											<display:setProperty name="basic.msg.empty_list"
												value='<span class="msg">Nothing found to display</span>'>
											</display:setProperty>
											
											<display:setProperty name="paging.banner.group_size"
												value='20'></display:setProperty>
											<display:setProperty name="paging.banner.placement"
												value="both" />

											<display:column title="Candidate Name">
												<s:property value="#attr.rowIndex.candidateName" />
											</display:column>
	
											<display:column title="Parent Name">
												<s:property value="#attr.rowIndex.parentName" />
											</display:column>
											
											<display:column title="Address">
												<s:property value="#attr.rowIndex.address" />
											</display:column>
											
											<display:column title="Total Course Fee">
												<s:property value="#attr.rowIndex.totalCourseFees" />
											</display:column>										
											
											<display:column title="Amount Received">
												<s:property value="#attr.rowIndex.amountRecieved" />
											</display:column>
											
											<display:column title="Balance Amount">
												<s:property value="#attr.rowIndex.balanceAmount" />																						
											</display:column>
											
											<display:column title="Edit">
												<img src='<s:url value="/images/edit.png"/>' style="cursor: pointer;" title="Edit candidate record" onclick='javascript:editStudentPaymentDetails("${attr.rowIndex.id}")'/>																					
											</display:column>
											
											<display:column title="Receipt">
												<img src='<s:url value="/images/view.png"/>' style="cursor: pointer;" title="Generate Receipt" onclick='javascript:generateReceipt("${attr.rowIndex.id}")'/>																					
											</display:column>
											
											
									<%-- 		
											
											 <s:if test='<s:property value="#attr.rowIndex.paymentMode == '1'  " />'>
											
												<display:column title="Payment Mode">
													Cash
												</display:column>									
											
											</s:if>
											<s:elseif test='<s:property value="#attr.rowIndex.paymentMode == '2'  " />'>>
												
												<display:column title="Payment Mode ">
													A/C Payee Cheque
												</display:column>
												
												<display:column title="Bank Details">
													<s:property value="#attr.rowIndex.bankDetails" />	
												</display:column>
												
												<display:column title="Cheque/DD No">
													<s:property value="#attr.rowIndex.chequeDDNumber" />	
												</display:column>
												
												<display:column title="Dated">
													<s:property value="#attr.rowIndex.chequeDated" />	
												</display:column>
												
												<display:column title="Name of Authorised Signatory">
													<s:property value="#attr.rowIndex.nameOfAuthSign" />	
												</display:column>
											
											</s:elseif>
											<s:else>
												
												<display:column title="Payment Mode ">
													A/C Payee Demand Draft
												</display:column>
												
												<display:column title="Bank Details">
													<s:property value="#attr.rowIndex.bankDetails" />	
												</display:column>
												
												<display:column title="Cheque/DD No">
													<s:property value="#attr.rowIndex.chequeDDNumber" />	
												</display:column>
												
												<display:column title="Dated">
													<s:property value="#attr.rowIndex.chequeDated" />	
												</display:column>
												
												<display:column title="Name of Authorised Signatory">
													<s:property value="#attr.rowIndex.nameOfAuthSign" />	
												</display:column>
											
											</s:else>
											
											 --%>

										</display:table></td>
								</tr>


								
							</s:if>

						</table> 
						<s:token></s:token>
						</form>

			

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
