package org.springframework.samples.petris.amigos;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petris.user.User;

public interface InvitacionAmistadRepository extends CrudRepository<InvitacionAmistad, Integer>{
	

	@Query("SELECT i FROM InvitacionAmistad i WHERE i.user1 = :user")
	public List<InvitacionAmistad> findInvitacionByUser1(@Param("user") User user);

	@Query("SELECT i FROM InvitacionAmistad i WHERE i.user2 = :user")
    public List<InvitacionAmistad> findInvitacionByUser2(@Param("user") User user);  
	
	@Modifying
	@Query(value="INSERT INTO AMIGOS(user_username, amigos_username) VALUES (:username1,:username2) AND INSERT INTO AMIGOS(user_username, amigos_username) VALUES (:username2,:username1)",nativeQuery=true)
    public void guardarAmigos(@Param("username1") String username1, @Param("username2") String username2);
	
	@Modifying
	@Query(value="INSERT INTO AMIGOS(user_username, amigos_username) VALUES (:username1,:username2)", nativeQuery=true)
    public void guardarAmigoUser1(@Param("username1") String username1, @Param("username2") String username2);
	
	@Modifying
	@Query(value="INSERT INTO AMIGOS(user_username, amigos_username) VALUES (:username2,:username1)", nativeQuery=true)
    public void guardarAmigoUser2(@Param("username2") String username2, @Param("username1") String username1);

	@Query(value="SELECT f.amigos_username FROM AMIGOS f WHERE f.user_username = :username", nativeQuery=true)
	public List<String> findAmigosUserFromUsername(@Param("username") String username);
}
