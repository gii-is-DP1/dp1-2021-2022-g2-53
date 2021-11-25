package org.springframework.samples.petclinic.game;



import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardTests {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PieceService pieceService;
	
	
	@Test
	public void TestBinary() {
		Game game = new Game();
        game.setId(1);
        Board board = boardService.findById(2);
        board.setGame(game);
        Assertions.assertThat(board.binary(5)).isEqualTo("red");
        Assertions.assertThat(board.binary(3)).isEqualTo("black");

	}
	
	@Test
	public void TestMoveInvalidPosition() {
		Game game = new Game();
		game.setId(1);
		Board board= boardService.findById(2);
		board.setGame(game);
		

	}
	
	@Test
	public void TestMoveInvalidPosition2() {
		Game game = new Game();
		game.setId(1);
		Board board= boardService.findById(2);
		board.setGame(game);
		Piece piece2 = new Piece();
		piece2.setBoard(board);
		piece2.setColor("black");
		piece2.setPosition(5);
		pieceService.save(piece2);
		Movement m2 = new Movement();
		m2.setInitialPosition(piece2.getPosition());
		m2.setNumber(1);
		m2.setDestinyPosition(7);
		Assertions.assertThat(board.getNumberOfPiecesByPosition(5)).isEqualTo(1);
	}

}
