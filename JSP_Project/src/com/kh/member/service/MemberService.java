package com.kh.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	
	
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		//트랜잭션처리를 하기 위한 Connection객체를 Service안에서 만들어줌
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		//m에는 null값이나, memberdao에서 변환시킨 값이 들어갈 예정
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;

	}
	
	
}
