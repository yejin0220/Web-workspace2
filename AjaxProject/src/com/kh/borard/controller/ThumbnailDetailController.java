package com.kh.borard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.borard.model.service.BoardService;
import com.kh.borard.model.vo.Attachment;
import com.kh.borard.model.vo.Board;

/**
 * Servlet implementation class ThumbnailDetailController
 */
@WebServlet("/detail.th")
public class ThumbnailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int boardNo = Integer.parseInt(request.getParameter("tno"));
		
		
		BoardService bService = new BoardService();
		
		int result = bService.increaseThumbnailCount(boardNo);
	
		if(result > 0) {
			Board b = bService.selecThumbnailtBoard(boardNo);
			ArrayList<Attachment> list = bService.selectThumbnailAttachment(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getre
			
			
			
			
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
