package org.springframework.samples.petclinic.persona;





import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Board;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @GetMapping(value = "/personas/registro")
    public String showGamePerson(ModelMap modelMap) {
        String view = "personas/listPersonas";
        Iterable<Persona> personas = personaService.findAll();
        modelMap.addAttribute("personas", personas);
        return view;
    }
    
  




    private static final String FORM = "personas/createOrUpdatePersonaForm";


    @Autowired
	public PersonaController(PersonaService personaService, UserService userService, AuthoritiesService authoritiesService) {
		this.personaService = personaService;
	}

    @GetMapping("/register")
    public String initCreationForm(Map<String, Object> model){
        Persona persona = new Persona();
        model.put("persona", persona);
        return FORM;
    }
    @PostMapping("/register")
    public String proccessCreationFOrm(@Valid Persona persona, BindingResult result){
        if(result.hasErrors()){
            return FORM;
        } else {
            System.out.println("");
            this.personaService.savePersona(persona);
            return "redirect:/";
        }
    }
    
    
    @GetMapping(value = "/personas/edit/{personaId}")
	public String editGame(ModelMap modelMap, @PathVariable("personaId") int personaid) {
		String view = FORM;
		Persona persona = personaService.findId(personaid);
		boolean edit = true;
		modelMap.addAttribute("persona", persona);
		modelMap.addAttribute("edit", edit);
		return view;
	}

	@PostMapping(value = "/personas/edit/{personaId}")
	public String processUpdateGameForm(ModelMap modelMap, @PathVariable("personaId") int personaid, @Valid Persona persona,
			BindingResult result) {
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
			return "redirect:/personas/registro";
		}

	}
    
    @GetMapping(value = "/personas/delete/{personaId}")
	public String deleteGame(ModelMap modelMap, @PathVariable("personaId") int personaid) {
		
		Optional<Persona> persona = Optional.of(personaService.findId(personaid));
		
		if (persona.isPresent()){
			personaService.delete(persona.get());
			modelMap.addAttribute("message", "persona borrado");
		} else {
			modelMap.addAttribute("message", "persona no borrado");

		}
		return "redirect:/personas/registro";

	}

}
