<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>

<title>Different View</title>

</head>

<body style="margin: 0px; padding: 0px; font-family: 'Trebuchet MS',verdana;">

<table width="100%" style="height: 100%;" cellpadding="10" cellspacing="0" border="0">
<tr>

<!-- ============ HEADER SECTION ============== -->
<td colspan="2" style="height: 100px;" bgcolor="#777d6a"><h1>Other View</h1></td></tr>

<tr>
<!-- ============ LEFT COLUMN (MENU) ============== -->
<td width="20%" valign="top" bgcolor="#999f8e">
</td>

<!-- ============ RIGHT COLUMN (CONTENT) ============== -->
<td width="80%" valign="top" bgcolor="#d2d8c7">

<h1>User Information</h2>
<jsp:useBean id="bean" class="market.UserBean" scope="session" />
<h2>Username</h1>
<p><jsp:getProperty name="bean" property="username"/></p>
<h2>First Name</h2>
<p><jsp:getProperty name="bean" property="firstName"/></p>
<h2>Last Name</h2>
<p><jsp:getProperty name="bean" property="lastName"/></p>
<h2>Email</h2>
<p><jsp:getProperty name="bean" property="email"/></p>
<h2>Password</h2>
<p><jsp:getProperty name="bean" property="password"/></p>
<h2>Role</h2>
<p><jsp:getProperty name="bean" property="role"/></p>
<h2>Balance</h2>
<p><jsp:getProperty name="bean" property="balance"/></p>

<br>
<br>

</body>

</html>
