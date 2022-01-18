package org.springframework.samples.petclinic.persona;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {
	
	
	@Autowired
	private PersonaRepository personaRepo;
	
	public Persona getPersonaByUserName(String username) {
		return personaRepo.findPersonaByUserName(username);
	}
	
	@Transactional
	public Persona findId(int id) {
		return personaRepo.findById(id).get();

	}
	
	@Transactional
	public Iterable<Persona> findAll() {
		return personaRepo.findAll();
	}

}
