package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class LoginController {
	/**
	 * loginCheck.do : 로그인 처리
	 */
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.select(vo);
		
		if(result == 1){
			//로그인 성공 --> session객체에 key,value 추가 후 index 페이지로 이동
			mv.addObject("login_result","ok");
			mv.setViewName("index");
		}else{
			mv.addObject("login_result","fail");
			mv.setViewName("/login/login");
		}
				
		return mv;
	}
	
	/**
	 * login.do : 로그인 폼
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
}
