package com.kh.di.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Primary // 동일한 타입의 빈이 여러 개 존재할 때 기본으로 주입될 빈을 지정한다.
@Component //내가 방금 만든 클래스를 애플리케이션 실행할때 있으면 빈으로 만들어서 실행..
public class Sword implements Weapon {
	@Value("집행검")
	private String name;

	@Override
	public String attack() {	
		return "검을 휘두른다.";
	}
}