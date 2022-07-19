package fr.iocean.species;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Person;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.PersonRepository;

@SpringBootApplication
public class SpeciesApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(SpeciesApplication.class);

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AnimalRepository animalRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpeciesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        logger.info(">>>>>>> Toutes les personnes : {}",
                this.personRepository.findAll());
        logger.info(">>>>>>> Tous les animaux : {}",
                this.animalRepository.findAll());

        // Ajout personne avec 1 animal
        Person newPersonne = new Person();
        newPersonne.setAge(55);
        newPersonne.setFirstname("Johnatan");
        newPersonne.setLastname("Doerémi");

        Set<Animal> listAnim = new HashSet<>();
        Optional<Animal> optional = this.animalRepository.findById(1);
        if (optional.isPresent()) {
            listAnim.add(optional.get());
        }

        newPersonne.setAnimals(listAnim);
        this.personRepository.save(newPersonne);
        
        // On va chercher la personne créée
//        Person personneCree = this.personRepository.findById(newPersonne.getId()).orElse(null);
        Optional<Person> personneCree = this.personRepository.findById(newPersonne.getId());
        if (personneCree.isPresent()) {
            logger.info(">>>>>>> find personne créée : {}", personneCree.get());

            // On va delete la personne créée
            logger.info(">>>>>>> delete personne creee");
            this.personRepository.delete(personneCree.get());
        }
    }
    
    // Manière différente pour lancer un "run" :
//    @Bean
//    public CommandLineRunner demo(PersonRepository repository) {
//      return (args) -> {
//          logger.info("Toutes les personnes : {}", repository.findAll());
//      };
//    }
}
