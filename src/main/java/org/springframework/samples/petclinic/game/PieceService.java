package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PieceService {
	
	@Autowired
	PieceRepository pieceRepo;
	
	@Transactional
	public void save(Piece piece) {
		pieceRepo.save(piece);
	}
}
