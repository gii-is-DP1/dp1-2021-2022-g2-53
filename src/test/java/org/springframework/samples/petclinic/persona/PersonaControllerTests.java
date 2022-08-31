package org.springframework.samples.petclinic.persona;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.BoardService;

import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.PieceService;
import org.springframework.samples.petclinic.jugador.JugadorService;

import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
@WebMvcTest(value = PersonaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class PersonaControllerTests {
	 private static final String FORM = "personas/createOrUpdatePersonaForm";

		@MockBean
		private GameService gameService;

		@MockBean
		private BoardService boardService;

		@MockBean
		private PieceService pieceService;

		@MockBean
		private JugadorService jugadorService;

		@MockBean
		private PersonaService personaService;
		@MockBean
		private UserService userService;
		@MockBean
		private AuthoritiesService authoritiesService;


		@Autowired
		private MockMvc mockMvc;
		
		private Persona persona;
		private Persona persona2;

		@BeforeEach
		void setUp() {
			persona = new Persona();
			User u= new User();
			u.setUsername("person3");
			u.setPassword("personi");
			persona.setId(4);
			persona.setFirstName("George");
			persona.setLastName("Franklin");
			persona.setUser(u);
			
			
			List<Persona> result =  new ArrayList<Persona>();
			result.add(persona);
			result.add(persona2);
		
			
			given(this.personaService.getPersonaByUserName("person3")).willReturn(persona);
			given(this.personaService.findId(4)).willReturn(persona);

			
		}
		

		
		@WithMockUser(value = "persona")
		@Test
		void testGamePersonaLista() throws Exception {
			mockMvc.perform(get("/personas"))
			.andExpect(status().isOk())
			.andExpect(view().name("personas/partidaspersona"));
		}
		
		@WithMockUser(value = "persona")
		@Test
		void testinitCreationForm() throws Exception {
			mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("persona"))
			.andExpect(view().name(FORM));
		}
		
		@WithMockUser(value = "persona")
		@Test
		void testProcessCreationErrorForm() throws Exception {
			mockMvc.perform(post("/register")
			
			.param("firstName","George")
			.with(csrf())
			.param("lastName", "Franklin")
			.param("username", "person3")
			.param("password", "personi"))
			.andExpect(view().name("redirect:/"));
		}
		
		@WithMockUser(value = "persona")
		@Test
		void testInitEditPersona() throws Exception {
			mockMvc.perform(get("/personas/edit/{personaId}",4))
					.andExpect(status().isOk())
					.andExpect(model().attribute("persona", hasProperty("id", is(4))))
					.andExpect(model().attribute("persona", hasProperty("firstName", is("George"))))
					.andExpect(model().attribute("persona", hasProperty("lastName", is("Franklin"))))
					.andExpect(view().name(FORM));
				
			
		}
		
		@WithMockUser(value = "persona")
		@Test
		void testProccesEditPersona() throws Exception {
			mockMvc.perform(post("/personas/edit/{personaId}",4)
					.with(csrf())
					.param("firstName","George")
					.param("lastName", "Franklin")
					.param("username", "person3")
					.param("password", "personi"))
					.andExpect(view().name("redirect:/welcome"));

		}
		
		
		
		@WithMockUser(value = "person3")
		@Test
		void testInitEditPersonaPerfil() throws Exception {
			mockMvc.perform(get("/personas/editperfil/{personaId}",4))
					.andExpect(status().isOk())
					.andExpect(model().attribute("persona", hasProperty("firstName", is("George"))))
					.andExpect(model().attribute("persona", hasProperty("lastName", is("Franklin"))))
					.andExpect(view().name(FORM));
				
			
		}
		
		
		@WithMockUser(value = "persona")
		@Test
		void testProccesEditPersonaPerfil() throws Exception {
			mockMvc.perform(post("/personas/editperfil/{personaId}",4)
					.with(csrf())
					.param("firstName","George")
					.param("lastName", "Franklin")
					.param("username", "person3")
					.param("password", "personi"))
					.andExpect(view().name("redirect:/logout"));

		}
		
		
		
		
}
