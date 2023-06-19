package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VillageRepository extends JpaRepository<Village, Integer> {
}
