package org.springframework.samples.petclinic.persona;

import java.util.List;


import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.Person;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter
@Setter

public class Persona extends Person {

	private String userName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private List<Jugador> jugadores;
	
}
