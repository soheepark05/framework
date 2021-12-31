package com.kh.mybatis.member.model.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.kh.mybatis.member.model.vo.*;

public class MemberDao {

	public int getMemberCount(SqlSession session) {
		
		
		/*
		 * sqlSession 객체가 제공하는 메소드를 통해서 SQL을 실행시킨다.
		 * 객체 한개를 조회하기 위해서 sqlsession 객체의 selectone()메소드를 사용한다.
		 * 
		 * -첫번째 매개 값은 쿼리문이 존재하는 경로이다.("mapper네임스페이스, 쿼리문아이디")
		 * -두번째 매개 값은 쿼리문에서 사용될 파라미터 객체이다.
		 */
		
		
		
		return session.selectOne("memberMapper.selectCount");
	}
	
	public List<Member> findAll(SqlSession session){
		
		List<Member> members = new ArrayList<>();
		
		members= session.selectList("memberMapper.selectAll");
		
		return members;
	}

}
