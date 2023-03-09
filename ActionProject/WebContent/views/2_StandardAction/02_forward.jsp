<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>여기는 02_forward.jsp 페이지 입니다.</h1>
	
	<!-- 
		forward 특성상 url은 그대로고 화면만 바뀌었다.
		forward는 조건문과 함께 사용되는 경우가 많음
	 -->
	 <jsp:forward page="01_include.jsp"/>
	 
	<%-- <%if(true){ %>
		<jsp:forward page="01_include.jsp"/>
	<%}else{ %>
		<jsp:forward page="footer.jsp"/>
	<%} %>
	 --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>