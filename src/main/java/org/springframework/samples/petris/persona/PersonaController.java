package org.springframework.samples.petris.persona;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.game.GameRepository;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.samples.petris.jugador.JugadorRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.samples.petris.user.AuthoritiesService;

import org.springframework.samples.petris.user.UserService;
import org.springframework.validation.BindingResult;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	@Autowired
	private PersonaRepository personaRepo;

	@Autowired
	private JugadorRepository jugadorRepo;

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/personas")
	public String showGamePerson(ModelMap modelMap, HttpServletResponse response) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		modelMap.addAttribute("persona", username);
		String view = "personas/partidaspersona";

		Collection<Game> res;
		res = this.jugadorRepo.getJugadorbygameId(persona.getId());
		modelMap.addAttribute("games", res);
		return view;
	}

	@GetMapping(value = "/people")
	public String showGamePeople(ModelMap modelMap, HttpServletResponse response) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		modelMap.addAttribute("persona", username);
		String view = "personas/partidaspeople";
		Collection<Game> res;
		res = this.gameRepo.getJugadoresbyGame();
		modelMap.addAttribute("games", res);
		return view;
	}

	@GetMapping(value = "/estadisticas")
	public String estadisticas(ModelMap modelMap, HttpServletResponse response) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		modelMap.addAttribute("persona", username);
		Persona persona = personaService.getPersonaByUserName(username);
		String view = "personas/estadisticas";

		Collection<Game> res;
		res = this.gameRepo.getJugadoresbyGame();
		modelMap.addAttribute("games", res);

		List<Integer> id;
		id = this.gameRepo.getIdbyGame();
		modelMap.addAttribute("id", id);

		Integer pb;
		pb = this.gameRepo.getpointsBlackbyGame();
		modelMap.addAttribute("pb", pb);

		Integer pr;
		pr = this.gameRepo.getpointsRedbyGame();
		modelMap.addAttribute("pr", pr);

		Iterable<Persona> g;
		g = this.personaRepo.getPersonas();
		modelMap.addAttribute("g", g);

		Integer u;
		u = this.jugadorRepo.getGamesFromPersona();
		modelMap.addAttribute("u", u);

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

		String clearTextPassword = persona.getUser().getPassword();
		persona.getUser().setPassword(passwordEncoder.encode(clearTextPassword));

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
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona2 = personaService.getPersonaByUserName(username);
		if (!persona2.equals(persona)) {
			return "redirect:/errorSuplantacion";
		}
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

	@GetMapping(value = "/personas/auditoria")
	public String showAuditable(ModelMap modelMap) {
		String view = "personas/auditoria";
		Iterable<Persona> persona = personaRepo.getPersonas();
		modelMap.addAttribute("personas", persona);

		return view;
	}

}
