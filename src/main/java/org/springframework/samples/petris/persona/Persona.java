package org.springframework.samples.petris.persona;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.model.Person;
import org.springframework.samples.petris.user.User;

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
