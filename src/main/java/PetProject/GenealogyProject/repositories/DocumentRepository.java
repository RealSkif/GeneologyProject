package PetProject.GenealogyProject.repositories;

import PetProject.GenealogyProject.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
