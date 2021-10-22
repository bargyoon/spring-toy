package com.kh.spring.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.admin.model.service.AdminMemberService;
import com.kh.spring.member.model.dto.Member;

@Controller
@RequestMapping("admin")
public class AdminMemberController {

	//get 방식으로 /admin/member-list로 요청이 올 경우 호출 될 Controller메서드 작성
	//DataBase에서 현재 모든 회원의 정보를 조회해서, Controller메서드에서 출력
	//index.jsp로 요청 재지정
	//AdminMemberControllerTest 클래스를 생성해 200번 응답코드가 반환되면 테스트 통과 하도록 
	//테스트 메서드 작성
	
	@Autowired
	private AdminMemberService adminMemberService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("member-list")
	public void memberList(Model model) { 
		
		List<Member> memberList = adminMemberService.selectMemberList();
		model.addAttribute("members", memberList);
		
		
	}
	
	@PostMapping("member/delete")
	public String deleteMember(String userId) {
		adminMemberService.deleteMember(userId);
		
		
		return "index";
	}
}
