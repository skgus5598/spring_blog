package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
//	public ArrayList<MemberDTO> loginChk(String id, String pwd);
	public ArrayList<MemberDTO> loginChk(@Param("id") String id, @Param("pwd") String pwd);
	public MemberDTO getMember(String id); // 쌤이 한_로그인체크 
	public ArrayList<MemberDTO> memberList();
	public int insert(MemberDTO dto);
	public void modify(MemberDTO dto);
	public void delete(String id);
	
	public void keepLogin(Map<String, Object> map);
	public MemberDTO getUserSessionId(String sessionId);
}
