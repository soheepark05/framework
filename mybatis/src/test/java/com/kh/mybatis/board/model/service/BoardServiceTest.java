package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

class BoardServiceTest {
	private BoardService service;

	@BeforeEach
	public void setUp() throws Exception {
		service = new BoardService();
	}

	@Test
	@Disabled //잘되는지 동작만 확인
	public void nothing() {
	}
	
	@Test
	@Disabled //잘되는지 동작만 확인
	public void create() {
		assertThat(service).isNotNull();
	}
	
	@ParameterizedTest
	@CsvSource(
		value = {
			"is, null, null, 1",
			"null, 22, null, 2",
			"null, null, 테스트, 1",
			"null, null, null, 157"},
		nullValues = "null"
	)
	public void findAllTest(String writer, String title, String content, int expected) {		
		List<Board> list = null;
		
		list = service.findAll(writer, title, content); 
		
		//writer에 조건에 해당하는 데이터가 있는지 검색해준다. 
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isEqualTo(expected);
	}
	
	@ParameterizedTest
	@MethodSource("filterProvider")
	public void getBoardCountTest(String[] filters) {
		int count = 0;
		
		count = service.getBoardCount(filters);
		
		//System.out.println(count);
		
		assertThat(count).isPositive().isGreaterThan(0);
	}
	
	@ParameterizedTest
	@MethodSource("listProvider")
	public void findAllTest(String[] filters, int currentPage, int expected) {
		//String[] filters = new String[] {"B2", "B3"};  
		// request.getParameterValues("filter");
		List<Board> list = null;
		PageInfo pageInfo = null;
		int listCount = 0;
		
		listCount = service.getBoardCount(filters);
		pageInfo = new PageInfo(currentPage,10,listCount,10);
		list = service.findAll(filters,pageInfo);
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isEqualTo(expected);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {156, 157})
	public void findBoardByNoTest(int no) {
		Board board = null;
		
		board = service.findBoardByNo(no);
		
		assertThat(board).isNotNull().extracting("no").isEqualTo(no);
		assertThat(board.getReplies()).isNotNull();
		assertThat(board.getReplies().size()).isGreaterThan(0);
	}
	//-----------------------------------------------------
	
	
//argument가 위에 파라미터값을 가변인자로 넘겨주는 것
	public static Stream<Arguments> filterProvider(){
		return Stream.of(
				Arguments.arguments((Object) new String[]{"B2","B3"}),
				Arguments.arguments((Object) new String[]{"B1"}) //153개 조회된다.`
				
				);
				
				
	}
	
	public static Stream<Arguments> listProvider() {
		return Stream.of(				
				//배열, 페이지번호, 예상되는 조회값갯수(그냥넣는거다)
				Arguments.arguments((Object) new String[] {"B2", "B3"},1,5),
				Arguments.arguments((Object) new String[] {"B1"},1,10),
				Arguments.arguments((Object) new String[] {"B1"},16,2)
				);
		
		
	}
	
	
	
}
