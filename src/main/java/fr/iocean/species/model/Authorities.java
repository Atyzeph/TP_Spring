package fr.iocean.species.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authorities {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
	@Column(length = 255)
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
