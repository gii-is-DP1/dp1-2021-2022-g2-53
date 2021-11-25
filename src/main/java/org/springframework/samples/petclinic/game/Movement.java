package org.springframework.samples.petclinic.game;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement extends BaseEntity{

	@Range(min=1,max=7)
	private Integer initialPosition;
	@Range(min=1,max=4)
	private Integer number;
	@Range(min=1,max=7)
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
