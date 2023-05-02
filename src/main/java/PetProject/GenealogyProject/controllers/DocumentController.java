package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.dto.DocumentDAO;
import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/documents")
public class DocumentController {

//    private final DocumentDAO documentDAO;
    private final DocumentRepository documentRepository;



    @Autowired
    public DocumentController( DocumentRepository documentRepository) {
//        this.documentDAO = documentDAO;
        this.documentRepository = documentRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("documents", documentRepository.findAll());
        return "documents/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("documents", documentRepository.findById(id));
        return "documents/show";
//    }
//
//    @GetMapping("/new")
//    public String newDocument(@ModelAttribute("document") Document document) {
//        return "documents/new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("document") Document document) {
//        documentDAO.save(document);
//        return "redirect:/documents";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("document", documentDAO.show(id));
//        return "documents/edit";
//    }
//
////    @PatchMapping("/{id}")
////    public String update(@ModelAttribute("document") Document document, @PathVariable("id") int id) {
////        documentDAO.update(id, document);
////        return "redirect:/documents";
////    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        documentDAO.delete(id);
//        return "redirect:/documents";
    }

}
