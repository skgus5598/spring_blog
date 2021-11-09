package com.care.root;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.member.controller.MemberController;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberServiceImpl;
import com.care.root.mybatis.member.MemberMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:testMember.xml",
										"file:src/main/webapp/WEB-INF/spring/root-context.xml"
										})
public class TestMember {
	@Autowired MemberController mc;
	@Autowired MemberServiceImpl ms;
	@Autowired MemberMapper mapper;
	
	@Test
	public void testMapper() {
		assertNotNull(mapper); //객체가 잘 만들어 졌는가 
	}

	@Test   // mapper(=dao)의 insert 테스트 
	public void testInsert() {
		MemberDTO dto = new MemberDTO();
		dto.setId("ababab");
		dto.setPwd("123");
		dto.setAddr("seoulcity");
		mapper.insert(dto);		
	}
	
	@Test
	public void testMemberList() {  
		ArrayList<MemberDTO> list = mapper.memberList();
		System.out.println(list.size());
	}
	

}
