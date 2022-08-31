package org.springframework.samples.petclinic.game;

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
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.persona.Persona;
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTests {


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

	private Game game;

	private Game game2;
	
	private Game game3;

	private Persona persona1;
	
	private Persona persona2;

	private Jugador jugador1;

	private Jugador jugador2;
	
	
	

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	
		game3 = new Game();
		game3.setId(5);
		game3.setPointsBlack(2);
		game3.setPointsRed(2);
		game3.setTurno(2);
		
	
		game2 = new Game();
		game2.setId(4);
		game2.setPointsBlack(2);
		game2.setPointsRed(2);
		game2.setTurno(1);

		Board board2 = new Board();
		
		game3.setBoard(board2);
		game2.setBoard(board2);

		game = new Game();
		game.setId(3);
		game.setPointsBlack(2);
		game.setPointsRed(3);
		game.setTurno(40);
		game.setToken("abc-abc");
		
		

		Board board = new Board();

		game.setBoard(board);

		persona1= new Persona();
		User u = new User();
		u.setUsername("persona1");
		u.setPassword("personi");
		persona1.setId(4);
		persona1.setFirstName("George");
		persona1.setLastName("Franklin");
		persona1.setUser(u);
		
		persona2= new Persona();
		User u2 = new User();
		u2.setUsername("persona2");
		u.setPassword("personi");
		persona2.setId(6);
		persona1.setFirstName("Hola");
		persona1.setLastName("Hola");
		persona1.setUser(u2);

		jugador1 = new Jugador();
		jugador1.setColor("red");
		jugador2 = new Jugador();
		jugador2.setColor("black");
		
		jugador1.setPersona(persona1);
		jugador2.setPersona(persona2);
		
		persona1.setJugadores(List.of(jugador1));
		persona2.setJugadores(List.of(jugador2));
		
		jugador1.setGame(game3);
		jugador2.setGame(game3);

		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		
		List<Persona > personas = new ArrayList<>();
		personas.add(persona1);
		personas.add(persona2);
		
		game3.setJugadores(jugadores);
		game.setJugadores(jugadores);

		given(this.gameService.findId(3)).willReturn(game);
		given(this.gameService.findId(4)).willReturn(game2);
		given(this.gameService.findId(5)).willReturn(game3);
		given(this.personaService.getUserByUserName("persona1")).willReturn(u);
		given(this.personaService.getUserByUserName("persona2")).willReturn(u2);
		given(this.personaService.getPersonaByUser(u)).willReturn(persona1);
		given(this.personaService.getPersonaByUser(u2)).willReturn(persona2);
		given(this.personaService.getPersonaByUserName("persona")).willReturn(persona1);
		given(this.personaService.getPersonaByUserName("persona2")).willReturn(persona2);
		
		
		given(this.gameService.findGameByToken("abc-abc")).willReturn(game);

	}

	@WithMockUser(authorities = "admin")
	@Test
	void testGamesList() throws Exception {
		mockMvc.perform(get("/games/mostrarpartidas"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("games"))
			.andExpect(view().name("games/listGames"));
	}
	
	@WithMockUser(authorities = "admin")
	@Test
	void testGamesListCurrent() throws Exception {
		mockMvc.perform(get("/games/mostrarpartidasencurso"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("games"))
			.andExpect(view().name("games/listGames"));
	}
	
	@WithMockUser(value = "persona")
	@Test
	void testShowGame() throws Exception {
		mockMvc.perform(get("/games/{gameId}", 4))
		.andExpect(status().isOk())
		.andExpect(view().name("games/showGame"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testShowGameEnd() throws Exception {
		mockMvc.perform(get("/games/{gameId}", 3))
		.andExpect(status().isOk())
		.andExpect(view().name("games/endGame"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testPlayGameFin() throws Exception {
		mockMvc.perform(get("/games/play/{gameId}", 3))
		.andExpect(status().isOk())
		.andExpect(view().name("games/endGame"));
	}
	
	@WithMockUser(value = "persona")
	@Test
	void testPlayGamePhase() throws Exception {
		mockMvc.perform(get("/games/play/{gameId}", 5))
		.andExpect(view().name("games/playGame"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testPlayGameJuego() throws Exception {
		mockMvc.perform(get("/games/play/{gameId}", 5))
		.andExpect(status().isOk())
		.andExpect(view().name("games/playGame"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testCreateGame() throws Exception {
		mockMvc.perform(get("/games/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("game"))
		.andExpect(view().name("games/createGame"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testCreateGameFriend() throws Exception {
		mockMvc.perform(get("/games/newGame"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("game"))
		.andExpect(view().name("games/createGameFriend"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testSaveJuegoAmigo() throws Exception {
		mockMvc.perform(get("/games/saveFriend/{token}", "abc-abc"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/games/play/null"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testJoinGame() throws Exception {
		mockMvc.perform(get("/games/join"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("game"))
		.andExpect(view().name("games/joinGameFriend"));

	}

	@WithMockUser(value = "persona")
	@Test
	void testEditGame() throws Exception {
		mockMvc.perform(get("/games/edit/{gameId}", 3))
				.andExpect(status().isOk())
				.andExpect(model().attribute("game", hasProperty("id", is(3))))
				.andExpect(view().name("games/editGame"));

	}
	
	@WithMockUser(value = "persona")
	@Test
	void testSaveGame() throws Exception {
		mockMvc.perform(post("/games/save")
				.with(csrf()))
				.andExpect(view().name("redirect:/games/play/null"));

	}

	@WithMockUser(value = "persona")
	@Test
	void testSaveToken() throws Exception {
		mockMvc.perform(post("/games/saveToken")
				.with(csrf()))
				.andExpect(view().name("games/createGame"));

	}

	@WithMockUser(value = "persona")
	@Test
	void testProcessUpdateGameForm() throws Exception {
		mockMvc.perform(
				post("/games/edit/{gameId}", 3)
				.with(csrf())
				.param("points_black", "2").param("points_red", "2"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/games"));
	}
}
