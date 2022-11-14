
package org.springframework.samples.petris.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	private UserService userService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository, UserService userService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userService = userService;
	}

	@Transactional(rollbackFor=Exception.class)
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}

	@Transactional(rollbackFor=Exception.class)
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		User user = userService.findUser(username);
		if (user != null) {
			authority.setUser(user);
			authority.setAuthority(role);
			authoritiesRepository.save(authority);
		} else
			throw new DataAccessException("User '" + username + "' not found!") {
			};
	}

	@Transactional
    public Boolean getAuthorities(String username) throws DataAccessException {

        String ret = "";
        Boolean re  = false;
        List<Authorities> user = authoritiesRepository.findByUsername(username);
             ret = user.get(0).getAuthority();
             if(ret.equals("admin")) {
                 re = true;
             }
        return re;
    }

}
