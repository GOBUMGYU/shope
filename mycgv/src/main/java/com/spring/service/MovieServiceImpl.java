package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

public class MovieServiceImpl implements MovieService{
	/**
	 * 영화 전체 로우수
	 */
	@Override
	public int getTotalCount() {
		CgvMovieDAO dao = new CgvMovieDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/**
	 * 영화 전체 리스트
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		CgvMovieDAO dao = new CgvMovieDAO();
		ArrayList<CgvMovieVO> list = dao.select(startCount, endCount);
		return list;
	}
	
	/**
	 * 영화 등록
	 */
	@Override
	public int getInsert(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();		
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * 영화 등록 후 mid 가져오기
	 */
	@Override
	public String getMid() {
		CgvMovieDAO dao = new CgvMovieDAO();
		String mid = dao.selectMid();
		return mid;
	}
	
	/**
	 * 영화 파일 등록
	 */
	@Override
	public int getInsertFile(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();
		int result2 = dao.insert_file(vo);
		return result2;
	}
	
	/**
	 * 영화 상세보기
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		CgvMovieDAO dao = new CgvMovieDAO();
		CgvMovieVO vo = dao.select(mid);
		return vo;
	}

}









