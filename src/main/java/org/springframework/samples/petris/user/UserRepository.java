package org.springframework.samples.petris.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT  p from User p ")
	Iterable<User> getUsers();

	@Query("SELECT  p from User p WHERE p.username = :username ")
	Iterable<User> getUserByUsername(String username);

	@Query("SELECT u.amigos FROM User u where u.username = :username")
	public List<List<User>> findAmigos(@Param("username") String username);

	@Query("SELECT u FROM User u where u.username = :username")
	public User findByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.username LIKE %:text%")
	public Page<User> findUsersWithPagination(Pageable pageable, String text);

	@Query("SELECT u FROM User u")
	public Page<User> findAllUsersWithPagination(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.username LIKE %:text%")
	public List<User> findUsersByText(@Param("text") String text);

}
