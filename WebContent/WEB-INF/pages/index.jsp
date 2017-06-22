<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link id="bs-css" href="css/bootstrap.min.css" rel="stylesheet">
<script src="<s:url value='/js/login.algorithm.js'/>" type="text/javascript"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="row">
      <div class="col-md-12 center login-header">
        <h2>User Login</h2>
      </div>
    </div>
    <s:actionerror/>
    <s:actionmessage/>
    <div class="row">
      <div class="well col-md-5 center login-box">
        <div class="alert alert-info"> Please login with your <b>Center</b> EmailId and Password. </div>
        <form method="post" action="userlogin" onSubmit="encodeInputText();" name="loginForm" autocomplete="off">
          <fieldset>
            <div class="input-group input-group-lg"> <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
              <input type="text" class="form-control" name="userId" id="userId" placeholder="Email Id" maxlength="75">
            </div>
            <div class="clearfix"></div>
            <br>
            <div class="input-group input-group-lg"> <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
              <input type="password" class="form-control" placeholder="Password" name="password" id="password">
            </div>
            <div class="clearfix"></div>
            <p class="center col-md-5">
              <input type="submit" value="Login" class="btn btn-primary"/>              
            </p>
            <!-- <p><a href="http://aishe.nic.in/aishe/forgetPassword" target="_blank">Forgot User Id or Password?</a></p> -->
          </fieldset>
          
          <s:hidden name="rndToken" id="rndToken"></s:hidden>
		  <s:token></s:token>
        </form>
        
        <div style="margin-left: 20px; color: orange; text-align: left">
				<s:if test="#request.ActionErrors != null ">
						<c:forEach items="${request.ActionErrors}" var="current"> ${current} <br />
						</c:forEach>
				</s:if>
		</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
