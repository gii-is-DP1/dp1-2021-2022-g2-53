package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JugadorRepository extends CrudRepository<Jugador, Integer>{
	
	@Query("SELECT j FROM Jugador j WHERE j.persona.id= :id")
    List<Jugador> getJugadorbypersonid(int id);

}
