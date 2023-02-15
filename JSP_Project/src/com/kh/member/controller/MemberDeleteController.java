package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		
		//회원탈퇴 form태그내에 필요한 정보 -> 전달되는 정보는 userPwd만 있음
		//							 -> userId는 session안에 저장되어있는 정보를 불러와야 함.		
		//								getAttribute => session영역에 저장된 정보를 뽑아오는 메소드	
		//								session.setAttribute("loginUser", loginUser); -> LoginController에서 session에 저장해두었음.
		
		//로그인한 회원의 정보를 얻어오는 방법
		//방법 1) session 영역에 담겨있는 회원객체로부터 뽑아내기
		//방법 2) input type="hidden" 회원 정보를 숨겨서 요청시 함께 전달하기
		
		String userId = ((Member) request.getSession().getAttribute("loginUser")).getUserId();
		String userPwd = request.getParameter("userPwd");
		
		//회원탈퇴 : 탈퇴하자마자 로그아웃처리를 하기 때문에 session영역에 저장을 할 필요가 없음
		//반환값으로 변경된 회원정보를 지정할 필요가 없기 때문에 Member가 아닌 int 자료형으로 정보를 저장
		int result = new MemberService().deleteMember(userId, userPwd); 
		
		HttpSession session = request.getSession();
		
		if(result == 0) { //회원탈퇴 실패
			session.setAttribute("alertMsg", "회원탈퇴에 실패하셨습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		
		}else { //회원탈퇴 성공 + 로그아웃처리까지 하기
			session.setAttribute("alertMsg", "회원탈퇴에 성공하셨습니다.");
			response.sendRedirect(request.getContextPath()+"/logout.me"); 
			//회원탈퇴 후 로그아웃처리가 되어야함 -> 현재 LogoutController 에 작성되어 있는 invalidate대신 removeAttribute로 수정해줘야 함
			//=> 로그아웃처리를 로그아웃 서블릿에게 위임하는 방법
			
			//invalidate() : 사용시 세션이 만료되어 안에 들어간 데이터가 모두 날라감
			// => 단점 : 아무리 session영역에 alertMsg를 넣어놔도 메세지 호출이 불가능함
			// => 해결방안 : removeAttribute("loginUser") -> 로그인한 사용자의 정보만 지워줌
			
			
			//세션안에서 로그인 정보만 지우는 방법
			//1. session.setAttribute("alertMsg", "회원탈퇴에 성공하셨습니다.");
			//2. session.remobeAttribute("loginUser");
			//3. response.sendRedirect(request.getContextPath());
			
		}	
		
	}

}
