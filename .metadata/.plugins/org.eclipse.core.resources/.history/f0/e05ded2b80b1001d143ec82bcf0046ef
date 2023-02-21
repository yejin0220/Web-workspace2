package com.kh.borard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.borard.model.service.BoardService;

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
		
		
		
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int result = new BoardService().selectBoard(bno);
		
		if(result >0 ) {
			
			request.setAttribute("bno", bno);
			request.getRequestDispatcher("view/board/boardDetailView.jsp").forward(request, response);
			
		}else {
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
		}
		
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
