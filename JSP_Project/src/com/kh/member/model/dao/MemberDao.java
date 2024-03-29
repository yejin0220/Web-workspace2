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

import com.kh.common.JDBCTemplate;
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
	
	public int insertMember(Connection conn, Member m) { 
		//Insert 문 => 처리된 행의 갯수
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}
	
	
	public int updateMember(Connection conn, Member m) {
		
		//UPDATE문 => DML문 (반환값 처리된 행의 갯수가 반환됨)
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate(); //쿼리문 실행 후, 처리된 결과값을 result에 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}
	
	
	public Member selectMember(Connection conn, String userId) {
		
		
		//Select문 => 결과값 ResultSet객체에 저장(id값은 unique제약조건이 걸려있어서 무조건 한행이거나, 없거나로 조회가 됨)
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //조회된 결과가 있고, 커서가 다음으로 넘어간다면 =>true / 아니라면 => false 반환
				
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
		} finally {
			//생성된 순서의 역순으로 닫아주기
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
		

	}
	
	
	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
		//UPDATE문 => DML문 // 처리된 행의 갯수 반환
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
		
		
	}
	
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
		
		
	}
	
	
	public int idCheck(Connection conn, String userId) {
		
		//SELECT문 실행 -> 결과값은 무조건 한행만 나오도록
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				//다음행이 존재한다면 1을 반환(중복되는 아이디가 존재한다면) / 존재 하지 않는다면 0의 값을 반환
				//next()의 시작위치는 column의 0번째 위치에서 값이 있을 경우 다음 위치인 1번으로 이동
				//select일때마아아아안!!!!
				count = rset.getInt(1);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(conn);
			
		}
		
		return count;
		
	}
	
	
	
}
