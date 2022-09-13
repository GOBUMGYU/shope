package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

public class MemberServiceImpl implements MemberService{
	
	/**
	 * 회원 상세 정보
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		return vo;
	}
	
	/**
	 * 회원 전체수
	 */
	@Override
	public int getTotalCount() {
		CgvMemberDAO dao = new CgvMemberDAO();	
		int result = dao.totalCount();	//DB에서 가져온 전체 행수
		
		return result;
	}
	
	/**
	 * 회원 전체 리스트
	 */
	@Override
	public ArrayList<CgvMemberVO> getMemberList(int startCount, int endCount){	
		CgvMemberDAO dao = new CgvMemberDAO();	
		ArrayList<CgvMemberVO> list = dao.selectAll(startCount, endCount);
		
		return list;
	}
	
	/** 
	 * 회원가입 처리
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);		
		return result;
	}
	
	/**
	 * 로그인 처리
	 */
	@Override
	public int getLoginResult(CgvMemberVO vo) {	
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);		
		return result;
	}
}
