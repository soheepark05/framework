package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.RootConfig;
import com.kh.di.pet.Cat;

// JUnit에서 스프링을 사용할 수 있도록 SpringExtension.class를 사용하여 기능을 확장한다.
// 해당 설정이 있어야 @ContextConfiguration()을 통해서 설정 파일을 읽고 애플리케이션 컨텍스트를 생성할 수 있다. 
@ExtendWith(SpringExtension.class)
// @ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
@ContextConfiguration(classes = {RootConfig.class})
//@ContextConfiguration(classes = {
//	OwnerConfig.class,
//	PetConfig.class
//})
class OwnerTest {
	// 애플리케이션 컨텍스트 상에서 클래스 타입과 일치하는 빈을 자동으로 주입시켜준다.
	// 이때 동일한 클래스 타입에 빈이 여러 개 존재할 경우 @Qualifier("빈 ID")를 명시적으로 넣어주어야 한다.
	@Autowired
	@Qualifier("hong")
	private Owner owner;

	@Test
	@Disabled
	public void nothing() {
	}
	
	@Test
	public void create() {
		// 기존에 자바 애플리케이션에서는 다형성과 생성자 주입을 통해 객체간의 결합을 느슨하게 만들어준다.
		Owner owner = new Owner("문인수", 20, new Cat("나비"));
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	public void contextTest() {
		// 스프링의 애플리케이션 컨텍스를 활용하여 객체 간의 결합을 더욱 느슨하게 만들어준다.
		// new GenericXmlApplicationContext("클래스패스 상의 xml 파일의 위치 지정");		
		
		ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		//		new GenericXmlApplicationContext("spring/root-context.xml");
		//		new GenericXmlApplicationContext("classpath:spring/root-context.xml");
		//		new GenericXmlApplicationContext("file:src/main/resources/spring/root-context.xml");
		//		new GenericXmlApplicationContext("spring/owner-context.xml", "spring/pet-context.xml");
				
		
//		Owner owner = (Owner) context.getBean("moon");
		Owner owner = context.getBean("moon", Owner.class);
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	public void autoWiredTest() {		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
		assertThat(owner.getPet().bark()).isNotNull().isEqualTo("야옹~");
	}
}