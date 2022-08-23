package org.springframework.samples.petclinic.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PieceTest {

	@Autowired
	private BoardService boardService;

	@Test
	public void getPositionXInPixelsPiecesTest() {

		Board board = boardService.findById(2);

		Integer position = 6;
		Piece piece = new Piece();
		piece.setBoard(board);
		piece.setPosition(position);
		piece.setColor("black");

		Integer position2 = 3;
		Piece piece2 = new Piece();
		piece2.setBoard(board);
		piece2.setPosition(position2);
		piece2.setColor("red");

		Assertions.assertThat(piece.getPositionXInPixels(position)).isEqualTo(80);
		Assertions.assertThat(piece2.getPositionXInPixels(position2)).isEqualTo(10);

	}

	@Test
	public void getPositionYInPixelsBlackPiecesTest() {

		Board board = boardService.findById(2);

		Integer position = 6;
		Piece piece = new Piece();
		piece.setBoard(board);
		piece.setPosition(position);
		piece.setColor("black");

		Assertions.assertThat(piece.getPositionYInPixels(position)).isEqualTo(210);
	}

	@Test
	public void getPositionYInPixelsRedPiecesTest() {

		Board board = boardService.findById(2);

		Integer position = 5;

		Piece pieceRed = new Piece();
		pieceRed.setBoard(board);
		pieceRed.setPosition(position);
		pieceRed.setColor("red");

		Assertions.assertThat(pieceRed.getPositionYInPixels(position)).isEqualTo(170);
	}

}
