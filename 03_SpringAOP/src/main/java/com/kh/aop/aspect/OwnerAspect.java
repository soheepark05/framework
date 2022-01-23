package com.kh.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


import com.kh.aop.annotation.Repeat;
@Aspect
//빈으로만들려고한다면? component
@Component
public class OwnerAspect {
	/*
	 * @Around("execution(* com.kh.aop.pet.*.bark()) && !@annotation(com.kh.aop.annotation.NoLogging)"
	 * ) // @Around("execution(* com.kh.aop.pet.*.bark()) && !bean(dog)") //도그가 아닌
	 * 빈이 아니면 실행시킨다. public String barkAdvice(ProceedingJoinPoint jp) { String
	 * result = null;
	 * 
	 * try { //before System.out.println("짖어보렴!");
	 * 
	 * result=(String)jp.proceed();
	 * 
	 * //after returning System.out.println(result); } catch (Throwable e) { //
	 * after throwing System.out.println("왜 안짖니,,");
	 * 
	 * } return result; }
	 */

	
	@Around("@annotation(com.kh.aop.annotation.Repeat)")
	public String barkAdvice(ProceedingJoinPoint jp) {
	String result = null;
	MethodSignature signature = (MethodSignature)jp.getSignature();
	Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);
	
//	System.out.println(repeat);
//	System.out.println(repeat.count());
	
	try {
		System.out.println("짖어보렴~");
		
		result = (String)jp.proceed();
		
		for (int i = 0; i < repeat.count(); i++) {			
			System.out.println(result);
		}
	} catch (Throwable e) {
		System.out.println("왜 안짖니?ㅠㅠ");
	}
	
	return result;
}
}
