package com.kh.borard.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.borard.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		try {
			prop.loadFromXML(new FileInputStream( BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath() ));
			
			
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		
		PreparedStatement pstmt =null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		/*
		 * SELECT COUNT(*) AS COUNT
		 * FROM	  BOARD
		 * WHERE STATUS = 'Y'
		 * 	AND	 BOARD_TYPE = 1
		 * 
		 * 1이면 일반 게시판
		 * 2이면 사진 게시판
		 */
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
		
	}
	
	
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi){
		
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset= null;
		
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			/*
			 * 시작과 종료
			 *  boardLimit이 10이라고 가정
			 *  currentPage 1을 요청시 ->1~10
			 *  currentPage 2를 요청시 ->11~20
			 *  currentPage n을 요청시 -> 시작값 (currentPage-1) * boardLimit -1  ~ 종료값 : 시작값 + boardLimit -1
			 *  
			 */
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit()+1;
			int endRow = startRow+ pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"),
									rset.getString("BOARD_TITLE"),	
									rset.getString("USER_ID"),
									rset.getInt("COUNT"),
									rset.getDate("CREATE_DATE")
						);
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
		
		
		
	}

}
