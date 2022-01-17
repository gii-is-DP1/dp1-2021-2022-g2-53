package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PieceService {

	@Autowired
	PieceRepository pieceRepo;
	
	@Transactional
	public int pieceCount() {
		return (int) pieceRepo.count();
	}
	
	public Piece findById(Integer id){
		return pieceRepo.findById(id).get();
	}
	

	@Transactional
	public void save(Piece piece) {
		pieceRepo.save(piece);
	}

	@Transactional
	public void delete(Piece piece) {
		pieceRepo.delete(piece);
	}

	@Transactional
	public void deleteById(Integer id) {
		pieceRepo.deleteById(id);
	}
}
