<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.SimpleDateFormat, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String year = new SimpleDateFormat("yyyy").format(new Date());
		//2023
		
	%>
	
	Copyright 1998-<%=year %> KH Information Educational Institude All Right Reserved
	
	<br>
	
	include 페이지에서 넘긴 파라미터 : ${param.test}

</body>
</html>