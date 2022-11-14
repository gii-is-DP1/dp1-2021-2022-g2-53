package org.springframework.samples.petris.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petris.amigos.InvitacionAmistad;
import org.springframework.samples.petris.amigos.InvitacionAmistadRepository;
import org.springframework.samples.petris.amigos.InvitacionAmistadService;
import org.springframework.samples.petris.user.User;
import org.springframework.samples.petris.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AmigosServiceTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InvitacionAmistadService invitacionAmistadService;
	
	@Autowired
	private InvitacionAmistadRepository invitacionAmistadRepository;
	
	@Test
	public void deberiaGuardarInvitacion() {
		InvitacionAmistad invitacion = new InvitacionAmistad();
		invitacion.setId(1);
		User user1= userService.findUser("person8");
		User user2= userService.findUser("person9");
		invitacion.setUser1(user1);
		invitacion.setUser2(user2);
		this.invitacionAmistadService.guardarInvitacion(invitacion);
		
		// Comprobamos que se haya guardado con id = 1
		Assertions.assertTrue(invitacionAmistadService.findById(1).getId() == 1);
	}
	
	@Test
	public void NoDeberiaGuardarInvitacion() {
		InvitacionAmistad invitacion = new InvitacionAmistad();
		invitacion.setId(2);
		User user1= userService.findUser("person8");
		User user2= userService.findUser("person9");
		invitacion.setUser1(user1);
		invitacion.setUser2(user2);
		String r = this.invitacionAmistadService.guardarInvitacion(invitacion);
		// Comprobamos que redirige a welcome sin guardar la invitacion
		Assertions.assertTrue(r == "/welcome");
	}
	
	@Test
	public void deberiaGuardarComoAmigos() {
		try {
			this.invitacionAmistadService.guardarAmigos("person8", "person9");
		} catch (DataAccessException e) {
		}
		// Comprobamos que person1 ha adquirido como amigo a person2
		Assertions.assertTrue(userService.findUser("person8").getAmigos().contains(userService.findUser("person9")));
		
	}
	
	@Test
	public void noDeberiaGuardarComoAmigos() {
		try {
			this.invitacionAmistadService.guardarAmigos("person8", "person9");
		} catch (DataAccessException e) {
		}
		// Comprobamos que person1 no se puede incluir a si mismo como amigo
		Assertions.assertTrue(!userService.findUser("person8").getAmigos().contains(userService.findUser("person8")));
		
	}
	
	@Test
	public void deberiaDeclinarInvitacion() {
		InvitacionAmistad invitacion = new InvitacionAmistad();
		invitacion.setId(3);
		User user1= userService.findUser("person8");
		User user2= userService.findUser("person9");
		invitacion.setUser1(user1);
		invitacion.setUser2(user2);
		
		this.invitacionAmistadService.declinarInvitacion(invitacion, user1);
		
		// Comprobamos que person1 no se puede incluir a si mismo como amigo
		Assertions.assertTrue(!userService.findUser("person8").getAmigos().contains(userService.findUser("person8")));
		
	}
	
	@Test
	public void deberiaAceptarInvitacion() {
		User user2= userService.findUser("person9");
		this.invitacionAmistadService.aceptarInvitacion(1, user2);
		Assertions.assertTrue(userService.findUser("person8").getAmigos().contains(userService.findUser("person9")));
	}
	
	@Test
	public void deberiaBorrarUser() {
		User user1= userService.findUser("person8");
		Integer tamanoListaAntesEliminar = invitacionAmistadRepository.findInvitacionByUser1(user1).size();
		this.invitacionAmistadService.borrarUser(user1);
		Integer tamanoListaDespuesEliminar = invitacionAmistadRepository.findInvitacionByUser1(user1).size();
		Assertions.assertTrue(tamanoListaAntesEliminar == tamanoListaDespuesEliminar+1);
	}
	
	@Test
	public void deberiaBorrarUser2() {
		User user2= userService.findUser("person9");
		Integer tamanoListaAntesEliminar = invitacionAmistadRepository.findInvitacionByUser2(user2).size();
		this.invitacionAmistadService.borrarUser(user2);
		Integer tamanoListaDespuesEliminar = invitacionAmistadRepository.findInvitacionByUser2(user2).size();
		Assertions.assertTrue(tamanoListaAntesEliminar == tamanoListaDespuesEliminar+1);
	}

}
