package org.springframework.samples.petris.game;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petris.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sarcine extends BaseEntity {

	String color;

	@Range(min = 1, max = 7)
	int position;

	@ManyToOne
	@JoinColumn(name = "board_id")
	Board board;

	private static final String red_color = "red";
	private static final String black_color = "black";

	private static final Integer posicion1 = 1;
	private static final Integer posicion2 = 2;
	private static final Integer posicion3 = 3;
	private static final Integer posicion4 = 4;
	private static final Integer posicion5 = 5;
	private static final Integer posicion6 = 6;
	private static final Integer posicion7 = 7;

	public Sarcine() {
		super();
	}

	public String getURLimage(Integer size) {
		return color;
	}

	public Integer getPositionXInPixels(Integer size) {
		Integer pos = 0;
		if (this.position == posicion1 || this.position == posicion6) {
			pos = 140;
		} else if (this.position == posicion2 || this.position == posicion7) {
			pos = 315;
		} else if (this.position == posicion3) {
			pos = 70;
		} else if (this.position == posicion4) {
			pos = 240;
		} else if (this.position == posicion5) {
			pos = 415;
		} else {
			return null;
		}
		if (this.color.equals(red_color)) {
			return pos + 20;
		} else if (this.color.equals(black_color)) {
			return pos - 20;
		} else {
			return null;
		}
	}

	public Integer getPositionYInPixels(Integer size) {
		Integer pos = 0;
		if (this.position == posicion1 || this.position == posicion2) {
			pos = 50;
		} else if (this.position == posicion3 || this.position == posicion4 || this.position == posicion5) {
			pos = 140;
		} else if (this.position == posicion6 || this.position == posicion7) {
			pos = 230;
		} else {
			return null;
		}

		return pos;
	}

}