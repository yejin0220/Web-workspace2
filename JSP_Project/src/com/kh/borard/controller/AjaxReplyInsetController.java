package com.kh.borard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.borard.model.service.BoardService;
import com.kh.borard.model.vo.Reply;
import com.kh.borard.model.vo.ReplyBuilder;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsetController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//비동기속성에서 data에 사용자가 작성한 key값 사용하기
		String content = request.getParameter("content");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo()+""; 
		
		
		Reply r = new Reply();
		r.setReplyCotent(content);
		r.setReplyBno(bno);
		r.setReplyWriter(writer);
		
		
		ReplyBuilder rb = new ReplyBuilder.Builder(1).setReplyContent("댓글내용").setRefBno(1).build();
		
		
		
		int result = new BoardService().insertReply(r);
		response.getWriter().print(result);
		
		
		
		
	
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
