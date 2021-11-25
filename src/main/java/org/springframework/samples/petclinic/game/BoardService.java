package org.springframework.samples.petclinic.game;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

	@Autowired 
	private BoardRepository boardRepo;

	
	public Optional<Board> findById(Integer id){
		return boardRepo.findById(id);
	}
	
	@Transactional
	public void save(Board board) {
		boardRepo.save(board);
		
	}
	
}