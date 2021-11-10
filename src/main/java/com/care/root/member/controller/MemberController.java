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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberServiceImpl;



@Controller
@RequestMapping("member")
public class MemberController implements MemberSessionName{
	
	@Autowired MemberServiceImpl ms;
	
	@GetMapping("index")
	public String index() {	return "default/main"; 	}
	
	@GetMapping("/login")
	public String loginPage() { return "member/login";	}
	
	@PostMapping("chkUser")
	public String chkUser(@RequestParam String id, @RequestParam String pwd, Model model) {
		int result = ms.loginChk(id, pwd, model);
		model.addAttribute("result", result);		
		model.addAttribute("id",id);
		return "member/successLogin";
	}
	
//  쌤이 만든 로그인 검증 , alert가 따로 없어 
	@PostMapping("/chkUser2")
	public String chkUser(@RequestParam String id, @RequestParam String pwd,
												RedirectAttributes rs) {
		
		int result = ms.userCheck(id, pwd);
		if(result == 0) {
			rs.addAttribute("id",id);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@RequestMapping("successLogin")
	public String loginSuccess(HttpSession session, @RequestParam String id) {
		session.setAttribute(MemberSessionName.LOGIN, id );
		return "default/main";
	}
	
/*	@GetMapping("logout")   // 내 코드 logout 
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
*/	@GetMapping("logout")    //샘님 코드 logout
	public String logout(HttpSession session) {
		if(session.getAttribute(MemberSessionName.LOGIN ) != null) {
			session.invalidate();
		}
		return "member/login";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		ms.memberList(model);
		return "member/memberInfo";
	}
	
	@GetMapping("memInfo")
	public String memInfo(@RequestParam String id, Model model) {
		ms.memInfo(id,model);		
		return "member/info";
	}	
	
	@PostMapping("register") //내가만든 회원가입 
	public void register(MemberDTO dto, HttpServletResponse response) {
		int result = ms.register(dto);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(result == 1) {						
			out.print("<script> alert('회원가입 성공! 다시 로그인해 주세요~');"
					+ "location.href='login'; </script>");					
		} else {			
			out.print("<script> alert('중복된 아이디가 존재합니다. 회원가입 실패~'); "
					+ "location.href='login';</script>");				
		}		
	}
	//샘님이 만든 회원가입 
	public String register2(MemberDTO dto) {
		int result = ms.register(dto);
		if(result == 1) 
			return "redirect:login";
		return "redirect:register";
		}
	
	//회원정보 수정하기위해 정보 불러온 후 수정창으로 이동 
	@GetMapping("modify")
	public String modify(@RequestParam String id, Model model) {
		ms.memInfo(id,model);			
		return "member/modifyMem";
	}
	//회원정보 수정 
	@PostMapping("modifyMem")
	public String modifyMem(MemberDTO dto, RedirectAttributes rs ) {
		ms.modifyMem(dto);
		rs.addAttribute("id", dto.getId());
		return "redirect:memInfo";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam String id, HttpSession session) {
		ms.deleteMem(id);
		session.invalidate();
		return "redirect:memberList";
	}
	
}








