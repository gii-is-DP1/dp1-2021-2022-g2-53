package org.springframework.samples.petclinic.game;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardTest {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PieceService pieceService;
	@Autowired
	private SarcineService sarcineService;

	@Test
	public void TestBinaryBoard() {

		Board board = boardService.findById(2);
		Assertions.assertThat(board.binaryboard(5)).isEqualTo("red");
		Assertions.assertThat(board.binaryboard(3)).isEqualTo("black");

	}

	// board con id=2 parte con una bacteria roja en posicion 5 y una bacteria negra
	// en la posicion 3
	@Test
	public void getAllPiecesInTheSamePositionTest() {
		Board board = boardService.findById(2);
		Piece pieceNewRed = new Piece();
		pieceNewRed.setBoard(board);
		pieceNewRed.setPosition(6);
		pieceNewRed.setColor("red");
		Piece pieceNewBlack = new Piece();
		pieceNewBlack.setBoard(board);
		pieceNewBlack.setPosition(6);
		pieceNewBlack.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		Piece pieceNewBlack2 = new Piece();
		pieceNewBlack2.setBoard(board);
		pieceNewBlack2.setPosition(6);
		pieceNewBlack2.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		pieceService.save(pieceNewBlack2);

		Assertions.assertThat(board.getAllPiecesInTheSamePosition(pieceNewBlack).size()).isEqualTo(3);

	}

	@Test
	public void getAllPiecesInTheSamePositionAndSameColorTest() {
		Board board = boardService.findById(2);
		Piece pieceNewRed = new Piece();
		pieceNewRed.setBoard(board);
		pieceNewRed.setPosition(6);
		pieceNewRed.setColor("red");
		Piece pieceNewBlack = new Piece();
		pieceNewBlack.setBoard(board);
		pieceNewBlack.setPosition(6);
		pieceNewBlack.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		Piece pieceNewBlack2 = new Piece();
		pieceNewBlack2.setBoard(board);
		pieceNewBlack2.setPosition(6);
		pieceNewBlack2.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		pieceService.save(pieceNewBlack2);

		Assertions.assertThat(board.getAllPiecesInTheSamePositionAndSameColor(pieceNewBlack).size()).isEqualTo(2);

	}

	@Test
	public void getNumberOfPiecesByPositionTest() {
		Board board = boardService.findById(2);
		Piece pieceNewRed = new Piece();
		pieceNewRed.setBoard(board);
		pieceNewRed.setPosition(6);
		pieceNewRed.setColor("red");
		Piece pieceNewBlack = new Piece();
		pieceNewBlack.setBoard(board);
		pieceNewBlack.setPosition(6);
		pieceNewBlack.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		Piece pieceNewBlack2 = new Piece();
		pieceNewBlack2.setBoard(board);
		pieceNewBlack2.setPosition(6);
		pieceNewBlack2.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		pieceService.save(pieceNewBlack2);

		Assertions.assertThat(board.getNumberOfPiecesByPosition(6)).isEqualTo(3);

	}

	@Test
	public void getNumberOfPiecesByPositionTest2() {
		Board board = boardService.findById(2);
		Assertions.assertThat(board.getNumberOfPiecesByPosition(5)).isEqualTo(1);

	}

	@Test
	public void getNumberOfPiecesByPositionTest3() {
		Board board = boardService.findById(2);
		Assertions.assertThat(board.getNumberOfPiecesByPosition(3)).isEqualTo(1);

	}

	@Test
	public void getAllPiecesByPositionTest() {
		Board board = boardService.findById(2);
		Piece pieceNewRed = new Piece();
		pieceNewRed.setBoard(board);
		pieceNewRed.setPosition(6);
		pieceNewRed.setColor("red");
		Piece pieceNewBlack = new Piece();
		pieceNewBlack.setBoard(board);
		pieceNewBlack.setPosition(6);
		pieceNewBlack.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		Piece pieceNewBlack2 = new Piece();
		pieceNewBlack2.setBoard(board);
		pieceNewBlack2.setPosition(6);
		pieceNewBlack2.setColor("black");
		pieceService.save(pieceNewBlack);
		pieceService.save(pieceNewRed);
		pieceService.save(pieceNewBlack2);

		Assertions.assertThat(board.getAllPiecesByPosition(6).size()).isEqualTo(3);

	}

	@Test
	public void containsSarcineTest() {
		Board board = boardService.findById(2);
		Sarcine sarcinered = new Sarcine();
		sarcinered.setBoard(board);
		sarcinered.setColor("red");
		sarcinered.setPosition(6);

		sarcineService.save(sarcinered);

		Assertions.assertThat(board.containsSarcine(6, "red")).isEqualTo(true);

	}


	@Test
	public void pollutionTest() {
		Board board = boardService.findById(2);
		Piece pieceNewRed = new Piece();
		pieceNewRed.setBoard(board);
		pieceNewRed.setPosition(6);
		pieceNewRed.setColor("red");
		pieceService.save(pieceNewRed);
		List<Integer> puntuacion = board.pollution();

		Assertions.assertThat(puntuacion.get(0)).isEqualTo(2);
		Assertions.assertThat(puntuacion.get(1)).isEqualTo(1);

	}

//	    @Test
//	    public void movePiecesTest() throws MoveInvalidException {
//	    	Movement move = new Movement(3,1,1);//se mueve de la casilla 3 una pieza a la casilla 1
//	    	Board board = boardService.findById(2);
//	    	BindingResult reslult = new BindingResult();
//	    	Assertions.assertThat(board.getNumberOfPiecesByPosition(3)).isEqualTo(1);
//	        
//			board.movePieces(move,reslult);
//	    	Assertions.assertThat(board.getNumberOfPiecesByPosition(1)).isEqualTo(1);
//	    	
//	    }
}
