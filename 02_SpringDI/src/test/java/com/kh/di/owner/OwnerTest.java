package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.di.pet.Dog;
//import com.kh.di.pet.Cat;

class OwnerTest {

	
	  @Test
	  @Disabled 
	  public void nothing() {  
	  }
	  
	  @Test 
	  public void create() {
		// Owner owner = new Owner("아무개", 20, new Cat("나비"));
		//기존의 자바 애플리케이션에서는 다형성과 생성자 주입을 통해 객체간의 결합을 느슨하게 만들어 준다.
	  Owner owner = new Owner("아무개", 20, new Dog("댕댕이")); //
	  
		  assertThat(owner).isNotNull();
		 // assertThat(owner.getDog()).isNotNull();
		  assertThat(owner.getPet()).isNotNull();
	  
	  }
	 @Test
	 public void contextTest() {
		 //스프링의 애플리케이션 컨텍스를 활용하여 객체 안의 결합을 더욱 느슨하게 만들어준다.
		 //new GenericXmlApplicationContext("클래스 패스 상의 xml 파일의 위치 지정");
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("spring/root-context.xml"); //오류난다.
		//Owner owner = (Owner) context.getBean("owner");
		Owner owner = context.getBean("owner", Owner.class);
		
		System.out.println(owner); // 제대로 되는지 동작 테스트
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	 }

}
