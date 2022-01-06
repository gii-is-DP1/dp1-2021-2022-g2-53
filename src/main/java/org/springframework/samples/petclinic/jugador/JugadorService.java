package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Board;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {

	@Autowired
	JugadorRepository jugadorRepo;
	@Autowired
	private GameService gameService;

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
	public List<Game> historialgame(int id) {
		List<Game> lista = new ArrayList<Game>();
		for (int i = 0; i < jugadorCount(); i++) {
			Jugador jugador = findId(i);
			if (jugador.getPersona().getId() == id) {
				Game game = jugador.getGame();
				lista.add(game);
			}

		}
		return lista;
	}
}
