package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Integer> {
}
