package org.springframework.samples.petclinic.game;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameTest {
	
	@Autowired
	private GameService gameService;
	
	@Test
	public void TestPoints() {
		Game game = gameService.findId(1);
		Assertions.assertThat(game.getPointsBlack().equals(3));
		Assertions.assertThat(game.getPointsRed().equals(8));
	}
	
	/* public void addPointsTest() {
		Game game = gameService.findId(2);
		
	} */
	

}
