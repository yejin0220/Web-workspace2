package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no") //topcat이 시작이 될때 WebServlet파일을 다 읽으면서 내가 입력한 경로로 서비스를 제공
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1) 공지사항 전체 리스트 조회 
		ArrayList<Notice> list = new NoticeService().selectNoticeList();
		  // 설정할 쿼리문 :  SELECT * FROM NOTICE WHERE STATUS='Y';
		System.out.println(list);
		

		//2) 조회결과를 담아서 응답페이지로 포워딩
		//조회결과 담기
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
		
		
		
		
		
		
		//session : 모든 페이지에서 자원을 가져와야 하는 경우
		//request : 특정 페이지에서 자원을 가져와야 하는 경우
		

		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
