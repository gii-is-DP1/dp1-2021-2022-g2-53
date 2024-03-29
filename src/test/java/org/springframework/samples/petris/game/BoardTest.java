package org.springframework.samples.petris.game;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardTest {

	@Autowired
	private BoardService boardService;
	@Autowired
	private PieceService pieceService;
	@Autowired
	private SarcineService sarcineService;
	

	@Test
	@Transactional
	public void TestBinaryBoard() {

		Board board = boardService.findById(2);
		Assertions.assertThat(board.binaryboard(5)).isEqualTo("red");
		Assertions.assertThat(board.binaryboard(3)).isEqualTo("black");

	}

	
	@Test
	@Transactional
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
	@Transactional
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
	@Transactional
	public void getAllPiecesSameColorTest() {
		Board board = boardService.findById(2);
		

		Assertions.assertThat(board.getAllPiecesSameColor("red").size()).isEqualTo(1);
		Assertions.assertThat(board.getAllPiecesSameColor("black").size()).isEqualTo(1);

	}
	@Test
	@Transactional
	public void getAllSarcinesSameColorTest() {
		Board board = boardService.findById(2);
		List<Sarcine> ls = new ArrayList<Sarcine>(); 
		Sarcine sarcine1 = new Sarcine();
		Sarcine sarcine2 = new Sarcine();
		sarcine1.setId(3);
		sarcine1.setColor("red");
		sarcine1.setBoard(board);
		sarcine1.setPosition(5);
		board.setSarcines(null);
		sarcine2.setId(4);
		sarcine2.setColor("black");
		sarcine2.setBoard(board);
		sarcine2.setPosition(3);
		sarcineService.save(sarcine1);
		sarcineService.save(sarcine2);
		ls.add(sarcine1);
		ls.add(sarcine2);
		board.setSarcines(ls);
		Assertions.assertThat(board.getAllSarcinesSameColor("red").size()).isEqualTo(1);
		Assertions.assertThat(board.getAllSarcinesSameColor("black").size()).isEqualTo(1);

	}

	@Test
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
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


}
