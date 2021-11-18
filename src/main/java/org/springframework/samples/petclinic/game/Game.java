package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.jugador.Jugador;
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
	/*@OneToMany(cascade = CascadeType.ALL,mappedBy = "game",fetch = FetchType.EAGER)
	private List<Jugador> jugadores;*/
	@Min(0)
	private Integer turno;
	@OneToOne
	private Board board;
	
	

	public Game(Integer pointsBlack, Integer pointsRed, Board board) {
		super();
		this.pointsBlack = pointsBlack;
		this.pointsRed = pointsRed;
		this.board = board;
		//this.jugadores = jugadores;
		this.turno = 0;
	}

	public Game() {
		super();
	}
	
	public List<String> getTurnos() {
		//faltan varias cosas
		List<String> turnos = new ArrayList<>();
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		return turnos;
	}
	
	
	

}
