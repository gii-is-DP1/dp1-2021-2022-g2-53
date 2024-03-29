package org.springframework.samples.petris.game;

import javax.validation.Valid;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.jugador.JugadorService;
import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PieceService pieceService;
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private GameRepository repository;

	private static final Integer numero_maximo_puntos_para_perder = 9;

	@GetMapping(value = "/mostrarpartidas")
	public String listGames(ModelMap modelMap) {
		String view = "games/listGames";

		Collection<Game> res;
		res = this.repository.findFinishedGames();
		modelMap.addAttribute("games", res);
		return view;
	}

	@GetMapping(value = "/mostrarpartidasencurso")
	public String listCurrentGames(ModelMap modelMap) {
		String view = "games/listGames";

		Collection<Game> res;
		res = this.repository.findCurrentGames();
		modelMap.addAttribute("games", res);
		return view;
	}

	@GetMapping(value = "/herramientasAdmin")
	public String herramientasAdmin() {
		return "herramientasAdmin";

	}

	@GetMapping(value = "/{gameId}")
	public String showGame(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {
		String view = "games/showGame";
		String viewFin = "games/endGame";
		Game game = gameService.findId(gameId);
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);

//		if (game.getJugadores().get(0).getPersona() != persona && game.getJugadores().get(1).getPersona() != persona ) {
//				
//			return "redirect:/errorIntentoBorrado";
//		}
		response.addHeader("Refresh", "1");
		if (game.getTurnos().get(game.getTurno()).equals("fin")) {
			modelMap.addAttribute("winner", game.getGanador());
			return viewFin;
		}

		modelMap.addAttribute("game", game);
		modelMap.addAttribute("board", game.getBoard());
		modelMap.put("now", new Date());
		return view;
	}

	@GetMapping(value = "/play/{gameId}")
	public String playGame(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {

		String view = "games/playGame";
		String viewFin = "games/endGame";
		Game game = gameService.findId(gameId);
		modelMap.addAttribute("game", game);
		modelMap.addAttribute("board", game.getBoard());
		modelMap.addAttribute("movement", new Movement());
		modelMap.addAttribute("turnoActual", game.getTurnos().get(game.getTurno()));

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		String jugador = persona.getJugadores().get(persona.getJugadores().size() - 1).getColor();
		
		  if (game.getJugadores().get(0).getPersona() != persona &&
		  game.getJugadores().get(1).getPersona() != persona ) {
		  
		  return "redirect:/errorSuplantacion"; }
		 

		if (game.getJugadores().size() > 1) {
			if (game.getJugadores().get(0).getPersona().equals(game.getJugadores().get(1).getPersona())) {
				return "redirect:/errorMismoUsuarioEnPartida";
			}
		}

		modelMap.put("now", new Date());
		if (game.getTurnos().get(game.getTurno()).equals("fin")
				|| game.getPointsBlack() >= numero_maximo_puntos_para_perder
				|| game.getPointsBlack() >= numero_maximo_puntos_para_perder) {
			modelMap.addAttribute("winner", game.getGanador());
			return viewFin;
		}
		if (jugador.equals(game.getTurnos().get(game.getTurno()))) {

			return view;

		} else if (!jugador.equals(game.getTurnos().get(game.getTurno()))
				&& !game.getTurnos().get(game.getTurno()).equals("binary")
				&& !game.getTurnos().get(game.getTurno()).equals("pollution")) {
			response.addHeader("Refresh", "1");
			modelMap.addAttribute("message3", "Espera a que tu oponente realice su movimiento");
			return view;
		}
		response.addHeader("Refresh", "2");
		modelMap.addAttribute("message4",
				"¡Entrefase, respira profundo y pulsa para continuar o espera a que tu rival lo pulse!");
		return view;

	}

	@PostMapping(value = "/play/{gameId}")
	public String processMovementForm(ModelMap modelMap, @PathVariable("gameId") int gameId, @Valid Movement movement,
			BindingResult result, HttpServletResponse response) throws MoveInvalidException {
		Game game = gameService.findId(gameId);
		if (result.hasErrors()) {
			modelMap.put("board", gameService.findId(gameId).getBoard());
			boolean edit = true;
			modelMap.put("edit", edit);
			return "games/playGame";
		} else {

			gameService.phases(game, movement, result);

			if (result.hasErrors()) {
				modelMap.put("board", gameService.findId(gameId).getBoard());
				if (!result.getFieldErrors("destinyPosition").isEmpty()) {
					modelMap.put("error", result.getFieldError("destinyPosition").getDefaultMessage());
				} else if (!result.getFieldErrors("initialPosition").isEmpty()) {
					modelMap.put("error", result.getFieldError("initialPosition").getDefaultMessage());
				}
				return "games/playGame";
			} else {
				return "redirect:/games/play/{gameId}";
			}
		}

	}

	@GetMapping(path = "/new")
	public String creategame(ModelMap modelMap) {
		Game game = new Game();
		String view = "games/createGame";
		modelMap.addAttribute("game", game);
		return view;

	}

	@GetMapping(path = "/newGame")
	public String createGameFriend(ModelMap modelMap) {
		String view = "games/createGameFriend";
		Game game = new Game();
		String token = game.generarToken();
		game.setToken(token);
		modelMap.addAttribute("game", game);
		return view;

	}

	@GetMapping(path = "/saveFriend/{token}")
	public String salvarjuegoamigo(ModelMap modelMap, Game game, @PathVariable("token") String token,
			BindingResult result) {
		if (result.hasErrors()) {
			modelMap.addAttribute("game", game);
			return "games/createGame";
		} else {

			UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ud.getUsername();
			Persona persona = personaService.getPersonaByUserName(username);
			Board board = new Board();
			Piece pieceb = new Piece();
			Piece piecer = new Piece();
			Jugador jugadorRed = new Jugador();

			boardService.save(board);
			pieceb.setColor("black");
			pieceb.setPosition(3);
			piecer.setColor("red");
			piecer.setPosition(5);
			piecer.setBoard(board);
			pieceb.setBoard(board);

			game.setBoard(board);
			pieceService.save(pieceb);
			pieceService.save(piecer);

			game.setTurno(0);
			game.setPointsRed(0);
			game.setPointsBlack(0);
			game.setToken(token);

			gameService.save(game);

			jugadorRed.setColor("red");
			jugadorRed.setGame(game);
			jugadorRed.setPersona(persona);
			jugadorService.save(jugadorRed);
			List<Jugador> jugadores = new ArrayList<Jugador>();
			jugadores.add(jugadorRed);
			game.setJugadores(jugadores);
			gameService.save(game);

			modelMap.addAttribute("message", "Partida creada");
			return "redirect:/games/play/" + game.getId();

		}

	}

	@GetMapping(path = "/join")
	public String joinGame(ModelMap modelMap) {
		String view = "games/joinGameFriend";
		Game game = new Game();
		modelMap.addAttribute("game", game);
		return view;
	}

	@PostMapping(path = "/saveToken")
	public String saveToken(ModelMap modelMap, Game game, BindingResult result) {
		String token = game.getToken();
		gameService.findAll();
		Game newGame = gameService.findGameByToken(token);
		if (result.hasErrors()) {
			modelMap.addAttribute("game", game);
			return "games/createGame";
		} else if (newGame != null && newGame.getId() != game.getId()) {
			UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ud.getUsername();
			Persona persona = personaService.getPersonaByUserName(username);
			Jugador jugadorBlack = new Jugador();
			jugadorBlack.setColor("black");
			jugadorBlack.setGame(newGame);
			jugadorBlack.setPersona(persona);
			List<Jugador> aux = newGame.getJugadores();
			aux.add(jugadorBlack);
			newGame.setJugadores(aux);
			gameService.save(newGame);
			return "redirect:/games/play/" + newGame.getId();
		} else {
			return "games/createGame";
		}
	}

	@PostMapping(path = "/save")
	public String salvarjuego(ModelMap modelMap, Game game, BindingResult result) {
		String token = (String) modelMap.getAttribute("token");
		if (result.hasErrors()) {
			modelMap.addAttribute("game", game);
			return "games/createGame";
		} else {
			Board board = new Board();
			Piece pieceb = new Piece();
			Piece piecer = new Piece();

			boardService.save(board);
			pieceb.setColor("black");
			pieceb.setPosition(3);
			piecer.setColor("red");
			piecer.setPosition(5);
			piecer.setBoard(board);
			pieceb.setBoard(board);

			game.setBoard(board);
			pieceService.save(pieceb);
			pieceService.save(piecer);
			game.setTurno(0);
			game.setPointsRed(0);
			game.setPointsBlack(0);
			gameService.save(game);
			modelMap.addAttribute("message", "Partida creada");

			return "redirect:/games/play/" + game.getId();
		}

	}

	@GetMapping(value = "/edit/{gameId}")
	public String editGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
		String view = "games/editGame";
		Game game = gameService.findId(gameId);
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		if (game.getJugadores().get(0).getPersona() != persona || game.getJugadores().get(1).getPersona() != persona) {

			return "redirect:/errorIntentoBorrado";
		}
		boolean edit = true;
		modelMap.addAttribute("game", game);
		modelMap.addAttribute("edit", edit);
		return view;
	}

	@PostMapping(value = "/edit/{gameId}")
	public String processUpdateGameForm(ModelMap modelMap, @PathVariable("gameId") int gameId, @Valid Game game,
			BindingResult result) {

		if (result.hasErrors()) {
			boolean edit = true;
			modelMap.put("edit", edit);
			return "games/editGame";
		} else {
			Game gameEdited = gameService.findId(gameId);
			gameEdited.setPointsBlack(game.getPointsBlack());
			gameEdited.setPointsRed(game.getPointsRed());
			gameService.save(gameEdited);
			return "redirect:/games";
		}

	}

	@GetMapping(value = "/delete/{gameId}")
	public String deleteGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
		String view = "games/listGames";
		Game game = gameService.findId(gameId);
		Board board = game.getBoard();

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		if (game.getJugadores().get(0).getPersona() != persona || game.getJugadores().get(1).getPersona() != persona) {

			return "redirect:/errorIntentoBorrado";
		}

		if (game.getId().equals(gameId)) {

			gameService.delete(game);
			boardService.delete(board);
			modelMap.addAttribute("message", "evento borrado");
		} else {
			modelMap.addAttribute("message", "evento no borrado");

		}
		return view;

	}

}
