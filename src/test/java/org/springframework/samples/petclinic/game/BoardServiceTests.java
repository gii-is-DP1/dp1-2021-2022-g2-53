package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardServiceTests {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PieceService pieceService;

	// En la base de datos tenemos 2 board ya creadas
	
	@Test
	public void boardCountTest() {
		int count = boardService.boardCount();
		assertEquals(count, 2);
	}

	
	@Test
	public void findByIdBoardTest() {
		Board board = boardService.findById(1);
		assertEquals(board.getId(), 1);
	}
	
	@Test
	public void findByIdBoardTest2() {
		Board board = boardService.findById(2);
		assertEquals(board.getId(), 2);
	}
	

	@Test
	public void SaveBoardTest() {
		Board board = new Board();
		boardService.save(board);
		int count = boardService.boardCount();
		assertEquals(count, 3);

	}

	@Test
	public void DeleteboardTest() {
		Board board = new Board();
		boardService.save(board);
		boardService.delete(board);
		int count = boardService.boardCount();
		assertEquals(count, 2);

	}
	
//    @Test
//    public void TestBinaryWhiteTwoPiece() {
//    Board board = gameService.findId(2).getBoard();
//    
//    Piece pieceNewRed = new Piece();
//      pieceNewRed.setBoard(board);
//      pieceNewRed.setPosition(5);
//      pieceNewRed.setColor("red");
//    Piece pieceNewBlack = new Piece();
//      pieceNewBlack.setBoard(board);
//      pieceNewBlack.setPosition(3);
//      pieceNewBlack.setColor("black");
//      pieceService.save(pieceNewBlack);
//      pieceService.save(pieceNewRed);
//      boardService.binary(board);
//      boardService.save(board);
// 
//    
//    Assertions.assertThat(board.getNumberOfPiecesByPosition(5)).isEqualTo(3);
//
//    }

}
