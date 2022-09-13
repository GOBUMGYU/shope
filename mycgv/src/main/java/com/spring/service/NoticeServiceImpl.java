package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

public class NoticeServiceImpl implements NoticeService{
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getDelete(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		return result;
	}
	
	/**
	 * �������� ���� ó��
	 */
	@Override
	public int getUpdate(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * �������� �۾��� ó��
	 */
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * �������� ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		dao.updateHits(nid);
	}
	
	
	/**
	 * �������� �󼼺���
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		return vo;
	}
	
	/**
	 * �������� ��ü �ο��
	 */
	@Override
	public int getTotalCount() {
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/**
	 * �������� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		CgvNoticeDAO dao = new CgvNoticeDAO();
		ArrayList<CgvNoticeVO> list = dao.select(startCount,endCount);
		return list;
	}
}
