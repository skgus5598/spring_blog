package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter implements MemberSessionName {
	
	@Autowired MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("auto login execute");
/*		for(Cookie c : request.getCookies()) {   //생성된 모~~든 쿠키값을 가져와서 비교하던 기존 방법.
			if(c.getName().equals("loginCookie")) {
				
			}
		}
*/		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSessionId(loginCookie.getValue());
			if(dto != null) {
/*				HttpSession session = request.getSession();    // 이 두줄이 아래 한줄과 같다 
				session.setAttribute(LOGIN, dto.getId());
*/				request.getSession().setAttribute(LOGIN, dto.getId());
			}
		}		
		return true;
	}

}
