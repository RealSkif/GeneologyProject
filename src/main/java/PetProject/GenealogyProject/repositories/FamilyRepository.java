package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRepository extends JpaRepository<Family, Integer> {
    List<Family> findByVillagesIn(List<Village> villages);
}
