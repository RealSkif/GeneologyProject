package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.models.Village;
import PetProject.GenealogyProject.services.DocumentService;
import PetProject.GenealogyProject.services.FamilyService;
import PetProject.GenealogyProject.services.PersonService;
import PetProject.GenealogyProject.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;
    private final PersonService personService;
    private final DocumentService documentService;
    private final VillageService villageService;

    @Autowired
    public FamilyController(FamilyService familyService, PersonService personService, DocumentService documentService, VillageService villageService) {
        this.familyService = familyService;
        this.personService = personService;
        this.documentService = documentService;
        this.villageService = villageService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("families", familyService.findAll());
        return "families/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("family", familyService.findOne(id));
        return "families/show";
    }

    @GetMapping("/{id}/members")
    public String showFamilyMembers(@PathVariable("id") int id, Model model) {
        Family owner = familyService.findOne(id);
        model.addAttribute("members", personService.findByOwner(owner));
        return "/families/members";
    }

    @GetMapping("/new")
    public String mewFamily(Model model) {
        List<Document> documents = documentService.findAll();
        List<Village> villages = villageService.findAll();
        model.addAttribute("allVillages", villages);
        model.addAttribute("allDocuments", documents);
        model.addAttribute("family", new Family());
        return "families/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("family") Family family) {
        familyService.save(family);
        return "redirect:/families";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Document> documents = documentService.findAll();
        List<Village> villages = villageService.findAll();
        model.addAttribute("allVillages", villages);
        model.addAttribute("allDocuments", documents);
        model.addAttribute("family", familyService.findOne(id));
        return "families/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("family") Family family, @PathVariable("id") int id) {
        familyService.update(id, family);
        return "redirect:/families";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        familyService.delete(id);
        return "redirect:/families";
    }
}