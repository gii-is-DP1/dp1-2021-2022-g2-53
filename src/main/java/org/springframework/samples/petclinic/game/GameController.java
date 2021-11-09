package org.springframework.samples.petclinic.game;


import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

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
		//response.addHeader("Refresh","1");
		Game game=gameService.findId(gameId);
		modelMap.addAttribute("game",game);
		modelMap.addAttribute("board",game.getBoard());
		modelMap.put("now", new Date());
		return view;
	}
	
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
