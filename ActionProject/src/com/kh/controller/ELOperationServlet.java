package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class ELOperationServlet
 */
@WebServlet("/operation.do")
public class ELOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ELOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("big", 10);
		request.setAttribute("small", 3);
	
		request.setAttribute("sOne", "안녕");
		request.setAttribute("sTwo", new String("안녕"));
		String s1 = "안녕";
		String s2 = "안녕";
		System.out.println(s1 == s2);
		
		request.setAttribute("pOne", new Person("홍길동","남","요리사"));
		request.setAttribute("pTwo", null);
		
		ArrayList<String> list1 = new ArrayList<>();
		request.setAttribute("lOne", list1);
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("문자열 잘들어가나?");
		request.setAttribute("lTwo", list2);
	
	
	
	
	
	
	
	
	
	
	
	
		request.getRequestDispatcher("views/1_EL/02_elOperation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
