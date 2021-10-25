package org.springframework.samples.petclinic.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game extends BaseEntity {

	private Integer pointsBlack;
	private Integer pointsRed;

}
