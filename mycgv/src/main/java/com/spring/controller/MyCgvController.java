package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyCgvController {
	
	@RequestMapping(value = "/mycgv.do", method = RequestMethod.GET)
	public String mycgv() {
		return "/mycgv/mycgv";
	}
}
