package fr.iocean.species.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;


@RequestMapping
@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@GetMapping("/animals")
	public String listAnimals(Model model) {
		List<Animal> animals = (List<Animal>) animalRepository.findAll();
		model.addAttribute("animals", animals);
		
		return "animals";
	}
	
	@GetMapping("/animals/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animals = animalRepository.findById(id);
		if (animals.isPresent()) {
			model.addAttribute(animals.get());
			
			return "animals";
		}
		return "error";
	}
}
