package com.kh.spring.member.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;

@Mapper
public interface MemberRepository {

	@Select("select password from member where user_id = #{userId}")	
	String selectPasswordByUserId(String userId);

	@Insert("insert into member(user_id,password,tell,email) values(#{userId},#{password},#{tell},#{email})")
	void insertMember(JoinForm form);
	
	@Select("select * from member")
	List<Member> selectMemberList();

	@Delete("delete from member where user_id = #{userId}")
	void deleteMember(String userId);

	@Select("select * from member where user_id = #{userId} and password = #{password}")
	Member authenticateMember(Member member);

	@Select("select * from member where user_id = #{userId}")
	Member selectMemberByUserId(String userId);
}
