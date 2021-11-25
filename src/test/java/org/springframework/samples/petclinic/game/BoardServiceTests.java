package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardServiceTests {
	
	@Autowired
    private GameService gameService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private PieceService pieceService;

    
    //en la base de datos tenemos 2 board ya creadas
    @Test
    public void boardCountTest() {
        int count=boardService.boardCount();
        assertEquals(count, 2);
    }
    @Test
  //en la base de datos tenemos 2 board ya creadas una con id=1 y otra con id=2
    public void findByIdBoardTest(){
    	int id= 2;
    	 Board board = boardService.findById(id);
         assertEquals(board.getId(), id);
	}
    
    
  //en la base de datos tenemos 2 board ya creadas
      @Test
        public void SaveBoardTest() {
            Board board = new Board();
            boardService.save(board);
            int count=boardService.boardCount();
            assertEquals(count, 3);

        }
      
    //en la base de datos tenemos 2 board ya creadas
      @Test
        public void DeleteboardTest() {
    	  Board board = new Board();
            boardService.save(board);
            boardService.delete(board);
            int count=boardService.boardCount();
            assertEquals(count,2);

        }

     
}
