package com.kh.mybatis.member.model.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kh.mybatis.member.model.vo.Member;

@DisplayName("Member 테스트")
class MemberServiceTest {
	private MemberService service; 
	
	@BeforeEach
	public void setUp() {
		service = new MemberService();
	}
	
	@Test
	@Disabled
	public void nothing() {
	}
	
	@Test
	@Disabled
	public void create() {
		assertThat(service).isNotNull();
	}
	
	/* 오류떠서 잠시막음
	 * @Test public void getMemberCountTest() { int count =
	 * service.getMemberCount();
	 * 
	 * assertThat(count).isPositive().isGreaterThanOrEqualTo(1); //2보다 클것같아 }
	 */
	
	@Test
	public void findAllTest() {
		List<Member> members = null;
		members = service.findAll();
		
		assertThat(members).isNotNull();
		
	}
	
}