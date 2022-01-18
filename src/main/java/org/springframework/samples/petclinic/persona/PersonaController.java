package org.springframework.samples.petclinic.persona;




import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;


import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


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
    
    @GetMapping(value = "/personas/register")
    public String showGamePerson(ModelMap modelMap) {
        String view = "personas/listPersonas";
        Iterable<Persona> personas = personaService.findAll();
        modelMap.addAttribute("personas", personas);
        return view;
    }
    
  




}
