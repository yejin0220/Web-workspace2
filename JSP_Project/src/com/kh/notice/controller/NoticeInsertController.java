package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
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
		
		String noticeWriter = request.getParameter("userNo");
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		Notice n = new Notice(noticeTitle, noticeContent, noticeWriter);
		
		int result = new NoticeService().insertNotice(n);
		
		HttpSession session = request.getSession();
		
		if(result>0) {
			session.setAttribute("alertMsg", "공지사항 등록 완료");
			
//			response.sendRedirect(request.getContextPath()+"/list.no"); => 게시글 성공시 게시글 목록으로 이동
			
			//글 등록에 성공했을때 내가 작성한 게시글로 바로 이동
			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+result);
			
		}else {
			
			session.setAttribute("alertMsg", "공지사항 등록 실패");
			
			response.sendRedirect(request.getContextPath());
		}
		
		
//		Notice에 매개변수 생성자를 만들지 않고 사용하는 방법		
//		String userNo = request.getParameter("userNo");
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		
//		Notic n = new Notice();
//		n.setNoticeTitle(title);
//		n.setNoticeContent(content);
//		n.setNoticeWriter(userNo);
//		
		
	}

}
