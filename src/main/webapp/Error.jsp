<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   Exception e = (Exception)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error!</title>
</head>
<body>
	<p>
		Đã xảy ra sự cố khi xử lý yêu cầu của bạn.
		Vui lòng thử lại sau.
		Nếu sự cố này tiếp tục xảy ra, hãy liên hệ với nhóm vận hành hệ thống.
		Vui lòng liên hệ (số nội bộ: 8282).
	</p>
	<p><%= e.getMessage() %></p>
	
</body>
</html>