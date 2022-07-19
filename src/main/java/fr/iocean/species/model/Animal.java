package fr.iocean.species.model;

import fr.iocean.species.enums.Sex;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String color;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    private Species species;
    
    /*
    // Pas obligé d'avoir la relation de ce coté
    @ManyToMany(mappedBy = "animals")
    Set<Person> owners;
    */
    
    @Override
    public String toString() {
        return "Animal [id=" + id + ", name=" + name + ", color=" + color + ", sex=" + sex + ", species=" + species
                + "]";
    }
    
    // Getters / setters


    /*
     * public Set<Person> getOwners() { return owners; }
     * 
     * public void setOwners(Set<Person> owners) { this.owners = owners; }
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
