package org.springframework.samples.petclinic.persona;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import org.springframework.samples.petclinic.user.User;





@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer>{

		@Query("SELECT p from Persona p WHERE p.user = :user")
		Persona getPersonaByUser(@Param("user") User user);

	//@Query("SELECT p from Persona p WHERE p.username.name = :userName")
	//@Query("SELECT persona from Persona persona WHERE user.username LIKE :username%")
	//Persona findPersonaByUserName(@Param("userName") String userName);
	
}
