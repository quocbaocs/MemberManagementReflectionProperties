<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm thành viên</title>
</head>
<body>
	<jsp:include page="Header.jsp"/>
	<h1>Thêm thành viên</h1>
	<table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="2"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">Registration</font></td>
     </tr>
     <form method="post" action="add.html">
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Username*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="userName" maxlength="20" value="" size="20"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Password*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="password"  name="password" maxlength="20" value="" size="20"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Confirm Password*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="password"  name="member_password2" maxlength="20" value="" size="20"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">First Name*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="firstName" maxlength="50" value="" size="50"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Last Name*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="lastName" maxlength="50" value="" size="50"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Email*</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="email" maxlength="50" value="" size="50"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Address</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="address" maxlength="50" value="" size="50"></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Phone</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="phone" maxlength="50" value="" size="50"></td>
     </tr>
     
     <tr>
      <td colspan="2" align="right"><input type="submit" value="Register"></td>
     </tr>
     </form>
    </table>
	<jsp:include page="Tail.jsp"/>
</body>
</html>