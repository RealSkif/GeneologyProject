package PetProject.GenealogyProject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "document")
public class Document {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(mappedBy = "documents", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Village> villages;
    @ManyToMany(mappedBy = "documents", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Person> persons;
    @Column(name = "title")
    private String title;
    @Column(name = "documenttype")
    private String documentType;
    @Column(name = "year")
    private int year;

    public void removePerson(Person person){
        this.persons.remove(person);
        person.getDocuments().remove(this);
    }

    public void addPerson(Person person){
        this.persons.add(person);
        person.getDocuments().add(this);
    }
    public void removeVillage(Village village){
        this.villages.remove(village);
        village.getDocuments().remove(this);
    }

    public void addVillage(Village village){
        this.villages.add(village);
        village.getDocuments().add(this);
    }
}
