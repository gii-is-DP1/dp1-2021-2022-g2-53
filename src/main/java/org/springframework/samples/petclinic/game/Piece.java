package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
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
    Board board;
    
    
    public Integer getPositionXInPixels(Integer size) {
    	if (this.position==1 || this.position == 6) {
            return 150;
        } else if (this.position==2 || this.position == 7) {
            return 350;
        } else if (this.position==3) {
            return 50;
        } else if (this.position==4) {
            return 250;
        } else if (this.position==5) {
            return 450;
        } else {
            return null;
        }
    }
    
    public Integer getPositionYInPixels(Integer size) {
    	if (this.position==1 || this.position == 2) {
            return 50;
        } else if (this.position==3 || this.position == 4 || this.position == 5) {
            return 150;
        } else if (this.position==6 || this.position==7){
            return 250;
        } else {
            return null;
        }
    }
}