package org.springframework.samples.petris.game;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

	private static final Integer numero_de_casillas = 7;
	private static final Integer numero_maximo_de_fichas_en_casilla = 5;
	private static final Integer numero_maximo_antes_de_sarcina = 4;

	@Autowired
	private BoardRepository boardRepo;

	@Autowired
	private PieceService pieceService;
	@Autowired
	private SarcineService sarcineService;

	private static final String red_color = "red";
	private static final String black_color = "black";
	private static final String red_color_sarcine = "red_sarcine";
	private static final String black_color_sarcine = "black_sarcine";
	private static final String bactery_type = "bacterium";

	@Transactional
	public int boardCount() {
		return (int) boardRepo.count();
	}

	@Transactional
	public Board findById(Integer id) {
		return boardRepo.findById(id).get();
	}

	@Transactional
	public void save(Board board) {
		boardRepo.save(board);

	}

	@Transactional
	public void delete(Board board) {
		boardRepo.delete(board);

	}

	@Transactional
	public void binary(Board board) {

		int i = 1;
		while (i <= numero_de_casillas) {
			if (board.binaryboard(i).equals(red_color) || board.binaryboard(i).equals(black_color)) {
				Piece piece = new Piece();
				piece.setBoard(board);
				piece.setColor(board.binaryboard(i));
				piece.setPosition(i);
				piece.setType(bactery_type);
				pieceService.save(piece);
			} else if (board.binaryboard(i).equals(red_color_sarcine)) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor(red_color);
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				int j = 0;
				while (j < numero_maximo_antes_de_sarcina) {
					board.getAllPiecesByPosition(i).get(j).setColor("");
					j++;
				}
				sarcineService.save(sarcine);
			} else if (board.binaryboard(i).equals(black_color_sarcine)) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor(black_color);
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				int j = 0;
				while (j < numero_maximo_antes_de_sarcina) {
					board.getAllPiecesByPosition(i).get(j).setColor("");
					j++;
				}
				sarcineService.save(sarcine);
			}
			i++;
		}
	}

	@Transactional
	public void generateSarcines(Board board) {
		int i = 1;
		while (i <= numero_de_casillas) {
			if (board.checkSarcines(i).equals(red_color_sarcine)) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor(red_color);
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				int j = 0;
				while (j < numero_maximo_de_fichas_en_casilla) {
					board.getAllPiecesByPosition(i).get(j).setColor("");
					j++;
				}
				sarcineService.save(sarcine);
			} else if (board.checkSarcines(i).equals(black_color_sarcine)) {
				Sarcine sarcine = new Sarcine();
				sarcine.setColor(black_color);
				sarcine.setBoard(board);
				sarcine.setPosition(i);
				int j = 0;
				while (j < numero_maximo_de_fichas_en_casilla) {
					board.getAllPiecesByPosition(i).get(j).setColor("");
					j++;
				}
				sarcineService.save(sarcine);
			}
			i++;
		}
	}

}