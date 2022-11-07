package org.springframework.samples.petris.jugador;



import org.springframework.beans.factory.annotation.Autowired;


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

	

}
