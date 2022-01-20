package org.springframework.samples.petclinic.game;

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
	
	/* @Test
	public void ganadorPorBacteriasTest() {
		Game game = new Game();
		Board board = new Board();
		Piece p1, p2, p3, p4, p5, p6, p7;
		p1 = p2 = p3 = p4 = p5 = p6 = p7 = new Piece();
		p1.setColor("red");
		p2.setColor("red");
		p3.setColor("red");
		p4.setColor("black");
		p5.setColor("black");
		p6.setColor("black");
		p7.setColor("black");
		List<Piece> pieces = List.of(p1, p2, p3, p4, p5, p6, p7);
		
		game.setTurno(game.getTurnos().size()-1);
		game.setPointsBlack(0);
		game.setPointsRed(0);
		board.setPieces(pieces);
		game.setBoard(board);
		Assertions.assertThat(game.getGanador() == "Jugador rojo");
	} */
	
}
