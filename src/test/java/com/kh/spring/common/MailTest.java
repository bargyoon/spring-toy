package com.kh.spring.common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MailTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	
	@Test
	public void sendMail() throws MessagingException {
		 MimeMessage msg = mailSender.createMimeMessage();
	        msg.setFrom("bargyoon@gmail.com");
	        msg.setRecipients(Message.RecipientType.TO,"bargyoon@gmail.com");
	        msg.setSubject("메일테스트");
	        msg.setSentDate(new Date());
	        msg.setText("<h1>이메일 테스트 입니다.</h1>","utf-8","html");
	        mailSender.send(msg);
	}
	
	@Test
	public void restTemplateGetTest() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		String naverIndex = restTemplate.getForObject("https://www.naver.com", String.class);
		
		HttpHeaders kakaoHeader = new HttpHeaders();
		kakaoHeader.add("Authorization", "KakaoAK ce76be2d4df22f340c760f62a17e1cc1");
		
		RequestEntity request = RequestEntity.get("https://dapi.kakao.com/v3/search/book?query=java")
				.headers(kakaoHeader).build();
		String obj =  restTemplate.exchange(request, String.class).getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(obj);
		
		for (JsonNode jsonNode : root.findValues("title")) {
			logger.debug(jsonNode.asText());
		}
		
		
		
		//logger.debug(obj);
	}
	

	@Test
	public void restTemplatePostTest() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		String naverIndex = restTemplate.getForObject("https://www.naver.com", String.class);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.add("X-Naver-Client-Id", "aI0JHEG5c20DAUzp7i0I");
		header.add("X-Naver-Client-Secret", "0PbC_sdUDY");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("source", "ko");
		body.add("target", "en");
		body.add("text", "만나서 반갑습니다.");
		RequestEntity<Map> request = RequestEntity.post("https://openapi.naver.com/v1/papago/n2mt")
				.headers(header).body(body);
		
		String obj = restTemplate.exchange(request, String.class).getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(obj);
		for (JsonNode jsonNode : root.findValues("translatedText")) {
			logger.debug(jsonNode.asText());
		}	}
	
	
	
}
