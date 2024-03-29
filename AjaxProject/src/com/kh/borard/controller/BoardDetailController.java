package com.kh.borard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.borard.model.service.BoardService;
import com.kh.borard.model.vo.Attachment;
import com.kh.borard.model.vo.Board;

/**
 * Servlet implementation class boardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		
		
		BoardService bService = new BoardService();
		
		//조회수 증가 / 게시글 조회(Board)/첨부파일 조회(Attachment)
		int result = bService.increaseCount(boardNo);
		
		if(result >0 ) {//조회수 증가 성공시 => 게시글 정보, 첨부파일을 조회해서 request영역안에 담은 후에, 상세 페이지로 포워딩
			Board b = bService.selectBoard(boardNo);
			Attachment at = bService.selectAttachment(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		
		}else { //조회수 증가 실패시
			
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
			
		}
		
		
		
	}
				

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
