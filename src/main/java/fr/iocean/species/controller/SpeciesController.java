package fr.iocean.species.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.iocean.species.model.Species;
import fr.iocean.species.repository.SpeciesRepository;


@Controller
public class SpeciesController {

	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("/species")
	public String listSpecies(Model model) {
		List<Species> species = speciesRepository.findAll();
		model.addAttribute("species", species);
		
		return "spicies";
	}
	
	@GetMapping("/species/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Species> species = speciesRepository.findById(id);
		if (species.isPresent()) {
			model.addAttribute(species.get());
			
			return "species";
		}
		return "error";
	}
}
