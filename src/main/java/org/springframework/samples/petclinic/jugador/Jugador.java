package org.springframework.samples.petclinic.jugador;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.persona.Persona;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jugador extends BaseEntity {

	
	String color;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	Game game;

	/*
	@ManyToOne
	private Game game;*/

}
