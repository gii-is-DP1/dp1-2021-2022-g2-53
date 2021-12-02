package org.springframework.samples.petclinic.persona;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.jugador.Jugador;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter
@Setter

public class Persona extends NamedEntity {
	
	@Column(name = "contraseña")
	private String contraseña;
	@Column(name = "estado")
	private Boolean estado;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "en_linea")
	private Boolean enLinea;
	@Column(name = "url_foto")
	private String urlFoto;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
//	private Set<Jugador> jugadores;
}
