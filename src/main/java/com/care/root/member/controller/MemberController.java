package com.care.root.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberServiceImpl;

@Controller
public class MemberController {
	
	@Autowired MemberServiceImpl ms;
	
	@GetMapping("index")
	public String index() {	return "default/main"; 	}
	
	@GetMapping("login")
	public String loginPage() { return "member/login";	}
	
	@PostMapping("chkUser")
	public String chkUser(@RequestParam String id, @RequestParam String pwd, Model model) {
		int result = ms.loginChk(id, pwd, model);
		model.addAttribute("result", result);		
		model.addAttribute("id",id);
		return "member/successLogin";
	}
	
	@RequestMapping("successLogin")
	public String loginSuccess(HttpSession session, @RequestParam String id) {
		session.setAttribute("loginUser", id );
		return "default/main";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		ms.memberList(model);
		return "member/memberInfo";
	}
	
	@GetMapping("memInfo")
	public String memInfo(@RequestParam String id, Model model) {
		MemberDTO d = ms.memInfo(id);
		model.addAttribute("dto", d);
		return "member/info";
	}
	
	@PostMapping("register")
	public void register(MemberDTO dto, HttpServletResponse response) {
		int result = ms.register(dto);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(result == -1) {						
			out.print("<script> alert('중복된 아이디가 존재합니다. 회원가입 실패');"
					+ "location.href='login'; </script>");			
		} else {			
			out.print("<script> alert('회원가입 성공! 다시 로그인해 주세요~'); "
					+ "location.href='login';</script>");			
		}		
	}
	
}








