package org.springframework.samples.petclinic.game;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement extends BaseEntity {

	@Range(min = 1, max = 7)
	//@NotNull(message = "Debe ser un valor entre 1 y 7")
	@Valid
	private Integer initialPosition;
	@Range(min = 1, max = 4)
	//@NotNull(message = "Debe ser un valor entre 1 y 4")
	@Valid
	private Integer number;
	@Range(min = 1, max = 7)
	//@NotNull(message = "Debe ser un valor entre 1 y 7")
	@Valid
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
