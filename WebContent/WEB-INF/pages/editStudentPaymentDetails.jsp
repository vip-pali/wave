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

<title>:::Student Payment Details:::</title>


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
  function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	    	
	}
  
  function enterBankDetails(val){
	  
	//  alert("Bank Details option is: "+val);	
	
	 
	  
	if(val == '2' || val == '3'){
		  
		  document.getElementById('bankDetails').disabled = false;
		  document.getElementById('chequeDDNumber').disabled = false;
		  document.getElementById('chequeDated').disabled = false;
		  document.getElementById('nameOfAuthSign').disabled = false;
	  
	  }else{
		  
		  document.getElementById('bankDetails').value = '';
		  document.getElementById('chequeDDNumber').value = '';
		  document.getElementById('chequeDated').value = '';
		  document.getElementById('nameOfAuthSign').value = '';
		  
		  document.getElementById('bankDetails').disabled = true;
		  document.getElementById('chequeDDNumber').disabled = true;
		  document.getElementById('chequeDated').disabled = true;
		  document.getElementById('nameOfAuthSign').disabled = true;
		 
		  
	  }  
	  
  }
  
  
  function pageLoad(){
	  
	  var option = <%= request.getAttribute("optionValue")%>;
	  //alert("option is: "+option);
	  
	  if(option == 1){
		  
		  document.getElementById('paymentMode1').checked = true;
	  
	  }else if(option == 2){
		  
		  document.getElementById('paymentMode2').checked = true;
	  
	  }else if(option == 3){
		  
		  document.getElementById('paymentMode3').checked = true;
	  }
  }
  
	function validate(){	
		
		var option1 = document.getElementById('paymentMode1').checked;
		var option2 = document.getElementById('paymentMode2').checked;
		var option3 = document.getElementById('paymentMode3').checked;		
		
		 if(document.getElementById('courseId').value == '-1'){
			alert("Please select course");			
			return false;
		} 
		
		if(document.getElementById('candidateName').value == ''){
			alert("Please enter candidate name");
			document.getElementById('candidateName').focus();
			return false;
		}
		
		if(document.getElementById('parentName').value == ''){
			alert("Please enter parent name");
			document.getElementById('parentName').focus();
			return false;
		}
		
		if(document.getElementById('address').value == ''){
			alert("Please enter address");
			document.getElementById('address').focus();
			return false;
		}
		
		if(document.getElementById('totalCourseFees').value == ''){
			alert("Please enter total course fees");
			document.getElementById('totalCourseFees').focus();
			return false;
		}
		
		if(document.getElementById('amountRecieved').value == ''){
			alert("Please enter recieved amount");
			document.getElementById('amountRecieved').focus();
			return false;
		}
		
		
		 
		var totalfee = document.getElementById('totalCourseFees').value;
		var amtRec = document.getElementById('amountRecieved').value;
		
		if(totalfee < amtRec){
			
			alert("Received amount can not be greater than total amount");
			document.getElementById('amountRecieved').value = '';
			document.getElementById('balanceAmount').value = '';
			document.getElementById('amountRecieved').focus();
			
			return false;
		}
		
		if(option2 == true || option3 == true){
			
			//alert("mmmm");
			
			if(document.getElementById('bankDetails').value == ''){
				alert("Please enter bank details");
				document.getElementById('bankDetails').focus();
				return false;
			}
			
			if(document.getElementById('chequeDDNumber').value == ''){
				alert("Please enter cheque/DD number");
				document.getElementById('chequeDDNumber').focus();
				return false;
			}
			
			if(document.getElementById('chequeDated').value == ''){
				alert("Please enter cheque/DD date");
				document.getElementById('chequeDated').focus();
				return false;
			}
			
			if(document.getElementById('nameOfAuthSign').value == ''){
				alert("Please enter name of authorised signatory");
				document.getElementById('nameOfAuthSign').focus();
				return false;
			}
		}
		 
		 
		
	}

	function countBalanceAmt() {
		
		//checkInt();
		$('#balanceAmount').val(checkNan($('#totalCourseFees').val()) - checkNan($('#amountRecieved').val()));

	}
	
	
	
	function checkInt() {
	
		
		$(".changeInt").each(function() {
			if ((!isNaN(parseInt($(this).val())))) {
				$(this).val(parseInt($(this).val()));
			} else {
				$(this).val('');
			}
		});
	}
	
	function checkNan(val) {
		
		return (!isNaN(parseInt(val))) ? parseInt(val) : 0;
	}
	
	

	
  </script>
  
  <script>
		$(document).ready(function() {
		
			$('.datepick').datepicker({  				
				dateFormat: 'dd-mm-yy'
			});
			
			//populateMaxDate();
		});
		
		/* function populateMaxDate() {
			var todayDate = new Date();	
			$('#startDate').datepicker("option", "maxDate",todayDate);
			$('#endDate').datepicker("option", "maxDate",todayDate);
		} */
	</script>	


