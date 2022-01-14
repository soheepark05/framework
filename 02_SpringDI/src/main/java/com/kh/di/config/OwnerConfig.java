package com.kh.di.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.di.owner.Owner;
import com.kh.di.pet.Pet;

@Configuration
public class OwnerConfig {
	@Bean("moon")
	public Owner owner(@Autowired @Qualifier("dog") Pet pet) {
		// dog() 메소드는 빈으로 등록되어있기 때문에 호출 시 마다 객체를
		// 생성하는 것이 아닌 애플리케이션 컨텍스트에서 등록된 빈 객체를 리턴한다.
		return new Owner("문인수", 22, pet);
	}
	
	@Bean("hong")
	public Owner owner2(/* @Autowired (생략 가능) */ @Qualifier("ray") Pet pet) {
		return new Owner("홍길동", 32, pet);
	}
}