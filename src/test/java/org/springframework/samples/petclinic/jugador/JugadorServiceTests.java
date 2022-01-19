package org.springframework.samples.petclinic.jugador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.petclinic.game.Game;

import org.springframework.samples.petclinic.persona.Persona;
import org.springframework.samples.petclinic.persona.PersonaRepository;

import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {

	
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaRepository personaRepo;

	@Test
	public void historialgameTest() {
	
		
		Persona persona = personaRepo.findById(2).get();	
		List<Game> list = jugadorService.historialgame(persona);
		assertEquals(list.size(), 2);
	}

	

}
