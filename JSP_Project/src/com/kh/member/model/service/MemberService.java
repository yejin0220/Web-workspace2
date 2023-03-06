package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public int insertMember(Member m) {
		// 반환형 int : 처리된 행의 갯수.
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		// 트랜잭션처리
		if(result > 0) { // 성공
			//커밋
			JDBCTemplate.commit(conn);
		}else { // 실패
			//롤백
			JDBCTemplate.rollback(conn);
		}
		
		// 사용한 자원 반납. conn.close();
		JDBCTemplate.close(conn);
		
		// 컨트롤로에게 결과값 반환(처리된 행의 갯수)
		return result;
	}
	
	
	
	/**
	 * 회원 정보 수정용 서비스
	 * @param m : 수정할 회원의 정보를 담은 Member객체
	 * @return => 수정한 회원의 갱신된 정보
	 */
	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return updateMem;
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, userId);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
	
	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	public int idCheck(String userId) {
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().idCheck(conn, userId);
		
		
		JDBCTemplate.close(conn);
		
		return count;
		//반환형 int지만, string형태로 전달할 것이기 때무네 따로 트랜잭션 처리 필요 없음
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
