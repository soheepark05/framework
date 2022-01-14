package com.kh.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.di.weapon.Weapon;

import lombok.Data;

@Data
@Component // 빈 생성시 별도의 ID를 지정해주지 않으면 클래스 이름에서 앞글자를 소문자로 바꾼 ID를 갖는다. (character)
// @PropertySource 어노테이션을 사용해서 properties 파일의 값을 읽어오는 방법
// 1. Environment 객체 사용
// 2. 스프링 프로퍼티 플레이스 홀더 사용 (${key:기본값})
//@PropertySource("classpath:character.properties")

//@PropertySource 어노테이션을 생략 후 스프링 프로퍼티 플레이스 홀더를 통해서 properties 파일의 값을 읽어오는 방법
// 1. xml 설정 파일에 경우 <context:property-placeholder /> 추가
// 2. java 설정 파일에 경우 PropertySourcesPlaceholderConfigurer 빈 등록
public class Character {
	// 스프링 프로퍼티 플레이스 홀더 사용 (${key:기본값})
	@Value("${character.name:홍길동}")
	private String name;
	
	@Value("${character.level:99}")
	private int level;
	
	@Autowired
	@Qualifier("windForce")
	private Weapon weapon;

//  Environment 객체 사용
//	public Character (/*@Autowired*/ Environment env) {
//		this.name = env.getProperty("character.name");
//		this.level = Integer.parseInt(env.getProperty("character.level"));
//	}
}