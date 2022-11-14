package org.springframework.samples.petris.chat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.model.BaseEntity;
import org.springframework.samples.petris.persona.Persona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

	@NotNull
	String text;

	@ManyToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;

	@OneToOne
	@JoinColumn(name = "game_id")
	Game game;

}