package org.springframework.samples.petclinic.persona;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.user.User;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PersonaServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private PersonaRepository personaRepo;
	@Autowired
	private PersonaService personaService;

	@Test
	public void getUserByUserNameTest() {
		User u = userService.findUser("person1").get();
		Assertions.assertThat(u.getUsername()).isEqualTo("person1");

	}

	@Test
	public void getPersonaByUserTest() {
		User u = userService.findUser("person1").get();
		Persona p= personaService.getPersonaByUser(u);
		Assertions.assertThat(u).isEqualTo(p.getUser());
	}
	
	@Test
	public void getPersonaByUserNameTest() {
		Persona p = personaService.getPersonaByUserName("person1");
		Assertions.assertThat(p.getUser().getUsername()).isEqualTo("person1");
	}
	
	@Test
	public void findIdTest() {
		int id= 1;
		Persona p= personaRepo.findById(1).get();
		assertEquals(p.getId(), id);

	}
	
//	@Test
//	public void findAllTest() {
//		Persona p = new Persona();
//		User u= userService.findUser("person3").get();
//		p.setFirstName("jj");
//		p.setLastName("hh");
//		p.setUser(u);
//		personaService.savePersona(p);
//		Iterable<Persona> it= personaService.findAll();
//		List<Persona> result =  StreamSupport.stream(it.spliterator(), false)
//				    .collect(Collectors.toList());
//		Assertions.assertThat(result.contains(p));
//		
//
//	}
	
	@Test
	public void deleteTest() {
		Persona p = personaService.findId(3);
		personaService.delete(p);
		int count =(int) personaRepo.count();
		assertEquals(count,11);


	}
	
	@Test
	public void saveTest() {
		Persona p = new Persona();
		User u= userService.findUser("person3").get();
		p.setFirstName("jj");
		p.setLastName("hh");
		p.setUser(u);
		int count1 =(int) personaRepo.count();
		personaService.savePersona(p);
		int count2 =(int) personaRepo.count();
		assertEquals(count2,count1+1);


	}

	
	

}
