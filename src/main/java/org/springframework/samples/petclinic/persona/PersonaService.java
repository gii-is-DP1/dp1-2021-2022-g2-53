package org.springframework.samples.petclinic.persona;




import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {
	
	
	@Autowired
	private PersonaRepository personaRepo;

	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;

	@Transactional
	public User getUserByUserName(String username) {
		return userService.findUser(username).get();
	}

	@Transactional
	public Persona getPersonaByUser(User user){
		return personaRepo.getPersonaByUser(user);
	}

	@Transactional
	public Persona getPersonaByUserName(String username) {
		return this.getPersonaByUser(getUserByUserName(username));
	}
	
	@Transactional
	public Persona findId(int id) {
		return personaRepo.findById(id).get();

	}
	
	@Transactional
	public Iterable<Persona> findAll() {
		return personaRepo.findAll();
	}
	
	@Transactional
	public void delete(Persona persona) {
		personaRepo.delete(persona);
		

	}

	@Transactional
	public void savePersona(Persona p) throws DataAccessException {
		//creating persona
		personaRepo.save(p);		
		//creating user
		userService.saveUser(p.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(p.getUser().getUsername(), "persona");
	}


}
