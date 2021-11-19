package com.care.root.member.service;

import java.sql.Date;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	
	public int loginChk(String id, String pwd, Model model); // 내가 만든거_로그인 체크 
	public int userCheck(String id, String pw); //쌤이 만든거 _로그인 체크 
	public void memberList(Model model);
//	public MemberDTO memInfo(String id);  내가 만든거 
	public void memInfo(String id, Model model); // 쌤이 만든거_개인정보불러오
	public int register(MemberDTO dto);
	public void modifyMem(MemberDTO dto); //수정하기 
	public void deleteMem(String id); // 삭제하기 
	
	public void keepLogin(String sessionId, Date limitDate, String id); // 로그인 유지 쿠키 
	public MemberDTO getUserSessionId(String sessionId);
}
