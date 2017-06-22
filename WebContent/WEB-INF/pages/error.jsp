<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<link href="css/aises-css.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div id="main-container">	  
	   <div id="header-id" style="height: 137px;">
		 <img src="images/top-header.jpg" width="898" height="104" border="0" usemap="#Map2" />
		</div>
		<table width="100%" align="center">
		<tr>
		<td align="center" valign="top" style="padding-top: 100px;">
		<h2>Error has occurred!</h2><br/><br/>
		<%
	    	 System.out.println("In error.jsp for following exception");
	    	ValueStack vs = ActionContext.getContext().getValueStack();
	    	Exception exceptionObject = (Exception) vs.findValue("exception");
	    	
	    	if(exceptionObject != null) {
	    		
	    		exceptionObject.printStackTrace();
	    	} 
	    	 %>
		</td>
		</tr>
		<tr>
		<td align="center" valign="top"><br/><br/>
		 <a href='<s:url value="/home"/>' title="Back"><img src="images/back.gif" title="Back" border="0"/></a>
		</td>
		</tr>
		<tr>
		<td>
		<div style="height: 110px;">
		</div>
		<div class="footer">
		<div class="footer-left">
		<a href="http://www.nic.in/" target="_blank"><img src="images/nic_logo.jpg" alt="National Informatics Centre" width="28" height="28" border="0" title="National Informatics Centre" /></a>
		&copy; Copyright 2010, <a href="http://www.nic.in/" target="_blank" title="National Informatics Centre (External website opens in a new window)"  class="nic">National Informatics Centre</a>. All rights reserved.
		Content Provided and maintained by <a href="http://www.ncert.nic.in/" target="_blank" title="National Council Of Educational Research And Training (External website opens in a new window)" class="nic">NCERT</a></div>
		<div class="footer-right">	
		</div>
		</div>
		</td>
		</tr>
      </table>
    </div>
</body>
</html>