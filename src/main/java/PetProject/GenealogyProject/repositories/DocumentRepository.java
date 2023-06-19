package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByVillagesIn(List<Village> village);
}
