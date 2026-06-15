package com.obando.cv_spring_boot.cv.controller;

import com.obando.cv_spring_boot.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>();

    /*@GetMapping
    public String skills(Model model){
        List<String> skills = List.of("Java", "Spring Boot", "Css", "Spring MVC");
        model.addAttribute("skills", skills);

        return "skills";
    }*/

    @GetMapping
    public String showSkills(@RequestParam(defaultValue = "", required = false) String filter,
                             Model model) {
        List<Skill> skillsFiltered = skills.stream()
                        .filter(skill -> skill
                                .getSkillName()
                                .toLowerCase()
                                .contains(filter.toLowerCase()))
                        .toList();

        model.addAttribute("skills", skillsFiltered);
        //model.addAttribute("skillsFiltered", skillsFiltered);

        return "skills";
    }

    @GetMapping("/{index}")
    public String showSkillDetail(@PathVariable Integer index, Model model) {
        if (index >= 0 && index < skills.size()) {
            Skill skill = skills.get(index);
            model.addAttribute("skill", skill);

            return "skill-detail";
        }

        return "redirect:/skills";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("skill", new Skill());

        return "add-skill";
    }

    @PostMapping("/add")
    public String addSkill(@ModelAttribute Skill skill) {
        skills.add(skill);

        return "redirect:/skills";
    }
}
