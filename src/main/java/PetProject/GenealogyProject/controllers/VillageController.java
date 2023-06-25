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
        List<Document> documents = documentService.findAll();
        model.addAttribute("village", new Village());
        model.addAttribute("allDocuments", documents);
        return "villages/new";
    }

    @PostMapping()
    public String saveVillage(@ModelAttribute("village") Village village) {
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

