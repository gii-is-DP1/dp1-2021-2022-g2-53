package org.springframework.samples.petclinic.jugador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {

	
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaService personaService;
	
	@Test
	public void jugadorCountTest() {
		int count = jugadorService.jugadorCount();
		assertEquals(count, 2);
	}

	
	@Test
	public void findByIdjugadorTest() {
		Jugador jugador = jugadorService.findId(1);
		assertEquals(jugador.getId(), 1);
	}
	
	@Test
	public void findByIdjugadorTest2() {
		Jugador jugador = jugadorService.findId(2);
		assertEquals(jugador.getId(), 2);
	}
	

	@Test
	public void SaveBoardTest() {
		Jugador jugador = new Jugador();
		jugadorService.save(jugador);
		int count = jugadorService.jugadorCount();
		assertEquals(count, 3);

	}

	

	

}