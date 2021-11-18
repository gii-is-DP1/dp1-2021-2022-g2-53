package org.springframework.samples.petclinic.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import javax.persistence.Table;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class Board extends BaseEntity {
    String background;
    @Positive
    int width;
    @Positive
    int height;
    @OneToOne
    Game game;

    public Board(){
        this.background="/resources/images/tablero.jpg";
        this.width=500;
        this.height=300;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board",fetch = FetchType.EAGER)
    List<Piece> pieces;
    
    public List<Piece> getAllPiecesInTheSamePosition(Piece piece){
    	List<Piece> ls = this.pieces.stream().filter(x->x.getPosition()==piece.getPosition()).collect(Collectors.toList());
    	Comparator<Piece> cmp = Comparator.comparingInt(x->x.getId());
    	ls.sort(cmp);
    	return ls;
    }
    
    public List<Piece> getAllPiecesInTheSamePositionAndSameColor(Piece piece){
    	List<Piece> ls = this.pieces.stream().filter(x->x.getPosition()==piece.getPosition()&& x.getColor().equals(piece.getColor())).collect(Collectors.toList());
    	Comparator<Piece> cmp = Comparator.comparingInt(x->x.getId());
    	ls.sort(cmp);
    	return ls;
    }
    
    public Integer getNumberOfPiecesByPosition(Integer pos){
    	return (int) this.pieces.stream().filter(x->x.getPosition()==pos).count();
    }
    
    public void movePieces(Movement movement) {
    	if (movement.getTipo().equals("red") || (movement.getTipo().equals("black"))) {
    		List<Piece> pieces = this.pieces.stream().filter(x->x.getPosition()==movement.getInitialPosition()&& x.getColor().equals(movement.getTipo())).collect(Collectors.toList());
        	pieces.subList(0, movement.getNumber());
        	pieces.stream().forEach(x->x.setPosition(movement.getDestinyPosition()));
    	} else {
    		//faltan la fase binaria y de contamincacion
    	}
    }
}