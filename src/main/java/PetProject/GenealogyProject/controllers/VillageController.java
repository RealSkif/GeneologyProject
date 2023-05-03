package PetProject.GenealogyProject.controllers;

import PetProject.GenealogyProject.models.Village;
import PetProject.GenealogyProject.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/villages")
public class VillageController {

    private final VillageService villageService;

    @Autowired
    public VillageController( VillageService villageService) {
        this.villageService = villageService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("village", villageService.findAll());
        return "villages/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("village", villageService.findOne(id));
        return "villages/show";
    }

    @GetMapping("/new")
    public String newDocument(@ModelAttribute("village") Village village) {
        return "villages/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("village") Village village) {
        villageService.save(village);
        return "redirect:/villages/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("village", villageService.findOne(id));
        return "villages/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("village") Village village, @PathVariable("id") int id) {
        villageService.update(id, village);
        return "redirect:/villages/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        villageService.delete(id);
        return "redirect:/villages/index";
    }
}

