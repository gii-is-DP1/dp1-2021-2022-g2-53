package org.springframework.samples.petclinic.game;


import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

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
		// response.addHeader("Refresh","1");
		Game game=gameService.findId(gameId);
		modelMap.addAttribute("game",game);
		modelMap.addAttribute("board",game.getBoard());
		modelMap.addAttribute("movement",new Movement());
		modelMap.addAttribute("turnoActual",game.getTurnos().get(game.getTurno()));
		modelMap.put("now", new Date());
		return view;
	}
	
	@PostMapping(value="/play/{gameId}")
	public String processMovementForm(ModelMap modelMap, @PathVariable("gameId") int gameId ,@Valid Movement movement, BindingResult result) throws MoveInvalidException {
		if (result.hasErrors()) {
			boolean edit=true;
			modelMap.put("edit", edit);
			return "games/playGame";
		}else {
			gameService.phases(gameId, movement, result);
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
	
	
}
