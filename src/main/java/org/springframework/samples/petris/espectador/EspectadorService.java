/*package org.springframework.samples.petris.espectador;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspectadorService {

	@Autowired
	EspectadorRepository espectadorRepo;

	@Transactional
	public void save(Espectador espectador) {
		espectadorRepo.save(espectador);
	}

	@Transactional
	public int espectadorCount() {
		return (int) espectadorRepo.count();
	}

	@Transactional
	public Espectador findId(int id) {
		return espectadorRepo.findById(id).get();

	}

	

}
*/