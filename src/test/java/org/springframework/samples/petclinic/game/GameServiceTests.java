package org.springframework.samples.petclinic.game;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {

	@Autowired
	private GameService gameService;


	@Test
	public void GameCountTest() {
		int count = gameService.gameCount();
		assertEquals(count, 2);
	}

	@Test
	public void SaveGameTest() {
		Game game = new Game();
		gameService.save(game);
		int count = gameService.gameCount();
		assertEquals(count, 3);

	}

	@Test
	public void DeleteGameTest() {
		Game game = new Game();
		gameService.save(game);
		gameService.delete(game);
		int count = gameService.gameCount();
		assertEquals(count, 2);

	}

	@Test
	public void FindByIdTest() {
		Game n = gameService.findId(2);
		assertEquals(n.getId(), 2);
		assertEquals(n.getBoard().getId(), 2);

	}

	@Test
	public void FindGameByToken() {
		Game game = gameService.findId(2);
		Assertions.assertThat(game.getToken()).isEqualTo("abc-abc");
	}

}