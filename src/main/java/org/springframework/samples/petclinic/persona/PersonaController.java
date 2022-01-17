package org.springframework.samples.petclinic.persona;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PersonaController {
	
	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaService personaService;
	
	private static final String VIEWS_PERSONA_CREATE_OR_UPDATE_FORM = "personas/createOrUpdatepersonaForm";
	
	
	@GetMapping(value = "/personas")
	public String showGame(ModelMap modelMap, HttpServletResponse response) {
		String view = "personas/partidaspersona";
		
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username); 
		List<Game> lista = jugadorService.historialgame(persona);
		modelMap.addAttribute("games", lista.iterator());
		return view;
	}
	
	@GetMapping(value = "/personas/new")
	public String initCreationForm(Map<String, Object> model) {
		Persona persona = new Persona();
		model.put("persona", persona);
		return VIEWS_PERSONA_CREATE_OR_UPDATE_FORM;
	}
	
	
	@GetMapping(value = "/personas/{personaId}/edit")
	public String initUpdateOwnerForm(@PathVariable("personaId") int personaId, Model model) {
		
		Persona persona = this.personaService.findId(personaId);
		model.addAttribute(persona);
		return VIEWS_PERSONA_CREATE_OR_UPDATE_FORM;
	}

	
	
	

}
