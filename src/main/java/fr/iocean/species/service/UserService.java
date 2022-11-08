package fr.iocean.species.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.iocean.species.model.User;
import fr.iocean.species.repository.AuthorityRepository;
import fr.iocean.species.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void findByLogin(String login) {
		Optional<User> userOptional = this.userRepository.findById(login);
	}
	
	public User createUser(String userName, String password) {
		User user = new User();
		user.setUsername(userName);
		user.setPassword(passwordEncoder.encode(password));
		user.getAuthorities().add(this.authorityRepository.findById("ROLE_USER").orElseThrow(EntityNotFoundException::new));
		
		return this.userRepository.save(user);
	}
}
