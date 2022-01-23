package com.kh.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration // 해당 클래스가 자바 설정 파일임을 선언한다.
@Import(value = {
	OwnerConfig.class,
	PetConfig.class
})
@ComponentScan("com.kh.di") //베이스 pakage설정해주는것
public class RootConfig {
	/*
	@Bean // 별도로 빈 ID를 지정해주지 않으면 메소드명으로 ID를 지정한다.
	public Dog dog() {
		return new Dog("댕댕이");
	}
	
	@Bean("moon")
	public Owner owner() {
		// dog() 메소드는 빈으로 등록되어있기 때문에 호출 시 마다 객체를
		// 생성하는 것이 아닌 애플리케이션 컨텍스트에서 등록된 빈 객체를 리턴한다.
		return new Owner("문인수", 22, dog());
	}
	*/
	
	@Bean //별도로 빈 ID를 지정해주지 않으면 메소드명으로 ID를 지정한다.
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[] {
				new ClassPathResource("character.properties"),
				new ClassPathResource("driver.properties")
		};
		
		configurer.setLocations(resources);
				
		return configurer;
	}
}