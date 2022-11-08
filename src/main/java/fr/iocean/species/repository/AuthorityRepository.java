package fr.iocean.species.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.iocean.species.model.Authorities;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, String> {

}
