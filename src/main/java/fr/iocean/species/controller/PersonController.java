package fr.iocean.species.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/person")
	public String listPerson(Model model) {
		model.addAttribute(
				"persons", 
				personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstname"))
				);
		
		return "personView/person";
	}
	
	@GetMapping("/person/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			model.addAttribute(person.get());
			
			return "personView/personId";
		}
		return "error";
	}
	
	@GetMapping("/person/create")
	public String create(Model model) {
		
		model.addAttribute(new Person());
		
		return "personView/personCreate";
	}
	
	@PostMapping("/person")
	public String createOrUpdate(Person person) {
		this.personRepository.save(person);
		
		return "redirect:/person";
	}
	
	@GetMapping("/person/delete/{id}")
	public String delete(@PathVariable("id") Integer personId) {
		Optional<Person> personToDelete = this.personRepository.findById(personId);
		personToDelete.ifPresent(pers -> this.personRepository.delete(pers));
		
		return "redirect:/person";
	}
}
