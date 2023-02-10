package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.member.model.vo.Member;

public class MemberDao {
	//db에 접근해서 실제로 존재하는 회원인지 아닌지 검사
	
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		//Select문 => 반환형 ResultSte객체(조회된 행은 1개이거나 없거나 -> 아이디값은 unique로 고유하기 때문)
		Member m = null;
		
		ResultSet rset = null;
		
		PreparedStatement pstmt = null;
		//sql문을 실행하기 위한 객체
		
		String sql = prop.getProperty("loginMember");
		//실행될 SQL문을 따로 관리(XML)하고, DAO에서는 xml에 저장된 "loginMember"쿼리문을 가져와서 실행
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			//sql문에 있는 ?값 -> 첫번째 ?는 userId, 두번째 ?는 userPwd
			
			rset = pstmt.executeQuery();
			//결과값이 통재로rset에 담겨서 자바로 전달
			
			
			if(rset.next()) { //조회된 결과값 있다면 true, 없다면 false반환
				
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		}
		
		return m;
		
	}
	
}