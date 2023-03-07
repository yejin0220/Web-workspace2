package com.kh.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCookieController
 */
@WebServlet("/checkCookie.do")
public class CheckCookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCookieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//client가 보낸 cookie값을 확인
		//cookie값은 request객체 안에 담겨 있고, getCookies()메소드를 이용하여 꺼내올 수 있다.
		//session값도 request객체 안에 담겨있고, getSession()메소드를 이용하여 꺼내왔음
		//getCookies()의 반환형은 cookie[]임(서버에 저장되는 쿠키값이 한개가 아니기 때문에)
		
		Cookie[] cookies = request.getCookies();
		//저장된 쿠키가 없을 경우 null값이 전달됨
		
		if(cookies != null) {
			
			for(Cookie c : cookies) {
				//Cookie값을 확인하는 메소드 : getName(), getVlaue()
				System.out.println("키값 : "+c.getName()+", vlaue값 : "+c.getValue());
				
				
			}
			
		}
	
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
