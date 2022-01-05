package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSession() {
		InputStream is = null;
		SqlSessionFactoryBuilder builder = null;
		SqlSessionFactory factory = null;
		SqlSession session = null;
		
		try {
			// Resources는 Mybatis에서 제공하는 유틸 클래스로
			// 클래스패스로부터 자원(Resource)를 쉽게 읽어오는 메소드를 제공한다. 
			is = Resources.getResourceAsStream("mybatis-config.xml");
			
			builder = new SqlSessionFactoryBuilder();
			factory = builder.build(is);
//			factory = builder.build(is, "kh");
			
			// true : 오토커밋 활성, false : 오토커밋 비활성
			session = factory.openSession(false);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
