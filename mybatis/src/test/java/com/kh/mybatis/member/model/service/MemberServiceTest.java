package com.kh.mybatis.member.model.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

@DisplayName("MemberService 테스트")
@TestMethodOrder(OrderAnnotation.class)
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
	
	@Test
	@DisplayName("회원 수 조회 테스트") //테스트 이름 적는 것.
	@Order(1) // @Order() -> 테스트 순서 적는 것.
	public void getMemberCountTest() {
		int count = service.getMemberCount(); //멤버 수 카운트 값 가져오고.
		
		assertThat(count).isPositive().isGreaterThanOrEqualTo(0); //2 //assertThat : 코드의 가독성을 위해 쓰는것;;//뜻은 카운트의 값이.0보다 큰지 비교(=존재하는지?)
	}
	
	@Test
	@DisplayName("모든 회원 조회 테스트") 
	@Order(2) //두번째 순서
	public void findAllTest() {
		List<Member> members = null;  //멤버 리스트 객체에 null값 넣어주고
		
		members = service.findAll();
		
		assertThat(members).isNotNull()  //members에서 
						   .isNotEmpty()   
						   .extracting("id") //extracting = 추출 "id"를 추출?
						   .isNotNull()	
						  // .contains("ismoon","admin2");
						   .contains("admin2"); //admin2을 포함해서?
	}
	/*
	 * 첫번째는 ismoon이 userid로 들어가서 한번 테스팅해주고
	 * 두번째는 admin2가 userid로 들어가서 테스팅해준다.
	 * */

	@ParameterizedTest //하나의 테스트 메소드로 여러개의 파라미터에 대해서 테스트 할 수 있다.
	//@ValueSource(strings = {"ismoon", "admin2"})
	@ValueSource(strings= {"admin2"}) //원래 ParameterizedTest를 쓰려면 이렇게 ValueSource(=배열로 argument를 전달하는것)를 써야한다.
	@DisplayName("회원 조회 테스트(ID로 검색)")
	@Order(3)
	public void findMemberByIdTest(String userId) { //FIND MEMBER ID
		
		Member member = service.findMemberById(userId);
		
		assertThat(member).isNotNull()
						  .extracting("id")
						  .isEqualTo(userId);
	}
	
	@ParameterizedTest
	@CsvSource({
		"test1, 1234, 류동우",
		"test2, 4567, 이수정",
	})
	@DisplayName("회원 등록 테스트")
	@Order(4)
	public void insertMemberTest(String id, String password, String name) {
		int result = 0;
		Member findMember = null;
		Member member = new Member(id, password, name);
		
		result = service.save(member);
		// 실제로 DB에 Member가 저장되었는지 확인하기 위해서 다시 Member를 조회(ID로 조회)
		findMember = service.findMemberById(id);
		
		assertThat(result).isGreaterThan(0);
		assertThat(member.getNo()).isGreaterThan(0);
		assertThat(findMember).isNotNull().extracting("name").isEqualTo(name);
	}
	
	@ParameterizedTest
	@CsvSource({// 여러개 넘길때는 csvsource를 이용하면된다.
		"test1, 5678, 세종대왕",
		"test2, 0000, 광개토대왕",
	})
	@DisplayName("회원 정보 수정 테스트")
	@Order(5)
	public void updateMemberTest(String id, String password, String name) {
		int result = 0;
		Member member = null;
		Member findMember = null;
		
		member = service.findMemberById(id);
		
		member.setPassword(password);
		member.setName(name);
		
		result = service.save(member);
		
		// 실제로 DB에 Member가 수정되었는지 확인하기 위해서 다시 Member를 조회(ID로 조회)
		findMember = service.findMemberById(id);
		
		assertThat(result).isGreaterThan(0);
		assertThat(findMember.getName()).isNotNull().isEqualTo(name);
		assertThat(findMember.getPassword()).isNotNull().isEqualTo(password);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"test1", "test2"})
	@DisplayName("회원 삭제 테스트")
	@Order(6)
	public void deleteTest(String id) {
		int result = 0;
		Member findMember = null;
		Member member = service.findMemberById(id);
		
		result = service.delete(member.getNo());//update,delete,insert 다 jdbc이용하는것
		//영향받는 행의 갯수를 리턴
		// 실제로 DB에 Member가 삭제되었는지 확인하기 위해서 다시 Member를 조회(ID로 조회)
		findMember = service.findMemberById(id);
		
		assertThat(result).isPositive().isEqualTo(1);
		assertThat(findMember).isNull();
	}
}