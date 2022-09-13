package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.CgvMovieVO;
import com.mycgv.vo.CgvNoticeVO;

public class CgvMovieDAO extends DBConn{
	
	/**
	 * select : 영화 상세 정보
	 */
	public CgvMovieVO select(String mid) {
		CgvMovieVO vo = new CgvMovieVO();
		String sql = " select cm.mid,mcategory,mname, mdesc,mdate,mfile1,msfile1,mfile2,msfile2 "
				+ " from cgv_movie cm, cgv_movie_file cmf "
				+ " where cm.mid = cmf.mid and cm.mid=? ";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				vo.setMid(rs.getString(1));
				vo.setMcategory(rs.getString(2));
				vo.setMname(rs.getString(3));
				vo.setMdesc(rs.getString(4));
				vo.setMdate(rs.getString(5));
				vo.setMfile1(rs.getString(6));
				vo.setMsfile1(rs.getString(7));
				vo.setMfile2(rs.getString(8));
				vo.setMsfile2(rs.getString(9));				
			}			
			close();
		} catch (Exception e) {	e.printStackTrace();  }
		
		return vo;
	}
	
	
	/**
	 * select : 전체 영화 리스트
	 */
	public ArrayList<CgvMovieVO> select(int startCount, int endCount){
		ArrayList<CgvMovieVO> list = new ArrayList<CgvMovieVO>();
		String sql = " select rno, mid,mcategory, mname,mdate "
				+ " from (select rownum rno, mid,mcategory, mname,mdate "
				+ "           from (select mid, mcategory, mname,mdate from cgv_movie "
				+ "  	     order by mdate desc)) "
				+ " where rno between ? and ?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMovieVO vo = new CgvMovieVO();
				vo.setRno(rs.getInt(1));
				vo.setMid(rs.getString(2));
				vo.setMcategory(rs.getString(3));
				vo.setMname(rs.getString(4));
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
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {
		int result = 0;
		String sql = "select count(*) from cgv_movie";
		
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
	 * insert_file : 영화이미지 등록 처리
	 */
	public int insert_file(CgvMovieVO vo) {
		int result = 0;
		String sql = "insert into cgv_movie_file values('f_'||sequ_cgv_movie_file.nextval,?,?,?,?,? )";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getMfile1());
			pstmt.setString(2, vo.getMsfile1());
			pstmt.setString(3, vo.getMfile2());
			pstmt.setString(4, vo.getMsfile2());
			pstmt.setString(5, vo.getMid());
			
			result = pstmt.executeUpdate();	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	
	/**
	 * selectMid : mid 가져오기
	 */
	public String selectMid() {
		String mid = "";
		String sql = "select  mid from (select mid from cgv_movie order by mdate desc) where rownum=1";
		
		try {
			getPreparedStatement(sql);		
			
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				mid = rs.getString(1);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return mid;
	}
	
	/**
	 * insert : 영화등록 기능
	 */
	public int insert(CgvMovieVO vo) {
		int result = 0;
		String sql = "insert into cgv_movie values('m_'||sequ_cgv_movie.nextval,?,?,?,sysdate )";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getMcategory());
			pstmt.setString(2, vo.getMname());
			pstmt.setString(3, vo.getMdesc());
			
			result = pstmt.executeUpdate();	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
}
