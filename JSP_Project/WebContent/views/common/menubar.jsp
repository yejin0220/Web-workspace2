<%@ page import="com.kh.member.model.vo.Member" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String contextPath = request.getContextPath();

	Member loginUser = (Member)session.getAttribute("loginUser");
	//loginUser -> 로그인 전 : null값이 담김
	//			-> 로그인 후 : 로그인한 회원의 Member객체
	
	String alertMsg = (String)session.getAttribute("alertMsg");
	//alertMsg -> 모든 서비스 요청 전 : null값이 담김
	//		   -> 모든 서비스 요청 후 : alert으로 띄워줄 메세지 문구 ex)로그인에 성공하ㅅ였습니ㅏㄷ. 실패하였습니다..
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome C Class</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>

	#login-form, #user-info{float:right;}
	#user-info a{
		text-decoration:none;
		color:black;
		font-size:12px;
	}
	
	.nav-area{background:black;}
	.menu{
		display:table-cell; /*인라인 요소처럼 배치 가능*/
		height:50px;
		width:150px;
	}
	.menu a{
		text-decoration:none;
		color:white;
		font-size:20px;
		font-weight:bold;
		display:block;
		width : 100%;
		height:100%;
		
		line-height:50px; /*위 아래에서 가운데로 조정*/
	}
	.menu a:hover{
		background:darkgray;	
	}
</style>
</head>
<body>
	<script>
		const msg = "<%= alertMsg %>";
		
		if(msg != "null"){ //"성공적으로 로그인이 되었습니다." or "null"
			alert(msg);
			//알림창을 띄워준 후 session에 담긴 메세지는 지워줘야함 -> 안그러면 menubar.jsp가 로딩 될때마다 alert함수가 실행됨
			//알림창이 한번 띄워진 뒤에 새로고침이나 다른 페이지로 넘어가도 다시 안뜨게 끔
			<% session.removeAttribute("alertMsg"); %> 
		}
	

	</script>
	
	

	<h1 align="center">Welcome C Class</h1>
	
	<div class="login-area">
		
		<% if(loginUser == null){ %>
		<!-- 로그인 하기 이전에만 보여지는 로그인 form -->
		<form id="login-form" action="<%= request.getContextPath() %>/login.me" method="post">
			<table>
				<tr>
					<th> 아이디 : </th>
					<td><input type="text" name="userId" required></td>
				</tr>
				<tr>
					<th> 비밀번호 : </th>
					<td><input type="password" name="userPwd" required></td>
				</tr>
				<tr>
					<th colspan="2">
						<button>로그인</button>
						<button type="button" onclick="enrollPage();">회원가입</button>
					</th>
				</tr>
			</table>
		</form>	
		<script>
			function enrollPage(){
				<%--//location.href =  "<%= contextPath %>/views/member/memberEnrollForm.jsp"; 했을때 이동은 가능--%>
				//웹애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약
				
				//단순한 정적인 페이지 이동요청이라고 해도 반드시 servlet을거쳐 갈것 => url에 survlet 매핑값만 노출되도록 하기
				//아래의 location.href은 a태그와 같은 형태로 이동 -> get방식
				location.href = "<%= contextPath %>/enrollForm.me";
				
			}
		
		
		</script>
		<% } else{ %>
			<!-- 로그인 성공 후 보여질 화면 -->
			<div id="user-info">
			
				<b><%= loginUser.getUserName() %></b>님 환영합니다. <br><br>
				<div align="center">
					<a href="<%= contextPath %>/myPage.me">마이페이지</a>
					<a href="<%= contextPath %>/logout.me">로그아웃</a> <!-- a 태그는 무조건 get방식 -->
				</div>
			</div>
		<%} %>
	</div>
	
	<br clear="both"> <!-- float 속성 해제 -->
	<br>
	
	<div class="nav-area" align="center">
	
		<div class="menu"><a href="<%= contextPath %>/">HOME</a></div>
		<div class="menu"><a href="<%= contextPath %>/">공지사항</a></div>
		<div class="menu"><a href="<%= contextPath %>/">일반게시판</a></div>
		<div class="menu"><a href="<%= contextPath %>/">사진게시판</a></div>
n
	</div>















</body>
</html>