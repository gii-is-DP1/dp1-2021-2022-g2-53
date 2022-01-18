package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.persona.Persona;
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class GameService {
					
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private PieceService pieceService;
	@Autowired
	private SarcineService sarcineService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private BoardService boardService;
	
	
	@Transactional
	public int gameCount() {
		return (int) gameRepo.count();
	}

	@Transactional
	public Iterable<Game> findAll() {
		return gameRepo.findAll();
	}

	@Transactional
	public void save(Game game) {
		gameRepo.save(game);
	}
	

	@Transactional
	public Game findId(int id) {
		return gameRepo.findById(id).get();

	}
	
	
	
	@Transactional
	public Game findGameByToken(String token) {
		return gameRepo.findByToken(token);
	} 

	

	
	@Transactional
	public void phases(Game gameEdited, Movement movement, BindingResult result) throws MoveInvalidException {
		
		String turno = gameEdited.getTurnos().get(gameEdited.getTurno());
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username); 
		String jugador = persona.getJugadores().get(persona.getJugadores().size()-1).getColor();
		if ((turno.equals("red") && turno.equals(jugador)) || (turno.equals("black") && turno.equals(jugador))){
				movement.setTipo(gameEdited.getTurnos().get(gameEdited.getTurno()));
				gameEdited.getBoard().movePieces(movement, result);
				if(!result.hasErrors()) {
					gameEdited.setTurno(gameEdited.getTurno()+1);
					save(gameEdited);	
				}else {
					result.getFieldError("destinyPosition");
					result.getAllErrors();
					save(gameEdited);
				}
		} else if (turno.equals("binary")) {
			boardService.binary(gameEdited.getBoard());
			gameEdited.setTurno(gameEdited.getTurno()+1);
			save(gameEdited);
		} else if (turno.equals("pollution")) {
			//POSICION 0 SON ROJOS Y 1 SON NEGROS
			gameEdited.setPointsRed(gameEdited.getPointsRed() + gameEdited.getBoard().pollution().get(0));
			gameEdited.setPointsBlack(gameEdited.getPointsBlack() +gameEdited.getBoard().pollution().get(1));
			gameEdited.setTurno(gameEdited.getTurno()+1);
			save(gameEdited);}
	
		}
	

	@Transactional
	public void delete(Game game) {
		gameRepo.delete(game);
		

	}

}
