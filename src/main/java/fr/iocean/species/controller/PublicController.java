package fr.iocean.species.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.iocean.species.model.User;
import fr.iocean.species.repository.UserRepository;

@Controller
public class PublicController {


	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/create")
	public String createUser(User user) {
		
		this.userRepository.save(user);
		
		return "redirect:/person";
	}
}
