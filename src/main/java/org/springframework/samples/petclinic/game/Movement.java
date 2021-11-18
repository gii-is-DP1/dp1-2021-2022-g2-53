package org.springframework.samples.petclinic.game;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement extends BaseEntity{

	private Integer initialPosition;
	private Integer number;
	private Integer destinyPosition;
	//los valores son "red" "black" "binary" "pollution"
	private String tipo;
	
	public Movement(Integer initialPosition, Integer number, Integer destinyPosition) {
		super();
		this.initialPosition = initialPosition;
		this.number = number;
		this.destinyPosition = destinyPosition;
	}

	public Movement() {
		// TODO Auto-generated constructor stub
	}
	
	
}
