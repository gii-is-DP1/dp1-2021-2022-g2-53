package org.springframework.samples.petris.game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.model.BaseEntity;
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

	private static final Integer numero_maximo_puntos_para_perder = 9;
	private static final Integer maximo_letras_token = 3;
	private static final Integer maximo_numeros_token = 3;
	private static final Integer total_caracteres_token = 6;

	@Min(0)
	private Integer turno;
	@OneToOne
	private Board board;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
	
	@Size(max=2)
	private List<Jugador> jugadores;

	public Game(Integer pointsBlack, Integer pointsRed, Board board) {
		super();
		this.pointsBlack = pointsBlack;
		this.pointsRed = pointsRed;
		this.board = board;
		this.turno = 0;
	}

	private static final String red_color = "red";
	private static final String black_color = "black";
	private static final String binary_type = "binary";
	private static final String pollution_type = "pollution";

	public Game() {
		super();
	}

	/* 
	public Map<Integer, String> getJugadoresPorColor(List<Jugador> jugadores) {
		Map<Integer, String> res = new HashMap<Integer, String>();
		if (jugadores.size()<=2) {
		for (int i = 0; i < jugadores.size(); i++) {
			res.put(i, jugadores.get(i).getColor());
		}
		return res;
		}else{
			return null;
		}
	
	}
	*/

	public String generarToken() {
		String bancoLetras = "abcdefghijklmnopqrstuvw";
		String bancoNumeros = "123456789";
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i <= total_caracteres_token; i++) {
			if (i < maximo_letras_token) {
				int randomInt = secureRandom.nextInt(bancoLetras.length());
				char randomChar = bancoLetras.charAt(randomInt);
				strB.append(randomChar);
			}
			if (i == maximo_letras_token) {
				strB.append("-");
			}
			if (i > maximo_numeros_token) {
				int randomNumInt = secureRandom.nextInt(bancoNumeros.length());
				char randomNum = bancoNumeros.charAt(randomNumInt);
				strB.append(randomNum);
			}
		}
		return strB.toString();
	}

	public List<String> getTurnos() {
		List<String> turnos = new ArrayList<>();
		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(pollution_type);

		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(pollution_type);

		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(pollution_type);

		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(black_color);
		turnos.add(red_color);
		turnos.add(binary_type);
		turnos.add(red_color);
		turnos.add(black_color);
		turnos.add(binary_type);
		turnos.add(pollution_type);

		turnos.add("fin");
		return turnos;
	}

	public String getGanador() {
		if (this.getTurnos().get(this.getTurno()).equals("fin")) {
			if (this.getPointsBlack().equals(this.getPointsRed())) {
				List<Piece> lsred = this.getBoard().getAllPiecesSameColor(red_color);
				List<Piece> lsblack = this.getBoard().getAllPiecesSameColor(black_color);
				List<Sarcine> lsredsar = this.getBoard().getAllSarcinesSameColor(red_color);
				List<Sarcine> lsblacksar = this.getBoard().getAllSarcinesSameColor(black_color);
				if (lsred.size() < lsblack.size()) {
					return "Jugador rojo";
				} else if (lsred.size() > lsblack.size()) {
					return "Jugador negro";
				} else if (lsred.size() == lsblack.size()) {

					if (lsredsar.size() < lsblacksar.size()) {
						return "Jugador rojo";
					} else if (lsredsar.size() > lsblacksar.size()) {
						return "Jugador negro";
					} else if (lsredsar.size() == lsblacksar.size()) {
						return "Empate";
					}

				}
			} else if (this.getPointsBlack() < this.getPointsRed()) {
				return "Jugador negro";
			} else if (this.getPointsBlack() > this.getPointsRed()) {
				return "Jugador rojo";
			}

		} else if (this.getPointsBlack() >= numero_maximo_puntos_para_perder
				|| this.getPointsBlack() >= numero_maximo_puntos_para_perder) {
			this.setTurno(this.getTurnos().size() - 1);
			return this.getGanador();
		}
		return "";
	}

}
