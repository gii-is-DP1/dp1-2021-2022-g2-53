package org.springframework.samples.petclinic.jugador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.game.BoardService;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.persona.Persona;
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private JugadorService jugadorService;
	private PersonaService personaService;

	@Test
	public void historialgameTest() {
	
		Persona personi = personaService.findId(2);
		
		List<Game> list = jugadorService.historialgame(personi);
		assertEquals(list.size(), 2);
	}

	

}