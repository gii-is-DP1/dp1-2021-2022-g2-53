package org.springframework.samples.petris.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

	@Query("SELECT g from Game g WHERE g.pointsRed < 9 AND g.pointsBlack < 9")
	Collection<Game> findCurrentGames();
	
	@Query("SELECT g from Game g WHERE g.pointsRed >= 9 OR g.pointsBlack >= 9")
	Collection<Game> findFinishedGames();
	
	@Query("SELECT g from Game g WHERE g.token = :token")
	Game findByToken(@Param("token") String token);
	
	@Query("SELECT g FROM Game g")
	Collection<Game> getJugadoresbyGame();

}
