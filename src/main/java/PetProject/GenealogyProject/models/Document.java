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
    @Column(name = "title")
    private String title;
    @Column(name = "documenttype")
    private String documentType;
    @Column(name = "year")
    private int year;

    public void removeVillage(Village village){
        this.villages.remove(village);
        village.getDocuments().remove(this);
    }

    public void addVillage(Village village){
        this.villages.add(village);
        village.getDocuments().add(this);
    }
}
