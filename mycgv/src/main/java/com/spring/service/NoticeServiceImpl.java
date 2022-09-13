package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

public class NoticeServiceImpl implements NoticeService{
	
	/**
	 * 공지사항 삭제 처리
	 */
	@Override
	public int getDelete(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		return result;
	}
	
	/**
	 * 공지사항 수정 처리
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * 공지사항 글쓰기 처리
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * 공지사항 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		dao.updateHits(nid);
	}
	
	
	/**
	 * 공지사항 상세보기
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		return vo;
	}
	
	/**
	 * 공지사항 전체 로우수
	 */
	@Override
	public int getTotalCount() {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/**
	 * 공지사항 전체 리스트
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		CgvNoticeDAO dao = new CgvNoticeDAO();
		ArrayList<CgvNoticeVO> list = dao.select(startCount,endCount);
		return list;
	}
}
