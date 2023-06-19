package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.models.Village;
import PetProject.GenealogyProject.services.DocumentService;
import PetProject.GenealogyProject.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/villages")
public class VillageController {

    private final VillageService villageService;
    private final DocumentService documentService;

    @Autowired
    public VillageController(VillageService villageService, DocumentService documentService) {
        this.villageService = villageService;
        this.documentService = documentService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("villages", villageService.findAll());
        return "villages/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("village", villageService.findOne(id));

        return "villages/show";
    }
//    @GetMapping("/{id}/families")
//    public String showFamilies(@PathVariable("id") int id, Model model) {
//        List<Village> village = List.of(villageService.findOne(id));
//        model.addAttribute("families", familyService.findByVillagesIn(village));
//        return "villages/families";
//    }
    @GetMapping("/{id}/documents")
    public String showDocuments(@PathVariable("id") int id, Model model) {
        List<Village> village = List.of(villageService.findOne(id));
        model.addAttribute("documents", documentService.findByVillagesIn(village));
        return "villages/documents";
    }
    @GetMapping("/new")
    public String newVillage(Model model) {
        Village village = new Village();
        List<Document> documents = documentService.findAll();
        ArrayList<Integer> ids = new ArrayList<>();
        model.addAttribute("village", village);
        model.addAttribute("docs", documents);
        model.addAttribute("id", ids);
        return "villages/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("village") Village village,
                         @ModelAttribute("docs") Document doc,
                         @ModelAttribute("id") ArrayList<Integer> ids) {
        doc = documentService.findOne(doc.getId());
        System.out.println(doc.getId() + " "+ doc.getTitle()) ;
        village.setDocuments(List.of(doc));
        village.setId(null);
        System.out.println( village.getId());
//        List<Document> selectedDocuments = village.getSelectedDocuments();
        // Perform any necessary operations with the selected documents
        // For example, you can associate the documents with the village entity

        villageService.save(village);
        return "redirect:/villages";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("village", villageService.findOne(id));
        return "villages/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("village") Village village, @PathVariable("id") int id) {
        villageService.update(id, village);
        return "redirect:/villages";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        villageService.delete(id);
        return "redirect:/villages";
    }
}

