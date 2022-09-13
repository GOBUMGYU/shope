package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;
import com.spring.service.MemberServiceImpl;

@Controller
public class JoinController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	/**
	 * joinCheck.do : 회원가입 처리
	 */
	@RequestMapping(value="/joinCheck.do", method=RequestMethod.POST)
	public ModelAndView joinCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.getJoinResult(vo);
		
		if(result == 1){
			mv.addObject("join_result","ok");
			mv.setViewName("/login/login");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * join.do : 회원가입 폼
	 */
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
