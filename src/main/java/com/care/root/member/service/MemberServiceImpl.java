package com.care.root.member.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberMapper mapper;

	@Override
	public int loginChk(String id, String pwd, Model model) {
		ArrayList<MemberDTO> dto = mapper.loginChk(id, pwd);
		
		if(dto.isEmpty()) {
			return 1;  // 아이디 없음 
		} else if(!pwd.equals(dto.get(0).getPwd())) {
			return 2; // 비밀번호 틀림 
		} else {
			return 3; //로그인 성공
		}		 		
	}

	@Override
	public void memberList(Model model) {
		ArrayList<MemberDTO> d = mapper.memberList();
		model.addAttribute("dto",d);
	}

	public MemberDTO memInfo(String id) {
		ArrayList<MemberDTO> list = mapper.memberList();
		MemberDTO d = null;
		for(int i=0; i<list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				d = new MemberDTO();
				d.setId(id);
				d.setPwd(list.get(i).getPwd());
				d.setAddr(list.get(i).getAddr());
			}
		}
		return d;
	}
	
	public int register(MemberDTO dto) {
		int result = dupleChk(dto);
		if(result == 1) {
			mapper.insert(dto);
		}
		return result;
	}
	
	private int dupleChk(MemberDTO dto) {
		ArrayList<MemberDTO> list = mapper.memberList();		
		int result = 0;
		for(int i=0; i<list.size(); i++) {
			if(dto.getId().equals(list.get(i).getId())) {
				result = -1;
				break;
			} else {
				result = 1;
			}
		}
		return result;		
	}
}
