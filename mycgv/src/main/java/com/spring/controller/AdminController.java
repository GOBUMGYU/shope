package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvNoticeVO;

@Controller
public class AdminController {
	/**
	 * admin_notice_write_check.do : 공지사항 글쓰기 처리
	 */
	@RequestMapping(value="/admin_notice_write_check.do", method=RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request) throws Exception{
		
		//파일 선택되었는지 체크 
		if(vo.getFile1().getOriginalFilename().equals("")) {
			vo.setNfile("");
			vo.setNsfile("");
		}else { //파일선택O
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid + "_" + vo.getFile1().getOriginalFilename());
		}
		
			
		ModelAndView mv = new ModelAndView();
		//DB연동
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		if(result == 1){
			if(!vo.getNfile().equals("")) {
				//upload 폴더에 파일을 저장
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";	
				
				File file = new File(path + vo.getNsfile());
				vo.getFile1().transferTo(file);
			}
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
	public String admin_notice_delete() {
		return "/admin/admin_notice/admin_notice_delete";
	}
	
	/**
	 * admin_notice_update.do : 공지사항 수정화면 
	 */
	@RequestMapping(value="/admin_notice_update.do", method=RequestMethod.GET)
	public String admin_notice_update() {
		return "/admin/admin_notice/admin_notice_update";
	}
	
	/**
	 * admin_notice_content.do : 공지사항 상세 정보 
	 */
	@RequestMapping(value="/admin_notice_content.do", method=RequestMethod.GET)
	public String admin_notice_content() {
		return "/admin/admin_notice/admin_notice_content";
	}
	
	/**
	 * admin_notice_list.do : 공지사항 전체리스트 
	 */
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		//String rpage = request.getParameter("rpage");
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = dao.totalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}

		ArrayList<CgvNoticeVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list",list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", reqPage);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	/**
	 * admin_member_content.do : 회원 상세 정보 
	 */
	@RequestMapping(value="/admin_member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
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
		
		//String rpage = request.getParameter("rpage");
		CgvMemberDAO dao = new CgvMemberDAO();
		
		//startCount, endCount
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = dao.totalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}


		ArrayList<CgvMemberVO> list = dao.selectAll(startCount, endCount);
		
		mv.addObject("list", list);
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", reqPage);
		mv.addObject("pageSize", pageSize);
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
