package com.care.root.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	

	
	@GetMapping("/login")
	public String loginPage() { return "member/login";	}
	
	@PostMapping("chkUser2")
	public String chkUser(@RequestParam String id, 
										  @RequestParam String pwd, 
										  @RequestParam(required = false) String autoLogin, //@requestParam("autoLogin") String auto 이름 다르면 이렇게 
										  Model model ) {
		
		System.out.println("autoLogin : " + autoLogin); //체크하면 on이 출력되고, 체크하지 않으면 400에러가 뜬다(근데 위에 required해서 null이 뜬당)
		
		int result = ms.loginChk(id, pwd, model);
		model.addAttribute("result", result);		
		model.addAttribute("id",id);

		return "member/successLogin";
	}
	
//  쌤이 만든 로그인 검증 , alert가 따로 없어 
	@PostMapping("/chkUser")
	public String chkUser(@RequestParam String id, @RequestParam String pwd,
										@RequestParam(required = false) String autoLogin,
												RedirectAttributes rs) {		
		int result = ms.userCheck(id, pwd);
		if(result == 0) {
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@RequestMapping("successLogin")
	public String loginSuccess(HttpSession session, @RequestParam String id,
														@RequestParam(required = false) String autoLogin,
														HttpServletResponse response) {
		if(autoLogin != null) {
			int limitTime = 60*60*24*90; //90
			Cookie loginCookie = new Cookie("loginCookie", session.getId()); //쿠키값은 유일한 값으로 하는게 좋다.동일한 값이 없는 것으로!
			loginCookie.setMaxAge(limitTime);
			loginCookie.setPath("/"); //최상위를 두겠다. 모든 경로에서 다 사용하겠다.
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();
	//		cal.setTime(new java.util.Date()); //얘는 자바의 유틸 . 자바에서 시간설정해주는것 .아래와 같이 써줘도 됨 
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, 3); // 위에 셋팅한 현재의 날짜에서 month는 3개월 후로 설정해 주세요.
			//위의 java util의 date를  sql타입의 date로 변환하여 넣어준다.
			java.sql.Date limitDate = new java.sql.Date(cal.getTimeInMillis()); 
			ms.keepLogin(session.getId(), limitDate, id);
		}
		session.setAttribute(MemberSessionName.LOGIN, id );
		return "default/main";
	}
	
/*	@GetMapping("logout")   // 내 코드 logout 
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
*/	@GetMapping("logout")    //샘님 코드 logout
	public String logout(HttpSession session, HttpServletResponse response,
									@CookieValue(value="loginCookie", required=false) Cookie loginCookie) {
		if(session.getAttribute(MemberSessionName.LOGIN ) != null) {
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);  //시간을 0을 바꿔버림. 만료시킴 
				loginCookie.setPath("/"); // 경로가 같아야 같은 쿠키로 인식한다. 최상위부터 하위에있는 모든 쿠키를 삭제 
				response.addCookie(loginCookie); //사용자에게 전달되면서 쿠키가 사라짐. 
				ms.keepLogin("nan",	new java.sql.Date(System.currentTimeMillis()), //아~더이상 자동로그인을 하지 않는구나 하고 nan으로 바꿈 		
											(String)session.getAttribute(LOGIN));
			}
			session.invalidate();
			
			
		}
		return "member/login";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model, HttpSession session) {
	//	if(session.getAttribute(LOGIN) != null) {
			ms.memberList(model);
			return "member/memberInfo";
	//	}
	//	return "redirect:login";
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








