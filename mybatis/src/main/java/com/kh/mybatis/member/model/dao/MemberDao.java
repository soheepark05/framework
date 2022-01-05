package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int getMemberCount(SqlSession session) {
		/*
		 * SqlSession 객체가 제공하는 메소드를 통해서 SQL을 실행 시킨다.
		 * 객체 한 개를 조회하기 위해서 SqlSession 객체의 selectOne() 메소드를 사용한다.
		 *   - 첫 번째 매개 값은 쿼리문이 존재하는 경로이다. ("Mapper네임스페이스.쿼리문아이디")
		 *   - 두 번째 매개 값은 쿼리문에서 사용될 파라미터 객체이다.
		 */
		return session.selectOne("memberMapper.selectCount");
	}

	public List<Member> findAll(SqlSession session) {
		
		return session.selectList("memberMapper.selectAll");
	}

	public Member findMemberById(SqlSession session, String id) {
		
		return session.selectOne("memberMapper.selectMemberById", id);
	}

	public int insertMember(SqlSession session, Member member) {
		
		return session.insert("memberMapper.insertMember", member);
	}

	public int updateMember(SqlSession session, Member member) {
		
		return session.update("memberMapper.updateMember", member);
	}

	public int delete(SqlSession session, int no) {
		
		return session.delete("memberMapper.deleteMember", no);
	}
}

//1리턴하게 해주면 당장의 테스트는 성공한다.
