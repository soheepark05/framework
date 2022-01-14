package com.kh.di.character;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.RootConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring/root-context.xml")
//@ContextConfiguration(classes = RootConfig.class)
class CharacterTest {
	// required는 빈 주입이 필수로 진행되어야 하는지 설정하는 옵션이다.
	// required가 true일 경우 주입해야 되는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 Exception이 발생한다. (default)
	// required가 false 경우 주입해야 되는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 null을 주입한다.
/*
 * AOP 용어 정리
 * 1.Aspect
 * - AOP 횡단 관심사(한 애플리케이션의 여러 부분에 공통적으로 사용하는 기능)를 에스펙트라는 특별한 클래스로 모듈화해서 관리한다.
 * - 애스펙스는 어드바이스(advice)와 pointcut을 합친 것 이다.
 * 
 * 2. JoinPoint 
 * - advice를 적용할 수 있는 모든 지점을 조인 포인트라고 한다.
 * - 즉, joinpoint는 애플리케이션실행에 공통적인 기능을 끼워 넣을 수 있는 지점(point)을 말한다,
 * (메소드 호출 지점이나 예외 발생 등)
 * 
 * 3. Advice
 * - Aspect가 해야하는 작업을 aop용어로 advice라고 한다.
 */
	
	
	@Autowired(required = false)
	private Character character;
	
	@Value("${character.name}")
	private String name;
	
	@Value("${character.level}")
	private int level;
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;

	@Test
//	@Disabled
	public void test() {
		assertThat(driver).isNotNull().isEqualTo("oracle.jdbc.driver.OracleDriver");
		assertThat(url).isNotNull().isEqualTo("jdbc:oracle:thin:@localhost:1521:xe");
	}

	@Test
	public void create() {
		System.out.println(character);
		
		assertThat(character).isNotNull();
		assertThat(character.getName()).isNotNull().isEqualTo(name);
		assertThat(character.getLevel()).isPositive().isGreaterThan(0).isEqualTo(level);
		assertThat(character.getWeapon()).isNotNull();
	}
}