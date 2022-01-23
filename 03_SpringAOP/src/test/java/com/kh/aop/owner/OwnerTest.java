package com.kh.aop.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.aop.config.RootConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
class OwnerTest {
	
	@Autowired// (required = false) 이러면 에러 안남 //이어노테이션있으면 오너객체 어디서 가져오냐?: applicationcontext 에서 빈팩토리로
	private Owner owner; //오너객체 생성
	
	@Test
	public void create() {
		assertThat(owner).isNotNull();
		assertThat(owner.getName()).isNotNull();
		assertThat(owner.getAge()).isPositive().isGreaterThan(0);
	}
	
	@Test
	public void barkTest() throws Exception {
		assertThat(owner.getPet()).isNotNull();
		assertThat(owner.getPet().bark()).isNotNull();
	}
	

}