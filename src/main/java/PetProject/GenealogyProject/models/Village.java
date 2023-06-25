package PetProject.GenealogyProject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.print.Doc;
import java.util.ArrayList;
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
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "document_village",
            joinColumns = @JoinColumn(name = "village_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents;

    @ManyToMany(mappedBy = "villages", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Family> families;
    @ManyToMany(mappedBy = "villages", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Person> persons;

    public void removeDocument(Document document) {
        this.documents.remove(document);
        document.getVillages().remove(this);
    }

    public void addDocument(Document document) {
        if (this.documents == null) documents = new ArrayList<>();
        this.documents.add(document);
        document.getVillages().add(this);
    }
}
