package org.springframework.samples.petclinic.game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game extends BaseEntity {
	
	
	private static final SecureRandom secureRandom = new SecureRandom();
	private Integer pointsBlack;
	private Integer pointsRed;
	private String token;
	/*@OneToMany(cascade = CascadeType.ALL,mappedBy = "game",fetch = FetchType.EAGER)
	private List<Jugador> jugadores;*/
	@Min(0)
	private Integer turno;
	@OneToOne
	private Board board;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
	private List<Jugador> jugadores;
	
	

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
	
	public Map<Integer, String> getJugadoresPorColor(List<Jugador> jugadores) {
		Map<Integer, String> res = new HashMap<Integer, String>();
		for (int i = 0; i < jugadores.size(); i++) {
			res.put(i, jugadores.get(i).getColor());
		}
		return res;
	}
	
	
	public String generarToken() {
		String bancoLetras="abcdefghijklmnopqrstuvw";
		String bancoNumeros = "123456789";
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i <= 6; i++) {
			if(i < 3) {
			int randomInt = secureRandom.nextInt(bancoLetras.length());
	        char randomChar = bancoLetras.charAt(randomInt);
	        strB.append(randomChar);
			}
	        if(i == 3) {
	        	strB.append("-");
	        }
	        if(i > 3) {
	        	int randomNumInt = secureRandom.nextInt(bancoNumeros.length());
		        char randomNum = bancoNumeros.charAt(randomNumInt);
		        strB.append(randomNum);
	        }
		}
		return strB.toString();
	}
	
	
	
	public List<String> getTurnos() {
		//faltan varias cosas
		List<String> turnos = new ArrayList<>();
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("pollution");
		
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("pollution");
		
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("pollution");
		
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("black");
		turnos.add("red");
		turnos.add("binary");
		turnos.add("red");
		turnos.add("black");
		turnos.add("binary");
		turnos.add("pollution");
		
		turnos.add("fin");
		return turnos;
	}
	
	
	

}
