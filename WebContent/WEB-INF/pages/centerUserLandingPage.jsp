<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>


<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link id="bs-css" href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
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





</script>

</head>

<s:form action="logout" method="get" id="logoutform" name="logoutform">
	<s:hidden name="rndToken" value="%{session.rndToken}"></s:hidden>
	<s:token></s:token>
</s:form>


<body onload="enterBankDetails()">
	<div class="container">
		<div class="row">
			<div id="content" class="col-lg-12 col-sm-12">
				<div class="box">
					<div class="alerts text-left">
						<img alt="logo" src="images/wave_logo.jpg">
						
						<div class="pull-right">
							
      							 <a href="centerUserhome"><i class="glyphicon glyphicon-home"></i> Home</a>
        
      						</div>

					</div> 
					<div class="alert alert-info text-left">Welcome, <strong><font color="blue">Center Code:</font></strong>&nbsp;<font color="red"><s:property value="generatedCenterId"/></font>
						
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
									href='<s:url value="/gotostudentPaymentDetailsPage"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">Enter student payment details</font></a>

								<%-- <a
									href='<s:url value="/gotoViewStudentDetails"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">View student details</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;   <a
									href='<s:url value="/gotoEditDeleteStudentDetails"></s:url>'><font
									color="#324C6E" style="font-size: 14px; font-weight: normal">Edit/Delete student payment details</font></a> --%>

								</div>
								<div class="box-content">
									<span class="mandy"> <s:actionerror />
									</span>

								</div>
							</div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
