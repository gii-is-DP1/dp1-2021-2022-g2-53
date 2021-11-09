package org.springframework.samples.petclinic.jugador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.persona.Persona;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jugadores")
@Getter
@Setter

public class Jugador extends NamedEntity {
	
	@Column(name = "color")
	private boolean color;

	@ManyToOne
	@JoinColumn(name = "partidas_jugadas")
	private Persona persona;

}
