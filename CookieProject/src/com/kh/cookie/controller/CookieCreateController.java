package com.kh.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieCreateController
 */
@WebServlet("/cookieTest.do")
public class CookieCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieCreateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	
		//쿠키 생성하기
		Cookie c = new Cookie("userId","admin");
		//쿠키 객체 생성시 저장할 key, value값을 생성자의 매개변수로 넣음
		
		c.setMaxAge(24*60*60); //만들어지고 나서 하루 동안 유지되도록 유지시간 설정
		
		//생성된쿠키를 클라이언트에게 전달
		response.addCookie(c);
		
		Cookie c2 = new Cookie("email","gmald@naver.com");
		c2.setMaxAge(60); //60초만 유지
		response.addCookie(c2);
		
		//view지정
		request.getRequestDispatcher("views/responsePage.jsp").forward(request, response);
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
