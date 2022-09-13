package com.mycgv.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvMemberVO;

@Repository
public class CgvMemberDAO  {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {
		int result = 0;
		String sql = "select count(*) from cgv_member";
		
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * idCheck : 아이디 중복체크
	 */
	public int idCheck(String id) {
		int result = 0;
		String sql = "select count(id) from cgv_member "
				+ " where id=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * selectContent : 회원 상세 정보
	 */
	public CgvMemberVO selectContent(String id) {
		CgvMemberVO vo = new CgvMemberVO();
		String sql = "select id, name, zoncode, addr1, addr2, pnumber "
				+ " , hobbylist, mdate, intro  "
				+ " from cgv_member where id=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setZonecode(rs.getString(3));
				vo.setAddr1(rs.getString(4));
				vo.setAddr2(rs.getString(5));
				vo.setPnumber(rs.getString(6));
				vo.setHobbylist(rs.getString(7));
				vo.setMdate(rs.getString(8));
				vo.setIntro(rs.getString(9));
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * selectAll : 회원 전체 리스트
	 */
	public ArrayList<CgvMemberVO> selectAll(int startCount, int endCount){
		ArrayList<CgvMemberVO> list = new ArrayList<CgvMemberVO>();
		String sql = " select rno, id, name, pnumber, mdate "
				+ " from (select rownum rno, id, name, pnumber, to_char(mdate,'yyyy-mm-dd') mdate " + 
				" from (select id, name, pnumber, mdate from cgv_member " + 
				"            order by mdate desc)) "
				+ " where rno between ? and ?"; 
		
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMemberVO vo = new CgvMemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPnumber(rs.getString(4));
				vo.setMdate(rs.getString(5));
				
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * select : 로그인
	 */
	public int select(CgvMemberVO vo) {
		int result = 0;
		String sql = "select count(*) from cgv_member "
				+ " where id=? and pass=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 * insert : 회원가입 
	 */
	public int insert(CgvMemberVO vo) {
		//sqlSession 객체의 메소드를 호출하여 실행결과를 가져옴.
		return sqlSession.insert("mapper.member.join", vo);
	}
}
