package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
		/*
		 * <HttpServletRequest 객체와 HttpServletResponse 객체>
		 * -request : 서버로 요청할 때의 정보들이 담겨있음(요청시 전달한 값, 전송방식 등)
		 * -response : 요청에 대해 응답할때 필요한 객체
		 * 
		 * <GET방식과 POST방식의 차이점>
		 * -GET 방식  : 사용자가 입력한 값들이 url에 노출	 / 데이터 길이 제한이 있음  / 즐겨찾기 기능상 편리
		 * -POST 방식 : 사용자가 입력한 값들이 url에 노출 안됨 / 데이터 길이 제한이 없음 / 대신 TimeOut이 존재 
		 */
		
		//1)전달값에 한글이 있을 경우 인코딩 처리해야함(POST방식일 경우에만)
		request.setCharacterEncoding("UTF-8");
		
		//2)요청시 전달값을 꺼내서 변수 or 객체에 기록
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		
		//3)해당 요청(로그인요청)을 처리하는 서비스 클래스의 메소드를 호출
		//loginMember : 
		Member loginUser =new MemberService().loginMember(userId, userPwd);
		
		//4)처리된 결과를 가지고 사용자가 보게될 화면(view)을 지정 후 포워딩 or url 재요청
		/*
		 * 응답페이지에 전달한 값이 있을 경우 값을 어딘가에 담아줘야 함(담아줄 수 있는 Servlet Scope의 내장객체 4종류)
		 * 
		 * 1) application : application에 담은 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸 수 있음
		 * 2) session 	  : session에 담은 데이터는 모든 jsp와 servlet에서 꺼내 쓸 수 있음
		 * 					한번 담은 데이터는 내가 직접 지우기 전까지, 서버가 멈추기 전까지, 브라우저가 멈추기 전까지 접근해서 꺼내 쓸 수 있음
		 * 3) request     : request에 담은 데이터는 해당 request를 포워딩한 응답 jsp에서만 꺼내쓸 수 있다.
		 * 4) page   	  : jsp에서만 꺼내 쓸 수 있다.
		 * 
		 * => session과 request를 주로 쓴다.
		 * 
		 * *공통적으로 데이터를 담을 때 : .setAttribute("키","벨류");
		 * 				   꺼낼 때 : .setAttribute("키");
		 * 				   지울 때 : .removeAttribute("키");
		 *  
		 */
		
		if(loginUser == null) { //로그인 실패 => 에러페이지 응답
			
			request.setAttribute("errorMsg", "로그인에 실패 했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			//포워딩 방식 : 해당 경로로 선택된 뷰가 보여질 뿐, url은 변경되지 않는다.(요청했을 때의  url(login.me)이 그대로 남아있음)
			view.forward(request, response);
			
		}else { //로그인 성공 => 인덱스페이지 응답(메인페이지)
			
			//로그인한 회원의 정보를 로그아웃하기 전까지 계속 가져다 사용할 것이기 때문에, session영역에 사용자의 정보를 담아 유지시키기
			//Servlet에서 JSP내장객체인 session에 접근하고자 한다면 우선 session객체를 얻어와야 함.
			HttpSession session =  request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			
			session.setAttribute("alertMsg", "성공적으로 로그인이 되었습니다.");
			
			//1. 포워딩 방식 응답 뷰 출력하기
			//   선택된 jsp가 보여질 뿐 url에는 여전히 현재 이 서블릿에 대한 매핑값이 남아있을 것
			//   localhost:8082/jspproject/login.me
			//   위의 url이 그대로 남아있는 상태에서 새로고침(f5)누르게 되면 게속 로그인 요청을 보내게 됨
			
			//2. url재요청방식(sendRedirect방식)
			//	 localhost:8082/jspproject라는 url로 재요청 보냄
			//   로그인 시에는 redirect방식이 사용된다.
			response.sendRedirect(request.getContextPath());
			

			
		}
				
	}

}
