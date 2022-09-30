package org.springframework.samples.petris.jugador;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class JugadorController {

	@Autowired
	JugadorService jugadorService;

}
