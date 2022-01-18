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
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTests {

	private static final int TEST_GAME_ID = 1;
	private static final String TEST_TOKEN = "abc-abc";
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

	@BeforeEach
	void setUp() {
		game = new Game();
		game.setId(1);
		game.setPointsBlack(2);
		game.setPointsRed(2);

		Board board = new Board();

		game.setBoard(board);

		given(this.gameService.findId(1)).willReturn(game);
	}

	@WithMockUser(value = "persona")
	@Test
	void testGamesList() throws Exception {
		mockMvc.perform(get("/games"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("games"))
			.andExpect(view().name("games/listGames"));
	}

	@WithMockUser(value = "persona")
	@Test
	void testShowGame() throws Exception {
		mockMvc.perform(get("/games/{gameId}", 1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("game", hasProperty("pointsBlack", is(2))))
			.andExpect(model().attribute("game", hasProperty("pointsRed", is(2))))
			.andExpect(view().name("games/showGame"));
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
	void testEditGame() throws Exception {
		mockMvc.perform(get("/games/edit/{gameId}", TEST_GAME_ID))
				.andExpect(status().isOk())
				.andExpect(model().attribute("game", hasProperty("id", is(1))))
				.andExpect(view().name("games/editGame"));

	}
	
//	@WithMockUser(value = "persona")
//	@Test
//	void testSalvarJuegoAmigo() throws Exception {
//		mockMvc.perform(get("/games/saveFriend/{token}", TEST_TOKEN))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeExists("game"))
//				.andExpect(view().name("games/createGame"));

//	}
	
	

//	@WithMockUser(value = "persona")
//	@Test
//	void testProcessUpdateGameForm() throws Exception {
//		mockMvc.perform(post("/games/edit/{gameId}", TEST_GAME_ID)
//				.with(csrf())
//				.param("points_black",2)
//				.param("points_red",2)
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/games")));
//	}
	}


