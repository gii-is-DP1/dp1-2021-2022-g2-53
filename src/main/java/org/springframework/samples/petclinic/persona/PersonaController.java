package org.springframework.samples.petclinic.persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.game.Game;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.validation.BindingResult;

@Controller
public class PersonaController {

	@Autowired
	private JugadorService jugadorService;
	@Autowired
	private PersonaService personaService;

	@GetMapping(value = "/personas")
	public String showGamePerson(ModelMap modelMap, HttpServletResponse response) {
		String view = "personas/partidaspersona";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		List<Game> lista = jugadorService.historialgame(persona);
		modelMap.addAttribute("games", lista.iterator());
		return view;
	}

	@GetMapping(value = "/personas/registro/{PageId}")
	public String showGamePerson(ModelMap modelMap, @PathVariable("PageId") int PageId) {
		String view = "personas/listPersonas";
		Pageable pageable = PageRequest.of(PageId, 5);
		Page<Persona> personas = personaService.findAll(pageable);
		modelMap.addAttribute("total_pages", personas.getTotalPages());
		modelMap.addAttribute("page_id", PageId);

		modelMap.addAttribute("personas", personas.toList());
		return view;
	}

	private static final String FORM = "personas/createOrUpdatePersonaForm";

	@Autowired
	public PersonaController(PersonaService personaService, UserService userService,
			AuthoritiesService authoritiesService) {
		this.personaService = personaService;
	}

	@GetMapping("/register")
	public String initCreationForm(Map<String, Object> model) {
		Persona persona = new Persona();
		model.put("persona", persona);
		return FORM;
	}

	@PostMapping("/register")
	public String proccessCreationFOrm(@Valid Persona persona, BindingResult result) {
		if (result.hasErrors()) {
			return FORM;
		} else {
			System.out.println("");
			this.personaService.savePersona(persona);
			return "redirect:/";
		}
	}

	@GetMapping(value = "/personas/edit/{personaId}")
	public String editPersona(ModelMap modelMap, @PathVariable("personaId") int personaid) {
		String view = FORM;
		Persona persona = personaService.findId(personaid);
		boolean edit = true;
		modelMap.addAttribute("persona", persona);
		modelMap.addAttribute("edit", edit);
		return view;
	}

	@PostMapping(value = "/personas/edit/{personaId}")
	public String processUpdatePersonaForm(ModelMap modelMap, @PathVariable("personaId") int personaid,
			@Valid Persona persona,  BindingResult result) {
		if (result.hasErrors()) {
			boolean edit = true;
			modelMap.put("edit", edit);
			return FORM;
		} else {
			Persona personaEdited = personaService.findId(personaid);
			personaEdited.setFirstName(persona.getFirstName());
			personaEdited.setLastName(persona.getLastName());
			personaEdited.setUser(persona.getUser());
			personaService.savePersona(personaEdited);
			return "redirect:/welcome";
		}

	}

	@GetMapping(value = "/personas/delete/{personaId}")
	public String deletePersona(ModelMap modelMap, @PathVariable("personaId") int personaid) {

		Optional<Persona> persona = Optional.of(personaService.findId(personaid));

		if (persona.isPresent()) {
			personaService.delete(persona.get());
			modelMap.addAttribute("message", "persona borrado");
		} else {
			modelMap.addAttribute("message", "persona no borrado");

		}
		return "redirect:/welcome";

	}

	@GetMapping(value = "/personas/seguroview")
	public String showPerson(ModelMap modelMap, HttpServletResponse response) {
		String view = "personas/seguro";

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		List<Persona> ls = new ArrayList<Persona>();
		ls.add(persona);
		modelMap.addAttribute("persona", ls);
		return view;
	}

	@GetMapping(value = "/personas/editperfil/{personaId}")
	public String editPersonaperfil(ModelMap modelMap, @PathVariable("personaId") int personaid) {
		String view = FORM;
		Persona persona = personaService.findId(personaid);
		boolean edit = true;
		modelMap.addAttribute("persona", persona);
		modelMap.addAttribute("edit", edit);
		return view;
	}

	@PostMapping(value = "/personas/editperfil/{personaId}")
	public String processUpdatePersonaperfilForm(ModelMap modelMap, @PathVariable("personaId") int personaid,
			@Valid Persona persona, BindingResult result) {
		if (result.hasErrors()) {
			boolean edit = true;
			modelMap.put("edit", edit);
			return FORM;
		} else {
			Persona personaEdited = personaService.findId(personaid);
			personaEdited.setFirstName(persona.getFirstName());
			personaEdited.setLastName(persona.getLastName());
			personaEdited.setUser(persona.getUser());
			personaService.savePersona(personaEdited);
			return "redirect:/logout";
		}

	}

}
