package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvBoardVO;

public interface BoardService {
	int getTotalCount();	//게시글 전체수
	ArrayList<CgvBoardVO> getList(int startCount, int endCount);	//게시글 전체 리스트
	int getWriteResult(CgvBoardVO vo);		//게시글 쓰기
	CgvBoardVO getContent(String bid);		//게시글 상세보기
	void getUpdateHits(String bid);			//게시글 조회수 업데이트
	int getUpdate(CgvBoardVO vo);				//게시글 수정 처리
	int getDelete(String bid);				//게시글 삭제 처리
}
