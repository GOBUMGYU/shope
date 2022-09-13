package com.spring.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mycgv.vo.CgvBoardVO;
import com.mycgv.vo.CgvMovieVO;
import com.mycgv.vo.CgvNoticeVO;

public class FileServiceImpl {
	/**
	 * 영화등록 : 멀티파일 저장
	 */
	public void multiFileSave(CgvMovieVO vo, HttpServletRequest request) throws Exception {
		//파일을 upload에 저장
		for(int i=0; i<vo.getFiles().length; i++) {
			CommonsMultipartFile file = vo.getFiles()[i];
			
			if(!file.getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				
				File upload_file = new File(path+vo.getMsfiles().get(i));
				file.transferTo(upload_file);
			}
		}//for
	}
	
	/**
	 * 영화등록 : 멀티파일 체크
	 */
	public CgvMovieVO multiFileCheck(CgvMovieVO vo) {
		for(CommonsMultipartFile file : vo.getFiles()) {
			if(!file.getOriginalFilename().equals("")) {
				UUID uuid = UUID.randomUUID();
				
				vo.getMfiles().add(file.getOriginalFilename());
				vo.getMsfiles().add(uuid+"_"+file.getOriginalFilename());
			}else {
				vo.getMfiles().add("");
				vo.getMsfiles().add("");
			}
		}
		vo.setMfile1(vo.getMfiles().get(0));
		vo.setMfile2(vo.getMfiles().get(1));
		vo.setMsfile1(vo.getMsfiles().get(0));
		vo.setMsfile2(vo.getMsfiles().get(1));
		
		return vo;
	}
	
	/**
	 * 공지사항 : 게시글 삭세 시 파일이 존재하면 삭제하기
	 */
	public void fileDelete(CgvNoticeVO vo, HttpServletRequest request) throws Exception{
		if(vo.getNsfile() != null) {
			String path=request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File old_file = new File(path+vo.getNsfile());
			if(old_file.exists()) {
				old_file.delete();
			}
		}
	}
	
	/**
	 * 게시판 : 게시글 삭세 시 파일이 존재하면 삭제하기
	 */
	public void fileDelete(CgvBoardVO vo, HttpServletRequest request) throws Exception{
		if(vo.getBsfile() != null) {
			String path=request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File old_file = new File(path+vo.getBsfile());
			if(old_file.exists()) {
				old_file.delete();
			}
		}
	}
	
	/**
	 * 공지사항 : 파일이 있는 경우 update시 파일저장
	 */
	public void update_filesave(CgvNoticeVO vo, HttpServletRequest request, String old_filename) 
														throws Exception {
		//새로운 파일을 upload 폴더에 저장
		if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			System.out.println(path);
			
			File file = new File(path+vo.getNsfile());
			vo.getFile1().transferTo(file);
		
			//기존파일이 있는 경우에는 파일 삭제
			File ofile = new File(path+old_filename);
			if(ofile.exists()) {
				ofile.delete();
			}
		}
	}
	
	/**
	 * 게시판 : 파일이 있는 경우 update시 파일저장
	 */
	public void update_filesave(CgvBoardVO vo, HttpServletRequest request, String old_filename) 
														throws Exception {
		//새로운 파일을 upload 폴더에 저장
		if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			System.out.println(path);
			
			File file = new File(path+vo.getBsfile());
			vo.getFile1().transferTo(file);
		
			//기존파일이 있는 경우에는 파일 삭제
			File ofile = new File(path+old_filename);
			if(ofile.exists()) {
				ofile.delete();
			}
		}
	}
	
	/**
	 * 공지사항 : 파일이 있는 경우 update시 파일체크
	 */
	public CgvNoticeVO update_fileCheck(CgvNoticeVO vo) {
		if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
			
			UUID uuid = UUID.randomUUID();
			
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		}
		return vo;
	}
	
	/**
	 * 게시판 : 파일이 있는 경우 update시 파일체크
	 */
	public CgvBoardVO update_fileCheck(CgvBoardVO vo) {
		if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
			
			UUID uuid = UUID.randomUUID();
			
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		}
		return vo;
	}
	
	/**
	 * 공지사항 : 파일 upload 폴더에 저장
	 */
	public void fileSave(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		//2. upload 폴더에 nsfile 명으로 실제 파일 업로드 처리
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File file = new File(path+vo.getNsfile());
			vo.getFile1().transferTo(file);
		}
	}
	
	/**
	 * 게시판 : 파일 upload 폴더에 저장
	 */
	public void fileSave(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		//2. upload 폴더에 bsfile 명으로 실제 파일 업로드 처리
		if(!vo.getFile1().getOriginalFilename().equals("")) {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "\\resources\\upload\\";
			
			File file = new File(path+vo.getBsfile());
			vo.getFile1().transferTo(file);
		}
	}
	
	/**
	 * 공지사항 : 파일 체크 후 nfile, nsfile 생성
	 */
	public CgvNoticeVO fileCheck(CgvNoticeVO vo) {
		//1. vo객체의 파일체크 후 nfile, nsfile에 저장되는 이름 생성
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setNfile("");
			vo.setNsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
		return  vo;
	}//fileCheck
	
	/**
	 * 게시판 : 파일 체크 후 bfile, bsfile 생성
	 */
	public CgvBoardVO fileCheck(CgvBoardVO vo) {
		//1. vo객체의 파일체크 후 bfile, bsfile에 저장되는 이름 생성
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setBfile("");
			vo.setBsfile("");
		}else {
			UUID uuid = UUID.randomUUID();
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
		return  vo;
	}//fileCheck
}
