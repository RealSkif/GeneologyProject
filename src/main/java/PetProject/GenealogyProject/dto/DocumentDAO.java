package PetProject.GenealogyProject.dto;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.services.DocumentService;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;

@Component
public class DocumentDAO {


        private static int DOCUMENT_COUNT;
        private DocumentService documents;



        public List<Document> index() {
            return documents.findAll();
        }

        public Document show(int id) {
            return documents.findOne(id);
        }

        public void save(Document document) {
            documents.save(document);
        }

//        public void update(int id, Document updatedDocument) {
//            Document documentToBeUpdated = show(id);
//
//            documentToBeUpdated.setTitle(updatedDocument.getTitle());
//        }

        public void delete(int id) {
            documents.delete(id);
        }

}
