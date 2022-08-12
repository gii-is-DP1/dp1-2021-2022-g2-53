package org.springframework.samples.petclinic.persona;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import org.springframework.samples.petclinic.user.User;





@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer>{

		@Query("SELECT p from Persona p WHERE p.user = :user")
		Persona getPersonaByUser(@Param("user") User user);
		
		Page<Persona> findAll(Pageable pageable);
		

		
	

	//@Query("SELECT p from Persona p WHERE p.username.name = :userName")
	//@Query("SELECT persona from Persona persona WHERE user.username LIKE :username%")
	//Persona findPersonaByUserName(@Param("userName") String userName);
	
}
