package PetProject.GenealogyProject.controllers;


import PetProject.GenealogyProject.models.Person;
import PetProject.GenealogyProject.services.DocumentService;
import PetProject.GenealogyProject.services.PersonService;
import PetProject.GenealogyProject.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final DocumentService documentService;
    private final VillageService villageService;

    @Autowired
    public PersonController(PersonService personService, DocumentService documentService, VillageService villageService) {
        this.personService = personService;
        this.documentService = documentService;
        this.villageService = villageService;
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
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personService.save(person);
        return "redirect:/people/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personService.update(id, person);
        return "redirect:/people/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people/index";
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
