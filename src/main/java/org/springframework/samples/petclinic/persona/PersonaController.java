package org.springframework.samples.petclinic.persona;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.user.AuthoritiesService;

@Controller
public class PersonaController {

    private static final String FORM = "personas/createOrUpdatePersonaForm";

    private final PersonaService personaService;

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
            this.personaService.savePersona(persona);
            return "redirect:/welcome";
        }
    }

}
