package org.springframework.samples.petris.game;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petris.persona.Persona;
import org.springframework.samples.petris.persona.PersonaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class GameService {

	private static final Integer numero_maximo_puntos_para_perder = 9;

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private PersonaService personaService;
	@Autowired
	private BoardService boardService;

	private static final String red_color = "red";
	private static final String black_color = "black";
	private static final String binary_type = "binary";
	private static final String pollution_type = "pollution";

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
	public Game findId(Integer id) {
		return gameRepo.findById(id).get();

	}

	@Transactional
	public Game findGameByToken(String token) {
		return gameRepo.findByToken(token);
	}

	@Transactional(rollbackFor=Exception.class)
	public void phases(Game gameEdited, Movement movement, BindingResult result) throws MoveInvalidException {

		String turno = gameEdited.getTurnos().get(gameEdited.getTurno());
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		Persona persona = personaService.getPersonaByUserName(username);
		String jugador = persona.getJugadores().get(persona.getJugadores().size() - 1).getColor();

		if (gameEdited.getPointsBlack() >= numero_maximo_puntos_para_perder
				|| gameEdited.getPointsBlack() >= numero_maximo_puntos_para_perder) {
			gameEdited.setTurno(gameEdited.getTurnos().size() - 2);
		}

		if ((turno.equals(red_color) && turno.equals(jugador))
				|| (turno.equals(black_color) && turno.equals(jugador))) {
			movement.setTipo(gameEdited.getTurnos().get(gameEdited.getTurno()));
			gameEdited.getBoard().movePieces(movement, result);
			if (!result.hasErrors()) {
				gameEdited.setTurno(gameEdited.getTurno() + 1);
				save(gameEdited);
			} else {
				result.getFieldError("destinyPosition");
				result.getAllErrors();
				save(gameEdited);
			}
		} else if (turno.equals(binary_type)) {
			boardService.binary(gameEdited.getBoard());
			gameEdited.setTurno(gameEdited.getTurno() + 1);
			save(gameEdited);
		} else if (turno.equals(pollution_type)) {
			// POSICION 0 SON ROJOS Y 1 SON NEGROS
			gameEdited.setPointsRed(gameEdited.getPointsRed() + gameEdited.getBoard().pollution().get(0));
			gameEdited.setPointsBlack(gameEdited.getPointsBlack() + gameEdited.getBoard().pollution().get(1));
			if (gameEdited.getPointsBlack() >= numero_maximo_puntos_para_perder
					|| gameEdited.getPointsBlack() >= numero_maximo_puntos_para_perder) {
				gameEdited.setTurno(gameEdited.getTurnos().size() - 2);
			} else {
				gameEdited.setTurno(gameEdited.getTurno() + 1);
			}
			save(gameEdited);
		}

		boardService.generateSarcines(gameEdited.getBoard());
		save(gameEdited);
	}

	@Transactional
	public void delete(Game game) {
		gameRepo.delete(game);

	}

}
