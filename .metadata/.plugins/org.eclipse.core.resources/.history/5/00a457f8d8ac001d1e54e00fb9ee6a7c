package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그아웃 요청 처리 => 연동되어 있던 loginUser 세션 정보를 만료시키기(세션 무효화)
		//request.getSession().removeAttribute("loginUser");
		request.getSession().invalidate();     //invalidate() : 세션 영역에 있는 모든 데이터를 모두 날리는 함수
		
		//응답페이지 => /jspproject
		//url재요청 방식
		response.sendRedirect(request.getContextPath());  // => /jspproject
		//=> 로그아웃을 하면 loginUser 가 null이 되므로 다시 로그인을 요청할 수 있음
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
