package fr.iocean.species.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.SpeciesRepository;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;



@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("/animals")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String listAnimals(Model model) {
		model.addAttribute(
				"animals", 
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "species"))
				);
		
		return "animalsView/animals";
	}
	
	@GetMapping("/animals/{id}")
	@PreAuthorize("haseRole('ADMIN') or hasRole('USER')")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animals = animalRepository.findById(id);
		if (animals.isPresent()) {
			model.addAttribute(animals.get());
			model.addAttribute(
					"speciesList",
					speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName"))
					);
			
			return "animalsView/animalId";
		}
		return "error";
	}
	
	@GetMapping("/animals/create")
	@PreAuthorize("haseRole('ADMIN') or hasRole('USER')")
	public String Create(Model model) {
		model.addAttribute(
				"speciesList",
				speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName"))
				);
		model.addAttribute(new Animal());
		
		return "animalsView/animalCreate";
	}
		
	@PostMapping("/animals")
	@PreAuthorize("haseRole('ADMIN') or hasRole('USER')")
	public String CreateOrUpdate(Animal animal) {
		
		this.animalRepository.save(animal);
		
		return "redirect:/animals";
	}

	@GetMapping("/animals/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
		animalToDelete.ifPresent(a -> this.animalRepository.delete(a));
		
		return "redirect:/animals";
	}
}
