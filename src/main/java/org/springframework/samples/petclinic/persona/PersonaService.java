package org.springframework.samples.petclinic.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
	
	
	@Autowired
	private PersonaRepository personaRepo;
	
	public Persona getPersonaByUserName(String username) {
		return personaRepo.findPersonaByUserName(username);
	}

}
