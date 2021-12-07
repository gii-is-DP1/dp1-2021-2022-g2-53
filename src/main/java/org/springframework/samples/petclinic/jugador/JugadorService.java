package org.springframework.samples.petclinic.jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {
	
	@Autowired
	JugadorRepository jugadorRepo;
	
	@Transactional
	public void save(Jugador jugador) {
		jugadorRepo.save(jugador);
	}
}
