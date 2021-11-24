package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private PieceService pieceService;
	
	@Transactional
	public int gameCount() {
		return (int) gameRepo.count();
	}

	@Transactional
	public Iterable<Game> findAll() {
		// TODO Auto-generated method stub
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
	public void binary(int gameId) {
		Board board = findId(gameId).getBoard();
		int i = 1;
		while (i <= 7) {
			if (board.binary(i).equals("red") || board.binary(i).equals("black")) {
				Piece piece = new Piece();
				piece.setBoard(board);
				piece.setColor(board.binary(i));
				piece.setPosition(i);
				pieceService.save(piece);
			}
			i++;
		}
	}

	
	@Transactional
	public void phases(int gameId, Movement movement, BindingResult result) throws MoveInvalidException {
		Game gameEdited = findId(gameId);
		String turno = gameEdited.getTurnos().get(gameEdited.getTurno());
		if (turno.equals("red") || turno.equals("black")) {
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
			binary(gameId);
			gameEdited.setTurno(gameEdited.getTurno()+1);
			save(gameEdited);
		} else if (turno.equals("pollution")) {
			//POSICION 0 SON ROJOS Y 1 SON NEGROS
			gameEdited.setPointsRed(gameEdited.getPointsRed() + gameEdited.getBoard().pollution().get(0));
			gameEdited.setPointsBlack(gameEdited.getPointsBlack() +gameEdited.getBoard().pollution().get(1));
			gameEdited.setTurno(gameEdited.getTurno()+1);
			save(gameEdited);
		}

	@Transactional
	public void delete(Game game) {
		gameRepo.delete(game);
		

	}

}
