package org.springframework.samples.petris.jugador;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.model.BaseEntity;
import org.springframework.samples.petris.persona.Persona;

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

}
