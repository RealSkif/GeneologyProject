package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.models.Family;
import PetProject.GenealogyProject.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/families")
public class FamilyController {

    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("families", familyService.findAll());
        return "families/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("family", familyService.findOne(id));
        return "families/show";
    }

    @GetMapping("/new")
    public String mewFamily(@ModelAttribute("family") Family family) {
        return "families/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("family") Family family) {
        familyService.save(family);
        return "redirect:/families/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("family", familyService.findOne(id));
        return "families/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("family") Family family, @PathVariable("id") int id) {
        familyService.update(id, family);
        return "redirect:/families/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        familyService.delete(id);
        return "redirect:/villages/index";
    }
}