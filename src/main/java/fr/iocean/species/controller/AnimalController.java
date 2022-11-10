package fr.iocean.species.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import org.springframework.data.domain.Sort;


@RequestMapping
@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@GetMapping("/animals")
	public String listAnimals(Model model) {
		model.addAttribute(
				"animals", 
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "species"))
				);
		
		return "animalsView/animals";
	}
	
	@GetMapping("/animals/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animals = animalRepository.findById(id);
		if (animals.isPresent()) {
			model.addAttribute(animals.get());
			
			return "animalsView/animalId";
		}
		return "error";
	}
	
	@GetMapping("/animals/create")
	public String Create(Model model) {
		
		model.addAttribute(new Animal());
		
		return "animalsView/animalCreate";
	}
		
	@PostMapping("/animals")
	public String CreateOrUpdate(Animal animal) {
		
		this.animalRepository.save(animal);
		
		return "redirect:/animals";
	}

	@GetMapping("/animals/delete/{id}")
	public String delete(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
		animalToDelete.ifPresent(a -> this.animalRepository.delete(a));
		
		return "redirect:/animals";
	}
}
