package org.springframework.samples.petclinic.persona;

import java.util.List;



import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.user.User;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter
@Setter

public class Persona extends Person {

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "username")
	private User user;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private List<Jugador> jugadores;
	
}
