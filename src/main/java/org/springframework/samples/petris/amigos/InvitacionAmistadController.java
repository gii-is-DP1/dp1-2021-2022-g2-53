package org.springframework.samples.petris.amigos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petris.user.User;
import org.springframework.samples.petris.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvitacionAmistadController {

	@Autowired
	private InvitacionAmistadService amistadService;
	@Autowired
	private InvitacionAmistadRepository amistadRepo;
	@Autowired
	private UserService userService;

	@GetMapping(path = "/invitacionAmigo")
	public String listaInvitaciones(ModelMap modelMap) {

		String vista = "invitaciones/listaInvitaciones";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
		List<InvitacionAmistad> invitacion = amistadRepo.findInvitacionByUser2(user);
		modelMap.addAttribute("invitacion", invitacion);

		return vista;
	}

	@PostMapping(path = "/invitacionAmigo/{id}/aceptar")
	public String aceptar(@PathVariable("id") int id, ModelMap modelMap) {

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
		String inicio = amistadService.aceptarInvitacion(id, user);
		return inicio;
	}

	@PostMapping(path = "/invitacionAmigo/{id}/rechazar")
	public String rechazar(@PathVariable("id") int id, ModelMap modelMap) {

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user= userService.findUser(ud.getUsername());
		InvitacionAmistad invitacion = amistadService.findById(id);
		String inicio = amistadService.declinarInvitacion(invitacion, user);
		return inicio;
	}

	@GetMapping(path = "/invitacionAmigo/{username1}/{username2}/save")
	public String guardar(ModelMap modelMap, @PathVariable("username1") String username1,
			@PathVariable("username2") String username2) {

		User user1= userService.findUser(username1);
		User user2= userService.findUser(username2);
		if (!user1.getAmigos().contains(user2)) {
			InvitacionAmistad invitacion = new InvitacionAmistad();
			invitacion.setUser1(user1);
			invitacion.setUser2(user2);
			String inicio = amistadService.guardarInvitacion(invitacion);
			return inicio;

		} else {
			modelMap.addAttribute("message", "Ya eres amigo de ese usuario");
			return "/error";
		}
	}

	@PostMapping(path = "/invitacionAmigo/{user1}/{user2}/save")
	public String guardar(ModelMap modelMap, @PathVariable("user1") User user1, @PathVariable("user2") User user2) {

		if (!user1.getAmigos().contains(user2)) {
			InvitacionAmistad invitacion = new InvitacionAmistad();
			invitacion.setUser1(user1);
			invitacion.setUser2(user2);
			String retornar = amistadService.guardarInvitacion(invitacion);
			return retornar;
		} else {
			modelMap.addAttribute("message", "Ya eres amigo de ese usuario");
			return "/error";

		}

	}
}
