package org.springframework.samples.petris.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petris.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		List<Person> personas = new ArrayList<>();

		Person persona1 = new Person();
		persona1.setFirstName("Juan Antonio");
		persona1.setLastName("Mena");
		personas.add(persona1);

		Person persona2 = new Person();
		persona2.setFirstName("Luis");
		persona2.setLastName("Chacon");
		personas.add(persona2);

		Person persona3 = new Person();
		persona3.setFirstName("Jose Maria");
		persona3.setLastName("Garcia");
		personas.add(persona3);

		model.put("personas", personas);
		model.put("title", "Petris");
		model.put("group", "Grupo g2-53");

		return "welcome";
	}
	
	@GetMapping({"/errorSuplantacion" })
	public String errorSuplantacion(Map<String, Object> model) {
		return "errorSuplantacion";
	}
	
	@GetMapping({"/errorMismoUsuarioEnPartida" })
	public String errorMismoUsuarioEnPartida(Map<String, Object> model) {
		return "errorMismoUsuarioEnPartida";

		
	}

	@GetMapping({"/error" })
		public String error(Map<String, Object> model) {
			return "error";
		}
}
