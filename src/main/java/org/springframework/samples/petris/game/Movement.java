package org.springframework.samples.petris.game;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petris.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement extends BaseEntity {

	@Range(min = 1, max = 7)
	//@NotNull(message = "Debe ser un valor entre 1 y 7")
	private Integer initialPosition;

	@Range(min = 1, max = 4)
	//@NotNull(message = "Debe ser un valor entre 1 y 4")
	private Integer number;

	@Range(min = 1, max = 7)
	//@NotNull(message = "Debe ser un valor entre 1 y 7")
	private Integer destinyPosition;

	private String tipo;

	public Movement(Integer initialPosition, Integer number, Integer destinyPosition) {
		super();
		this.initialPosition = initialPosition;
		this.number = number;
		this.destinyPosition = destinyPosition;
	}

	// Es necesario para inicializar el movimiento
	public Movement() {

	}

}
