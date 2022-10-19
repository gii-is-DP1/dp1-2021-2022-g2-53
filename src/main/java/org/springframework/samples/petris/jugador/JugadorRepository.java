package org.springframework.samples.petris.jugador;

import java.util.Collection;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.persona.Persona;

public interface JugadorRepository extends CrudRepository<Jugador, Integer> {

	@Query("SELECT j FROM Jugador j WHERE j.persona.id= :id")
	List<Jugador> getJugadorbypersonid(int id);
	
	

	@Query("SELECT j FROM Jugador j WHERE j.color= :color")
	List<Jugador> getJugadorbycolor(String color);
	
	
	@Query("SELECT j.game FROM Jugador j WHERE j.persona.id= :id")
	Collection<Game> getJugadorbygameId(int id);
	
	@Query("SELECT COUNT(j.game) FROM Jugador j")
	Integer getGamesFromPersona();
	
	@Query("SELECT j FROM Jugador j")
	List<Jugador> getJugadores();
	
	


}
