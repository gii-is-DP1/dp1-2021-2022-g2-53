package org.springframework.samples.petclinic.persona;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;






public interface PersonaRepository extends CrudRepository<Persona, Integer>{
		
	@Query("SELECT p from Persona p WHERE p.userName = :userName")
	Persona findPersonaByUserName(@Param("userName") String userName);
	
}
