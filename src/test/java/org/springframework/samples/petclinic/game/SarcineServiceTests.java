package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SarcineServiceTests {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private SarcineService sarcineService;
	
  
    
    @Test
    public void SavePiece() {
		Game game = new Game();
        game.setId(1);
        Board board = boardService.findById(2);
        board.setGame(game);
        Sarcine sarcine=new Sarcine();
        sarcine.setBoard(board);
        sarcine.setColor("red");
        sarcine.setPosition(1);
        sarcineService.save(sarcine);
        int count= sarcineService.sarcineCount();
        assertEquals(count, 3);
}
    
    @Test
    public void piecesCount() {
        int count=sarcineService.sarcineCount();
        assertEquals(count, 2);
    }
    
   
    
    
    
}
