package com.kh.mybatis.board.model.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;

@Slf4j //lombok을 사용한다.
public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public int getBoardCount(String[] filters) {
		
		log.info("getBoardCount() 호출");
		
		int count = 0;
		SqlSession session = getSession();
		
		count = dao.getBoardCount(session, Arrays.asList(filters));
		
		session.close();
		
		return count;
	}

	public List<Board> findAll(String writer, String title, String content) {
		
		log.info("findAll() 호출");
		List<Board> list = null;
		SqlSession session = getSession();
		
		list = dao.findAll(session, writer, title, content);
		
		session.close();
		
		return list;
	}

	public List<Board> findAll(String[] filters, PageInfo pageInfo) {
		List<Board> list = null;
		
		SqlSession session = getSession();
		
		list = dao.findAll(session, filters, pageInfo);
		
		session.close();
		
		return list;
	}

	public Board findBoardByNo(int no) {
		
		log.info("findBoardByNo() 호출");
		
		Board board = null;
		SqlSession session = getSession();
		
		board = dao.findBoardByNo(session, no);
		
		session.close();
		
		return board;
	}

	public int save(Board board) {
		
		int result = 0;
		SqlSession session =getSession();
		
		if(board.getNo() != 0) {
			//update
			result = dao.updateBoard(session,board);
		}else {
			//insert
			
			result = dao.insertBoard(session, board); //영향받는 행의 갯수 리턴
		}
		if(result>0) {
			session.commit();
			
		}else {
			session.rollback();
			
		}
		session.close();
		
		return result;
	}
	
	public int delete(int no) {
		int result = 0;
		SqlSession session = getSession();
		
		result = dao.updateStatus(session, no, "N");
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	
}