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
	public int loginChk(String id, String pwd, Model model) { // 내가 만든_로그인 체크 
		ArrayList<MemberDTO> dto = mapper.loginChk(id, pwd);
		
		if(dto.isEmpty()) {
			return 1;  // 아이디 없음 
		} else if(!pwd.equals(dto.get(0).getPwd())) {
			return 2; // 비밀번호 틀림 
		} else {
			return 3; //로그인 성공
		}		 		
	}
	
	@Override   //쌤이 한거 _ 로그인 체크 기능 
	public int userCheck(String id, String pw) {
		MemberDTO dto = mapper.getMember(id);
		if(dto != null) {
			if(pw.equals(dto.getPwd())) {
				return 0;    //로그인 성공 
			}
		}
		return 1;   //로그인 실패 		
	}
	

	@Override
	public void memberList(Model model) {
		ArrayList<MemberDTO> d = mapper.memberList();
		model.addAttribute("dto",d);
//		model.addAttribute("d", mapper.memberList());    이렇게 한줄로 쓸 수 있다 
	}
/*
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
*/	
	public void memInfo(String id, Model model) {  //샘이 만든 코드_개인정보 불러오기 
		model.addAttribute("dto",mapper.getMember(id));
	}
	
	//샘이 만든 회원가입 . 아래에 내가 만든것처럼 복잡하게 하지 않아도, try, catch로 예외처리를 해주면~~된다!
	// 성공하면 1, 에러나 실패는 0 또는 -1을 리턴한다 . 1이나와야 성공이다 
	public int register(MemberDTO dto) {
		int result = 0;
		try {
			result = mapper.insert(dto);
		} catch (Exception e) {
			e.printStackTrace(); //어떤 에러가 발생됐는지 개발자가 알기 위함이다.
		}		
		return result;
	}
	
/*	public int register(MemberDTO dto) {
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
	}*/
	public void modifyMem(MemberDTO dto) {
		mapper.modify(dto);
	}
	
	public void deleteMem(String id) {
		mapper.delete(id);
	}

}
