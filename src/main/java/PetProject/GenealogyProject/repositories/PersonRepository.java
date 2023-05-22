package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByOwner(Family owner);
    List<Person> findPersonByFatherOrMother(int motherId, int fatherId);

}
