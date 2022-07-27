package org.springframework.samples.petclinic.game;

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

import org.springframework.samples.petclinic.model.BaseEntity;

import org.springframework.validation.BindingResult;

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

	public Board() {
		this.background = "/resources/images/tablero.jpg";
		this.width = 500;
		this.height = 300;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	List<Piece> pieces;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
	List<Sarcine> sarcines;

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
		if (reds.size() >= 4 && blacks.size() == 0 && !containsSarcine(pos, red_color) && redsSar.size() < 5) {
			return red_color_sarcine;
		} else if (reds.size() == 0 && blacks.size() >= 4 && !containsSarcine(pos, black_color)
				&& blacksSar.size() < 5) {
			return black_color_sarcine;
		} else if (reds.size() > 0 && blacks.size() == 0 && !containsSarcine(pos, black_color) && reds.size() < 20) {
			return red_color;
		} else if (reds.size() == 0 && blacks.size() > 0 && !containsSarcine(pos, red_color) && blacks.size() < 20) {
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
		if (reds.size() >= 5 && !containsSarcine(pos, red_color) && redsSar.size() < 5) {
			return red_color_sarcine;
		} else if ( blacks.size() >= 5 && !containsSarcine(pos, black_color) && blacksSar.size() < 5) {
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
		if (posini == 1 && (posfin == 6 || posfin == 7 || posfin == 5 || posfin == 1)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 2 && (posfin == 3 || posfin == 6 || posfin == 7 || posfin == 2)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 3 && (posfin == 2 || posfin == 5 || posfin == 7 || posfin == 3)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 4 && posfin == 4) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 5 && (posfin == 1 || posfin == 3 || posfin == 6 || posfin == 5)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 6 && (posfin == 1 || posfin == 2 || posfin == 5 || posfin == 6)) {
			result.rejectValue("destinyPosition", "moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		} else if (posini == 7 && (posfin == 1 || posfin == 2 || posfin == 3 || posfin == 7)) {
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
			if (pieces.isEmpty()) {
				result.rejectValue("initialPosition", "moveInvalid",
						"La casilla seleccionada está vacía");
			}else if (pieces.size() < movement.getNumber()) {
				result.rejectValue("number", "moveInvalid",
						"Selecciona un número válido de bacterias para mover, en esta casilla solo puedes mover " + pieces.size() + "como máximo");
			}else {
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
		for (Integer i = 1; i <= 7; i++) {
			Integer p = i;
			Integer sarcineRed = 0;
			Integer sarcineBlack = 0;
			List<Piece> blacks = this.pieces.stream()
					.filter(x -> x.getPosition() == p && x.getColor().equals(black_color)).collect(Collectors.toList());
			List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == p && x.getColor().equals(red_color))
					.collect(Collectors.toList());
			if (this.containsSarcine(p, black_color)) {
				sarcineBlack = 5;
			}
			if (this.containsSarcine(p, red_color)) {
				sarcineRed = 5;
			}

			if (reds.size() + sarcineRed > blacks.size() + sarcineBlack) {
				red++;
			} else if (reds.size() + sarcineRed < blacks.size() + sarcineBlack) {
				black++;
			}

		}
		ls.add(red);
		ls.add(black);
		return ls;
	}

	public List<Piece> getAllPiecesByPosition(int i) {
		return this.pieces.stream().filter(x -> x.getPosition() == i).collect(Collectors.toList());

	}

	public Boolean containsSarcine(int position, String color) {
		return 0 != sarcines.stream().filter(x -> x.getPosition() == position && x.getColor().equals(color)).count();
	}
}
