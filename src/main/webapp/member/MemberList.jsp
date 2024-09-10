<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- import java dùng phương thức trong Class-->
<%@ page import="com.store.model.Member" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách thành viên</title>

</head>
<body>
<div>
<jsp:include page="Header.jsp"/>
	<h1>Danh sách thành viên</h1>
	<p><a href='add.html'>Thêm thành viên</a></p>
	<table border="1" style="border-collapse: collapse; width: 800px">
	<tr>
		<th>ID</th>
		<th>User Name</th>
			<th>Password</th>
		<th>First Name</th>
			<th>Last Name</th>
		<th>Email</th>
			<th>Phone</th>
		<th>Address</th>
			<th>Create Date</th>
		<th>Action</th>
	
	</tr>
	<c:forEach var="member" items="${members}" >
		<tr>
			<td> ${member.id}</td>
			<td><a href="update.html?id=${member.id}">${member.userName}</a></td>
			<td>${member.password}</td>
			<td>${member.firstName}</td>
			<td>${member.lastName}</td>
			<td>${member.email}</td>
			<td>${member.phone}</td>
			<td>${member.address}</td>
			<td>${member.createdDate}</td>
			<td>
				<a href="delete.html?id=${member.id}">[Delete]</a>
				<a href="update.html?id=${member.id}">[Update]</a>
			</td>
		</tr>
	</c:forEach>
		</table>
		<br/>
	<jsp:include page="Tail.jsp"/>
	</div>
</body>
</html>
