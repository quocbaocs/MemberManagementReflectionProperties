<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<div style="background-color:#336699; color:white; 
		height:20px; padding:5px; ">
 Project Management Member System

<c:if test="${!empty sessionScope.member and 
			  !empty sessionScope.member.email}">
	<span style="float:right;">
		${sessionScope.member.userName}
		<a style="color:white;"
			href="<%=request.getContextPath()%>/auth/logout.html">
			Logout
		</a>
	</span>
</c:if>
</div>
