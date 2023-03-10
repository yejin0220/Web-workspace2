<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.model.vo.Person"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Functions Library</h1>
	
	<c:set var="str" value="How are you?"/>
	str : ${ str }<br>
	문자열의 길이 ? : ${ fn:length(str)  } 글자입니다. <br>
	문자열의 길이 ? : ${ str.length() } 글자입니다.    <br>
	
	<%
	ArrayList<Person> list = new ArrayList<>();
	list.add(new Person("김예진","여","학생"));
	list.add(new Person("예진","여","무직"));
	list.add(new Person("메진","여","백수"));
	
	request.setAttribute("list", list);
	%>
	배열의 길이 :  ${list.size() } <br>
	
	문자열을 대문자로 출력 : ${ fn:toUpperCase(str) } <br>
	문자열을 모두 소문자로 출력 : ${fn:toLowerCase(str) } <br>
	
	are의 시작인덱스 : ${fn:indexOf(str, 'are') }<br>
	문자열을 변환 : ${ fn:replace(str, "are", "were") }<br> <!-- 원본배열에 영향x -->
	원본 str : ${str }<br>
	
	str에 "are"이라는 문자열이 포함되어있나? : ${ fn:contains(str,"are") ? '포함되어있습니당' : '없습니당' }<br>

	문자열 앞, 뒤 공백 제거 : ${ fn:trim(str) } <br>
	문자열 잘라주기 : ${fn:substring(str,0,4) } <br>
	문자열 잘라주기2 : ${fn:substringAfter(str,"are")} <br>
	문자열 나누기 : 
	<c:forEach var="s" items="${fn:split(str,' ') }"> <!-- 공백기준으로 문자열 나눠서 s에 담기 -->
		${s } <br>
	</c:forEach>
	
	배열 합치기 : ${ fn:join(fn:split(str,' '), ',') } <br>
	
	<c:set var="htmlStr" value="<p>hi</p>"/>
	일반적으로 출력 : ${htmlStr }	<br>
	태그를 문자열로 바꿔주는 함수 : ${fn:escapeXml(htmlStr)}
	












































</body>
</html>