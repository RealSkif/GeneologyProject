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
@Table(name = "family")
public class Family {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    @JoinTable(
            name = "family_village",
            joinColumns = @JoinColumn(name = "family_id"),
            inverseJoinColumns = @JoinColumn(name = "village_id")
    )
    private List<Village> villages;
    @OneToMany(mappedBy = "owner")
    private List<Person> persons;
    @Column(name = "lastname")
    private String lastName;


}
