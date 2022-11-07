package org.springframework.samples.petris.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PieceServiceTests {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PieceService pieceService;
	
    @Test
    public void piecesCount() {
        int count=pieceService.pieceCount();
        assertEquals(count, 26);
    }
    
    @Test
    public void SavePiece() {
    	Game game= gameService.findId(1);
        Board board = boardService.findById(2);
        board.setGame(game);
        Piece piece=new Piece();
        piece.setBoard(board);
        piece.setColor("red");
        piece.setPosition(1);
        pieceService.save(piece);
        int count= pieceService.pieceCount();
        assertEquals(count, 27);
}
    
    
    @Test
    public void DeletePiece() {
        Board board = boardService.findById(2);
        Piece piece=new Piece();
        piece.setBoard(board);
        piece.setColor("red");
        piece.setPosition(1);
        pieceService.save(piece);
        pieceService.delete(piece);
        int count=gameService.gameCount();
        assertEquals(count,2);

    }
    
    @Test
    public void TestDeleteById() {
    	Game game = gameService.findId(1);
        Board board = boardService.findById(1);
        board.setGame(game);
        pieceService.deleteById(5);
        int count=pieceService.pieceCount();
        assertEquals(count, 25);

    }
    
    
    
}
