package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController( DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("documents", documentService.findAll());
        return "documents/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("document", documentService.findOne(id));
        return "documents/show";
  }

   @GetMapping("/new")
    public String newDocument(@ModelAttribute("document") Document document) {
        return "documents/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("document") Document document) {
        documentService.save(document);
        return "redirect:/documents";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("document", documentService.findOne(id));
        return "documents/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("document") Document document, @PathVariable("id") int id) {
        documentService.update(id, document);
        return "redirect:/documents";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        documentService.delete(id);
        return "redirect:/documents";
    }
}
