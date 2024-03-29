package org.springframework.samples.petris.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.samples.petris.model.BaseEntity;
import org.springframework.validation.BindingResult;

@Entity
@Table(name = "boards")
@Getter
@Setter
@Slf4j
public class Board extends BaseEntity {

	private static final Integer numero_de_casillas = 7;
	private static final Integer numero_maximo_de_fichas_en_casilla = 5;
	private static final Integer numero_maximo_de_fichas = 20;
	private static final Integer numero_maximo_antes_de_sarcina = 4;

	private static final Integer posicion1 = 1;
	private static final Integer posicion2 = 2;
	private static final Integer posicion3 = 3;
	private static final Integer posicion4 = 4;
	private static final Integer posicion5 = 5;
	private static final Integer posicion6 = 6;
	private static final Integer posicion7 = 7;

	String background;
	@Positive
	int width;
	@Positive
	int height;

	public Board() {
		this.background = "/resources/images/tablero.png";
		this.width = 500;
		this.height = 300;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	List<Piece> pieces;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	List<Sarcine> sarcines;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "board")
	Game game;

	private static final String red_color = "red";
	private static final String black_color = "black";
	private static final String red_color_sarcine = "red_sarcine";
	private static final String black_color_sarcine = "black_sarcine";

	public List<Piece> getAllPiecesInTheSamePosition(Piece piece) {
		List<Piece> ls = this.pieces.stream().filter(x -> x.getPosition() == piece.getPosition())
				.collect(Collectors.toList());
		Comparator<Piece> cmp = Comparator.comparingInt(x -> x.getId());
		ls.sort(cmp);
		return ls;
	}

	public List<Piece> getAllPiecesInTheSamePositionAndSameColor(Piece piece) {
		List<Piece> ls = this.pieces.stream()
				.filter(x -> x.getPosition() == piece.getPosition() && x.getColor().equals(piece.getColor()))
				.collect(Collectors.toList());
		Comparator<Piece> cmp = Comparator.comparingInt(x -> x.getId());
		ls.sort(cmp);
		return ls;
	}

	public List<Piece> getAllPiecesSameColor(String color) {
		List<Piece> ls = this.pieces.stream().filter(x -> x.getColor().equals(color)).collect(Collectors.toList());
		Comparator<Piece> cmp = Comparator.comparingInt(x -> x.getId());
		ls.sort(cmp);
		return ls;
	}

	public List<Sarcine> getAllSarcinesSameColor(String color) {
		List<Sarcine> ls = this.sarcines.stream().filter(x -> x.getColor().equals(color)).collect(Collectors.toList());
		Comparator<Sarcine> cmp = Comparator.comparingInt(x -> x.getId());
		ls.sort(cmp);
		return ls;
	}

	public Integer getNumberOfPiecesByPosition(Integer pos) {
		return (int) this.pieces.stream().filter(x -> x.getPosition() == pos).count();
	}

