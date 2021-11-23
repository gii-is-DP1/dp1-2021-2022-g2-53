package org.springframework.samples.petclinic.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;



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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board", fetch = FetchType.EAGER)
	List<Piece> pieces;

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

	public Integer getNumberOfPiecesByPosition(Integer pos) {
		return (int) this.pieces.stream().filter(x -> x.getPosition() == pos).count();
	}

	public String binary(Integer pos) {
		List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == pos && x.getColor().equals("red"))
				.collect(Collectors.toList());
		List<Piece> blacks = this.pieces.stream().filter(x -> x.getPosition() == pos && x.getColor().equals("black"))
				.collect(Collectors.toList());
		if (reds.size() > 0 && blacks.size() == 0) {
			return "red";
		} else if (reds.size() == 0 && blacks.size() > 0) {
			return "black";
		} else {
			return "";
		}
	}
	
	public boolean moveInvalid(List<Piece> ls, Movement movement, BindingResult result) {
		boolean res = false;
		List<Piece> piecesDiferentDestiny = this.pieces.stream().filter(
				x -> x.getPosition() == movement.getDestinyPosition() && x.getColor().equals(movement.getTipo())==false)
				.collect(Collectors.toList());
		List<Piece> piecesSameDestiny = this.pieces.stream().filter(
				x -> x.getPosition() == movement.getDestinyPosition() && x.getColor().equals(movement.getTipo()))
				.collect(Collectors.toList());
		List<Piece> piecesAux = pieces.subList(0, movement.getNumber());
		if(piecesAux.size()+piecesSameDestiny.size() == piecesDiferentDestiny.size() && piecesDiferentDestiny.size()!=0) {
			result.reject("moveInvalid", "No puedes mover la ficha a esa casilla");
			res = true;
		}
		return res;
	}
	
	
	public boolean moveInvalidPosition(Movement movement, BindingResult result) {
		boolean res = false;
		Integer posini = movement.getInitialPosition();
		Integer posfin = movement.getDestinyPosition();
		if(posini == 1 && (posfin==6 || posfin==7 || posfin==5 || posfin==1)) {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 2 && (posfin==3 || posfin==6 || posfin==7 || posfin==2)) {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 3 && (posfin==2 || posfin==5 || posfin==7 || posfin==3))  {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 4 && posfin==4)  {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 5 && (posfin==1 || posfin==3 || posfin==6 || posfin==5)) {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 6 && (posfin==1 || posfin==2 || posfin==5 || posfin==6)) {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}else if(posini == 7 && (posfin==1 || posfin==2 || posfin==3 || posfin==7)) {
			result.reject("moveInvalidPosition", "No puedes mover la ficha a esa casilla");
			res = true;
		}
		return res;
	}

	public void movePieces(Movement movement, BindingResult result) throws MoveInvalidException {
		if (movement.getTipo().equals("red") || (movement.getTipo().equals("black"))) {
			List<Piece> pieces = this.pieces.stream().filter(
					x -> x.getPosition() == movement.getInitialPosition() && x.getColor().equals(movement.getTipo()))
					.collect(Collectors.toList());
			if(pieces.isEmpty() || pieces.size()<movement.getNumber()) {
				result.reject("moveInvalidPosition2", "No puedes escoger esa casilla inicial");
			}else {
			List<Piece> piecesAux = pieces.subList(0, movement.getNumber());
			if(movement.getTipo().equals("red") || movement.getTipo().equals("black")) {
				if(moveInvalid(pieces, movement, result) == false && moveInvalidPosition(movement, result) == false) {
					piecesAux.stream().forEach(x -> x.setPosition(movement.getDestinyPosition()));
				}
			}else {
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
			List<Piece> blacks = this.pieces.stream().filter(x -> x.getPosition() == p && x.getColor().equals("black"))
					.collect(Collectors.toList());
			List<Piece> reds = this.pieces.stream().filter(x -> x.getPosition() == p && x.getColor().equals("red"))
					.collect(Collectors.toList());
			if (reds.size() > blacks.size()) {
				red++;
			} else if (reds.size() < blacks.size()) {
				black++;
			}
		 
		}
		ls.add(red);
		ls.add(black);
		return ls;
	}
}
