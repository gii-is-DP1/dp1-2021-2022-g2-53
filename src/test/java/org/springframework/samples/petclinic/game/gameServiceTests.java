package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

	@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
	public class gameServiceTests {

	    @Autowired
	    private GameService gameService;
	    @Autowired
	    private BoardService boardService;
	    @Autowired
	    private PieceService pieceService;

	    @Test
	    public void GameCount() {
	        int count=gameService.gameCount();
	        assertEquals(count, 2);
	    }


	      @Test
	        public void SaveGame() {
	            Game game = new Game();
	            gameService.save(game);
	            int count=gameService.gameCount();
	            assertEquals(count, 3);

	        }

	      @Test
	        public void DeleteGame() {
	            Game game = new Game();
	            gameService.save(game);
	            gameService.delete(game);
	            int count=gameService.gameCount();
	            assertEquals(count,2);

	        }

	      @Test
	        public void TestFindById() {
	            Game n=gameService.findId(2);
	            assertEquals(n.getId(),2);
	            assertEquals(n.getBoard().getId(), 2);

	        }
}
		