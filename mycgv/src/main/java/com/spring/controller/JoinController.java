package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class JoinController {
	
	
	/**
	 * id_check.do : ���̵� �ߺ� üũ
	 */
	@ResponseBody
	@RequestMapping(value = "/id_check.do", method = RequestMethod.GET)
	public String id_check(String id) {
		CgvMemberDAO dao = new CgvMemberDAO();
		int reulst = dao.idCheck(id);
		
		//Ajax�� �����ϴ� ����� �ݵ��!! ���ڿ�(String)���·� �����ؾ� �Ѵ�.
		//�ڹٿ��� ����ȯ : Integer.parseInt(type), String valueOf(type)
		
		return String.valueOf(reulst);
	}
	
	/**
	 * joinCheck.do : ȸ������ ó��
	 */
	@RequestMapping(value="/joinCheck.do", method=RequestMethod.POST)
	public ModelAndView joinCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);
		
		if(result == 1){
			mv.addObject("join_result","ok");
			mv.setViewName("/login/login");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * join.do : ȸ������ ��
	 */
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
