package org.springframework.samples.petclinic.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

	@Autowired 
	private BoardRepository boardRepo;

	

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
	
}