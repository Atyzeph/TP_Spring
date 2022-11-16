package fr.iocean.species.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

//import fr.iocean.species.model.User;
import fr.iocean.species.repository.UserRepository;

@Service
public class DetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//User user = userRepository.findByUsername(username);
		
//		User(
//				myUser.getLogin(),
//				myUser.getPassword(),
//				grantedAuthorities // <= liste d’authorities à créer aussi
//				);
		
		return null;
	}

}
