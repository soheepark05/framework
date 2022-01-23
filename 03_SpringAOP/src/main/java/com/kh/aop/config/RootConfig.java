package com.kh.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //자바설정파일임을 알려준다.
@ComponentScan("com.kh.aop") //compoent라는 거 있으면 빈으로 만들어("")경로밑으로있는...
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RootConfig {
	
	
}