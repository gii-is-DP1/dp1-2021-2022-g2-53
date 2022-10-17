/*package org.springframework.samples.petris.espectador;

import java.util.Collection;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petris.game.Game;

public interface EspectadorRepository extends CrudRepository<Espectador, Integer> {

	@Query("SELECT e FROM Espectador e WHERE e.persona.id= :id")
	List<Espectador> getEspectadorbypersonid(int id);

	
	@Query("SELECT e.game FROM Espectador e WHERE e.persona.id= :id")
	Collection<Game> getEspectadorbygameId(int id);


}*/