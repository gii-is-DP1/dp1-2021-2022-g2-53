package org.springframework.samples.petclinic.game;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;
	
	@Transactional
	public int gameCount() {
		return (int) gameRepo.count();
	}

	@Transactional
	public Iterable<Game> findAll() {
		// TODO Auto-generated method stub
		return gameRepo.findAll();
	}
	
	
}
