package org.springframework.samples.petclinic.jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JugadorController {
	@Autowired
	JugadorService jugadorService;
	
	
	
	@GetMapping(value = "/jugadores")
	public String listGames(ModelMap modelMap) {
		String view = "jugadores/listJugadores";
		Iterable<String> usernames = jugadorService.getJugadorusername();
		modelMap.addAttribute("usernames", usernames);
		return view;
	}

}
