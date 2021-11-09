package com.care.root.member.service;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	
	public int loginChk(String id, String pwd, Model model);
	public void memberList(Model model);
	public MemberDTO memInfo(String id);
	public int register(MemberDTO dto);
}