	public String binaryboard(Integer pos) {
		List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == pos && x.getColor().equals(red_color))
				.collect(Collectors.toList());
		List<Piece> blacks = this.pieces.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(black_color)).collect(Collectors.toList());
		List<Sarcine> redsSar = this.sarcines.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(red_color)).collect(Collectors.toList());
		List<Sarcine> blacksSar = this.sarcines.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(black_color)).collect(Collectors.toList());
		if (reds.size() >= numero_maximo_antes_de_sarcina && blacks.size() == 0 && !containsSarcine(pos, red_color)
				&& redsSar.size() < numero_maximo_de_fichas_en_casilla) {
			return red_color_sarcine;
		} else if (reds.size() == 0 && blacks.size() >= numero_maximo_antes_de_sarcina
				&& !containsSarcine(pos, black_color) && blacksSar.size() < numero_maximo_de_fichas_en_casilla) {
			return black_color_sarcine;
		} else if (reds.size() > 0 && blacks.size() == 0 && !containsSarcine(pos, black_color)
				&& reds.size() < numero_maximo_de_fichas) {
			return red_color;
		} else if (reds.size() == 0 && blacks.size() > 0 && !containsSarcine(pos, red_color)
				&& blacks.size() < numero_maximo_de_fichas) {
			return black_color;
		} else {
			return "";
		}
	}

	public String checkSarcines(Integer pos) {
		List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == pos && x.getColor().equals(red_color))
				.collect(Collectors.toList());
		List<Piece> blacks = this.pieces.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(black_color)).collect(Collectors.toList());
		List<Sarcine> redsSar = this.sarcines.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(red_color)).collect(Collectors.toList());
		List<Sarcine> blacksSar = this.sarcines.stream()
				.filter(x -> x.getPosition() == pos && x.getColor().equals(black_color)).collect(Collectors.toList());
		if (reds.size() >= numero_maximo_de_fichas_en_casilla && !containsSarcine(pos, red_color)
				&& redsSar.size() < numero_maximo_de_fichas_en_casilla) {
			return red_color_sarcine;
		} else if (blacks.size() >= numero_maximo_de_fichas_en_casilla && !containsSarcine(pos, black_color)
				&& blacksSar.size() < numero_maximo_de_fichas_en_casilla) {
			return black_color_sarcine;
		} else {
			return "";
		}
	}

	public boolean moveInvalid(List<Piece> ls, Movement movement, BindingResult result) {
		boolean res = false;
		List<Piece> piecesDiferentDestiny = this.pieces.stream()
				.filter(x -> x.getPosition() == movement.getDestinyPosition()
						&& x.getColor().equals(movement.getTipo()) == false)
				.collect(Collectors.toList());
		List<Piece> piecesSameDestiny = this.pieces.stream().filter(
				x -> x.getPosition() == movement.getDestinyPosition() && x.getColor().equals(movement.getTipo()))
				.collect(Collectors.toList());
		List<Piece> piecesAux = pieces.subList(0, movement.getNumber());
		if (piecesAux.size() + piecesSameDestiny.size() == piecesDiferentDestiny.size()
				&& piecesDiferentDestiny.size() != 0) {
			result.rejectValue("destinyPosition", "moveInvalid2",
					"No puede haber el mismo numero de bacterias en una misma casilla");
			res = true;
		}
		return res;
	}

	public boolean moveInvalid2(List<Piece> ls, Movement movement, BindingResult result) {
		boolean res = false;
		List<Piece> piecesDiferentInitial = this.pieces.stream()
				.filter(x -> x.getPosition() == movement.getInitialPosition()
						&& x.getColor().equals(movement.getTipo()) == false)
				.collect(Collectors.toList());
		List<Piece> piecesSameInitial = this.pieces.stream().filter(
				x -> x.getPosition() == movement.getInitialPosition() && x.getColor().equals(movement.getTipo()))
				.collect(Collectors.toList());
		List<Piece> piecesAux = pieces.subList(0, movement.getNumber());
		if (piecesSameInitial.size() - piecesAux.size() == piecesDiferentInitial.size()
				&& piecesDiferentInitial.size() != 0) {
			result.rejectValue("destinyPosition", "moveInvalid2",
					"No puede haber el mismo numero de bacterias en una misma casilla");
			res = true;
		}
		return res;
	}

	public boolean moveInvalidPosition(Movement movement, BindingResult result) {
		boolean res = false;
		Integer posini = movement.getInitialPosition();
		Integer posfin = movement.getDestinyPosition();
		if (posini == posicion1
				&& (posfin == posicion6 || posfin == posicion7 || posfin == posicion5 || posfin == posicion1)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion2
				&& (posfin == posicion3 || posfin == posicion6 || posfin == posicion7 || posfin == posicion2)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion3
				&& (posfin == posicion2 || posfin == posicion5 || posfin == posicion7 || posfin == posicion3)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion4 && posfin == posicion4) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion5
				&& (posfin == posicion1 || posfin == posicion3 || posfin == posicion6 || posfin == posicion5)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion6
				&& (posfin == posicion1 || posfin == posicion2 || posfin == posicion5 || posfin == posicion6)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == posicion7
				&& (posfin == posicion1 || posfin == posicion2 || posfin == posicion3 || posfin == posicion7)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		}
		return res;
	}

	public void movePieces(Movement movement, BindingResult result) throws MoveInvalidException {
		if (movement.getTipo().equals(red_color) || (movement.getTipo().equals(black_color))) {
			List<Piece> pieces = this.pieces.stream().filter(
					x -> x.getPosition() == movement.getInitialPosition() && x.getColor().equals(movement.getTipo()))
					.collect(Collectors.toList());

			if (pieces.isEmpty() || movement.getInitialPosition().toString().isEmpty()) {
				result.rejectValue("initialPosition", "moveInvalid",
						"La casilla seleccionada no tiene fichas de tu color o pretendes mover una sarcina");

			} else if (pieces.size() < movement.getNumber()) {
				result.rejectValue("number", "moveInvalid",
						"Selecciona un número válido de bacterias para mover, en esta casilla solo puedes mover "
								+ pieces.size() + " como máximo");

			} else {
				List<Piece> piecesAux = pieces.subList(0, movement.getNumber());
				if (movement.getTipo().equals(red_color) || movement.getTipo().equals(black_color)) {
					if (moveInvalid(pieces, movement, result) == false && moveInvalidPosition(movement, result) == false
							&& moveInvalid2(pieces, movement, result) == false) {
						piecesAux.stream().forEach(x -> x.setPosition(movement.getDestinyPosition()));
					}
				} else {
					piecesAux.stream().forEach(x -> x.setPosition(movement.getDestinyPosition()));
				}
			}
		}
	}

	// O -> RED Y 1 -> BLACK
	public List<Integer> pollution() {
		List<Integer> ls = new ArrayList<>();
		Integer red = 0;
		Integer black = 0;
		for (Integer i = 1; i <= numero_de_casillas; i++) {
			Integer p = i;
			Integer sarcineRed = 0;
			Integer sarcineBlack = 0;
			List<Piece> blacks = this.pieces.stream()
					.filter(x -> x.getPosition() == p && x.getColor().equals(black_color)).collect(Collectors.toList());
			List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == p && x.getColor().equals(red_color))
					.collect(Collectors.toList());
			if (this.containsSarcine(p, black_color)) {
				sarcineBlack = numero_maximo_de_fichas_en_casilla;
			}
			if (this.containsSarcine(p, red_color)) {
				sarcineRed = numero_maximo_de_fichas_en_casilla;
			}

			if (reds.size() + sarcineRed > blacks.size() + sarcineBlack) {
				red++;
			} else if (reds.size() + sarcineRed < blacks.size() + sarcineBlack) {
				black++;
			}

			log.info(
					"------------------------------------------------------------------------------------------------");
			log.info("red {} =", red);
			log.info(
					"------------------------------------------------------------------------------------------------");

			log.info(
					"------------------------------------------------------------------------------------------------");
			log.info("black {} =", black);
			log.info(
					"------------------------------------------------------------------------------------------------");

		}
		ls.add(red);
		ls.add(black);
		return ls;
	}

	public List<Piece> getAllPiecesByPosition(int i) {
		log.info("------------------------------------------------------------------------------------------------");
		log.info("Adding {}", i);
		log.info("------------------------------------------------------------------------------------------------");
		return this.pieces.stream().filter(x -> x.getPosition() == i).collect(Collectors.toList());

	}

	public Boolean containsSarcine(int position, String color) {
		log.info("------------------------------------------------------------------------------------------------");
		log.info("Funcionas????");
		log.info("------------------------------------------------------------------------------------------------");
		return 0 != sarcines.stream().filter(x -> x.getPosition() == position && x.getColor().equals(color)).count();
	}
}
