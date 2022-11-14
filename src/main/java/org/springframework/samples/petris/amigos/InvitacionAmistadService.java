package org.springframework.samples.petris.amigos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petris.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitacionAmistadService {
	
	 	@Autowired 
		private InvitacionAmistadRepository amistadRepository;

		@Transactional
		public Iterable<InvitacionAmistad> findAll(){
			return amistadRepository.findAll();
		}
	    
		@Transactional(readOnly = true)
		public InvitacionAmistad findById(Integer id) throws DataAccessException{
			return amistadRepository.findById(id).get();
		}
		
		@Transactional
		public String guardarInvitacion(InvitacionAmistad invitacion) throws DataAccessException{
			if (invitacion.getUser1() != invitacion.getUser2()) {
				amistadRepository.save(invitacion);
				return "/welcome";
			} 
			return "/welcome";
		}

		@Transactional
		public String aceptarInvitacion(int id, User userAutenticado) throws DataAccessException {
	 
			InvitacionAmistad invitacion = this.findById(id);
			if(userAutenticado == invitacion.getUser2()) {
				this.guardarAmigos(invitacion.getUser1().getUsername(), invitacion.getUser2().getUsername());
				this.declinarInvitacion(invitacion, userAutenticado);
				
			} 
			return "redirect:/welcome";
		}
		
	

		@Transactional
		public String declinarInvitacion(InvitacionAmistad invitacion, User userAutenticado) throws DataAccessException{
			if (invitacion.getUser2() != invitacion.getUser1()) {
				amistadRepository.delete(invitacion);
			} 
			return "redirect:/welcome";
		}

	

		@Transactional
		public void borrarUser(User user) throws DataAccessException {
			List<InvitacionAmistad> invitacionUser1 = amistadRepository.findInvitacionByUser1(user);
			List<InvitacionAmistad> invitacionUser2 = amistadRepository.findInvitacionByUser2(user);
			for (InvitacionAmistad f1: invitacionUser1) {
				amistadRepository.delete(f1);
			}
			for (InvitacionAmistad f2: invitacionUser2) {
				amistadRepository.delete(f2);
			}
		}
	
		@Transactional
	public void guardarAmigos(String username1, String username2) throws DataAccessException {
		if (username1 != username2) {
			amistadRepository.guardarAmigoUser1(username1, username2);
			amistadRepository.guardarAmigoUser2(username2, username1);
		} else {
			throw new DataAccessException("Un usuario no puede agregarse a si mismo") {};
		}
	}
	

}
