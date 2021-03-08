package com.bluessom.toyapp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String joinOk(Member member, HttpServletRequest request) {
		
		String checkPw = request.getParameter("checkPw");
		HttpSession session = request.getSession();
		
		if(member.getMemPw().equals(checkPw)) {
			service.memberJoin(member);
			session.setAttribute("member", member);
		}
		else {
			return "redirect:/member/joinForm";
		}
		
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
		return "redirect:/";
	}
	
	//logout
	@RequestMapping("/logout")
	public String logout(Member member, HttpSession session) {
		
		session.invalidate();
		
		//로그아웃 후 메인 페이지로
		return "redirect:/";
	}
	
	//modify
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("member", member);
		//String newPw = request.getParameter("newPw");
		//System.out.println(newPw);
		
		//Member modifiedMem = service.memberModify(member);
		
		//if(modifiedMem == null) return "/modifyForm";
		//else {
		//	session.setAttribute("member", modifiedMem);
			//member 바뀐값으로 설정
		//}
		
		return "/member/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member curMem = (Member) session.getAttribute("member");
		
		String newPw = request.getParameter("newPw");
		if (newPw == null) return "redirect:/member/modifyForm";
		
		if(curMem.getMemPw().equals(member.getMemPw())) {
			curMem.setMemPw(newPw);
		}
		else {
			return "redirect:/member/modifyForm";
		}
		
		Member modifiedMem = service.memberModify(curMem);
		
		if(modifiedMem == null) return "/member/modifyForm";
		else {
			session.setAttribute("member", modifiedMem);
		}
		
		return "redirect:/";
	}
	
	//remove
	@RequestMapping("/remove")
	public String remove(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member curMem = (Member) session.getAttribute("member");
		
		int result = 0;
		if(curMem.getMemPw().equals(member.getMemPw())) {
			result = service.memberRemove(curMem);
		}
		else {
			return "redirect:/member/modifyForm";
		}
		
		if(result == 0) return "redirect:/member/modifyForm";
		session.invalidate();
		
		return "redirect:/";
	}
}
