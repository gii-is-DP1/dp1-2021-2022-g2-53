package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void delete(Game game) {
		gameRepo.delete(game);
		
	}

}
