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
@Table(name = "person")
public class Person {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    @JoinTable(
            name = "document_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents;

    @ManyToMany
    @JoinTable(
            name = "person_village",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "village_id")
    )
    private List<Village> villages;

    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    private Family owner;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "age")
    private int age;
    @Column(name = "mother")
    private Integer mother;
    @Column(name = "father")
    private Integer father;
    @Column(name = "socialstatus")
    private String socialStatus;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", documents=" + documents +
                ", villages=" + villages +
                ", owner=" + owner +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", mother=" + mother +
                ", father=" + father +
                ", socialStatus='" + socialStatus + '\'' +
                '}';
    }
}