</head>

<s:form action="logout" method="get" id="logoutform" name="logoutform">
	<s:hidden name="rndToken" value="%{session.rndToken}"></s:hidden>
	<s:token></s:token>
</s:form>

<body onload="pageLoad();">


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
					<div class="alert alert-info text-left">Welcome Admin!
						
							<div class="pull-right">
							<span class="pull-center fields"><%= new java.util.Date() %> </span>
      							 
        
      						</div>
						</div>
					
				</div>

			<div class="pull-center">
				<s:if test="#request.studentPaymentDetaildSavedSuccessfully == 'yes'">
								
						<font color="green"><strong>Payment details has been saved successfully!</strong></font>
									
				</s:if>
				<s:elseif test="#request.studentPaymentDetaildSavedSuccessfully == 'no'">
								
						<font color="red"><strong>Error while saving student payment details, please contact site admin!</strong></font>
				</s:elseif>
								
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
							
						<br>		
						
						<div class="pull-center">
							<s:if test="#request.studentPaymentDetaildEditedSuccessfully == 'yes'">
								
								<font color="green"><strong>Student payment details has been edited successfully!</strong></font>
									
							</s:if>
							<s:elseif test="#request.studentPaymentDetaildEditedSuccessfully == 'no'">
								
								<font color="red"><strong>Error while editing student payment details, please contact site admin!</strong></font>
							</s:elseif>
								
						</div>
							
							<form action="saveEditedStudentPaymentDetails" method="POST" autocomplete="off">
							<s:hidden name="studentPaymentId" value="%{studentPaymentDetails.id}"/>
							<s:hidden name="studentPaymentDetails.id" value="%{studentPaymentDetails.id}"/>
							<%-- <s:hidden name="refCenter" value='<s:property value="refCenterId" '/>  --%>
							 
								
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Course Name</label>
												<s:select list="refCourseList"
													name="studentPaymentDetails.courseId.id" id="courseId"
													listValue="name" headerKey="-1"
													headerValue="---Select Course---" listKey="id"
													cssClass="form-control" />
											</div>
										</div>



										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Candidate Name</label>
												<div id="districtCodeId">
													<s:textfield id="candidateName"
													name="studentPaymentDetails.candidateName"
													placeholder="enter candidate name" maxlength="100" cssClass="form-control"/>
												</div>

											</div>
										</div>

										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Father's/Mother's Name</label>
												<s:textfield id="parentName" name="studentPaymentDetails.parentName" placeholder="enter parent name" maxlength="100" cssClass="form-control"/>

											</div>
										</div>

									</div>
									
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Address</label>
												<s:textarea id="address" name="studentPaymentDetails.address" placeholder="address" cssClass="form-control"/>
											</div>
										</div>



										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Total Course Fee</label>
												<s:textfield id="totalCourseFees" Class="changeInt" name="studentPaymentDetails.totalCourseFees" placeholder="enter total course fee" maxlength="6" onchange="javascript:countBalanceAmt();" onkeypress="return isNumber(event)" cssClass="form-control"/>

											</div>
										</div>

										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Amount Received</label>
												<s:textfield id="amountRecieved" Class="changeInt" name="studentPaymentDetails.amountRecieved" placeholder="enter amount received" maxlength="6" onchange="javascript:countBalanceAmt();" onkeypress="return isNumber(event)" cssClass="form-control"/>

											</div>
										</div>

									</div>
									
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Balance Amount</label>
												<s:textfield id="balanceAmount" name="studentPaymentDetails.balanceAmount" readonly="true" cssClass="form-control"/>
											</div>
										</div>



										<%-- <div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Payment Mode</label><br>
												<s:radio id="paymentMode" name="studentPaymentDetails.paymentMode" list="#{'1':'Cash','2':'A/C Payee Cheque','3':'A/C Payee Demand Draft'}" value="1" onclick="enterBankDetails(this.value)"/>

											</div>
										</div> --%>
										
										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Payment Mode</label><br>
												<%-- <s:radio id="paymentMode" name="studentPaymentDetails.paymentMode" list="#{'1':'Cash','2':'A/C Payee Cheque','3':'A/C Payee Demand Draft'}" value="1" onclick="enterBankDetails(this.value)"/> --%>
												<input type="radio" id="paymentMode1" name="studentPaymentDetails.paymentMode" value="1" checked onclick="enterBankDetails(1)"><strong>Cash</strong>
												<input type="radio" id="paymentMode2" name="studentPaymentDetails.paymentMode" value="2" onclick="enterBankDetails(2)"><strong>A/C Payee Cheque</strong>
												<input type="radio" id="paymentMode3" name="studentPaymentDetails.paymentMode" value="3" onclick="enterBankDetails(3)"><strong>A/C Payee Demand Draft</strong>
												

											</div>
										</div>

										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Bank Name with Branch & City</label>
												<s:textfield id="bankDetails" name="studentPaymentDetails.bankDetails" placeholder="enter bank name, branch and city" disabled="true" cssClass="form-control"/>

											</div>
										</div>

									</div>
									
									<div class="col-lg-12">

										<div class="col-lg-4">
											<div class="form-group text-left">

												<label for="exampleInputName2"><span class="mandy">*</span>Cheque/DD Number</label>
												<s:textfield id="chequeDDNumber" name="studentPaymentDetails.chequeDDNumber" placeholder="enter cheque or DD number" disabled="true" onkeypress="return isNumber(event)" cssClass="form-control"/>
											</div>
										</div>



										<%-- <div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Dated</label>
												<s:textfield id="chequeDated" name="studentPaymentDetails.chequeDated" placeholder="enter cheque or DD date" disabled="true" cssClass="form-control"/>

											</div>
										</div> --%>
										
										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Dated</label>
												<%-- <s:textfield id="chequeDated" name="studentPaymentDetails.chequeDated" 
												placeholder="enter cheque or DD date" disabled="true" cssClass="form-control"/> --%>
												<s:textfield id="chequeDated" name="studentPaymentDetails.chequeDated" disabled="true"
													cssClass="datepick form-control" />

											</div>
										</div>

										<div class="col-lg-4">
											<div class="form-group text-left">
												<label for="exampleInputName2"><span class="mandy">*</span>Name of Authorised Signatory</label>
												<s:textfield id="nameOfAuthSign" name="studentPaymentDetails.nameOfAuthSign" placeholder="enter name of authorised signatory" disabled="true" cssClass="form-control" />

											</div>
										</div>

									</div>

									<div class="col-lg-12" id="search">

										<div class="pull-left"></div>
										<div class="pull-right">
											<button type="submit" class="btn btn-default" name="search"	value="Edit" onclick="return validate()"/>Edit</button>
											<button type="button" class="btn btn-default " name="Reset"	value="Reset" onClick="window.location.reload()" />
											Reset
											</button>
										</div>

									</div>
									<s:token></s:token>
									</form>
						
						
						
						
						
								

						
									<!-- <div class="col-lg-12" id="search">

										<div class="pull-left"></div>
										<div class="pull-right">
											<button type="submit" class="btn btn-default" name="search"	value="Submit" />Submit</button>
											<button type="button" class="btn btn-default " name="Reset"	value="Reset" onClick="window.location.reload()" />
											Reset
											</button>
										</div>

									</div> -->
									
									
			

			

							



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
