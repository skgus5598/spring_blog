package com.care.root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:testMember.xml",
										"file:src/main/webapp/WEB-INF/spring/root-context.xml"
										})
public class TestMock {
	
	@Autowired MemberController mc;
	MockMvc mock; //
	
	@Before
	public void setUp() {
		System.out.println("@test 실행 전 초기화하는 용도 = @before");
		mock = MockMvcBuilders.standaloneSetup(mc).build();
	}
	@Test
	public void testIndex() throws Exception {
		System.out.println("@test실행됨 ");
		mock.perform(get("/index")).andDo(print())
		.andExpect(status().isOk()) //현재 상태가 200이냐 
		.andExpect(forwardedUrl("default/main"));//리턴 경로가 다음과 같냐 
	}
	
	@Test
	@Transactional(transactionManager = "txMgr")
	public void testInsert() throws Exception {
		mock.perform(post("/register").param("id", "nanana").param("pwd", "999")
				.param("addr", "australia"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	
	

}









