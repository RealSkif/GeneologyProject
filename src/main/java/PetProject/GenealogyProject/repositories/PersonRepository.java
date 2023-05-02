package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
