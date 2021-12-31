package com.kh.mybatis.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.SqlSessionTemplate;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberService {

	private MemberDao dao = new MemberDao();
	
	public int getMemberCount() {
		
		int count = 0;
		SqlSession session = SqlSessionTemplate.getSession();
		count = dao.getMemberCount(session);
		
		
		session.close();
		
		
		return count;
	} //컨트럴 + 1 누르기

	public List<Member> findAll() {
		List<Member>members = null;
		SqlSession session = SqlSessionTemplate.getSession();
		members = dao.findAll(session);
		System.out.println(members);
		session.close();
		
		return members;
	}

}
