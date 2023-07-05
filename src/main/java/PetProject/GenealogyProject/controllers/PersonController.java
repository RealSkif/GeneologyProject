package PetProject.GenealogyProject.controllers;


import PetProject.GenealogyProject.models.Document;
import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.models.Person;
import PetProject.GenealogyProject.models.Village;
import PetProject.GenealogyProject.services.DocumentService;
import PetProject.GenealogyProject.services.FamilyService;
import PetProject.GenealogyProject.services.PersonService;
import PetProject.GenealogyProject.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final DocumentService documentService;
    private final VillageService villageService;
    private final FamilyService familyService;

    @Autowired
    public PersonController(PersonService personService, DocumentService documentService, VillageService villageService, FamilyService familyService) {
        this.personService = personService;
        this.documentService = documentService;
        this.villageService = villageService;
        this.familyService = familyService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, Model children, Model mother, Model father) {

        Person person = personService.findOne(id);
        if (person.getFather() != null) father.addAttribute("father", personService.findOne(person.getFather()));
        if (person.getMother() != null) mother.addAttribute("mother", personService.findOne(person.getMother()));
        children.addAttribute("children", personService.findChildren(id, id));
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        List<Document> documents = documentService.findAll();
        List<Village> villages = villageService.findAll();
        List<Family> families = familyService.findAll();


        model.addAttribute("person", new Person());
        model.addAttribute("allVillages", villages);
        model.addAttribute("allDocuments", documents);
        model.addAttribute("allFamilies", families);
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person,
                         @RequestParam("families") int familyId) {
        person.setOwner(familyService.findOne(familyId));
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Document> documents = documentService.findAll();
        List<Village> villages = villageService.findAll();
        List<Family> families = familyService.findAll();
        List<Person> thisFamily = personService.findByOwner(personService.findOne(id).getOwner());
        model.addAttribute("allVillages", villages);
        model.addAttribute("allDocuments", documents);
        model.addAttribute("allFamilies", families);
        model.addAttribute("thisFamily", thisFamily);
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id,
                         @RequestParam("families") int familyId) {
        person.setOwner(familyService.findOne(familyId));
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/documents")
    public String showDocuments(@PathVariable("id") int id, Model model) {
        List<Person> person = List.of(personService.findOne(id));
        model.addAttribute("documents", documentService.findByPersonsIn(person));
        return "people/documents";
    }

    @GetMapping("/{id}/villages")
    public String showVillages(@PathVariable("id") int id, Model model) {
        List<Person> person = List.of(personService.findOne(id));
        model.addAttribute("villages", villageService.findByPersonsIn(person));
        return "people/villages";
    }

}
