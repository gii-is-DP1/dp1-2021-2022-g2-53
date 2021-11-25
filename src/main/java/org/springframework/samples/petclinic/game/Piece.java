package org.springframework.samples.petclinic.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Piece extends BaseEntity {
    String color;
    @Range(min=1,max=7)
    int position;
    @ManyToOne
	@JoinColumn(name = "board_id")
    Board board;
    String type;
    
    public Piece() {
		super();
	}
    
    public String getURLimage(Integer size) {
    	if(this.type.equals("bacterium")) {
    		return color;
    	}	else {
    		return null;
    	}
    }
    
    public Integer getNumberOfPiecesInPosition(Integer i){
    	return (int) this.board.getPieces().stream().filter(x->x.getPosition()==this.position).count();
    }
    
    public Integer getPositionXInPixels(Integer size) {
    	Integer pos = 0;
    	if (this.position==1 || this.position == 6) {
            pos = 100;
        } else if (this.position==2 || this.position == 7) {
            pos = 275;
        } else if (this.position==3) {
            pos = 30;
        } else if (this.position==4) {
            pos = 200;
        } else if (this.position==5) {
            pos = 375;
        } else {
            return null;
        }
    	Integer modifier = board.getAllPiecesInTheSamePositionAndSameColor(this).indexOf(this);
    	return pos + modifier * 20;
    }
    
    public Integer getPositionYInPixels(Integer size) {
    	Integer pos = 0;
    	if (this.position==1 || this.position == 2) {
            pos = 50;
        } else if (this.position==3 || this.position == 4 || this.position == 5) {
        	pos = 150;
        } else if (this.position==6 || this.position==7){
        	pos = 230;
        } else {
            return null;
        }
    	
    	if (this.color.equals("red")) {
    		return pos + 20;
    	} else if (this.color.equals("black")) {
    		return pos - 20;
    	} else {
    		return null;
    	}
    }

	
}