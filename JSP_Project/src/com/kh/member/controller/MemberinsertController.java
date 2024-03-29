package com.kh.member.controller;

import java.io. IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberinsertController
 */
@WebServlet("/insert.me")
public class MemberinsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberinsertController() {
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
		
		//2)요청시 전달값을 뽑아서 변수로 만들고 객체에 기록
		String userId = request.getParameter("userId");		// userId, userPwd, userName은 필수값 전달
		String userPwd = request.getParameter("userPwd");	// 
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
		
		//전달받은 parameter를 가지고 Member클래스로 만들어주기
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		//3)요청처리 : 서비스메소드를 초훌하고 결과값(회원가입을 성공했는지 아닌지) 돌려받기
		int result = new MemberService().insertMember(m);
		
		//4)처리결과를 가지고 사용자가 보게될 응답화면을 지정
		if(result > 0) { //성공 => 메인페이지로 이동 : /jsproject(url재요청 방식)
			
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다.");
			response.sendRedirect(request.getContextPath()); // request.getContextPath() = /jspproject 동일한 경로
			
		}else {//실패 => 에러페이지로 이동 : 포워딩 방식
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
