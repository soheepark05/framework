package com.kh.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.di.weapon.Weapon;

import lombok.Data;

@Data
//component는 bean(빈)을 만들어주는것->root.context에 <태그>를 선언해줘야한다.
@Component // 빈 생성시 별도의 ID를 지정해주지 않으면 클래스 이름에서 앞글자를 소문자로 바꾼 ID를 갖는다. (character)

// @PropertySource 어노테이션을 사용해서 properties 파일의 값을 읽어오는 방법
// 1. Environment 객체 사용
// 2. 스프링 프로퍼티 플레이스 홀더 사용 (${key:기본값})
//@PropertySource("classpath:character.properties")

//@PropertySource 어노테이션을 생략 후 스프링 프로퍼티 플레이스 홀더를 통해서 properties 파일의 값을 읽어오는 방법
// 1. xml 설정 파일에 경우 <context:property-placeholder /> 추가
// 2. java 설정 파일에 경우 PropertySourcesPlaceholderConfigurer 빈 등록
public class Character {
	//@Value("홍길동")
	//private String name; //자바 리플렉션(reflection)을 통해 내부의 private도 직접 주입을 해줄 수 있다.
	
	
	// 스프링 프로퍼티 플레이스 홀더 사용 (${key:기본값})
	@Value("${character.name:홍길동}") //@Value기본 리터럴 값 넣어주는 역할  {}
	private String name;
	
	@Value("${character.level:99}")
	private int level;
	
	@Autowired
	@Qualifier("windForce") 
	//동일한 클래스 타입에 빈이 여러개 일 경우 써주어야한다. @Qualifier("빈 ID")
	private Weapon weapon;

//  Environment 객체 사용
//	public Character (/*@Autowired*/ Environment env) {
//		this.name = env.getProperty("character.name");
//		this.level = Integer.parseInt(env.getProperty("character.level"));
//	}
}