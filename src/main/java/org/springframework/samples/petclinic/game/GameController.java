package org.springframework.samples.petclinic.game;

import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PieceService pieceService;

	@GetMapping()
	public String listGames(ModelMap modelMap) {
		String view = "games/listGames";
		Iterable<Game> games = gameService.findAll();
		modelMap.addAttribute("games", games);
		return view;
	}

	@GetMapping(value = "/{gameId}")
	public String showGame(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {
		String view = "games/showGame";
		response.addHeader("Refresh", "1");
		Game game = gameService.findId(gameId);
		modelMap.addAttribute("game", game);
		modelMap.addAttribute("board", game.getBoard());
		modelMap.put("now", new Date());
		return view;
	}

	@GetMapping(value = "/play/{gameId}")
	public String playGame(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {
		String view = "games/playGame";
		// response.addHeader("Refresh","1");
		Game game = gameService.findId(gameId);
		modelMap.addAttribute("game", game);
		modelMap.addAttribute("board", game.getBoard());
		modelMap.addAttribute("movement", new Movement());
		modelMap.addAttribute("turnoActual", game.getTurnos().get(game.getTurno()));
		modelMap.put("now", new Date());
		return view;
	}

	@PostMapping(value = "/play/{gameId}")
	public String processMovementForm(ModelMap modelMap, @PathVariable("gameId") int gameId, @Valid Movement movement,
			BindingResult result) throws MoveInvalidException {
		if (result.hasErrors()) {
			modelMap.put("board", gameService.findId(gameId).getBoard());
			boolean edit = true;
			modelMap.put("edit", edit);
			return "games/playGame";
		} else {
			gameService.phases(gameId, movement, result);
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

		String view = "games/createGame";
		modelMap.addAttribute("game", new Game());
		return view;

	}

	@PostMapping(path = "/save")
	public String salvarjuego(ModelMap modelMap, @Valid Game game, BindingResult result) {
		String view = "games/listGames";
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
			board.addgame(game);
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

	/////////////////////////////////////////////

	@GetMapping(value = "/edit/{gameId}")
	public String editGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
		String view = "games/editGame";
		Game game = gameService.findId(gameId);
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
		Optional<Game> game = Optional.of(gameService.findId(gameId));
		Board board = game.get().getBoard();
		if (game.isPresent()) {

			gameService.delete(game.get());
			boardService.delete(board);
			modelMap.addAttribute("message", "evento borrado");
		} else {
			modelMap.addAttribute("message", "evento no borrado");

		}
		return view;

	}

}
