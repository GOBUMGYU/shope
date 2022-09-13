package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvNoticeVO;

public interface NoticeService {
	int getTotalCount();	//공지사항 전체 로우수
	ArrayList<CgvNoticeVO> getList(int startCount, int endCount);  //공지사항 전체 리스트
	CgvNoticeVO getContent(String nid);	//공지사항 상세보기
	void getUpdateHits(String nid);     //공지사항 조회수 업데이트
	int getWriteResult(CgvNoticeVO vo);		//공지사항 글쓰기 처리
	int getUpdate(CgvNoticeVO vo);		//공지사항 수정 처리
	int getDelete(String nid);		//공지사항 삭제 처리
}
