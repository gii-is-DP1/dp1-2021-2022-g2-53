/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petris.user;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";

	@Autowired
	private final PersonaService personaService;
	@Autowired
	private final UserRepository userRepo;
	@Autowired
	private final UserService userService;


	@Autowired
	public UserController(PersonaService personaService, UserService userService,UserRepository userRepo) {
		this.personaService = personaService;
		this.userService= userService;
		this.userRepo= userRepo;
	
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Persona persona = new Persona();
		model.put("owner", persona);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Persona p, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		} else {
			this.personaService.savePersona(p);
			return "redirect:/";
		}
	}

	@GetMapping(path="users/amigos")
	public String listaAmigos(ModelMap modelMap) {
		String vista = "users/listaAmigos";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
		List<User> amigos =  userService.findAmigos(user.getUsername());
		modelMap.addAttribute("amigos", amigos);
		return vista;
	}  

	@GetMapping(path="users/buscar/user")
    public String buscarUsuario(ModelMap modelMap) {
    	String vista = "users/buscarUsuario";
    	Persona personaBusqueda = new Persona();
    	modelMap.addAttribute("personaBusqueda", personaBusqueda);
    	return vista;
    }

	@GetMapping(path="users/encontrados")
	public String listaUsuariosEncontrados(ModelMap modelMap, @RequestParam("username") String username) {
    
		String vista = "users/usuariosEncontrados";
		Iterable<User> users =  userRepo.getUserByUsername(username);
		modelMap.addAttribute("users", users);
    	UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
        modelMap.addAttribute("usuActual", user);
        modelMap.addAttribute("username", username);
		return vista;
	}  

	@PostMapping(path="users/encontrado")
    public String buscarTexto(@Valid Persona p, BindingResult result, ModelMap modelMap) {
        return "redirect:/users/encontrados?username=" + p.getUser().getUsername();
    }

	
	@GetMapping(path="users/delete/{username}")
    public String eliminarAmigo(@PathVariable("username") String username, ModelMap modelMap) {
    	
    	UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
    	userService.borrarAmigo(user, username);
    	return "redirect:/users/amigos";
    }



}
