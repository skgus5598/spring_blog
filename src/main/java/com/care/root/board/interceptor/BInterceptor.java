package com.care.root.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.session.MemberSessionName;

public class BInterceptor extends HandlerInterceptorAdapter implements MemberSessionName {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("게시판 사용자 개별 인터셉터 동작 확인 ");
		
		return true;
	}
	

}
