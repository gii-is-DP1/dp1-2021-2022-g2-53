package org.springframework.samples.petris.game;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petris.amigos.InvitacionAmistad;
import org.springframework.samples.petris.amigos.InvitacionAmistadController;
import org.springframework.samples.petris.amigos.InvitacionAmistadRepository;
import org.springframework.samples.petris.amigos.InvitacionAmistadService;
import org.springframework.samples.petris.configuration.SecurityConfiguration;
import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaService;
import org.springframework.samples.petris.user.User;
import org.springframework.samples.petris.user.UserService;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.jugador.JugadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=InvitacionAmistadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AmigosControllerTests {
	
	private static final int TEST_INVITACION_AMISTAD = 1;
	private static final String TEST_USERNAME_1 = "persona1";
	private static final String TEST_USERNAME_2 = "user";
	private static final String TEST_USERNAME_3 = "persona2";

	
	
//	@Autowired
//	private InvitacionAmistadController invitacionAmistadController;
	
	@MockBean
	private InvitacionAmistadRepository invitacionAmistadRepository;
	
	@MockBean
	private InvitacionAmistadService amistadService;
	
	@MockBean
	private UserService userService;
	
	
	
	private Persona persona1;
	
	private Persona persona2;
	
	private Persona persona3;
	
	private User user1;
	
	private User user2;
	
	private User user3;
	
	private InvitacionAmistad invitacion;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		
	invitacion = new InvitacionAmistad();
		
	
	persona1= new Persona();
	List<User> amigos = new ArrayList();
	List<User> amigos2 = new ArrayList();
	user1 = new User();
	user1.setUsername(TEST_USERNAME_1);
	user1.setPassword("personi");
	user1.setAmigos(amigos);
	persona1.setId(4);
	persona1.setFirstName("George");
	persona1.setLastName("Franklin");
	persona1.setUser(user1);
	
	
	persona2= new Persona();
	user2 = new User();
	user2.setUsername(TEST_USERNAME_2);
	user2.setPassword("password");
	persona2.setId(5);
	persona2.setFirstName("Luis");
	persona2.setLastName("Chacon");
	persona2.setUser(user2);
	amigos2.add(user2);
	
	persona3= new Persona();
	user3 = new User();
	user3.setUsername(TEST_USERNAME_3);
	user3.setPassword("personi");
	persona3.setId(6);
	persona3.setFirstName("Juan");
	persona3.setLastName("Mena");
	persona3.setUser(user3);
	user3.setAmigos(amigos2);
	
	
	
	
	invitacion.setId(TEST_INVITACION_AMISTAD);
	invitacion.setUser1(user1);
	invitacion.setUser2(user2);
	
	given(this.userService.findUser("persona2")).willReturn(user3);
	given(this.userService.findUser("persona1")).willReturn(user1);
	given(this.userService.findUser("user")).willReturn(user2);

	given(this.amistadService.findById(TEST_INVITACION_AMISTAD)).willReturn(invitacion);
	given(this.amistadService.aceptarInvitacion(TEST_INVITACION_AMISTAD, user2)).willReturn("redirect:/welcome");
	given(this.amistadService.declinarInvitacion(invitacion, user2)).willReturn("redirect:/welcome");
	given(this.amistadService.guardarInvitacion(invitacion)).willReturn("redirect:/welcome");

	
	

	}

	
	@WithMockUser(authorities = "admin")
	@Test
	void testListaInivtaciones() throws Exception {
		mockMvc.perform(get("/invitacionAmigo"))
			.andExpect(status().isOk())
			.andExpect(view().name("invitaciones/listaInvitaciones"));
	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testAceptar() throws Exception {
		mockMvc.perform(post("/invitacionAmigo/{id}/aceptar",TEST_INVITACION_AMISTAD)
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testRechazar() throws Exception {
		mockMvc.perform(post("/invitacionAmigo/{id}/rechazar",TEST_INVITACION_AMISTAD)
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testGuardarPost() throws Exception {
		mockMvc.perform(post("/invitacionAmigo/{user1}/{user2}/save", user1, user2)
		.with(csrf()))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testGuardarGet() throws Exception {
		mockMvc.perform(get("/invitacionAmigo/{user1}/{user2}/save", TEST_USERNAME_1, TEST_USERNAME_2))
		.andExpect(status().isOk());

	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testGuardarGetError() throws Exception {
		mockMvc.perform(get("/invitacionAmigo/{user1}/{user2}/save", TEST_USERNAME_3, TEST_USERNAME_2))
		.andExpect(model().attributeExists("message"))
		.andExpect(view().name("/error"));
	}

}
