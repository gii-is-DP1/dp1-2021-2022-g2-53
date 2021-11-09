package org.springframework.samples.petclinic.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import javax.persistence.Table;
import java.util.List;
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
}