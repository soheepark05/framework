package com.kh.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect //일반적인 자바 클래스가 아니라 애스팩트임을 나타낸다.
public class CharacterAspect {
	// 포인트 커트를 한 번만 정의하고 필요할 때마다 참조할 수 있는 @Pointcut 어노테이션을 제공한다.
	@Pointcut("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	public void questPointCut(String questName) {
	}
	
	//이거선언하면 root-context.xml이랑 같아진다.
//	@Before("execution(* com.kh.aop.character.Character.quest(..))")
	public void beforeQuest(JoinPoint jp) { //메소드
		System.out.println(jp.getSignature().getName());
		System.out.println(jp.getSignature().getDeclaringTypeName());
		System.out.println(jp.getArgs()[0]+"퀘스트 준비중..");

	}
	//@After("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	public void afterQuest(String questName) { //메소드   //questName 이거 이름 동일해야한다 위랑 아래랑
	//퀘스트 수행 결과에 상관없이 수행 후에 필요한 부가 작업을 수행한다.
		System.out.println(questName + "퀘스트 수행 완료...");
	}
	//@AfterReturning(
		//	value = "questPointCut(questName)",
		//	returning = "result"
		//	)
	public void questSucess(String questName, String result) {
		//퀘스트 수행 완료 후에 필요한 부가 작업을 수행한다.
		System.out.println(result);
		System.out.println(questName+"퀘스트 수행 완료..");
	}
	
	//@AfterThrowing(
	//	value = "execution(* com.kh.aop.character.Character.quest(..) && args(questName) ",
	//	throwing = "exception"
	//		)
	public void questFail(String questName,Exception exception) {
		//퀘스트 수행 중에 예외를 던졌을 때 필요한 부가 작업을 수행한다.
		System.out.println(exception.getMessage());
		System.out.println("에러가 발생하였습니다. 다시 시도해 주세요.");
	}
	@Around("execution(* com.kh.aop.character.Character.quest(..))")
	public String questAdvice(ProceedingJoinPoint jp) {
		String result = null;
		String questName="["+(String)jp.getArgs()[0]+"]"; //character에서 가져옴
		
	//	System.out.println(jp.getArgs()[0]);
		try {
			//before 어드바이스에 대한 기능을 수행	
			System.out.println("퀘스트 준비중...");
			
			//타겟 객체의 메소드를 실행시킨다.
			//jp.proceed();
			
			//타겟 객체의 메소드에 리턴값이 있을 경우
		//	result = (String) jp.proceed();
			
			//타겟 객체의 메소드에 파라미터 값을 변경해서 전달해줄 경우
			result = (String) jp.proceed(new Object[] {questName});
			
			//after-returning 어드바이스에 대한 기능을 수행
			System.out.println("퀘스트 수행 완료..");
			
		} catch (Throwable e) {
			//after-throwing 어드바이스에 대한 기능을 수행.
			System.out.println(e.getMessage());
			System.out.println("에러가 발생하였습니다. 다시 시도해 주세요.");
			
		}
		
		return result;
	}
	
	// 실습 문제
	// Sword, Bow의 attack 메소드 실행 시
	// @Around 어드바이스를 사용하여 코드작성하세요.
	// 1. attack 메소드 정상 동작
	//    공격을 준비중 입니다.
	//    "검을 휘두른다." or "민첩하게 활을 쏜다." 출력
	//    공격을 성공했습니다.
	// 2. attack 메소드 실행중에 예외 발생 시
	//    공격을 준비중 입니다.
	//    에러가 발생하였습니다..
	
	@Around("execution(* com.kh.aop.weapon.*.attack())")
	public String attackAdvice(ProceedingJoinPoint jp) {
		String result = null;
		
		try {
			System.out.println("공격을 준비중 입니다.");
			
			result = (String) jp.proceed();
			
			System.out.println(result);
			
			System.out.println("공격을 성공했습니다.");
		} catch (Throwable e) {
			System.out.println("에러가 발생하였습니다..");
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
