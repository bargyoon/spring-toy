package com.kh.spring.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class AdminMemberControllerTest {
	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(context).build();
	}
	
	@Test
	public void selectMemberListTest() throws Exception{
		mockMvc.perform(get("/admin/member-list"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
		.andDo(print());
	}
	
	@Test
	public void deleteMember() throws Exception{
		mockMvc.perform(post("/admin/member/delete").param("userId","testUser"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
		.andDo(print());
	}
}
