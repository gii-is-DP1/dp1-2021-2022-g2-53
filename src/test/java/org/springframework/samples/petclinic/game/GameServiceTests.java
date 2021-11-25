package org.springframework.samples.petclinic.game;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
	  
	  @Test
	    public void TestBinary() {
	        Board board= new Board();
	        board.setId(1);
	        Piece red= new Piece();
	        red.setColor("red");
	        red.setPosition(1);
	        red.setId(1);
	        red.setBoard(board);
	        Piece black= new Piece();
	        black.setColor("black");
	        black.setPosition(2);
	        black.setId(2);
	        black.setBoard(board);
//	        assertThat(1,  );
	        
	    }
//	  @Test
//	    public void binary() {
//	        Board board= new Board();
//	        
//	        assertEquals(n.getId(),2);
//	        assertEquals(n.getBoard().getId(), 2);
//	        
//	    }
//	
//	@Test
//	public void test3() {
//		int n=1;
//		Game count=(int)gameService.findId(n).getId();
//		assertEquals(count, 2);
//	}

}

