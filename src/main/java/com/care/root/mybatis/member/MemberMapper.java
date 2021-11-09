package com.care.root.mybatis.member;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
//	public ArrayList<MemberDTO> loginChk(String id, String pwd);
	public ArrayList<MemberDTO> loginChk(@Param("id") String id, @Param("pwd") String pwd);
	public ArrayList<MemberDTO> memberList();
	public int insert(MemberDTO dto);
	
}
