package com.kh.mybatis.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;
//여러개 데이터를 보관할 수 있는 것? (리스트도 있지만) 컬렉션
//여러개 값을 넘길 수 있는 것? = 컬렉션 
public class BoardDao {
	public int getBoardCount(SqlSession session, List<String> filters) {

		return session.selectOne("boardMapper.getBoardCountByFilters", filters);
	}
	
	public List<Board> findAll(SqlSession session, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
		
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return session.selectList("boardMapper.selectAll", map);
	}

	public List<Board> findAll(SqlSession session, String[] filters, PageInfo pageInfo) {
		/*
		 * List 타입이나 Array 타입의 데이터를 파라미터로 전달하면 내부적으로 Map으로 타입이 변환되어서 저장되기 때문에
		 * Mapper에서는 list(또는 collection)나 array라는 이름으로 사용해야 한다.
		 * 
		 * 	Dao.java
		 * 		session.selectList("boardMapper.selectBoardListByFilters", filters);
		 * 
		 *  Mapper.xml
		 *  	<if test="array != null">
		 *  		...
		 *  	</if>
		 *  
		 * 만약에 filters라는 이름을 Mapper에서 사용하고 싶으면 Map 객체에 담아서 파라미터로 전달한다.
		 */
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("filters", filters);
		
		/*
		 * 기존에 Servlet/JSP에서는 쿼리문에서 Rownum컬럼과 서브쿼리를 통해서 페이징 처리를 하였다.
		 * 하지만 mybatis 에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공해준다.
		 * 
		 * RowBounds의 객체를 생성할 때 offset과 limit값을 전달해서 페이징 처리를 쉽게 구현한다.
		 * (offset 만큼 건너뛰고 limit만큼 가져온다.)
		 * 
		 * offset = 0 , limit= 10
		 * 	 - 0개를 건너뛰고 10개를 가져온다.(1~10)
		 * offset = 10 , limit= 10
		 *  - 10개를 건너뛰고 10개를 가져온다. (11~20번째 데이터를 가져온다.)
		 *  offset = 20 , limit= 10
		 *  - 20개를 건너뛰고 10개를 가져온다.(21~30)
		 *  
		 *  
		 *  ->쿼리가 복잡하지않아 기존보다 편리하다.
		 * 
		 * */
		//현재페이지가 1이고 -1하면 0이된다. 
		int offset = (pageInfo.getCurrentPage()-1)* pageInfo.getListLimit();
		int limit = pageInfo.getListLimit();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return session.selectList("boardMapper.selectBoardListByFilters", map, rowBounds);
	}

	public Board findBoardByNo(SqlSession session, int no) {
		
		return session.selectOne("boardMapper.selectBoardByNo",no);
	}
}