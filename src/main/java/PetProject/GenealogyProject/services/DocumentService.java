package PetProject.GenealogyProject.services;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)

public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document findOne(int id) {
        Optional<Document> foundDocument = documentRepository.findById(id);
        return foundDocument.orElse(null);
    }

    @Transactional
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Transactional
    public void update(int id, Document updatedDocument) {
        updatedDocument.setId(id);
        documentRepository.save(updatedDocument);
    }

    @Transactional
    public void delete(int id) {
        documentRepository.deleteById(id);
    }

}
