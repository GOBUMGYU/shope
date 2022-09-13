package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

public class BoardServiceImpl implements BoardService{
	/**
	 * ���� ó��
	 */
	@Override
	public int getDelete(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		return result;
	}
	
	/**
	 * ����ó��
	 */
	@Override
	public int getUpdate(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		return result;
	}
	
	/**
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		dao.updateHits(bid);
	}
	
	/**
	 * �Խñ� ����
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/**
	 * �Խñ� �󼼺���
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		return vo;
	}		
	
	/**
	 * �Խñ� ��ü �ο�
	 */
	@Override
	public int getTotalCount() {
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.totalCount();
		return result;
	}
	
	/**
	 * �Խñ� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){
		CgvBoardDAO dao = new CgvBoardDAO();
		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		return list;
	}

}
