package org.springframework.samples.petris.game;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class PieceTest {
    


    @Autowired
    private BoardService boardService;

    
    
    /*
    @Test
    public void getNumberOfPiecesInPositionTest() {
        
        Board board = boardService.findById(2);
        List<Piece> pieces = new ArrayList<>();
        Piece piece = new Piece();
        piece.setBoard(board);
        piece.setPosition(6);
        piece.setColor("black");
        
        pieces.add(piece);
        
        Piece pieceRed = new Piece();
        pieceRed.setBoard(board);
        pieceRed.setPosition(6);
        pieceRed.setColor("red");
        pieces.add(pieceRed);
        
        Piece pieceNewBlack2 = new Piece();
        pieceNewBlack2.setBoard(board);
        pieceNewBlack2.setPosition(6);
        pieceNewBlack2.setColor("black");
        pieces.add(pieceNewBlack2);
        pieceService.save(piece);
        pieceService.save(pieceNewBlack2);
        pieceService.save(pieceRed);
        

        Assertions.assertThat(pieceEntidad.getNumberOfPiecesInPosition(6)).isEqualTo(pieces.size());
    }
    */
    @Test
    public void getPositionXInPixelsPiecesTest() {
    
        Board board = boardService.findById(2);
        
        Integer position = 6;
        Piece piece = new Piece();
        piece.setBoard(board);
        piece.setPosition(position);
        piece.setColor("black");
        
        Integer position2 = 3;
        Piece piece2 = new Piece();
        piece2.setBoard(board);
        piece2.setPosition(position2);
        piece2.setColor("red");
        
        Assertions.assertThat(piece.getPositionXInPixels(position)).isEqualTo(80);
        Assertions.assertThat(piece2.getPositionXInPixels(position2)).isEqualTo(10);

    }
    

    @Test
    public void getPositionYInPixelsBlackPiecesTest() {
    
        Board board = boardService.findById(2);
    
        Integer position = 6;
        Piece piece = new Piece();
        piece.setBoard(board);
        piece.setPosition(position);
        piece.setColor("black");
        
        
        
        
        Assertions.assertThat(piece.getPositionYInPixels(position)).isEqualTo(210);
    }
    
    @Test
    public void getPositionYInPixelsRedPiecesTest() {
    
        Board board = boardService.findById(2);
        
        Integer position = 5;
        
        Piece pieceRed = new Piece();
        pieceRed.setBoard(board);
        pieceRed.setPosition(position);
        pieceRed.setColor("red");
        
        Assertions.assertThat(pieceRed.getPositionYInPixels(position)).isEqualTo(170);
    }

}
