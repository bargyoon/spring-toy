package com.kh.spring.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.member.model.dto.Member;

@Mapper
public interface MybatisRepository {
	
	@Select("select password from member where user_id = #{userId}")
	String selectPasswordByUserId(String userId);
	
	@Select("select * from member where user_id = #{userId}")
	Member selectMemberById(String userId);
	
	List<Map<String,Object>> selectRentAndMemberById(String userId);
	
	List<Map<String,Object>> selectRentDataByUserId(String userId);
	
	@Insert("insert into member(user_id,password,tell,email) values(#{userId},#{password},#{tell},#{email})")
	void insertWithDTO(Member member);
	
	@Insert("insert into rent_master(rm_idx,user_id,title,rent_book_cnt) values(sc_rm_idx.nextval,#{member.userId},#{title},#{rentBookCnt})")
	void insertWithMap(Map<String,Object> commandMap);
	
	@Delete("delete from rent_master where user_id = #{userId}")
	void delete(String userId);
	
	@Update("update member set password = #{password} where user_id = #{userId}")
	void update(Map<String,Object> commandMap);
	
	
	void procedure(String rbIdx);
	
	Map<String,Object> dynamicQueryIf(Map<String,Object> commandMap);
	Map<String,Object> dynamicQueryChoose(Map<String,Object> commandMap);
	Map<String,Object> dynamicQueryWhereAndForeachTag(Map<String,Object> commandMap);
	Map<String,Object> dynamicQueryForeachTagWithList(Map<String,Object> commandMap);
	
	void insertTemplate(Map<String,Object> commandMap);
	
	void dynamicQuerySetTag(Member member);
	
}
