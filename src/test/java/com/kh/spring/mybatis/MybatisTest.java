package com.kh.spring.mybatis;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring.member.model.dto.Member;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
public class MybatisTest {

	// SqlSessionTemplate 주요 메서드
	// selectOne : 단일행을 반환하는 select문 실행 메서드
	// selectList : 다중행을 반환하는 select문 실행 메서드
	// insert : 메서드의 결과값은 쿼리에 의해 영향을 받은 row 수
	// update :
	// delete :
	// ** procedure 호출은 dml쿼리 메서드 중 선택(아무거나 적절한 것)

	@Autowired
	private MybatisRepository mybatisRepository;
	String userId = "DEV";

	@Test
	public void selectOneTest() {
		mybatisRepository.selectPasswordByUserId(userId);
	}

	@Test
	public void selectOneAsDTO() {
		mybatisRepository.selectMemberById(userId);
	}

	@Test
	public void selectListAsMap() {
		mybatisRepository.selectRentAndMemberById(userId);
	}

	@Test
	public void selectListUsingResultMap() {
		mybatisRepository.selectRentDataByUserId(userId);
	}

	@Test
	public void insertWithDTO() {
		Member member = new Member();
		member.setUserId("mybatisTest");
		member.setPassword("1234");
		member.setTell("010-1234-1234");
		member.setEmail("asd@asd.com");

		mybatisRepository.insertWithDTO(member);
	}

	@Test
	public void insertWithMap() {
		Member member = new Member();
		member.setUserId("mybatisTest");
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("member", member);
		commandMap.put("title", "실습");
		commandMap.put("rentBookCnt", 1);
		mybatisRepository.insertWithMap(commandMap);
	}

	@Test
	public void delete() {
		mybatisRepository.delete(userId);
	}

	@Test
	public void update() {
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("userId", userId);
		commandMap.put("password", "!234");

	}

	@Test
	public void procedure() {
		mybatisRepository.procedure("100002");
	}

	@Test
	public void dynamicQueryIF() {
		// 사용자가 도서 검색필터에서 info를 선택하고 검색하면 사용자가 입력한 키워드로 info 컬럼을 검색
		// 사용자가 도서 검색필터에서 author를 선택하고 검색하면 사용자가 입력한 키워드로 author 컬럼을 검색
		// 사용자 선택 필터 : info
		// 사용자 입력 키워드 : 도시

		Map<String, Object> commandMap = new HashMap<String, Object>();

		commandMap.put("searchType", "info");
		commandMap.put("keyword", "도시");
		mybatisRepository.dynamicQueryIf(commandMap);

	}

	@Test
	public void dynamicQueryChoose() {
		
		mybatisRepository.dynamicQueryChoose(Map.of("searchType", "info","keyword", "도시"));

	}

	@Test
	public void dynamicQueryWhereAndForeachTag() {
		Map<String, Object> commandMap = new HashMap<String, Object>();
		String[] searchTypes = { "title", "author" };
		commandMap.put("searchTypes", searchTypes);
		commandMap.put("keyword", "김영애");

		mybatisRepository.dynamicQueryWhereAndForeachTag(commandMap);

	}

	@Test
	public void dynamicQueryForeachTagWithList() {
		String[] nameList = { "admin", "DEV" };
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("list", nameList);
		mybatisRepository.dynamicQueryForeachTagWithList(commandMap);

	}

	@Test
	public void dynamicQueryForeachTag() {
		Map<String, Object> commandMap = new LinkedHashMap<String, Object>();
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("user_id", "mybatiss");
		data.put("password", "1234");
		data.put("tell", "010-1234-4321");
		data.put("email", "bbb@bbb.com");

		commandMap.put("tableName", "member");
		commandMap.put("data", data);
		mybatisRepository.insertTemplate(commandMap);

	}

	@Test
	public void dynamicQuerySetTag() {
		Member member = new Member();

		member.setPassword("1111");
		mybatisRepository.dynamicQuerySetTag(member);

	}


	@Test
	public void test01() {

	}

	@Test
	public void test02() {
	}

	@Test
	public void test03() {

	}

	@Test
	public void test04() {

	}

}
