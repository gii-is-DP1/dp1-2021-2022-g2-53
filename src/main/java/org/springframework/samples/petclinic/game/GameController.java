package org.springframework.samples.petclinic.game;


import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping()
	public String listGames(ModelMap modelMap) {
		String view = "games/listGames";
		Iterable<Game> games = gameService.findAll();
		modelMap.addAttribute("games", games);
		return view;
	}
	
	@GetMapping(value="/{gameId}")
	public String showGame(ModelMap modelMap,@PathVariable("gameId") int gameId, HttpServletResponse response) {
		String view="games/showGame";
		response.addHeader("Refresh","1");
		Game game=gameService.findId(gameId);
		modelMap.addAttribute("game",game);
		modelMap.addAttribute("board",game.getBoard());
		modelMap.put("now", new Date());
		return view;
	}
	
	@GetMapping(value="/play/{gameId}")
	public String playGame(ModelMap modelMap,@PathVariable("gameId") int gameId, HttpServletResponse response) {
		String view="games/playGame";
		//response.addHeader("Refresh","1");
		Game game=gameService.findId(gameId);
		modelMap.addAttribute("game",game);
		modelMap.addAttribute("board",game.getBoard());
		modelMap.addAttribute("movement",new Movement());
		modelMap.addAttribute("turnoActual",game.getTurnos().get(game.getTurno()));
		modelMap.put("now", new Date());
		return view;
	}
	
	@PostMapping(value="/play/{gameId}")
	public String processMovementForm(ModelMap modelMap,@PathVariable("gameId") int gameId ,@Valid Movement movement, BindingResult result) {
		if (result.hasErrors()) {
			boolean edit=true;
			modelMap.put("edit", edit);
			return "games/playGame";
		}else {
			Game gameEdited = gameService.findId(gameId);
			String turno = gameEdited.getTurnos().get(gameEdited.getTurno());
			if (turno.equals("red") || turno.equals("black")) {
				movement.setTipo(gameEdited.getTurnos().get(gameEdited.getTurno()));
				gameEdited.getBoard().movePieces(movement);
				gameEdited.setTurno(gameEdited.getTurno()+1);
				gameService.save(gameEdited);
			} else if (turno.equals("binary")) {
				gameService.binary(gameId);
				gameEdited.setTurno(gameEdited.getTurno()+1);
				gameService.save(gameEdited);
			} else if (turno.equals("pollution")) {
				//POSICION 0 SON ROJOS Y 1 SON NEGROS
				gameEdited.setPointsRed(gameEdited.getPointsRed() + gameEdited.getBoard().pollution().get(0));
				gameEdited.setPointsBlack(gameEdited.getPointsBlack() +gameEdited.getBoard().pollution().get(1));
				gameEdited.setTurno(gameEdited.getTurno()+1);
				gameService.save(gameEdited);
			}
			
			return "redirect:/games/play/{gameId}";
		}
		
	}
	
	
	/////////////////////////////////////////////
	
	@GetMapping(value="/edit/{gameId}")
	public String editGame(ModelMap modelMap,@PathVariable("gameId") int gameId) {
		String view="games/editGame";
		Game game=gameService.findId(gameId);
		boolean edit=true;
		modelMap.addAttribute("game",game);
		modelMap.addAttribute("edit",edit);
		return view;
	}
	
	@PostMapping(value="/edit/{gameId}")
	public String processUpdateGameForm(ModelMap modelMap,@PathVariable("gameId") int gameId , @Valid Game game , BindingResult result) {
		if (result.hasErrors()) {
			boolean edit=true;
			modelMap.put("edit", edit);
			return "games/editGame";
		}else {
			Game gameEdited = gameService.findId(gameId);
			gameEdited.setPointsBlack(game.getPointsBlack());
			gameEdited.setPointsRed(game.getPointsRed());
			gameService.save(gameEdited);
			return "redirect:/games";
		}
		
	}
	
	
	@GetMapping(value="/delete/{gameId}")
	public String deleteGame(ModelMap modelMap,@PathVariable("gameId") int gameId) {
		String view="games/listGames"; 
		Optional<Game> game= Optional.of(gameService.findId(gameId));
		if(game.isPresent()) {
			gameService.delete(game.get());
			modelMap.addAttribute("message","evento borrado");
		}
		else {
			modelMap.addAttribute("message","evento no borrado");
			
		}
			return view;
		
		
	}
	@GetMapping(path="/new")
	public String creategame(ModelMap modelMap) {
		String view="games/createGame";
		modelMap.addAttribute("game",new Game());
		return view;
	
			
		}
	@PostMapping(path="/save")
	public String salvarjuego(ModelMap modelMap, @Valid Game game , BindingResult result) {
		String view="games/listGames"; 
		if (result.hasErrors()) {
			modelMap.addAttribute("game", game);
			return "games/createGame";
		}else {
			gameService.save(game);
			modelMap.addAttribute("message","evento salvado");
			return view;
		}
		
	}	
	
	
	
}
