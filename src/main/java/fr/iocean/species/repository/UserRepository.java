package fr.iocean.species.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.iocean.species.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	// Le nom de la méthode correspond à la colone en bdd
	public User findByUsername(String username);
	
}
