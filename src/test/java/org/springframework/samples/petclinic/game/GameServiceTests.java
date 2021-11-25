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
	  
	  @Test
//	    public void TestBinary() {
//		  Game game = new Game();
//		  Board board = new Board();
//          Piece pieceb = new Piece();
//          Piece piecer = new Piece();
//          
//          boardService.;
//          pieceb.setColor("black");
//          pieceb.setPosition(3);
//          piecer.setColor("red");
//          piecer.setPosition(5);
//          piecer.setBoard(board);
//          pieceb.setBoard(board);
//          board.addgame(game);
//          pieceService.save(pieceb);
//          pieceService.save(piecer);
//          game.setTurno(0);
//          game.setPointsRed(0);
//          game.setPointsBlack(0);
//          gameService.save(game);
//          assertEquals(board.getNumberOfPiecesByPosition(1),3) ;
          
           
	        
	    
	  
	  
	  @Test
	    public void TestBinaryWhiteTwoPiece() {
		  Game game = new Game();
        game.setId(1);
        Board board = boardService.findById(2);
        board.setGame(game);
        Assertions.assertThat(board.binary(5)).isEqualTo("red");
        Assertions.assertThat(board.binary(5)).isEqualTo("red");
        Assertions.assertThat(board.binary(3)).isEqualTo("black");
        gameService.binary(1);
        Piece pieceNewRed = new Piece();
      pieceNewRed.setPosition(1);
      pieceNewRed.setId(3);
      pieceNewRed.setColor("red");
      pieceNewRed.setBoard(board);
      Piece pieceNewRed2 = new Piece();
      pieceNewRed2.setPosition(1);
      pieceNewRed2.setId(3);
      pieceNewRed2.setColor("red");
      pieceNewRed2.setBoard(board);
      Piece pieceNewBlack = new Piece();
      pieceNewBlack.setPosition(1);
      pieceNewBlack.setId(4);
      pieceNewBlack.setColor("black");
      pieceNewBlack.setBoard(board);
      gameService.save(game);
      Assertions.assertThat(board.getNumberOfPiecesByPosition(1).equals(2)) ;
         
	        
	    }


}

