package com.kh.aop.owner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.aop.pet.Pet;

//import com.kh.di.pet.Cat;
//import com.kh.di.pet.Dog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Owner {
	@Value("홍길동")
	private String name;
	
	@Value("20")
	private int age;
	
	//private Dog dog; //강아지
	//private Cat cat;
	@Autowired
	@Qualifier("dog")
	private Pet pet;

}
