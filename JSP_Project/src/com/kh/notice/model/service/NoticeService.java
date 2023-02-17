package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*; //JDBCTemplate 클래스의 모든 메소드들을 그냥 가져다가 쓸 수 있음(commit, rollback..등등)

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	
	public ArrayList<Notice> selectNoticeList(){
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		close(conn); //원래 : JDBCTemplate.close(conn); 
		
		return list;
		
	}
	
	
	public int increaseCount(int nno) { //nno = notice no의 약자
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn, nno);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}
	
	public Notice selectNotice(int nno) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, nno);
		
		close(conn);
		
		return n;
		
		
	}
	
	public int insertNotice(Notice n) {
		
		Connection conn= getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		
		if(result >0) {
			commit(conn);
			
			result = new NoticeDao().selectNoticeNo(conn); //NoticeNo값이 반환
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
		
		
	}
	
	
	public int updateNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int deleteNotice(int nno) {
	
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, nno);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
