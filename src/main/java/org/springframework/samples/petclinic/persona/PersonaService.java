package org.springframework.samples.petclinic.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameRepository;

public class PersonaService {
	
	
	@Autowired
	private PersonaRepository personaRepo;

}
