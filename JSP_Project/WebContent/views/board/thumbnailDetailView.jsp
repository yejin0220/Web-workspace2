<%@ page import="java.util.ArrayList, com.kh.borard.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

   Board b = (Board) request.getAttribute("b");

   ArrayList<Attachment> list = (ArrayList<Attachment>) request.getAttribute("list");
   
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <%@ include file="../common/menubar.jsp" %>
   
   <div class="outer">
      <br> <h2 style="text-align:center">사진게시판 상세보기</h2>
      
      <table class="detail-area " align="center">
         <tr>      
            <td width="70">제목</td>
            <td colspan="3" width="600"></td>
         </tr>
         <tr>
            <td>작성자</td>
            <td></td>
            <td>작성일</td>
            <td></td>   
         </tr>
         <tr>
            <td>내용</td>
            <td colspan="3">
               <p style="height:50px"></p>
            </td>         
         </tr>
         <tr>
            <td>대표사진</td>
            <td colspan="3">
               <img src=""width="500" height="300">
            </td>         
         </tr>
         <tr>
            <td>상세사진</td>
            <td colspan="3">
               <img src="" width="200" height="150">
               <img src="" width="200" height="150">
               <img src="" width="200" height="150">
            </td>
         </tr>
      
      </table>
   
   </div>

















</body>
</html>