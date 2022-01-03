package org.springframework.samples.petclinic.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.persona.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

	@Autowired 
	private BoardRepository boardRepo;
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private PieceService pieceService;
	@Autowired
	private SarcineService sarcineService;
	@Autowired
	private PersonaService personaService;
	

	@Transactional
	public int boardCount() {
		return (int) boardRepo.count();
	}

	

	public Board findById(Integer id){
		return boardRepo.findById(id).get();
	}
	
	
	
	@Transactional
	public void save(Board board) {
		boardRepo.save(board);
		
	}
	public void delete(Board board) {
		boardRepo.delete(board);
		
	}
	@Transactional
	public void binary(Board board) {
		
		int i = 1;
		while (i <= 7) {
			if (board.binary(i).equals("red") || board.binary(i).equals("black")) {
				Piece piece = new Piece();
				piece.setBoard(board);
				piece.setColor(board.binary(i));
				piece.setPosition(i);
				piece.setType("bacterium");
				pieceService.save(piece);
			} else if (board.binary(i).equals("red_sarcine")) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor("red");
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				board.getAllPiecesByPosition(i).stream().forEach(x->x.setColor(""));
				sarcineService.save(sarcine);
			} else if (board.binary(i).equals("black_sarcine")) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor("black");
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				board.getAllPiecesByPosition(i).stream().forEach(x->x.setColor(""));
				sarcineService.save(sarcine);
			}
			i++;
		}
	}
	
}