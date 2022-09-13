package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

public class BoardServiceImpl implements BoardService{
	/**
	 * 삭제 처리
	 */
	@Override
	public int getDelete(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		return result;
	}
	
	/**
	 * 수정처리
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		dao.updateHits(bid);
	}
	
	/**
	 * 게시글 쓰기
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * 게시글 상세보기
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		return vo;
	}		
	
	/**
	 * 게시글 전체 로우
	 */
	@Override
	public int getTotalCount() {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.totalCount();
		return result;
	}
	
	/**
	 * 게시글 전체 리스트
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){
		CgvBoardDAO dao = new CgvBoardDAO();
		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		return list;
	}

}
