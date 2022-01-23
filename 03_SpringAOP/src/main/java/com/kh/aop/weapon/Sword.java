package com.kh.aop.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class Sword implements Weapon {
	private String name;

	@Override
	public String attack() throws Exception {	
		if(true) {
			throw new Exception();
		}
		
		return "검을 휘두른다.";
	}
	
	
}