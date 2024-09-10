<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<style type="text/css">
.container {
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
}
</style>
</head>
<body style="align-content: center;">

<div class="container">
	<h2>Form Login</h2>
	<form action="login.html" method="post">

		<table border=0>
			<tr>
				<td colspan=2
					style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1 colspan=2><font style=font-size: 12pt; color:#FFFFFF; font-weight:bold">Enter
					login and password</font>
				</td>
			</tr>

			<tr>
				<td colspan=2 style="background-color: #FFFFFF; border-width: 1"><font
					style="font-size: 10pt; color: #FF0000"></font></td>
			</tr>


			<tr>
				<td
					style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font
					style="font-size: 10pt; color: #000000">Login</font></td>
				<td style="background-color: #FFFFFF;"border-width: 1"><input
					type=text name=email maxlength=50></td>
			</tr>
			<tr>
				<td
					style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font
					style="font-size: 10pt; color: #000000">Password</font></td>
				<td style="background-color: #FFFFFF;"border-width: 1"><input
					type=password name=password maxlength=50></td>
			</tr>
			<tr align="center">
				<td colspan=2>
					<input type=submit value=Login></td>
			</tr>

		</table>
				<p>guest/guest<br> admin/admin</p>
		

	</form>
</div>
</body>
</html>