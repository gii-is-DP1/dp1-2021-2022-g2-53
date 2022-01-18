package org.springframework.samples.petclinic.persona;




import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;


import org.springframework.samples.petclinic.game.GameService;

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

}
