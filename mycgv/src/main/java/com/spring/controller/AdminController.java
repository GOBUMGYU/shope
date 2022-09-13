package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvMovieVO;
import com.mycgv.vo.CgvNoticeVO;
import com.spring.service.FileServiceImpl;
import com.spring.service.MemberServiceImpl;
import com.spring.service.MovieServiceImpl;
import com.spring.service.NoticeServiceImpl;
import com.spring.service.PageServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	@Autowired
	private MovieServiceImpl  movieService;
	
	@Autowired
	private PageServiceImpl  pageService;
	
	@Autowired
	private FileServiceImpl  fileService;
	
	/**
	 * admin_movie_content.do : 영화 상세 정보
	 */
	@RequestMapping(value="/admin_movie_content.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_content(String mid) {
		ModelAndView mv = new ModelAndView();
		
		CgvMovieVO vo = movieService.getContent(mid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_movie/admin_movie_content");
		
		
		return mv;
	}
	
	
	
	/**
	 * admin_movie_regist_check.do : 영화 등록 처리
	 */
	@RequestMapping(value="/admin_movie_regist_check.do", method=RequestMethod.POST)
	public ModelAndView admin_movie_regist_check(CgvMovieVO vo, HttpServletRequest request) 
													throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//파일을 선택했는지 체크
		vo = fileService.multiFileCheck(vo);
		
		//DB 연동
		//1. cgv_movie 테이블 저장 --> mid 생성
		int result = movieService.getInsert(vo);
		
		if(result == 1) {
			//2. mid값을 가져오기
			String mid = movieService.getMid();
			
			//3. mid를 레퍼런스하는 cgv_movie_file 테이블 저장
			vo.setMid(mid);
			int result2 = movieService.getInsertFile(vo);
			
			if(result2 ==1) {
				//파일을 upload에 저장
				fileService.multiFileSave(vo, request);
			}
			
		}		
		
		mv.setViewName("redirect:/admin_movie_list.do");
		
		return mv;
	}
	
	/**
	 * admin_movie_regist.do : 영화 등록 화면
	 */
	@RequestMapping(value="/admin_movie_regist.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_regist() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/admin/admin_movie/admin_movie_regist");
		
		return mv;
	}
	
	/**
	 * admin_movie_list.do : 전체 영화 리스트
	 */
	@RequestMapping(value="/admin_movie_list.do", method=RequestMethod.GET)
	public ModelAndView admin_movie_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "movie", movieService);
		ArrayList<CgvMovieVO> list = movieService.getList(param.get("startCount"),param.get("endCount"));
		
		mv.addObject("list",list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_movie/admin_movie_list");
		
		return mv;
	}
	
	
	
	/**
	 * admin_notice_delete_check.do : 공지사항 삭제 처리
	 */
	@RequestMapping(value="/admin_notice_delete_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid, HttpServletRequest request)
															throws Exception {
		ModelAndView mv = new ModelAndView();
	
		CgvNoticeVO vo = noticeService.getContent(nid);
		int result = noticeService.getDelete(nid);
		
		if(result == 1){	
			fileService.fileDelete(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_update_check.do : 공지사항 수정 처리
	 */
	@RequestMapping(value="/admin_notice_update_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo, HttpServletRequest request)
																throws Exception {
		ModelAndView mv = new ModelAndView();

		String old_filename = vo.getNsfile();	//수정화면에서 hidden으로 넘어오는 기존 upload 폴더에 저장된 파일명
		
		vo = fileService.update_fileCheck(vo);
		int result = noticeService.getUpdate(vo);
		
		if(result == 1){
			//새로운 파일을 upload 폴더에 저장한 후 기존의 파일은 삭제
			fileService.update_filesave(vo, request, old_filename);
			mv.setViewName("redirect:/admin_notice_list.do");
			
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_write_check.do : 공지사항 글쓰기 처리
	 */
	@RequestMapping(value="/admin_notice_write_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request )
															throws Exception {
		ModelAndView mv = new ModelAndView();
		
		vo = fileService.fileCheck(vo);
		int result = noticeService.getWriteResult(vo);
		
		if(result == 1){			
			fileService.fileSave(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{

			mv.setViewName("error_page");
		}		
		
		return mv;
	}
	
	
	/**
	 * admin_notice_write.do : 공지사항 글쓰기 화면 
	 */
	@RequestMapping(value="/admin_notice_write.do", method=RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}
	
	/**
	 * admin_notice_delete.do : 공지사항 삭제 화면 
	 */
	@RequestMapping(value="/admin_notice_delete.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");
		
		return mv;
	}
	
	/**
	 * admin_notice_update.do : 공지사항 수정화면 
	 */
	@RequestMapping(value="/admin_notice_update.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");
		
		return mv;
	}
	
	/**
	 * admin_notice_content.do : 공지사항 상세 정보 
	 */
	@RequestMapping(value="/admin_notice_content.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeVO vo = noticeService.getContent(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");
		
		return mv;
	}
	
	
	/**
	 * admin_notice_list.do : 공지사항 전체리스트 
	 */
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "notice", noticeService);
		ArrayList<CgvNoticeVO> list = noticeService.getList(param.get("startCount"),param.get("endCount"));
		
		mv.addObject("list",list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	/**
	 * admin_member_content.do : 회원 상세 정보 
	 */
	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberVO vo = memberService.getMemberContent(id);
		String address = vo.getZonecode()+" "+vo.getAddr1()+" "+ vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
	/**
	 * admin_member_list.do : 회원 전체리스트 
	 */
	@RequestMapping(value="/admin_member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
			
		Map<String, Integer> param = pageService.getPageResult(rpage, "member", memberService);
		ArrayList<CgvMemberVO> list = memberService.getMemberList(param.get("startCount"), param.get("endCount"));
		
		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_member/admin_member_list");		
		
		return mv;
	}
	
	/**
	 * admin.do : 관리자 페이지 
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
}
