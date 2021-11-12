package com.care.root.member.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberMapper mapper;
	
	BCryptPasswordEncoder encoder;  //비밀번호 암호화 하기위한 변수. 생성자로 초기화 시켜준다.
	
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();  //객체 생성 완료 
	}

	@Override   // 내가 만든_로그인 체크 
	public int loginChk(String id, String pwd, Model model) {
		ArrayList<MemberDTO> dto = mapper.loginChk(id, pwd);
		
		if(dto.isEmpty()) {
			return 1;  // 아이디 없음 
		}// else if(!pwd.equals(dto.get(0).getPwd())) {   //비밀번호가 암호화되서 이렇게하면 검증 안됨 
			 else if(encoder.matches(pwd, dto.get(0).getPwd()) || pwd.equals(dto.get(0).getPwd())) {// (사용자가 입력한 평문 , 암호화되어있는 비밀번호)순서로 넣어야한다.  
				 															//이건 기존에 만들어둔것도 임시로 로그인 가능하게 하려고. 나중엔 encoder만 필요함.
			return 3; //로그인 성공
		} else {
			return 2; // 로그인 실패 . 비밀번호 틀림 
		}
		
	}
	
	@Override   //쌤이 한거 _ 로그인 체크 기능 
	public int userCheck(String id, String pw) {
		MemberDTO dto = mapper.getMember(id);
		if(dto != null) {
	//		if(pw.equals(dto.getPwd())) { //비밀번호가 암호화되서 이렇게하면 검증 안됨 
			if(encoder.matches(pw, dto.getPwd()) // (사용자가 입력한 평문 , 암호화되어있는 비밀번호)순서로 넣어야한다. 
					|| pw.equals(dto.getPwd())) {  				
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
		System.out.println("변경 전 : " + dto.getPwd());
		String securePw = encoder.encode(dto.getPwd());
		System.out.println("변경 후 : "  + securePw);
		
		dto.setPwd(securePw); //암호화된 값으로 비밀번호를 저장한다. 
		
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

	//쿠키값_ 여기서 Date는 sql의 date.
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		
//		mapper.keepLogin(sessionId, limitDate, id);
		mapper.keepLogin(map);
	}
	
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
	
	
}
