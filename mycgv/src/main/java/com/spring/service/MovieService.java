package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvMovieVO;

public interface MovieService {
	int getTotalCount();				//영화 전체 로우수
	ArrayList<CgvMovieVO> getList(int startCount, int endCount);  	//영화 전체 리스트
	int getInsert(CgvMovieVO vo);		//영화 등록
	String getMid();					//영화 등록 시 mid 가져오기
	int getInsertFile(CgvMovieVO vo);	//영화 등록
	CgvMovieVO getContent(String mid);  //영화 상세보기
}
