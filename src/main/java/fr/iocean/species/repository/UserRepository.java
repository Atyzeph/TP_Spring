package fr.iocean.species.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.iocean.species.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
