package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.samples.petclinic.game.Game;

import org.springframework.samples.petclinic.persona.Persona;
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

	@Transactional
	public int jugadorCount() {
		return (int) jugadorRepo.count();
	}

	@Transactional
	public Jugador findId(int id) {
		return jugadorRepo.findById(id).get();

	}
	 

	
	@Transactional
	public List<Game> historialgame(Persona persona) {
		List<Game> lista = new ArrayList<Game>();
		List<Jugador> lsjug = jugadorRepo.getJugadorbypersonid(persona.getId());
		for (int i = 0; i < lsjug.size(); i++) {
			Jugador j = lsjug.get(i);
			Game game = j.getGame();
				lista.add(game);
			}

		
		return lista;
	}
}
