package com.kh.di.owner;

import com.kh.di.pet.Pet;

//import com.kh.di.pet.Cat;
//import com.kh.di.pet.Dog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
	
	
	private String name;
	
	private int age;
	
	//private Dog dog; //강아지
	//private Cat cat;
	private Pet pet;

}
