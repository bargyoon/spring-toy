package com.kh.spring.member.model.service;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.common.code.Config;
import com.kh.spring.common.mail.sender.MailSender;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.validator.JoinForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final RestTemplate template;
	private final MailSender mailSender;
	private final MemberRepository memberRepository;
	private final PasswordEncoder password;
	

	public String selectPasswordById() {
		return memberRepository.selectPasswordByUserId("DEV");
	}

	public void insertMember(JoinForm form) {
		
		
		memberRepository.insertMember(form);
		
	}

	public Member authenticateMember(Member member) {
		return memberRepository.authenticateMember(member);
		
	}

	public Member selectMemberByUserId(String userId) {
		
		return memberRepository.selectMemberByUserId(userId);
	}

	public void authenticateByEmail(JoinForm form, String token) {
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("mail-template", "join-auth-email");
		body.add("userId", form.getUserId());
		body.add("persistToken",token);
		//RestTemplate의 기본 ContentType이 application/json이다.
		RequestEntity<MultiValueMap<String,String>> request = RequestEntity
				.post(Config.DOMAIN.DESC+"/mail")
				.accept(MediaType.APPLICATION_FORM_URLENCODED)
				.body(body);
		
		String htmlText = template.exchange(request, String.class).getBody();
		mailSender.sendEmail(form.getEmail(), "회원가입을 축하합니다.", htmlText);
		
		
	}
}
