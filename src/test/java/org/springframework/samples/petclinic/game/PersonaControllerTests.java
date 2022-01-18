package org.springframework.samples.petclinic.game;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.persona.Persona;
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
public class PersonaControllerTests {


		private static final int TEST_PERSONA_ID = 1;
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

		private Game game;

		@Autowired
		private MockMvc mockMvc;
		
		private Persona prueba;

		@BeforeEach
		void setUp() {
			prueba = new Persona();
			prueba.setId(TEST_PERSONA_ID);
			prueba.setFirstName("George");
			prueba.setLastName("Franklin");
			prueba.setUserName("prueba1");

			
		}
		
//		@WithMockUser(value = "persona")
//		@Test
//		void testGamePerson() throws Exception {
//			mockMvc.perform(get("/personas"))
//			.andExpect(model().attribute("prueba", hasProperty("lastName", is("Franklin"))))
//			.andExpect(model().attribute("prueba", hasProperty("firstName", is("George"))))
//			.andExpect(view().name("personas/partidaspersona"));
//		}
}
