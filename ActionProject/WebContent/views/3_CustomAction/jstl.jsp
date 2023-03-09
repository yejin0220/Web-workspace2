<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL 이란?</h1>
	<p>
		JSP Standard Tag Library의 약자로 JSP에서 사용되는 커스텀 액션 태그로 <br>
		공통적으로 사용되는 코드들의 집합을 보다 쉽게 사용할 수 있도록 태그화 해서 표준으로 제공하는 라이브러리
		(아파치 톰캣에서 배포)
	</p>
	
	<hr>
	
	<h3>*라이브러리 다운로드 후 추가하는 방법</h3>
	1) http://tomcat/apache.org/download-taglibs.cgi 접속<br>
	2) Standard-1.2.5 Jar Files 4개 다운로드<br>
	3) C드라이브 안의 DEV 폴더로 4개의 jar파일 추가하기 <br>
	4) WEB-INF/lib폴더에 추가하기
	
	<h3>*JSTL 선언 방법</h3>
	<p>	
		JSTL을 사용하고자 하는 해당 JSP 페이지 상단에 <br>
		taglib지시어를 사용해서 선언 <br><br>
		
		&lt;%@ taglib prefix="접두어" uri="라이브러리 파일상의 url주소"%&gt;
	</p>
	
	<h3>*JSTL 분류</h3>
	<h4>1. JSTL Core Library</h4>
	<p>
		변수의 선언, 조건문, 반복문과 같은 로직과 관련된 문법을 제공
		
	
	</p>
	
	<a href="01_core.jsp">core.jsp</a>	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>