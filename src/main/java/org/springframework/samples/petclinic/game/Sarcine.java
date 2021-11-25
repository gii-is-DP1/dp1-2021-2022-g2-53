package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

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

	public Sarcine() {
		super();
	}

	public String getURLimage(Integer size) {
		return color;
	}

	public Integer getPositionXInPixels(Integer size) {
		Integer pos = 0;
		if (this.position == 1 || this.position == 6) {
			pos = 140;
		} else if (this.position == 2 || this.position == 7) {
			pos = 315;
		} else if (this.position == 3) {
			pos = 70;
		} else if (this.position == 4) {
			pos = 240;
		} else if (this.position == 5) {
			pos = 415;
		} else {
			return null;
		}
		if (this.color.equals("red")) {
			return pos + 20;
		} else if (this.color.equals("black")) {
			return pos - 20;
		} else {
			return null;
		}
	}

	public Integer getPositionYInPixels(Integer size) {
		Integer pos = 0;
		if (this.position == 1 || this.position == 2) {
			pos = 50;
		} else if (this.position == 3 || this.position == 4 || this.position == 5) {
			pos = 150;
		} else if (this.position == 6 || this.position == 7) {
			pos = 230;
		} else {
			return null;
		}

		return pos;
	}

}