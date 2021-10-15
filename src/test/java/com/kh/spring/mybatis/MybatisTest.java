package com.kh.spring.mybatis;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
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
	private SqlSessionTemplate session;
	private final String NAMESPACE = "com.kh.spring.mybatis.mybatisMapper.";
	String userId = "DEV";

	@Test
	public void selectOneTest() {

		session.selectOne(NAMESPACE + "selectPasswordByUserId", userId);
	}

	@Test
	public void selectOneAsDTO() {
		Member member = session.selectOne(NAMESPACE + "selectMemberById", userId);
		System.out.println(member);
	}

	@Test
	public void selectListAsMap() {
		List<Map<String, Object>> res = session.selectList(NAMESPACE + "selectRentAndMemberById", userId);
		for (Map<String, Object> map : res) {
			System.out.println(map);
		}
	}

	@Test
	public void selectListUsingResultMap() {
		List<Map<String, Object>> res = session.selectList(NAMESPACE + "selectRentDataByUserId", userId);
		for (Map<String, Object> map : res) {
			System.out.println(map);
		}
	}

	@Test
	public void insertWithDTO() {
		Member member = new Member();
		member.setUserId("mybatis");
		member.setPassword("1234");
		member.setEmail("pclass@mybatis.com");
		member.setTell("010-1234-5678");

		session.insert(NAMESPACE + "insertWithDTO", member);

	}

	@Test
	public void insertWithMap() {
		Member member = new Member();
		member.setUserId("mybatis");

		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("member", member);
		commandMap.put("title", "mybatis 입문 서적 외 2권");
		commandMap.put("rentBookCnt", 3);

		session.insert(NAMESPACE + "insertWithMap", commandMap);
	}

	@Test
	public void delete() {
		String userId = "mybatis";
		session.delete(NAMESPACE + "delete", userId);
	}

	@Test
	public void update() {
		Member member = new Member();
		member.setUserId("mybatis");
		member.setPassword("abcdefg");

		session.update(NAMESPACE + "update", member);

	}

	@Test
	public void procedure() {
		String rbIdx = "100002";
		session.insert(NAMESPACE + "procedure", rbIdx);
	}

	@Test
	public void dynamicQueryIF() {
		// 사용자가 도서 검색필터에서 info를 선택하고 검색하면 사용자가 입력한 키워드로 info 컬럼을 검색
		// 사용자가 도서 검색필터에서 author를 선택하고 검색하면 사용자가 입력한 키워드로 author 컬럼을 검색
		// 사용자 선택 필터 : info
		// 사용자 입력 키워드 : 도시

	}

	@Test
	public void dynamicQueryChoose() {
		// 사용자가 도서 검색필터에서 info를 선택하고 검색하면 사용자가 입력한 키워드로 info 컬럼을 검색
		// 사용자가 도서 검색필터에서 author를 선택하고 검색하면 사용자가 입력한 키워드로 author 컬럼을 검색
		// 사용자가 검색필터를 지정 하지 않을 경우 도서 제목으로 검색
		// 사용자 선택 필터 : info
		// 사용자 입력 키워드 : 도시

	}

	@Test
	public void dynamicQuerySetTag() {
		// 사용자가 회원수정란에서 수정한 내용을 update하는 쿼리를 작성
		// 사용자가 password, email만 수정했다면, update쿼리를
		// [update tb_member set password = 1234, email = 'aa@aa.com' where userId =
		// 'aa']

	}

	@Test
	public void dynamicQueryForeachTagWithList() {
		// 만능 insert쿼리 생성
		session.selectList(NAMESPACE + "dynamicQueryForeachTagWithList", List.of("test", "insertTest", "insertTest2"));
	}

	@Test
	public void dynamicQueryWhereAndForeachTag() {
		// 검색조건을 or 연산으로 검색하기
		// 대출가능, 제목, 작가
		// 사용자가 입력한 키워드

	}

	@Test
	public void dynamicQueryForeachTag() {
		// 만능 insert쿼리 생성

	}
	
	
	
	
	
	
	
	
	
	
	
	//실습

	// 1. 도서명 : 쿠키와 세션,
	// 작가 : 김영아
	// 도서번호 : 시퀀스 사용
	// 인 도서를 BOOK 테이블에 저장하기
	// 메서드 이름 : test01

	@Test
	public void test01() {

		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("title", "쿠키와 세션");
		testMap.put("author", "김영아");

		session.insert(NAMESPACE + "test01", testMap);

	}

	// 2. 연장횟수가 2회 이상인 모든 대출도서 정보를
	// 연장횟수 0회로 초기화 해주세요.
	// 메서드 이름 : test02

	@Test
	public void test02() {
		int extensionCnt = 0;
		session.update(NAMESPACE + "test02", extensionCnt);
	}
	// 3. 2021년 9월 이전에 가입된 회원정보를 삭제
	// 메서드 이름 : test03

	@Test
	public void test03() {
		String regDate = "2021/09";
		session.delete(NAMESPACE + "test03", regDate);

	}

	// 4. 대출 횟수가 가장 많은 3권의 도서를 조회
	// 메서드 이름 : test04
	@Test
	public void test04() {
		int rentCnt = 4;
		List<Map<String, Object>> res = session.selectList(NAMESPACE + "test04", rentCnt);
		for (Map<String, Object> map : res) {
			System.out.println(map);
		}

	}

}
