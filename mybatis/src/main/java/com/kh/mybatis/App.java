package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;


import com.kh.mybatis.common.SqlSessionTemplate;
public class App 
{
    public static void main( String[] args )
    {
       
        
        SqlSession session = SqlSessionTemplate.getSession();
        System.out.println("HELLO WORLD");
    }
}
