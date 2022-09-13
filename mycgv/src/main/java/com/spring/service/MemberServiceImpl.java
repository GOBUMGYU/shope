package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

public class MemberServiceImpl implements MemberService{
	
	/**
	 * ȸ�� �� ����
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		return vo;
	}
	
	/**
	 * ȸ�� ��ü��
	 */
	@Override
	public int getTotalCount() {
		CgvMemberDAO dao = new CgvMemberDAO();	
		int result = dao.totalCount();	//DB���� ������ ��ü ���
		
		return result;
	}
	
	/**
	 * ȸ�� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount){	
		CgvMemberDAO dao = new CgvMemberDAO();	
		ArrayList<CgvMemberVO> list = dao.selectAll(startCount, endCount);
		
		return list;
	}
	
	/** 
	 * ȸ������ ó��
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);		
		return result;
	}
	
	/**
	 * �α��� ó��
	 */
	@Override
	public int getLoginResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);		
		return result;
	}
}
