package com.kh.ajaxController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//ajax는 결과를 딱 한 개만 응답할 수 있음
		//하나의 문자열, 하나의 html페이지 등등 
		//요청처리를 다 했다는 가정 하에 응답할 데이터를 "문자열" 형태로 셋팅
		
		//버전1)
		/*
		 * String responseData = "이름 : "+name+", 나이: "+age;
		 * response.setContentType("text/html; charset=UTF-8");
		 * response.getWriter().print(responseData);
		 */
		
		
		//버전2) 응답데이터 여러개 보내고 싶을때, "JSON"형태로 셋팅
		/*
		 * JSON(JavaScript Object Notation :  자바 스크립트의 객체 표기법)
		 * -ajax통신시 데이터 전송에 사용되는 포맷 중 하나
		 * 
		 * -JSON처리시 사용되는 클래스 종류 
		 *  => 기본적으로 자바에서 제공하지 않기 때문에 별도의 라이브러리가 필요함 
		 *  => 라이브러리 : json-simple-x.x.x.jar 다운로드 후 WEB-INF/lib폴더에 복사해줘야 함
		 *  
		 *  1. JSONArray[value, vlaue, vlaue,,,,,] : 배열형태로 value값만 들어가 있음
		 *  2. JSONObject{key:value, key:value...} : 객체형태로 key:value값 들어가있음
		 *  
		 * 
		 */
		
		/*
		 * JSONArray jArr = new JSONArray(); //[] jArr.add(name); //["홍길동"]
		 * jArr.add(age); //["40"]
		 * 
		 * //응답할 데이터의 contentType을 설정. text/html -> 문자열
		 * response.setContentType("apllication/json; charset=UTF-8");
		 * 
		 * response.getWriter().print(jArr);
		 */
		
		
		
		//JSONObject로 객체 전달 해주기
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("name", name); // {name : "예진"}
		jobj.put("age", age); //{name : "예진", age : "29"}
		
		response.setContentType("apllication/json; charset=UTF-8");
		
		response.getWriter().print(jobj);
		
		
		
	}

}
