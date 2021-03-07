package com.bluessom.toyapp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluessom.toyapp.member.Member;
import com.bluessom.toyapp.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	//join
	@RequestMapping("/joinForm")
	public String joinFrom(Member meber) {
		return "/member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinOk(Member member) {
		
		service.memberJoin(member);
		
		//회원가입 후 다시 메인 페이지로, 별도의 완료 표시 필요할듯
		return "index";
	}
	
	//login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginOk(Member member, HttpSession session) {
		
		Member searchedMem = service.memberSearch(member);
		
		if(searchedMem == null) return "/member/loginForm";
		
		session.setAttribute("member", searchedMem);
		
		//로그인 후 메인 페이지로
		return "index";
	}
	
	//logout
	@RequestMapping("/logout")
	public String logout(Member member, HttpSession session) {
		
		session.invalidate();
		
		//로그아웃 후 메인 페이지로
		return "/member/loginForm";
	}
	
	@RequestMapping("/logoutOk")
	public String logoutOk(Member member) {
		
		return "index";
	}
	
	//modify
	/*
	@RequestMapping("/modifyForm")
	public String modifyForm(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Member modifiedMem = service.memberModify(member);
		
		if(modifiedMem == null) return "/modifyForm";
		else {
			session.setAttribute("member", modifiedMem);
			//member 바뀐값으로 설정
		}
		
		return "/member/modifyForm";
	}
	*/
}
