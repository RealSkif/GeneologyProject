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
    @ManyToMany(mappedBy = "documents")
    private List<Village> villages;
    @ManyToMany(mappedBy = "documents")
    private List<Person> persons;
    @Column(name = "title")
    private String title;
    @Column(name = "documenttype")
    private String documentType;
    @Column(name = "year")
    private int year;
}
