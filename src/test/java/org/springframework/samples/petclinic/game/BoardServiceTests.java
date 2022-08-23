package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	@Test
	public void TestBinaryWithBlackPieces() {
		Board board = gameService.findId(2).getBoard();

		Piece pieceB = new Piece();
		pieceB.setBoard(board);
		pieceB.setColor("black");
		pieceB.setPosition(3);
		pieceB.setType("bacterium");
		pieceService.save(pieceB);

		boardService.binary(board);
		boardService.save(board);

		assertEquals(board.getNumberOfPiecesByPosition(3), 2);

	}

	@Test
	public void TestBinaryWithRedPieces() {
		Board board = gameService.findId(2).getBoard();

		Piece pieceR = new Piece();

		pieceR.setColor("red");
		pieceR.setPosition(5);
		pieceR.setBoard(board);
		pieceR.setType("bacterium");
		pieceService.save(pieceR);

		boardService.binary(board);
		boardService.save(board);

		assertEquals(board.getNumberOfPiecesByPosition(5), 2);

	}

	@Test
	public void TestBinaryWithTwoTypesOfPieces() {
		Board board = gameService.findId(2).getBoard();

		Piece pieceR = new Piece();
		pieceR.setBoard(board);
		pieceR.setColor("red");
		pieceR.setPosition(5);
		pieceR.setType("bacterium");
		pieceService.save(pieceR);

		Piece pieceB = new Piece();
		pieceB.setBoard(board);
		pieceB.setColor("black");
		pieceB.setPosition(3);
		pieceB.setType("bacterium");
		pieceService.save(pieceB);

		boardService.binary(board);
		boardService.save(board);

		assertEquals(board.getNumberOfPiecesByPosition(5), 2);
		assertEquals(board.getNumberOfPiecesByPosition(3), 2);

	}

}
