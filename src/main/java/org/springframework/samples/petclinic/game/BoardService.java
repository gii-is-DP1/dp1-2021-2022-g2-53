package org.springframework.samples.petclinic.game;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired 
	BoardRepository boardRepo;
	
	public Optional<Board> findById(Integer id){
		return boardRepo.findById(id);
	}
}