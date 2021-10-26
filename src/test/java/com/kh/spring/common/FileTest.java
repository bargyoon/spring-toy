package com.kh.spring.common;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class FileTest {

	@Autowired
	WebApplicationContext context;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private MockMvc mockMvc;
	
	
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(context).build();
	}
	
	@Test
	public void downloadTest() throws Exception{
		mockMvc.perform(get("/download")
				.param("originFileName", "OFN.txt")
				.param("renameFileName","6e37fe0e-9405-4c96-b73a-afa3ac4687c5.txt")
				.param("savePath", "2021\\10\\25\\"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
