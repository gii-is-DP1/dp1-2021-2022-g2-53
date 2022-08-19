package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		List<Person> personas = new ArrayList<>();
		Person persona1 = new Person();
		persona1.setFirstName("Jose");
		persona1.setLastName("Martin");
		personas.add(persona1);

		Person persona2 = new Person();
		persona2.setFirstName("Juan Antonio");
		persona2.setLastName("Mena");
		personas.add(persona2);

		Person persona3 = new Person();
		persona3.setFirstName("Luis");
		persona3.setLastName("Chacon");
		personas.add(persona3);

		Person persona4 = new Person();
		persona4.setFirstName("Jose Maria");
		persona4.setLastName("Garcia");
		personas.add(persona4);

		model.put("personas", personas);
		model.put("title", "Petris");
		model.put("group", "Grupo g2-53");

		return "welcome";
	}
	
	@GetMapping({"/errorSuplantacion" })
	public String errorSuplantacion(Map<String, Object> model) {
		return "errorSuplantacion";
	}
}
