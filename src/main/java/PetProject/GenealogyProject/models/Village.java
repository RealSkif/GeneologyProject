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
@Table(name = "village")
public class Village {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(mappedBy = "villages")
    private List<Family> families;
    @ManyToMany(mappedBy = "villages")
    private List<Person> persons;
    @ManyToMany
    @JoinTable(
            name = "document_village",
            joinColumns = @JoinColumn(name = "village_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents;
    @Column(name = "name")
    private String name;


}

