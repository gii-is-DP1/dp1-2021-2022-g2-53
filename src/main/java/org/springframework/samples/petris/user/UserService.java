
package org.springframework.samples.petris.user;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}

	@Transactional(rollbackFor=Exception.class)
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Transactional
	public User findUser(String username) {
		return userRepository.findById(username).get();
	}

	@Transactional
	public List<User> findAmigos(String user){
		return userRepository.findByUsername(user).getAmigos();

	
	}

	@Transactional
	public List<User> findUsersByText(String text){
		return userRepository.findUsersByText(text);
	}

	@Transactional
	public void borrarAmigo(User user, String username) throws DataAccessException {
		
		List<User> amigos = user.getAmigos();
		User amigo = userRepository.findByUsername(username);
		List<User> amigo2 = amigo.getAmigos();
		amigos.remove(amigo);
		amigo2.remove(user);
		user.setAmigos(amigos);
		amigo.setAmigos(amigo2);
		userRepository.save(user);
		userRepository.save(amigo);
	}
	
	

}
