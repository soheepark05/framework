package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("BoardService 테스트")
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
	@Order(1)
	@DisplayName("게시글 목록 조회(검색 기능 적용) 테스트")
	public void findAllTest(String writer, String title, String content, int expected) {		
		List<Board> list = null;
		
		list = service.findAll(writer, title, content);  //검색기능이 포함되어있다. 
		
		
		//writer에 조건에 해당하는 데이터가 있는지 검색해준다. 
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isEqualTo(expected);
	}
	
	@ParameterizedTest
	@MethodSource("filterProvider")
	@Order(2)
	@DisplayName("게시글 수 조회(필터 적용) 테스트")
	public void getBoardCountTest(String[] filters) {
		int count = 0;
		
		count = service.getBoardCount(filters);
		
		assertThat(count).isPositive().isGreaterThan(0);
	}
	
	@ParameterizedTest
	@MethodSource("listProvider")
	@Order(3)
	@DisplayName("게시글 목록 조회(필터 적용) 테스트")
	public void findAllTest(String[] filters, int currentPage, int expected) {
		// request.getParameterValues("filter");
		// String[] filters = new String[] {"B2", "B3"};  
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
	@Order(4)
	@DisplayName("게시글 상세 조회(댓글 포함) 테스트")
	public void findBoardByNoTest(int no) {
		Board board = null;
		
		board = service.findBoardByNo(no);
		
		assertThat(board).isNotNull().extracting("no").isEqualTo(no);
		assertThat(board.getReplies()).isNotNull();
		assertThat(board.getReplies().size()).isGreaterThan(0);
	}

	@Test
	@Order(5)
	@DisplayName("게시글 등록 테스트")
	public void insertBoardTest() {
		int result = 0;
		Board board = new Board();
		Board findBoard = null;
		
		board.setWriterNo(3);
		board.setTitle("mybatis 게시글");
		board.setContent("mybatis로 게시글 등록을 해보았습니다.");
		
		
		result = service.save(board); //영향받는 행의 갯수 리턴
		findBoard = service.findBoardByNo(board.getNo());
		
		
		assertThat(result).isGreaterThan(0);
		assertThat(findBoard).isNotNull().extracting("no").isEqualTo(board.getNo());
	}
	
	@Test
	@Order(6)
	@DisplayName("게시글 수정 테스트")
	public void updateBoardTest() {
		int result = 0;
		Board board = service.findBoardByNo(158); //158
		Board findBoard = null;
		board.setOriginalFileName(null);
		board.setRenamedFileName(null);
		
		result = service.save(board);
		findBoard = service.findBoardByNo(board.getNo());
		
		assertThat(result).isGreaterThan(0);
		assertThat(findBoard).isNotNull().extracting("title").isEqualTo(board.getTitle());
		
	}
	
	@Test
	@Order(7)
	@DisplayName("게시글 삭제 테스트")
	public void deleteTest() {
		int result = 0;
		Board board = null;
		
		result = service.delete(158);
		board = service.findBoardByNo(158);
		
		assertThat(result).isPositive().isEqualTo(1);
		assertThat(board).isNull();
	}
	
	
	
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
