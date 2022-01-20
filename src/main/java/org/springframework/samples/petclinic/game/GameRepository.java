package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer>{

	@Query("SELECT g from Game g WHERE g.token = :token")
	Game findByToken(@Param("token") String token);
	
	
}
