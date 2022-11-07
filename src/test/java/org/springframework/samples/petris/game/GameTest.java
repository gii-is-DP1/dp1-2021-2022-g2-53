package org.springframework.samples.petris.game;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameTest {
	
	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	
	@Test
	public void pointsTest() {
		Game game = gameService.findId(1);
		Assertions.assertThat(game.getPointsBlack() == 3);
		Assertions.assertThat(game.getPointsRed() == 8);
	}
	
	@Test
	public void tokenTest() {
		Game game = new Game();
		String token = game.generarToken();
		Assertions.assertThat(token != null);
		Assertions.assertThat(token.length() == 7);
		Assertions.assertThat(token.matches("^[a-w]{3}-[1-9]{3}$"));
	}
	
	@Test
	public void ganadorPorPuntosTest() {
		Game game = new Game();
		game.setTurno(game.getTurnos().size()-1);
		game.setPointsBlack(1);
		game.setPointsRed(0);
		Assertions.assertThat(game.getGanador() == "Jugador rojo");
		game.setPointsBlack(7);
		game.setPointsRed(8);
		Assertions.assertThat(game.getGanador() == "Jugador negro");
	}
	
	@Test
	public void ganadorPorBacteriasTest() {
		Game game = new Game();
		Board board = boardService.findById(1);
		List<Piece> pieces = new ArrayList<>();
		Piece p1, p2, p3, p4, p5;
		p1 = p2 = p3 = p4 = p5 = new Piece();
		p1.setColor("red");
		p1.setId(1);
		p2.setColor("red");
		p2.setId(2);
		p3.setColor("black");
		p3.setId(3);
		p4.setColor("black");
		p4.setId(4);
		p5.setColor("black");
		p5.setId(5);
		
		game.setTurno(game.getTurnos().size()-1);
		game.setPointsBlack(0);
		game.setPointsRed(0);
		
		pieces.add(p1);
		pieces.add(p2);
		pieces.add(p3);
		board.setPieces(pieces);
		game.setBoard(board);
		Assertions.assertThat(game.getGanador() == "Jugador negro");
		
		pieces.add(p4);
		pieces.add(p5);
		Assertions.assertThat(game.getGanador() == "Jugador rojo");
	}
	
	@Test
	public void ganadorPorLimitePuntosTest() {
		Game game = new Game();
		game.setTurno(0);
		game.setPointsBlack(9);
		game.setPointsRed(0);
		Assertions.assertThat(game.getGanador() == "Jugador rojo");
		game.setPointsBlack(8);
		game.setPointsRed(9);
		Assertions.assertThat(game.getGanador() == "Jugador negro");
	}
    
}
