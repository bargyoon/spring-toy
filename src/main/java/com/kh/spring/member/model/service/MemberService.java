package com.kh.spring.member.model.service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;

public interface MemberService {
	
	String selectPasswordById();

	void insertMember(JoinForm form);

	Member authenticateMember(Member member);

	Member selectMemberByUserId(String userId);

	void authenticateByEmail(JoinForm form, String token);
}
