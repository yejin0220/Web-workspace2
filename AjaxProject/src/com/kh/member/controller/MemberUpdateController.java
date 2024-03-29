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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		
		//1)인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		//2)요청시 전달값들을 뽑아서 변수 및 객체에 담기
		String userId = request.getParameter("userId");		// userId, userName은 필수값 전달
		String userName = request.getParameter("userName");	// 
		String phone = request.getParameter("phone"); 		// phone, email, address는 빈문자열이 전달될 수 있음
		String email = request.getParameter("email");       //
		String address = request.getParameter("address");	//
		String[] interestArr = request.getParameterValues("interest"); // 아무것도 체크 안하면 -> null값 전달됨
		
		// String[]  ---> String 자료형으로 변환
		//["운동","등산",...]  --> "운동, 등산.."으로 변환
		//사용자가 입력한 값이 null이 아니라면-- 
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		
		//3)회원가입 요청 처리
		Member updateMem = new MemberService().updateMember(m);
		
		//4)돌려받은 처리 결과를 가지고 사용자가 보게될 응답 뷰를 지정
		if(updateMem == null) {//실패
			
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}else {//성공
			
			//변경된 회원정보를 session영역에 다시 한 번 저장
			HttpSession session = request.getSession();			
			session.setAttribute("loginUser", updateMem); //같은 키값은 존재할 수 없음(덮어씌우기가 가능) 새롭게 얻은 updateMem을 새롭게 저장
			session.setAttribute("alertMsg", "성공적으로 회원 정보를 수정했습니다.");
			
			//session.invalidate(); =>회원정보가 변경되면 로그아웃 처리 후 다시 로그인 하게끔 유도하는 경우도 있음.
			
			//url재요청 -> 마이페이지로 , 마이페이지에서 정보가 제대로 수정되었는지 확인
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
			
		}
		
		
	}

}
