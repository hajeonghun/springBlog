package com.example.spring02.controller.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("member/*")
public class MemberController {

	@Autowired
	MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}

	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session) {
		boolean result = memberService.loginCheck(dto, session);
		ModelAndView mav = new ModelAndView();
		if (result) {
			mav.setViewName("home");
			mav.addObject("message", "success");
		} else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		
		memberService.logout(session);
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
	}
	
		
}
