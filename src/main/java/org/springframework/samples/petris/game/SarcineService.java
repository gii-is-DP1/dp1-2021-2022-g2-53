package org.springframework.samples.petris.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SarcineService {

	@Autowired
	SarcineRepository sarcineRepo;

	@Transactional
	public int sarcineCount() {
		return (int) sarcineRepo.count();
	}

	@Transactional
	public void save(Sarcine sarcine) {
		sarcineRepo.save(sarcine);
	}
}
