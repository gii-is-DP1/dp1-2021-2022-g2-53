package org.springframework.samples.petris.jugador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaRepository;
import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.game.GameService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {

	
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaRepository personaRepo;
	@Autowired
	private GameService gameService;
	
	
	@Test
	public void JugadorCountTest() {
		int count = jugadorService.jugadorCount();
		assertEquals(count, 4);
	}
	
	@Test
	public void FindByIdTest() {
		Jugador n = jugadorService.findId(2);
		assertEquals(n.getId(), 2);
		

	}

	@Test
	public void SaveJugadorTest() {
		Jugador jugador = new Jugador();
		jugador.setPersona(personaRepo.findById(2).get());
		jugador.setGame(gameService.findId(2));
		jugadorService.save(jugador);
		
		int count = jugadorService.jugadorCount();
		assertEquals(count, 5);

	}

	@Test
	public void historialgameTest() {
	
		
		Persona persona = personaRepo.findById(2).get();	
		List<Game> list = jugadorService.historialgame(persona);
		assertEquals(list.size(), 2);
	}

	

}
