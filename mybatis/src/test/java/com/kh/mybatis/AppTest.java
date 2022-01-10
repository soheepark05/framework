package com.kh.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kh.mybatis.common.template.SqlSessionTemplate;
//TDD : 설계 -> 단위 테스트 코드 -> 구현코드 : 짧은 주기로 반복해서 작업을 진행한다.
//장점) 1. 객체지향적인 코드를 만들 수 있다. 2. 테스트코드 먼저 만들어서 빠르게 수정가능하다.
//3. 설계에 대한 수정 시간을 단축시킬 수 있다. 4. 디버깅과 유지보수 시간을 단축시킬수 있다.

@DisplayName("첫 번째 테스트 코드 작성")
public class AppTest {
	//test클래스 쓰고.
	//@Test
	//public void shouldAnswerWithTrue() {
	//	assertTrue(true);
//	} ---> test method

	private SqlSession session = null;
	
	// 테스트 메소드들이 실행되기 전에 실행되는 메소드 (가장 먼저 한 번만 실행된다.)
	@BeforeAll
	public static void init() {
		System.out.println("@BeforeAll");
	}
	
	// 각각의 테스트 메소드들이 실행되기 전에 무조건 실행되는 메소드
	@BeforeEach
	public void setup() {
		System.out.println("@BeforeEach");
		
		session = SqlSessionTemplate.getSession();
	}
	
	@Test //이거 붙어있으면 TEST 메소드라고 한다. test 메소드는 public 혹은 void로 만들어야함
	@Disabled // 테스트 클래스 또는 메소드를 비 활성할 수 있다. 
	public void nothing() {//처음에 태스트가 되는지 확인만 하는것!!
		// 이 테스트 메소드를 통해서 현재 프로젝트가 테스트 가능한 환경인지 확인한다.
	}
	
	@Test
	@DisplayName("SqlSession 생성 테스트")
	public void create() {
		assertNotNull(session);
	}
}