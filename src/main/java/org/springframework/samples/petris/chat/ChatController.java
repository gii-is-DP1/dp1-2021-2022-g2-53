package org.springframework.samples.petris.chat;

import java.util.Collection;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petris.game.Game;
import org.springframework.samples.petris.game.GameService;
import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatController {
    
	
	@Autowired
	private ChatService chatService;
    @Autowired
	private GameService gameService;
    @Autowired
	private PersonaService personaService;

    @Autowired
	private ChatRepository chatRepo;
		
	@GetMapping(path="/{gameId}")
	 public String listadoChats(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {
	 	String vista = "chats/chat";
        response.addHeader("Refresh","12");
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		Game game = gameService.findId(gameId);
		
		if (game.getJugadores().get(0).getPersona() != persona && game.getJugadores().get(1).getPersona() != persona) {
		
		return "redirect:/errorSuplantacion";
		}
		modelMap.addAttribute("persona", persona);
        Collection<Chat> res;
		res = this.chatRepo.getChatsbyGameId(game.getId());
		modelMap.addAttribute("chats", res);
		
		modelMap.addAttribute("NuevoMensaje", new Chat());
	 	return vista;
	 }

    @PostMapping(path="/{gameId}/save")
	public String saveChat(ModelMap modelMap, @PathVariable("gameId") int gameId, @Valid Chat chat) {
        Game game = gameService.findId(gameId);
        chat.setGame(game);
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
        chat.setPersona(persona);
        chatService.save(chat);
        return "redirect:/chats/" + game.getId();
		
	}
	
	
}