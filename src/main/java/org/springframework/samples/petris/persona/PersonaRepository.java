package org.springframework.samples.petris.persona;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.user.User;

@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {

	@Query("SELECT p from Persona p WHERE p.user = :user")
	Persona getPersonaByUser(@Param("user") User user);

	Page<Persona> findAll(Pageable pageable);

	@Query("SELECT  p from Persona p ")
	Iterable<Persona> getPersonas();

	@Query("SELECT  p.jugadores from Persona p ")
	List<Jugador> getJugadores();

}
