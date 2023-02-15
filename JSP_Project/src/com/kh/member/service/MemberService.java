package com.kh.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	
	
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		//트랜잭션처리를 하기 위한 Connection객체를 Service안에서 만들어줌, 직접 DB에 접근하지 않음
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		//m에는 null값이나, memberdao에서 변환시킨 값이 들어갈 예정
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;

	}
	
	
	public int insertMember(Member m) {
		//반환형 int : 처리된 행의 갯수
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		//트랜잭션 처리
		if(result>0) { //성공
			//커밋처리
			JDBCTemplate.commit(conn);
		}else { //실패
			//롤백처리
			JDBCTemplate.rollback(conn);
		}
		//사용한 자원 반납 conn.close();
			JDBCTemplate.close(conn);
		
		//컨트롤러에게 결과값 반환(처리된 행의 갯수)
		return result;

		
	}
	
	/**
	 * 회원정보 수정용 서비스
	 * @param m : 수정할 회원의 정보를 담은 Member객체
	 * @return => 수정한 회원의 갱신된 정보
	 */
	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result>0) { //성공
			
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId()); //업데이트 성공시에만 updateMem에 정보 저장해서 전달..?
			
		}else {//실패
			JDBCTemplate.rollback(conn);
		} 
		JDBCTemplate.close(conn); //자원반납
		
		return updateMem;
		
		
		
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn,userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result>0) { //성공
			JDBCTemplate.commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);  //아이디와 일치하는 모든 정보
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
		
	}
	
	
	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		
		
		if(result > 0 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
}
